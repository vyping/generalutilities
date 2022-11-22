package com.vyping.masterlibrary.Common;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.content.res.Configuration;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import androidx.annotation.NonNull;

public class MyDisplay {


    public int displayHeight(@NonNull Context context) {

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        return metrics.heightPixels;
    }

    public int displayWidth(@NonNull Context context) {

        DisplayMetrics metrics = context.getResources().getDisplayMetrics();

        return metrics.widthPixels;
    }

    public boolean isLandScape(@NonNull Context context) {

        int orientation = context.getResources().getConfiguration().orientation;

        if (orientation == Configuration.ORIENTATION_LANDSCAPE) {

            return TRUE;

        } else {

            return FALSE;
        }
    }

    public int getWidthDialog(Context context) {

        if (!isLandScape(context)) {

            return WindowManager.LayoutParams.MATCH_PARENT - 16;

        } else {

            return 640;
        }
    }

    public float dpsToPxs(final @NonNull Context context, final float dps) {

        return dps * context.getResources().getDisplayMetrics().density;
    }

    public float pxsToDps(final @NonNull Context context, final float pxs) {

        return pxs / context.getResources().getDisplayMetrics().density;
    }
}
