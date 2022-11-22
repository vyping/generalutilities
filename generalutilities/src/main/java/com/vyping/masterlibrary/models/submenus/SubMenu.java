package com.vyping.masterlibrary.models.submenus;

import static java.lang.Boolean.FALSE;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.RealData;
import com.vyping.masterlibrary.Json.MyJsonReader;
import com.vyping.masterlibrary.models.images.Image;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class SubMenu implements Serializable {

    @Exclude
    public static final String TAG_POSITION = "Position", TAG_IMAGE = "Image", TAG_TITLE = "Title", TAG_DESTIN = "Destin", TAG_MAIN = "Main";
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_POSITION, TAG_IMAGE, TAG_TITLE, TAG_DESTIN})
    public @interface Tags {}

    @Exclude
    public String Id;
    public String Position, Title, Destin;
    public Image Image;
    public Boolean Main;
    @Exclude
    public Boolean Selected;


    /*----- Main Model -----*/

    public SubMenu() {

        this.Id = "";
        this.Position = "";
        this.Title = "";
        this.Destin = "";
        this.Image = new Image();
        this.Main = FALSE;
        this.Selected = FALSE;
    }

    public SubMenu(@NonNull DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Position = realData.getString(TAG_POSITION);
        this.Title = realData.getString(TAG_TITLE);
        this.Destin = realData.getString(TAG_DESTIN);
        this.Image = new Image(dataSnapshot.child(TAG_IMAGE));
        this.Main = realData.getBoolean(TAG_MAIN);
        this.Selected = FALSE;
    }

    public SubMenu(String id, @NonNull JSONObject jsonSubMenu){

        this.Id = id;
        this.Position = new MyJsonReader().getJsonString(jsonSubMenu, TAG_POSITION);
        this.Title = new MyJsonReader().getJsonString(jsonSubMenu, TAG_TITLE);
        this.Destin = new MyJsonReader().getJsonString(jsonSubMenu, TAG_DESTIN);
        this.Main = new MyJsonReader().getJsonBoolean(jsonSubMenu, TAG_MAIN);
        this.Selected = FALSE;

        try {

            this.Image = new Image(id, jsonSubMenu.getJSONObject(TAG_IMAGE));

        } catch (JSONException e) {

            this.Image = new Image();
        }
    }
}

