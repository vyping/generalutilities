package com.vyping.libraries.utilities.models.products;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Firebase.RealData;
import com.vyping.masterlibrary.views.recyclerview.methods.MethodInterfase;

import java.util.ArrayList;

public class ProductsHandler implements MethodInterfase<ProductMethods> {


    // ----- SetUp ----- //

    public ProductsHandler() {}


    // ----- ModelMethods ----- //

    @Override
    public String getIndex(@NonNull ProductMethods model) {

        return model.getName();
    }

    @Override
    public String getIndex(DataSnapshot dataSnapshot) {

        return new Product(dataSnapshot).Name;
    }

    @Override
    public ProductMethods getMethod(DataSnapshot dataSnapshot) {

        Product product = new Product(dataSnapshot);

        if (dataSnapshot.getChildrenCount() == 1) {

            return new TItleMethods(product);

        } else {

            return new ProductMethods(product);
        }
    }

    @Override
    public ArrayList<Object> getSearchParameters(@NonNull ProductMethods model) {

        return model.getSearchParameters();
    }
}

