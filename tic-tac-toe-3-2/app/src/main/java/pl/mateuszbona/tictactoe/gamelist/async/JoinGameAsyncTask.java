package pl.mateuszbona.tictactoe.gamelist.async;

import android.os.AsyncTask;

import pl.mateuszbona.tictactoe.gamelist.model.JoinGameRequest;
import pl.mateuszbona.tictactoe.gamelist.service.GameListService;

public class JoinGameAsyncTask extends AsyncTask<JoinGameRequest, Void, Boolean> {

    private final JoinGameListener joinGameListener;
    private final GameListService gameListService;

    public JoinGameAsyncTask(JoinGameListener joinGameListener) {
        this.joinGameListener = joinGameListener;
        this.gameListService = new GameListService();
    }

    @Override
    protected Boolean doInBackground(JoinGameRequest... joinGameRequests) {
        try {
            gameListService.joinGame(joinGameRequests[0]);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result) {
            joinGameListener.joinedGame();
        } else {
            joinGameListener.failed();
        }
    }

    public interface JoinGameListener {
        void joinedGame();
        void failed();
    }

}
