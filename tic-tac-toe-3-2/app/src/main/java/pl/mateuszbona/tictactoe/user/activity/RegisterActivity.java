package pl.mateuszbona.tictactoe.user.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.mateuszbona.tictactoe.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pl.mateuszbona.tictactoe.gamelist.GameListActivity;
import pl.mateuszbona.tictactoe.user.model.RegisterRequest;
import pl.mateuszbona.tictactoe.user.async.RegisterAsyncTask;

/**
 * Created by RENT on 2016-12-17.
 */

public class RegisterActivity extends AppCompatActivity implements RegisterAsyncTask.OnRegisterListener {

    @BindView(R.id.login)
EditText login;

    @BindView(R.id.password)
    EditText password;

    @BindView(R.id.password_repeat)
    EditText passwordRepeat;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.register)
    public void register() {
        String login = this.login.getText().toString();
        String pass = password.getText().toString();
        String passRepeat = passwordRepeat.getText().toString();

        if(!pass.equals(passRepeat)) {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }

        RegisterRequest registerRequest = new RegisterRequest(login, pass);

        new RegisterAsyncTask(this).execute(registerRequest);
    }

    @Override
    public void completed() {
        Toast.makeText(this, "OK!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, GameListActivity.class));
    }

    @Override
    public void failed() {
        Toast.makeText(this, "Failed!", Toast.LENGTH_SHORT).show();
    }
}
