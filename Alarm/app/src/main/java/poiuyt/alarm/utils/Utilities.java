package poiuyt.alarm.utils;

import android.graphics.Color;
import java.util.Calendar;


public class Utilities {

    public static final String[] BACKGROUND_SPECTRUM = { "#212121", "#27232e", "#2d253a",
            "#332847", "#382a53", "#3e2c5f", "#442e6c", "#393a7a", "#2e4687", "#235395", "#185fa2",
            "#0d6baf", "#0277bd", "#0d6cb1", "#1861a6", "#23569b", "#2d4a8f", "#383f84", "#433478",
            "#3d3169", "#382e5b", "#322b4d", "#2c273e", "#272430" };

    public static Calendar getAlarmTime(Calendar alarmTime) {
        if (alarmTime.before(Calendar.getInstance()))
            alarmTime.add(Calendar.DAY_OF_MONTH, 1);
        return alarmTime;
    }

    public static String getAlarmTimeString(Calendar alarmTime) {
        String time = "";
        if (alarmTime.get(Calendar.HOUR_OF_DAY) <= 9) {
            time += "0";
            time += String.valueOf(alarmTime.get(Calendar.HOUR_OF_DAY));
            time += ":";
        }
        if (alarmTime.get(Calendar.MINUTE) <= 9) {
            time += "0";
            time += String.valueOf(alarmTime.get(Calendar.MINUTE));
        }
        return time;
    }


    public static int getCurrentHourColor() {
        final int hourOfDay = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
        return Color.parseColor(BACKGROUND_SPECTRUM[hourOfDay]);
    }

    public boolean isEmpty(String input) {
        return input == null || input.length() == 0;
    }


}