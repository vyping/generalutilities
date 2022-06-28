package com.vyping.masterlibrary.views.recyclerview.methods;

import androidx.annotation.NonNull;

import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.RealData;

import org.json.JSONObject;

public class Model {

    @Exclude
    public String Id;


    /*----- Main Model -----*/

    public Model() {}

    public Model(@NonNull RealData realData) {

        this.Id = realData.getKeyString();
    }

    public Model(String Id, @NonNull JSONObject jsonObject) {

        this.Id = Id;
    }
}
