package mt.com.casinoeuro.flipeuro.model;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The user registration model representation.
 * User: cscib
 * Date: 23/05/14
 * Time: 19.21
 *
 */
public class UserRegistration {

    /** The first name. */
    private String firstName;

    /** The last name. */
    private String lastName;

    /** Date of Birth. */
    private Date dateOfBirth;

    /** The initial balance. */
    private BigDecimal initialBalance;

    /** The username. */
    private String username;

    /** The password. */
    private String password;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

}
