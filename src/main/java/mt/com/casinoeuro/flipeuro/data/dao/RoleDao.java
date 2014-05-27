package mt.com.casinoeuro.flipeuro.data.dao;

import mt.com.casinoeuro.flipeuro.data.model.Role;
import mt.com.casinoeuro.flipeuro.data.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * The Role Dao interface
 * @author cscib
 * @version 1.0.0
 * @since 24/05/2014 14:30
 */
public interface RoleDao extends CrudRepository<Role, Long> {

    /**
     * Retrieves a role by rolename
     *
     * @param rolename the rolename
     * @return rolenames
     */
    List<Role> getRoleByRolename(String rolename);
}
