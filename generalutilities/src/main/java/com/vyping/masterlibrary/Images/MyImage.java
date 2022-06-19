package com.vyping.masterlibrary.Images;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.vyping.masterlibrary.Common.Files;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.resources.MyAssets;
import com.vyping.masterlibrary.resources.MyResources;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MyImage {

    public int errorImage = R.drawable.icon_image;

    public void putImageFromWeb(@NonNull ImageView imageView, String url) {

        Context context = imageView.getContext();

        Glide.with(context).load(url).error(errorImage).into(imageView);
    }

    public void putImageFromResources(@NonNull ImageView imageView, int resource) {

        Context context = imageView.getContext();

        Glide.with(context).load(resource).error(errorImage).into(imageView);
    }

    public void putImageFromAssets(@NonNull ImageView imageView, String nameFile, @Files.Type String type) {

        Context context = imageView.getContext();
        Uri uri = new MyAssets().getUri(nameFile, type);

        Glide.with(context).load(uri).error(errorImage).into(imageView);
    }

    public void putImageFromResourcesOrWeb(@NonNull ImageView imageView, int resource, Uri uriFile) {

        Context context = imageView.getContext();
        boolean exist =new MyResources().exist(context, resource);

        if (exist) {

            Glide.with(context).load(resource).error(errorImage).into(imageView);

        } else {

            Glide.with(context).load(uriFile).error(errorImage).into(imageView);
        }
    }

    public void putImageFromAssetsOrWeb(@NonNull ImageView imageView, String nameFile, @Files.Type String type, String uriFile) {

        Context context = imageView.getContext();
        boolean exist =new MyAssets().exists(context, nameFile, type);

        if (exist) {

            Uri uri = new MyAssets().getUri(nameFile, type);
            Glide.with(context).load(uri).error(errorImage).into(imageView);

        } else {

            Glide.with(context).load(uriFile).error(errorImage).into(imageView);
        }
    }

    public void putImageFromWebOrAssets(@NonNull ImageView imageView, String uriFile, String nameFile, @Files.Type String type) {

        Context context = imageView.getContext();

        try {

            URL url = new URL(uriFile);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            int responseCode = huc.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                Glide.with(context).load(uriFile).error(errorImage).into(imageView);

            } else {

                Uri uri = new MyAssets().getUri(nameFile, type);
                Glide.with(context).load(uri).error(errorImage).into(imageView);
            }

        } catch (IOException e) {

            Uri uri = new MyAssets().getUri(nameFile, type);
            Glide.with(context).load(uri).error(errorImage).into(imageView);
        }
    }

    public void putImageFromWebOrAssets2(@NonNull ImageView imageView, String uriFile, String nameFile, @Files.Type String type) {

        Context context = imageView.getContext();
        Drawable drawable = new MyDrawable().extractFromAssets(context, nameFile, type);

        Glide.with(context).load(uriFile).error(drawable).into(imageView);
    }
}
