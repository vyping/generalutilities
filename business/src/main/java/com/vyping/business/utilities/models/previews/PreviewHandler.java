package com.vyping.business.utilities.models.previews;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.adapters.sliderview.handler.SliderHandlerInterfase;

import java.util.ArrayList;

public class PreviewHandler implements SliderHandlerInterfase<PreviewMethods> {


    // ----- SetUp ----- //

    public PreviewHandler() {}


    // ----- ModelMethods ----- //

    @Override
    public String getIndex(@NonNull PreviewMethods previewMethods) {

        return previewMethods.getId();
    }

    @Override
    public String getIndex(DataSnapshot dataSnapshot) {

        return new Preview(dataSnapshot).Id;
    }

    @Override
    public PreviewMethods getMethod(DataSnapshot dataSnapshot) {

        Preview preview = new Preview(dataSnapshot);

        return new PreviewMethods(preview);
    }

    @Override
    public ArrayList<Object> getSearchParameters(@NonNull PreviewMethods previewMethods) {

        return previewMethods.getSearchParameters();
    }
}