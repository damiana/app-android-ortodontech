package br.ortodontech;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

public class MainActivity extends AppCompatActivity {

    Button btnConteudo, btnQuiz, btnPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConteudo = (Button) findViewById(R.id.btnConteudo);
        btnQuiz = (Button) findViewById(R.id.btnQuiz);
        btnPerfil = (Button) findViewById(R.id.btnPerfil);

        btnConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("mainDamiana:: ", "chamou metodo onClick Conteudo");
            }
        });

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("mainDamiana:: ", "chamou metodo onClick Quiz");
            }
        });

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("mainDamiana:: ", "chamou metodo onClick Perfil");
                getPerfil(view);
            }
        });

        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener( this,  new OnSuccessListener<InstanceIdResult>() {
            @Override
            public void onSuccess(InstanceIdResult instanceIdResult) {
                String newToken = instanceIdResult.getToken();
                Log.i("TokenApp",newToken);

            }
        });

    }

    public void getPerfil(View view) {
        Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
        startActivity(intent);
    }
}
