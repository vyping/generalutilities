package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL;

import android.app.Dialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Common.Definitions;
import com.vyping.masterlibrary.R;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).

public abstract class DialogShowInfo extends CreateDialog {

    private Context context;

    private TextView Tv_Text;

    private String text;


    /*----------- SetUp - Section ----------*/

    public DialogShowInfo(@NonNull Context context, int parameters, String text) {

        super(context, parameters);

        setParameters(context, text);
        setDialogViews();
        setModeButtons(BUTTONS_CANCEL);
        setDialogListener(new CreateDialog.DialogInterface() {

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

    private void setParameters(@NonNull Context context, String text) {

        this.context = context;
        this.text = text;
    }

    private void setDialogViews() {

        int style = R.style.DialogShowInfo;
        int attr = R.attr.dialogShowInfo;

        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);
        Tv_Text = new TextView(wrapper, null, attr, style);
        Tv_Text.setText(text);
        Paris.style(Tv_Text).apply(style);

        addCustomView(Tv_Text);
    }

    private void setOptionButtons() {


    }


    /*----- Return -----*/

    protected abstract void PositiveButton();
}
