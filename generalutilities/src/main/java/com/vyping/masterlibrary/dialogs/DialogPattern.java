package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_REFRESH_ACCEPT;

import android.annotation.SuppressLint;
import android.content.Context;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.vyping.masterlibrary.R;

import java.util.List;

public abstract class DialogPattern extends CreateDialog {

    private Context context;

    private static PatternLockView Plv_Pattern;

    private static String pattern;


    /*----------- SetUp - Section ----------*/

    @SuppressLint("SetTextI18n")
    public DialogPattern(@NonNull Context context, int parameters) {

        super(context, parameters);

        setParameters(context);
        setDialogViews();
        setModeButtons(setModeButtons());
        setDialogListener(new DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() {

                Plv_Pattern.clearPattern();

                setModeButtons(setModeButtons());
            }

            @Override
            public void PositiveClick() {

                patternResult(pattern);
            }
        });
    }

    private void setParameters(@NonNull Context Context) {

        context = Context;
    }

    private void setDialogViews() {

        int style = R.style.DialogPattern;
        int attr = R.attr.dialogPattern;

        Plv_Pattern = new PatternLockView(context);
        Plv_Pattern.addPatternLockListener(patternLockViewListener);
        Paris.style(Plv_Pattern).apply(style);

        addCustomView(Plv_Pattern);
    }

    /*----- Listeners -----*/

    private final PatternLockViewListener patternLockViewListener = new PatternLockViewListener() {

        @Override
        public void onStarted() { }

        @Override
        public void onProgress(List<PatternLockView.Dot> ProgressPattern) { }

        @Override
        public void onComplete(List<PatternLockView.Dot> Pattern) {

            pattern = PatternLockUtils.patternToString(Plv_Pattern, Pattern);

            setModeButtons(setModeButtons());
        }

        @Override
        public void onCleared() {

            setModeButtons(setModeButtons());
        }
    };


    /*----- Utilities -----*/

    private int setModeButtons() {

        List<PatternLockView.Dot> pattern = Plv_Pattern.getPattern();
        String Pattern = PatternLockUtils.patternToString(Plv_Pattern, pattern);

        if (Pattern.equals("")) {

            return BUTTONS_CANCEL;

        } else {

            return BUTTONS_REFRESH_ACCEPT;
        }
    }


    /*----- Return -----*/

    protected abstract void patternResult(String Pattern);
}
