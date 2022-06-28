package com.vyping.masterlibrary.views.recyclerview.methods;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Firebase.RealData;

import java.util.ArrayList;

public interface MethodInterfase<T> {

      String getIndex(T model);
      String getIndex(DataSnapshot dataSnapshot);
      T getMethod(DataSnapshot dataSnapshot);
      default ArrayList<Object> getSearchParameters(T model) { return new ArrayList<Object>(); };
}
