package mt.com.casinoeuro.flipeuro.model;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The credit card deposit model representation.
 *
 * @author cscib
 * @version 1.0.0
 * @since 23/05/14 19.45
 */
@ScriptAssert(lang = "javascript", script = "_this.depositAmount != null && _this.depositAmount > 10", message = "Please deposit more than 10 currency units.")
public class CreditCardDeposit implements Serializable {

    private static final long serialVersionUID = -3726929221488418781L;

    /**
     * The card number.
     */
    @CreditCardNumber(message = "Credit card number is invalid. Luhn test Failed.")
    @NotEmpty
    private String cardNumber;

    /**
     * The card holder's name
     */
    @NotEmpty
    private String cardHoldersName;

    /**
     * The expiry month.
     */
    @Range(min = 1, max = 12, message = "Please enter a valid month")
    @NotNull
    private Byte expiryMonth;

    /**
     * The expiry year.
     */
    @Range(min = 14, max = 20, message = "Please enter a valid year in YY format")
    @NotNull
    private Byte expiryYear;

    /**
     * The credit card Id.
     */
    private long creditCardId;

    /**
     * The cvv.
     */
    @Size(min = 3, max = 3, message = "Please enter a 3 digit CVV.")
    @Pattern(regexp = "(^$|[0-9]{3})", message = "Please enter a 3 digit CVV.")
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
