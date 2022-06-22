package com.vyping.libraries.utilities.models.products;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.views.recyclerview.methods.MethodBinder;

import java.util.ArrayList;

public class ProductsHandler implements MethodBinder<ProductMethods> {


    // ----- SetUp ----- //

    public ProductsHandler() {}


    // ----- ModelMethods ----- //

    @Override
    public String getIndex(@NonNull ProductMethods model) {

        return model.getName();
    }

    @Override
    public String getIndex(DataSnapshot snapshot) {

        ProductMethods productMethods = new ProductMethods(new Product(snapshot));

        return productMethods.getName();
    }

    @Override
    public ProductMethods getMethod(DataSnapshot snapshot) {

        return new ProductMethods(new Product(snapshot));
    }

    @Override
    public ArrayList<Object> getSearchParameters(@NonNull ProductMethods model) {

        return model.getSearchParameters();
    }
}

