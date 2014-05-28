package mt.com.casinoeuro.flipeuro.controller;

import mt.com.casinoeuro.flipeuro.data.model.CreditCard;
import mt.com.casinoeuro.flipeuro.data.model.Deposit;
import mt.com.casinoeuro.flipeuro.data.model.User;
import mt.com.casinoeuro.flipeuro.model.*;
import mt.com.casinoeuro.flipeuro.model.mapper.CreditCardDepositMapper;
import mt.com.casinoeuro.flipeuro.util.JsonResponse;
import mt.com.casinoeuro.flipeuro.util.Message;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * The Deposit Money Controller.
 *
 * @author cscib
 * @version 1.0.0
 * @since 26/05/2014 19:35
 */
@Controller
public class DepositController extends BaseController {


    @RequestMapping(method = RequestMethod.GET, value = "/deposit")
    public String renderDepositScreen() throws IOException {

        return "deposit";
    }

    /**
     * The deposit request method.
     *
     * @param servletRequest  the servlet request
     * @param servletResponse the servlet response
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/deposit")
    public void deposit(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {

        UserLoginResponse userLoginResponse = new UserLoginResponse();
        JsonResponse json = new JsonResponse();
        json.setData(userLoginResponse);

        try {

            // Retrieve the logged in username
            String loggedUsername = getUserNameFromPrincipal();

            User loggedUser = userDao.getUserByUsername(loggedUsername).get(0);

            // Retrieve the object CreditCardDeposit from the request
            CreditCardDeposit creditCardDeposit = this.objectFromStream(servletRequest.getInputStream(), CreditCardDeposit.class);

            // Validate
            validate(creditCardDeposit, CreditCardDeposit.class, json);
            if (json.getMessages() != null && !json.getMessages().isEmpty()) {
                this.objectToStream(servletResponse.getOutputStream(), json);
                return;
            }

            List<CreditCard> creditCards = creditCardDao.getCreditCardByCardNumber(creditCardDeposit.getCardNumber());

            CreditCard creditCard = null;
            // If a credit card already exists validate it has the same user logged in and overwrite details
            if (creditCards != null && creditCards.size() > 0) {
                // There should be one credit Card with that number
                creditCard = creditCards.get(0);

                // Retrieve the user registered with the credit card
                User user = userDao.findOne(creditCard.getUserId());

                // Compare the saved credit card's user with the user logged in and return error message if not equal
                if (!user.getUsername().equals(loggedUsername)) {
                    json.addMessage(new Message("Credit Card is registered to another user", Message.Type.ERROR));
                    this.objectToStream(servletResponse.getOutputStream(), json);
                    return;
                }
                // Overwrite with the new details
                creditCard = CreditCardDepositMapper.mapCreditCardDetails(creditCard, loggedUser, creditCardDeposit);
                creditCardDao.save(creditCard);
            } else {
                creditCard = CreditCardDepositMapper.mapCreditCardDetails(null, loggedUser, creditCardDeposit);
                creditCard.setUserByUserId(loggedUser);
                creditCardDao.save(creditCard);
            }

            Deposit deposit = CreditCardDepositMapper.mapDeposit(creditCardDeposit, creditCard, loggedUser);
            depositDao.save(deposit);

            loggedUser.setBalance(loggedUser.getBalance() + deposit.getDepositAmount());
            userDao.save(loggedUser);

            userLoginResponse.setUsername(loggedUsername);
            userLoginResponse.setSuccessful(true);
            userLoginResponse.setBalance(loggedUser.getBalance());

        } finally {
            this.objectToStream(servletResponse.getOutputStream(), json);
        }

    }


}
