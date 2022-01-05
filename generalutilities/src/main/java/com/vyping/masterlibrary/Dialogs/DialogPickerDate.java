package com.vyping.masterlibrary.Dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_SINGLE;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_THREE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.view.ContextThemeWrapper;
import android.widget.DatePicker;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Common.DateTools;
import com.vyping.masterlibrary.Common.Definitions;
import com.vyping.masterlibrary.R;

import java.util.Calendar;
import java.util.Locale;

public abstract class DialogPickerDate {

    private Context context;
    private final CreateDialog createDialog;

    private DatePicker Dp_Picker;

    private int prevDay, prevMonth, prevYear, day, month, year;


    /*----------- SetUp - Section ----------*/

    @SuppressLint("SetTextI18n")
    public DialogPickerDate(Context context, int parameters, @Definitions.TypeCalendar int type) {

        setParameters(context,"");

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(@NonNull Dialog dialog) {

                setViewsOnLayout(dialog, type);

                Dp_Picker.updateDate(year, month-1, day);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    Dp_Picker.setOnDateChangedListener(onDateChangedListener);
                }

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

                day = prevDay;
                month = prevMonth;
                year = prevYear;

                Dp_Picker.updateDate(year, month-1, day);

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
    public DialogPickerDate(Context context, int parameters, String date, @Definitions.TypeCalendar int type) {

        setParameters(context, date);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(@NonNull Dialog dialog) {

                setViewsOnLayout(dialog, type);

                Dp_Picker.updateDate(year, month-1, day);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    Dp_Picker.setOnDateChangedListener(onDateChangedListener);
                }

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

                day = prevDay;
                month = prevMonth;
                year = prevYear;

                Dp_Picker.updateDate(year, month-1, day);

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
    public DialogPickerDate(Context context, int parameters, long date, @Definitions.TypeCalendar int type) {

        setParameters(context, date);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(@NonNull Dialog dialog) {

                setViewsOnLayout(dialog, type);

                Dp_Picker.updateDate(year, month-1, day);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    Dp_Picker.setOnDateChangedListener(onDateChangedListener);
                }

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

                day = prevDay;
                month = prevMonth;
                year = prevYear;

                Dp_Picker.updateDate(year, month-1, day);

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
    public DialogPickerDate(Context context, int parameters, Calendar date, @Definitions.TypeCalendar int type) {

        setParameters(context, date);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(@NonNull Dialog dialog) {

                setViewsOnLayout(dialog, type);

                Dp_Picker.updateDate(year, month-1, day);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    Dp_Picker.setOnDateChangedListener(onDateChangedListener);
                }

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

                day = prevDay;
                month = prevMonth;
                year = prevYear;

                Dp_Picker.updateDate(year, month-1, day);

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
    public DialogPickerDate(Context context, int parameters, String minDate, String maxDate, String date, @Definitions.TypeCalendar int type) {

        setParameters(context, date);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(@NonNull Dialog dialog) {

                setViewsOnLayout(dialog, type);

                Dp_Picker.updateDate(year, month-1, day);

                if (!minDate.equals("")) {

                    Calendar minCalendar = new DateTools().readStringDate(minDate);
                    Dp_Picker.setMinDate(minCalendar.getTimeInMillis());
                }

                if (!maxDate.equals("")) {

                    Calendar maxCalendar = new DateTools().readStringDate(maxDate);
                    Dp_Picker.setMaxDate(maxCalendar.getTimeInMillis());
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    Dp_Picker.setOnDateChangedListener(onDateChangedListener);
                }

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

                day = prevDay;
                month = prevMonth;
                year = prevYear;

                Dp_Picker.updateDate(year, month-1, day);

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
    public DialogPickerDate(Context context, int parameters, long minDate, long maxDate, long date, @Definitions.TypeCalendar int type) {

        setParameters(context, date);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(@NonNull Dialog dialog) {

                setViewsOnLayout(dialog, type);

                Dp_Picker.updateDate(year, month-1, day);

                if (minDate != 0) {

                    Dp_Picker.setMinDate(minDate);
                }

                if (maxDate != 0) {

                    Dp_Picker.setMaxDate(maxDate);
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    Dp_Picker.setOnDateChangedListener(onDateChangedListener);
                }

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

                day = prevDay;
                month = prevMonth;
                year = prevYear;

                Dp_Picker.updateDate(year, month-1, day);

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
    public DialogPickerDate(Context context, int parameters, Calendar minDate, Calendar maxDate, Calendar date, @Definitions.TypeCalendar int type) {

        setParameters(context, date);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(@NonNull Dialog dialog) {

                setViewsOnLayout(dialog, type);

                Dp_Picker.updateDate(year, month-1, day);

                if (minDate != null) {

                    Dp_Picker.setMinDate(date.getTimeInMillis());
                }

                if (maxDate != null) {

                    Dp_Picker.setMaxDate(date.getTimeInMillis());
                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                    Dp_Picker.setOnDateChangedListener(onDateChangedListener);
                }

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

                day = prevDay;
                month = prevMonth;
                year = prevYear;

                Dp_Picker.updateDate(year, month-1, day);

                setOptionButtons();
            }

            @Override
            protected void PositiveClick() {

                setPickerDate();
            }
        };

        setOptionButtons();
    }

    private void setOptionButtons() {

        if (day == prevDay && month == prevMonth && year == prevYear) {

            createDialog.setModeButtons(BUTTONS_SINGLE);

        } else {

            createDialog.setModeButtons(BUTTONS_THREE);
        }
    }


    /*----- Listeners -----*/

    private final DatePicker.OnDateChangedListener onDateChangedListener = new DatePicker.OnDateChangedListener() {

        @Override
        public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {

            day = i2;
            month = i1+1;
            year = i;

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

    private void setPickerDate() {

        String day = new DateTools().completeDigitsDate(Dp_Picker.getDayOfMonth());
        String month = new DateTools().completeDigitsDate(Dp_Picker.getMonth() + 1);
        String year = new DateTools().convertTwoDigitsYear(Dp_Picker.getYear());
        String date = day + "-" + month + "-" + year;

        Calendar calendar = new DateTools().readStringDate(date);
        long milis = calendar.getTimeInMillis();

        SetDate(calendar, milis, date, day, month, year);
    }

    private void setViewsOnLayout(@NonNull Dialog dialog, @Definitions.TypeCalendar int Type) {

        int style, attr;

        if (Type == Definitions.DATEPICKER_SPINNER) {

            style = R.style.DialogPickerDateSpinner;
            attr = R.attr.dialogPickerDateSpinner;

        } else {

            style = R.style.DialogPickerDateCalendar;
            attr = R.attr.dialogPickerDateCalendar;
        }

        LinearLayout layout = dialog.findViewById(R.id.Ll_Custom_MainContainer);
        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        Dp_Picker = new DatePicker(wrapper, null, attr, style);

        Paris.style(Dp_Picker).apply(style);

        layout.addView(Dp_Picker);
    }


    /*----------- Return - Section ----------*/

    protected abstract void SetDate(Calendar calendar, long milis, String date, String day, String month, String year);
}
