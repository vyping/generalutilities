package com.vyping.masterlibrary.models.categories;

import static java.lang.Boolean.FALSE;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.RealModel;
import com.vyping.masterlibrary.models.images.Image;
import com.vyping.masterlibrary.models.products.Product;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

public class Category implements Serializable {

    @Exclude
    public static final String TAG_NAME = "Name", TAG_DESCRIPTION = "Description", TAG_IMAGE = "Image", TAG_POSITION = "Position", TAG_PRODUCTS = "Products";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_NAME, TAG_DESCRIPTION, TAG_IMAGE, TAG_POSITION, TAG_PRODUCTS})
    public @interface Tags {}

    @Exclude
    public String Id;
    public String Name, Description, Position;
    public com.vyping.masterlibrary.models.images.Image Image;
    public HashMap<String, Product> Products;

    @Exclude
    public boolean selected;


    /*----- Main Model -----*/

    public Category() {

        this.Id = "";
        this.Name = "";
        this.Description = "";
        this.Position = "1";
        this.Products = new HashMap<>();
        this.Image = new Image();
        this.selected = FALSE;
    }

    public Category(@NonNull DataSnapshot dataSnapshot) {

        RealModel<Product> realData = new RealModel<Product>(dataSnapshot).setInterfase(new RealModel.Interfase<>() {

            @Override @NonNull
            public Product newModel(DataSnapshot dataModel) {

                return new Product(dataModel);
            }

            private void DummyVoid() {};
        });

        this.Id = realData.getKeyString();
        this.Name = realData.getString(TAG_NAME);
        this.Description = realData.getString(TAG_DESCRIPTION);
        this.Position = realData.getString(TAG_POSITION);
        this.Products = realData. mapModels(TAG_PRODUCTS);
        this.Image = new Image(dataSnapshot.child(TAG_IMAGE));
        this.selected = FALSE;
    }
}
