package com.vyping.masterlibrary.Common;

import android.content.Context;
import android.os.Build;
import android.provider.Settings;

public class MyPhone {

    public int androidSdk() {

        return Build.VERSION.SDK_INT;
    }

    public String phoneManufacturer() {

        return Build.MANUFACTURER;
    }

    public String phoneModel() {

        return Build.MODEL;
    }

    public String phoneId(Context context) {

        String address = Settings.Secure.getString(context.getContentResolver(), Settings.Secure.ANDROID_ID);

        return address;
    }
}
