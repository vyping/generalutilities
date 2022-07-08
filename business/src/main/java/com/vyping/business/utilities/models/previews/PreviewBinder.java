package com.vyping.business.utilities.models.previews;

import com.vyping.masterlibrary.adapters.sliderview.binder.SliderConditionalBinder;

public class PreviewBinder extends SliderConditionalBinder<PreviewMethods> {


    // ----- SetUp ----- //

    public PreviewBinder(int bindingVariable, int layoutId) {

        super(bindingVariable, layoutId);
    }


    // ----- Methods ----- //

    @Override
    public boolean canHandle(PreviewMethods model) {

        return true;
    }
}
