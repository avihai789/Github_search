package main.github.model.table;


import javax.persistence.*;

/**
 * this class saves all the data about a specific search with search count
 */
@Entity
public class SearchHistory {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    private String gitUrl;

    private Integer searchCount;

    /**
     * gets the generated id
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     *sets the generated id
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * gets the user name
     * @return the user name
     */
    public String getUserName() {
        return userName;
    }

    /**
     * sets the user name
     * @param userName user name
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * gets the url of the user
     * @return users url
     */
    public String getGitUrl() {
        return gitUrl;
    }

    /**
     * gets the url of the user
     * @param gitUrl url of the user
     */
    public void setGitUrl(String gitUrl) {
        this.gitUrl = gitUrl;
    }

    /**
     * gets how many searches was for the user
     * @return number of searches
     */
    public Integer getSearchCount() {
        return searchCount;
    }

    /**
     * sets number of searches
     * @param searchCount number of searches
     */
    public void setSearchCount(Integer searchCount) {
        this.searchCount = searchCount;
    }
}
