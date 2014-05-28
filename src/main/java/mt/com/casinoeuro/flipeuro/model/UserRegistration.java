package mt.com.casinoeuro.flipeuro.model;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.ScriptAssert;

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
@ScriptAssert(lang = "javascript", script = "_this.password1.equals(_this.password2)", message = "Passwords are not identical.")
public class UserRegistration implements Serializable {

    private static final long serialVersionUID = 6176879792718057872L;

    /**
     * The first name.
     */
    @NotEmpty
    private String firstName;

    /**
     * The last name.
     */
    @NotEmpty
    private String lastName;

    /**
     * The initial balance.
     */
    private BigDecimal initialBalance;

    /**
     * The username.
     */
    @NotEmpty
    private String username;

    /**
     * The password.
     */
    @NotEmpty
    private String password1;

    /**
     * The password verification.
     */
    @NotEmpty
    private String password2;

    /**
     * The address.
     */
    private String address;

    /**
     * Birthday day
     */
    @NotEmpty
    private String birthdateDay;

    /**
     * Birthday day
     */
    @NotEmpty
    private String birthdateMonth;

    /**
     * Birthday day
     */
    @NotEmpty
    private String birthdateYear;

    /**
     * Terms and Conditions
     */
    @NotEmpty(message = "Please accept the terms and conditions.")
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
