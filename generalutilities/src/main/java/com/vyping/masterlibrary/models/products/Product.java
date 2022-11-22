package com.vyping.masterlibrary.models.products;

import static java.lang.Boolean.FALSE;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.RealData;
import com.vyping.masterlibrary.models.images.Image;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Product {

    @Exclude
    public static final String TAG_POSITION = "Position", TAG_NAME = "Name", TAG_IMAGE = "Image", TAG_PRICE = "Price", TAG_SIZE = "Size", TAG_DESCRIPTION = "Description", TAG_POTATOES = "Potatoes";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_POSITION, TAG_NAME, TAG_IMAGE, TAG_PRICE, TAG_SIZE, TAG_DESCRIPTION, TAG_POTATOES})
    public @interface Tags {}

    @Exclude
    public String Id;
    public String Position, Name, Price, Size, Description;
    public int Potatoes;
    public com.vyping.masterlibrary.models.images.Image Image;
    @Exclude
    public boolean selected;


    /*----- Main Model -----*/

    public Product() {

        this.Id = "";
        this.Position = "1";
        this.Name = "";
        this.Price = "";
        this.Size = "";
        this.Description = "";
        this.Potatoes = 0;
        this.Image = new Image();
        this.selected = FALSE;
    }

    public Product(@NonNull DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Position = realData.getString(TAG_POSITION);
        this.Name = realData.getString(TAG_NAME);
        this.Price = realData.getString(TAG_PRICE);
        this.Size = realData.getString(TAG_SIZE);
        this.Description = realData.getString(TAG_DESCRIPTION);
        this.Potatoes = realData.getIntegerOrDefault(TAG_POTATOES, 0);
        this.Image = new Image(dataSnapshot.child(TAG_IMAGE));
        this.selected = FALSE;
    }
}
