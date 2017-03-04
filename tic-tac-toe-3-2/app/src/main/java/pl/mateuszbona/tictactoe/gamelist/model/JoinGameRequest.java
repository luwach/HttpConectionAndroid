package pl.mateuszbona.tictactoe.gamelist.model;

public class JoinGameRequest {

    private final String game;

    public JoinGameRequest(String game) {
        this.game = game;
    }

    public String getGame() {
        return game;
    }

}
