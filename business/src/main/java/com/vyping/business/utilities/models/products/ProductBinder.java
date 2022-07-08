package com.vyping.business.utilities.models.products;

import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerConditionalBinder;

public class ProductBinder extends RecyclerConditionalBinder<ProductMethods> {


    // ----- SetUp ----- //

    public ProductBinder(int bindingVariable, int layoutId) {

        super(bindingVariable, layoutId);
    }


    // ----- ModelMethods ----- //

    @Override
    public boolean canHandle(ProductMethods model) {

        return true;
    }
}
