package mt.com.casinoeuro.flipeuro.data.dao;

import mt.com.casinoeuro.flipeuro.data.model.User;
import org.springframework.data.repository.CrudRepository;

/**
 * The User Dao interface.
 * @author cscib
 * @version 1.0.0
 * @since 24/05/2014 14:28
 */
public interface UserDao extends CrudRepository<User, Long> {
}
