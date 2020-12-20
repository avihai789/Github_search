package main.github.model;

/**
 * class for each search that keeps the login, url and number of followers
 */
public class Search {
    private String login;
    private String html_url;
    private Integer followers;

    /**
     * getter for the login string
     * @return login string
     */
    public String getLogin() {
        return login;
    }

    /**
     * setter for the login string
     * @param login login string
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * getter for the url string
     * @return
     */
    public String getHtml_url() {
        return html_url;
    }

    /**
     * setter for the url string
     * @param html_url url string
     */
    public void setHtml_url(String html_url) {
        this.html_url = html_url;
    }

    /**
     * getter for the number of followers
     * @return number of followers
     */
    public Integer getFollowers() {
        return followers;
    }

    /**
     * setter for the number of followers
     * @param followers number of followers
     */
    public void setFollowers(Integer followers) {
        this.followers = followers;
    }
}
