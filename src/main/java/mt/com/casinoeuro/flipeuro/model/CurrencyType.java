package mt.com.casinoeuro.flipeuro.model;

import java.io.Serializable;

/**
 * The currency type model representation.
 * @author cscib
 * @version 1.0.0
 * @since  23/05/14 20.04
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
