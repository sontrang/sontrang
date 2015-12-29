package poiuyt.alarm.data;

import android.media.RingtoneManager;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;

import poiuyt.alarm.utils.Constants.Day;

public class AlarmApart implements Serializable {

    public int id;
    private Boolean alarActive = true;
    private Calendar alarmTime = Calendar.getInstance();
    private Day[] days = {Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY};
    private String repeatDay;
    private String alarmTonePath = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM).toString();
    private Boolean vibrate = true;
    private String alarmLabel = "AlarmPlus Clock";

    public Boolean getAlarmActive() {
        return alarActive;
    }

    public void setAlarmActive(Boolean alarmActive) {
        this.alarActive = alarmActive;
    }

    public Calendar getAlarmTime() {
        if (alarmTime.before(Calendar.getInstance()))
            alarmTime.add(Calendar.DAY_OF_MONTH, 1);
        return alarmTime;
    }

    public String getAlarmTimeString() {
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

    public void setAlarmTime(Calendar alarmTime) {
        this.alarmTime = alarmTime;
    }

    public void setAlarmTime(String alarmTime) {
        String[] timePieces = alarmTime.split(":");
        Calendar newAlarmTime = Calendar.getInstance();
        newAlarmTime.set(Calendar.HOUR_OF_DAY, Integer.parseInt(timePieces[0]));
        newAlarmTime.set(Calendar.MINUTE, Integer.parseInt(timePieces[1]));
        newAlarmTime.set(Calendar.SECOND, 0);
        setAlarmTime(newAlarmTime);
    }

    public Day[] getDays() {
        return days;
    }

    public void setDays(Day[] days) {
        this.days = days;
    }


    public String getAlarmTonePath() {
        return alarmTonePath;
    }

    public void setAlarmTonePath(String alarmTonePath) {
        this.alarmTonePath = alarmTonePath;
    }

    public Boolean getVibrate() {
        return vibrate;
    }

    public void setVibrate(Boolean vibrate) {
        this.vibrate = vibrate;
    }

    public String getAlarmLabel() {
        return alarmLabel;
    }

    public void setAlarmLabel(String AlarmLabel) {
        this.alarmLabel = AlarmLabel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRepeatDays() {
        StringBuilder daysStringBuilder = new StringBuilder();
        if (getDays().length == Day.values().length) {
            daysStringBuilder.append("Every Day");
        } else {
            Arrays.sort(getDays(), new Comparator<Day>() {

                @Override
                public int compare(Day lhs, Day rhs) {
                    return 0;
                }
            });
            for (Day d : getDays()) {
                switch (d) {
                    case TUESDAY:
                    case THURSDAY:
                    default:
                        daysStringBuilder.append(d.toString().substring(0, 3));
                        break;
                }
                daysStringBuilder.append(',');
            }
            daysStringBuilder.setLength(daysStringBuilder.length() - 1);
        }

        return daysStringBuilder.toString();
    }
}
