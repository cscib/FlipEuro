package mt.com.casinoeuro.flipeuro.model.mapper;

import mt.com.casinoeuro.flipeuro.data.model.Coin;
import mt.com.casinoeuro.flipeuro.data.model.CoinFlip;
import mt.com.casinoeuro.flipeuro.data.model.User;
import mt.com.casinoeuro.flipeuro.model.CoinDetails;

import java.sql.Date;
import java.util.List;

/**
 * @author cscib
 * @version 1.0.0
 * @since 27/05/2014 01:44
 */
public class CoinMapper {

    /**
     * Maps a Coin Flip.
     *
     * @param user        {@link User}
     * @param coinDetails {@link CoinDetails}
     * @param outcome     HEADS or TAILS
     * @return {@link CoinFlip}
     */
    public static CoinFlip mapToCoinFlip(User user, CoinDetails coinDetails, Coin coin, String outcome) {
        CoinFlip coinFlip = new CoinFlip();
        coinFlip.setBet(coinDetails.getFlipOption());
        coinFlip.setCoinFlipDate(new Date(new java.util.Date().getTime()));
        coinFlip.setCoinByCoinId(coin);
        coinFlip.setUserByUserId(user);

        coinFlip.setOutcome(outcome);
        return coinFlip;
    }
}
