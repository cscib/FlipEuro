package mt.com.casinoeuro.flipeuro.data.dao;

import mt.com.casinoeuro.flipeuro.data.model.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Credit Card Dao interface.
 * @author cscib
 * @version 1.0.0
 * @since 24/05/2014 14:33
 */
public interface CreditCardDao extends JpaRepository<CreditCard, Long> {
}
