package poiuyt.alarm.activities;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.content.WakefulBroadcastReceiver;
import android.widget.Toast;

import java.util.Calendar;

import poiuyt.alarm.R;
import poiuyt.alarm.utils.LogUtils;

public class AlarmReceiver extends WakefulBroadcastReceiver {

    Context context;
    private final long[] mVibratePattern = {1000, 200, 300, 500};

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.d("Alarm Received");
        this.context = context;
        LogUtils.d(intent.getBooleanExtra("Alo", false) + "Test EveryWhere");

        Uri alarmUri = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_ALARM);

        if (alarmUri == null) {
            alarmUri = RingtoneManager
                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
//        LogUtils.d("alarmUri: " + alarmUri.toString());
//        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
//        ringtone.play();



        Intent mStartStopAlarmActivity = new Intent(context, StopAlarmActivity.class);

        PendingIntent mPendingIntent = PendingIntent.getActivity(context, 1, mStartStopAlarmActivity, PendingIntent.FLAG_ONE_SHOT);
        Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setAutoCancel(true)
//                .setTicker(context.getResources().getString(R.string.alarm))
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setContentTitle(context.getResources().getString(R.string.alarm))
                .setContentText(context.getResources().getString(R.string.cancel)).setContentIntent(mPendingIntent)
                .setSound(alarmUri).setVibrate(mVibratePattern);

        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(1, notificationBuilder.build());


        setResultCode(Activity.RESULT_OK);
    }

}
