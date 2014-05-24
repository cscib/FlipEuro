package mt.com.casinoeuro.flipeuro.model;

/**
 * The credit card deposit model representation.
 * @author cscib
 * @version 1.0.0
 * @since  23/05/14 19.37
 */
public class CreditCardDetails {

    /** The card number. */
    private String cardNumber;

    /** The card holder's name */
    private String cardHoldersName;

    /** The expiry month. */
    private int expiryMonth;

    /** The expiry year. */
    private int expiryYear;

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHoldersName() {
        return cardHoldersName;
    }

    public int getExpiryMonth() {
        return expiryMonth;
    }

    public int getExpiryYear() {
        return expiryYear;
    }

}
