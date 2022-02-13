package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL_ACCEPT;

import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.R;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).

public abstract class DialogCheck extends CreateDialog {

    private Context context;

    private CheckBox Cb_Return;

    private String text;
    private boolean checked;


    /*----- SetUp -----*/

    public DialogCheck(@NonNull Context context, int parameters, boolean Checked) {

        super(context, parameters);

        setParameters(context, parameters, Checked);
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

                boolean isChecked = Cb_Return.isChecked();

                ButtonConfirm(isChecked);
            }
        });
    }

    private void setParameters(@NonNull Context context, int parameters, boolean checked) {

        this.context = context;
        this.checked = checked;

        text = listParams[6];
    }

    private void setDialogViews() {

        int style = R.style.DialogCheckBox;
        int attr = R.attr.dialogCheckBox;

        Cb_Return = new CheckBox(context, null, attr, style);
        Cb_Return.setChecked(checked);
        Cb_Return.setText(text);
        Cb_Return.setOnCheckedChangeListener(onCheckedChangeListener);
        //Paris.style(Cb_Return).apply(style);

        addCustomView(Cb_Return);
    }


    /*----- Process -----*/

    public void setText(String text) {

        Cb_Return.setText(text);
    }

    public boolean getState() {

        return Cb_Return.isChecked();
    }

    public void changeState(boolean checked) {

        this.checked = checked;

        Cb_Return.setChecked(checked);
    }


    /*----- Listeners -----*/

    private final CompoundButton.OnCheckedChangeListener onCheckedChangeListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton compoundButton, boolean Checked) {

            checked = Checked;

            ChangeCheckedState(checked);
        }

        private void DummyVoid() {}
    };


    /*----- Return -----*/

    protected abstract void ChangeCheckedState(boolean IsChecked);
    protected abstract void ButtonConfirm(boolean IsChecked);
}
