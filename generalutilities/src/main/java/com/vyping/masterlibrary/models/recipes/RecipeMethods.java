package com.vyping.masterlibrary.models.recipes;

import static com.vyping.masterlibrary.definitions.Buckets.BUCKET_CARTE;
import static com.vyping.masterlibrary.models.images.ImageMethods.PRIORITY_WEB;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.models.ingredients.Ingredient;
import com.vyping.masterlibrary.binding.BindingMethods;
import com.vyping.masterlibrary.models.images.ImageMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RecipeMethods extends BindingMethods {

    private final Recipe recipe;


    // ----- SetUp ----- //

    public RecipeMethods() {

        this.recipe = new Recipe();
    }

    public RecipeMethods(@NonNull Recipe recipe) {

        this.recipe = recipe;
    }


    // ----- Getters Methods ----- //

    public Recipe getRecipe()
    {
        return this.recipe;
    }

    public String getId()
    {
        return this.recipe.Id;
    }

    public String getName()
    {
        return this.recipe.Name;
    }

    public String getDescription()
    {
        return this.recipe.Description;
    }

    public int getCost()
    {
        return this.recipe.Cost;
    }

    public int getProduction()
    {
        return this.recipe.Production;
    }

    public ImageMethods getImage() {

        return new ImageMethods(recipe.Image).priority(PRIORITY_WEB).storage(BUCKET_CARTE);
    }

    public HashMap<String, Ingredient> getIngredients()
    {
        return this.recipe.Ingredients;
    }

    public boolean getSelected() {

        return recipe.selected;
    }


    // ----- Setters Methods ----- //

    public void setId(String id)
    {
        this.recipe.Id = id;
    }

    public void setName(String name)
    {
        this.recipe.Name = name;
    }

    public void setDescription(String description)
    {
        this.recipe.Description = description;
    }

    public void setCost(int cost)
    {
        this.recipe.Cost = cost;
    }

    public void setProduction(int production)
    {
        this.recipe.Production = production;
    }

    public void setImage(@NonNull ImageMethods imageMethods)

    {
        this.recipe.Image = imageMethods.getImage();
    }

    public void setIngredients(HashMap<String, Ingredient> ingredients)
    {
        this.recipe.Ingredients = ingredients;
    }

    public void setSelected(boolean selected) {

        this.recipe.selected = selected;
    }


    // ----- Search ModelMethods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(getName()));
    }
}
