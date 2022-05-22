package com.vyping.masterlibrary.Comunication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class SendMail {


    /**
     * -------- Main Process - Section
     */

    public SendMail(@NonNull Context context, String senderEmail, String senderName, @NonNull String receiverEmail) {

        if (!receiverEmail.equals("")) {

            try {

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + receiverEmail));
                intent.putExtra(Intent.EXTRA_EMAIL, senderEmail);
                intent.putExtra(Intent.EXTRA_SUBJECT, senderName);
                context.startActivity(intent);

            } catch (Exception e) {

                Toast.makeText(context, "¡No se ha podido enviar el correo: " + e + "!", Toast.LENGTH_LONG).show();
            }

        } else {

            Toast.makeText(context, "¡No existe el email indicado!", Toast.LENGTH_LONG).show();
        }
    }
}
