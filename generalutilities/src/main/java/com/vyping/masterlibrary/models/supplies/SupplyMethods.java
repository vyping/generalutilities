package com.vyping.masterlibrary.models.supplies;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.models.supplies.Supply;
import com.vyping.masterlibrary.binding.BindingMethods;

import java.util.ArrayList;
import java.util.Arrays;

public class SupplyMethods extends BindingMethods {

    public final Supply supply;


    // ----- SetUp ----- //

    public SupplyMethods() {

        this.supply = new Supply();
    }

    public SupplyMethods(@NonNull Supply supply) {

        this.supply = supply;
    }


    // ----- Getters Methods ----- //

    public Supply getSupply()
    {
        return this.supply;
    }

    public String getId()
    {
        return this.supply.Id;
    }

    public String getName()
    {
        return this.supply.Name;
    }

    public String getDescription()
    {
        return this.supply.Description;
    }

    public String getUnits()
    {
        return this.supply.Units;
    }

    public String getProvider()
    {
        return this.supply.Provider;
    }

    public String getPhone() {
        return this.supply.Phone;
    }

    public int getQuantity()
    {
        return this.supply.Quantity;
    }

    public int getValue()
    {
        return this.supply.Value;
    }

    public boolean getSelected() {

        return supply.selected;
    }


    // ----- Getters Methods ----- //

    public void setId(String id)
    {
        this.supply.Id = id;
    }

    public void setName(String name)
    {
        this.supply.Name = name;
    }

    public void setDescription(String description)
    {
        this.supply.Description = description;
    }

    public void setUnits(String units)
    {
        this.supply.Units = units;
    }

    public void setProvider(String provider)
    {
        this.supply.Provider = provider;
    }

    public void setPhone(String phone)
    {
        this.supply.Phone = phone;
    }

    public void setQuantity(int quantity)
    {
        this.supply.Quantity = quantity;
    }

    public void setValue(int value)
    {
        this.supply.Value = value;
    }

    public void setSelected(boolean selected) {

        this.supply.selected = selected;
    }


    // ----- Search ModelMethods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(getName()));
    }
}
