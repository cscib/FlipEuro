package mt.com.casinoeuro.flipeuro.controller;

import mt.com.casinoeuro.flipeuro.data.dao.RoleDao;
import mt.com.casinoeuro.flipeuro.data.dao.UserDao;
import mt.com.casinoeuro.flipeuro.data.model.Role;
import mt.com.casinoeuro.flipeuro.data.model.User;
import mt.com.casinoeuro.flipeuro.model.UserLogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
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


    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    //TODO - DELETE This method
    @RequestMapping(method = RequestMethod.GET, value = "/testAddUSer")
    public
    @ResponseBody
    String testAddUser(@RequestParam String username, @RequestParam String password, @RequestParam String roleName) {

        final List<User> users = userDao.getUserByUsername(username);

        if (users != null && users.size() > 0) {
            return "User " + username + " already exists!";
        }

        Role role = new Role();
        role.setRoleName(roleName);
        roleDao.save(role);

        User user = new User();
        user.setRoleByRoleId(role);
        user.setUsername(username);
        user.setPassword(password);

        userDao.save(user);

        return "USER Created";
    }


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

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public void loginPost(HttpServletRequest servletRequest, HttpServletResponse servletResponse) throws IOException {

        servletResponse.setContentType("application/json");

        // Get the printwriter object from response to write the required json object to the output stream
        PrintWriter out = servletResponse.getWriter();

        UserLogin userLogin = this.objectFromStream(servletRequest.getInputStream(), UserLogin.class);

        //Assumption : Remember me functionality not supported in this scope
        UsernamePasswordToken token = new UsernamePasswordToken(userLogin.getUsername(), userLogin.getPassword(), false);
        try {
            SecurityUtils.getSubject().login(token);
            // Assuming your json object is **jsonObject**, perform the following, it will return your json object
            out.print("{\"status\":\"OK\"}");
        } catch (AuthenticationException e) {
            //Return generic login error so as not to help hackers.
            out.print("{\"status\":\"ERROR\"}");
        } finally {
            out.flush();

        }


    }


}

