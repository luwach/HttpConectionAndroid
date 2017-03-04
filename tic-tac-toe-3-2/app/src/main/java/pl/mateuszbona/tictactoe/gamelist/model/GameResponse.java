package pl.mateuszbona.tictactoe.gamelist.model;

import java.util.List;

public class GameResponse {

    private final List<Game> data;

    public GameResponse(List<Game> data) {
        this.data = data;
    }

    public List<Game> getData() {
        return data;
    }
}
