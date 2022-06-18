package com.vyping.masterlibrary.resources;

import android.content.Context;
import android.content.res.AssetManager;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.io.IOException;
import java.io.InputStream;

public class MyAssets {


    // ----- Main Methods ----- //

    public AssetManager getAssetManager(@NonNull Context context) {

        return context.getResources().getAssets();
    }

    public boolean exists(@NonNull Context context, String pathInAssetsDir) {

        AssetManager assetManager = getAssetManager(context);
        InputStream inputStream = null;

        try {

            inputStream = assetManager.open(pathInAssetsDir);

            if(null != inputStream ) {

                return true;
            }

        }  catch(IOException e) {

            e.printStackTrace();

        } finally {

            try {

                inputStream.close();

            } catch (IOException e) {

                e.printStackTrace();
            }
        }

        return false;
    }

    public Uri getUri(String asset) {

        return Uri.parse("file:///android_asset/" + asset);
    }
}
