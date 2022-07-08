package com.vyping.masterlibrary.adapters.spinner.binder;

public abstract class SpinnerConditionalBinder<T> extends SpinnerItemBinder<T> {

    public SpinnerConditionalBinder(int bindingVariable, int layoutId) {

        super(bindingVariable, layoutId);
    }

    public abstract boolean canHandle(T model);

    public boolean canHandle(int layoutId) {

        return this.layoutId == layoutId;
    }
}
