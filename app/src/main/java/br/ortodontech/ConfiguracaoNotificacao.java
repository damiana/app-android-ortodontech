package br.ortodontech;


import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import java.util.Calendar;

public class ConfiguracaoNotificacao {

    public void setarContextoParaNotificacao(Context contexto) {
        setarHorarioParaNotificacao(contexto);
    }

    protected void setarHorarioParaNotificacao(Context contexto) {

        /* Set alarm para start as 09:00 AM */
        Calendar calendario = Calendar.getInstance();
        calendario.setTimeInMillis(System.currentTimeMillis());
        calendario.set(Calendar.HOUR_OF_DAY, 9);
        calendario.set(Calendar.MINUTE, 0);
        calendario.set(Calendar.SECOND, 0);

        Intent MyIntent = new Intent(contexto, NotificacaoReceiver.class);
        PendingIntent MyPendIntent = PendingIntent.getBroadcast(
                contexto, 0, MyIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmeManager = (AlarmManager) contexto.getSystemService(contexto.ALARM_SERVICE);

        /* Repeating on every 120 minutes interval or 2h*/
        alarmeManager.setRepeating(AlarmManager.RTC_WAKEUP, calendario.getTimeInMillis(),
                1000 * 60 * 120, MyPendIntent);
    }
}
