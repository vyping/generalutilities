package com.vyping.masterlibrary.menu.side.holders;

import static com.vyping.masterlibrary.menu.side.MySideMenu.hide;
import static java.lang.Boolean.TRUE;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.BR;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.adapters.recyclerview.adapter.MyRecyclerAdapter;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerCompositeBinder;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerConditionalBinder;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerItemBinderInterfase;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandlerInterfase;
import com.vyping.masterlibrary.models.menus.Menu;
import com.vyping.masterlibrary.models.menus.MenuMethods;
import com.vyping.masterlibrary.models.submenus.SubMenu;

import org.json.JSONObject;

public class SideMenuHolder {

    private static final int layout = R.layout.sidemenu_holder;


    // ----- Classes ----- //

    public static class Binder extends RecyclerConditionalBinder<Methods> {


        // ----- SetUp ----- //

        public Binder() {

            super(BR.sideMenuBinding, BR.sideMenuMethod, layout);
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
        public String getIdentifier(@NonNull Methods mainMenu) {

            return mainMenu.getId();
        }

        @Override
        public String getIdentifier(DataSnapshot dataSnapshot) {

            return new Menu(dataSnapshot).Id;
        }

        @Override
        public String getIndex(@NonNull Methods mainMenu) {

            return mainMenu.getPosition();
        }

        @Override
        public String getIndex(DataSnapshot dataSnapshot) {

            return new Menu(dataSnapshot).Position;
        }

        @Override
        public Methods getMethod(DataSnapshot dataSnapshot) {

            return new Methods(dataSnapshot);
        }
    }

    public static class Methods extends MenuMethods<SideSubMenuHolder.Methods> {


        // ----- SetUp ----- //

        public Methods(@NonNull Menu menu) {

            super(menu, new Interfase<>() {

                @Override
                public RecyclerHandlerInterfase<SideSubMenuHolder.Methods> GetHandler(Menu menu) {

                    return new SideSubMenuHolder.Handler();
                }

                @Override
                public SideSubMenuHolder.Methods SetModel(SubMenu subMenu) {

                    return new SideSubMenuHolder.Methods(subMenu);
                }
            });
        }

        public Methods(@NonNull DataSnapshot dataSnapshot) {

            super(dataSnapshot, new Interfase<>() {

                @Override
                public RecyclerHandlerInterfase<SideSubMenuHolder.Methods> GetHandler(Menu menu) {

                    return new SideSubMenuHolder.Handler();
                }

                @Override
                public SideSubMenuHolder.Methods SetModel(SubMenu subMenu) {

                    return new SideSubMenuHolder.Methods(subMenu);
                }

            });
        }

        public Methods(@NonNull String id, JSONObject jsonMainMenu) {

            super(id, jsonMainMenu, new Interfase<>() {

                @Override
                public RecyclerHandlerInterfase<SideSubMenuHolder.Methods> GetHandler(Menu menu) {

                    return new SideSubMenuHolder.Handler();
                }

                @Override
                public SideSubMenuHolder.Methods SetModel(SubMenu subMenu) {

                    return new SideSubMenuHolder.Methods(subMenu);
                }
            });
        }


        // ----- Recyclerview Methods ----- //

        public RecyclerItemBinderInterfase<SideSubMenuHolder.Methods> getSubMenusBinder() {

            return new RecyclerCompositeBinder<>(new SideSubMenuHolder.Binder());
        }

        public final MyRecyclerAdapter.Interfase<SideSubMenuHolder.Methods> getSubMenusInterfase = new MyRecyclerAdapter.Interfase<>() {

            @Override
            public void OnClick(ViewDataBinding binding, RecyclerView.ViewHolder viewHolder, @NonNull View view, @NonNull SideSubMenuHolder.Methods subMenuMethods, int position) {

                hide();

                new MyActivity().Start(view.getContext(), subMenuMethods.getDestine(), TRUE);
            }

            private void DummyVoid() {};
        };
    }
}
