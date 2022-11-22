package com.vyping.masterlibrary.models.ingredients;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.binding.BindingMethods;
import com.vyping.masterlibrary.models.ingredients.Ingredient;

import java.util.ArrayList;
import java.util.Arrays;

public class IngredientMethods extends BindingMethods {

    private Ingredient ingredient;


    // ----- SetUp ----- //

    public IngredientMethods() {

        this.ingredient = new Ingredient();
    }

    public IngredientMethods(@NonNull Ingredient ingredient) {

        this.ingredient = ingredient;
    }

    public IngredientMethods(@NonNull DataSnapshot dataSnapshot) {

        this.ingredient = new Ingredient(dataSnapshot);
    }


    // ----- Getters Methods ----- //

    public Ingredient getIngredient()
    {
        return this.ingredient;
    }

    public String getId()
    {
        return this.ingredient.Id;
    }

    public String getName()
    {
        return this.ingredient.Name;
    }

    public String getUnits()
    {
        return this.ingredient.Units;
    }

    public int getQuantity()
    {
        return this.ingredient.Quantity;
    }

    public int getValue()
    {
        return this.ingredient.Value;
    }

    public boolean getSelected() {

        return ingredient.selected;
    }


    // ----- Setters Methods ----- //

    public void setId(String id)
    {
        this.ingredient.Id = id;
    }

    public void setName(String name)
    {
        this.ingredient.Name = name;
    }

    public void setUnits(String units)
    {
        this.ingredient.Units = units;
    }

    public void setQuantity(int quantity)
    {
        this.ingredient.Quantity = quantity;
    }

    public void setValue(int value)
    {
        this.ingredient.Value = value;
    }

    public void setSelected(boolean selected) {

        this.ingredient.selected = selected;
    }


    // ----- Search ModelMethods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(getName()));
    }
}
