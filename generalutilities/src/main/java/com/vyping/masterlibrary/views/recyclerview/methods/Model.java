package com.vyping.masterlibrary.views.recyclerview.methods;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.MyRealtimeReader;

import org.json.JSONObject;

public class Model {

    @Exclude
    public String Id;


    /*----- Main Model -----*/

    public Model() {}

    public Model(@NonNull DataSnapshot dataSnapshot) {

        this.Id = new MyRealtimeReader().getKeyString(dataSnapshot);;
    }

    public Model(String Id, @NonNull JSONObject jsonObject) {

        this.Id = Id;
    }
}
