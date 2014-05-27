package mt.com.casinoeuro.flipeuro.controller;

import mt.com.casinoeuro.flipeuro.model.UserLogin;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * The Deposit Money Controller.
 *
 * @author cscib
 * @version 1.0.0
 * @since 26/05/2014 19:35
 */
@Controller
public class DepositController extends BaseController {


    @RequestMapping(method = RequestMethod.GET, value = "/deposit")
    public String renderDepositScreen() throws IOException {

        return "deposit";
    }

}
