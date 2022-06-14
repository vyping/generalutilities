package com.vyping.masterlibrary.Images;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.vyping.masterlibrary.Common.MyStrings;
import com.vyping.masterlibrary.R;

import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MyDrawable {

    public static final String TYPE_PNG = ".png";
    public static final String TYPE_JPG = ".jpg";
    public static final String TYPE_BMP = ".bmp";
    public static final String TYPE_ICO = ".ico";
    public static final String TYPE_GIF = ".gif";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TYPE_PNG, TYPE_JPG, TYPE_BMP, TYPE_ICO, TYPE_GIF})
    public @interface Type {}

    /**
     * ------  Extract Operatios - Section
     */

    public Drawable extractFromString(Context context, @NonNull String stringDrawable) {

        if (!stringDrawable.equals("")) {

            int resDrawable = context.getResources().getIdentifier(stringDrawable, "drawable", context.getPackageName());

            return ContextCompat.getDrawable(context, resDrawable);

        } else {

            return extractFromResources(context, R.drawable.icon_image);
        }
    }

    public Drawable extractFromString(@NonNull View view, @NonNull String stringDrawable) {

        Context context = view.getContext();

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

    public Drawable extractFromResources(@NonNull View view, int resourceDrawable) {

        Context context = view.getContext();

        if (resourceDrawable != 0) {

            return ContextCompat.getDrawable(context, resourceDrawable);

        } else {

            return ContextCompat.getDrawable(context, R.drawable.icon_image);
        }
    }

    public Drawable extractFromAssets(@NonNull Context context, @NonNull String stringDrawable) {

        try {

            return Drawable.createFromStream(context.getAssets().open(stringDrawable), null);

        } catch (IOException ignored) {

            return extractFromResources(context, R.drawable.icon_image);
        }
    }

    public Drawable extractFromAssets(@NonNull View view, @NonNull String stringDrawable) {

        Context context = view.getContext();

        try {

            return Drawable.createFromStream(context.getAssets().open(stringDrawable), null);

        } catch (IOException ignored) {

            return extractFromResources(context, R.drawable.icon_image);
        }
    }


    /**
     * ------  Return Operatios - Section
     */

    /**
     * @deprecated
     * This method will be remplaced
     * <p> Use {@link MyDrawable#returnNameFromResources(Context, int)} instead.
     *
     * @param context context
     * @param resourceDrawable integer resourceDrawable
     * @return name from resource
     */
    @Deprecated(since = "v1.0.11", forRemoval = true)
    public String extractNameFromResources(@NonNull Context context, int resourceDrawable) {

        return context.getResources().getResourceEntryName(resourceDrawable);
    }

    public String returnNameFromResources(@NonNull Context context, int resourceDrawable) {

        return context.getResources().getResourceEntryName(resourceDrawable);
    }

    /**
     * @deprecated
     * This method will be remplaced
     * <p> Use {@link MyDrawable#returnIdFromString(Context, String)} instead.
     *
     * @param context context
     * @param stringDrawable String stringDrawable
     * @return id from resource
     */
    @Deprecated(since = "v1.0.11", forRemoval = true)
    public int extractIdFromString(@NonNull Context context, String stringDrawable) {

        return context.getResources().getIdentifier(stringDrawable, "drawable", context.getPackageName());
    }

    public int returnIdFromString(@NonNull Context context, String stringDrawable) {

        return context.getResources().getIdentifier(stringDrawable, "drawable", context.getPackageName());
    }


    /**
     * ------  Uri - Section
     */

    public Uri getUriFromAsset(String nameImage, @Type String type) {

        return Uri.parse("file:///android_asset/" + nameImage + type);
    }


    /**
     * ------  Modifiers - Section
     */

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
