package main.github.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpSession;

/**
 * class that authenticates the login and that the user is still logged in
 */
@Component
public class AuthenticationChecker {

    @Value("${regular.user.name}")
    private String userName;

    @Value("${regular.password}")
    private String password;

    /**
     * checks if the user name and password is correct
     * @param nUsername user name that the user put
     * @param nPassword password that the user put
     * @return true if they match and false if dont
     */
    public Boolean loadUserByUsername(String nUsername, String nPassword) {
        Boolean isAuthenticated = Boolean.FALSE;
        if (userName.equals(nUsername) && password.equals(nPassword)){
            isAuthenticated = Boolean.TRUE;
        }
        return isAuthenticated;
    }

    /**
     * checks the session
     * @param session the session
     * @return if still logged
     */
    public boolean isLoggedIn(HttpSession session) {
        return session.getAttribute("user") != null;
    }
}
