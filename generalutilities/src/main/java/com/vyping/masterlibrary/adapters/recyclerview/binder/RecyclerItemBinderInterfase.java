package com.vyping.masterlibrary.adapters.recyclerview.binder;

public interface RecyclerItemBinderInterfase<T> {

      int getLayoutRes(T model);
      int getBindingVariable(T model);
}
