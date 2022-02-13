package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_REFRESH_ACCEPT;
import static java.lang.Boolean.FALSE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Common.Files;
import com.vyping.masterlibrary.Views.SignView;
import com.vyping.masterlibrary.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

public abstract class DialogSigning extends CreateDialog {

    private Context context;

    private SignView signView;

    private boolean signed;


    /*----- SetUp -----*/

    @SuppressLint("SetTextI18n")
    public DialogSigning(Context context, int parameters) {

        super(context, parameters);

        setParameters(context);
        setModeButtons(setModeButtons());
        setDialogListener(new CreateDialog.DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() {

                signed = FALSE;

                signView.ClearSignBitmap();

                setModeButtons(setModeButtons());
            }

            @Override
            public void PositiveClick() {

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
        });
    }

    private void setParameters(@NonNull Context context) {

        this.context = context;

        signed = FALSE;
    }

    private void setViewsOnLayout() {

        int style = R.style.DialogSigning;
        int attr = R.attr.dialogSigning;

        signView = new SignView(context, null, attr, style);
        signView.setOnSignedListener(onDrawListener);
        Paris.style(signView).apply(style);
        addCustomView(signView);
    }


    /*----- Tools -----*/

    private final SignView.OnSignedListener onDrawListener = new SignView.OnSignedListener() {

        @Override
        public void StatusSign(boolean Signed) {

            signed = Signed;

            setModeButtons(setModeButtons());
        }

        private void DummyVoid() { }
    };

    /*----- Utilities -----*/

    private int setModeButtons() {

        if (!signed) {

            return BUTTONS_CANCEL;

        } else {

            return BUTTONS_REFRESH_ACCEPT;
        }
    }


    /*----- Return -----*/

    protected abstract void Signed(Bitmap SigningBitmap);
}


