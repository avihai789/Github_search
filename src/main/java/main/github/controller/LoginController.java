package main.github.controller;

import main.github.LoginObject;
import main.github.model.User;
import main.github.service.AuthenticationChecker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 *the login controller is the object that controls all the logins
 */
@Controller
public class LoginController {

    @Autowired
    AuthenticationChecker authenticationChecker;

    /**
     * function that checks the login session
     * @param session session
     * @return string that directs to the right place
     */
    @GetMapping("/")
    public String loginStr(HttpSession session) {
        if(!authenticationChecker.isLoggedIn(session)){
            return "login/login";
        }
        return "search/search";
    }

    /**
     * function that creates a login object and verifies the user name and password
     * @param request the request
     * @param session the session
     * @return a login object
     */
    @PostMapping("/login")
    public @ResponseBody LoginObject postLogin(@RequestBody Map<String, String> request, HttpSession session) {
        User user = new User(request.get("uname"), request.get("pwd"));
        LoginObject loginObject;
        if (authenticationChecker.loadUserByUsername(user.getUsername(), user.getPassword())) {
            session.setAttribute("user", user);
            loginObject = new LoginObject("success");
        } else {
            loginObject = new LoginObject("Invalid username or password.");
        }
        return loginObject;
    }

    /**
     * logout function
     * @param session the session
     * @return string that directs to the right place
     */
    @GetMapping("/logout")
    public String postLogout(HttpSession session) {
        session.setAttribute("user", null);
        return "redirect:/";
    }

    /**
     * error
     * @return string that directs to the right place
     */
    @GetMapping("/404")
    public String error() {
        return "error/404";
    }
}
