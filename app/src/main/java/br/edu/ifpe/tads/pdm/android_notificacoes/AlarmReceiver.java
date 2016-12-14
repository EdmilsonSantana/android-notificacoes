package br.edu.ifpe.tads.pdm.android_notificacoes;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;

/**
 * Created by Aluno on 14/12/2016.
 */
public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        Intent newIntent = new Intent(context, MainActivity.class);
        newIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP );

        PendingIntent pendingNotificationIntent = PendingIntent
                .getActivity(context, 0, newIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentText("Alarme disparou! Toque para reagendar.");
        builder.setContentIntent(pendingNotificationIntent);
        builder.setAutoCancel(Boolean.TRUE);
        builder.setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_SOUND | Notification.FLAG_SHOW_LIGHTS);
        builder.setLights(0xff00ff00, 300, 100);
        builder.setSound(soundUri);

        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN) {
            notification = builder.build();
        }

        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        if (notification != null) {
            notificationManager.notify(0, notification);
        }

    }
}
