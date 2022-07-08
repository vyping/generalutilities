package com.vyping.masterlibrary.adapters.sliderview.binder;

public class SliderItemBinder<T> implements SliderItemBinderInterfase<T> {

    protected final int bindingVariable;
    protected final int layoutId;

    public SliderItemBinder(int bindingVariable, int layoutId) {

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
