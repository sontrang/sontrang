package poiuyt.alarm.helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.util.ArrayList;
import java.util.List;

import poiuyt.alarm.data.AlarmApart;

public class DataHelper extends SQLiteOpenHelper {

    private static final String ALARMPLUS_TABLE = "alarmplus";
    private static final String COLUMN_ALARMPLUS_ID = "_id";
    private static final String COLUMN_ALARMPLUS_ACTIVE = "alarmplus_active";
    private static final String COLUMN_ALARMPLUS_DURATION = "alarmplus_duration";
    private static final String COLUMN_ALARMPLUS_TIME = "alarmplus_time";
    private static final String COLUMN_ALARMPLUS_DAYS = "alarmplus_days";
    private static final String COLUMN_ALARMPLUS_TONE = "alarmplus_tone";
    private static final String COLUMN_ALARMPLUS_VIBRATE = "alarmplus_vibrate";
    private static final String COLUMN_ALARMPLUS_LABEL = "alarmplus_label";
    private static final String TEXT_TYPE = " TEXT";
    private static final String COMMA_SEP = ",";
    private static final String DATABASE_NAME = "AlarmPlus.db";
    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_TABLE_CREATE = "CREATE TABLE "
            + ALARMPLUS_TABLE + " (" + COLUMN_ALARMPLUS_ID
            + " INTEGER PRIMARY KEY," + COLUMN_ALARMPLUS_ACTIVE + TEXT_TYPE
            + COMMA_SEP + COLUMN_ALARMPLUS_TIME + TEXT_TYPE + COMMA_SEP
            + COLUMN_ALARMPLUS_DURATION + TEXT_TYPE + COMMA_SEP
            + COLUMN_ALARMPLUS_DAYS + TEXT_TYPE + COMMA_SEP
            + COLUMN_ALARMPLUS_TONE + TEXT_TYPE + COMMA_SEP
            + COLUMN_ALARMPLUS_VIBRATE + TEXT_TYPE + COMMA_SEP
            + COLUMN_ALARMPLUS_LABEL + TEXT_TYPE + COMMA_SEP + TEXT_TYPE + ");";

    private  DataHelper instance = null;
    private  SQLiteDatabase database = null;

    public DataHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public DataHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DataHelper(context);
        }
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_TABLE_CREATE);
    }

    public SQLiteDatabase getDatabase() {
        if (database == null)
            database = instance.getWritableDatabase();
        return database;
    }

    public void deActive() {
        if (null != database && database.isOpen()) {
            database.close();
        }
        database = null;
        instance = null;
    }

    public long create(AlarmApart alarm) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ALARMPLUS_ACTIVE, alarm.getAlarmActive());
        cv.put(COLUMN_ALARMPLUS_TIME, alarm.getAlarmTimeString());

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = null;
            oos = new ObjectOutputStream(bos);
            oos.writeObject(alarm.getDays());
            byte[] buff = bos.toByteArray();

            cv.put(COLUMN_ALARMPLUS_DAYS, buff);

        } catch (Exception e) {
        }

        // cv.put(COLUMN_ALARM_, alarm.getDifficulty().ordinal());
        cv.put(COLUMN_ALARMPLUS_TONE, alarm.getAlarmTonePath());
        cv.put(COLUMN_ALARMPLUS_VIBRATE, alarm.getVibrate());
        cv.put(COLUMN_ALARMPLUS_LABEL, alarm.getAlarmLabel());

        return getDatabase().insert(ALARMPLUS_TABLE, null, cv);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public int update(AlarmApart alarm) {
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ALARMPLUS_ACTIVE, alarm.getAlarmActive());
        cv.put(COLUMN_ALARMPLUS_TIME, alarm.getAlarmTimeString());

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = null;
            oos = new ObjectOutputStream(bos);
            oos.writeObject(alarm.getDays());
            byte[] buff = bos.toByteArray();

            cv.put(COLUMN_ALARMPLUS_DAYS, buff);

        } catch (Exception e) {
        }

        // cv.put(COLUMN_ALARM_, alarm.getDifficulty().ordinal());
        cv.put(COLUMN_ALARMPLUS_TONE, alarm.getAlarmTonePath());
        cv.put(COLUMN_ALARMPLUS_VIBRATE, alarm.getVibrate());
        cv.put(COLUMN_ALARMPLUS_LABEL, alarm.getAlarmLabel());

        return getDatabase().update(ALARMPLUS_TABLE, cv,
                "_id=" + alarm.getId(), null);
    }

    public int deleteEntry(AlarmApart alarm) {
        return deleteEntry(alarm.getId());
    }

    public int deleteEntry(int id) {
        return getDatabase().delete(ALARMPLUS_TABLE, COLUMN_ALARMPLUS_ID + "=" + id, null);
    }

    public int deleteAll() {
        return getDatabase().delete(ALARMPLUS_TABLE, "1", null);
    }

    public AlarmApart getAlarm(int id) {
        String[] columns = new String[]{
                COLUMN_ALARMPLUS_ID,
                COLUMN_ALARMPLUS_ACTIVE,
                COLUMN_ALARMPLUS_TIME,
                COLUMN_ALARMPLUS_DAYS,
//				COLUMN_ALARM_,
                COLUMN_ALARMPLUS_TONE,
                COLUMN_ALARMPLUS_VIBRATE,
                COLUMN_ALARMPLUS_LABEL
        };
        Cursor c = getDatabase().query(ALARMPLUS_TABLE, columns, COLUMN_ALARMPLUS_ID + "=" + id, null, null, null,
                null);
        AlarmApart alarm = null;

        if (c.moveToFirst()) {

            alarm = new AlarmApart();
            alarm.setId(c.getInt(1));
            alarm.setAlarmActive(c.getInt(2) == 1);
            alarm.setAlarmTime(c.getString(3));
            byte[] repeatDaysBytes = c.getBlob(4);

            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(repeatDaysBytes);
            try {
                ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
                AlarmApart.Day[] repeatDays;
                Object object = objectInputStream.readObject();
                if (object instanceof AlarmApart.Day[]) {
                    repeatDays = (AlarmApart.Day[]) object;
                    alarm.setDays(repeatDays);
                }
            } catch (StreamCorruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            alarm.setAlarmTonePath(c.getString(6));
            alarm.setVibrate(c.getInt(7) == 1);
            alarm.setAlarmLabel(c.getString(8));
        }
        c.close();
        return alarm;
    }

    public Cursor getCursor() {
        String[] columns = new String[]{
                COLUMN_ALARMPLUS_ID,
                COLUMN_ALARMPLUS_ACTIVE,
                COLUMN_ALARMPLUS_TIME,
                COLUMN_ALARMPLUS_DAYS,
//				COLUMN_ALARM_,
                COLUMN_ALARMPLUS_TONE,
                COLUMN_ALARMPLUS_VIBRATE,
                COLUMN_ALARMPLUS_LABEL
        };
        return getDatabase().query(ALARMPLUS_TABLE, columns, null, null, null, null, null);
    }

    public List<AlarmApart> getAll() {
        List<AlarmApart> alarms = new ArrayList<AlarmApart>();

        Cursor cursor =getCursor();
        if (cursor.moveToFirst()) {

            do {
                AlarmApart alarm = new AlarmApart();
                alarm.setId(cursor.getInt(0));
                alarm.setAlarmActive(cursor.getInt(1) == 1);
                alarm.setAlarmTime(cursor.getString(2));
                byte[] repeatDaysBytes = cursor.getBlob(3);

                ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(
                        repeatDaysBytes);
                try {
                    ObjectInputStream objectInputStream = new ObjectInputStream(
                            byteArrayInputStream);
                    AlarmApart.Day[] repeatDays;
                    Object object = objectInputStream.readObject();
                    if (object instanceof AlarmApart.Day[]) {
                        repeatDays = (AlarmApart.Day[]) object;
                        alarm.setDays(repeatDays);
                    }
                } catch (StreamCorruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }


                alarm.setAlarmTonePath(cursor.getString(5));
                alarm.setVibrate(cursor.getInt(6) == 1);
                alarm.setAlarmLabel(cursor.getString(7));

                alarms.add(alarm);

            } while (cursor.moveToNext());
        }
        cursor.close();
        return alarms;
    }


}
