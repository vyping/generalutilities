package com.vyping.masterlibrary.Images;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.vyping.masterlibrary.R;

public class MyColor {


    /**
     * -------- Basics Operations - Section
     */

    public int extractFromString(Context context, @NonNull String stringColor) {

        if (!stringColor.equals("")) {

            int resColor = context.getResources().getIdentifier(stringColor, "color", context.getPackageName());

            return ContextCompat.getColor(context, resColor);

        } else {

            return extractFromResources(context, R.color.Primary);
        }
    }

    public int extractFromResources(Context context, int resourceColor) {

        if (resourceColor != 0) {

            return ContextCompat.getColor(context, resourceColor);

        } else {

            return ContextCompat.getColor(context, R.color.Primary);
        }
    }

    public String extractNameFromResources(@NonNull Context context, int resourceColor) {

        return context.getResources().getResourceEntryName(resourceColor);
    }

    public int extractIdFromString(@NonNull Context context, String stringColor) {

        return context.getResources().getIdentifier(stringColor, "color", context.getPackageName());
    }
}
