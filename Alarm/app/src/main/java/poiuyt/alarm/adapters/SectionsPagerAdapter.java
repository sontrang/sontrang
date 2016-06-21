package poiuyt.alarm.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

public class SectionsPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fList;

    public SectionsPagerAdapter(FragmentManager fm, List<Fragment> fList) {
        super(fm);
        this.fList = fList;
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class
        // below).
        return fList.get(position);

    }

    @Override
    public int getCount() {
        return fList.size();
    }

}
