package pl.mateuszbona.tictactoe.game.async;

import android.os.AsyncTask;

import java.io.IOException;

import pl.mateuszbona.tictactoe.game.model.MakeMoveResult;
import pl.mateuszbona.tictactoe.game.model.MoveRequest;
import pl.mateuszbona.tictactoe.game.service.GameService;

public class MakeMoveAsyncTask extends AsyncTask<MoveRequest, Void, MakeMoveResult> {

    private final MoveMadeListener moveMadeListener;
    private final GameService gameService;

    public MakeMoveAsyncTask(MoveMadeListener moveMadeListener) {
        this.moveMadeListener = moveMadeListener;
        gameService = new GameService();

        boolean b =   true;
    }

    @Override
    protected MakeMoveResult doInBackground(MoveRequest... moveRequests) {
        try {
             return gameService.makeMove(moveRequests[0]);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new MakeMoveResult("failed");
    }

    @Override
    protected void onPostExecute(MakeMoveResult makeMoveResult) {
        if (makeMoveResult.getErrorMessage() == null) {
            moveMadeListener.moveMade();
        } else {
            moveMadeListener.failed(makeMoveResult.getErrorMessage());
        }
    }

    public interface MoveMadeListener {
        void moveMade();
        void failed(String error);
    }

}