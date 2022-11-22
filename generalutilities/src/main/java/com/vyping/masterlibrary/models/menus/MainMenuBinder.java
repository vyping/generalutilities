package com.vyping.masterlibrary.models.menus;

import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerConditionalBinder;

public class MainMenuBinder extends RecyclerConditionalBinder<MenuMethods> {


    // ----- SetUp ----- //

    public MainMenuBinder(int viewDataBinding, int bindingVariable, int layoutId) {

        super(viewDataBinding, bindingVariable, layoutId);
    }


    // ----- ModelMethods ----- //

    @Override
    public boolean canHandle(MenuMethods model) {

        return true;
    }
}
