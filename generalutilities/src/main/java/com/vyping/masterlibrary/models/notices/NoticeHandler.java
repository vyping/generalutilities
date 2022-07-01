package com.vyping.masterlibrary.models.notices;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.views.recyclerview.methods.MethodInterfase;

import java.util.ArrayList;

public class NoticeHandler implements MethodInterfase<NoticeMethods> {


    // ----- SetUp ----- //

    public NoticeHandler() {}


    // ----- ModelMethods ----- //

    @Override
    public String getIndex(@NonNull NoticeMethods noticeMethods) {

        return noticeMethods.getId();
    }

    @Override
    public String getIndex(DataSnapshot dataSnapshot) {

        return new Notice(dataSnapshot).Id;
    }

    @Override
    public NoticeMethods getMethod(DataSnapshot dataSnapshot) {

        Notice notice = new Notice(dataSnapshot);

        return new NoticeMethods(notice);
    }

    @Override
    public ArrayList<Object> getSearchParameters(@NonNull NoticeMethods noticeMethods) {

        return noticeMethods.getSearchParameters();
    }
}