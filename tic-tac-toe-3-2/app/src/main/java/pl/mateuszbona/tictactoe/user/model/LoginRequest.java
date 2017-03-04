package pl.mateuszbona.tictactoe.user.model;

/**
 * Created by RENT on 2016-12-17.
 */

public class LoginRequest {

    private final String username;
    private final String password;
    private final String appcode = "1234567890";


    public LoginRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getAppcode() {
        return appcode;
    }
}