package com.vyping.masterlibrary.Firebase;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;
import java.util.HashMap;

public class RealModel<T> extends RealData {

    public HashMap<String, T> hashModel;
    public Interfase<T> interfase;


    // ----- SetUp ----- //

    public RealModel(@NonNull DataSnapshot dataSnapshot) {

        super(dataSnapshot);
    }


    // ----- Chained Methods ----- //

    public RealModel<T> setInterfase(Interfase<T> interfase) {

        this.interfase = interfase;

        return this;
    }

    /*----- Methods -----*/

    public HashMap<String, T> mapModels(String child) {

        this.hashModel = new HashMap<>();

        for (DataSnapshot dataModel : dataSnapshot.child(child).getChildren()) {

            T model = interfase.newModel(dataModel);

            this.hashModel.put(dataModel.getKey(), model);
        }

        return this.hashModel;
    }

    public interface Interfase<T> {

        T newModel(DataSnapshot dataModel);
    }
}