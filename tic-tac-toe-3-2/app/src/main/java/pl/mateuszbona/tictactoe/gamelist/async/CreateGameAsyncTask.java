package pl.mateuszbona.tictactoe.gamelist.async;

import android.os.AsyncTask;

import pl.mateuszbona.tictactoe.gamelist.model.CreateGameRequest;
import pl.mateuszbona.tictactoe.gamelist.model.CreateGameResult;
import pl.mateuszbona.tictactoe.gamelist.service.GameListService;

public class CreateGameAsyncTask extends AsyncTask<CreateGameRequest, Void, CreateGameResult> {

    private final GameListService gameListService;
    private final CreateGameListener createGameListener;

    public CreateGameAsyncTask(CreateGameListener createGameListener) {
        this.createGameListener = createGameListener;
        gameListService = new GameListService();
    }

    @Override
    protected CreateGameResult doInBackground(CreateGameRequest... createGameRequests) {
        try{
            return  gameListService.createGame(createGameRequests[0]);
        }catch (Exception e){
            e.printStackTrace();
        }
        return new CreateGameResult(null, "Failed");
    }

    @Override
    protected void onPostExecute(CreateGameResult createGameResult) {
        createGameListener.onResult(createGameResult);
    }

    public interface CreateGameListener {
        void onResult(CreateGameResult createGameResult);

    }
}
