package com.vyping.masterlibrary.adapters.spinner.binder;

public class SpinnerCompositeBinder<T> implements SpinnerItemBinderInterfase<T> {

    private final SpinnerConditionalBinder<T>[] conditionalDataBinders;

    @SafeVarargs
    public SpinnerCompositeBinder(SpinnerConditionalBinder<T>... conditionalDataBinders) {

        this.conditionalDataBinders = conditionalDataBinders;
    }

    @Override
    public int getLayoutRes(T model) {

        for (SpinnerConditionalBinder<T> dataBinder : conditionalDataBinders) {

            if (dataBinder.canHandle(model)) {

                return dataBinder.getLayoutRes(model);
            }
        }

        throw new IllegalStateException();
    }

    @Override
    public int getBindingVariable(T model) {

        for (SpinnerConditionalBinder<T> dataBinder : conditionalDataBinders) {

            if (dataBinder.canHandle(model)) {

                return dataBinder.getBindingVariable(model);
            }
        }

        throw new IllegalStateException();
    }
}
