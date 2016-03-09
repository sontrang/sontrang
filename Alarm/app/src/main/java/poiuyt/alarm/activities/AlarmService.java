package poiuyt.alarm.activities;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import poiuyt.alarm.data.AlarmApart;
import poiuyt.alarm.helpers.DataHelper;
import poiuyt.alarm.utils.LogUtils;


public class AlarmService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    public static void startAlarmService(Context context, Calendar calendar) {
        LogUtils.d("Starting alarm service: calendar: " + calendar.toString());
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intentOfAlarm = new Intent(context, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intentOfAlarm, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }


}
