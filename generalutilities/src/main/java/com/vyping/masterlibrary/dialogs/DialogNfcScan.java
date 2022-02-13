package com.vyping.masterlibrary.dialogs;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL_ACCEPT;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL_REFRESH;
import static com.vyping.masterlibrary.Common.Definitions.SENSOR_ACTIVE;
import static com.vyping.masterlibrary.Common.Definitions.SENSOR_DISABLED;
import static com.vyping.masterlibrary.Common.Definitions.SENSOR_NONEXISTENT;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.view.ContextThemeWrapper;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Animations.Animations;
import com.vyping.masterlibrary.Animations.TraslationCircular;
import com.vyping.masterlibrary.Common.Definitions;
import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.R;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).

public abstract class DialogNfcScan extends CreateDialog {

    private Context context;
    private NfcAdapter nfcAdapter;
    private TraslationCircular traslationCircular;

    private ConstraintLayout containerLayout;
    private ImageView Iv_Indicator, Iv_Search;

    private static String idTag;
    private static boolean reading;
    @Definitions.SensorState
    private static int stateSensor;



    /*----- SetUp -----*/

    public DialogNfcScan(@NonNull Context context, int parameters, PendingIntent mPendingIntent) {

        super(context, parameters);

        setParameters(context, mPendingIntent);
        setDialogViews();
        startProccess();
        setModeButtons(BUTTONS_CANCEL);
        setDialogListener(new DialogInterface() {

            @Override
            public void NegativeClick() {

                ButtonNegative();
            }

            @Override
            public void RefreshClick() {

                idTag = "";
                reading = TRUE;

                startProccess();
            }

            @Override
            public void PositiveClick() {

                new Animations().stopAnimation();
                ButtonConfirm(idTag);
            }
        });
    }

    private void setParameters(@NonNull Context context, PendingIntent pendingIntent) {

        this.context = context;

        nfcAdapter = NfcAdapter.getDefaultAdapter(context);

        if (nfcAdapter != null) {

            if (nfcAdapter.isEnabled()) {

                stateSensor = SENSOR_ACTIVE;

                nfcAdapter.enableForegroundDispatch(((Activity) context), pendingIntent, null, null);

            } else {

                stateSensor = SENSOR_DISABLED;
            }

        } else {

            stateSensor = SENSOR_NONEXISTENT;
        }

        idTag = "";
        reading = TRUE;
    }

    private void setDialogViews() {

        int styleLayout = R.style.ConstraintLayoutCustom, attrLayout = R.attr.constraintLayoutCustom;
        int styleImage = R.style.IconIdCard, attrImage = R.attr.iconIdCard;
        ContextThemeWrapper wrapperLayout = new ContextThemeWrapper(context, styleLayout);
        ContextThemeWrapper wrapperImage = new ContextThemeWrapper(context, styleImage);

        containerLayout = new ConstraintLayout(wrapperLayout, null, attrLayout, styleLayout);
        Paris.style(containerLayout).apply(styleLayout);
        addCustomView(containerLayout);

        Iv_Indicator = new ImageView(wrapperImage, null, attrImage, attrImage);
        Iv_Indicator.setImageDrawable(new MyDrawable().changeDrawableColor(context, R.drawable.icon_idcard, R.color.colorBlanco));
        Paris.style(Iv_Indicator).apply(styleImage);
        containerLayout.addView(Iv_Indicator);
    }

    public void startProccess() {

        if (stateSensor == SENSOR_ACTIVE) {

            showInstructions("¡Acerque el tag que desea escanear!");

            Drawable drawable = new MyDrawable().changeDrawableColor(context, R.drawable.icon_idcard, R.color.colorBlanco);
            Iv_Indicator.setImageDrawable(drawable);

            int styleImage = R.style.IconIdCard, attrImage = R.attr.iconIdCard;
            ContextThemeWrapper wrapperImage = new ContextThemeWrapper(context, styleImage);
            Iv_Search = new ImageView(wrapperImage, null, attrImage, attrImage);
            Iv_Search.setImageDrawable(new MyDrawable().extractFromResources(context, R.drawable.icon_search));
            Iv_Search.setVisibility(VISIBLE);
            Paris.style(Iv_Search).apply(styleImage);
            containerLayout.addView(Iv_Search);

            traslationCircular = new TraslationCircular(Iv_Search, containerLayout, 2000, 100);

        } else if (stateSensor == SENSOR_DISABLED) {

            showError("¡El sensor NFC del dispositivo se encuentra desactivado!");

            Drawable drawable = new MyDrawable().changeDrawableColor(context, R.drawable.icon_question, R.color.RedDark100);
            Iv_Indicator.setImageDrawable(drawable);

        } else if (stateSensor == SENSOR_NONEXISTENT) {

            showError("¡Este dispositivo no soporta lectura de tarjetas o tags NFC!");

            Drawable drawable = new MyDrawable().changeDrawableColor(context, com.vyping.masterlibrary.R.drawable.icon_times, R.color.RedDark100);
            Iv_Indicator.setImageDrawable(drawable);
        }
    }


    /*----- Methods -----*/

    public void getTagInfo(@NonNull Intent intent) {

        if (reading == TRUE) {

            reading = FALSE;

            traslationCircular.stopCircularTraslation();
            Iv_Search.setVisibility(GONE);

            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            byte[] tagInfo = tag.getId();
            StringBuilder tagId = new StringBuilder();

            for (byte Byte : tagInfo) {

                tagId.append(Integer.toHexString(Byte & 0xFF));
            }

            idTag = String.valueOf(tagId);

            ScannedCard(idTag);
        }
    }

    public void successMessage(String message) {

        showInstructions(message);
        Iv_Indicator.setImageDrawable(new MyDrawable().changeDrawableColor(context, com.vyping.masterlibrary.R.drawable.icon_check, com.vyping.masterlibrary.R.color.GreenMid100));
        setModeButtons(BUTTONS_CANCEL_ACCEPT);
    }

    public void errorMessage(String message) {

        showError(message);
        Iv_Indicator.setImageDrawable(new MyDrawable().changeDrawableColor(context, com.vyping.masterlibrary.R.drawable.icon_times, com.vyping.masterlibrary.R.color.RedMid100));
        setModeButtons(BUTTONS_CANCEL_REFRESH);
    }


    /*----- Return -----*/

    protected abstract void ScannedCard(String codeCard);
    protected abstract void ButtonNegative();
    protected abstract void ButtonConfirm(String codeCard);
}
