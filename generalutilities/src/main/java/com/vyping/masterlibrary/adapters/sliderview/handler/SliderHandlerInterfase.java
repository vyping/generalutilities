package com.vyping.masterlibrary.adapters.sliderview.handler;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public interface SliderHandlerInterfase<T> {

      String getIndex(T model);
      String getIndex(DataSnapshot dataSnapshot);
      T getMethod(DataSnapshot dataSnapshot);
      default ArrayList<Object> getSearchParameters(T model) { return new ArrayList<Object>(); };
}
