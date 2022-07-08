package com.vyping.libraries.utilities.models.sizes;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.libraries.utilities.models.products.TItleMethods;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandlerInterfase;
import com.vyping.masterlibrary.adapters.spinner.handler.SpinnerHandlerInterfase;

import java.util.ArrayList;

public class SizeHandler implements SpinnerHandlerInterfase<SizeMethods> {


    // ----- SetUp ----- //

    public SizeHandler() {}


    // ----- ModelMethods ----- //

    @Override
    public String getIndex(@NonNull SizeMethods model) {

        return model.getName();
    }

    @Override
    public String getIndex(DataSnapshot dataSnapshot) {

        return new Size(dataSnapshot).Name;
    }

    @Override
    public SizeMethods getMethod(DataSnapshot dataSnapshot) {

        Size product = new Size(dataSnapshot);

        return new SizeMethods(product);
    }
}

