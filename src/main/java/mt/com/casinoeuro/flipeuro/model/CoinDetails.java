package mt.com.casinoeuro.flipeuro.model;

/**
 * The coin details model representation.
 * User: Caroline
 * Date: 23/05/14
 * Time: 19.59
 */
public class CoinDetails {

    /** The name. */
    private String name;

    /** The denomination. */
    private String denomination;

    /** The currency. */
    private CurrencyType currency;

    public String getName() {
        return name;
    }

    public String getDenomination() {
        return denomination;
    }

    public CurrencyType getCurrency() {
        return currency;
    }
}
