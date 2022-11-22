package com.vyping.masterlibrary.models.subcategories;

import static java.lang.Boolean.FALSE;

import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.RealData;
import com.vyping.masterlibrary.models.images.Image;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class SubCategory {

    @Exclude
    public static final String TAG_POSITION = "Position", TAG_NAME = "Name", TAG_IMAGE = "Image", TAG_DESCRIPTION = "Description", TAG_PRODUCTS = "Products";
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_POSITION, TAG_NAME, TAG_IMAGE, TAG_DESCRIPTION, TAG_PRODUCTS})
    public @interface Tags {}

    @Exclude
    public String Id;
    public String Position, Name, Description;
    public com.vyping.masterlibrary.models.images.Image Image;

    @Exclude
    public ArrayList<DataSnapshot> Products;
    @Exclude
    public boolean selected;

    /*----- Main Model -----*/

    public SubCategory() {

        this.Id = "";
        this.Name = "";
        this.Description = "";
        this.Position = "1";
        this.Products = new ArrayList<>();
        this.Image = new Image();
        this.selected = FALSE;
    }

    public SubCategory(DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Position = realData.getString(TAG_POSITION);
        this.Name = realData.getString(TAG_NAME);
        this.Description = realData.getString(TAG_DESCRIPTION);
        this.Products = realData.getArrayDataSnapshot(TAG_PRODUCTS);
        this.Image = new Image(dataSnapshot.child(TAG_IMAGE));
        this.selected = FALSE;
    }
}
