package pl.mateuszbona.tictactoe.game.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mateuszbona.tictactoe.R;

import java.util.Arrays;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.mateuszbona.tictactoe.game.async.GameRefresher;
import pl.mateuszbona.tictactoe.game.async.LeaveGameAsyncTask;
import pl.mateuszbona.tictactoe.game.async.MakeMoveAsyncTask;
import pl.mateuszbona.tictactoe.game.model.MoveRequest;
import pl.mateuszbona.tictactoe.gamelist.GameListActivity;
import pl.mateuszbona.tictactoe.gamelist.model.Game;
import pl.mateuszbona.tictactoe.gamelist.model.Move;

/**
 * Created by RENT on 2016-12-19.
 */

public class GameActivity extends AppCompatActivity implements LeaveGameAsyncTask.LeaveGameListener, GameRefresher.GameRefreshed, MakeMoveAsyncTask.MoveMadeListener {

    private boolean isOwner = false;
    private boolean isPlayer = false;
    private boolean isSpectator = false;
    private String gameId;

    private TextView field1;
    private TextView field2;
    private TextView field3;

    private TextView field4;
    private TextView field5;
    private TextView field6;

    private TextView field7;
    private TextView field8;
    private TextView field9;

    private GameRefresher gameRefresher;
    private List<TextView> fields;
    private Game gameState;
    private Move moveToMake;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        ButterKnife.bind(this);

        isOwner = getIntent().getBooleanExtra("isOwner", false);
        isPlayer = getIntent().getBooleanExtra("isPlayer", false);
        isSpectator = getIntent().getBooleanExtra("isSpectator", false);
        gameId = getIntent().getStringExtra("gameId");

        if (gameId == null || gameId.isEmpty()) {
            finish();
            return;
        }

        field1 = (TextView) findViewById(R.id.field1);
        field2 = (TextView) findViewById(R.id.field2);
        field3 = (TextView) findViewById(R.id.field3);

        field4 = (TextView) findViewById(R.id.field4);
        field5 = (TextView) findViewById(R.id.field5);
        field6 = (TextView) findViewById(R.id.field6);

        field7 = (TextView) findViewById(R.id.field7);
        field8 = (TextView) findViewById(R.id.field8);
        field9 = (TextView) findViewById(R.id.field9);

        fields = Arrays.asList(field1, field2, field3, field4, field5, field6, field7, field8, field9);

        for (int i = 0; i < fields.size(); i++) {
            TextView field = fields.get(i);
            setMoveClickListener(field, i);
        }
    }

    private void setMoveClickListener(TextView field, final int i) {
        field.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makeMove(i);
            }
        });
    }

    @OnClick(R.id.exit)
    public void leaveGame() {
        new LeaveGameAsyncTask(this).execute();
    }

    @Override
    public void onGameLeft() {
        startActivity(new Intent(this, GameListActivity.class));
    }

    @Override
    public void Failed() {
        Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameRefresher = new GameRefresher();
        gameRefresher.startRefreshing(this, gameId);
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameRefresher.close();
    }

    @Override
    public void gameRefreshed(Game game) {
        if (game == null) {
            Toast.makeText(this, "other player left game!", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        gameState = game;
        printGameData(game);
    }

    private void printGameData(Game game) {
        for (Move move : game.getMovesOwner()) {
            printSingleMove(move, "x");
        }

        for (Move move : game.getMovesPlayer()) {
            printSingleMove(move, "o");
        }
    }

    private void printSingleMove(Move move, String sign) {
        int index = move.toBoardFieldNumber() - 1;
        fields.get(index).setText(sign);
    }

    private void makeMove(int i) {
        if (isSpectator) {
            return;
        }

        int x = i % 3;
        int y = (i - x) / 3;
        moveToMake = new Move(x, y);

        MoveRequest moveRequest = new MoveRequest(x, y, gameId);

        new MakeMoveAsyncTask(this).execute(moveRequest);
    }

    @Override
    public void moveMade() {
        printSingleMove(moveToMake, isPlayer ? "o" : "x");
    }

    @Override
    public void failed(String error) {
        Toast.makeText(this, "failed: "+error, Toast.LENGTH_LONG).show();;
    }

}
