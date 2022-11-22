package com.vyping.masterlibrary.adapters.recyclerview.binder;

import com.vyping.masterlibrary.adapters.recyclerview.interfase.RecyclerInterfase;

public class RecyclerCompositeBinder<T> implements RecyclerItemBinderInterfase<T> {

    private final RecyclerConditionalBinder<T>[] conditionalDataBinders;


    // ----- SetUp ----- //

    @SafeVarargs
    public RecyclerCompositeBinder(RecyclerConditionalBinder<T>... conditionalDataBinders) {

        this.conditionalDataBinders = conditionalDataBinders;
    }

    @Override
    public int getViewDataBinding(T model) {

        for (RecyclerConditionalBinder<T> dataBinder : conditionalDataBinders) {

            if (dataBinder.canHandle(model)) {

                return dataBinder.getViewDataBinding(model);
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
    public int getNameCallBack(T model) {

        for (RecyclerConditionalBinder<T> dataBinder : conditionalDataBinders) {

            if (dataBinder.canHandle(model)) {

                return dataBinder.getNameCallBack(model);
            }
        }

        throw new IllegalStateException();
    }

    @Override
    public RecyclerInterfase getInterfase(T model) {

        for (RecyclerConditionalBinder<T> dataBinder : conditionalDataBinders) {

            if (dataBinder.canHandle(model)) {

                return dataBinder.getInterfase(model);
            }
        }

        throw new IllegalStateException();
    }
}
