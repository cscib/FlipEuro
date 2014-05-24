package mt.com.casinoeuro.flipeuro.model;

import java.math.BigDecimal;

/**
 * The coin details model representation.
 * @author cscib
 * @version 1.0.0
 * @since  23/05/14 19.59
 */
public class CoinDetails {

    /** The name. */
    private String name;

    /** The denomination. */
    private BigDecimal denomination;

    /** The currency. */
    private CurrencyType currency;

    public String getName() {
        return name;
    }

    public BigDecimal getDenomination() {
        return denomination;
    }

    public CurrencyType getCurrency() {
        return currency;
    }
}
