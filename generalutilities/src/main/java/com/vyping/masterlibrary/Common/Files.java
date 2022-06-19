package com.vyping.masterlibrary.Common;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.vyping.masterlibrary.Comunication.ShareData;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.net.MalformedURLException;
import java.net.URL;

public class Files {

    public static final String TYPE_JPG = ".jpg";
    public static final String TYPE_PNG = ".png";
    public static final String TYPE_BMP = ".bmp";
    public static final String TYPE_ICO = ".ico";
    public static final String TYPE_GIF = ".gif";
    public static final String TYPE_PDF = ".pdf";


    // ----- SetUp ----- //

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TYPE_JPG, TYPE_PNG, TYPE_BMP, TYPE_ICO, TYPE_PDF, TYPE_GIF, TYPE_PDF})
    public @interface Type {}


    public String setName(String name, @Type String type) {

        if (name != null) {

            String trimed = name.trim();
            String whithOutSpace = new MyStrings().replaceSpaceByUnderScore(trimed);

            return new MyStrings().removeAccentMark(whithOutSpace);

        } else {

            return "" + type;
        }
    }

    public String setNameLower(String name, @Type String type) {

        if (name != null) {

            return setName(name, type).toLowerCase();

        } else {

            return "";
        }
    }

    public void eraseFilesOnDirectory(@NonNull File directory, File file) {

        File[] files = directory.listFiles();

        if (files != null) {

            for (File previousFile : files) {

                if (!previousFile.getName().equals(file.getName())) {

                    boolean delete = previousFile.delete();
                }
            }
        }
    }

    public File CreateFileInExternalDirectory(@NonNull Context context, String folder, String file) {

        File directory = context.getExternalFilesDir(folder);

        if (!directory.exists()) {

            boolean create = directory.mkdirs();
        }

        return new File(directory, file);
    }


    public void DownloadsImage(Context context, String url, String nameFile, String title, String text, String appName) {

        DownloadsImage DownloadsImage = new DownloadsImage();
        DownloadsImage.execute(context, url, nameFile, title, text, appName);
    }


    class DownloadsImage extends AsyncTask<Object, Void, Void> {

        @Override
        protected Void doInBackground(@NonNull Object... objects) {

            URL url = null;
            try {
                url = new URL((String) objects[1]);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            Bitmap bm = null;
            try {
                bm = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }

            File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/" + objects[5]); //Creates app specific folder

            if (!path.exists()) {
                path.mkdirs();
            }

            File imageFile = new File(path, objects[2] + ".png"); // Imagename.png
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(imageFile);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            try {
                bm.compress(Bitmap.CompressFormat.PNG, 100, out); // Compress Image
                out.flush();
                out.close();
                // Tell the media scanner about the new file so that it is
                // immediately available to the user.
                MediaScannerConnection.scanFile((Context) objects[0], new String[]{imageFile.getAbsolutePath()}, null, new MediaScannerConnection.OnScanCompletedListener() {
                    public void onScanCompleted(String path, Uri uri) {

                        new ShareData().ImageAndData((Context) objects[0], uri, (String) objects[3], (String) objects[4]);
                    }
                });
            } catch (Exception e) {
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
        }
    }
}
