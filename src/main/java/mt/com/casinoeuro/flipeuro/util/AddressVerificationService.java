package mt.com.casinoeuro.flipeuro.util;

import org.springframework.stereotype.Component;

/**
 * A util class that validates the address.
 *
 * @author cscib
 * @version 1.0.0
 * @since 28/05/2014 03:18
 */
@Component
public class AddressVerificationService {

    public boolean verifyAddress(String address) {
        if (address != null && !address.isEmpty()) {
            return true;
        }
        return false;
    }

}
