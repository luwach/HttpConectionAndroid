package pl.mateuszbona.tictactoe.gamelist.async;

import android.os.AsyncTask;

import java.util.List;

import pl.mateuszbona.tictactoe.gamelist.model.Game;
import pl.mateuszbona.tictactoe.gamelist.service.GameListService;

public class DownloadGameTask extends AsyncTask<Void, Void, List<Game>> {
    private final GameListService gameListService;
    private final GamesDownloadedListener gamesDownloadedListener;

    public DownloadGameTask(GamesDownloadedListener gamesDownloadedListener) {
        this.gamesDownloadedListener = gamesDownloadedListener;
        gameListService = new GameListService();
    }

    @Override
    protected List<Game> doInBackground(Void... voids) {
        try {
            return gameListService.getGames();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<Game> games) {
        gamesDownloadedListener.downloadedGame(games);
    }

    public interface GamesDownloadedListener {
        void downloadedGame(List<Game> games);
    }
}
