package com.vyping.masterlibrary.models.recipes;

import static java.lang.Boolean.FALSE;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.RealData;
import com.vyping.masterlibrary.models.images.Image;
import com.vyping.masterlibrary.models.ingredients.Ingredient;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

public class Recipe {

    @Exclude
    public static final String TAG_NAME = "Name", TAG_DESCRIPTION = "Description", TAG_IMAGE = "Image", TAG_INGREDIENTS = "Ingredients", TAG_COST = "Cost", TAG_PRODUCTION = "Production";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_NAME, TAG_DESCRIPTION, TAG_IMAGE, TAG_INGREDIENTS, TAG_COST, TAG_PRODUCTION})
    public @interface Tags {}

    @Exclude
    public String Id;
    public String Name, Description;
    public int Cost, Production;
    public com.vyping.masterlibrary.models.images.Image Image;
    public HashMap<String, Ingredient> Ingredients;
    @Exclude
    public boolean selected;


    /*----- Main Model -----*/

    public Recipe() {

        this.Id = "";
        this.Name = "";
        this.Description = "";
        this.Cost = 0;
        this.Production = 0;
        this.Image = new Image();
        this.Ingredients = new HashMap<>();
        this.selected = FALSE;
    }

    public Recipe(@NonNull DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Name = realData.getString(TAG_NAME);
        this.Description = realData.getString(TAG_DESCRIPTION);
        this.Cost = realData.getIntegerOrDefault(TAG_COST, 0);
        this.Production = realData.getIntegerOrDefault(TAG_PRODUCTION, 0);
        this.Image = new Image(dataSnapshot.child(TAG_IMAGE));
        this.Ingredients = new HashMap<>();

        for (DataSnapshot snapIngredient : dataSnapshot.child(TAG_INGREDIENTS).getChildren()) {

            String id = snapIngredient.getKey();
            Ingredient ingredient = new Ingredient(snapIngredient);

            Ingredients.put(id, ingredient);
        }

        this.selected = FALSE;
    }
}
