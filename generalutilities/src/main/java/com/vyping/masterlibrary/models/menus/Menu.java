package com.vyping.masterlibrary.models.menus;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.RealData;
import com.vyping.masterlibrary.Json.MyJsonReader;
import com.vyping.masterlibrary.models.submenus.SubMenu;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;
import java.util.Iterator;

public class Menu implements Serializable {

    @Exclude
    public static final String TAG_POSITION = "Position", TAG_TITLE = "Title", TAG_SUBMENUS = "SubMenus";
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_POSITION, TAG_TITLE, TAG_SUBMENUS})
    public @interface Tags {}

    @Exclude
    public String Id, Current;
    public String Position, Title;
    public HashMap<String, SubMenu> SubMenus;

    /*----- Main Model -----*/


    public Menu() {

        this.Id = "";
        this.Current = "";
        this.Position = "";
        this.Title = "";
        this.SubMenus = new HashMap<>();
    }

    public Menu(String Id, String Position, String Title, HashMap<String, SubMenu> SubMenus) {

        this.Id = Id;
        this.Current = "";
        this.Position = Position;
        this.Title = Title;
        this.SubMenus = SubMenus;
    }

    public Menu(@NonNull DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Current = "";
        this.Position = realData.getString(TAG_POSITION);
        this.Title = realData.getString(TAG_TITLE);
        this.SubMenus = new HashMap<>();

        for (DataSnapshot subDataSnapshot : dataSnapshot.child(TAG_SUBMENUS).getChildren()) {

            SubMenu subMenu = new SubMenu(subDataSnapshot);

            this.SubMenus.put(subMenu.Id, subMenu);
        }
    }

    public Menu(String id, JSONObject jsonMainMenu) {

        this.Id = id;
        this.Current = "";
        this.Position = new MyJsonReader().getJsonString(jsonMainMenu, TAG_POSITION);
        this.Title = new MyJsonReader().getJsonString(jsonMainMenu, TAG_TITLE);
        this.SubMenus = new HashMap<>();

        try {

            JSONObject subMenuObject = jsonMainMenu.getJSONObject(TAG_SUBMENUS);
            Iterator<String> keys = subMenuObject.keys();

            while (keys.hasNext()) {

                String key = keys.next();
                JSONObject jsonSubMenu = subMenuObject.getJSONObject(key);
                SubMenu subMenu = new SubMenu(key, jsonSubMenu);

                SubMenus.put(subMenu.Id, subMenu);
            }

        } catch (JSONException ignored) {}
    }
}

