package mt.com.casinoeuro.flipeuro.controller;

import mt.com.casinoeuro.flipeuro.data.dao.*;
import mt.com.casinoeuro.flipeuro.data.model.Coin;
import mt.com.casinoeuro.flipeuro.data.model.Role;
import mt.com.casinoeuro.flipeuro.data.model.User;
import mt.com.casinoeuro.flipeuro.model.CurrencyType;
import mt.com.casinoeuro.flipeuro.util.JsonResponse;
import mt.com.casinoeuro.flipeuro.util.Message;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;

/**
 * An abstract class to be the parent of other controllers.
 *
 * @author cscib
 * @version 1.0.0
 * @since 25/05/2014 23:16
 */
public abstract class BaseController {

    /* The logger. */
    private static final Logger log = Logger.getLogger(BaseController.class);

    /* The Hibernate Validator */
    @Autowired
    private Validator validator;

    /* The user Dao */
    @Autowired
    protected UserDao userDao;

    /* The role Dao */
    @Autowired
    protected RoleDao roleDao;

    /* The coin Dao */
    @Autowired
    protected CoinDao coinDao;

    /* The coin flip Dao */
    @Autowired
    protected CoinFlipDao coinFlipDao;

    /* The creidt card Dao */
    @Autowired
    protected CreditCardDao creditCardDao;

    /* The deposit Dao */
    @Autowired
    protected DepositDao depositDao;


    /* Emumerator Roles */
    protected enum Roles {
        normal,
        backoffice
    }

    /**
     * Reads an entire JSON object from the input stream, converting it to the specified type {@code T}.
     *
     * @param stream The {@link InputStream} from which the JSON object is to be read.
     * @param type   The type of class the object is to be converted to.
     * @param <T>    The type placeholder.
     * @return A new instance of type {@code T}.
     * @throws IOException An error occurred while reading from the input stream.
     */
    public static <T> T objectFromStream(InputStream stream, Class<T> type) throws IOException {
        ObjectMapper mapper = createConfiguredObjectMapper();
        return mapper.readValue(stream, type);
    }

    /**
     * Writes an entire object to JSON to the output stream.
     *
     * @param stream The {@link OutputStream} to which the object is to be written.
     * @param object The object to write.
     * @throws IOException An error occurred while writing to the output stream.
     */
    public static void objectToStream(OutputStream stream, Object object) throws IOException {
        ObjectMapper mapper = createConfiguredObjectMapper();
        mapper.writeValue(stream, object);
    }

    /**
     * Creates a new and configured instance of a {@code Jackson} {@link ObjectMapper}
     * instance. The following (explicitly defined) configurations apply:
     * <ul>
     * <li>{@code SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING} is enabled</li>
     * <li>{@code DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING} is enabled</li>
     * <li>{@code SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS} is disabled</li>
     * <li>Date format is set to {@code dd-MM-yyyy}</li>
     * </ul>
     *
     * @return A new configured {@code ObjectMapper} instance.
     */
    private static ObjectMapper createConfiguredObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.enable(SerializationConfig.Feature.WRITE_ENUMS_USING_TO_STRING);
        mapper.enable(DeserializationConfig.Feature.READ_ENUMS_USING_TO_STRING);
        mapper.disable(SerializationConfig.Feature.WRITE_DATES_AS_TIMESTAMPS);
        mapper.setDateFormat(new SimpleDateFormat("dd-MM-yyyy"));
        return mapper;
    }

    protected void bootstrap() {
        if (roleDao.count() == 0) {
            log.info("Bootstrapping roles !!");
            Role role = new Role();
            role.setRoleName(Roles.normal.name());
            roleDao.save(role);

            role = new Role();
            role.setRoleName(Roles.backoffice.name());
            roleDao.save(role);
        }
        if (userDao.count() == 0) {
            log.info("Bootstrapping admin !!");
            Role role = roleDao.getRoleByRolename(Roles.backoffice.name()).get(0);
            User user = new User();
            user.setUsername("admin");
            user.setPassword("l3tm31n");
            user.setRoleByRoleId(role);
            userDao.save(user);
        }

        if (coinDao.count() == 0) {
            log.info("Bootstrapping coins !!");
            Coin coin = new Coin();
            coin.setCurrency(CurrencyType.GBP.name());
            coin.setDenomination(10d);
            coin.setName("Lucky Stars");
            coinDao.save(coin);

            coin = new Coin();
            coin.setCurrency(CurrencyType.EUR.name());
            coin.setDenomination(20d);
            coin.setName("Silver Spring");
            coinDao.save(coin);

            coin = new Coin();
            coin.setCurrency(CurrencyType.EUR.name());
            coin.setDenomination(50d);
            coin.setName("Gold Mine");
            coinDao.save(coin);
        }
    }

    /**
     * Retrieves the username from the principal object.
     *
     * @return username
     */
    protected String getUserNameFromPrincipal() {
        return (String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal();
    }

    /**
     * Validates a form object using the Hibernate library, and fills the json respnse.
     *
     * @param formObject
     * @param type
     * @param json
     * @param <T>
     */
    protected <T> void validate(T formObject, Class<T> type, JsonResponse json) {

        Set<ConstraintViolation<T>> constraintViolations = validator.validate(formObject);

        Iterator<ConstraintViolation<T>> constraintIterator = constraintViolations.iterator();

        while (constraintIterator.hasNext()) {

            ConstraintViolation<T> constraintViolation = constraintIterator.next();

            json.addMessage(new Message(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage(), Message.Type.ERROR));
        }

    }
}
