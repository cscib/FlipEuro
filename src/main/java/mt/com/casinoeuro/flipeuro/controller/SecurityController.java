package mt.com.casinoeuro.flipeuro.controller;

import mt.com.casinoeuro.flipeuro.data.model.Role;
import mt.com.casinoeuro.flipeuro.data.model.User;
import mt.com.casinoeuro.flipeuro.model.UserLogin;
import mt.com.casinoeuro.flipeuro.model.UserLoginResponse;
import mt.com.casinoeuro.flipeuro.model.UserRegistration;
import mt.com.casinoeuro.flipeuro.model.mapper.UserMapper;
import mt.com.casinoeuro.flipeuro.util.AddressVerificationService;
import mt.com.casinoeuro.flipeuro.util.JsonResponse;
import mt.com.casinoeuro.flipeuro.util.Message;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;


/**
 * A security controller used to perform login handling.
 *
 * @author cscib
 * @version 1.0.0
 * @since 25/05/2014 21:20
 */
@Controller
public class SecurityController extends BaseController {

    private static final Logger log = Logger.getLogger(SecurityController.class);

    @Autowired
    private AddressVerificationService addressVerificationService;

    /**
     * This method is used to call the bootstrap to make sure there is test data in DB.
     */
    @PostConstruct
    public void initialize() {
        bootstrap();
    }


    /**
     * Login Request Method.
     *
     * @param servletRequest  the servlet request
     * @param servletResponse the servlet response
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public void loginPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {

        UserLoginResponse userLoginResponse = new UserLoginResponse();
        JsonResponse json = new JsonResponse();
        json.setData(userLoginResponse);

        try {
            UserLogin userLogin = this.objectFromStream(servletRequest.getInputStream(), UserLogin.class);

            //Assumption : Remember me functionality not supported in this scope
            UsernamePasswordToken token = new UsernamePasswordToken(userLogin.getUsername(), userLogin.getPassword(), false);
            SecurityUtils.getSubject().login(token);
            //Return status and user information
            userLoginResponse.setUsername(userLogin.getUsername());
            userLoginResponse.setSuccessful(true);

            List<User> users = userDao.getUserByUsername(userLogin.getUsername());
            userLoginResponse.setBalance(users.get(0).getBalance());
        } finally {
            this.objectToStream(servletResponse.getOutputStream(), json);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/register")
    public String renderRegistrationScreen() throws IOException {

        return "registration";
    }

    /**
     * The login GET request method. Returns a string indicating login requirement.
     *
     * @return {@link String} a message
     */
    @RequestMapping(method = RequestMethod.GET, value = "/login")
    public
    @ResponseBody
    String logIn() {
        return "You are not logged in. Please login to continue accessing this website.";
    }


    /**
     * The logout request method.
     *
     * @return {@link String} a message
     */
    @RequestMapping(method = RequestMethod.GET, value = "/logout")
    public
    @ResponseBody
    String logOut() {

        try {
            SecurityUtils.getSubject().logout();
        } catch (AuthenticationException e) {
            //Return generic login error so as not to help hackers.
            return "Error Logging out";
        }
        return "Logged out";

    }


    /**
     * The register request method.
     *
     * @param servletRequest  {@link HttpServletRequest}
     * @param servletResponse {@link HttpServletResponse}
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/isLoggedIn")
    public void isLoggedIn(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {

        UserLoginResponse userLoginResponse = new UserLoginResponse();
        JsonResponse json = new JsonResponse();
        json.setData(userLoginResponse);
        try {
            boolean authResult = SecurityUtils.getSubject().isAuthenticated();
            //Return status and user information
            userLoginResponse.setSuccessful(authResult);
            if (authResult) {
                userLoginResponse.setUsername((String) SecurityUtils.getSubject().getPrincipals().getPrimaryPrincipal());
                List<User> users = userDao.getUserByUsername(userLoginResponse.getUsername());
                userLoginResponse.setBalance(users.get(0).getBalance());
            }
        } finally {
            this.objectToStream(servletResponse.getOutputStream(), json);
        }
    }


    /**
     * The register request method
     *
     * @param servletRequest  the servlet request
     * @param servletResponse the servlet response
     * @throws IOException
     */
    @RequestMapping(method = RequestMethod.POST, value = "/register")
    public void registerUser(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {

        UserLoginResponse userLoginResponse = new UserLoginResponse();
        JsonResponse json = new JsonResponse();
        json.setData(userLoginResponse);

        try {

            UserRegistration userRegistration = this.objectFromStream(servletRequest.getInputStream(), UserRegistration.class);
            validate(userRegistration, UserRegistration.class, json);
            if (json.getMessages() != null && !json.getMessages().isEmpty()) {
                this.objectToStream(servletResponse.getOutputStream(), json);
                return;
            }

            if (!addressVerification(userRegistration.getAddress())) {
                json.addMessage(new Message("Supplied address fails verification checks. Please enter a valid address.", Message.Type.ERROR));
                return;
            }

            if (existsUsers(userRegistration.getUsername())) {
                json.addMessage(new Message("User " + userRegistration.getUsername() + " already exists!", Message.Type.ERROR));
                return;
            }

            // Only 1 row should be retrieved
            Role role = roleDao.getRoleByRolename(Roles.normal.name()).get(0);

            User user = null;

            user = UserMapper.mapToUser(userRegistration, role);
            user.setBalance(50d);
            userDao.save(user);

            //Assumption : Remember me functionality not supported in this scope
            UsernamePasswordToken token = new UsernamePasswordToken(userRegistration.getUsername(), userRegistration.getPassword1(), false);

            SecurityUtils.getSubject().login(token);
            //Return status and user information
            userLoginResponse.setUsername(userRegistration.getUsername());
            userLoginResponse.setSuccessful(true);
            userLoginResponse.setBalance(user.getBalance());

        } catch (ParseException e) {
            json.addMessage(new Message("Birth date validation failed", Message.Type.ERROR));
        } finally {
            this.objectToStream(servletResponse.getOutputStream(), json);
        }

    }

    private boolean addressVerification(String address) {
        return addressVerificationService.verifyAddress(address);
    }

    /**
     * Checks if the username already exists.
     *
     * @param username the username
     * @return true or false
     */
    private boolean existsUsers(String username) {
        final List<User> users = userDao.getUserByUsername(username);

        if (users != null && users.size() > 0) {
            return true;
        }
        return false;
    }


}

