package pl.mateuszbona.tictactoe.gamelist;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mateuszbona.tictactoe.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.mateuszbona.tictactoe.game.activity.GameActivity;
import pl.mateuszbona.tictactoe.gamelist.adapter.GameListAdapter;
import pl.mateuszbona.tictactoe.gamelist.async.DownloadGameTask;
import pl.mateuszbona.tictactoe.gamelist.async.GameListRefresher;
import pl.mateuszbona.tictactoe.gamelist.async.GetMyGameStatusAsyncTask;
import pl.mateuszbona.tictactoe.gamelist.async.JoinGameAsyncTask;
import pl.mateuszbona.tictactoe.gamelist.model.Game;
import pl.mateuszbona.tictactoe.gamelist.model.GameStatus;
import pl.mateuszbona.tictactoe.gamelist.model.JoinGameRequest;
import pl.mateuszbona.tictactoe.user.activity.LoginActivity;
import pl.mateuszbona.tictactoe.user.async.LogoutAsyncTask;


public class GameListActivity extends AppCompatActivity
        implements LogoutAsyncTask.LogoutListener,
        DownloadGameTask.GamesDownloadedListener,
        GameListAdapter.ItemClickListener,
        JoinGameAsyncTask.JoinGameListener,
        GetMyGameStatusAsyncTask.GameStatusDownloaded {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private GameListRefresher gameListRefresher;
    private Game gameToJoin;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gamelist);

        ButterKnife.bind(this);

        checkBeingInsideGameAlready();
    }

    private void checkBeingInsideGameAlready() {
        new GetMyGameStatusAsyncTask(this).execute();
    }

    @Override
    public void gameStatusDownloaded(GameStatus gameStatus) {
        if(gameStatus == null || gameStatus.getGameId() == null || gameStatus.getGameId().isEmpty()) {
            return;
        }

        //we are inside game, start game activity!
        Intent intent = new Intent(this,  GameActivity.class);

        if("OWNER".equals(gameStatus.getType())) {
            intent.putExtra("isOwner", true);
        } else {
            intent.putExtra("isPlayer", true);
        }
        intent.putExtra("gameId", gameStatus.getGameId());

        startActivity(intent);

        Toast.makeText(this, "Already in game! game: "+gameStatus.getName()+ ", as: "+gameStatus.getType(), Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.logout)
    public void logout() {
        new LogoutAsyncTask(this).execute();
    }

    @Override
    public void onLogoutCompleted() {
        startActivity(new Intent(this, LoginActivity.class));
    }

    @Override
    public void downloadedGame(List<Game> games) {
        GameListAdapter adapter = new GameListAdapter(this, games, this);
        recyclerView.setAdapter(adapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

    @Override
    public void itemClicked(final Game game) {
        new AlertDialog.Builder(this)
                .setMessage("Join game as player or spectator?")
                .setPositiveButton("Player", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        joinGame(game);
                    }
                })
                .setNegativeButton("Spectator", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = new Intent(GameListActivity.this, GameActivity.class);
                        intent.putExtra("isSpectator", true);
                        intent.putExtra("gameId", game.getId());
                        startActivity(intent);
                    }
                })
                .show();
    }

    private void joinGame(Game game) {
        gameToJoin = game;
        JoinGameRequest joinGameRequest = new JoinGameRequest(game.getName());

        new JoinGameAsyncTask(this).execute(joinGameRequest);
    }

    @Override
    public void joinedGame() {
        Intent intent = new Intent(GameListActivity.this, GameActivity.class);
        intent.putExtra("isPlayer", true);
        intent.putExtra("gameId", gameToJoin.getId());
        startActivity(intent);

        gameToJoin = null;
    }

    @Override
    public void failed() {
        Toast.makeText(this, "failed to join game!", Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.newGame)
    public void startNewGame() {
        startActivity(new Intent(this, CreateGameActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameListRefresher = new GameListRefresher();
        gameListRefresher.startRefreshing(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameListRefresher.close();
    }
}
