package com.vyping.masterlibrary.Images;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.vyping.masterlibrary.Common.MyFile;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.resources.MyAsset;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyDrawable {

    public int errorImage = R.drawable.icon_image;


    /**
     * ------  Extract Operatios - Section
     */

    public Drawable getErrorImage(Context context) {

        return extractFromResources(context, errorImage);
    }

    public Drawable extractFromString(Context context, @NonNull String stringDrawable) {

        if (!stringDrawable.equals("")) {

            int resDrawable = 0;

            try {

                resDrawable = context.getResources().getIdentifier(stringDrawable, "drawable", context.getPackageName());

                return ContextCompat.getDrawable(context, resDrawable);

            } catch (Exception ignored) {

                try {

                    resDrawable = context.getResources().getIdentifier(stringDrawable, "drawable", "com.vyping.masterlibrary");

                    return ContextCompat.getDrawable(context, resDrawable);

                } catch (Exception ignored2) {

                    return getErrorImage(context);
                }
            }

        } else {

            return extractFromResources(context, errorImage);
        }
    }

    public Drawable extractFromString(@NonNull View view, @NonNull String stringDrawable) {

        Context context = view.getContext();

        return extractFromString(context, stringDrawable);
    }

    public Drawable extractFromResources(Context context, int resourceDrawable) {

        if (resourceDrawable != 0) {

            return ContextCompat.getDrawable(context, resourceDrawable);

        } else {

            return ContextCompat.getDrawable(context, errorImage);
        }
    }

    public Drawable extractFromResources(@NonNull View view, int resourceDrawable) {

        Context context = view.getContext();

        if (resourceDrawable != 0) {

            return ContextCompat.getDrawable(context, resourceDrawable);

        } else {

            return ContextCompat.getDrawable(context, errorImage);
        }
    }

    public Drawable extractFromAssets(@NonNull Context context, @NonNull String stringDrawable) {

        try {

            return Drawable.createFromStream(context.getAssets().open(stringDrawable), null);

        } catch (IOException ignored) {

            return extractFromResources(context, errorImage);
        }
    }

    public Drawable extractFromAssets(@NonNull Context context, @NonNull String stringDrawable, @MyFile.Type String type) {

        try {

            return Drawable.createFromStream(context.getAssets().open(stringDrawable + type), null);

        } catch (IOException ignored) {

            return extractFromResources(context, errorImage);
        }
    }

    public Drawable extractFromAssets(@NonNull View view, @NonNull String stringDrawable) {

        Context context = view.getContext();

        try {

            return Drawable.createFromStream(context.getAssets().open(stringDrawable), null);

        } catch (IOException ignored) {

            return extractFromResources(context, errorImage);
        }
    }

    public Drawable extractFromAssets(@NonNull View view, @NonNull String asset, @MyFile.Type String type) {

        Context context = view.getContext();

        InputStream inputStream = new MyAsset().open(context, asset, type);
        
        if (inputStream != null) {

            return Drawable.createFromStream(inputStream, null);

        } else {

            return extractFromResources(context, errorImage);
        }
    }

    public Drawable extractFromUrl(@NonNull Context context, String url) {

        try {

            HttpURLConnection connection = (HttpURLConnection) new URL(url).openConnection();
            connection.connect();

            InputStream input = connection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(input);

            return new BitmapDrawable(Resources.getSystem(), bitmap);

        } catch (IOException e) {

            return extractFromResources(context, errorImage);
        }
    }

    public Drawable extractFromUrl(@NonNull View view, String url) {

        Context context = view.getContext();

        return extractFromUrl(context, url);
    }


    /**
     * ------  Return Operatios - Section
     */

    public String extractNameFromResources(@NonNull Context context, int resourceDrawable) {

        return context.getResources().getResourceEntryName(resourceDrawable);
    }

    public String returnNameFromResources(@NonNull Context context, int resourceDrawable) {

        return context.getResources().getResourceEntryName(resourceDrawable);
    }

    public int extractIdFromString(@NonNull Context context, String stringDrawable) {

        return context.getResources().getIdentifier(stringDrawable, "drawable", context.getPackageName());
    }

    public int returnIdFromString(@NonNull Context context, String stringDrawable) {

        return context.getResources().getIdentifier(stringDrawable, "drawable", context.getPackageName());
    }


    /**
     * ------  Uri - Section
     */

    public Uri getUriFromAsset(String nameImage, @MyFile.Type String type) {

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


    /**
     * ------  View  - Section
     */

    public Drawable toInsertOnButton(Context context, int resourceDrawable) {

        Drawable drawable = extractFromResources(context, resourceDrawable);
        drawable.setBounds(0, 0, 60, 60);

        return drawable;
    }

    public Drawable toInsertOnButton(Context context, int resourceDrawable, int resourceColor) {

        int color = ContextCompat.getColor(context, resourceColor);

        Drawable drawable = extractFromResources(context, resourceDrawable);
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable, color);

        wrappedDrawable.setBounds(0, 0, 60, 60);

        return wrappedDrawable;
    }

    public Drawable toInsertOnButton(Context context, int resourceDrawable, int resourceColor, int size) {

        int color = ContextCompat.getColor(context, resourceColor);

        Drawable drawable = extractFromResources(context, resourceDrawable);
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTint(wrappedDrawable, color);

        wrappedDrawable.setBounds(0, 0, size, size);

        return wrappedDrawable;
    }
}
