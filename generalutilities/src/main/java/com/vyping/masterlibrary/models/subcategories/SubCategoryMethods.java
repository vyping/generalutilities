package com.vyping.masterlibrary.models.subcategories;

import static com.vyping.masterlibrary.definitions.Buckets.BUCKET_CARTE;
import static com.vyping.masterlibrary.models.images.ImageMethods.PRIORITY_WEB;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.binding.BindingMethods;
import com.vyping.masterlibrary.models.subcategories.SubCategory;
import com.vyping.masterlibrary.models.images.ImageMethods;

import java.util.ArrayList;
import java.util.Arrays;

public class SubCategoryMethods extends BindingMethods {

    public final SubCategory subCategory;


    // ----- SetUp ----- //

    public SubCategoryMethods() {

        this.subCategory = new SubCategory();
    }

    public SubCategoryMethods(SubCategory subCategory) {

        this.subCategory = subCategory;
    }


    // ----- Getters Methods ----- //

    public SubCategory getSubCategory()
    {
        return subCategory;
    }

    public String getId()
    {
        return subCategory.Id;
    }

    public String getPosition()
    {
        return subCategory.Position;
    }

    public String getName()
    {
        return subCategory.Name;
    }

    public String getDescription()
    {
        return subCategory.Description;
    }

    public ArrayList<DataSnapshot> getProducts()
    {
        return subCategory.Products;
    }

    public ImageMethods getImage() {

        return new ImageMethods(subCategory.Image).priority(PRIORITY_WEB).storage(BUCKET_CARTE);
    }

    public boolean getSelected() {

        return subCategory.selected;
    }


    // ----- Getters Methods ----- //

    public void setId(String id) {

        this.subCategory.Id = id;
    }

    public void setPosition(String position) {

        this.subCategory.Position = position;
    }

    public void setName(String name) {

        this.subCategory.Name = name;
    }

    public void setImage(@NonNull ImageMethods imageMethods) {

        this.subCategory.Image = imageMethods.getImage();
    }

    public void setDescription(String description) {

        this.subCategory.Description = description;
    }

    public void setProducts(ArrayList<DataSnapshot> products) {

        this.subCategory.Products =  products;
    }

    public void setSelected(boolean selected) {

        this.subCategory.selected = selected;
    }

    // ----- Search ModelMethods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(subCategory.Name, subCategory.Description));
    }
}
