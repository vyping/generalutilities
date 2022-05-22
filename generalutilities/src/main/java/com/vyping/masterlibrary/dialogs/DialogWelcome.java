package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Models.DialogParams.MODE;
import static com.vyping.masterlibrary.Models.DialogParams.PARAMS;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.views.MyImageView;
import com.vyping.masterlibrary.views.MyTextView;

public abstract class DialogWelcome extends CreateDialog {


    /**
     * -------- SetUp - Section
     */

    public DialogWelcome(@NonNull Context context, int arrayParameters) {

        super(context, arrayParameters);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogWelcome(@NonNull Context context, @DialogMode int dialogMode, int arrayParameters) {

        super(context, dialogMode, arrayParameters);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogWelcome(@NonNull Context context, Drawable icon, String title, String description, String help, String error, String success) {

        super(context, icon, title, description, help, error, success);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogWelcome(@NonNull Context context, @DialogMode int dialogMode, Drawable icon, String title, String description, String help, String error, String success) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogWelcome(@NonNull Context context, int icon, String title, String description, String help, String error, String success) {

        super(context, icon, title, description, help, error, success);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    public DialogWelcome(@NonNull Context context, @DialogMode int dialogMode, int icon, String title, String description, String help, String error, String success) {

        super(context, dialogMode, icon, title, description, help, error, success);

        setParameters();
        setDialogViews();
        setModeDialogButtons();
    }

    private void setParameters() {
    }

    private void setDialogViews() {

        Drawable drawable = new MyDrawable().extractFromString(CONTEXT, PARAMS[6]);
        Drawable Drawable = new MyDrawable().changeSizeBounds(drawable, 200, 200);

        SetContainerOrientation(LinearLayout.VERTICAL);
        setWelcomeImage(Drawable);
        setWelcomeTitle(PARAMS[7]);
    }


    /*----- Process -----*/

    public void setWelcomeTitle(String text) {

        int style = R.style.DialogShowText;
        int attr = R.attr.dialogShowText;

        TextView textView = new MyTextView().createByAttributes(CONTEXT, attr, style, text);

        AddCustomView(textView);
    }

    public void setWelcomeTitle(int text) {

        int style = R.style.DialogShowText;
        int attr = R.attr.dialogShowText;

        TextView textView = new MyTextView().createByAttributes(CONTEXT, attr, style, text);

        AddCustomView(textView);
    }

    public void setWelcomeImage(Drawable drawable) {

        int style = R.style.DialogShowImage;
        int attr = R.attr.dialogShowImage;

        ImageView imageView = new MyImageView().createByAttributes(CONTEXT, attr, style, drawable);

        AddCustomView(imageView);
    }

    public void setWelcomeImage(int imageResource) {

        int style = R.style.DialogShowImage;
        int attr = R.attr.dialogShowImage;

        ImageView imageView = new MyImageView().createByAttributes(CONTEXT, attr, style, imageResource);

        AddCustomView(imageView);
    }


    /*----- Utilities -----*/

    public void setModeDialogButtons() {

        if (MODE == DIALOG_STEP_INITIAL) {

            SetButtonCancel(BUTTON_LEFT, new Interfase() {

                @Override
                public boolean ClickButton() {

                    return NegativeButton();
                }

                private void DummyVoid() {
                }
            });
            SetButtonNext(BUTTON_RIGHT, new Interfase() {

                @Override
                public boolean ClickButton() {

                    return PositiveButton();
                }

                private void DummyVoid() {
                }
            });

        } else if (MODE == DIALOG_STEP_FINAL) {

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
