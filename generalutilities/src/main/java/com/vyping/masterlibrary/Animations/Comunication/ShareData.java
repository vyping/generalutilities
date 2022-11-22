package com.vyping.masterlibrary.Animations.Comunication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

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

    public void ImageAndData(@NonNull Context context, Uri urlImage, String Title, String text) {

        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, Title);
        shareIntent.putExtra(Intent.EXTRA_TITLE, Title);
        shareIntent.putExtra(Intent.EXTRA_TEXT, text);
        shareIntent.putExtra(Intent.EXTRA_STREAM, urlImage);
        shareIntent.setType("image/*");
        shareIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);

        context.startActivity(Intent.createChooser(shareIntent, "Share images..."));
    }
}
