package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL_ACCEPT;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.R;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).

public abstract class DialogQuestion extends CreateDialog {

    private Context context;

    private TextView Tv_Text;

    private String prevText, text;


    /*----------- SetUp - Section ----------*/

    public DialogQuestion(@NonNull Context context, int parameters, String text) {

        super(context, parameters);

        setParameters(context, text);
        setDialogViews();
        setModeButtons(BUTTONS_CANCEL_ACCEPT);
        setDialogListener(new CreateDialog.DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() { }

            @Override
            public void PositiveClick() {

                ButtonConfirm(text);
            }
        });
    }

    private void setParameters(@NonNull Context context, String text) {

        this.context = context;
        this.text = text;

        prevText = text;
    }

    private void setDialogViews() {

        int style = R.style.DialogShowInfo;
        int attr = R.attr.dialogShowInfo;

        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);
        Tv_Text = new TextView(wrapper, null, attr, style);
        Paris.style(Tv_Text).apply(style);
        Tv_Text.setText(text);
        addCustomView(Tv_Text);
    }


    /*----- Return -----*/

    protected abstract void ButtonConfirm(String InputText);
}
