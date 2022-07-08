package com.vyping.masterlibrary.adapters.recyclerview.handler;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public interface RecyclerHandlerInterfase<T> {

      String getIndex(T model);
      String getIndex(DataSnapshot dataSnapshot);
      T getMethod(DataSnapshot dataSnapshot);
      default ArrayList<Object> getSearchParameters(T model) { return new ArrayList<Object>(); };
}
