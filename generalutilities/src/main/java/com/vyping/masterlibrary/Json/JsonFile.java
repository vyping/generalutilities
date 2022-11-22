package com.vyping.masterlibrary.Json;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;

import androidx.annotation.NonNull;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;

public class JsonFile extends MyJsonReader {


    // ----- Setup ----- //

    public JsonFile(@NonNull Context context, String dirName, String fileName, @NonNull Interface interfase) {

        File directory = context.getExternalFilesDir(dirName);
        File file = new File(directory, fileName);

        try {

            InputStream inputStream = new FileInputStream(file);

            readStream(inputStream, interfase);

        } catch (IOException ignored) {}
    }

    public JsonFile(@NonNull Context context, String fileName, @NonNull Interface interfase) {

        try {

            InputStream inputStream = context.getAssets().open(fileName);

            readStream(inputStream, interfase);

        } catch (IOException ignored) {}
    }

    public JsonFile(@NonNull Context context, int fileName, @NonNull Interface interfase) {

        InputStream inputStream = context.getResources().openRawResource(fileName);

        readStream(inputStream, interfase);
    }


    // ----- ModelMethods ----- //

    private void readStream(@NonNull InputStream inputStream, @NonNull Interface interfase) {

        try {

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            int read = inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, StandardCharsets.UTF_8);

            try {

                JSONObject obj = new JSONObject(json);
                Iterator<String> keys = obj.keys();

                while (keys.hasNext()) {

                    String key = keys.next();
                    JSONObject value = obj.getJSONObject(key);

                    interfase.GetJsonObject(key, value);
                }

            } catch (JSONException ignored) {}

        } catch (IOException ignored) {}
    }


    // ----- Interface ----- //

    public interface Interface {

        void GetJsonObject(String key, JSONObject jsonObject);
    }
}

