package mt.com.casinoeuro.flipeuro.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * The user registration model representation.
 *
 * @author cscib
 * @version 1.0.0
 * @since 23/05/14 19.21
 */
public class UserRegistration implements Serializable {

    private static final long serialVersionUID = 6176879792718057872L;

    /**
     * The first name.
     */
    private String firstName;

    /**
     * The last name.
     */
    private String lastName;

    /**
     * The initial balance.
     */
    private BigDecimal initialBalance;

    /**
     * The username.
     */
    private String username;

    /**
     * The password.
     */
    private String password1;

    /**
     * The password verification.
     */
    private String password2;

    /**
     * The address.
     */
    private String address;

    /**
     * Birthday day
     */
    private String birthdateDay;

    /**
     * Birthday day
     */
    private String birthdateMonth;

    /**
     * Birthday day
     */
    private String birthdateYear;

    /**
     * Terms and Conditions
     */
    private String terms;

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getInitialBalance() {
        return initialBalance;
    }

    public String getUsername() {
        return username;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword1() {
        return password1;
    }

    public String getPassword2() {
        return password2;
    }

    public String getBirthdateDay() {
        return birthdateDay;
    }

    public String getBirthdateMonth() {
        return birthdateMonth;
    }

    public String getBirthdateYear() {
        return birthdateYear;
    }

    public String getTerms() {
        return terms;
    }
}
