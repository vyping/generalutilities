package com.vyping.masterlibrary.Images;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.vyping.masterlibrary.R;

public class MyColor {


    /**
     * -------- Basics Operations - Section
     */

    public int getColor(@NonNull Context context, int resColor) {

        return ContextCompat.getColor(context, resColor);
    }
}
