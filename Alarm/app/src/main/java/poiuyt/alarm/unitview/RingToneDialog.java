package poiuyt.alarm.unitview;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import poiuyt.alarm.R;

public class RingToneDialog extends Dialog {

    private RcallBack rCallBack;
    private Button btnOk, btnCancel;
    private Spinner spRingTone;
    private String[] listOfRingTone = getContext().getResources().getStringArray(R.array.RingTones);
    private int[] resID = {R.raw.one, R.raw.two, R.raw.three, R.raw.four, R.raw.five, R.raw.six, R.raw.seven, R.raw.eight};
    ArrayAdapter<String> adapter;


    public interface RcallBack {
        void setText(String strRingTone);
    }

    public RingToneDialog(Context context, final RcallBack rCallBack) {
        super(context);
        this.rCallBack = rCallBack;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.ringtone_dialog);
        spRingTone = (Spinner) findViewById(R.id.spRingTone);
        adapter = new ArrayAdapter<String>(context, android.R.layout.simple_list_item_single_choice, listOfRingTone);
        spRingTone.setAdapter(adapter);
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
                int i= spRingTone.getSelectedItemPosition();
                int b=resID[i];
                String strRingTone = spRingTone.getSelectedItem().toString() == "None" ? "Silent" : spRingTone.getSelectedItem().toString();
                rCallBack.setText(strRingTone);
                dismiss();
            }
        });
    }
}

