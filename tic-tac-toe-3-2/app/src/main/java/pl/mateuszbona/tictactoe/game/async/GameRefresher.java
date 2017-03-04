package pl.mateuszbona.tictactoe.game.async;

import android.os.Handler;
import android.os.Looper;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import pl.mateuszbona.tictactoe.game.service.GameService;
import pl.mateuszbona.tictactoe.gamelist.model.Game;

public class GameRefresher {

    private final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
    private final GameService gameService;

    public GameRefresher() {
        gameService = new GameService();
    }

    public void startRefreshing(final GameRefreshed gameRefreshed, final String gameId) {
        executorService.scheduleAtFixedRate(
                buildRefreshingRunnable(gameRefreshed, gameId),
                0,
                1,
                TimeUnit.SECONDS
        );
    }

    private Runnable buildRefreshingRunnable(final GameRefreshed gameRefreshed, final String gameId) {
        return new Runnable() {
            @Override
            public void run() {
                refreshGameData(gameRefreshed, gameId);
            }
        };
    }

    private void refreshGameData(GameRefreshed gameRefreshed, String gameId) {
        Game game = null;
        try {
            game = gameService.getGameData(gameId);
        } catch (Exception e){
            e.printStackTrace();
        }
        postGameRefreshed(game, gameRefreshed);
    }

    private void postGameRefreshed(final Game game, final GameRefreshed gameRefreshed) {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                gameRefreshed.gameRefreshed(game);
            }
        });
    }

    public void close() {
        executorService.shutdown();
    }

    public interface GameRefreshed {
        void gameRefreshed(Game game);
    }

}
