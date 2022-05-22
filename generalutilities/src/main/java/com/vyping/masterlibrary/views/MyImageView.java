package com.vyping.masterlibrary.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Animations.MyAnimation;
import com.vyping.masterlibrary.Images.MyDrawable;

public class MyImageView {


    /**
     * -------- Created By Attributes - Section
     */

    @NonNull
    public ImageView createByAttributes(@NonNull Context context, int attr, int style, Drawable drawable) {

        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        ImageView imageView = new ImageView(wrapper, null, attr, style);
        imageView.setImageDrawable(drawable);

        Paris.style(imageView).apply(style);

        return imageView;
    }

    @NonNull
    public ImageView createByAttributes(@NonNull Context context, int attr, int style, int imageResource) {

        Drawable drawable = new MyDrawable().extractFromResources(context, imageResource);

        return createByAttributes(context, attr, style, drawable);
    }


    /**
     * -------- ImageView from Context - Section
     */

    public ImageView createFromContext(@NonNull Context context, int view, int visibility) {

        ImageView imageView = ((Activity) context).findViewById(view);
        imageView.setVisibility(visibility);

        return imageView;
    }

    public ImageView createFromContext(@NonNull Context context, int view, int imageResource, int visibility) {

        ImageView imageView = ((Activity) context).findViewById(view);
        imageView.setImageResource(imageResource);
        imageView.setVisibility(visibility);

        return imageView;
    }

    public ImageView createFromContext(@NonNull Context context, int view, Drawable drawable, int visibility) {

        ImageView imageView = ((Activity) context).findViewById(view);
        imageView.setImageDrawable(drawable);
        imageView.setVisibility(visibility);

        return imageView;
    }

    public ImageView createFromContext(@NonNull Context context, int view, View.OnClickListener listener, int imageResource, int color) {

        Drawable drawable = new MyDrawable().changeDrawableColor(context, imageResource, color);

        ImageView imageView = ((Activity) context).findViewById(view);
        imageView.setImageDrawable(drawable);
        imageView.setOnClickListener(listener);

        new MyAnimation().AlphaAmpliate(imageView, 1000);

        return imageView;
    }

    public ImageView createFromContext(@NonNull Context context, int view, View.OnClickListener listener, Drawable drawable, int color) {

        ImageView imageView = ((Activity) context).findViewById(view);
        imageView.setImageDrawable(drawable);
        imageView.setOnClickListener(listener);

        return imageView;
    }

    public ImageView createFromContext(@NonNull Context context, int view, View.OnClickListener listener, int imageResource, int visibility, int color) {

        ImageView imageView = ((Activity) context).findViewById(view);
        imageView.setImageResource(imageResource);
        imageView.setVisibility(visibility);
        imageView.setOnClickListener(listener);

        return imageView;
    }

    public ImageView createFromContext(@NonNull Context context, int view, View.OnClickListener listener, Drawable drawable, int visibility, int color) {

        ImageView imageView = ((Activity) context).findViewById(view);
        imageView.setImageDrawable(drawable);
        imageView.setVisibility(visibility);
        imageView.setOnClickListener(listener);

        return imageView;
    }


    /**
     * -------- ImageView from Dialog - Section
     */

    public ImageView createForDialog(@NonNull Dialog dialog, int view, int visibility) {

        ImageView imageView = dialog.findViewById(view);
        imageView.setVisibility(visibility);

        return imageView;
    }

    public ImageView createForDialog(@NonNull Dialog dialog, int view, int imageResource, int visibility) {

        ImageView imageView = dialog.findViewById(view);
        imageView.setImageResource(imageResource);
        imageView.setVisibility(visibility);

        return imageView;
    }

    public ImageView createForDialog(@NonNull Dialog dialog, int view, View.OnClickListener listener, int imageResource) {

        ImageView imageView = dialog.findViewById(view);
        imageView.setImageResource(imageResource);
        imageView.setOnClickListener(listener);

        return imageView;
    }

    public ImageView createForDialog(@NonNull Dialog dialog, int view, View.OnClickListener listener, int imageResource, int visibility) {

        ImageView imageView = dialog.findViewById(view);
        imageView.setImageResource(imageResource);
        imageView.setVisibility(visibility);
        imageView.setOnClickListener(listener);

        return imageView;
    }
}
