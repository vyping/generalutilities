package com.vyping.masterlibrary.views.recyclerview.binder;

public interface ItemBinder<T> {

      int getLayoutRes(T model);
      int getBindingVariable(T model);
}
