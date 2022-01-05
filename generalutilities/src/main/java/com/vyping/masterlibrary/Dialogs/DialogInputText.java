package com.vyping.masterlibrary.Dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_BOTH;
import static java.lang.Boolean.TRUE;

import android.app.Dialog;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Common.Definitions;
import com.vyping.masterlibrary.R;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).

public abstract class DialogInputText {

    private Context context;
    private final CreateDialog createDialog;

    private EditText Et_InputText;

    private String prevText, text;


    /*----- SetUp -----*/

    public DialogInputText(@NonNull Context context, int parameters, String Text) {

        setParameters(context, Text);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(Dialog dialog) {

                setViewsOnLayout(dialog);

                Et_InputText.setText(text);
                Et_InputText.addTextChangedListener(textWatcher);

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

                Et_InputText.setText("");
            }

            @Override
            protected void PositiveClick() {

                text = String.valueOf(Et_InputText.getText());

                ButtonConfirm(text);
            }
        };

        setOptionButtons();
    }

    private void setOptionButtons() {

        if (!text.equals("") && !text.equals(prevText)) {

            createDialog.setModeButtons(BUTTONS_BOTH);

        } else {

            createDialog.setModeButtons(Definitions.BUTTONS_SINGLE);
        }
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

            setOptionButtons();
        }

        @Override
        public void afterTextChanged(Editable editable) { }
    };


    /*----- Tools -----*/

    private void setParameters(@NonNull Context context, String text) {

        this.context = context;
        this.text = text;

        prevText = text;
    }

    private void setViewsOnLayout(@NonNull Dialog dialog) {

        int style = R.style.DialogInputText;
        int attr = R.attr.dialogInputText;

        LinearLayout layout = dialog.findViewById(R.id.Ll_Custom_MainContainer);
        layout.setOrientation(LinearLayout.VERTICAL);

        Et_InputText = new EditText(context, null, attr, style);

        Paris.style(Et_InputText).apply(style);

        layout.addView(Et_InputText);
    }


    /*----- Return -----*/

    protected abstract void ButtonConfirm(String InputText);
}
