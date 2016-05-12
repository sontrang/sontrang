package poiuyt.alarm.adapters;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

public class ListOfMusicAdapter extends BaseAdapter {



    Context context;
    ArrayList<String> arrayList;

    public ListOfMusicAdapter(Context context, ArrayList<String> arrayList) {
    super();
        this.context= context;
        this.arrayList=arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return null;
    }

}
