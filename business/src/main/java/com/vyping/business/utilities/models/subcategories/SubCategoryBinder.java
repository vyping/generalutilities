package com.vyping.business.utilities.models.subcategories;


import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerConditionalBinder;

public class SubCategoryBinder extends RecyclerConditionalBinder<SubCategoryMethods> {


    // ----- SetUp ----- //

    public SubCategoryBinder(int bindingVariable, int layoutId) {

        super(bindingVariable, layoutId);
    }


    // ----- ModelMethods ----- //

    @Override
    public boolean canHandle(SubCategoryMethods model) {

        return true;
    }
}
