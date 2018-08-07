package br.ortodontech;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    EditText editLogin, editSenha;
    Button btnLogin;

    static String USUARIO = "";
    static String SENHA = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editLogin = (EditText) findViewById(R.id.edTxtLogin);
        editSenha = (EditText)findViewById(R.id.edTxtSenha);

        btnLogin = (Button) findViewById(R.id.btnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("mainDamiana", "MainActivity:onClick ");
                onLogin(view);
            }
        });
    }

    public void onLogin(View view) {
        String email = editLogin.getText().toString();
        String senha = editSenha.getText().toString();

        LoginActivity.USUARIO = email;
        LoginActivity.SENHA = senha;

        String type = "login";
        LoginServer loginServer = new LoginServer(this);
        loginServer.execute(type, email, senha);
    }

}
