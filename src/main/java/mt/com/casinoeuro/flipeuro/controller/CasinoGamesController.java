package mt.com.casinoeuro.flipeuro.controller;

import mt.com.casinoeuro.flipeuro.data.model.Coin;
import mt.com.casinoeuro.flipeuro.data.model.CoinFlip;
import mt.com.casinoeuro.flipeuro.data.model.User;
import mt.com.casinoeuro.flipeuro.model.CoinDetails;
import mt.com.casinoeuro.flipeuro.model.UserRegistration;
import mt.com.casinoeuro.flipeuro.model.mapper.CoinMapper;
import mt.com.casinoeuro.flipeuro.util.JsonResponse;
import mt.com.casinoeuro.flipeuro.util.Message;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.io.IOException;
import java.sql.Date;
import java.util.Iterator;
import java.util.List;

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

    @RequestMapping(method = RequestMethod.GET, value = "/games")
    public String renderGamesScreen(ModelMap map) throws IOException {

        Iterator iterator = coinDao.findAll().iterator();

        map.addAttribute("coins", iterator);

        return "casinogames";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/games")
    public void flipCoinGame(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException, IllegalAccessException {

        //UserLoginResponse userLoginResponse = new UserLoginResponse();
        JsonResponse json = new JsonResponse();
        //json.setData(userLoginResponse);

        String username = (String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
        List<User> user = userDao.getUserByUsername(username);
        if (user == null || user.size() != 1) {
            throw new IllegalAccessException("User is empty.");
        }

        CoinDetails coinDetails = this.objectFromStream(servletRequest.getInputStream(), CoinDetails.class);

        //TODO Check if client has enough balance

        Coin coin = coinDao.getCoinByCoinId(coinDetails.getCoinId()).get(0);

        CoinFlip coinFlip = CoinMapper.mapToCoinFlip(user, coinDetails, coin, HEADS);
        coinFlipDao.save(coinFlip);

        // TODO Set the new Balance !!

        if (coinFlip.getOutcome().equals(coinFlip.getBet())) {
            json.addMessage(new Message("Congratulations !! You have won", Message.Type.INFO));
        } else {
            json.addMessage(new Message("We are sorry !! You lost.", Message.Type.INFO));
        }
        this.objectToStream(servletResponse.getOutputStream(), json);
    }


}
