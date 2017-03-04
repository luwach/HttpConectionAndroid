package pl.mateuszbona.tictactoe.user.async;

import android.os.AsyncTask;

import pl.mateuszbona.tictactoe.user.model.RegisterRequest;
import pl.mateuszbona.tictactoe.user.service.UserService;

/**
 * Created by RENT on 2016-12-17.
 */

public class RegisterAsyncTask extends AsyncTask<RegisterRequest,Void, Boolean>{

    private final OnRegisterListener onRegisterListener;
    private final UserService userService;

    public RegisterAsyncTask(OnRegisterListener onRegisterListener) {
        this.onRegisterListener = onRegisterListener;

        userService = UserService.getInstance();

    }

    @Override
    protected Boolean doInBackground(RegisterRequest... registerRequests) {
        try{
            userService.register(registerRequests[0]);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    protected void onPostExecute(Boolean reult) {
        if (reult){
            onRegisterListener.completed();
        }else{
            onRegisterListener.failed();
        }
    }

    public interface OnRegisterListener{
        void completed();
        void failed();
    }
}
