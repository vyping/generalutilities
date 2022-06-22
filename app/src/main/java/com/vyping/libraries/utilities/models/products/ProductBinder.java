package com.vyping.libraries.utilities.models.products;

import com.vyping.masterlibrary.views.recyclerview.binder.ConditionalDataBinder;

public class ProductBinder extends ConditionalDataBinder<ProductMethods> {


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
