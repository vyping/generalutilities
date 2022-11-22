package com.vyping.masterlibrary.Animations.Comunication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.core.content.ContextCompat;

import com.vyping.masterlibrary.Common.MyPermissions;

public class PhoneCall extends MyPermissions {

    private Context context;

    private static String PHONE;


    // ----- Setup ----- //

    public PhoneCall(Context context, int parameters, String phone) {

        super(context, parameters, new String[]{PERMISSION_PHONE_CALL}, PERMISSION_CODE_PHONE_CALL);

        SetParameters(context, phone);
        RequestPermissions(permissionsInterfase);
    }


    // ----- ModelMethods ----- //

    private void makePhoneCall() {

        if (ContextCompat.checkSelfPermission(context, PERMISSION_PHONE_CALL) == PERMISSIONS_GRANTED) {

            if (!PHONE.equals("")) {

                try {

                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:" + PHONE));
                    context.startActivity(intent);

                } catch (Exception e) {

                    Toast.makeText(context, "¡No se ha podido realizar la llamada: " + e + "!", Toast.LENGTH_LONG).show();
                }

            } else {

                Toast.makeText(context, "¡No existe el teléfono indicado!", Toast.LENGTH_LONG).show();
            }
        }
    }


    // ----- Listeners ----- //

    private final Interfase permissionsInterfase = new Interfase() {

        @Override
        public void PermissionsResult(int result) {

            makePhoneCall();
        }

        private void DummyVoid() {
        }
    };


    // ----- Tools ----- //

    private void SetParameters(Context context, String phone) {

        this.context = context;

        PHONE = phone;
    }
}
