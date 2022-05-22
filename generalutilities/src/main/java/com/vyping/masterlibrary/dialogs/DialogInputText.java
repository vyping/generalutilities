package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Common.Definitions.TEXT_INPUT_NORMAL;
import static com.vyping.masterlibrary.Models.DialogParams.MODE;
import static com.vyping.masterlibrary.Models.DialogParams.PARAMS;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Common.Definitions;
import com.vyping.masterlibrary.R;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).

public abstract class DialogInputText extends CreateDialog {

    private EditText Et_InputText;

    private String prevText, text, hint;
    @Definitions.TextInput
    int inputType;


    /*----- SetUp -----*/

    public DialogInputText(@NonNull Context context, int arrayParameters) {

        super(context, arrayParameters);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogInputText(@NonNull Context context, @DialogMode int dialogMode, int arrayParameters) {

        super(context, dialogMode, arrayParameters);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogInputText(@NonNull Context context, Drawable icon, String title, String description, String help, String error, String success) {

        super(context, icon, title, description, help, error, success);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogInputText(@NonNull Context context, @DialogMode int dialogMode, Drawable icon, String title, String description, String help, String error, String success) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogInputText(@NonNull Context context, int icon, String title, String description, String help, String error, String success) {

        super(context, icon, title, description, help, error, success);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogInputText(@NonNull Context context, @DialogMode int dialogMode, int icon, String title, String description, String help, String error, String success) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    private void setParameters() {

        this.text = "";
        this.prevText = "";
        this.hint = PARAMS[4];
        this.inputType = TEXT_INPUT_NORMAL;
    }

    private void setParameters(String prevText) {

        this.text = "";
        this.prevText = prevText;
        this.hint = PARAMS[4];
        this.inputType = TEXT_INPUT_NORMAL;
    }

    private void setParameters(@Definitions.TextInput int inputType) {

        this.text = "";
        this.prevText = "";
        this.hint = PARAMS[4];
        this.inputType = inputType;
    }

    private void setParameters(String prevText, @Definitions.TextInput int inputType) {

        this.text = "";
        this.prevText = prevText;
        this.hint = PARAMS[4];
        this.inputType = inputType;
    }

    private void setDialogViews() {

        int style = R.style.DialogInputText;
        int attr = R.attr.dialogInputText;

        Et_InputText = new EditText(CONTEXT, null, attr, style);
        Paris.style(Et_InputText).apply(style);
        AddCustomView(Et_InputText);

        Et_InputText.setInputType(inputType);
        Et_InputText.setText(text);
        Et_InputText.setHint(hint);
        Et_InputText.addTextChangedListener(textWatcher);
    }


    /*----- Process -----*/

    public void setAditionalInfo(String text, String hint, int inputType) {

        this.text = text;
        this.hint = hint;
        this.inputType = inputType;

        setText(text);
        setHint(hint);
        setTypeInput(inputType);
    }

    public String getText() {

        return String.valueOf(Et_InputText.getText());
    }

    public void setText(String text) {

        this.text = text;

        Et_InputText.setText(text);

        setModeDialogButtons();
    }

    public void setHint(String hint) {

        Et_InputText.setHint(hint);
    }

    public void setTypeInput(int inputType) {

        Et_InputText.setInputType(inputType);
    }

    public void setError() {

        this.setError(PARAMS[5]);
    }

    public void setError(String error) {

        SetErrorMessage(error);
        setModeDialogButtons();
    }


    /*----- Listeners -----*/

    private final TextWatcher textWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void afterTextChanged(Editable editable) {

            text = String.valueOf(editable);

            setModeDialogButtons();
        }
    };


    /*----- Utilities -----*/

    private void setModeDialogButtons() {

        removeButtons();

        if (MODE == DIALOG_NORMAL) {

            if (text.equals("")) {

                SetButtonCancel(BUTTON_RIGHT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        return true;
                    }

                    private void DummyVoid() {
                    }
                });

            } else {

                SetButtonNeutral(BUTTON_LEFT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        Et_InputText.setText("");
                        setModeDialogButtons();

                        return false;
                    }

                    private void DummyVoid() {
                    }
                });
                SetButtonConfirm(BUTTON_RIGHT, new Interfase() {

                    @Override
                    public boolean ClickButton() {

                        return ButtonConfirm(text);
                    }

                    private void DummyVoid() {
                    }
                });
            }

        } else if (MODE == DIALOG_STEP_INITIAL) {

            SetButtonCancel(BUTTON_LEFT, new Interfase() {

                @Override
                public boolean ClickButton() {

                    return ButtonCancel();
                }

                private void DummyVoid() {
                }
            });
            SetButtonNext(BUTTON_RIGHT, new Interfase() {

                @Override
                public boolean ClickButton() {

                    return ButtonConfirm(text);
                }

                private void DummyVoid() {
                }
            });

        } else if (MODE == DIALOG_STEP_INTERMEDIATE) {

            SetButtonBack(BUTTON_LEFT, new Interfase() {

                @Override
                public boolean ClickButton() {

                    return ButtonCancel();
                }

                private void DummyVoid() {
                }
            });
            SetButtonNext(BUTTON_RIGHT, new Interfase() {

                @Override
                public boolean ClickButton() {

                    return ButtonConfirm(text);
                }

                private void DummyVoid() {
                }
            });

        } else if (MODE == DIALOG_STEP_FINAL) {

            SetButtonBack(BUTTON_LEFT, new Interfase() {

                @Override
                public boolean ClickButton() {

                    return ButtonCancel();
                }

                private void DummyVoid() {
                }
            });
            SetButtonConfirm(BUTTON_RIGHT, new Interfase() {

                @Override
                public boolean ClickButton() {

                    return ButtonConfirm(text);
                }

                private void DummyVoid() {
                }
            });
        }
    }


    /*----- Return -----*/

    protected abstract boolean ButtonCancel();

    protected abstract boolean ButtonConfirm(String InputText);
}
