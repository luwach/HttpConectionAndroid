package pl.mateuszbona.tictactoe.game.model;

public class MakeMoveResult {

    private final String errorMessage;

    public MakeMoveResult(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public MakeMoveResult() {
        this.errorMessage = null;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
