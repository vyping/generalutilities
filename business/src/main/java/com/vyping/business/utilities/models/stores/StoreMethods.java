package com.vyping.business.utilities.models.stores;

import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.views.MyImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class StoreMethods {

    private final Store store;


    // ----- SetUp ----- //

    public StoreMethods(@NonNull Store store) {

        this.store = store;
    }


    // ----- Basic Methods ----- //

    public Store getStore()
    {
        return store;
    }

    public String getId()
    {
        return store.Id;
    }

    public String getName()
    {
        return store.Name;
    }

    public String getDescription()
    {
        return store.Description;
    }

    public String getImageName()
    {
        return store.ImageName;
    }

    public String getImageToken()
    {
        return store.ImageToken;
    }

    public String getPosition()
    {
        return store.Position;
    }



    // ----- compound Methods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(store.Name, store.Description));
    }

    /*----- Binding Methods -----*/

    @BindingAdapter(value={"imageFromAssets"}, requireAll=true)
    public static void loadImage(@NonNull ImageView view, String asset) {

        new LogCat("asset", asset);
        new MyImageView().putImageFromAssets(view, asset);
    }
}
