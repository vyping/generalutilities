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

        } catch (IOException ignored) {

            interfase.StartListen(FALSE, 0);
        }
    }

    public JsonFile(@NonNull Context context, String fileName, @NonNull Interface interfase) {

        try {

            InputStream inputStream = context.getAssets().open(fileName);

            readStream(inputStream, interfase);

        } catch (IOException ignored) {

            interfase.StartListen(FALSE, 0);
        }
    }

    public JsonFile(@NonNull Context context, int fileName, @NonNull Interface interfase) {

        InputStream inputStream = context.getResources().openRawResource(fileName);

        readStream(inputStream, interfase);
    }


    // ----- Methods ----- //

    private void readStream(@NonNull InputStream inputStream, @NonNull Interface interfase) {

        try {

            int size = inputStream.available();
            byte[] buffer = new byte[size];
            int read = inputStream.read(buffer);
            inputStream.close();

            String json = new String(buffer, StandardCharsets.UTF_8);

            try {

                int count = 0;
                JSONObject obj = new JSONObject(json);
                Iterator<String> keys = obj.keys();
                long countKeys = obj.length();

                interfase.StartListen(TRUE, countKeys);

                while (keys.hasNext()) {

                    count = count + 1;
                    String key = keys.next();
                    JSONArray value = obj.getJSONArray(key);

                    interfase.ValueListen(key, value, count);
                }

                interfase.FinishListen();

            } catch (JSONException ignored) {

                interfase.StartListen(FALSE, 0);
            }

        } catch (IOException ignored) {

            interfase.StartListen(FALSE, 0);
        }
    }


    // ----- Interface ----- //

    public interface Interface {

        void StartListen(boolean exist, long childCount);

        void ValueListen(String key, JSONArray valueArray, int count);

        void FinishListen();
    }
}

