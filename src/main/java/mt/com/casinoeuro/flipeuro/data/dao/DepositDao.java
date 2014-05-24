package mt.com.casinoeuro.flipeuro.data.dao;

import mt.com.casinoeuro.flipeuro.data.model.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The Deposit Dao interface.
 * @author cscib
 * @version 1.0.0
 * @since 24/05/2014 14:34
 */
public interface DepositDao extends JpaRepository<Deposit, Long> {
}
