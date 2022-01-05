package com.vyping.masterlibrary.Comunication;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

public class SendSMS {


    /**
     * -------- Main Process - Section
     */


    public SendSMS(Context context, String phone, String message) {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS) == PackageManager.PERMISSION_GRANTED) {

            if (!phone.equals("")) {

                SmsManager smsManager = SmsManager.getDefault();
                smsManager.sendTextMessage(phone, null, message, null, null);

                Toast.makeText(context, "¡Mensaje enviado con éxito!", Toast.LENGTH_LONG).show();

            } else {

                Toast.makeText(context, "¡El destinatario no tiene teléfono registrado!", Toast.LENGTH_LONG).show();
            }
        }
    }
}
