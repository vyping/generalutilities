package com.vyping.masterlibrary.views.recyclerview.methods;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public interface MethodBinder<T> {

      String getIndex(T model);
      String getIndex(DataSnapshot snapshot);
      T getMethod(DataSnapshot snapshot);
      default ArrayList<Object> getSearchParameters(T model) { return new ArrayList<Object>(); };
}
