package com.vyping.masterlibrary.adapters.sliderview.binder;

public abstract class SliderConditionalBinder<T> extends SliderItemBinder<T> {

    public SliderConditionalBinder(int bindingVariable, int layoutId) {

        super(bindingVariable, layoutId);
    }

    public abstract boolean canHandle(T model);

    public boolean canHandle(int layoutId) {

        return this.layoutId == layoutId;
    }
}
