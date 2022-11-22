package com.vyping.masterlibrary.models.submenus;

import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerConditionalBinder;

public class SubMenuBinder extends RecyclerConditionalBinder<SubMenuMethods> {


    // ----- SetUp ----- //

    public SubMenuBinder(int viewDataBinding, int bindingVariable, int layoutId) {

        super(viewDataBinding, bindingVariable, layoutId);
    }


    // ----- ModelMethods ----- //

    @Override
    public boolean canHandle(SubMenuMethods model) {

        return true;
    }
}
