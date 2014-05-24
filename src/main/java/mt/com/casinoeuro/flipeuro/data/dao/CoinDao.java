package mt.com.casinoeuro.flipeuro.data.dao;

import mt.com.casinoeuro.flipeuro.data.model.Coin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Coin Dao interface.
 * @author cscib
 * @version 1.0.0
 * @since 24/05/2014 14:37
 */
public interface CoinDao extends JpaRepository<Coin, Long> {
}
