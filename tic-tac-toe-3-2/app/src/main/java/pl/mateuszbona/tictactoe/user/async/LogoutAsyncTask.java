package pl.mateuszbona.tictactoe.user.async;

import android.os.AsyncTask;

import pl.mateuszbona.tictactoe.user.service.UserService;

/**
 * Created by RENT on 2016-12-17.
 */

public class LogoutAsyncTask extends AsyncTask<Void, Void, Void> {

    private final LogoutListener onLogoutListener;
    private final UserService userService;

    public LogoutAsyncTask(LogoutListener onLogoutListener) {
        this.onLogoutListener = onLogoutListener;
        userService = UserService.getInstance();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        userService.logout();
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        onLogoutListener.onLogoutCompleted();
    }

    public interface LogoutListener {
        void onLogoutCompleted();
    }
}
