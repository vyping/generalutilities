package com.vyping.business.utilities.models.offers;

import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerConditionalBinder;

public class OfferBinder extends RecyclerConditionalBinder<OfferMethods> {


    // ----- SetUp ----- //

    public OfferBinder(int bindingVariable, int layoutId) {

        super(bindingVariable, layoutId);
    }


    // ----- Methods ----- //

    @Override
    public boolean canHandle(OfferMethods model) {

        return true;
    }
}
