package com.vyping.business.utilities.models.categories;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandlerInterfase;

import java.util.ArrayList;

public class CategoryHandler implements RecyclerHandlerInterfase<CategoryMethods> {


    // ----- SetUp ----- //

    public CategoryHandler() {}


    // ----- ModelMethods ----- //

    @Override
    public String getIndex(@NonNull CategoryMethods category) {

        return category.getPosition();
    }

    @Override
    public String getIndex(DataSnapshot dataSnapshot) {

        return new Category(dataSnapshot).Position;
    }

    @Override
    public CategoryMethods getMethod(DataSnapshot dataSnapshot) {

        Category category = new Category(dataSnapshot);

        return new CategoryMethods(category);
    }

    @Override
    public ArrayList<Object> getSearchParameters(@NonNull CategoryMethods categoryMethods) {

        return categoryMethods.getSearchParameters();
    }
}

