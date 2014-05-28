package mt.com.casinoeuro.flipeuro.controller;

import mt.com.casinoeuro.flipeuro.data.model.Coin;
import mt.com.casinoeuro.flipeuro.data.model.CoinFlip;
import mt.com.casinoeuro.flipeuro.data.model.User;
import mt.com.casinoeuro.flipeuro.model.CoinDetails;
import mt.com.casinoeuro.flipeuro.model.mapper.CoinMapper;
import mt.com.casinoeuro.flipeuro.util.JsonResponse;
import mt.com.casinoeuro.flipeuro.util.Message;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * The Casino Games Controller.
 *
 * @author cscib
 * @version 1.0.0
 * @since 26/05/2014 19:36
 */
@Controller
public class CasinoGamesController extends BaseController {

    public static final String HEADS = "HEADS";
    public static final String TAILS = "TAILS";
    private static Random random = new Random();

    @RequestMapping(method = RequestMethod.GET, value = "/games")
    public String renderGamesScreen(ModelMap map) throws IOException {

        Iterator iterator = coinDao.findAll().iterator();

        map.addAttribute("coins", iterator);

        return "casinogames";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/games")
    public void flipCoinGame(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException, IllegalAccessException {

        JsonResponse json = new JsonResponse();

        try {
            String username = getUserNameFromPrincipal();
            List<User> users = userDao.getUserByUsername(username);
            if (users == null || users.size() != 1) {
                json.addMessage(new Message("User does not exist in database.", Message.Type.ERROR));
                return;
            }

            // Retrieve coinDetails from stream
            CoinDetails coinDetails = this.objectFromStream(servletRequest.getInputStream(), CoinDetails.class);

            User user = users.get(0);
            Coin coin = coinDao.getCoinByCoinId(coinDetails.getCoinId()).get(0);

            if (user.getBalance() >= coin.getDenomination()) {

                CoinFlip coinFlip = CoinMapper.mapToCoinFlip(user, coinDetails, coin, getRandomOutcome());
                coinFlipDao.save(coinFlip);

                if (coinFlip.getOutcome().equals(coinFlip.getBet())) {
                    user.setBalance(user.getBalance() + coin.getDenomination() * 2);
                    userDao.save(user);
                    json.addMessage(new Message("Congratulations !! You have won", Message.Type.INFO));
                } else {
                    user.setBalance(user.getBalance() - coin.getDenomination());
                    userDao.save(user);
                    json.addMessage(new Message("We are sorry !! You lost.", Message.Type.INFO));
                }
            } else {
                json.addMessage(new Message("We are sorry but your account has not enough balance !!." + user.getBalance(), Message.Type.ERROR));
            }


        } catch (Exception e) {
            json.addMessage(new Message("Error encountered !!", Message.Type.ERROR));
        } finally {
            this.objectToStream(servletResponse.getOutputStream(), json);
        }
    }


    /*
    * Returns a random game outcome
     */
    private String getRandomOutcome() {

        if (random.nextBoolean()) {
            return HEADS;
        } else {
            return TAILS;
        }
    }

}
