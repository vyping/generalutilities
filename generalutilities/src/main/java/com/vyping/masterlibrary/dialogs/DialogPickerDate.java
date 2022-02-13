package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_REFRESH_ACCEPT;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.view.ContextThemeWrapper;
import android.widget.DatePicker;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Common.DateTools;
import com.vyping.masterlibrary.Common.Definitions;
import com.vyping.masterlibrary.R;

import java.util.Calendar;
import java.util.Locale;

public abstract class DialogPickerDate extends CreateDialog {

    private Context context;

    private DatePicker Dp_Picker;

    private int prevDay, prevMonth, prevYear, day, month, year;


    /*----------- SetUp - Section ----------*/

    @SuppressLint("SetTextI18n")
    public DialogPickerDate(Context context, int parameters, @Definitions.TypeCalendar int type) {

        super(context, parameters);

        setParameters(context, "");
        setDialogViews(type);
        setModeButtons(setModeButtons());
        setDialogListener(new CreateDialog.DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() {

                day = prevDay;
                month = prevMonth;
                year = prevYear;

                Dp_Picker.updateDate(year, month - 1, day);

                setModeButtons(setModeButtons());
            }

            @Override
            public void PositiveClick() {

                setPickerDate();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public DialogPickerDate(Context context, int parameters, String date, @Definitions.TypeCalendar int type) {

        super(context, parameters);

        setParameters(context, date);
        setDialogViews(type);
        setModeButtons(setModeButtons());
        setDialogListener(new CreateDialog.DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() {

                day = prevDay;
                month = prevMonth;
                year = prevYear;

                Dp_Picker.updateDate(year, month - 1, day);

                setModeButtons(setModeButtons());
            }

            @Override
            public void PositiveClick() {

                setPickerDate();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public DialogPickerDate(Context context, int parameters, long date, @Definitions.TypeCalendar int type) {

        super(context, parameters);

        setParameters(context, date);
        setDialogViews(type);
        setModeButtons(setModeButtons());
        setDialogListener(new CreateDialog.DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() {

                day = prevDay;
                month = prevMonth;
                year = prevYear;

                Dp_Picker.updateDate(year, month - 1, day);

                setModeButtons(setModeButtons());
            }

            @Override
            public void PositiveClick() {

                setPickerDate();
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public DialogPickerDate(Context context, int parameters, Calendar date, @Definitions.TypeCalendar int type) {

        super(context, parameters);

        setParameters(context, date);
        setDialogViews(type);
        setModeButtons(setModeButtons());
        setDialogListener(new CreateDialog.DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() {

                day = prevDay;
                month = prevMonth;
                year = prevYear;

                Dp_Picker.updateDate(year, month - 1, day);

                setModeButtons(setModeButtons());
            }

            @Override
            public void PositiveClick() {

                setPickerDate();
            }
        });
    }

    private void setParameters(@NonNull Context context, @NonNull String date) {

        this.context = context;

        Calendar calendar = Calendar.getInstance(Locale.getDefault());

        if (!date.equals("")) {

            calendar = new DateTools().readStringDate(date);
        }

        prevDay = day = calendar.get(Calendar.DAY_OF_MONTH);
        prevMonth = month = calendar.get(Calendar.MONTH)+1;
        prevYear = year = calendar.get(Calendar.YEAR);
    }

    private void setParameters(@NonNull Context context, long date) {

        this.context = context;

        Calendar calendar = Calendar.getInstance(Locale.getDefault());

        if (date != 0) {

            calendar.setTimeInMillis(date);
        }

        prevDay = day = calendar.get(Calendar.DAY_OF_MONTH);
        prevMonth = month = calendar.get(Calendar.MONTH)+1;
        prevYear = year = calendar.get(Calendar.YEAR);
    }

    private void setParameters(@NonNull Context context, Calendar date) {

        this.context = context;

        Calendar calendar = Calendar.getInstance(Locale.getDefault());

        if (date != null) {

            calendar = date;
        }

        prevDay = day = calendar.get(Calendar.DAY_OF_MONTH);
        prevMonth = month = calendar.get(Calendar.MONTH)+1;
        prevYear = year = calendar.get(Calendar.YEAR);
    }

    private void setDialogViews(@Definitions.TypeCalendar int Type) {

        int style, attr;

        if (Type == Definitions.DATEPICKER_SPINNER) {

            style = R.style.DialogPickerDateSpinner;
            attr = R.attr.dialogPickerDateSpinner;

        } else {

            style = R.style.DialogPickerDateCalendar;
            attr = R.attr.dialogPickerDateCalendar;
        }

        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        Dp_Picker = new DatePicker(wrapper, null, attr, style);
        Dp_Picker.updateDate(year, month - 1, day);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            Dp_Picker.setOnDateChangedListener(onDateChangedListener);
        }

        Paris.style(Dp_Picker).apply(style);

        addCustomView(Dp_Picker);
    }


    /*----- Methods -----*/

    private void setPickerDate() {

        String day = new DateTools().completeDigitsDate(Dp_Picker.getDayOfMonth());
        String month = new DateTools().completeDigitsDate(Dp_Picker.getMonth() + 1);
        String year = new DateTools().convertTwoDigitsYear(Dp_Picker.getYear());
        String date = day + "-" + month + "-" + year;

        Calendar calendar = new DateTools().readStringDate(date);
        long milis = calendar.getTimeInMillis();

        SetDate(calendar, milis, date, day, month, year);
    }



    /*----- Listeners -----*/

    private final DatePicker.OnDateChangedListener onDateChangedListener = new DatePicker.OnDateChangedListener() {

        @Override
        public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {

            day = i2;
            month = i1 + 1;
            year = i;

            setModeButtons(setModeButtons());
        }

        private void DummyVoid() {
        }
    };


    /*----- Utilities -----*/

    private int setModeButtons() {

        if (day == prevDay && month == prevMonth && year == prevYear) {

            return BUTTONS_CANCEL;

        } else {

            return BUTTONS_REFRESH_ACCEPT;
        }
    }


    /*----------- Return - Section ----------*/

    protected abstract void SetDate(Calendar calendar, long milis, String date, String day, String month, String year);
}
