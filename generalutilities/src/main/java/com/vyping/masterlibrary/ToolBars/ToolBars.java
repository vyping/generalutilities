package com.vyping.masterlibrary.ToolBars;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.vyping.masterlibrary.Common.Definitions.DATEPICKER_CALENDAR;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.vyping.masterlibrary.Common.DateTools;
import com.vyping.masterlibrary.Common.GeneralTools;
import com.vyping.masterlibrary.Dialogs.DialogPickerDate;
import com.vyping.masterlibrary.R;

import java.util.Calendar;
import java.util.Locale;
import java.util.Objects;

public abstract class ToolBars {

    private final Context context;
    private Calendar calendar;
    private DateInterface dateInterface;
    private SearchInterface searchInterface;

    private final View inflated;
    private LinearLayout Ll_Date, Ll_Search;
    private Button Btn_BckDate, Btn_SetDate, Btn_FwdDate, Btn_Cancel;
    private EditText Et_Search;


    /*
     *-------- SetUp Section --------
     */

    /**
     * Despliega un cuadro de dialogo con un calendario para seleccionar una fecha.
     * @param context : Desde el cual se desplego el dialogo.
     */
    @SuppressLint("SetTextI18n")
    public ToolBars(@NonNull final Context context, final int container) {

        this.context = context;

        ConstraintLayout Container = ((Activity) context).findViewById(container);
        RelativeLayout createLayout = new RelativeLayout(new ContextThemeWrapper(context, R.style.ToolBars), null, R.style.ToolBars);
        Container.addView(createLayout);

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflated = Objects.requireNonNull(inflater).inflate(R.layout.toolbars_datesearch, createLayout, true);
    }

    public void setDateToolBar(DateInterface DateInterface) {

        dateInterface = DateInterface;
        calendar = Calendar.getInstance(Locale.getDefault());
        String label = new DateTools().selectedDateToView(calendar);

        Ll_Date = inflated.findViewById(R.id.Llh_DyS_setDate);
        Btn_BckDate = inflated.findViewById(R.id.Btn_DyS_bckDate);
        Btn_SetDate = inflated.findViewById(R.id.Btn_DyS_calDate);
        Btn_FwdDate = inflated.findViewById(R.id.Btn_DyS_fwdDate);

        Ll_Date.setVisibility(VISIBLE);
        Btn_SetDate.setText(label);

        Btn_BckDate.setOnClickListener(v -> {

            calendar.add(Calendar.DAY_OF_YEAR, -1);

            setDate();
        });
        Btn_SetDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                dialogDatePicker(R.array.Dialogs_PickerDate);
            }

            private void DummyVoid() {}
        });
        Btn_FwdDate.setOnClickListener(v -> {

            calendar.add(Calendar.DAY_OF_YEAR, 1);

            setDate();
        });
    }

    public void setSearchToolBar(SearchInterface SearchInterface) {

        searchInterface = SearchInterface;

        Ll_Search = inflated.findViewById(R.id.Ll_DyS_Search);
        Et_Search = inflated.findViewById(R.id.Et_DyS_Search);
        Btn_Cancel = inflated.findViewById(R.id.Btn_DyS_Cancel);

        Ll_Search.setVisibility(VISIBLE);
        Et_Search.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
        Btn_Cancel.setVisibility(GONE);

        Et_Search.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence sequence, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence sequence, int start, int before, int count) {

                String Search = String.valueOf(Et_Search.getText());

                searchInterface.SelectedSearch(Search);

                if (Search.equals("") && Btn_Cancel.getVisibility() == VISIBLE) {

                    Btn_Cancel.setVisibility(GONE);

                } else if (!Search.equals("") && Btn_Cancel.getVisibility() == GONE) {

                    Btn_Cancel.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable sequence) { }
        });
        Btn_Cancel.setOnClickListener(v -> {

            if (v == Btn_Cancel) {

                Et_Search.setText("");
                Btn_Cancel.setVisibility(GONE);

                new GeneralTools().hideSoftInput(context, Et_Search);
            }
        });
    }


    /**
     * -------- Process - Section
     */

    public void selectBar() {

        if (Ll_Date.getVisibility() == VISIBLE) {

            Ll_Date.setElevation(0f);
            TranslateAnimation animateOut = new TranslateAnimation(0, 0, 0, -Ll_Date.getHeight() - 24);
            animateOut.setDuration(400);
            animateOut.setFillAfter(true);
            Ll_Date.startAnimation(animateOut);
            Ll_Date.setVisibility(GONE);
            Btn_BckDate.setEnabled(TRUE);
            Btn_SetDate.setEnabled(TRUE);
            Btn_FwdDate.setEnabled(TRUE);

            Ll_Search.setElevation(6f);
            Ll_Search.setVisibility(VISIBLE);
            TranslateAnimation animateIn = new TranslateAnimation(0, 0, -Ll_Search.getHeight(), 0);
            animateIn.setDuration(400);
            animateIn.setFillAfter(TRUE);
            Ll_Search.startAnimation(animateIn);
            Btn_BckDate.setEnabled(FALSE);
            Btn_SetDate.setEnabled(FALSE);
            Btn_FwdDate.setEnabled(FALSE);

        } else {

            Ll_Search.setElevation(0f);
            TranslateAnimation animateOut = new TranslateAnimation(0, 0, 0, -Ll_Search.getHeight() - 24);
            animateOut.setDuration(400);
            animateOut.setFillAfter(true);
            Ll_Search.startAnimation(animateOut);
            Ll_Search.setVisibility(GONE);
            Btn_BckDate.setEnabled(FALSE);
            Btn_SetDate.setEnabled(FALSE);
            Btn_FwdDate.setEnabled(FALSE);

            Ll_Date.setElevation(6f);
            Ll_Date.setVisibility(VISIBLE);
            TranslateAnimation animateIn = new TranslateAnimation(0, 0, -Ll_Date.getHeight(), 0);
            animateIn.setDuration(400);
            animateIn.setFillAfter(TRUE);
            Ll_Date.startAnimation(animateIn);
            Btn_BckDate.setEnabled(TRUE);
            Btn_SetDate.setEnabled(TRUE);
            Btn_FwdDate.setEnabled(TRUE);
        }
    }

    public void setDate() {

        long milis = calendar.getTimeInMillis();
        String label = new DateTools().selectedDateToView(calendar);
        String date = new DateTools().selectedDateToTag(calendar);
        String day = new DateTools().completeDigitsDate(calendar.get(Calendar.DAY_OF_MONTH));
        String month = new DateTools().completeDigitsDate(calendar.get(Calendar.MONTH) + 1);
        String year = new DateTools().convertTwoDigitsYear(calendar.get(Calendar.YEAR));

        Btn_SetDate.setText(label);
        dateInterface.SelectedDate(calendar, milis, date, day, month, year);
    }

    public void putSearch(String search) {

        Et_Search.setText(search);
    }


    /**
     * -------- Dialogs - Section
     */

    @SuppressLint("SetTextI18n")
    public void dialogDatePicker(int parameters) {


        new DialogPickerDate(context, parameters, calendar, DATEPICKER_CALENDAR) {

            @Override
            protected void SetDate(Calendar Calendar, long milis, String date, String day, String month, String year) {

                calendar = Calendar;

                String label = new DateTools().selectedDateToView(calendar);

                Btn_SetDate.setText(label);
                dateInterface.SelectedDate(Calendar, milis, date,  day, month, year);
            }
        };
    }


    //----- Interface - Section-----//

    public interface DateInterface {

        void SelectedDate(Calendar Calendar, long milis, String date, String day, String month, String year);
        void DummyVoid();
    }

    public interface SearchInterface {

        void SelectedSearch(String Search);
        void DummyVoid();
    }

}

