package com.vyping.masterlibrary.Downloads;

import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;

import androidx.annotation.NonNull;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class MyDownloadStream {

    public Context context;
    public Interfase interfase;

    private final String urlString, path, fileName;

    public MyDownloadStream(@NonNull Context context, String urlString, String path, String fileName, Interfase interfase) {

        this.context = context;
        this.interfase = interfase;
        this.urlString = urlString;
        this.path = path;
        this.fileName = fileName;

        MyAsyncTasks myAsyncTasks = new MyAsyncTasks();
        myAsyncTasks.execute(urlString);
    }

    public class MyAsyncTasks extends AsyncTask<String, String, File> {

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
        }

        @Override
        protected File doInBackground(@NonNull String... strings) {

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            try {

                URL url = new URL(urlString);
                InputStream inputStream = url.openStream();
                DataInputStream dataInputStream = new DataInputStream(inputStream);

                byte[] buffer = new byte[1024];
                int length;

                File file = new File(context.getExternalFilesDir(path), fileName);
                boolean existFile = file.exists();

                if (!existFile) {

                    file.createNewFile();
                }

                FileOutputStream fileOutputStream = new FileOutputStream(file);

                while ((length = dataInputStream.read(buffer)) > 0) {

                    fileOutputStream.write(buffer, 0, length);
                }

                return file;

            } catch (IOException mue) {

                mue.printStackTrace();

                return null;
            }
        }

        @Override
        protected void onPostExecute(File fileDownloaded) {

            interfase.Downloaded(fileDownloaded);
        }
    }

    public interface Interfase {

        void Downloaded(File fileDownloaded);
    }
}
