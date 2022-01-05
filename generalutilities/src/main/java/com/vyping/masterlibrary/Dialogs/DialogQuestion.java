package com.vyping.masterlibrary.Dialogs;

import static java.lang.Boolean.TRUE;

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

public abstract class DialogQuestion {

    private Context context;
    private final CreateDialog createDialog;

    private TextView Tv_Text;

    private String prevText, text;


    /*----------- SetUp - Section ----------*/

    public DialogQuestion(@NonNull Context context, int parameters, String text) {

        setParameters(context, text);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(Dialog dialog) {

                setViewsOnLayout(dialog);

                Tv_Text.setText(text);

                dialog.show();
            }

            @Override
            protected void RefreshClick() { }

            @Override
            protected void PositiveClick() {

                ButtonConfirm(text);
            }
        };

        setOptionButtons();
    }

    private void setOptionButtons() {

        createDialog.setModeButtons(Definitions.BUTTONS_BOTH);
    }


    /*----- Tools -----*/

    private void setParameters(@NonNull Context context, String text) {

        this.context = context;
        this.text = text;

        prevText = text;
    }

    private void setViewsOnLayout(@NonNull Dialog dialog) {

        int style = R.style.DialogShowInfo;
        int attr = R.attr.dialogShowInfo;

        LinearLayout layout = dialog.findViewById(R.id.Ll_Custom_MainContainer);
        layout.setOrientation(LinearLayout.VERTICAL);
        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        Tv_Text = new TextView(wrapper, null, attr, style);

        Paris.style(Tv_Text).apply(style);

        layout.addView(Tv_Text);
    }


    /*----- Return -----*/

    protected abstract void ButtonConfirm(String InputText);
}
