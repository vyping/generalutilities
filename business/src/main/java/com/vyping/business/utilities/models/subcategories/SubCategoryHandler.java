package com.vyping.business.utilities.models.subcategories;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandlerInterfase;

import java.util.ArrayList;

public class SubCategoryHandler implements RecyclerHandlerInterfase<SubCategoryMethods> {


    // ----- SetUp ----- //

    public SubCategoryHandler() {}


    // ----- ModelMethods ----- //

    @Override
    public String getIndex(@NonNull SubCategoryMethods subCategoryMethods) {

        return subCategoryMethods.getPosition();
    }

    @Override
    public String getIndex(DataSnapshot dataSnapshot) {

        return new SubCategory(dataSnapshot).Position;
    }

    @Override
    public SubCategoryMethods getMethod(DataSnapshot dataSnapshot) {

        SubCategory subCategory = new SubCategory(dataSnapshot);

        return new SubCategoryMethods(subCategory);
    }

    @Override
    public ArrayList<Object> getSearchParameters(@NonNull SubCategoryMethods subCategoryMethods) {

        return subCategoryMethods.getSearchParameters();
    }
}

