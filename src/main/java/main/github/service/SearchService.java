package main.github.service;

import main.github.model.Search;
import main.github.model.table.SearchHistory;
import main.github.repository.SearchHistoryRepo;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This class is responsible for all the searches that happens in this program.
 */
@Component
public class SearchService {

    @Value("${github.base.api.url}")
    private String url;

    @Autowired
    private SearchHistoryRepo historyRepo;

    JsonObject result = null;


    /**
     * function that gets the user's url and gets the data as a string and converts to json
     *
     * @param userName the users name
     * @return the search object with the data in it
     */
    public Search findByUserName(String userName) throws IOException {
        String url = this.url + "users/" + userName.trim();


        URL githubURL = null;
        BufferedReader reader = null;
        githubURL = new URL(url);
        reader = new BufferedReader(new InputStreamReader(githubURL.openStream()));
        String githubResponse = null;

        githubResponse = reader.readLine();
        result = JsonParser.parseString(githubResponse).getAsJsonObject();
        reader.close();

        return putData(userName);
    }

    /**
     * gets all the data from the json and puts it in "Search" object
     *
     * @param userName the user name
     * @return Search object
     */
    public Search putData(String userName) {
        Search results = new Search();
        String str_html = result.get("html_url").toString();
        String str_login = result.get("login").toString();
        results.setHtml_url(str_html.substring(1, str_html.length() - 1));
        results.setFollowers(Integer.parseInt(result.get("followers").toString()));
        results.setLogin(str_login.substring(1, str_login.length() - 1));
        SearchHistory searchHistory = historyRepo.findByUserName(userName);
        SearchHistory history = new SearchHistory();
        if (searchHistory == null) {
            history.setUserName(userName);
            history.setGitUrl(results.getHtml_url());
            history.setSearchCount(1);
        } else {
            history.setId(searchHistory.getId());
            history.setUserName(searchHistory.getUserName());
            history.setGitUrl(searchHistory.getGitUrl());
            history.setSearchCount(searchHistory.getSearchCount() + 1);
        }
        historyRepo.save(history);
        return results;
    }


    /**
     * orgenize the history top 10 searches
     *
     * @return list of the 10 most searches
     */
    public List<SearchHistory> findPopularSearchHistory() {
        List<SearchHistory> histories = new ArrayList<>();
        AtomicLong count = new AtomicLong();
        historyRepo.findByOrderBySearchCountDesc().stream().forEach(searchHistory -> {
            if (count.getAndIncrement() < 10) {
                histories.add(searchHistory);
            }
        });
        return histories;
    }

    /**
     * deletes all searches
     */
    public void deleteAll() {
        historyRepo.deleteAll();
    }
}
