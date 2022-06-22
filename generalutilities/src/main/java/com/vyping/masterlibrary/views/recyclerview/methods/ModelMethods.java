package com.vyping.masterlibrary.views.recyclerview.methods;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.vyping.masterlibrary.views.MyImageView;

public class ModelMethods<T> extends BaseObservable {

    private final T model;


    // ----- SetUp ----- //

    public ModelMethods(T model)
    {
        this.model = model;
    }


    // ----- Binding ModelMethods ----- //

    @BindingAdapter(value={"imageFromAssets", "imageFromWeb"}, requireAll=true)
    public static void loadImage(@NonNull ImageView view, String asset, String url) {

        new MyImageView().putImageFromAssetsOrWeb(view, asset, url);
    }
}
