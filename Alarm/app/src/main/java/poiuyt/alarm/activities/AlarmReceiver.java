package poiuyt.alarm.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.content.WakefulBroadcastReceiver;

import poiuyt.alarm.utils.LogUtils;

public class AlarmReceiver extends WakefulBroadcastReceiver {

    Context context;

    @Override
    public void onReceive(Context context, Intent intent) {
        LogUtils.d("Alarm Received");
        this.context = context;
        LogUtils.d(intent.getBooleanExtra("Alo", false) + "Test EveryWhere");

//        Uri alarmUri = RingtoneManager
//                .getDefaultUri(RingtoneManager.TYPE_ALARM);
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
