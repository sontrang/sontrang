package poiuyt.alarm.unitview;

import android.annotation.SuppressLint;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Logger;

import poiuyt.alarm.R;
import poiuyt.alarm.adapters.AlarmFragmentAdapter;
import poiuyt.alarm.data.AlarmApart;
import poiuyt.alarm.utils.LogUtils;

@SuppressLint("NewApi")
public class AlarmFragment extends BaseFragment {
    private ImageView imgBtn;
    private View view;
    private ListView lv;
    private ArrayList<AlarmApart> arrayAlarm = new ArrayList<AlarmApart>();
    private AlarmFragmentAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.alarm_pager, container, false);
        lv = (ListView) view.findViewById(R.id.lvAlarm);
        adapter = new AlarmFragmentAdapter(getActivity(), arrayAlarm);
        lv.setAdapter(adapter);
        imgBtn = (ImageView) view.findViewById(R.id.imgBtn);
        imgBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                new TimePickerDialog(getActivity(), timePickerListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            }
        });
        return view;
    }

    private void startAlarmService(Context context, Calendar calendar) {
        LogUtils.d("Starting alarm service: calendar: " + calendar.toString());
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent("startAlarmService");
        intent.putExtra("Alo", true);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, 0);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {

        @Override
        public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
            LogUtils.d("onTimeSet");
            AlarmApart item = new AlarmApart();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
            calendar.set(Calendar.MINUTE, selectedMinute);
            item.setAlarmTime(calendar);
            arrayAlarm.add(item);
            adapter.notifyDataSetChanged();
            startAlarmService(getActivity(), calendar);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static AlarmFragment newInstance(int page, String title) {
        AlarmFragment alarmfragment = new AlarmFragment();
        Bundle args = new Bundle();
        args.putInt("someInt", page);
        args.putString("someTitle", title);
        alarmfragment.setArguments(args);
        return alarmfragment;
    }

    @Override
    public String getPageTitle() {
        return "";
    }

}