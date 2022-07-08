package com.vyping.business.utilities.models.stores;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.adapters.spinner.handler.SpinnerHandlerInterfase;

import java.util.ArrayList;

public class StoreHandler implements SpinnerHandlerInterfase<StoreMethods> {


    // ----- SetUp ----- //

    public StoreHandler() {}


    // ----- ModelMethods ----- //

    @Override
    public String getIndex(@NonNull StoreMethods store) {

        return store.getPosition();
    }

    @Override
    public String getIndex(DataSnapshot dataSnapshot) {

        return new Store(dataSnapshot).Position;
    }

    @Override
    public StoreMethods getMethod(DataSnapshot dataSnapshot) {

        Store store = new Store(dataSnapshot);

        return new StoreMethods(store);
    }

    @Override
    public ArrayList<Object> getSearchParameters(@NonNull StoreMethods storeMethods) {

        return storeMethods.getSearchParameters();
    }
}

