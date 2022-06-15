package com.vyping.masterlibrary.Common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import androidx.annotation.NonNull;

public class MyApp {


    public PackageInfo getPackageInfo(@NonNull Context context) {

        try {

            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0);

        } catch (PackageManager.NameNotFoundException e) {

            e.printStackTrace();

            return null;
        }
    }

    public String getVersion(@NonNull Context context) {

        PackageInfo PackageInfo = getPackageInfo(context);

        return PackageInfo.versionName;

    }

    public int getCode(@NonNull Context context) {

        PackageInfo PackageInfo = getPackageInfo(context);

        return PackageInfo.versionCode;
    }

    public void requestUpdate(@NonNull Context context) {

        final String appPackageName = context.getPackageName();

        try {

            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));

        } catch (android.content.ActivityNotFoundException anfe) {

            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}
