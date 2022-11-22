package com.vyping.masterlibrary.models.images;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.RealData;
import com.vyping.masterlibrary.Json.MyJsonReader;

import org.json.JSONObject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Image {

    @Exclude
    public static final String TAG_NAME = "Name", TAG_TYPE = "Type", TAG_TOKEN = "Token", TAG_URL = "Url";
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_NAME, TAG_TYPE, TAG_TOKEN, TAG_URL})
    public @interface Tags {}

    public String Name, Type, Token, Url;


    /*----- Main Model -----*/

    public Image() {

        Name = "icon_image";
        Type = "";
        Token = "";
        Url = "";
    }

    public Image(@NonNull DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        Name = realData.getString(TAG_NAME);
        Type = realData.getString(TAG_TYPE);
        Token = realData.getString(TAG_TOKEN);
        Url = realData.getString(TAG_URL);
    }

    public Image(String id, @NonNull JSONObject jsonImage) {

        this.Name = new MyJsonReader().getJsonString(jsonImage, TAG_NAME);
        this.Type = new MyJsonReader().getJsonString(jsonImage, TAG_TYPE);
        this.Token = new MyJsonReader().getJsonString(jsonImage, TAG_TOKEN);
        this.Url = new MyJsonReader().getJsonString(jsonImage, TAG_URL);
    }
}
