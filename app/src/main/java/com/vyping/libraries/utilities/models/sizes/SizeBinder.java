package com.vyping.libraries.utilities.models.sizes;

import com.vyping.masterlibrary.adapters.spinner.binder.SpinnerConditionalBinder;

public class SizeBinder extends SpinnerConditionalBinder<SizeMethods> {


    // ----- SetUp ----- //

    public SizeBinder(int bindingVariable, int layoutId) {

        super(bindingVariable, layoutId);
    }


    // ----- ModelMethods ----- //

    @Override
    public boolean canHandle(SizeMethods model) {

        return true;
    }
}
