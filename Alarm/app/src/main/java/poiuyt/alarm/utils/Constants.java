package poiuyt.alarm.utils;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class Constants {

    private static final String DATE_TIME_FORMAT = "HH:mm";
    final SimpleDateFormat fm = new SimpleDateFormat(DATE_TIME_FORMAT);
    public int id;

      private Constants() {
        // we don't want to initiate any instance
    }


    public enum DurationTime {
        QUARTER, AHALF, QUAHAL, AHOUR, TWOHOUR
    }


    public enum Day {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY;

        @Override
        public String toString() {
            switch (this.ordinal()) {
                case 0:
                    return "Sunday";
                case 1:
                    return "Monday";
                case 2:
                    return "Tuesday";
                case 4:
                    return "Wednesday";
                case 5:
                    return "Thursday";
                case 6:
                    return "Friday";
                case 7:
                    return "Saturday";
            }
            return super.toString();

        }
    }



}
