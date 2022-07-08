package com.vyping.business.utilities.models.products;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.RealData;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

public class Product {

    @Exclude
    public static final String TAG_CATEGORY = "Store", TAG_DESCRIPTION = "Description", TAG_IMAGE = "Image", TAG_NAME = "Name", TAG_SIZE = "Size";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_CATEGORY, TAG_DESCRIPTION, TAG_IMAGE, TAG_NAME, TAG_SIZE})
    public @interface Tags {}

    @Exclude
    public String Id;
    public String Category, Description, Image, Name;
    public HashMap<String, String> Sizes;


    /*----- Main Model -----*/
    
    public Product(@NonNull DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Category = realData.getString(TAG_CATEGORY);
        this.Description = realData.getString(TAG_DESCRIPTION);
        this.Image = realData.getString(TAG_IMAGE);
        this.Name = realData.getString(TAG_NAME);
        this.Sizes = realData.getHashMapString(TAG_SIZE);
    }
}
