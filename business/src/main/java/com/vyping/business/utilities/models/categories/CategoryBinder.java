package com.vyping.business.utilities.models.categories;

import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerConditionalBinder;

public class CategoryBinder extends RecyclerConditionalBinder<CategoryMethods> {


    // ----- SetUp ----- //

    public CategoryBinder(int bindingVariable, int layoutId) {

        super(bindingVariable, layoutId);
    }


    // ----- ModelMethods ----- //

    @Override
    public boolean canHandle(CategoryMethods model) {

        return true;
    }
}
