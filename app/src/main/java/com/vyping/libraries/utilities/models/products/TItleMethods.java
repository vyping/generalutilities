package com.vyping.libraries.utilities.models.products;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.vyping.libraries.utilities.definitions.Buckets.BUCKET_SHOP;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.vyping.libraries.utilities.definitions.Buckets;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.views.recyclerview.methods.ModelMethods;

import java.util.ArrayList;
import java.util.Arrays;

public class TItleMethods extends ProductMethods {

    private final Product product;


    // ----- SetUp ----- //

    public TItleMethods(Product product) {

        super(product);

        this.product = product;
    }

    public String getName()
    {
        return product.Name;
    }
}
