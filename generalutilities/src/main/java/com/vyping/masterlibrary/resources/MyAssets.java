package com.vyping.masterlibrary.resources;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Common.Files;

import java.io.IOException;
import java.io.InputStream;

public class MyAssets {


    // ----- Main Methods ----- //

    public AssetManager getAssetManager(@NonNull Context context) {

        return context.getResources().getAssets();
    }

    public boolean exists(@NonNull Context context, String asset, @Files.Type String type) {

        boolean exist = FALSE;

        String file = asset + type;
        InputStream inputStream = open(context, asset, type);

        if (null != inputStream) {

            exist = TRUE;
        }

        return exist;
    }

    public InputStream open(@NonNull Context context, String asset, @Files.Type String type) {

        AssetManager assetManager = getAssetManager(context);
        InputStream inputStream = null;

        try {

            String assetName = asset + type;
            inputStream = assetManager.open(assetName);

        } catch (IOException ignored) {}

        return inputStream;
    }

    public Uri getUri(String asset, @Files.Type String type) {

        String assetName = asset + type;

        return Uri.parse("file:///android_asset/" + assetName);
    }
}
