package pl.mateuszbona.tictactoe.gamelist.model;

import java.util.List;

public class Game {

    private final String id;
    private final String name;
    private final String creator;
    private final String player;
    private final List<Move> movesOwner;
    private final List<Move> movesPlayer;

    public Game(String id, String name, String creator, String player, List<Move> movesOwner, List<Move> movesPlayer) {
        this.id = id;
        this.name = name;
        this.creator = creator;
        this.player = player;
        this.movesOwner = movesOwner;
        this.movesPlayer = movesPlayer;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCreator() {
        return creator;
    }

    public String getPlayer() {
        return player;
    }

    public List<Move> getMovesOwner() {
        return movesOwner;
    }

    public List<Move> getMovesPlayer() {
        return movesPlayer;
    }
}
