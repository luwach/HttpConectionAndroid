package pl.mateuszbona.tictactoe.gamelist.async;

import android.os.AsyncTask;

import pl.mateuszbona.tictactoe.gamelist.model.GameStatus;
import pl.mateuszbona.tictactoe.gamelist.service.GameListService;

public class GetMyGameStatusAsyncTask extends AsyncTask<Void, Void, GameStatus> {

    private final GameStatusDownloaded listener;
    private final GameListService gameListService;

    public GetMyGameStatusAsyncTask(GameStatusDownloaded listener) {
        this.listener = listener;
        this.gameListService = new GameListService();
    }

    @Override
    protected GameStatus doInBackground(Void... voids) {
        try {
            return gameListService.getMyGameStatus();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(GameStatus gameStatus) {
        listener.gameStatusDownloaded(gameStatus);
    }

    public interface GameStatusDownloaded {
        void gameStatusDownloaded(GameStatus gameStatus);
    }
}
