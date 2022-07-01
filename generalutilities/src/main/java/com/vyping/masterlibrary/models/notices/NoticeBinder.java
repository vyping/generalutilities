package com.vyping.masterlibrary.models.notices;

import com.vyping.masterlibrary.views.recyclerview.binder.ConditionalDataBinder;

public class NoticeBinder extends ConditionalDataBinder<NoticeMethods> {


    // ----- SetUp ----- //

    public NoticeBinder(int bindingVariable, int layoutId) {

        super(bindingVariable, layoutId);
    }


    // ----- Methods ----- //

    @Override
    public boolean canHandle(NoticeMethods model) {

        return true;
    }
}
