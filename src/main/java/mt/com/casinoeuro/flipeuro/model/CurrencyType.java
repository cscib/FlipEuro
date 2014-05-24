package mt.com.casinoeuro.flipeuro.model;

/**
 * The currency type model representation.
 * User: cscib
 * Date: 23/05/14
 * Time: 20.04
 */
public enum CurrencyType {

    EUR("Euro"),
    GBP("Pound sterling"),
    CHF("Swiss franc"),
    SEK("Swedish kronor"),
    NOK("Norwegian krone"),
    PLN("Polish zloty"),
    USD("United States dollar");

    private String displayName;

    private CurrencyType(String displayName) {
        this.displayName = displayName;
    }
}
