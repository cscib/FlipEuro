package mt.com.casinoeuro.flipeuro.data.dao;

import mt.com.casinoeuro.flipeuro.data.model.Coin;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The Coin Dao interface.
 * @author cscib
 * @version 1.0.0
 * @since 24/05/2014 14:37
 */
public interface CoinDao extends CrudRepository<Coin, Long> {

    /**
     * Retrieves a coin by coin id.
     *
     * @param coinId the coinId
     * @return a list of coins
     */
    List<Coin> getCoinByCoinId(Long coinId);
}
