package com.vyping.masterlibrary.adapters.recyclerview.binder;

import com.vyping.masterlibrary.adapters.recyclerview.interfase.RecyclerInterfase;

public interface RecyclerItemBinderInterfase<T> {

      public int getViewDataBinding(T model);
      public int getBindingVariable(T model);
      public int getLayoutRes(T model);
      public int getNameCallBack(T model);
      public RecyclerInterfase getInterfase(T model);
}
