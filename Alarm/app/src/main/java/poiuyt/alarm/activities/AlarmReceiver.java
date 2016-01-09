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
import android.support.v4.content.WakefulBroadcastReceiver;

import poiuyt.alarm.R;
import poiuyt.alarm.utils.LogUtils;

public class AlarmReceiver extends WakefulBroadcastReceiver {

    Context context;
    private Intent intentOfAlarm;
    private PendingIntent mPendingIntent;
    private Intent mNotificationIntent;
    private static final int MY_NOTIFICATION_ID = 1;
    private final long[] mVibratePattern = {0, 100, 200, 300, 400, 500};

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.d("Alarm Received");
        this.context = context;
        LogUtils.d(intent.getBooleanExtra("Alo", false) + "Test EveryWhere");

        Uri alarmUri = RingtoneManager
                .getDefaultUri(RingtoneManager.TYPE_ALARM);

        mNotificationIntent = new Intent(context, AlarmActivity.class);
        mPendingIntent = PendingIntent.getActivity(context, 0, mNotificationIntent, PendingIntent.FLAG_NO_CREATE);
        Notification.Builder notificationBuilder = new Notification.Builder(context)
                .setTicker(context.getResources().getString(R.string.alarm))
                .setSmallIcon(android.R.drawable.stat_sys_warning)
                .setAutoCancel(true).setContentTitle(context.getResources().getString(R.string.alarm))
                .setContentText(context.getResources().getString(R.string.cancel)).setContentIntent(mPendingIntent)
                .setSound(alarmUri).setVibrate(mVibratePattern);

        NotificationManager mNotificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.notify(MY_NOTIFICATION_ID, notificationBuilder.build());

//        if (alarmUri == null) {
//            alarmUri = RingtoneManager
//                    .getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        }
//        LogUtils.d("alarmUri: " + alarmUri.toString());
//        Ringtone ringtone = RingtoneManager.getRingtone(context, alarmUri);
//        ringtone.play();

        setResultCode(Activity.RESULT_OK);
    }

}
