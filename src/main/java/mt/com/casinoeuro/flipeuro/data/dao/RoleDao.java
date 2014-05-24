package mt.com.casinoeuro.flipeuro.data.dao;

import mt.com.casinoeuro.flipeuro.data.model.Role;
import org.springframework.data.repository.CrudRepository;

/**
 * The Role Dao interface
 * @author cscib
 * @version 1.0.0
 * @since 24/05/2014 14:30
 */
public interface RoleDao extends CrudRepository<Role, Long> {
}
