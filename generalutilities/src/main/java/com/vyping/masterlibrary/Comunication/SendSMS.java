package com.vyping.masterlibrary.Comunication;

import android.content.Context;
import android.telephony.SmsManager;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.vyping.masterlibrary.Common.MyPermissions;

public class SendSMS extends MyPermissions {

    private Context context;

    private static String PHONE, MESSAGE;


    // ----- Setup ----- //

    public SendSMS(Context context, int parameters, String phone, String message) {

        super(context, parameters, new String[]{PERMISSION_SMS_SEND}, PERMISSION_CODE_SMS_SEND);

        SetParameters(context, phone, message);
        RequestPermissions(permissionsInterfase);
    }


    // ----- ModelMethods ----- //

    private void sendSMS() {

        if (ContextCompat.checkSelfPermission(context, PERMISSION_SMS_SEND) == PERMISSIONS_GRANTED) {

            if (!PHONE.equals("") && !MESSAGE.equals("")) {

                try {

                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(PHONE, null, MESSAGE, null, null);

                    Toast.makeText(context, "¡Mensaje enviado con éxito!", Toast.LENGTH_LONG).show();

                } catch (Exception e) {

                    Toast.makeText(context, "¡No se ha podido enviar el mensaje: " + e + "!", Toast.LENGTH_LONG).show();
                }

            } else {

                if (PHONE.equals("")) {

                    Toast.makeText(context, "¡No existe el destinatario indicado!", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(context, "¡No existe texto en el mensaje para enviar!", Toast.LENGTH_LONG).show();
                }
            }
        }
    }


    // ----- Listeners ----- //

    private final MyPermissions.Interfase permissionsInterfase = new MyPermissions.Interfase() {

        @Override
        public void PermissionsResult(int result) {

            sendSMS();
        }

        private void DummyVoid() {
        }
    };


    // ----- Tools ----- //

    private void SetParameters(Context context, String phone, String message) {

        this.context = context;

        PHONE = phone;
        MESSAGE = message;
    }
}
