package com.vyping.masterlibrary.adapters.spinner.handler;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public interface SpinnerHandlerInterfase<T> {

      String getIndex(T model);
      String getIndex(DataSnapshot dataSnapshot);
      T getMethod(DataSnapshot dataSnapshot);
      default ArrayList<Object> getSearchParameters(T model) { return new ArrayList<Object>(); };
}
