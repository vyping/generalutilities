package com.vyping.masterlibrary.adapters.spinner.binder;

public interface SpinnerItemBinderInterfase<T> {

      int getLayoutRes(T model);
      int getBindingVariable(T model);
}
