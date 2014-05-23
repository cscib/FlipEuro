package mt.com.casinoeuro.flipeuro.model;

import java.math.BigDecimal;

/**
 * The credit card deposit model representation.
 * User: cscib
 * Date: 23/05/14
 * Time: 19.45
 */
public class CreditCardDeposit {

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
