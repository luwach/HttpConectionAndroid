package pl.mateuszbona.tictactoe.game.service;

import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;
import pl.mateuszbona.tictactoe.apiclient.ErrorResponse;
import pl.mateuszbona.tictactoe.apiclient.TicTacToeApiClient;
import pl.mateuszbona.tictactoe.apiclient.TttApiClientFactory;
import pl.mateuszbona.tictactoe.game.model.MakeMoveResult;
import pl.mateuszbona.tictactoe.game.model.MoveRequest;
import pl.mateuszbona.tictactoe.gamelist.model.Game;
import pl.mateuszbona.tictactoe.gamelist.model.GameResponse;
import pl.mateuszbona.tictactoe.user.service.UserService;
import retrofit2.Response;

public class GameService {
    private final TicTacToeApiClient ticTacToeApiClient;
    private final UserService userService;

    public GameService() {
        TttApiClientFactory tttApiClientFactory = new TttApiClientFactory();
        ticTacToeApiClient = tttApiClientFactory.createNewApiClient();
        userService = UserService.getInstance();
    }

    public void leaveGame() throws IOException {

        Response<ResponseBody> response = ticTacToeApiClient.leaveGame(userService.getToken()).execute();
        if(!response.isSuccessful()){
            throw new RuntimeException();
        }
    }

    public Game getGameData(String gameId) throws IOException {
        Response<GameResponse> response = ticTacToeApiClient.getGameData(
                userService.getToken(),
                gameId
        ).execute();

        if (!response.isSuccessful()) {
            throw new RuntimeException();
        }

        return response.body().getData().get(0);
    }

    public MakeMoveResult makeMove(MoveRequest moveRequest) throws IOException {
        Response<ResponseBody> response = ticTacToeApiClient.makeMove(moveRequest, userService.getToken()).execute();

        if(!response.isSuccessful()) {
            String errorResponseBody = response.errorBody().string();
            ErrorResponse errorResponse = new Gson().fromJson(errorResponseBody, ErrorResponse.class);
            return new MakeMoveResult(errorResponse.getMessage());
        }

        return new MakeMoveResult();
    }

}