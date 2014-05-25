package mt.com.casinoeuro.flipeuro.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * The credit card deposit model representation.
 * @author cscib
 * @version 1.0.0
 * @since  23/05/14 19.45
 */
public class CreditCardDeposit implements Serializable {

    private static final long serialVersionUID = -3726929221488418781L;

    /** The credit card Id. */
    private long creditCardId;

    /** The cvv. */
    private String cvv;

    /** The deposit amount. */
    private BigDecimal depositAmount;

    public long getCreditCardId() {
        return creditCardId;
    }

    public String getCvv() {
        return cvv;
    }

    public BigDecimal getDepositAmount() {
        return depositAmount;
    }
}
