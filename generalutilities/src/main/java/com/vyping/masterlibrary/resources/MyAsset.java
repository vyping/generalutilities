package com.vyping.masterlibrary.resources;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Common.MyFile;

import java.io.IOException;
import java.io.InputStream;

public class MyAsset {


    // ----- Main ModelMethods ----- //

    public AssetManager getAssetManager(@NonNull Context context) {

        return context.getResources().getAssets();
    }

    public boolean exists(@NonNull Context context, String asset) {

        boolean exist = FALSE;

        InputStream inputStream = open(context, asset);

        if (null != inputStream) {

            exist = TRUE;
        }

        return exist;
    }

    public boolean exists(@NonNull Context context, String asset, @MyFile.Type String type) {

        String file = asset + type;

        return exists(context, file);
    }

    public InputStream open(@NonNull Context context, String asset) {

        AssetManager assetManager = getAssetManager(context);
        InputStream inputStream = null;

        try {

            inputStream = assetManager.open(asset);

        } catch (IOException ignored) {}

        return inputStream;
    }

    public InputStream open(@NonNull Context context, String asset, @MyFile.Type String type) {

        String assetName = asset + type;

        return open(context, assetName);
    }

    public Uri getUri(String asset) {

        return Uri.parse("file:///android_asset/" + asset);
    }

    public Uri getUri(String asset, @MyFile.Type String type) {

        String assetName = asset + type;

        return getUri(assetName);
    }
}
