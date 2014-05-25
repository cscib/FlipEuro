package mt.com.casinoeuro.flipeuro.security;

import mt.com.casinoeuro.flipeuro.data.model.Role;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;

import mt.com.casinoeuro.flipeuro.data.dao.UserDao;
import mt.com.casinoeuro.flipeuro.data.model.User;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


/**
 * The FlipEuro realm.
 *
 * @author cscib
 * @version 1.0.0
 * @since 25/05/2014 09:57
 */
@Component
public class FlipEuroRealm extends JdbcRealm {

    @Autowired
    private UserDao userDao;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {

        final String username = (String) principals.getPrimaryPrincipal();

        User user = getUserForUsername(username);

        Set<String> roleSet = new HashSet<String>();
        Role role = user.getRoleByRoleId();
        roleSet.add(role.getRoleName());

        return new SimpleAuthorizationInfo(roleSet);

    }

    private User getUserForUsername(String username) {
        final List<User> users = userDao.getUserByUsername(username);

        if (users == null || users.isEmpty() || users.size() > 1) {

            throw new IllegalStateException("No valid account setup found for user [" + username + "]");

        }
        return users.get(0);

    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(
            AuthenticationToken token) throws AuthenticationException {
        // identify account to log to
        UsernamePasswordToken userPassToken = (UsernamePasswordToken) token;
        final String username = userPassToken.getUsername();

        if (username == null) {
            System.out.println("Username is null.");
            return null;
        }

        // read password hash and salt from db
        final User user = getUserForUsername(username);
        // return salted credentials
        AuthenticationInfo info = new FlipEuroSaltedAuthentificationInfo(
                username, user.getPassword(), "salt");

        return info;

    }


}
