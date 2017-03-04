package pl.mateuszbona.tictactoe.gamelist.model;

import com.google.gson.annotations.SerializedName;

public class CreateGameResponse {
    @SerializedName("data")
    private String id;

    public CreateGameResponse(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
