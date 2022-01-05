package com.vyping.masterlibrary.Dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_SINGLE;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_THREE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.widget.LinearLayout;
import android.widget.TimePicker;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Common.DateTools;
import com.vyping.masterlibrary.Common.Definitions;
import com.vyping.masterlibrary.R;

import java.util.Calendar;
import java.util.Locale;

public abstract class DialogPickerTimer {

    private Context context;
    private final CreateDialog createDialog;

    private TimePicker Tp_Picker;

    private int prevHour, prevMinute, hour, minute;


    /*----------- SetUp - Section ----------*/

    @SuppressLint("SetTextI18n")
    public DialogPickerTimer(Context context, int parameters, @Definitions.TypeClock int type, boolean format) {

        setParameters(context,"");

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(@NonNull Dialog dialog) {

                setViewsOnLayout(dialog, type);

                Tp_Picker.setIs24HourView(format);
                Tp_Picker.setHour(hour);
                Tp_Picker.setMinute(minute);
                Tp_Picker.setOnTimeChangedListener(onTimeChangedListener);

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

                hour = prevHour;
                minute = prevMinute;

                Tp_Picker.setHour(hour);
                Tp_Picker.setMinute(minute);

                setOptionButtons();
            }

            @Override
            protected void PositiveClick() {

                setPickerDate();
            }
        };

        setOptionButtons();
    }

    @SuppressLint("SetTextI18n")
    public DialogPickerTimer(Context context, int parameters, String time, @Definitions.TypeClock int type) {

        setParameters(context, time);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(@NonNull Dialog dialog) {

                setViewsOnLayout(dialog, type);

                Tp_Picker.setHour(hour);
                Tp_Picker.setMinute(minute);
                Tp_Picker.setOnTimeChangedListener(onTimeChangedListener);

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

            }

            @Override
            protected void PositiveClick() {

                setPickerDate();
            }
        };

        setOptionButtons();
    }

    @SuppressLint("SetTextI18n")
    public DialogPickerTimer(Context context, int parameters, long time, @Definitions.TypeClock int type) {

        setParameters(context, time);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(@NonNull Dialog dialog) {

                setViewsOnLayout(dialog, type);

                Tp_Picker.setHour(hour);
                Tp_Picker.setMinute(minute);
                Tp_Picker.setOnTimeChangedListener(onTimeChangedListener);

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

            }

            @Override
            protected void PositiveClick() {

                setPickerDate();
            }
        };

        setOptionButtons();
    }

    @SuppressLint("SetTextI18n")
    public DialogPickerTimer(Context context, int parameters, Calendar time, @Definitions.TypeClock int type) {

        setParameters(context, time);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(@NonNull Dialog dialog) {

                setViewsOnLayout(dialog, type);

                Tp_Picker.setHour(hour);
                Tp_Picker.setMinute(minute);
                Tp_Picker.setOnTimeChangedListener(onTimeChangedListener);

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

            }

            @Override
            protected void PositiveClick() {

                setPickerDate();
            }
        };

        setOptionButtons();
    }

    private void setOptionButtons() {

        if (hour == prevHour && minute == prevMinute) {

            createDialog.setModeButtons(BUTTONS_SINGLE);

        } else {

            createDialog.setModeButtons(BUTTONS_THREE);
        }
    }


    /*----- Listeners -----*/

    private final TimePicker.OnTimeChangedListener onTimeChangedListener = new TimePicker.OnTimeChangedListener() {

        @Override
        public void onTimeChanged(TimePicker timePicker, int i, int i1) {

            hour = i;
            minute =i1;

            setOptionButtons();
        }

        private void DummyVoid() { }
    };


    /*----- Tools -----*/

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

    private void setPickerDate() {

        String hour = new DateTools().completeDigitsDate(Tp_Picker.getHour());
        String minute = new DateTools().completeDigitsDate(Tp_Picker.getMinute());
        String time = hour + ":" + minute;

        Calendar calendar = new DateTools().readStringTime(time);
        long milis = calendar.getTimeInMillis();

        SetTime(calendar, milis, time, hour, minute);
    }

    private void setViewsOnLayout(@NonNull Dialog dialog, @Definitions.TypeClock int Type) {

        int style, attr;

        if (Type == Definitions.DATEPICKER_SPINNER) {

            style = R.style.DialogPickerTimeSpinner;
            attr = R.attr.dialogPickerTimeSpinner;

        } else {

            style = R.style.DialogPickerTimeClock;
            attr = R.attr.dialogPickerTimeClock;
        }

        LinearLayout layout = dialog.findViewById(R.id.Ll_Custom_MainContainer);
        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        Tp_Picker = new TimePicker(wrapper, null, attr, style);

        Paris.style(Tp_Picker).apply(style);

        layout.addView(Tp_Picker);
    }


    /*----------- Return - Section ----------*/

    protected abstract void SetTime(Calendar calendar, long milis, String time, String hour, String minute);
}
