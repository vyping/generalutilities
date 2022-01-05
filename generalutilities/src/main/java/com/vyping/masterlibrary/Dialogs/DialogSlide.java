package com.vyping.masterlibrary.Dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_SINGLE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.R;

import cheekiat.slideview.OnChangeListener;
import cheekiat.slideview.SlideView;

public abstract class DialogSlide {

    private Context context;
    private final CreateDialog createDialog;

    private SlideView Sv_Slide;


    /*----- SetUp -----*/

    @SuppressLint("SetTextI18n")
    public DialogSlide(Context context, int parameters) {

        setParameters(context);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(@NonNull Dialog dialog) {

                setViewsOnLayout(dialog);

                Sv_Slide.setOnChangeListener(onChangeListener);

                dialog.show();
            }

            @Override
            protected void RefreshClick() { }

            @Override
            protected void PositiveClick() {

                SlideButton();
            }
        };

        setOptionButtons();
    }

    private void setOptionButtons() {

        createDialog.setModeButtons(BUTTONS_SINGLE);
    }


    /*----- Listeners -----*/

    private final OnChangeListener onChangeListener = new OnChangeListener() {

        @Override
        public void onProgressChanged(int progress) { }

        @Override
        public void onStopChanged() { }

        @Override
        public void onComplete() {

            SlideButton();

            createDialog.dismissDialog();
        }
    };


    /*----- Tools -----*/

    private void setParameters(@NonNull Context context) {

        this.context = context;
    }

    private void setViewsOnLayout(@NonNull Dialog dialog) {

        int style = R.style.DialogSlide;
        int attr = R.attr.dialogSlide;

        LinearLayout layout = dialog.findViewById(R.id.Ll_Custom_MainContainer);
        layout.setOrientation(LinearLayout.VERTICAL);
        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        Sv_Slide = new SlideView(wrapper, null);

        //Paris.style(Sv_Slide).apply(style);

        layout.addView(Sv_Slide);
    }


    /*----- Return -----*/

    protected abstract void SlideButton();
}
