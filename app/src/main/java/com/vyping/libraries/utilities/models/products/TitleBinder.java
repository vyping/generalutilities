package com.vyping.libraries.utilities.models.products;

import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerConditionalBinder;

public class TitleBinder extends RecyclerConditionalBinder<ProductMethods> {


    // ----- SetUp ----- //

    public TitleBinder(int bindingVariable, int layoutId) {

        super(bindingVariable, layoutId);
    }


    // ----- ModelMethods ----- //

    @Override
    public boolean canHandle(ProductMethods model) {

        return model instanceof TItleMethods;
    }
}
