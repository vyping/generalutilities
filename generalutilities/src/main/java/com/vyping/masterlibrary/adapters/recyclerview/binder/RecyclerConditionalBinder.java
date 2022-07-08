package com.vyping.masterlibrary.adapters.recyclerview.binder;

public abstract class RecyclerConditionalBinder<T> extends RecyclerItemBinder<T> {

    public RecyclerConditionalBinder(int bindingVariable, int layoutId) {

        super(bindingVariable, layoutId);
    }

    public abstract boolean canHandle(T model);

    public boolean canHandle(int layoutId) {

        return this.layoutId == layoutId;
    }
}
