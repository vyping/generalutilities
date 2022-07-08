package com.vyping.business.utilities.models.products;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandlerInterfase;

import java.util.ArrayList;

public class ProductsHandler implements RecyclerHandlerInterfase<ProductMethods> {


    // ----- SetUp ----- //

    public ProductsHandler() {}


    // ----- ModelMethods ----- //

    @Override
    public String getIndex(@NonNull ProductMethods productMethods) {

        return productMethods.getId();
    }

    @Override
    public String getIndex(DataSnapshot dataSnapshot) {

        return new Product(dataSnapshot).Id;
    }

    @Override
    public ProductMethods getMethod(DataSnapshot dataSnapshot) {

        Product product = new Product(dataSnapshot);

        return new ProductMethods(product);
    }

    @Override
    public ArrayList<Object> getSearchParameters(@NonNull ProductMethods productMethods) {

        return productMethods.getSearchParameters();
    }
}

