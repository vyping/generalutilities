package com.vyping.masterlibrary.Dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_SINGLE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Common.Definitions;
import com.vyping.masterlibrary.Common.Files;
import com.vyping.masterlibrary.Common.SignView;
import com.vyping.masterlibrary.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public abstract class DialogSigning {

    private Context context;
    private final CreateDialog createDialog;

    private SignView signView;

    private boolean signed;


    /*----- SetUp -----*/

    @SuppressLint("SetTextI18n")
    public DialogSigning(Context context, int parameters) {

        setParameters(context);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(Dialog dialog) {

                setViewsOnLayout(dialog);

                signView.setOnSignedListener(onDrawListener);

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

                signed = FALSE;

                signView.ClearSignBitmap();

                setOptionButtons();
            }

            @Override
            protected void PositiveClick() {

                if (signView.requestIfIsSigned()) {

                    try {

                        File file = new Files().CreateFileInExternalDirectory(context, "Signing", "Sign.png");
                        OutputStream outputStream = new FileOutputStream(file);
                        Bitmap signBitmap = signView.getBitmap();
                        signBitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);

                        Signed(signBitmap);

                    } catch (FileNotFoundException ignored) { }
                }
            }
        };

        setOptionButtons();
    }

    private void setOptionButtons() {

        if (signed) {

            createDialog.setModeButtons(Definitions.BUTTONS_THREE);

        } else {

            createDialog.setModeButtons(BUTTONS_SINGLE);
        }
    }


    /*----- Tools -----*/

    private void setParameters(@NonNull Context context) {

        this.context = context;

        signed = FALSE;
    }

    private void setViewsOnLayout(@NonNull Dialog dialog) {

        int style = R.style.DialogSigning;
        int attr = R.attr.dialogSigning;

        LinearLayout layout = dialog.findViewById(R.id.Ll_Custom_MainContainer);
        layout.setOrientation(LinearLayout.VERTICAL);

        signView = new SignView(context, null, attr, style);

        Paris.style(signView).apply(style);

        layout.addView(signView);
    }


    private final SignView.OnSignedListener onDrawListener = new SignView.OnSignedListener() {

        @Override
        public void StatusSign(boolean Signed) {

            signed = Signed;

            setOptionButtons();
        }

        private void DummyVoid() { }
    };


    /*----- Return -----*/

    protected abstract void Signed(Bitmap SigningBitmap);
}


