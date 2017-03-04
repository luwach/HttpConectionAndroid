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
import pl.mateuszbona.tictactoe.user.async.LoginAsyncTask;
import pl.mateuszbona.tictactoe.user.model.LoginRequest;

public class LoginActivity extends AppCompatActivity implements LoginAsyncTask.OnLoginListener {

    @BindView(R.id.login)
    EditText login;

    @BindView(R.id.password)
    EditText password;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(this);
    }

    @OnClick(R.id.register)
    public void openRegisterActivity() {
        startActivity(new Intent(this, RegisterActivity.class));
    }
    @OnClick(R.id.login_button)
    public void login (){
        String login = this.login.getText().toString();
        String password = this.password.getText().toString();
        LoginRequest loginRequest = new LoginRequest(login, password);
        new LoginAsyncTask(this).execute(loginRequest);
    }

    @Override
    public void onLoginCompleted() {
        startActivity(new Intent(this, GameListActivity.class));
    }

    @Override
    public void failed() {

        Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show();
    }
}
