package com.vyping.libraries.utilities.models.products;

import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.views.recyclerview.binder.ConditionalDataBinder;

public class TitleBinder extends ConditionalDataBinder<ProductMethods> {


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
