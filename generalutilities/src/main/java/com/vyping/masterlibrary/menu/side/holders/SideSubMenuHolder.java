package com.vyping.masterlibrary.menu.side.holders;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.BR;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerConditionalBinder;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandlerInterfase;
import com.vyping.masterlibrary.binding.BindingMethods;
import com.vyping.masterlibrary.models.submenus.SubMenu;
import com.vyping.masterlibrary.models.submenus.SubMenuMethods;

import org.json.JSONObject;

public class SideSubMenuHolder extends BindingMethods {

    private static final int layout = R.layout.sidesubmenu_holder;


    // ----- Classes ----- //

    public static class Binder extends RecyclerConditionalBinder<Methods> {


        // ----- SetUp ----- //

        public Binder() {

            super(BR.subMenuBinding, BR.subMenuMethod, layout);
        }


        // ----- ModelMethods ----- //

        @Override
        public boolean canHandle(Methods model) {

            return true;
        }
    }

    public static class Handler implements RecyclerHandlerInterfase<Methods> {


        // ----- SetUp ----- //

        public Handler() {}


        // ----- ModelMethods ----- //

        @Override
        public String getIdentifier(Methods subMenu) {

            return subMenu.getId();
        }

        @Override
        public String getIdentifier(DataSnapshot dataSnapshot) {

            return new SubMenu(dataSnapshot).Id;
        }

        @Override
        public String getIndex(@NonNull Methods subMenu) {

            return subMenu.getPosition();
        }

        @Override
        public String getIndex(DataSnapshot dataSnapshot) {

            return new SubMenu(dataSnapshot).Position;
        }

        @Override
        public Methods getMethod(DataSnapshot dataSnapshot) {

            SubMenu subMenu = new SubMenu(dataSnapshot);

            return new Methods(subMenu);
        }

        @Override
        public Methods setSelection(@NonNull Methods model, boolean selected) {

            model.setSelected(selected);

            return model;
        }
    }

    public static class Methods extends SubMenuMethods {


        // ----- SetUp ----- //

        public Methods(@NonNull SubMenu subMenu) {

            super(subMenu);
        }

        public Methods(@NonNull DataSnapshot dataSnapshot) {

            super(dataSnapshot);
        }

        public Methods(String id, JSONObject jsonMainMenu) {

            super(id, jsonMainMenu);
        }
    }
}