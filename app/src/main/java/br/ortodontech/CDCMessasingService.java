package br.ortodontech;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

public class CDCMessasingService extends FirebaseMessagingService {

    @Override
    public void onNewToken(String s) {
        super.onNewToken(s);
        Log.d("TokenApp", s);
    }

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //RemoteMessage.Notification notification = remoteMessage.getNotification();
        //mostrarNotificacao(notification);

        super.onMessageReceived(remoteMessage);
        Log.d("TokenApp", "onMessageReceived");
        String title = "";
        if (remoteMessage.getNotification().getTitle() != null){
            title = remoteMessage.getNotification().getTitle();
        }
//
        String message = "";
        if (remoteMessage.getNotification().getBody() != null){
            message = remoteMessage.getNotification().getBody();
        }
//
        Log.e("notification","recieved");
//
//
        sendNotification(title, message);
    }
//
    private void sendNotification(String title, String message) {

        Intent intent = new Intent(this, MainActivity.class);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_quiz)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(),
                        R.mipmap.ic_launcher))
                .setContentTitle(title)
                .setContentText(message)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(getRequestCode(), notificationBuilder.build());
    }

    private static int getRequestCode() {
        Random rnd = new Random();
        return 100 + rnd.nextInt(900000);
    }

//    public void mostrarNotificacao(RemoteMessage.Notification notification) {
//        String titulo = notification.getTitle();
//        String mensagem = notification.getBody();
//
//        NotificationCompat.Builder builder =
//                new NotificationCompat.Builder(getApplicationContext());
//        Notification notificacao = builder.setContentTitle(titulo)
//                .setContentText(mensagem)
//                .setSmallIcon(R.drawable.ic_quiz)
//                .build();
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        notificationManager.notify(0, notificacao);
//    }
}
