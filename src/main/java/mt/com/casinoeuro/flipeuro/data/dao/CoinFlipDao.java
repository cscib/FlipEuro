package mt.com.casinoeuro.flipeuro.data.dao;

import mt.com.casinoeuro.flipeuro.data.model.CoinFlip;
import org.springframework.data.repository.CrudRepository;

/**
 * The Coin Flip Dao interface.
 * @author cscib
 * @version 1.0.0
 * @since 24/05/2014 14:32
 */
public interface CoinFlipDao extends CrudRepository<CoinFlip, Long> {
}
