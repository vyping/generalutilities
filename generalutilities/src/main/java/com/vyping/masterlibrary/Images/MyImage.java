package com.vyping.masterlibrary.Images;

import android.content.Context;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.resources.MyAssets;

public class MyImage {

    private int errorImage = R.drawable.icon_image;

    private void putImageFromWeb(@NonNull ImageView imageView, String url) {

        Context context = imageView.getContext();

        Glide.with(context).load(url).error(errorImage).into(imageView);
    }

    private void putImageFromResources(@NonNull ImageView imageView, int resource) {

        Context context = imageView.getContext();

        Glide.with(context).load(resource).error(errorImage).into(imageView);
    }

    private void putImageFromAssets(@NonNull ImageView imageView, String nameFile) {

        Context context = imageView.getContext();
        Uri uri = new MyAssets().getUri(nameFile);

        Glide.with(context).load(uri).error(errorImage).into(imageView);
    }
}
