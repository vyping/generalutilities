package com.vyping.masterlibrary.adapters.sliderview.binder;

public class SliderCompositeBinder<T> implements SliderItemBinderInterfase<T> {

    private final SliderConditionalBinder<T>[] conditionalDataBinders;

    @SafeVarargs
    public SliderCompositeBinder(SliderConditionalBinder<T>... conditionalDataBinders) {

        this.conditionalDataBinders = conditionalDataBinders;
    }

    @Override
    public int getLayoutRes(T model) {

        for (SliderConditionalBinder<T> dataBinder : conditionalDataBinders) {

            if (dataBinder.canHandle(model)) {

                return dataBinder.getLayoutRes(model);
            }
        }

        throw new IllegalStateException();
    }

    @Override
    public int getBindingVariable(T model) {

        for (SliderConditionalBinder<T> dataBinder : conditionalDataBinders) {

            if (dataBinder.canHandle(model)) {

                return dataBinder.getBindingVariable(model);
            }
        }

        throw new IllegalStateException();
    }
}
