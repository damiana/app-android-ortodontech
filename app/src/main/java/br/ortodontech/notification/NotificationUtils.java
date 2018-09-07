package br.ortodontech.notification;

import android.app.Notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.text.Html;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import br.ortodontech.MainActivity;
import br.ortodontech.QuizActivity;
import br.ortodontech.R;

public class NotificationUtils {

    private static final int NOTIFICATION_ID = 200;
    private static final String PUSH_NOTIFICATION = "pushNotification";
    private static final String CHANNEL_ID = "myChannel";
    private static final String URL = "url";
    private static final String ACTIVITY = "activity";
    Map<String, Class> activityMap = new HashMap<>();
    private Context mContext;

    public NotificationUtils(Context mContext) {
        this.mContext = mContext;
        //Populate activity map
        activityMap.put("MainActivity", MainActivity.class);
        activityMap.put("QuizActivity", QuizActivity.class);
    }

    public void exibirNotificacao(NotificationVO notificationVO, Intent resultIntent) {

        String mensagem = notificationVO.getMensagem();
        String titulo = notificationVO.getTitulo();
        String iconeUrl = notificationVO.getIconeUrl();
        String acao = notificationVO.getAcao();
        String acaoDestino = notificationVO.getAcaoDestino();
        Bitmap iconeBitMap = null;

        if (iconeUrl != null) {
            iconeBitMap = getBitmapFromURL(iconeUrl);
        }

        final int icone = R.drawable.ic_app_round;

        PendingIntent resultPendingIntent;

        if (URL.equals(acao)) {
            Intent notificationIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(acaoDestino));

            resultPendingIntent = PendingIntent.getActivity(mContext, 0, notificationIntent, 0);
        } else if (ACTIVITY.equals(acao) && activityMap.containsKey(acaoDestino)) {
            resultIntent = new Intent(mContext, activityMap.get(acaoDestino));

            resultPendingIntent =
                    PendingIntent.getActivity(
                            mContext,
                            0,
                            resultIntent,
                            PendingIntent.FLAG_CANCEL_CURRENT
                    );
        } else {
            resultIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
            resultPendingIntent =
                    PendingIntent.getActivity(
                            mContext,
                            0,
                            resultIntent,
                            PendingIntent.FLAG_CANCEL_CURRENT
                    );
        }

        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(
                mContext, CHANNEL_ID);

        Notification notification;

        if (iconeBitMap == null) {
            //When Inbox Style is applied, user can expand the notification
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();

            inboxStyle.addLine(mensagem);
            notification = mBuilder.setSmallIcon(icone).setTicker(titulo).setWhen(0)
                    .setAutoCancel(true)
                    .setContentTitle(titulo)
                    .setContentIntent(resultPendingIntent)
                    .setStyle(inboxStyle)
                    .setSmallIcon(R.drawable.ic_app_round) //R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icone))
                    .setContentText(mensagem)
                    .build();

        } else {
            //If Bitmap is created from URL, show big icon
            NotificationCompat.BigPictureStyle bigPictureStyle = new NotificationCompat.BigPictureStyle();
            bigPictureStyle.setBigContentTitle(titulo);
            bigPictureStyle.setSummaryText(Html.fromHtml(mensagem).toString());
            bigPictureStyle.bigPicture(iconeBitMap);
            notification = mBuilder.setSmallIcon(icone).setTicker(titulo).setWhen(0)
                    .setAutoCancel(true)
                    .setContentTitle(titulo)
                    .setContentIntent(resultPendingIntent)
                    .setStyle(bigPictureStyle)
                    .setSmallIcon(R.drawable.ic_app_round) //R.mipmap.ic_launcher)
                    .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), icone))
                    .setContentText(mensagem)
                    .build();
        }
        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, notification);
    }

    private Bitmap getBitmapFromURL(String strURL) {
        try {
            URL url = new URL(strURL);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void playNotificationSound() {
        try {
            Uri alarmSound = Uri.parse(ContentResolver.SCHEME_ANDROID_RESOURCE
                    + "://" + mContext.getPackageName() + "/raw/notification");
            Ringtone r = RingtoneManager.getRingtone(mContext, alarmSound);
            r.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
