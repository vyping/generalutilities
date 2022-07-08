package com.vyping.business.utilities.models.offers;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandlerInterfase;

import java.util.ArrayList;

public class OfferHandler implements RecyclerHandlerInterfase<OfferMethods> {


    // ----- SetUp ----- //

    public OfferHandler() {}


    // ----- ModelMethods ----- //

    @Override
    public String getIndex(@NonNull OfferMethods noticeMethods) {

        return noticeMethods.getId();
    }

    @Override
    public String getIndex(DataSnapshot dataSnapshot) {

        return new Offer(dataSnapshot).Id;
    }

    @Override
    public OfferMethods getMethod(DataSnapshot dataSnapshot) {

        Offer notice = new Offer(dataSnapshot);

        return new OfferMethods(notice);
    }

    @Override
    public ArrayList<Object> getSearchParameters(@NonNull OfferMethods noticeMethods) {

        return noticeMethods.getSearchParameters();
    }
}