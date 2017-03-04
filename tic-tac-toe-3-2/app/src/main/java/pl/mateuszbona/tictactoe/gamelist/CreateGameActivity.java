package pl.mateuszbona.tictactoe.gamelist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.mateuszbona.tictactoe.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.mateuszbona.tictactoe.game.activity.GameActivity;
import pl.mateuszbona.tictactoe.gamelist.async.CreateGameAsyncTask;
import pl.mateuszbona.tictactoe.gamelist.model.CreateGameRequest;
import pl.mateuszbona.tictactoe.gamelist.model.CreateGameResult;

public class CreateGameActivity extends AppCompatActivity implements CreateGameAsyncTask.CreateGameListener {

    @BindView(R.id.name)
    EditText gameName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);
        ButterKnife.bind(this);


    }

    @OnClick(R.id.startGameButton)
    public void startGame(){

        CreateGameRequest createGameRequest = new CreateGameRequest(gameName.getText().toString());
        new CreateGameAsyncTask(this).execute(createGameRequest);
    }

    @Override
    public void onResult(CreateGameResult createGameResult) {
        if(createGameResult.getId() != null){
            Intent intent = new Intent(this, GameActivity.class);
            intent.putExtra("isOwner", true);
            intent.putExtra("gameId", createGameResult.getId());
            startActivity(intent);
            finish();
            return;
        }
        Toast.makeText(this, createGameResult.getErrorMessage(), Toast.LENGTH_LONG).show();
    }
}
