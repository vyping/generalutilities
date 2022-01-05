package com.vyping.masterlibrary.Dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_SINGLE;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_THREE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.vyping.masterlibrary.R;

import java.util.List;

public abstract class DialogPattern {

    private Context context;
    private final CreateDialog createDialog;

    private static PatternLockView Plv_Pattern;

    private static String pattern;


    /*----------- SetUp - Section ----------*/

    @SuppressLint("SetTextI18n")
    public DialogPattern(@NonNull Context context, int parameters) {

        setParameters(context);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(Dialog dialog) {

                setViewsOnLayout(dialog);

                Plv_Pattern.addPatternLockListener(patternLockViewListener);

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

                Plv_Pattern.clearPattern();

                setOptionButtons();
            }

            @Override
            protected void PositiveClick() {

                patternResult(pattern);
            }
        };
    }

    private void setOptionButtons() {

        List<PatternLockView.Dot> pattern = Plv_Pattern.getPattern();
        String Pattern = PatternLockUtils.patternToString(Plv_Pattern, pattern);

        if (Pattern.equals("")) {

            createDialog.setModeButtons(BUTTONS_SINGLE);

        } else {

            createDialog.setModeButtons(BUTTONS_THREE);
        }
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

            setOptionButtons();
        }

        @Override
        public void onCleared() {

            setOptionButtons();
        }
    };


    /*----- Tools -----*/

    private void setParameters(@NonNull Context Context) {

        context = Context;
    }

    private void setViewsOnLayout(@NonNull Dialog dialog) {

        int style = R.style.DialogPattern;
        int attr = R.attr.dialogPattern;

        LinearLayout layout = dialog.findViewById(R.id.Ll_Custom_MainContainer);

        Plv_Pattern = new PatternLockView(context);
        Paris.style(Plv_Pattern).apply(style);

        layout.addView(Plv_Pattern);
    }


    /*----- Return -----*/

    protected abstract void patternResult(String Pattern);
}
