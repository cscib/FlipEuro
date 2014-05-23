package mt.com.casinoeuro.flipeuro.model;

/**
 * The credit card deposit model representation.
 * User: cscib
 * Date: 23/05/14
 * Time: 19.37
 */
public class CreditCardDetails {

    private String cardNumber;

    private String cardHoldersName;

    private String cvv;

    private int expiryMonth;

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
