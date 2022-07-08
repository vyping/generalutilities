package com.vyping.business.utilities.models.titles;

import com.vyping.business.utilities.models.categories.CategoryMethods;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerConditionalBinder;

public class TitleBinder extends RecyclerConditionalBinder<CategoryMethods> {


    // ----- SetUp ----- //

    public TitleBinder(int bindingVariable, int layoutId) {

        super(bindingVariable, layoutId);
    }


    // ----- ModelMethods ----- //

    @Override
    public boolean canHandle(CategoryMethods categoryMethods) {

        return categoryMethods instanceof TItleMethods;
    }
}
