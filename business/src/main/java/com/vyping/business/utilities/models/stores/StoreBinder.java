package com.vyping.business.utilities.models.stores;

import com.vyping.masterlibrary.adapters.spinner.binder.SpinnerConditionalBinder;

public class StoreBinder extends SpinnerConditionalBinder<StoreMethods> {


    // ----- SetUp ----- //

    public StoreBinder(int bindingVariable, int layoutId) {

        super(bindingVariable, layoutId);
    }


    // ----- ModelMethods ----- //

    @Override
    public boolean canHandle(StoreMethods model) {

        return true;
    }
}
