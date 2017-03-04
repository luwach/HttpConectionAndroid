package pl.mateuszbona.tictactoe.user.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by RENT on 2016-12-17.
 */

public class UserToken {
    @SerializedName("X-BB-SESSION")
    private final String token;

    public UserToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }
}
