package com.vyping.libraries.utilities.models.products;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.vyping.libraries.utilities.definitions.Buckets.BUCKET_SHOP;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.vyping.libraries.utilities.definitions.Buckets;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.views.recyclerview.methods.ModelMethods;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductMethods extends ModelMethods<Product> {

    private final Product product;


    // ----- SetUp ----- //

    public ProductMethods(Product product) {

        super(product);

        this.product = product;
    }


    // ----- Basic ModelMethods ----- //

    public Product getProduct()
    {
        return product;
    }

    public String getName()
    {
        return product.Name;
    }

    public String getMark()
    {
        return product.Mark;
    }

    public String getDescription()
    {
        return product.Description;
    }

    public String getUnits()
    {
        return product.Units;
    }

    public int getSize()
    {
        return product.Size;
    }

    public int getPrevPrice()
    {
        return product.PrevPrice;
    }

    public int getPrice()
    {
        return product.Price;
    }

    public int getStockMin()
    {
        return product.StockMin;
    }

    public int getStock()
    {
        return product.Stock;
    }

    public int getStockMax()
    {
        return product.StockMax;
    }


    // ----- Compounds ModelMethods ----- //

    public String getUrlImage() {

        new LogCat("url", new Buckets().getMediaResource(BUCKET_SHOP, product.BarCode, product.Image));

        return new Buckets().getMediaResource(BUCKET_SHOP, product.BarCode, product.Image);
    }

    public String getQuantity() {

        return product.Size + product.Units;
    }

    public String getPrevValue() {

        return new MyString().formatToMoney(product.PrevPrice);
    }

    public String getValue() {

        return new MyString().formatToMoney(product.Price);
    }

    public String getDiscount() {

        if (product.PrevPrice != 0) {

            int porcentaje = (-1)*(100 - ((product.Price * 100) / product.PrevPrice));

            return porcentaje + "%";

        } else {

            return "";
        }
    }


    // ----- Binding ModelMethods ----- //

    @BindingAdapter("discountVisible")
    public static void getPrevValueVisibility(@NonNull View view, @NonNull String discount) {

        if (discount.equals("")) {

            view.setVisibility(GONE);

        } else {

            view.setVisibility(VISIBLE);
        }
    }


    // ----- Search ModelMethods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(product.BarCode, product.Image, product.Name, product.Mark, product.Description, product.Units, product.Size, product.PrevPrice, product.Price, product.Labels));
    }
}
