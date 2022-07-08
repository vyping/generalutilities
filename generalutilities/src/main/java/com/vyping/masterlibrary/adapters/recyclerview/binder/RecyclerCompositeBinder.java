package com.vyping.masterlibrary.adapters.recyclerview.binder;

public class RecyclerCompositeBinder<T> implements RecyclerItemBinderInterfase<T> {

    private final RecyclerConditionalBinder<T>[] conditionalDataBinders;

    @SafeVarargs
    public RecyclerCompositeBinder(RecyclerConditionalBinder<T>... conditionalDataBinders) {

        this.conditionalDataBinders = conditionalDataBinders;
    }

    @Override
    public int getLayoutRes(T model) {

        for (RecyclerConditionalBinder<T> dataBinder : conditionalDataBinders) {

            if (dataBinder.canHandle(model)) {

                return dataBinder.getLayoutRes(model);
            }
        }

        throw new IllegalStateException();
    }

    @Override
    public int getBindingVariable(T model) {

        for (RecyclerConditionalBinder<T> dataBinder : conditionalDataBinders) {

            if (dataBinder.canHandle(model)) {

                return dataBinder.getBindingVariable(model);
            }
        }

        throw new IllegalStateException();
    }
}
