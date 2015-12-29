package poiuyt.alarm.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import poiuyt.alarm.utils.LogUtils;

public class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtils.d("onCreate()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LogUtils.d("onDestroy()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtils.d("onPause()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtils.d("onResume()");
    }

}
