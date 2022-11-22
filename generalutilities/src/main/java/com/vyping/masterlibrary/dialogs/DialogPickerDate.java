package com.vyping.masterlibrary.dialogs;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.ContextThemeWrapper;
import android.widget.DatePicker;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.time.MyTime;
import com.vyping.masterlibrary.Common.Definitions;
import com.vyping.masterlibrary.R;

public abstract class DialogPickerDate extends CreateDialog {

    private DatePicker Dp_Picker;

    private int prevDay, prevMonth, prevYear, day, month, year;


    /*----------- SetUp - Section ----------*/

    public DialogPickerDate(@NonNull Context context, int arrayParameters, @Definitions.TypeCalendar int type) {

        super(context, arrayParameters);

        setParameters();
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, int arrayParameters, @Definitions.TypeCalendar int type, MyTime myTime) {

        super(context, arrayParameters);

        setParameters(myTime);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, int arrayParameters, @Definitions.TypeCalendar int type) {

        super(context, dialogMode, arrayParameters);

        setParameters();
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, int arrayParameters, @Definitions.TypeCalendar int type, MyTime myTime) {

        super(context, dialogMode, arrayParameters);

        setParameters(myTime);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, Drawable icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type) {

        super(context, icon, title, description, help, error, success);

        setParameters();
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, Drawable icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, MyTime myTime) {

        super(context, icon, title, description, help, error, success);

        setParameters(myTime);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, Drawable icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters();
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, Drawable icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, MyTime myTime) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters(myTime);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, int icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type) {

        super(context, icon, title, description, help, error, success);

        setParameters();
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, int icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, MyTime myTime) {

        super(context, icon, title, description, help, error, success);

        setParameters(myTime);
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, int icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters();
        setDialogViews(type);
        setModeDialogButtons();
    }

    public DialogPickerDate(@NonNull Context context, @DialogMode int dialogMode, int icon, String title, String description, String help, String error, String success, @Definitions.TypeCalendar int type, MyTime myTime) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters(myTime);
        setDialogViews(type);
        setModeDialogButtons();
    }

    private void setParameters() {

        MyTime myTime = new MyTime();

        prevDay = day = myTime.getDay();
        prevMonth = month = myTime.getMonth();
        prevYear = year = myTime.getYear();
    }

    private void setParameters(@NonNull MyTime myTime) {

        prevDay = day = myTime.getDay();
        prevMonth = month = myTime.getMonth();
        prevYear = year = myTime.getYear();
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

        MyTime myTime = new MyTime(day, month, year);

        SetDate(myTime);
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

    private final Interfase interfase = new Interfase() {

        @Override
        public boolean ClickButton() {

            String day = new MyTime().completeDigitsDate(Dp_Picker.getDayOfMonth());
            String month = new MyTime().completeDigitsDate(Dp_Picker.getMonth() + 1);
            String year = new MyTime().convertTwoDigitsYear(Dp_Picker.getYear());

            MyTime myTime = new MyTime(day, month, year);

            return SetDate(myTime);
        }

        private void DummyVoid() {}
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
                SetButtonConfirm(BUTTON_RIGHT, interfase);

            } else if (MODE == DIALOG_STEP_INITIAL) {

                SetButtonBack(BUTTON_LEFT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        return true;
                    }

                    private void DummyVoid() {
                    }
                });
                SetButtonNext(BUTTON_RIGHT, interfase);

            } else if (MODE == DIALOG_STEP_INTERMEDIATE) {

                SetButtonBack(BUTTON_LEFT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        return true;
                    }

                    private void DummyVoid() {
                    }
                });
                SetButtonNext(BUTTON_RIGHT, interfase);

            } else if (MODE == DIALOG_STEP_FINAL) {

                SetButtonBack(BUTTON_LEFT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        return true;
                    }

                    private void DummyVoid() {
                    }
                });
                SetButtonConfirm(BUTTON_RIGHT, interfase);
            }
        }
    }


    /*----------- Return - Section ----------*/

    protected abstract boolean SetDate(MyTime myTime);
}
