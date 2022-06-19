package com.vyping.masterlibrary.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.bumptech.glide.Glide;
import com.vyping.masterlibrary.Animations.MyAnimation;
import com.vyping.masterlibrary.Common.MyFile;
import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.resources.MyAsset;
import com.vyping.masterlibrary.resources.MyResource;
import com.vyping.masterlibrary.web.MyWeb;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class MyImageView {

    public int errorImage = R.drawable.icon_image;


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


    /**
     * -------- Put Image - Section
     */

    public void putImageFromWeb(@NonNull ImageView imageView, String url) {

        Context context = imageView.getContext();

        Glide.with(context).load(url).error(errorImage).into(imageView);
    }

    public void putImageFromResources(@NonNull ImageView imageView, int resource) {

        Context context = imageView.getContext();

        Glide.with(context).load(resource).error(errorImage).into(imageView);
    }

    public void putImageFromAssets(@NonNull ImageView imageView, String nameFile) {

        Context context = imageView.getContext();
        Uri uri = new MyAsset().getUri(nameFile);

        Glide.with(context).load(uri).error(errorImage).into(imageView);
    }

    public void putImageFromAssets(@NonNull ImageView imageView, String nameFile, @MyFile.Type String type) {

        String file = nameFile + type;

        putImageFromAssets(imageView, file);
    }

    public void putImageFromResourcesOrWeb(@NonNull ImageView imageView, int resource, String urlFile) {

        Context context = imageView.getContext();
        boolean exist =new MyResource().exist(context, resource);

        if (exist) {

            putImageFromResources(imageView, resource);

        } else {

            putImageFromWeb(imageView, urlFile);
        }
    }

    public void putImageFromAssetsOrWeb(@NonNull ImageView imageView, String nameFile, String urlFile) {

        Context context = imageView.getContext();
        boolean exist =new MyAsset().exists(context, nameFile);

        if (exist) {

            putImageFromAssets(imageView, nameFile);

        } else {

            putImageFromWeb(imageView, urlFile);
        }
    }

    public void putImageFromAssetsOrWeb(@NonNull ImageView imageView, String nameFile, @MyFile.Type String type, String urlFile) {

        String file = nameFile + type;

        putImageFromAssetsOrWeb(imageView, file, urlFile);
    }

    public void putImageFromWebOrAssets(@NonNull ImageView imageView, String urlFile, String nameFile) {

        Context context = imageView.getContext();
        Drawable drawable = new MyDrawable().extractFromAssets(context, nameFile);

        Glide.with(context).load(urlFile).error(drawable).into(imageView);
    }

    public void putImageFromWebOrAssets(@NonNull ImageView imageView, String urlFile, String nameFile, @MyFile.Type String type) {

        String file = nameFile + type;

        putImageFromWebOrAssets(imageView, urlFile, file);
    }

    public void putImageFromWebOrAssets2(@NonNull ImageView imageView, String urlFile, String nameFile) {

        boolean exist = new MyWeb().exist(urlFile);

        if (exist) {

            putImageFromWeb(imageView, urlFile);

        } else {

            putImageFromAssets(imageView, nameFile);
        }
    }

    public void putImageFromWebOrAssets2(@NonNull ImageView imageView, String urlFile, String nameFile, @MyFile.Type String type) {

        String file = nameFile + type;

        putImageFromWebOrAssets2(imageView, urlFile, file);
    }
}
