package poiuyt.alarm.unitview;


import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import poiuyt.alarm.R;
import poiuyt.alarm.activities.AlarmService;
import poiuyt.alarm.data.AlarmApart;
import poiuyt.alarm.helpers.DataHelper;
import poiuyt.alarm.utils.LogUtils;

public class AlarmFragment extends BaseFragment {
    private ImageView imgBtn;
    private View view;
    private ListView lv;
    private ArrayList<AlarmApart> arrayAlarm = new ArrayList<AlarmApart>();
    private AlarmFragmentAdapter adapter;

    AlarmFragmentAdapter.Icallback callback = new AlarmFragmentAdapter.Icallback() {
        @Override
        public void updateAlarmList() {
//            DataHelper dataHelper= new DataHelper(getActivity());
            final List<AlarmApart> alarms =arrayAlarm;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AlarmFragment.this.adapter.notifyDataSetChanged();
                    if (alarms.size() > 0) {
                        getActivity().findViewById(R.id.noAlarms).setVisibility(View.INVISIBLE);
                    } else {
                        getActivity().findViewById(R.id.noAlarms).setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.alarm_pager, container, false);
         final DataHelper dataHelper= new DataHelper(getActivity());


        final TimePickerDialog.OnTimeSetListener timePickerListener = new TimePickerDialog.OnTimeSetListener() {

            @Override
            public void onTimeSet(TimePicker view, int selectedHour, int selectedMinute) {
                LogUtils.d("onTimeSet");
                AlarmApart item = new AlarmApart();
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, selectedHour);
                calendar.set(Calendar.MINUTE, selectedMinute);
                item.setTime(calendar);
                arrayAlarm.add(item);
                dataHelper.addItem(item);
                callback.updateAlarmList();
                AlarmService.startAlarmService(getContext(), calendar);
                Toast.makeText(getContext(), item.getTimeUntilNextAlarmMessage(), Toast.LENGTH_LONG).show();
            }
        };

        lv = (ListView) view.findViewById(R.id.lvAlarm);
        adapter = new AlarmFragmentAdapter(getActivity(), arrayAlarm, callback);
        lv.setAdapter(adapter);

        imgBtn = (ImageView) view.findViewById(R.id.imgBtn);
        imgBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
                new TimePickerDialog(getActivity(), timePickerListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
            }
        });
        return view;
    }

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

    @Override
    public void onResume() {
        super.onResume();
        callback.updateAlarmList();
    }


}