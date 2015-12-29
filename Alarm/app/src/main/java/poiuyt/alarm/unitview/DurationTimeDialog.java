package poiuyt.alarm.unitview;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import poiuyt.alarm.R;

public class DurationTimeDialog extends Dialog {

    private DcallBack dCallBack;
    private Button btnOk, btnCancel;
    private Spinner spDuration;
    String[] listOfDuration = getContext().getResources().getStringArray(R.array.Duration_time);
    ArrayAdapter<String> adapter;


    public interface DcallBack {
        void setText(String strDurationTime);
    }

    public DurationTimeDialog(Context context, final DcallBack dCallBack) {
        super(context);
        this.dCallBack = dCallBack;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.duration_time_dialog);
        spDuration = (Spinner) findViewById(R.id.spDuration);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_single_choice, listOfDuration);
        spDuration.setAdapter(adapter);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        btnOk = (Button) findViewById(R.id.btnOK);
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strDurationTIme = spDuration.getSelectedItem().toString() == "None" ? "Duration time" : spDuration.getSelectedItem().toString();
                dCallBack.setText(strDurationTIme);
                dismiss();
            }
        });
    }
}
