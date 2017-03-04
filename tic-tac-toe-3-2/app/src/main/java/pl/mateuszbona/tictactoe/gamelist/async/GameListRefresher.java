package pl.mateuszbona.tictactoe.gamelist.async;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import pl.mateuszbona.tictactoe.gamelist.model.Game;
import pl.mateuszbona.tictactoe.gamelist.service.GameListService;

/**
 * Created by RENT on 2016-12-19.
 */

public class GameListRefresher {


    private final ScheduledExecutorService executorService;
 private final GameListService gameListService;


    public GameListRefresher() {
        executorService = Executors.newSingleThreadScheduledExecutor();
        gameListService = new GameListService();

    }

    public void startRefreshing(final DownloadGameTask.GamesDownloadedListener listener) {
        executorService.scheduleAtFixedRate(getRunnable(listener), 0, 5, TimeUnit.SECONDS);

    }

    @NonNull
    private Runnable getRunnable(final DownloadGameTask.GamesDownloadedListener listener) {
        return new Runnable() {
            @Override
            public void run() {
                downloadGame(listener);
            }
        };
    }

    private void downloadGame(DownloadGameTask.GamesDownloadedListener listener) {
        try {
            List<Game> games= gameListService.getGames();
            postGamesDownloaded(games,listener);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void postGamesDownloaded(final List<Game> games, final DownloadGameTask.GamesDownloadedListener listener){
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                listener.downloadedGame(games);
            }
        });
    }
    public void close(){
        executorService.shutdown();
    }
}
