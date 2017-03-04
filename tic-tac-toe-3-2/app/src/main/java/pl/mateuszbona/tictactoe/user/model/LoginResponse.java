package pl.mateuszbona.tictactoe.user.model;

/**
 * Created by RENT on 2016-12-17.
 */

public class LoginResponse {

    private final String result;
    private final UserToken data;

    public LoginResponse(String result, UserToken data) {

        this.result = result;
        this.data = data;
    }

    public String getResult() {
        return result;
    }

    public UserToken getData() {
        return data;
    }
}
