package com.vyping.masterlibrary.views.recyclerview.adapter.binder;

public class CompositeItemBinder<T> implements ItemBinder<T> {

    private final ConditionalDataBinder<T>[] conditionalDataBinders;

    @SafeVarargs
    public CompositeItemBinder(ConditionalDataBinder<T>... conditionalDataBinders) {

        this.conditionalDataBinders = conditionalDataBinders;
    }

    @Override
    public int getLayoutRes(T model) {

        for (ConditionalDataBinder<T> dataBinder : conditionalDataBinders) {

            if (dataBinder.canHandle(model)) {

                return dataBinder.getLayoutRes(model);
            }
        }

        throw new IllegalStateException();
    }

    @Override
    public int getBindingVariable(T model) {

        for (ConditionalDataBinder<T> dataBinder : conditionalDataBinders) {

            if (dataBinder.canHandle(model)) {

                return dataBinder.getBindingVariable(model);
            }
        }

        throw new IllegalStateException();
    }
}
