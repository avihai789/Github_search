package main.github.model;

/**
 * object for the user that saves tha password and user name
 */
public class User {
    private String username;
    private String password;

    /**
     * sets the user name and password
     * @param username the user name
     * @param password his password
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * gets the user name
     * @return user name
     */
    public String getUsername() {
        return username;
    }

    /**
     * sets the user name
     * @param username the user name
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * gets the password
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
