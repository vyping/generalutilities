package com.vyping.masterlibrary.Comunication;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import androidx.annotation.NonNull;

public class ExportWaze {


    /**
     * -------- Main Process - Section
     */

    public ExportWaze(@NonNull Context context, double latitude, double longitude) {

        try {

            String url = "https://waze.com/ul?q=" + latitude + "," + longitude + "&navigate=yes";

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(intent);

        } catch (ActivityNotFoundException ex) {

            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=com.waze"));
            context.startActivity(intent);
        }
    }

}
