package poiuyt.alarm.unitview;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import java.util.logging.Logger;

import poiuyt.alarm.utils.LogUtils;

public abstract class BaseFragment extends Fragment {

    public abstract String getPageTitle();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onPause() {
        super.onPause();
        LogUtils.d("onPause()");
    }

    @Override
    public void onResume() {
        super.onResume();
        LogUtils.d("onResume()");
    }

}
