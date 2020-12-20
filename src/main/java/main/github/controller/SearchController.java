package main.github.controller;

import main.github.model.Search;
import main.github.model.table.SearchHistory;
import main.github.service.AuthenticationChecker;
import main.github.service.SearchService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * the search controller is the object that controls all the searches
 */
@Controller
@RequestMapping("/github")
public class SearchController {

    @Autowired
    SearchService searchService;
    @Autowired
    AuthenticationChecker authenticationChecker;

    /**
     * function that checks the session and the search
     *
     * @param session the session
     * @return string that directs to the right place
     */
    @GetMapping("/search")
    public String searchPage(HttpSession session) {
        if (authenticationChecker.isLoggedIn(session)) {
            return "search/search";
        }
        return "redirect:/";
    }

    /**
     * function that checks the session and checks if we found a user by this name.
     *
     * @param userName user name that we searched
     * @param model    the model
     * @param session  the session
     * @return string that directs to the right place
     */
    @PostMapping("/search")
    public String findByUserName(@ModelAttribute("username") String userName, Model model, HttpSession session) {
        if (authenticationChecker.isLoggedIn(session)) {
            Search gitUser = null;
            try {
                gitUser = searchService.findByUserName(userName);
            } catch (IOException e) {
                model.addAttribute("userNotFound", true);
                return "search/search";
            }
            model.addAttribute("gitUser", gitUser);
            if (gitUser.getFollowers().equals(0)) {
                model.addAttribute("noFollowers", "user has got no followers");

            }
            return "search/search";
        } else {
            return "redirect:/";
        }
    }

    /**
     * function that checks the session and add the search
     *
     * @param model   the model
     * @param session the session
     * @return string that directs to the right place
     */
    @GetMapping("/history")
    public String searchHistory(Model model, HttpSession session) {
        if (authenticationChecker.isLoggedIn(session)) {
            List<SearchHistory> searchHistories = searchService.findPopularSearchHistory();
            model.addAttribute("searchHistories", searchHistories);
            return "search/history";
        } else {
            return "redirect:/";
        }
    }

    /**
     * function that checks the session and then deletes a user from the list
     *
     * @param session the session
     * @return string that directs to the right place
     */
    @GetMapping("/delete")
    public String deleteAll(HttpSession session) {
        if (authenticationChecker.isLoggedIn(session)) {
            searchService.deleteAll();
            return "search/history";
        } else {
            return "redirect:/";
        }
    }

}
