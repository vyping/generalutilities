package com.vyping.masterlibrary.Common;

import android.os.Build;

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
}
