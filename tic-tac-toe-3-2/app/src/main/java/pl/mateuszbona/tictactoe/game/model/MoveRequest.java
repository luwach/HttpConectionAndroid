package pl.mateuszbona.tictactoe.game.model;

public class MoveRequest {

    private final int x;
    private final int y;
    private final String gameId;

    public MoveRequest(int x, int y, String gameId) {
        this.x = x;
        this.y = y;
        this.gameId = gameId;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public String getGameId() {
        return gameId;
    }
}
