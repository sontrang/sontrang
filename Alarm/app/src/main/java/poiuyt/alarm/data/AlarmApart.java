package poiuyt.alarm.data;

import android.media.RingtoneManager;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;


public class AlarmApart implements Serializable {

    private static final String DATE_TIME_FORMAT = "HH:mm";
    final SimpleDateFormat fm = new SimpleDateFormat(DATE_TIME_FORMAT);
    public int id;
    private Boolean alarActive = true;
    private Calendar alarmTime;
    //            = Calendar.getInstance();
    private Day[] days = {Day.MONDAY, Day.TUESDAY, Day.WEDNESDAY, Day.THURSDAY, Day.FRIDAY, Day.SATURDAY, Day.SUNDAY};

    public enum Day {SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY}

    private String repeatDay;
    private String alarmTonePath = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM).toString();
    private Boolean vibrate = true;
    private String alarmLabel = "";

    public Boolean getAlarmActive() {
        return alarActive;
    }

    public void setAlarmActive(Boolean alarmActive) {
        this.alarActive = alarmActive;
    }

    public Calendar getAlarmTime() {
        if (alarmTime.before(Calendar.getInstance()))
            alarmTime.add(Calendar.DAY_OF_MONTH, 1);
        while (!Arrays.asList(getDays()).contains(Day.values()[alarmTime.get(Calendar.DAY_OF_WEEK) - 1])) {
            alarmTime.add(Calendar.DAY_OF_MONTH, 1);
        }
        return alarmTime;
    }

    public void setAlarmTime(Calendar alarmTime) {
        this.alarmTime = alarmTime;
    }

    public String getAlarmTimeString() {
        return fm.format(alarmTime.getTime());
    }


    public void setAlarmTime(String alarmTime) {
        Calendar calendar = Calendar.getInstance();
        setAlarmTime(fm.format(calendar.getTime()));
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

    public String getTimeUntilNextAlarmMessage() {
        long timeDifference = getAlarmTime().getTimeInMillis() - System.currentTimeMillis();
        long days = timeDifference / (1000 * 60 * 60 * 24);
        long hours = timeDifference / (1000 * 60 * 60) - (days * 24);
        long minutes = timeDifference / (1000 * 60) - (days * 24 * 60) - (hours * 60);
        long seconds = timeDifference / (1000) - (days * 24 * 60 * 60) - (hours * 60 * 60) - (minutes * 60);
        String alert = "Alarm set for ";
        if (days > 0) {
            alert += String.format(
                    "%d days, %d hours, %d minutes", days,hours, minutes);
        } else {
            if (hours > 0) {
                alert += String.format("%d hours and %d minutes",  hours, minutes);
            } else {
                if (minutes > 0) {
                    alert += String.format("%d minutes", minutes);
                } else {
                    alert += "less one minute";
                }
            }
        }
        return alert + " from now";
    }
}
