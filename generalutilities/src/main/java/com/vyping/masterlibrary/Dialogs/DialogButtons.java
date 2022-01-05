package com.vyping.masterlibrary.Dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_BOTH;
import static java.lang.Boolean.TRUE;

import android.app.Dialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.R;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).

public abstract class DialogButtons {

    private Context context;
    private final CreateDialog createDialog;

    private LinearLayout layout;


    /*----------- SetUp - Section ----------*/

    public DialogButtons(@NonNull Context context, int parameters) {

        setParameters(context);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(Dialog dialog) {

                setViewsOnLayout(dialog);

                dialog.show();
            }

            @Override
            protected void RefreshClick() { }

            @Override
            protected void PositiveClick() {

                ButtonConfirm();
            }
        };

        setOptionButtons();
    }

    private void setOptionButtons() {

       createDialog.setModeButtons(BUTTONS_BOTH);
    }

    /*----- Process -----*/

    @NonNull
    public Button setButton(String icon, View.OnClickListener listener) {

        int style = R.style.DialogButtons;
        int attr = R.attr.dialogButtons;

        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        Button Btn_Buton = new Button(wrapper, null, attr, style);
        Btn_Buton.setText(icon);
        Btn_Buton.setOnClickListener(listener);

        Paris.style(Btn_Buton).apply(style);

        layout.addView(Btn_Buton);

        return Btn_Buton;
    }


    /*----- Tools -----*/

    private void setParameters(@NonNull Context context) {

        this.context = context;
    }

    private void setViewsOnLayout(@NonNull Dialog dialog) {

        layout = dialog.findViewById(R.id.Ll_Custom_MainContainer);
        layout.setOrientation(LinearLayout.HORIZONTAL);
    }


    /*----- Return -----*/

    protected abstract void ButtonConfirm();
}
