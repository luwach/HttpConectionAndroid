package pl.mateuszbona.tictactoe.game.async;

import android.os.AsyncTask;

import pl.mateuszbona.tictactoe.game.service.GameService;

/**
 * Created by RENT on 2016-12-19.
 */

public class LeaveGameAsyncTask extends AsyncTask<Void, Void, Boolean> {

    private final LeaveGameListener leaveGameListener;
    private final GameService gameService;

    public LeaveGameAsyncTask(LeaveGameListener leaveGameListener) {
        this.leaveGameListener = leaveGameListener;
        gameService = new GameService();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        try {
            gameService.leaveGame();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if(result){
            leaveGameListener.onGameLeft();
        }else{
            leaveGameListener.Failed();
        }
    }

    public interface LeaveGameListener {
        void onGameLeft();

        void Failed();


    }
}
