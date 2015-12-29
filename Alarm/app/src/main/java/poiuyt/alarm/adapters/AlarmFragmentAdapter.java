package poiuyt.alarm.adapters;

import android.annotation.SuppressLint;
import android.app.TimePickerDialog;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import poiuyt.alarm.R;
import poiuyt.alarm.data.AlarmApart;
import poiuyt.alarm.unitview.DurationTimeDialog;
import poiuyt.alarm.unitview.DurationTimeDialog.DcallBack;
import poiuyt.alarm.unitview.LabelDialog;
import poiuyt.alarm.unitview.LabelDialog.LcallBack;
import poiuyt.alarm.unitview.RingToneDialog;

@SuppressLint({"SimpleDateFormat", "InflateParams", "NewApi"})
public class AlarmFragmentAdapter extends BaseAdapter {
    private static final String DATE_TIME_FORMAT = "HH:mm";
    private Context mContext;
    private ArrayList<AlarmApart> arrayAlarm = new ArrayList<AlarmApart>();

    public AlarmFragmentAdapter(Context mContext,
                                ArrayList<AlarmApart> arrayAlarm) {
        super();
        this.mContext = mContext;
        this.arrayAlarm = arrayAlarm;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final TextView tvClock;
        final TextView tvLabel, tvDuration, tvRingTone;
        ToggleButton toActive, toSun, toM, toTue, toW, toThu, toFri, toSat;
        ImageView imgRing, imgDelete;
        CheckBox cbRepeat, cbVibravate;
        final LinearLayout lnRecycle, lnRepeatDay, lnAlarm;
        RelativeLayout rlAlarm;
        View view;
        if (convertView != null) {
            view = convertView;
        } else {
            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.alarm_item, null);
        }

        tvClock = (TextView) view.findViewById(R.id.tvClock);
        final Calendar calendar = arrayAlarm.get(position).getAlarmTime();
        final SimpleDateFormat fm = new SimpleDateFormat(DATE_TIME_FORMAT);
        tvClock.setText(fm.format(calendar.getTime()));
        final TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectHour, int selectMinute) {
                calendar.set(Calendar.HOUR_OF_DAY, selectHour);
                calendar.set(Calendar.MINUTE, selectMinute);
                tvClock.setText(fm.format(calendar.getTime()));
            }
        };
        tvClock.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new TimePickerDialog(mContext, onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), false).show();
            }
        });

        toActive = (ToggleButton) view.findViewById(R.id.toActive);
        toActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                arrayAlarm.get(position).setAlarmActive(isChecked);
            }
        });

        tvDuration = (TextView) view.findViewById(R.id.tvDuration);
        tvDuration.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new DurationTimeDialog(mContext, new DcallBack() {
                    @Override
                    public void setText(String strDurationTime) {
                        tvDuration.setText(strDurationTime);
                    }
                }).show();

            }
        });

        lnRepeatDay = (LinearLayout) view.findViewById(R.id.lnRepeatDay);
        cbRepeat = (CheckBox) view.findViewById(R.id.cbRepeat);
        cbRepeat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lnRepeatDay.getVisibility() == View.GONE) {
                    lnRepeatDay.setVisibility(View.VISIBLE);
                } else {
                    lnRepeatDay.setVisibility(View.GONE);
                }
            }
        });

        toSat = (ToggleButton) view.findViewById(R.id.toSa);
        toSat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                } else {
                }
            }
        });

        toM = (ToggleButton) view.findViewById(R.id.toM);
        toM.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                } else {
                }
            }
        });

        toThu = (ToggleButton) view.findViewById(R.id.toTh);
        toThu.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                } else {
                }
            }
        });

        toTue = (ToggleButton) view.findViewById(R.id.toTu);
        toTue.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                } else {
                }
            }
        });

        toW = (ToggleButton) view.findViewById(R.id.toW);
        toW.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                } else {
                }
            }
        });

        toFri = (ToggleButton) view.findViewById(R.id.toF);
        toFri.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                } else {
                }
            }
        });

        toSun = (ToggleButton) view.findViewById(R.id.toSun);
        toSun.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                if (isChecked) {
                } else {
                }
            }
        });


       Uri.parse("android.resource://home.sample.alarmsample/"+R.raw.one);
               /*
       File ring = new File("home.sample.alarmsample/raw");
        Uri path = MediaStore.Audio.Media.getContentUriForPath(ring.getAbsolutePath());
        */
        tvRingTone = (TextView) view.findViewById(R.id.tvRingTone);
        tvRingTone.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new RingToneDialog(mContext, new RingToneDialog.RcallBack() {
                    @Override
                    public void setText(String strRingTone) {
                        tvRingTone.setText(strRingTone);
                    }
                }).show();
            }
        });
        imgRing = (ImageView) view.findViewById(R.id.imgRing);
        imgRing.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new RingToneDialog(mContext, new RingToneDialog.RcallBack() {
                    @Override
                    public void setText(String strRingTone) {
                        tvRingTone.setText(strRingTone);
                    }
                }).show();
            }
        });

//            @Override
//            public void onClick(DialogInterface dialog, int item) {
//                Uri path = Uri.parse("android.resource://hom.sample.alarmsample/raw/");
//                switch (item) {
//                    case 0:
//                        RingtoneManager.setActualDefaultRingtoneUri(mContext, RingtoneManager.TYPE_RINGTONE, path);
//                        //RingtoneManager.getRingtone(getApplicationContext(), path).play();
//                        Log.d("RutaTONO", RingtoneManager.getActualDefaultRingtoneUri(mContext, RingtoneManager.TYPE_RINGTONE).toString());
//                        break;
//                    case 1:
//                        RingtoneManager.setActualDefaultRingtoneUri(mContext, RingtoneManager.TYPE_NOTIFICATION, path);
//                        break;
//                    case 2:
//                        RingtoneManager.setActualDefaultRingtoneUri(mContext, RingtoneManager.TYPE_ALARM, path);
//                        break;
//                }


        cbVibravate = (CheckBox) view.findViewById(R.id.cbVibrate);
        cbVibravate.setOnClickListener(new OnClickListener() {
                                           @Override
                                           public void onClick(View v) {

                                           }
                                       }

        );

        tvLabel = (TextView) view.findViewById(R.id.tvLabel);
        tvLabel.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                new LabelDialog(mContext, new LcallBack() {
                    @Override
                    public void setText(String label) {
                        tvLabel.setText(label);
                    }
                }).show();
            }
        });

        imgDelete = (ImageView) view.findViewById(R.id.imgDelete);
        imgDelete.setOnClickListener(new

                                             OnClickListener() {
                                                 @Override
                                                 public void onClick(View v) {
                                                     arrayAlarm.remove(position);
                                                     notifyDataSetChanged();
                                                 }
                                             }

        );

        lnAlarm = (LinearLayout) view.findViewById(R.id.lnAlarm);
        rlAlarm = (RelativeLayout) view.findViewById(R.id.rlAlarm);
        lnRecycle = (LinearLayout) view.findViewById(R.id.lnRecycle);
        lnRecycle.setOnClickListener(new

                                             OnClickListener() {

                                                 @Override
                                                 public void onClick(View v) {
                                                     lnAlarm.setVisibility(View.GONE);
                                                 }
                                             }

        );
        rlAlarm.setOnClickListener(new

                                           OnClickListener() {

                                               @Override
                                               public void onClick(View v) {
                                                   if (lnAlarm.getVisibility() == View.GONE) {
                                                       lnAlarm.setVisibility(View.VISIBLE);
                                                   } else {
                                                       lnAlarm.setVisibility(View.GONE);
                                                   }
                                               }
                                           }

        );
        return view;
    }

    @Override
    public int getCount() {
        return arrayAlarm.size();
    }

    @Override
    public Object getItem(int arg0) {
        return 0;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    public void remove(AlarmApart object) {
        arrayAlarm.remove(object);
        notifyDataSetChanged();
    }

}