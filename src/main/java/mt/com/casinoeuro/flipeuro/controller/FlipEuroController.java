package mt.com.casinoeuro.flipeuro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Flip Euro Main Controller
 *
 * @author cscib
 * @version 1.0.0
 * @since 24/05/2014 18:31
 */
@Controller
public class FlipEuroController {


    @Autowired
    private org.apache.shiro.web.mgt.DefaultWebSecurityManager securityManager;

    public FlipEuroController() {
        SecurityUtils.setSecurityManager(securityManager);
    }

    @RequestMapping("/view")
    public String defaultRenderer(ModelMap modelMap, HttpServletRequest servletRequest) {

        Subject subject = SecurityUtils.getSubject();
        return "flipeuro";
    }

}
