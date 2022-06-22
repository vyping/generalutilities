package com.vyping.masterlibrary.dialogs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.ContextThemeWrapper;
import android.widget.DatePicker;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.time.MyTime;
import com.vyping.masterlibrary.time.MyTimeTools;
import com.vyping.masterlibrary.Common.Definitions;
import com.vyping.masterlibrary.R;

import java.util.Calendar;
import java.util.Locale;

public abstract class DialogPickerDate extends CreateDialog {

    private DatePicker Dp_Picker;

    private int prevDay, prevMonth, prevYear, day, month, year;


    /*----------- SetUp - Section ----------*/

    public DialogPickerDate(@NonNull Context context, int arrayParameters, @Definitions.TypeCalendar int type) {

        super(context, arrayParameters);

        setParameters("");
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, int arrayParameters, @Definitions.TypeCalendar int type, String date) {

        super(context, arrayParameters);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, int arrayParameters, @Definitions.TypeCalendar int type, long date) {

        super(context, arrayParameters);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, int arrayParameters, @Definitions.TypeCalendar int type, Calendar date) {

        super(context, arrayParameters);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, int arrayParameters, @Definitions.TypeCalendar int type) {

        super(context, dialogMode, arrayParameters);

        setParameters("");
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, int arrayParameters, @Definitions.TypeCalendar int type, String date) {

        super(context, dialogMode, arrayParameters);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, int arrayParameters, @Definitions.TypeCalendar int type, long date) {

        super(context, dialogMode, arrayParameters);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, int arrayParameters, @Definitions.TypeCalendar int type, Calendar date) {

        super(context, dialogMode, arrayParameters);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, Drawable icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type) {

        super(context, icon, title, description, help, error, success);

        setParameters("");
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, Drawable icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, String date) {

        super(context, icon, title, description, help, error, success);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, Drawable icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, long date) {

        super(context, icon, title, description, help, error, success);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, Drawable icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, Calendar date) {

        super(context, icon, title, description, help, error, success);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, Drawable icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters("");
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, Drawable icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, String date) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, Drawable icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, long date) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, Drawable icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, Calendar date) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, int icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type) {

        super(context, icon, title, description, help, error, success);

        setParameters("");
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, int icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, String date) {

        super(context, icon, title, description, help, error, success);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, int icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, long date) {

        super(context, icon, title, description, help, error, success);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, int icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, Calendar date) {

        super(context, icon, title, description, help, error, success);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, int icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters("");
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, int icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, String date) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, int icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, long date) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, int icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, Calendar date) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters(date);
        setDialogViews(type);
        setModeDialogButtons();
    }

    private void setParameters(@NonNull String date) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());

        if (!date.equals("")) {

            calendar = new MyTime().getCalendar(date);
        }

        prevDay = day = calendar.get(Calendar.DAY_OF_MONTH);
        prevMonth = month = calendar.get(Calendar.MONTH) + 1;
        prevYear = year = calendar.get(Calendar.YEAR);
    }

    private void setParameters(long date) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());

        if (date != 0) {

            calendar.setTimeInMillis(date);
        }

        prevDay = day = calendar.get(Calendar.DAY_OF_MONTH);
        prevMonth = month = calendar.get(Calendar.MONTH) + 1;
        prevYear = year = calendar.get(Calendar.YEAR);
    }

    private void setParameters(Calendar date) {

        Calendar calendar = Calendar.getInstance(Locale.getDefault());

        if (date != null) {

            calendar = date;
        }

        prevDay = day = calendar.get(Calendar.DAY_OF_MONTH);
        prevMonth = month = calendar.get(Calendar.MONTH) + 1;
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

        ContextThemeWrapper wrapper = new ContextThemeWrapper(CONTEXT, style);

        Dp_Picker = new DatePicker(wrapper, null, attr, style);
        Dp_Picker.updateDate(year, month - 1, day);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            Dp_Picker.setOnDateChangedListener(onDateChangedListener);
        }

        Paris.style(Dp_Picker).apply(style);

        AddCustomView(Dp_Picker);
    }


    /*----- ModelMethods -----*/

    private void setPickerDate() {

        String day = new MyTime().completeDigitsDate(Dp_Picker.getDayOfMonth());
        String month = new MyTime().completeDigitsDate(Dp_Picker.getMonth() + 1);
        String year = new MyTime().convertTwoDigitsYear(Dp_Picker.getYear());

        Calendar calendar = new MyTime().getCalendar(day, month, year);
        long milis = calendar.getTimeInMillis();

        SetDate(calendar, milis, day, month, year);
    }



    /*----- Listeners -----*/

    private final DatePicker.OnDateChangedListener onDateChangedListener = new DatePicker.OnDateChangedListener() {

        @Override
        public void onDateChanged(DatePicker datePicker, int i, int i1, int i2) {

            day = i2;
            month = i1 + 1;
            year = i;

            setModeDialogButtons();
        }

        private void DummyVoid() {
        }
    };


    /*----- Utilities -----*/

    private void setModeDialogButtons() {

        if (day == prevDay && month == prevMonth && year == prevYear) {

            if (MODE == DIALOG_NORMAL) {

                SetButtonCancel(BUTTON_RIGHT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        return true;
                    }

                    private void DummyVoid() {
                    }
                });

            } else {

                SetButtonBack(BUTTON_RIGHT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        return true;
                    }

                    private void DummyVoid() {
                    }
                });
            }

        } else {

            if (MODE == DIALOG_NORMAL) {

                SetButtonCancel(BUTTON_LEFT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        return true;
                    }

                    private void DummyVoid() {
                    }
                });
                SetButtonConfirm(BUTTON_RIGHT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        String day = new MyTime().completeDigitsDate(Dp_Picker.getDayOfMonth());
                        String month = new MyTime().completeDigitsDate(Dp_Picker.getMonth() + 1);
                        String year = new MyTime().convertTwoDigitsYear(Dp_Picker.getYear());

                        Calendar calendar = new MyTime().getCalendar(day, month, year);
                        long milis = calendar.getTimeInMillis();

                        return SetDate(calendar, milis, day, month, year);
                    }

                    private void DummyVoid() {
                    }
                });

            } else if (MODE == DIALOG_STEP_INITIAL) {

                SetButtonBack(BUTTON_LEFT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        return true;
                    }

                    private void DummyVoid() {
                    }
                });
                SetButtonNext(BUTTON_RIGHT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        String day = new MyTime().completeDigitsDate(Dp_Picker.getDayOfMonth());
                        String month = new MyTime().completeDigitsDate(Dp_Picker.getMonth() + 1);
                        String year = new MyTime().convertTwoDigitsYear(Dp_Picker.getYear());

                        Calendar calendar = new MyTime().getCalendar(day, month, year);
                        long milis = calendar.getTimeInMillis();

                        return SetDate(calendar, milis, day, month, year);
                    }

                    private void DummyVoid() {
                    }
                });

            } else if (MODE == DIALOG_STEP_INTERMEDIATE) {

                SetButtonBack(BUTTON_LEFT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        return true;
                    }

                    private void DummyVoid() {
                    }
                });
                SetButtonNext(BUTTON_RIGHT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        String day = new MyTime().completeDigitsDate(Dp_Picker.getDayOfMonth());
                        String month = new MyTime().completeDigitsDate(Dp_Picker.getMonth() + 1);
                        String year = new MyTime().convertTwoDigitsYear(Dp_Picker.getYear());

                        Calendar calendar = new MyTime().getCalendar(day, month, year);
                        long milis = calendar.getTimeInMillis();

                        return SetDate(calendar, milis, day, month, year);
                    }

                    private void DummyVoid() {
                    }
                });

            } else if (MODE == DIALOG_STEP_FINAL) {

                SetButtonBack(BUTTON_LEFT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        return true;
                    }

                    private void DummyVoid() {
                    }
                });
                SetButtonConfirm(BUTTON_RIGHT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        String day = new MyTime().completeDigitsDate(Dp_Picker.getDayOfMonth());
                        String month = new MyTime().completeDigitsDate(Dp_Picker.getMonth() + 1);
                        String year = new MyTime().convertTwoDigitsYear(Dp_Picker.getYear());

                        Calendar calendar = new MyTime().getCalendar(day, month, year);
                        long milis = calendar.getTimeInMillis();

                        return SetDate(calendar, milis, day, month, year);
                    }

                    private void DummyVoid() {
                    }
                });
            }
        }
    }


    /*----------- Return - Section ----------*/

    protected abstract boolean SetDate(Calendar calendar, long milis, String day, String month, String year);
}
