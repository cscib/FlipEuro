package mt.com.casinoeuro.flipeuro.model.mapper;

import mt.com.casinoeuro.flipeuro.data.model.CreditCard;
import mt.com.casinoeuro.flipeuro.data.model.Deposit;
import mt.com.casinoeuro.flipeuro.data.model.User;
import mt.com.casinoeuro.flipeuro.model.CreditCardDeposit;

import java.util.Date;

/**
 * A mapper clas used to map credit card details and deposits.
 *
 * @author cscib
 * @version 1.0.0
 * @since 27/05/2014 21:11
 */
public class CreditCardDepositMapper {

    /**
     * Static class that maps various objects into a Deposit data model.
     *
     * @param creditCardDeposit {@link CreditCardDeposit}
     * @param creditCard        the {@link CreditCard}
     * @param loggedUser        {@link User}
     * @return {@link Deposit}
     */
    public static Deposit mapDeposit(CreditCardDeposit creditCardDeposit, CreditCard creditCard, User loggedUser) {
        Deposit deposit = new Deposit();
        deposit.setUserByUserId(loggedUser);
        deposit.setCreditCardByCreditCardId(creditCard);
        deposit.setDepositAmount(creditCardDeposit.getDepositAmount());
        deposit.setDepositDate(new java.sql.Date(new Date().getTime()));
        return deposit;
    }

    /**
     * Msps credit card deposit details to Credit Card data model.
     *
     * @param creditCard        {@link CreditCard}
     * @param user              {@link User}
     * @param creditCardDeposit {@link CreditCardDeposit}
     * @return {@link CreditCard}
     */
    public static CreditCard mapCreditCardDetails(CreditCard creditCard, User user, CreditCardDeposit creditCardDeposit) {
        if (creditCard == null) {
            creditCard = new CreditCard();
        }

        creditCard.setUserByUserId(user);
        creditCard.setCardHoldersName(creditCardDeposit.getCardHoldersName());
        creditCard.setCardNumber(creditCardDeposit.getCardNumber());
        creditCard.setExpiryMonth(creditCardDeposit.getExpiryMonth());
        creditCard.setExpiryYear(creditCardDeposit.getExpiryYear());

        return creditCard;
    }
}
