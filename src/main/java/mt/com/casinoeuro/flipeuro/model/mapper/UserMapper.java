package mt.com.casinoeuro.flipeuro.model.mapper;

import mt.com.casinoeuro.flipeuro.data.dao.RoleDao;
import mt.com.casinoeuro.flipeuro.data.model.Role;
import mt.com.casinoeuro.flipeuro.data.model.User;
import mt.com.casinoeuro.flipeuro.model.UserRegistration;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * The user mapper class.
 *
 * @author cscib
 * @version 1.0.0
 * @since 26/05/2014 21:42
 */
public class UserMapper {

    public static final String DD_MM_YYYY = "dd-MM-yyyy";

    /**
     * Static methods that maps a ui model and a role into a data User model
     *
     * @param userRegistration {@link UserRegistration}
     * @param role             {@link Role}
     * @return {@link User}
     */
    public static User mapToUser(UserRegistration userRegistration, Role role) throws ParseException {

        User user = new User();
        user.setUsername(userRegistration.getUsername());
        user.setAddress(userRegistration.getAddress());
        user.setBalance(0d);

        Date date = getDateByFormat(userRegistration, DD_MM_YYYY);
        user.setDateOfBirth(new java.sql.Date(date.getTime()));
        user.setFirstname(userRegistration.getFirstName());
        user.setLastname(userRegistration.getLastName());
        user.setPassword(userRegistration.getPassword1());
        user.setRoleByRoleId(role);

        return user;
    }

    /**
     * Retrieves java.util.date given a specific date format.
     *
     * @param userRegistration {@link UserRegistration}}
     * @param format           format
     * @return {@link Date}
     * @throws ParseException
     */
    private static Date getDateByFormat(UserRegistration userRegistration, String format) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        StringBuilder dateInString = new StringBuilder();
        dateInString.append(userRegistration.getBirthdateDay())
                .append("-")
                .append(userRegistration.getBirthdateMonth())
                .append("-")
                .append(userRegistration.getBirthdateDay());

        return sdf.parse(dateInString.toString());
    }
}
