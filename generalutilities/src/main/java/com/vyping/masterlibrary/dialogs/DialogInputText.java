package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_REFRESH_ACCEPT;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.R;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).

public abstract class DialogInputText extends CreateDialog {

    private Context context;

    private EditText Et_InputText;

    private String prevText, text;


    /*----- SetUp -----*/

    public DialogInputText(@NonNull Context context, int parameters, String Text) {

        super(context, parameters);

        setParameters(context, Text);
        setDialogViews();
        setModeButtons(setModeButtons());
        setDialogListener(new CreateDialog.DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() {

                Et_InputText.setText("");
            }

            @Override
            public void PositiveClick() {

                text = String.valueOf(Et_InputText.getText());

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

        int style = R.style.DialogInputText;
        int attr = R.attr.dialogInputText;

        Et_InputText = new EditText(context, null, attr, style);
        Et_InputText.setText(text);
        Et_InputText.addTextChangedListener(textWatcher);
        Paris.style(Et_InputText).apply(style);

        addCustomView(Et_InputText);
    }



    /*----- Process -----*/

    public void setText(String text) {

        this.text = text;

        Et_InputText.setText(text);
    }

    public String getText() {

        return String.valueOf(Et_InputText.getText());
    }


    /*----- Listeners -----*/

    private final TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            text = String.valueOf(charSequence);

            setModeButtons(setModeButtons());
        }

        @Override
        public void afterTextChanged(Editable editable) { }
    };


    /*----- Utilities -----*/

    private int setModeButtons() {

        if (!text.equals("") && !text.equals(prevText)) {

            return BUTTONS_REFRESH_ACCEPT;

        } else {

            return BUTTONS_CANCEL;
        }
    }

    /*----- Return -----*/

    protected abstract void ButtonConfirm(String InputText);
}
