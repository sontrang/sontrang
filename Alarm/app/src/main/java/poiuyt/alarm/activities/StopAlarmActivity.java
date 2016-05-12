package poiuyt.alarm.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;



import java.text.SimpleDateFormat;
import java.util.Calendar;

import poiuyt.alarm.R;

public class StopAlarmActivity extends Activity {
    private Button snooze, diss;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop_alarm_activity);
        final String DATE_TIME_FORMAT = "HH:mm";
        final SimpleDateFormat fm = new SimpleDateFormat(DATE_TIME_FORMAT);
        Intent intent = getIntent();
        Calendar calendar = Calendar.getInstance();

        tv = (TextView) findViewById(R.id.tvClock);
        tv.setText(fm.format(calendar.getTime()));

        snooze = (Button) findViewById(R.id.snooze);

        snooze.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(StopAlarmActivity.this, "Alarm Snooze ", Toast.LENGTH_LONG).show();
                finish();
            }
        });

        diss = (Button) findViewById(R.id.dissmiss);
        diss.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(StopAlarmActivity.this, "Alarm Off", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}
