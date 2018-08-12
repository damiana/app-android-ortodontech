package br.ortodontech;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import java.util.Random;

public class NotificacaoReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Log.d("mainDamiana", "onReceive() called");
        enviarNotificacao("Escovacao :)",
                "Você já escovou os dentes hoje?", context);

        //Toast.makeText(context, "Don't panik but your time is up!!!!.",
          //      Toast.LENGTH_LONG).show();
    }

    private void enviarNotificacao(String title, String message, Context contexto) {

        Intent intent = new Intent(contexto, PerfilActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(contexto, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri padraoSomUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(contexto)
                .setSmallIcon(R.drawable.ic_quiz) //TODO incluir um novo icone
                .setLargeIcon(BitmapFactory.decodeResource(contexto.getResources(),
                        R.mipmap.ic_launcher))
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(padraoSomUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) contexto.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(getCodigoRandomico(), notificationBuilder.build());
    }

    private static int getCodigoRandomico() {
        Random random = new Random();
        return 100 + random.nextInt(900000);
    }
}
