package com.vyping.masterlibrary.Comunication;

import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

public class ShareData {


    /**
     * -------- Main Process - Section
     */

    public void Coordinates(@NonNull Context context, double latitude, double longitude) {

        String uri = "https://www.google.com/maps/search/?api=1&query=" + latitude + "," + longitude;

        Intent sharingIntent = new Intent(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        //String ShareSub = "Here is my location";
        //sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, ShareSub);
        sharingIntent.putExtra(Intent.EXTRA_TEXT, uri);

        context.startActivity(Intent.createChooser(sharingIntent, "Compartir vía:"));
    }
}
