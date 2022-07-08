package com.vyping.business.utilities.models.subcategories;

import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Firebase.RealData;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class SubCategory {

    public static final String TAG_POSITION = "Position", TAG_DESCRIPTION = "Description", TAG_IMAGE = "Image", TAG_INDICATOR = "Indicator", TAG_STORES = "Stores";
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_POSITION, TAG_DESCRIPTION, TAG_IMAGE, TAG_INDICATOR, TAG_STORES})
    public @interface Tags {}

    public String Id, Position, Description, Image;
    public int Indicator;
    public ArrayList<DataSnapshot> Stores;


    /*----- Main Model -----*/

    public SubCategory(DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Position = realData.getString(TAG_POSITION);
        this.Description = realData.getString(TAG_DESCRIPTION);
        this.Image = realData.getString(TAG_IMAGE);
        this.Indicator = realData.getInteger(TAG_INDICATOR);
        this.Stores = realData.getArrayDataSnapshot(TAG_STORES);
    }
}
