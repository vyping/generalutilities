package com.vyping.masterlibrary.Views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Animations.Animations;
import com.vyping.masterlibrary.Images.MyDrawable;

public class ImageViews {


    /**
     * -------- ImageView from Context - Section
     */

    public ImageView setImageView(@NonNull Context context, int view, int visibility) {

        ImageView imageView = ((Activity) context).findViewById(view);
        imageView.setVisibility(visibility);

        return imageView;
    }

    public ImageView setImageView(@NonNull Context context, int view, int imageResource, int visibility) {

        ImageView imageView = ((Activity) context).findViewById(view);
        imageView.setImageResource(imageResource);
        imageView.setVisibility(visibility);

        return imageView;
    }

    public ImageView setImageView(@NonNull Context context, int view, Drawable drawable, int visibility) {

        ImageView imageView = ((Activity) context).findViewById(view);
        imageView.setImageDrawable(drawable);
        imageView.setVisibility(visibility);

        return imageView;
    }

    public ImageView setImageView(@NonNull Context context, int view, View.OnClickListener listener, int imageResource, int color) {

        Drawable drawable = new MyDrawable().changeDrawableColor(context, imageResource, color);

        ImageView imageView = ((Activity) context).findViewById(view);
        imageView.setImageDrawable(drawable);
        imageView.setOnClickListener(listener);

        new Animations().AlphaAmpliate(imageView, 1000);

        return imageView;
    }

    public ImageView setImageView(@NonNull Context context, int view, View.OnClickListener listener, Drawable drawable, int color) {

        ImageView imageView = ((Activity) context).findViewById(view);
        imageView.setImageDrawable(drawable);
        imageView.setOnClickListener(listener);

        return imageView;
    }

    public ImageView setImageView(@NonNull Context context, int view, View.OnClickListener listener, int imageResource, int visibility, int color) {

        ImageView imageView = ((Activity) context).findViewById(view);
        imageView.setImageResource(imageResource);
        imageView.setVisibility(visibility);
        imageView.setOnClickListener(listener);

        return imageView;
    }

    public ImageView setImageView(@NonNull Context context, int view, View.OnClickListener listener, Drawable drawable, int visibility, int color) {

        ImageView imageView = ((Activity) context).findViewById(view);
        imageView.setImageDrawable(drawable);
        imageView.setVisibility(visibility);
        imageView.setOnClickListener(listener);

        return imageView;
    }


    /**
     * -------- ImageView from Dialog - Section
     */

    public ImageView setImageView(@NonNull Dialog dialog, int view, int visibility) {

        ImageView imageView = dialog.findViewById(view);
        imageView.setVisibility(visibility);

        return imageView;
    }

    public ImageView setImageView(@NonNull Dialog dialog, int view, int imageResource, int visibility) {

        ImageView imageView = dialog.findViewById(view);
        imageView.setImageResource(imageResource);
        imageView.setVisibility(visibility);

        return imageView;
    }

    public ImageView setImageView(@NonNull Dialog dialog, int view, View.OnClickListener listener, int imageResource) {

        ImageView imageView = dialog.findViewById(view);
        imageView.setImageResource(imageResource);
        imageView.setOnClickListener(listener);

        return imageView;
    }

    public ImageView setImageView(@NonNull Dialog dialog, int view, View.OnClickListener listener, int imageResource, int visibility) {

        ImageView imageView = dialog.findViewById(view);
        imageView.setImageResource(imageResource);
        imageView.setVisibility(visibility);
        imageView.setOnClickListener(listener);

        return imageView;
    }
}
