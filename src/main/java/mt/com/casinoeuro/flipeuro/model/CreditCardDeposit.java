package mt.com.casinoeuro.flipeuro.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The credit card deposit model representation.
 *
 * @author cscib
 * @version 1.0.0
 * @since 23/05/14 19.45
 */
public class CreditCardDeposit implements Serializable {

    private static final long serialVersionUID = -3726929221488418781L;

    /**
     * The card number.
     */
    private String cardNumber;

    /**
     * The card holder's name
     */
    private String cardHoldersName;

    /**
     * The expiry month.
     */
    private Byte expiryMonth;

    /**
     * The expiry year.
     */
    private Byte expiryYear;

    /**
     * The credit card Id.
     */
    private long creditCardId;

    /**
     * The cvv.
     */
    private String cvv;

    /**
     * The deposit amount.
     */
    private Double depositAmount;

    public long getCreditCardId() {
        return creditCardId;
    }

    public String getCvv() {
        return cvv;
    }

    public Double getDepositAmount() {
        return depositAmount;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardHoldersName() {
        return cardHoldersName;
    }

    public Byte getExpiryMonth() {
        return expiryMonth;
    }

    public Byte getExpiryYear() {
        return expiryYear;
    }
}
