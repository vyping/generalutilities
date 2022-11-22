package com.vyping.masterlibrary.adapters.recyclerview.binder;

import com.vyping.masterlibrary.adapters.recyclerview.interfase.RecyclerInterfase;

public class RecyclerItemBinder<T> implements RecyclerItemBinderInterfase<T> {

    protected int viewDataBinding;
    protected final int bindingVariable;
    protected final int layoutId;
    protected int nameCallBack;
    protected RecyclerInterfase interfase;

    public RecyclerItemBinder(int bindingVariable, int layoutId) {

        this.bindingVariable = bindingVariable;
        this.layoutId = layoutId;
    }

    public RecyclerItemBinder(int bindingVariable, int layoutId, int nameCallBack, RecyclerInterfase interfase) {

        this.bindingVariable = bindingVariable;
        this.layoutId = layoutId;
        this.nameCallBack = nameCallBack;
        this.interfase = interfase;
    }

    public RecyclerItemBinder(int viewDataBinding, int bindingVariable, int layoutId) {

        this.viewDataBinding = viewDataBinding;
        this.bindingVariable = bindingVariable;
        this.layoutId = layoutId;
    }

    public RecyclerItemBinder(int viewDataBinding, int bindingVariable, int layoutId, int nameCallBack, RecyclerInterfase interfase) {

        this.viewDataBinding = viewDataBinding;
        this.bindingVariable = bindingVariable;
        this.layoutId = layoutId;
        this.nameCallBack = nameCallBack;
        this.interfase = interfase;
    }

    public int getViewDataBinding(T model)
    {
        return viewDataBinding;
    }

    public int getLayoutRes(T model)
    {
        return layoutId;
    }

    public int getBindingVariable(T model)
    {
        return bindingVariable;
    }

    public int getNameCallBack(T model)
    {
        return nameCallBack;
    }

    public RecyclerInterfase getInterfase(T model)
    {
        return interfase;
    }
}
