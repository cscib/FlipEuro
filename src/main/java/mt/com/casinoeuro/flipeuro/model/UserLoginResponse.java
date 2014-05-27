package mt.com.casinoeuro.flipeuro.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Caroline
 * @version 1.0.0
 * @since 26/05/2014 20:20
 */
public class UserLoginResponse implements Serializable {

    private static final long serialVersionUID = 4984817443180059990L;

    /**
     * The username.
     */
    private String username;

    /**
     * The balance.
     */
    private Double balance;

    /**
     * Successful.
     */
    private boolean successful = false;

    /**
     * Retrieve username.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Is successful.
     */
    public boolean isSuccessful() {
        return successful;
    }

    /**
     * Set successful.
     */
    public void setSuccessful(boolean successful) {
        this.successful = successful;
    }

    /**
     * Retrieves balance.
     *
     * @return balance
     */
    public Double getBalance() {
        return balance;
    }

    /**
     * Sets the balance.
     *
     * @param balance balance
     */
    public void setBalance(Double balance) {
        this.balance = balance;
    }
}
