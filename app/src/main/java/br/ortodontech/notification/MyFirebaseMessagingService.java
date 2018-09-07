package br.ortodontech.notification;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import br.ortodontech.QuizActivity;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgingService";
    private static final String TITLE = "title";
    private static final String EMPTY = "";
    private static final String MESSAGE = "message";
    private static final String IMAGE = "image";
    private static final String ACTION = "action";
    private static final String DATA = "data";
    private static final String ACTION_DESTINATION = "action_destination";


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        Log.d(TAG, "From: " + remoteMessage.getFrom());

        // Verifica se a mensagem contem dados.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Mensagem payload: " + remoteMessage.getData());
            Map<String, String> dados = remoteMessage.getData();
            getDados(dados);

        } else if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Notificacao corpo da mensagem: " + remoteMessage.getNotification().getBody());
            getNotificacao(remoteMessage.getNotification());
        }// Verifica se a mensagem contem uma notificacao
    }

    private void getNotificacao(RemoteMessage.Notification RemoteMsgNotification) {
        String mensagem = RemoteMsgNotification.getBody();
        String titulo = RemoteMsgNotification.getTitle();
        NotificationVO notificationVO = new NotificationVO();
        notificationVO.setTitulo(titulo);
        notificationVO.setMensagem(mensagem);

        Intent resultIntent = new Intent(getApplicationContext(), QuizActivity.class);
        NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
        notificationUtils.exibirNotificacao(notificationVO, resultIntent);
        notificationUtils.playNotificationSound();
    }

    private void getDados(Map<String, String> dados) {
        String titulo = dados.get(TITLE);
        String mensagem = dados.get(MESSAGE);
        String iconeUrl = dados.get(IMAGE);
        String acao = dados.get(ACTION);
        String acaoDestino = dados.get(ACTION_DESTINATION);
        NotificationVO notificationVO = new NotificationVO();
        notificationVO.setTitulo(titulo);
        notificationVO.setMensagem(mensagem);
        notificationVO.setIconeUrl(iconeUrl);
        notificationVO.setAcao(acao);
        notificationVO.setAcaoDestino(acaoDestino);

        Intent resultIntent = new Intent(getApplicationContext(), QuizActivity.class);

        NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
        notificationUtils.exibirNotificacao(notificationVO, resultIntent);
        notificationUtils.playNotificationSound();
    }
}
