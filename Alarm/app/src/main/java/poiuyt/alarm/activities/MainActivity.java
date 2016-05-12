package poiuyt.alarm.activities;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import poiuyt.alarm.R;
import poiuyt.alarm.adapters.SectionsPagerAdapter;
import poiuyt.alarm.unitview.AlarmFragment;
import poiuyt.alarm.unitview.ClockFragment;

public class MainActivity extends BaseActivity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    ImageView imgClock, imgAlarm;
    List<Fragment> fragments;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewpager);

        ActionBar mActionBar = getActionBar();
        mActionBar.setDisplayShowHomeEnabled(false);
        mActionBar.setDisplayShowTitleEnabled(false);
        ActionBar.LayoutParams layoutParams = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);

        View mCustomView = getLayoutInflater().inflate(R.layout.action_bar, null);
        imgClock = (ImageView) mCustomView.findViewById(R.id.imgclck);
        imgAlarm = (ImageView) mCustomView.findViewById(R.id.imgAlar);
        mActionBar.setCustomView(mCustomView, layoutParams);
        mActionBar.setDisplayShowCustomEnabled(true);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        fragments = getFragment();
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager(), fragments);

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setAdapter(mSectionsPagerAdapter);


        imgAlarm.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(1);
            }
        });

        imgClock.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(0);
            }
        });
    }

    private List<Fragment> getFragment() {
        List<Fragment> fList = new ArrayList<Fragment>();

        fList.add(ClockFragment.newInstance(1, "alarm"));
        fList.add(AlarmFragment.newInstance(0, "alarm"));
        return fList;
    }

    @Override
    public void onBackPressed() {
        if (mViewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            mViewPager.setCurrentItem(mViewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}
