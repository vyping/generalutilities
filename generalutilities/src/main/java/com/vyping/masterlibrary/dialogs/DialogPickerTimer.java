package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_REFRESH_ACCEPT;
import static com.vyping.masterlibrary.Common.Definitions.DATEPICKER_SPINNER;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.ContextThemeWrapper;
import android.widget.TimePicker;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Common.DateTools;
import com.vyping.masterlibrary.Common.Definitions;
import com.vyping.masterlibrary.R;

import java.util.Calendar;
import java.util.Locale;

public abstract class DialogPickerTimer extends CreateDialog {

    private Context context;

    private TimePicker Tp_Picker;

    private int prevHour, prevMinute, hour, minute;


    /*----------- SetUp - Section ----------*/

    @SuppressLint("SetTextI18n")
    public DialogPickerTimer(Context context, int parameters, @Definitions.TypeClock int type) {

        super(context, parameters);

        setParameters(context,"");
        setDialogViews(type);
        setModeButtons(setModeButtons());
        setDialogListener(new CreateDialog.DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() {

                hour = prevHour;
                minute = prevMinute;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    Tp_Picker.setHour(hour);
                    Tp_Picker.setMinute(minute);

                } else {

                    Tp_Picker.setCurrentHour(hour);
                    Tp_Picker.setCurrentMinute(minute);
                }

                setModeButtons(setModeButtons());
            }

            @Override
            public void PositiveClick() {

                setPickerTimer();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public DialogPickerTimer(Context context, int parameters, String time, @Definitions.TypeClock int type) {

        super(context, parameters);

        setParameters(context,time);
        setDialogViews(type);
        setModeButtons(setModeButtons());
        setDialogListener(new CreateDialog.DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() {

                hour = prevHour;
                minute = prevMinute;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    Tp_Picker.setHour(hour);
                    Tp_Picker.setMinute(minute);

                } else {

                    Tp_Picker.setCurrentHour(hour);
                    Tp_Picker.setCurrentMinute(minute);
                }

                setModeButtons(setModeButtons());
            }

            @Override
            public void PositiveClick() {

                setPickerTimer();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public DialogPickerTimer(Context context, int parameters, long time, @Definitions.TypeClock int type) {

        super(context, parameters);

        setParameters(context,time);
        setDialogViews(type);
        setModeButtons(setModeButtons());
        setDialogListener(new CreateDialog.DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() {

                hour = prevHour;
                minute = prevMinute;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    Tp_Picker.setHour(hour);
                    Tp_Picker.setMinute(minute);

                } else {

                    Tp_Picker.setCurrentHour(hour);
                    Tp_Picker.setCurrentMinute(minute);
                }

                setModeButtons(setModeButtons());
            }

            @Override
            public void PositiveClick() {

                setPickerTimer();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public DialogPickerTimer(Context context, int parameters, Calendar time, @Definitions.TypeClock int type) {

        super(context, parameters);

        setParameters(context,time);
        setDialogViews(type);
        setModeButtons(setModeButtons());
        setDialogListener(new CreateDialog.DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() {

                hour = prevHour;
                minute = prevMinute;

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    Tp_Picker.setHour(hour);
                    Tp_Picker.setMinute(minute);

                } else {

                    Tp_Picker.setCurrentHour(hour);
                    Tp_Picker.setCurrentMinute(minute);
                }

                setModeButtons(setModeButtons());
            }

            @Override
            public void PositiveClick() {

                setPickerTimer();
            }
        });
    }

    private void setParameters(@NonNull Context context, @NonNull String date) {

        this.context = context;

        Calendar calendar = Calendar.getInstance(Locale.getDefault());

        if (!date.equals("")) {

            calendar = new DateTools().readStringDate(date);
        }

        prevHour = hour = calendar.get(Calendar.HOUR_OF_DAY);
        prevMinute = minute = calendar.get(Calendar.MINUTE);
    }

    private void setParameters(@NonNull Context context, long date) {

        this.context = context;

        Calendar calendar = Calendar.getInstance(Locale.getDefault());

        if (date != 0) {

            calendar.setTimeInMillis(date);
        }

        prevHour = hour = calendar.get(Calendar.HOUR_OF_DAY);
        prevMinute = minute = calendar.get(Calendar.MINUTE);
    }

    private void setParameters(@NonNull Context context, Calendar date) {

        this.context = context;

        Calendar calendar = Calendar.getInstance(Locale.getDefault());

        if (date != null) {

            calendar = date;
        }

        prevHour = hour = calendar.get(Calendar.HOUR_OF_DAY);
        prevMinute = minute = calendar.get(Calendar.MINUTE);
    }

    private void setDialogViews(@Definitions.TypeClock int Type) {

        int style, attr;

        if (Type == DATEPICKER_SPINNER) {

            style = R.style.DialogPickerTimeSpinner;
            attr = R.attr.dialogPickerTimeSpinner;

        } else {

            style = R.style.DialogPickerTimeClock;
            attr = R.attr.dialogPickerTimeClock;
        }

        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);
        Tp_Picker = new TimePicker(wrapper, null, attr, style);
        Paris.style(Tp_Picker).apply(style);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            Tp_Picker.setHour(hour);
            Tp_Picker.setMinute(minute);

        } else {

            Tp_Picker.setCurrentHour(hour);
            Tp_Picker.setCurrentMinute(minute);
        }

        Tp_Picker.setOnTimeChangedListener(onTimeChangedListener);

        addCustomView(Tp_Picker);
    }


    /*----- Methods -----*/

    private void setPickerTimer() {

        String hour, minute;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            hour = new DateTools().completeDigitsDate(Tp_Picker.getHour());
            minute = new DateTools().completeDigitsDate(Tp_Picker.getMinute());

        } else {

            hour = new DateTools().completeDigitsDate(Tp_Picker.getCurrentHour());
            minute = new DateTools().completeDigitsDate(Tp_Picker.getCurrentMinute());
        }

        String time = hour + ":" + minute;

        Calendar calendar = new DateTools().readStringTime(time);
        long milis = calendar.getTimeInMillis();

        SetTime(calendar, milis, time, hour, minute);
    }


    /*----- Listeners -----*/

    private final TimePicker.OnTimeChangedListener onTimeChangedListener = new TimePicker.OnTimeChangedListener() {

        @Override
        public void onTimeChanged(TimePicker timePicker, int i, int i1) {

            hour = i;
            minute =i1;

            setModeButtons(setModeButtons());
        }

        private void DummyVoid() { }
    };


    /*----- Utilities -----*/

    private int setModeButtons() {

        if (hour == prevHour && minute == prevMinute) {

            return BUTTONS_CANCEL;

        } else {

            return BUTTONS_REFRESH_ACCEPT;
        }
    }


    /*----------- Return - Section ----------*/

    protected abstract void SetTime(Calendar calendar, long milis, String time, String hour, String minute);
}
