package pl.mateuszbona.tictactoe.gamelist.model;

public class CreateGameResult {

    private final String id;
    private final String errorMessage;

    public CreateGameResult(String id, String errorMessage) {
        this.id = id;
        this.errorMessage = errorMessage;
    }

    public String getId() {
        return id;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

}
