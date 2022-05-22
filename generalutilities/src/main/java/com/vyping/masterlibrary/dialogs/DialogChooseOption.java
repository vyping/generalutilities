package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Models.DialogParams.MODE;
import static com.vyping.masterlibrary.Models.DialogParams.PARAMS;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.R;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).

public abstract class DialogChooseOption extends CreateDialog {

    private TextView Tv_Text;

    private String text;


    /*----------- SetUp - Section ----------*/

    public DialogChooseOption(@NonNull Context context, int arrayParameters) {

        super(context, arrayParameters);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogChooseOption(@NonNull Context context, int arrayParameters, String text) {

        super(context, arrayParameters);

        setParameters(text);
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogChooseOption(@NonNull Context context, @DialogMode int dialogMode, int arrayParameters) {

        super(context, dialogMode, arrayParameters);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogChooseOption(@NonNull Context context, @DialogMode int dialogMode, int arrayParameters, String text) {

        super(context, dialogMode, arrayParameters);

        setParameters(text);
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogChooseOption(@NonNull Context context, Drawable icon, String title, String description, String help, String error, String success) {

        super(context, icon, title, description, help, error, success);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogChooseOption(@NonNull Context context, Drawable icon, String title, String description, String help, String error, String success, String text) {

        super(context, icon, title, description, help, error, success);

        setParameters(text);
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogChooseOption(@NonNull Context context, @DialogMode int dialogMode, Drawable icon, String title, String description, String help, String error, String success) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogChooseOption(@NonNull Context context, @DialogMode int dialogMode, Drawable icon, String title, String description, String help, String error, String success, String text) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters(text);
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogChooseOption(@NonNull Context context, int icon, String title, String description, String help, String error, String success) {

        super(context, icon, title, description, help, error, success);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogChooseOption(@NonNull Context context, int icon, String title, String description, String help, String error, String success, String text) {

        super(context, icon, title, description, help, error, success);

        setParameters(text);
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogChooseOption(@NonNull Context context, @DialogMode int dialogMode, int icon, String title, String description, String help, String error, String success) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogChooseOption(@NonNull Context context, @DialogMode int dialogMode, int icon, String title, String description, String help, String error, String success, String text) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters(text);
        setDialogViews();
        setModeDialogButtons();
    }

    private void setParameters() {

        this.text = PARAMS[3];
    }

    private void setParameters(String text) {

        this.text = text;
    }

    private void setDialogViews() {

        int attr = R.attr.dialogChooseOption;
        int style = R.style.DialogChooseOption;

        ContextThemeWrapper wrapper = new ContextThemeWrapper(CONTEXT, style);
        Tv_Text = new TextView(wrapper, null, attr, style);
        Tv_Text.setText(text);
        Paris.style(Tv_Text).apply(style);

        AddCustomView(Tv_Text);
    }

    /*----- Utilities -----*/

    private void setModeDialogButtons() {

        if (MODE == DIALOG_NORMAL) {

            SetButtonCancel(BUTTON_LEFT, new Interfase() {

                @Override
                public boolean ClickButton() {

                    return NegativeButton();
                }

                private void DummyVoid() {
                }
            });
            SetButtonConfirm(BUTTON_RIGHT, new Interfase() {

                @Override
                public boolean ClickButton() {

                    return NegativeButton();
                }

                private void DummyVoid() {
                }
            });

        } else {

            SetButtonBack(BUTTON_LEFT, new Interfase() {

                @Override
                public boolean ClickButton() {

                    return PositiveButton();
                }

                private void DummyVoid() {
                }
            });
            SetButtonConfirm(BUTTON_RIGHT, new Interfase() {

                @Override
                public boolean ClickButton() {

                    return PositiveButton();
                }

                private void DummyVoid() {
                }
            });
        }
    }


    /*----- Return -----*/

    protected abstract boolean NegativeButton();

    protected abstract boolean PositiveButton();
}
