package com.vyping.masterlibrary.models.menus;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandlerInterfase;

public class MainMenuHandler implements RecyclerHandlerInterfase<MenuMethods> {


    // ----- SetUp ----- //

    public MainMenuHandler() {}


    // ----- ModelMethods ----- //

    @Override
    public String getIdentifier(@NonNull MenuMethods mainMenu) {

        return mainMenu.getId();
    }

    @Override
    public String getIdentifier(DataSnapshot dataSnapshot) {

        return new Menu(dataSnapshot).Id;
    }

    @Override
    public String getIndex(@NonNull MenuMethods mainMenu) {

        return mainMenu.getPosition();
    }

    @Override
    public String getIndex(DataSnapshot dataSnapshot) {

        return new Menu(dataSnapshot).Position;
    }

    @Override
    public MenuMethods getMethod(DataSnapshot dataSnapshot) {

        Menu mainMenu = new Menu(dataSnapshot);

        return new MenuMethods(mainMenu);
    }
}

