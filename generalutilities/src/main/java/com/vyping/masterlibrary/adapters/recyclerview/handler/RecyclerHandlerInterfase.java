package com.vyping.masterlibrary.adapters.recyclerview.handler;

import com.google.firebase.database.DataSnapshot;

import java.util.ArrayList;

public interface RecyclerHandlerInterfase<T> {


      // ----- Main ----- //

      public String getIdentifier(T model);
      public String getIdentifier(DataSnapshot dataSnapshot);
      public String getIndex(T model);
      public String getIndex(DataSnapshot dataSnapshot);
      public T getMethod(DataSnapshot dataSnapshot);


      // ----- Optional ----- //

      public default boolean isDisplayable(T model) {

            return true;
      }
      public default boolean isDisplayable(DataSnapshot dataSnapshot) {

            return true;
      }
      public default T setSelection(T model, boolean deployed) { return model; };
      public default boolean containsSearchOnChilds(T model, String search) { return false; };
      public default ArrayList<Object> getSearchParameters(T model) { return new ArrayList<Object>(); };
}
