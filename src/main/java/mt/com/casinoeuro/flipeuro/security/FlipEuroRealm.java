package mt.com.casinoeuro.flipeuro.security;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.realm.jdbc.JdbcRealm;

import mt.com.casinoeuro.flipeuro.data.dao.UserDao;
import mt.com.casinoeuro.flipeuro.data.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * The FlipEuro realm.
 *
 * @author cscib
 * @version 1.0.0
 * @since 25/05/2014 09:57
 */
public class FlipEuroRealm extends JdbcRealm {

    private UserDao userDao;

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
        //final User user = userDao.getUserByUsername(username);
        User user = userDao.findOne(1L);

        if (user == null) {
            System.out.println("No account found for user [" + username + "]");
            return null;
        }

        // return salted credentials
        AuthenticationInfo info = new FlipEuroSaltedAuthentificationInfo(
                username, user.getPassword(), "salt");

        return info;

    }


    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

}
