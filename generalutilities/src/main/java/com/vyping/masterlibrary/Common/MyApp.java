package com.vyping.masterlibrary.Common;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
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

    public PackageManager getPackageManager(@NonNull Context context) {

        return context.getPackageManager();
    }

    public ApplicationInfo getApplicationInfo(@NonNull Context context) {

        try {

            PackageManager packageManager = getPackageManager(context);
            String packageName = getPackage(context);

            return packageManager.getApplicationInfo(packageName, 0);

        } catch (PackageManager.NameNotFoundException e) {

            return new ApplicationInfo();
        }
    }

    public String getPackage(@NonNull Context context) {

        //return context.getPackageName();
        return context.getApplicationInfo().packageName;
    }

    public String getName(Context context) {

        PackageManager packageManager = getPackageManager(context);
        ApplicationInfo applicationInfo = getApplicationInfo(context);
        CharSequence name = packageManager.getApplicationLabel(applicationInfo);

        return String.valueOf(name);
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

        final String appPackageName = getPackage(context);

        try {

            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));

        } catch (android.content.ActivityNotFoundException anfe) {

            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }
}
