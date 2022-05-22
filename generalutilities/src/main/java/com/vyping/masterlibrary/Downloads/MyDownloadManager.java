package com.vyping.masterlibrary.Downloads;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;

import androidx.annotation.NonNull;

import java.io.File;

public class MyDownloadManager {

    public MyDownloadManager(@NonNull Context context, String urlString, String path, String nameFile, String title, String description) {

        DownloadManager downloadmanager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        Uri uri = Uri.parse(urlString);
        File directory = context.getExternalFilesDir(path);

        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(title);
        request.setDescription(description);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setVisibleInDownloadsUi(false);
        request.setDestinationUri(Uri.fromFile(new File(directory, "/" + nameFile)));

        downloadmanager.enqueue(request);
    }
}
