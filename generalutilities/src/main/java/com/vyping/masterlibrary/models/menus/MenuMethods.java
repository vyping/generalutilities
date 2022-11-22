package com.vyping.masterlibrary.models.menus;

import static java.lang.Boolean.TRUE;

import androidx.annotation.NonNull;
import androidx.databinding.ObservableArrayList;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandler;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandlerInterfase;
import com.vyping.masterlibrary.binding.BindingMethods;
import com.vyping.masterlibrary.models.submenus.SubMenu;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MenuMethods<subMenu> extends BindingMethods {

    private final Menu menu;
    private Interfase<subMenu> interfase;
    private RecyclerHandler<subMenu> handler;


    // ----- SetUp ----- //

    public MenuMethods(@NonNull Menu menu) {

        this.menu = menu;

        setSubMenusHandler();
    }

    public MenuMethods(@NonNull Menu menu, Interfase<subMenu> interfase) {

        this.menu = menu;
        this.interfase = interfase;

        setSubMenusHandler();
    }

    public MenuMethods(@NonNull DataSnapshot dataSnapshot) {

        this.menu = new Menu(dataSnapshot);

        setSubMenusHandler();
    }

    public MenuMethods(@NonNull DataSnapshot dataSnapshot, Interfase<subMenu> interfase) {

        this.menu = new Menu(dataSnapshot);
        this.interfase = interfase;

        setSubMenusHandler();
    }

    public MenuMethods(String id, JSONObject jsonMainMenu) {

        this.menu = new Menu(id, jsonMainMenu);

        setSubMenusHandler();
    }

    public MenuMethods(String id, JSONObject jsonMainMenu, Interfase<subMenu> interfase) {

        this.menu = new Menu(id, jsonMainMenu);
        this.interfase = interfase;

        setSubMenusHandler();
    }


    // ----- Getters ----- //

    public Menu getMenu() {

        return menu;
    }

    public String getId() {

        return menu.Id;
    }

    public String getCurrent() {

        return menu.Current;
    }

    public String getPosition() {

        return menu.Position;
    }

    public String getTitle() {

        return menu.Title;
    }

    public HashMap<String, SubMenu> getSubMenus() {

        return menu.SubMenus;
    }

    public ObservableArrayList<subMenu> getSubMenusHandler() {

        return handler.methodsDisplayed;
    }


    // ----- Setters ----- //

    public void setId(String id) {

        this.menu.Id = id;
    }

    public void setCurrent(String current) {

        this.menu.Current = current;
    }

    public void setPosition(String position) {

        this.menu.Position = position;
    }

    public void setTitle(String title) {

        this.menu.Title = title;
    }

    public void setSubMenus(HashMap<String, SubMenu> subMenus) {

        this.menu.SubMenus = subMenus;
    }

    private void setSubMenusHandler() {

        RecyclerHandlerInterfase<subMenu> recyclerInterfase = interfase.GetHandler(menu);

        handler = new RecyclerHandler<>(recyclerInterfase, TRUE);

        for (Map.Entry<String, SubMenu> subMenuEntry : menu.SubMenus.entrySet()) {

            SubMenu subMenu = subMenuEntry.getValue();
            String[] destin = subMenu.Destin.split("\\.");
            subMenu.Selected = menu.Current.equals(destin[destin.length-1]);
            subMenu modelSubMenu = interfase.SetModel(subMenu);

            handler.addMethod(modelSubMenu);
        }
    }


    //----- Interface -----//

    public interface Interfase<model> {

        public RecyclerHandlerInterfase<model> GetHandler(Menu menu);
        public model SetModel(SubMenu subMenu);
    }
}
