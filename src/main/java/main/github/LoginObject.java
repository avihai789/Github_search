package main.github;

/**
 * object that keeps login message if succeed or not
 */
public class LoginObject {
    private String message;

    /**
     * sets the "Invalid username or password" or "success" message.
     * @param message
     */
    public LoginObject(String message){
        this.message = message;
    }

    /**
     * gets the message
     * @return "Invalid username or password" or "success" message
     */
    public String getMessage() {
        return message;
    }

    /**
     * sets the message
     * @param message the message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}
