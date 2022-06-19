package com.vyping.masterlibrary.web;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.os.StrictMode;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyWeb {

    public void setPolicy() {

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    public int connect(String urlFile) {

        try {

            setPolicy();

            URL url = new URL(urlFile);
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();

            return huc.getResponseCode();

        } catch (IOException ignored) {

          return 0;
        }
    }

    public boolean exist(String urlFile) {

        int responseCode = connect(urlFile);

        if (responseCode == HttpURLConnection.HTTP_OK) {

            return TRUE;

        } else {

            return FALSE;
        }
    }
}
