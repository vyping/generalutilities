package com.vyping.masterlibrary.adapters.spinner.binder;

public class SpinnerItemBinder<T> implements SpinnerItemBinderInterfase<T> {

    protected final int bindingVariable;
    protected final int layoutId;

    public SpinnerItemBinder(int bindingVariable, int layoutId) {

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
