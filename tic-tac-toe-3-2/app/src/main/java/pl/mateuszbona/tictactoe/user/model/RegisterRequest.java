package pl.mateuszbona.tictactoe.user.model;

/**
 * Created by RENT on 2016-12-17.
 */

public class RegisterRequest {

    private final String username;
    private final String password;

    public RegisterRequest(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
