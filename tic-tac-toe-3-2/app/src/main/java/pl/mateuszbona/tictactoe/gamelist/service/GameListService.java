package pl.mateuszbona.tictactoe.gamelist.service;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import pl.mateuszbona.tictactoe.apiclient.ErrorResponse;
import pl.mateuszbona.tictactoe.apiclient.TicTacToeApiClient;
import pl.mateuszbona.tictactoe.apiclient.TttApiClientFactory;
import pl.mateuszbona.tictactoe.gamelist.model.CreateGameRequest;
import pl.mateuszbona.tictactoe.gamelist.model.CreateGameResponse;
import pl.mateuszbona.tictactoe.gamelist.model.CreateGameResult;
import pl.mateuszbona.tictactoe.gamelist.model.Game;
import pl.mateuszbona.tictactoe.gamelist.model.GameResponse;
import pl.mateuszbona.tictactoe.gamelist.model.GameStatus;
import pl.mateuszbona.tictactoe.gamelist.model.GameStatusResponse;
import pl.mateuszbona.tictactoe.gamelist.model.JoinGameRequest;
import pl.mateuszbona.tictactoe.user.service.UserService;
import retrofit2.Response;

public class GameListService {

    private final TicTacToeApiClient ticTacToeApiClient;
    private final UserService userService;

    public GameListService() {
        TttApiClientFactory tttApiClientFactory = new TttApiClientFactory();
        ticTacToeApiClient = tttApiClientFactory.createNewApiClient();
        userService = UserService.getInstance();
    }

    public List<Game> getGames() throws IOException {
        Response<GameResponse> response = ticTacToeApiClient.getGames(userService.getToken()).execute();
        return response.body().getData();

    }

    public CreateGameResult createGame(CreateGameRequest createGameRequest) throws IOException {
        Response<CreateGameResponse> response = ticTacToeApiClient.createGame(userService.getToken(), createGameRequest).execute();

        if(!response.isSuccessful()){
            String responseBodyString = response.errorBody().string();
            ErrorResponse errorResponse = new Gson().fromJson(responseBodyString, ErrorResponse.class);

            return new CreateGameResult(null, errorResponse.getMessage());
        }
        return new CreateGameResult(response.body().getId(), null);

    }

    public void joinGame(JoinGameRequest joinGameRequest) throws IOException {
        Response<ResponseBody> response = ticTacToeApiClient.joinGame(
                userService.getToken(),
                joinGameRequest
        ).execute();

        if(!response.isSuccessful()) {
            throw new RuntimeException();
        }
    }

    public GameStatus getMyGameStatus() throws IOException {
        Response<GameStatusResponse> response = ticTacToeApiClient.getMyGameStatus(userService.getToken()).execute();

        if(!response.isSuccessful()) {
            throw new RuntimeException();
        }

        return response.body().getData();
    }
}
