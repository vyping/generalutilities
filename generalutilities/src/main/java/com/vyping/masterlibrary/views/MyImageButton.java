package com.vyping.masterlibrary.views;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Images.MyColor;
import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.R;

public class MyImageButton {


    /**
     * -------- Created By Attributes - Section
     */

    @NonNull
    public ImageButton createByAttributes(@NonNull Context context, int attr, int style) {

        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        final ImageButton imageButton = new ImageButton(wrapper, null, attr, style);

        Paris.style(imageButton).apply(style);

        return imageButton;
    }

    @NonNull
    public ImageButton createByAttributes(@NonNull Context context, int attr, int style, View.OnClickListener listener) {

        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        final ImageButton imageButton = new ImageButton(wrapper, null, attr, style);
        imageButton.setOnClickListener(listener);

        Paris.style(imageButton).apply(style);

        return imageButton;
    }

    @NonNull
    public ImageButton createByAttributes(@NonNull Context context, int attr, int style, int resDrawable) {

        final Drawable drawable = new MyDrawable().extractFromResources(context, resDrawable);
        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        final ImageButton imageButton = new ImageButton(wrapper, null, attr, style);
        imageButton.setImageDrawable(drawable);

        Paris.style(imageButton).apply(style);

        return imageButton;
    }

    @NonNull
    public ImageButton createByAttributes(@NonNull Context context, int attr, int style, int resDrawable, View.OnClickListener listener) {

        final Drawable drawable = new MyDrawable().extractFromResources(context, resDrawable);
        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        final ImageButton imageButton = new ImageButton(wrapper, null, attr, style);
        Paris.style(imageButton).apply(style);

        imageButton.setImageDrawable(drawable);
        imageButton.setOnClickListener(listener);

        return imageButton;
    }

    @NonNull
    public ImageButton createByAttributes(@NonNull Context context, int attr, int style, Drawable drawable) {

        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        final ImageButton imageButton = new ImageButton(wrapper, null, attr, style);
        imageButton.setImageDrawable(drawable);

        Paris.style(imageButton).apply(style);

        return imageButton;
    }

    @NonNull
    public ImageButton createByAttributes(@NonNull Context context, int attr, int style, Drawable drawable, View.OnClickListener listener) {

        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        final ImageButton imageButton = new ImageButton(wrapper, null, attr, style);
        imageButton.setImageDrawable(drawable);
        imageButton.setOnClickListener(listener);

        Paris.style(imageButton).apply(style);

        return imageButton;
    }


    /**
     * -------- ImageButtons from Dialogs - Section
     */

    public ImageButton setView(@NonNull Dialog dialog, int view, int visibility) {

        final ImageButton imageButton = dialog.findViewById(view);
        imageButton.setVisibility(visibility);

        return imageButton;
    }

    public ImageButton setImage(@NonNull Dialog dialog, int view, int resDrawable, int color) {

        final Context context = dialog.getContext();
        final Drawable drawable = new MyDrawable().changeDrawableColor(context, resDrawable, color);

        final ImageButton imageButton = dialog.findViewById(view);
        imageButton.setImageDrawable(drawable);
        imageButton.setVisibility(VISIBLE);

        return imageButton;
    }

    public ImageButton setImage(@NonNull Dialog dialog, int view, Drawable imageDrawable, int color) {

        final Context context = dialog.getContext();
        final Drawable drawable = new MyDrawable().changeDrawableColor(context, imageDrawable, color);

        final ImageButton imageButton = dialog.findViewById(view);
        imageButton.setImageDrawable(drawable);
        imageButton.setVisibility(VISIBLE);

        return imageButton;
    }

    public ImageButton setImageListener(@NonNull Dialog dialog, int view, View.OnClickListener listener, int resDrawable, int color) {

        final Context context = dialog.getContext();
        final Drawable drawable = new MyDrawable().changeDrawableColor(context, resDrawable, color);

        final ImageButton imageButton = dialog.findViewById(view);
        imageButton.setImageDrawable(drawable);
        imageButton.setOnClickListener(listener);
        imageButton.setVisibility(VISIBLE);

        return imageButton;
    }

    public ImageButton setImageListener(@NonNull Dialog dialog, int view, View.OnClickListener listener, Drawable imageDrawable, int color) {

        final Context context = dialog.getContext();
        final Drawable drawable = new MyDrawable().changeDrawableColor(context, imageDrawable, color);

        final ImageButton imageButton = dialog.findViewById(view);
        imageButton.setImageDrawable(drawable);
        imageButton.setOnClickListener(listener);
        imageButton.setVisibility(VISIBLE);

        return imageButton;
    }

    public ImageButton setImageBackgroundListener(@NonNull Dialog dialog, int view, View.OnClickListener listener, int imageDrawable, int colorImage, int imageBackground, int colorBackground) {

        final Context context = dialog.getContext();
        final Drawable background = new MyDrawable().changeDrawableColor(context, imageBackground, colorBackground);
        final Drawable image = new MyDrawable().changeDrawableColor(context, imageDrawable, colorImage);

        final ImageButton imageButton = dialog.findViewById(view);
        imageButton.setBackground(background);
        imageButton.setImageDrawable(image);
        imageButton.setOnClickListener(listener);
        imageButton.setVisibility(VISIBLE);

        return imageButton;
    }

    public ImageButton setImageBackgroundListener(@NonNull Dialog dialog, int view, View.OnClickListener listener, Drawable resDrawable, int colorImage, Drawable resBackground, int colorBackground) {

        final Context context = dialog.getContext();
        final Drawable background = new MyDrawable().changeDrawableColor(context, resDrawable, colorBackground);
        final Drawable image = new MyDrawable().changeDrawableColor(context, resBackground, colorImage);

        final ImageButton imageButton = dialog.findViewById(view);
        imageButton.setBackground(background);
        imageButton.setImageDrawable(image);
        imageButton.setOnClickListener(listener);
        imageButton.setVisibility(VISIBLE);

        return imageButton;
    }


    /**
     * -------- ImageButtons from ParentViews - Section
     */

    public ImageButton setView(@NonNull View parentView, int view, int visibility) {

        final ImageButton imageButton = parentView.findViewById(view);
        imageButton.setVisibility(visibility);

        return imageButton;
    }

    public ImageButton setImage(@NonNull View parentView, int view, Drawable drawable, int color) {

        final Context context = parentView.getContext();
        final Drawable modDrawable = new MyDrawable().changeDrawableColor(context, drawable, color);

        final ImageButton imageButton = parentView.findViewById(view);
        imageButton.setImageDrawable(modDrawable);
        imageButton.setVisibility(VISIBLE);

        return imageButton;
    }

    public ImageButton setImage(@NonNull View parentView, int view, int imageDrawable, int color) {

        final Context context = parentView.getContext();
        final Drawable drawable = new MyDrawable().changeDrawableColor(context, imageDrawable, color);

        final ImageButton imageButton = parentView.findViewById(view);
        imageButton.setImageDrawable(drawable);
        imageButton.setVisibility(VISIBLE);

        return imageButton;
    }

    public ImageButton setImageListener(@NonNull View parentView, int view, View.OnClickListener listener, Drawable drawable) {

        final ImageButton imageButton = parentView.findViewById(view);

        if (drawable != null) {

            final Context context = parentView.getContext();
            int color = new MyColor().extractFromResources(context, R.color.colorTransparente);

            imageButton.setBackgroundColor(color);
            imageButton.setImageDrawable(drawable);
            imageButton.setOnClickListener(listener);
            imageButton.setVisibility(VISIBLE);

        } else {

            imageButton.setVisibility(GONE);
        }

        return imageButton;
    }

    public ImageButton setImageListener(@NonNull View parentView, int view, View.OnClickListener listener, int imageDrawable) {

        if (imageDrawable != 0) {

            final Context context = parentView.getContext();
            final Drawable drawable = new MyDrawable().extractFromResources(context, imageDrawable);

            return setImageListener(parentView, view, listener, drawable);

        } else {

            final ImageButton imageButton = parentView.findViewById(view);
            imageButton.setVisibility(GONE);

            return imageButton;
        }
    }

    public ImageButton setImageListener(@NonNull View parentView, int view, View.OnClickListener listener, Drawable drawable, int color) {

        final Context context = parentView.getContext();
        final Drawable modDrawable = new MyDrawable().changeDrawableColor(context, drawable, color);

        final ImageButton imageButton = parentView.findViewById(view);
        imageButton.setImageDrawable(modDrawable);
        imageButton.setOnClickListener(listener);
        imageButton.setVisibility(VISIBLE);

        return imageButton;
    }

    public ImageButton setImageListener(@NonNull View parentView, int view, View.OnClickListener listener, int imageDrawable, int color) {

        final Context context = parentView.getContext();
        final Drawable drawable = new MyDrawable().changeDrawableColor(context, imageDrawable, color);

        final ImageButton imageButton = parentView.findViewById(view);
        imageButton.setImageDrawable(drawable);
        imageButton.setOnClickListener(listener);
        imageButton.setVisibility(VISIBLE);

        return imageButton;
    }

    public ImageButton setImageBackgroundListener(@NonNull View parentView, int view, View.OnClickListener listener, Drawable resBackground, Drawable resDrawable) {

        final ImageButton imageButton = parentView.findViewById(view);

        if (resDrawable != null) {

            imageButton.setBackground(resBackground);
            imageButton.setImageDrawable(resDrawable);
            imageButton.setOnClickListener(listener);
            imageButton.setVisibility(VISIBLE);

        } else {

            imageButton.setVisibility(GONE);
        }

        return imageButton;
    }

    public ImageButton setImageBackgroundListener(@NonNull View parentView, int view, View.OnClickListener listener, int imageBackground, int imageDrawable) {

        if (imageDrawable != 0 && imageBackground != 0) {

            final Context context = parentView.getContext();
            final Drawable background = new MyDrawable().extractFromResources(context, imageBackground);
            final Drawable image = new MyDrawable().extractFromResources(context, imageDrawable);

            return setImageBackgroundListener(parentView, view, listener, background, image);

        } else {

            final ImageButton imageButton = parentView.findViewById(view);
            imageButton.setVisibility(GONE);

            return imageButton;
        }
    }

    public ImageButton setImageBackgroundListener(@NonNull View parentView, int view, View.OnClickListener listener, Drawable resDrawable, int colorImage, Drawable resBackground, int colorBackground) {

        final Context context = parentView.getContext();
        final Drawable background = new MyDrawable().changeDrawableColor(context, resDrawable, colorBackground);
        final Drawable image = new MyDrawable().changeDrawableColor(context, resBackground, colorImage);

        final ImageButton imageButton = parentView.findViewById(view);
        imageButton.setBackground(background);
        imageButton.setImageDrawable(image);
        imageButton.setOnClickListener(listener);
        imageButton.setVisibility(VISIBLE);

        return imageButton;
    }

    public ImageButton setImageBackgroundListener(@NonNull View parentView, int view, View.OnClickListener listener, int imageDrawable, int colorImage, int imageBackground, int colorBackground) {

        final Context context = parentView.getContext();
        final Drawable background = new MyDrawable().changeDrawableColor(context, imageBackground, colorBackground);
        final Drawable image = new MyDrawable().changeDrawableColor(context, imageDrawable, colorImage);

        final ImageButton imageButton = parentView.findViewById(view);
        imageButton.setBackground(background);
        imageButton.setImageDrawable(image);
        imageButton.setOnClickListener(listener);
        imageButton.setVisibility(VISIBLE);

        return imageButton;
    }
}
