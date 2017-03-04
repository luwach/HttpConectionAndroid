package pl.mateuszbona.tictactoe.user.service;

import java.io.IOException;

import pl.mateuszbona.tictactoe.apiclient.TicTacToeApiClient;
import pl.mateuszbona.tictactoe.apiclient.TttApiClientFactory;
import pl.mateuszbona.tictactoe.user.model.LoginRequest;
import pl.mateuszbona.tictactoe.user.model.LoginResponse;
import pl.mateuszbona.tictactoe.user.model.RegisterRequest;
import retrofit2.Response;

/**
 * Created by RENT on 2016-12-17.
 */

public class UserService {

    private static UserService instance;
    private final TicTacToeApiClient ticTacToeApiClient;
    private String token;

    private UserService() {
        TttApiClientFactory tttApiClientFactory = new TttApiClientFactory();
        ticTacToeApiClient = tttApiClientFactory.createNewApiClient();
    }


    public static UserService getInstance() {
        if (instance == null) {
            instance = new UserService();
        }
        return instance;
    }

    public void register(RegisterRequest registerRequest) throws IOException {
        Response<LoginResponse> response = ticTacToeApiClient.register(registerRequest).execute();

        if (!response.isSuccessful()) {
            throw new RuntimeException("failed");
        }
        LoginResponse loginResponse = response.body();
        token = loginResponse.getData().getToken();
    }

    public void logout(){
        try {
            ticTacToeApiClient.logout(token).execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        token = null;

    }

    public void login (LoginRequest loginRequest) throws IOException {
        Response<LoginResponse> response = ticTacToeApiClient.login(loginRequest).execute();

        if (!response.isSuccessful()) {
            throw new RuntimeException("failed");
        }
        LoginResponse loginResponse = response.body();
        token = loginResponse.getData().getToken();
    }

    public String getToken() {
        return token;
    }
}