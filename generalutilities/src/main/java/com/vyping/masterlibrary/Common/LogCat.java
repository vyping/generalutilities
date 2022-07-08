package com.vyping.masterlibrary.Common;

import android.annotation.SuppressLint;
import android.util.Log;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerConditionalBinder;

public class LogCat {

    public LogCat(Object value1) {

        Log.i("Desarrollo", String.valueOf(value1));
    }

    public LogCat(Object key1, Object value1) {

        Log.i("Desarrollo", key1 + ": " + value1);
    }

    public LogCat(Object key1, Object value1, Object key2, Object value2) {

        Log.i("Desarrollo", key1 + ": " + value1 + ", " + key2 + ": " + value2);
    }

    public LogCat(Object key1, Object value1, Object key2, Object value2, Object key3, Object value3) {

        Log.i("Desarrollo", key1 + ": " + value1 + ", " + key2 + ": " + value2 + ", " + key3 + ": " + value3);
    }

    public LogCat(Object key1, Object value1, Object key2, Object value2, Object key3, Object value3, Object key4, Object value4) {

        Log.i("Desarrollo", key1 + ": " + value1 + ", " + key2 + ": " + value2 + ", " + key3 + ": " + value3 + ", " + key4 + ": " + value4);
    }
}
