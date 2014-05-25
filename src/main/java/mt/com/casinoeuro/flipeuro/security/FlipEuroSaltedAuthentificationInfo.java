package mt.com.casinoeuro.flipeuro.security;

import org.apache.shiro.authc.AuthenticationInfo;

import org.apache.shiro.authc.SaltedAuthenticationInfo;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.apache.shiro.util.SimpleByteSource;

/**
 * Flip Euro SaltedAuthentificationInfo implementation.
 *
 * @author cscib
 * @version 1.0.0
 * @since 25/05/2014 10:05
 */
public class FlipEuroSaltedAuthentificationInfo implements AuthenticationInfo {


    private static final long serialVersionUID = 8411581036532602572L;

    private final String username;
    private final String password;
    private final String salt;

    public FlipEuroSaltedAuthentificationInfo(String username, String password,
                                              String salt) {
        this.username = username;
        this.password = password;
        this.salt = salt;
    }

    @Override
    public Object getCredentials() {
        return password;
    }

    //@Override
    public org.apache.shiro.util.ByteSource getCredentialsSalt() {
        return new SimpleByteSource(Base64.decode(salt));
    }

    @Override
    public PrincipalCollection getPrincipals() {
        PrincipalCollection coll = new SimplePrincipalCollection(username,
                username);
        return coll;
    }
}
