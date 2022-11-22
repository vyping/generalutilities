package com.vyping.masterlibrary.adapters.dialog.binding;

import android.app.Dialog;

import androidx.databinding.ViewDataBinding;

public class DialogBinder<T, K> implements DialogBinderInterfase<T, K> {

    protected Dialog dialog;
    protected ViewDataBinding binding;
    protected T methods;
    protected int layout, variable;
    protected K interfase;


    // ----- Setup ----- //

    public DialogBinder() {

        this.layout = 0;
        this.variable = 0;
        this.dialog = null;
        this.binding = null;
        this.methods = null;
    }


    // ----- Setters ----- //

    public void setLayoutRes(int layout) {

        this.layout = layout;
    }

    public void setVariable(int variable) {

        this.variable = variable;
    }

    public void setDialog(Dialog dialog) {

        this.dialog = dialog;
    }

    public void setViewBinding(ViewDataBinding binding) {

        this.binding = binding;
    }

    public void setDialogMethods(T methods) {

        this.methods = methods;
    }

    public void setInterfase(K interfase) {

        this.interfase = interfase;
    }


    // ----- Listeners ----- //

    @Override
    public int getLayoutRes() {

        return layout;
    }

    @Override
    public ViewDataBinding getViewBinding() {

        return binding;
    }

    @Override
    public Dialog getDialog() {

        return dialog;
    }

    @Override
    public int getVariable() {

        return variable;
    }

    @Override
    public T getMethods() {

        return methods;
    }

    @Override
    public K getInterfase() {

        return interfase;
    }
}
