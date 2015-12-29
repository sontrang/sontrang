package poiuyt.alarm.unitview;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import poiuyt.alarm.R;

public class LabelDialog extends Dialog {
    private TextView tvLabel;
    private EditText edtLabel;
    private Button btnOK, btnCancel;
    private LcallBack callback;

    public interface LcallBack {
        void setText(String label);
    }

    public LabelDialog(Context mContext, LcallBack lcallBack) {
        super(mContext);
        this.callback = lcallBack;
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.label_dialog);
        tvLabel = (TextView) findViewById(R.id.tvLabel);
        edtLabel = (EditText) findViewById(R.id.edtLabel);
        btnOK = (Button) findViewById(R.id.btnOK);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                dismiss();
            }
        });
        btnOK.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                callback.setText((edtLabel.getText().toString()));
                dismiss();
            }
        });
    }

}