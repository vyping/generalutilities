package com.vyping.business.utilities.models.stores;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Firebase.RealData;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class Store {

    public static final String TAG_NAME = "Name", TAG_DESCRIPTION = "Description", TAG_IMAGE_NAME = "ImageName", TAG_IMAGE_TOKEN = "ImageToken", TAG_POSITION = "Position";
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_NAME, TAG_DESCRIPTION, TAG_IMAGE_NAME, TAG_IMAGE_TOKEN, TAG_POSITION})
    public @interface Tags {}

    public String Id;
    public String Name, Description, ImageName, ImageToken, Position;


    /*----- Main Model -----*/
    
    public Store(@NonNull DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Name = realData.getString(TAG_NAME);
        this.Description = realData.getString(TAG_DESCRIPTION);
        this.ImageName = realData.getString(TAG_IMAGE_NAME);
        this.ImageToken = realData.getString(TAG_IMAGE_TOKEN);
        this.Position = realData.getString(TAG_POSITION);
    }
}
