package com.vyping.masterlibrary.models.submenus;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.binding.BindingMethods;
import com.vyping.masterlibrary.models.images.Image;
import com.vyping.masterlibrary.models.images.ImageMethods;

import org.json.JSONObject;

public class SubMenuMethods extends BindingMethods {

    private SubMenu subMenu;


    // ----- SetUp ----- //

    public SubMenuMethods(@NonNull SubMenu subMenu) {

        this.subMenu = subMenu;
    }

    public SubMenuMethods(@NonNull DataSnapshot dataSnapshot) {

        this.subMenu = new SubMenu(dataSnapshot);
    }

    public SubMenuMethods(String id, JSONObject jsonMainMenu) {

        this.subMenu = new SubMenu(id, jsonMainMenu);
    }


    // -----  Getter Methods ----- //

    public SubMenu getSubMenu() {
        return subMenu;
    }

    public String getId() {
        return subMenu.Id;
    }

    public String getPosition() {
        return subMenu.Position;
    }

    public String getTitle() {
        return subMenu.Title;
    }

    public ImageMethods getImage()
    {
        return new ImageMethods(subMenu.Image);
    }

    public String getDestine()
    {
        return subMenu.Destin;
    }

    public boolean getSelected()
    {
        return subMenu.Selected;
    }


    // ----- Setter Methods ----- //

    public void setSubMenu(SubMenu subMenu) {
        this.subMenu = subMenu;
    }

    public void setId(String id) {
        this.subMenu.Id = id;
    }

    public void setPosition(String position) {
        this.subMenu.Position = position;
    }

    public void setTitle(String title) {
        this.subMenu.Title = title;
    }

    public void setImage(Image image)
    {
        this.subMenu.Image = image;
    }

    public void setDestine(String destine)
    {
        this.subMenu.Destin = destine;
    }

    public void setSelected(Boolean selected)
    {
        this.subMenu.Selected = selected;
    }
}