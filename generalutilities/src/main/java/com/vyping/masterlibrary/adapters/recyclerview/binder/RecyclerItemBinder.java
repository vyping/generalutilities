package com.vyping.masterlibrary.adapters.recyclerview.binder;

public class RecyclerItemBinder<T> implements RecyclerItemBinderInterfase<T> {

    protected final int bindingVariable;
    protected final int layoutId;

    public RecyclerItemBinder(int bindingVariable, int layoutId) {

        this.bindingVariable = bindingVariable;
        this.layoutId = layoutId;
    }

    public int getLayoutRes(T model)
    {
        return layoutId;
    }

    public int getBindingVariable(T model)
    {
        return bindingVariable;
    }
}
