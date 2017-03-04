package pl.mateuszbona.tictactoe.gamelist.model;

public class CreateGameRequest {

    private final String name;

    public CreateGameRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
