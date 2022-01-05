package com.vyping.masterlibrary.Dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_SINGLE;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_THREE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.app.Dialog;
import android.content.Context;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.R;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).

public abstract class DialogCheck {

    private Context context;
    private final CreateDialog createDialog;

    private CheckBox Cb_Return;

    private String text;
    private boolean prevChecked, checked;


    /*----- SetUp -----*/

    public DialogCheck(@NonNull Context context, int parameters, boolean Checked) {

        setParameters(context, parameters, Checked);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(Dialog dialog) {

                setViewsOnLayout(dialog);

                Cb_Return.setChecked(checked);
                Cb_Return.setText(text);
                Cb_Return.setOnCheckedChangeListener(onCheckedChangeListener);

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

                checked = FALSE;

                Cb_Return.setChecked(checked);

                setOptionButtons();
            }

            @Override
            protected void PositiveClick() {

                boolean isChecked = Cb_Return.isChecked();

                ButtonConfirm(isChecked);
            }
        };

        setOptionButtons();
    }

    private void setOptionButtons() {

        if (checked == prevChecked) {

            createDialog.setModeButtons(BUTTONS_SINGLE);

        } else {

            createDialog.setModeButtons(BUTTONS_THREE);
        }
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

            setOptionButtons();
        }

        private void DummyVoid() {}
    };



    /*----- Tools -----*/

    private void setParameters(@NonNull Context context, int parameters, boolean checked) {

        this.context = context;
        this.checked = checked;

        prevChecked = checked;

        String[] ListParams = context.getResources().getStringArray(parameters);
        text = ListParams[6];
    }

    private void setViewsOnLayout(@NonNull Dialog dialog) {

        int style = R.style.DialogCheckBox;
        int attr = R.attr.dialogCheckBox;

        LinearLayout layout = dialog.findViewById(R.id.Ll_Custom_MainContainer);

        Cb_Return = new CheckBox(context, null, attr, style);
        //Paris.style(Cb_Return).apply(style);

        layout.addView(Cb_Return);
    }


    /*----- Return -----*/

    protected abstract void ChangeCheckedState(boolean IsChecked);
    protected abstract void ButtonConfirm(boolean IsChecked);
}
