package poiuyt.alarm.unitview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AnalogClock;
import android.widget.DigitalClock;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

import poiuyt.alarm.R;

public class ClockFragment extends BaseFragment {

    AnalogClock clk;
    View view;
    TextView mTimeDisplay;
    private Date date;
    SimpleDateFormat fm;
    private static final String DATE_FOMAT = "EEE, dd-MM";

    @Override
    public String getPageTitle() {
        return getString(R.string.clock_fragment_title);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.clock_pager, container, false);
        clk = (AnalogClock) view.findViewById(R.id.clk);
        date = new Date();
        fm = new SimpleDateFormat(DATE_FOMAT);
        mTimeDisplay = (TextView) view.findViewById(R.id.tvClockDate);
        mTimeDisplay.setText(fm.format(date));
        return view;
    }

    public static ClockFragment newInstance(int page, String title) {
        ClockFragment fragment = new ClockFragment();
        Bundle args = new Bundle();
        args.putInt("page number", page);
        args.putString("page title", title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
