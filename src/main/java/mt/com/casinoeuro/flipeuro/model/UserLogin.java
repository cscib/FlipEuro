package mt.com.casinoeuro.flipeuro.model;

import java.io.Serializable;

/**
 * The user login model representation.
 * @author cscib
 * @version 1.0.0
 * @since 23/05/14 19.31
 */
public class UserLogin implements Serializable {

    private static final long serialVersionUID = -5238966029748302045L;

    /** The username. */
    private String username;

    /** The password. */
    private String password;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
