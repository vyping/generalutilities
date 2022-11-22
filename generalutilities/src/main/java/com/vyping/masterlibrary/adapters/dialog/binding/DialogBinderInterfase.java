package com.vyping.masterlibrary.adapters.dialog.binding;

import android.app.Dialog;

import androidx.databinding.ViewDataBinding;

public interface DialogBinderInterfase<T, K> {

      public int getLayoutRes();
      public ViewDataBinding getViewBinding();
      public Dialog getDialog();
      public int getVariable();
      public T getMethods();
      public K getInterfase();
}
