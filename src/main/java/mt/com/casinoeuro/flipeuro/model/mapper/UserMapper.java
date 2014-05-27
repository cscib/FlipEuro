package mt.com.casinoeuro.flipeuro.model.mapper;

import mt.com.casinoeuro.flipeuro.data.dao.RoleDao;
import mt.com.casinoeuro.flipeuro.data.model.Role;
import mt.com.casinoeuro.flipeuro.data.model.User;
import mt.com.casinoeuro.flipeuro.model.UserRegistration;

/**
 * @author Caroline
 * @version 1.0.0
 * @since 26/05/2014 21:42
 */
public class UserMapper {

    public static User mapToUser(UserRegistration userRegistration, Role role) {

        User user = new User();
        user.setUsername(userRegistration.getUsername());
        user.setAddress(userRegistration.getAddress());
        user.setBalance(0d);
        // TODO Continue the mapping
        //user.setDateOfBirth(userRegistration.getDateOfBirth());
        user.setFirstname(userRegistration.getFirstName());
        user.setLastname(userRegistration.getLastName());
        user.setPassword(userRegistration.getPassword1());
        user.setRoleByRoleId(role);

        return user;
    }
}
