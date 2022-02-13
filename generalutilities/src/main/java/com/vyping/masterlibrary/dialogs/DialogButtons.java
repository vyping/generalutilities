package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL_ACCEPT;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.R;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).

public abstract class DialogButtons extends CreateDialog {

    private Context context;


    /*----------- SetUp - Section ----------*/

    public DialogButtons(@NonNull Context context, int parameters) {

        super(context, parameters);

        setParameters(context);
        setDialogViews();
        setModeButtons(BUTTONS_CANCEL_ACCEPT);
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

        setContainerOrientation(LinearLayout.HORIZONTAL);
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

        addCustomView(Btn_Buton);

        return Btn_Buton;
    }


    /*----- Return -----*/

    protected abstract void PositiveButton();
}
