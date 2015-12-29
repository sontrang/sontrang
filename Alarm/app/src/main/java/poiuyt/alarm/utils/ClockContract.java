
package poiuyt.alarm.utils;

import android.net.Uri;
import android.provider.BaseColumns;

public final class ClockContract {

    public static final String AUTHORITY = "poiuyt.alarm";

    private ClockContract() {
    }

    public interface AlarmSettingColumns extends BaseColumns {

        public static final Uri NO_RINGTONE_URI = Uri.EMPTY;
        public static final String NO_RINGTONE = NO_RINGTONE_URI.toString();
        public static final String VIBRATE = "vibrate";
        public static final String LABEL = "label";
        public static final String RINGTONE = "ringtone";
    }

    public interface AlarmsColumns extends AlarmSettingColumns, BaseColumns {

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/alarms");
        public static final String HOUR = "hour";
        public static final String MINUTES = "minutes";
        public static final String DAYS_OF_WEEK = "daysofweek";
        public static final String ENABLED = "enabled";
        public static final String DELETE_AFTER_USE = "delete_after_use";
    }

    public interface InstancesColumns extends AlarmSettingColumns, BaseColumns {

        public static final Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/instances");
        public static final int SILENT_STATE = 0;
        public static final int LOW_NOTIFICATION_STATE = 1;
        public static final int HIDE_NOTIFICATION_STATE = 2;
        public static final int HIGH_NOTIFICATION_STATE = 3;
        public static final int SNOOZE_STATE = 4;
        public static final int FIRED_STATE = 5;
        public static final int MISSED_STATE = 6;
        public static final int DISMISSED_STATE = 7;
        public static final String YEAR = "year";
        public static final String MONTH = "month";
        public static final String DAY = "day";
        public static final String HOUR = "hour";
        public static final String MINUTES = "minutes";
        public static final String ALARM_ID = "alarm_id";
        public static final String ALARM_STATE = "alarm_state";
    }
}
