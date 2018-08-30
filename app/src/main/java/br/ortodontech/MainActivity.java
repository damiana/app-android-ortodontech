package br.ortodontech;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button btnConteudo, btnQuiz, btnPerfil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnConteudo = (Button) findViewById(R.id.btnConteudo);
        btnQuiz = (Button) findViewById(R.id.btnQuiz);
        btnPerfil = (Button) findViewById(R.id.btnPerfil);

        ConfiguracaoNotificacao configuracao = new ConfiguracaoNotificacao();
        configuracao.setarContextoParaNotificacao(this);

        btnConteudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("mainDamiana:: ", "chamou metodo onClick Conteudo");
                Intent intent = new Intent(MainActivity.this, WebViewConteudoActivity.class);
                startActivity(intent);
            }
        });

        btnQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("mainDamiana:: ", "chamou metodo onClick Quiz");
                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                startActivity(intent);
            }
        });

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("mainDamiana:: ", "chamou metodo onClick Perfil");
                getPerfil(view);
            }
        });

    }

    public void getPerfil(View view) {
        Intent intent = new Intent(MainActivity.this, PerfilActivity.class);
        startActivity(intent);
    }
}
