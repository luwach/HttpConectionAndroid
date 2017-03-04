package pl.mateuszbona.tictactoe.user.async;

import android.os.AsyncTask;

import pl.mateuszbona.tictactoe.user.model.LoginRequest;
import pl.mateuszbona.tictactoe.user.service.UserService;

public class LoginAsyncTask extends AsyncTask<LoginRequest, Void, Boolean> {

    private final OnLoginListener onLoginListener;
    private final UserService userService;

    public LoginAsyncTask(OnLoginListener onLoginListener) {
        this.onLoginListener = onLoginListener;
        userService = UserService.getInstance();
    }

    @Override
    protected Boolean doInBackground(LoginRequest... loginRequests) {
        try {
            LoginRequest loginRequest = loginRequests[0];
            userService.login(loginRequest);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean result) {
        if (result) {
            onLoginListener.onLoginCompleted();
        } else
            onLoginListener.failed();
    }

    public interface OnLoginListener {
        void onLoginCompleted();

        void failed();

    }
}
