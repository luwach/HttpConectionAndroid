package pl.mateuszbona.tictactoe.gamelist.model;

public class GameStatusResponse {

    private final GameStatus data;

    public GameStatusResponse(GameStatus data) {
        this.data = data;
    }

    public GameStatus getData() {
        return data;
    }

}
