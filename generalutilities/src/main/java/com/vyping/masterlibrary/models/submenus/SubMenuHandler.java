package com.vyping.masterlibrary.models.submenus;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandlerInterfase;

public class SubMenuHandler implements RecyclerHandlerInterfase<SubMenuMethods> {


    // ----- SetUp ----- //

    public SubMenuHandler() {}


    // ----- ModelMethods ----- //

    @Override
    public String getIdentifier(@NonNull SubMenuMethods subMenu) {

        return subMenu.getId();
    }

    @Override
    public String getIdentifier(DataSnapshot dataSnapshot) {

        return new SubMenu(dataSnapshot).Id;
    }

    @Override
    public String getIndex(@NonNull SubMenuMethods subMenu) {

        return subMenu.getPosition();
    }

    @Override
    public String getIndex(DataSnapshot dataSnapshot) {

        return new SubMenu(dataSnapshot).Position;
    }

    @Override
    public SubMenuMethods getMethod(DataSnapshot dataSnapshot) {

        SubMenu category = new SubMenu(dataSnapshot);

        return new SubMenuMethods(category);
    }
}

