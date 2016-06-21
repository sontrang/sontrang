package poiuyt.alarm.unitview;

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
import java.util.Date;

import poiuyt.alarm.R;
import poiuyt.alarm.activities.AlarmService;
import poiuyt.alarm.model.Alarm;
import poiuyt.alarm.unitview.DurationTimeDialog.DcallBack;
import poiuyt.alarm.unitview.LabelDialog.LcallBack;


public class FragmentAdapter extends BaseAdapter {
    private static final String DATE_TIME_FORMAT = "HH:mm";
    private Context mContext;
    private ArrayList<Alarm> arrayAlarm = new ArrayList<Alarm>();
    private Icallback callback;
    private TextView tvClock;
    private TextView tvLabel, tvDuration, tvRingTone;
    private ToggleButton toActive, toSun, toM, toTue, toW, toThu, toFri, toSat;
    private ImageView imgRing, imgDelete;
    private CheckBox cbRepeat, cbVibravate;
    private LinearLayout lnRecycle, lnRepeatDay, lnItemAlarm;
    private RelativeLayout rlTime;
    private View view;
    private Date date;

    Calendar calendar;
    TimePickerDialog.OnTimeSetListener onTimeSetListener;
    private SimpleDateFormat fm = new SimpleDateFormat(DATE_TIME_FORMAT);

    interface Icallback {
        void updateAlarmList();
    }

    public FragmentAdapter(Context mContext, ArrayList<Alarm> arrayAlarm, Icallback callback) {
        super();
        this.mContext = mContext;
        this.arrayAlarm = arrayAlarm;
        this.callback = callback;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        if (convertView != null) {
            view = convertView;
        } else {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.alarm_item, null);
        }

        date = arrayAlarm.get(position).getTime();
        tvClock = (TextView) view.findViewById(R.id.tvClock);
        tvClock.setText(fm.format(date.getTime()));

        onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectHour, int selectMinute) {
                calendar.set(Calendar.HOUR_OF_DAY, selectHour);
                calendar.set(Calendar.MINUTE, selectMinute);
//                Alarm item = arrayAlarm.get(position);
//                item.setTime(date);
                notifyDataSetChanged();
                AlarmService.startAlarmService(mContext, calendar);
                tvClock.setText(fm.format(date.getTime()));
            }
        };

//        tvClock.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new TimePickerDialog(mContext, onTimeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true).show();
//            }
//        });

        toActive = (ToggleButton) view.findViewById(R.id.toActive);
        toActive.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView,
                                         boolean isChecked) {
                arrayAlarm.get(position).setActive(isChecked);
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


        Uri.parse("android.resource://home.sample.alarmsample/" + R.raw.one);
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
        imgDelete.setOnClickListener(new OnClickListener() {
                                         @Override
                                         public void onClick(View v) {
                                             arrayAlarm.remove(position);
                                             notifyDataSetChanged();
                                             callback.updateAlarmList();
                                         }
                                     }

        );


        lnItemAlarm = (LinearLayout) view.findViewById(R.id.lnItemAlarm);
        rlTime = (RelativeLayout) view.findViewById(R.id.rlTime);
        lnRecycle = (LinearLayout) view.findViewById(R.id.lnRecycle);
        lnRecycle.setOnClickListener(new  OnClickListener() {

                                                 @Override
                                                 public void onClick(View v) {
                                                     lnItemAlarm.setVisibility(View.GONE);
                                                 }
                                             }

        );
        rlTime.setOnClickListener(new  OnClickListener() {

                                               @Override
                                               public void onClick(View v) {
                                                   if (lnItemAlarm.getVisibility() == View.GONE) {
                                                       lnItemAlarm.setVisibility(View.VISIBLE);
                                                   } else {
                                                       lnItemAlarm.setVisibility(View.GONE);
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


    public void remove(Alarm object) {
        arrayAlarm.remove(object);
        notifyDataSetChanged();
    }

}
