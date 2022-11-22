package com.vyping.masterlibrary.adapters.recyclerview.binder;

import com.vyping.masterlibrary.adapters.recyclerview.interfase.RecyclerInterfase;

public abstract class RecyclerConditionalBinder<T> extends RecyclerItemBinder<T> {


    // ----- SetUp ----- //

    public RecyclerConditionalBinder(int bindingVariable, int layoutId) {

        super(bindingVariable, layoutId);
    }

    public RecyclerConditionalBinder(int bindingVariable, int layoutId, int nameCallBack, RecyclerInterfase interfase) {

        super(bindingVariable, layoutId, nameCallBack, interfase);
    }

    public RecyclerConditionalBinder(int viewDataBinding, int bindingVariable, int layoutId) {

        super(viewDataBinding, bindingVariable, layoutId);
    }

    public RecyclerConditionalBinder(int viewDataBinding, int bindingVariable, int layoutId, int nameCallBack, RecyclerInterfase interfase) {

        super(viewDataBinding, bindingVariable, layoutId, nameCallBack, interfase);
    }

    public abstract boolean canHandle(T model);

    public boolean canHandle(int layoutId) {

        return this.layoutId == layoutId;
    }
}
