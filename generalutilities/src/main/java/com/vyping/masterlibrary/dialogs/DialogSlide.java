package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.R;

import cheekiat.slideview.OnChangeListener;
import cheekiat.slideview.SlideView;

public abstract class DialogSlide extends CreateDialog {

    private Context context;

    private SlideView Sv_Slide;


    /*----- SetUp -----*/

    @SuppressLint("SetTextI18n")
    public DialogSlide(Context context, int parameters) {

        super(context, parameters);

        setParameters(context);
        setDialogViews();
        setModeButtons(BUTTONS_CANCEL);
        setDialogListener(new DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() { }

            @Override
            public void PositiveClick() {

                PositiveButton();
            }
        });
    }

    private void setParameters(@NonNull Context context) {

        this.context = context;
    }

    private void setDialogViews() {

        int style = R.style.DialogSlide;
        int attr = R.attr.dialogSlide;

        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);
        Sv_Slide = new SlideView(wrapper, null);
        Sv_Slide.setOnChangeListener(onChangeListener);
        //Paris.style(Sv_Slide).apply(style);
        addCustomView(Sv_Slide);
    }


    /*----- Methods -----*/

    public void setText(String text) {

        showInstructions(text);
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
            dismissDialog();
        }
    };


    /*----- Return -----*/

    protected abstract void SlideButton();
    protected abstract void PositiveButton();
}
