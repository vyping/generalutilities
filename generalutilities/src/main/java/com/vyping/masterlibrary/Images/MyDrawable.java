package com.vyping.masterlibrary.Images;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.vyping.masterlibrary.R;

public class MyDrawable {


    /**
     * ------  Basic Operatios - Section
     */

    public Drawable extractFromString(Context context, @NonNull String stringDrawable) {

        if (!stringDrawable.equals("")) {

            int resDrawable = context.getResources().getIdentifier(stringDrawable, "drawable", context.getPackageName());

            return ContextCompat.getDrawable(context, resDrawable);

        } else {

            return extractFromResources(context, R.drawable.icon_image);
        }
    }

    public Drawable extractFromResources(Context context, int resourceDrawable) {

        if (resourceDrawable != 0) {

            return ContextCompat.getDrawable(context, resourceDrawable);

        } else {

            return ContextCompat.getDrawable(context, R.drawable.icon_image);
        }
    }

    public String extractNameFromResources(@NonNull Context context, int resourceDrawable) {

        return context.getResources().getResourceEntryName(resourceDrawable);
    }

    public int extractIdFromString(@NonNull Context context, String stringDrawable) {

        return context.getResources().getIdentifier(stringDrawable, "drawable", context.getPackageName());
    }

    public Drawable changeSizeBounds(Context context, int resourceDrawable, int weight, int height) {

        Drawable drawable = extractFromResources(context, resourceDrawable);
        drawable.setBounds(0, 0, weight, height);

        return drawable;
    }

    public Drawable changeDrawableColor(Context context, Drawable drawable, int resourceColor) {

        int color = ContextCompat.getColor(context, resourceColor);

        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable, color);

        return wrappedDrawable;
    }

    public Drawable changeDrawableColor(Context context, int resourceDrawable, int resourceColor) {

        Drawable drawable = extractFromResources(context, resourceDrawable);

        return changeDrawableColor(context, drawable, resourceColor);
    }

    public Drawable changeSizeBounds(@NonNull Drawable drawable, int weight, int height) {

        drawable.setBounds(0, 0, weight, height);

        return drawable;
    }
}
