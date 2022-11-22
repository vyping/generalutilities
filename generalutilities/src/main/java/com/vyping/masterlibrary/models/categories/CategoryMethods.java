package com.vyping.masterlibrary.models.categories;

import static com.vyping.masterlibrary.definitions.Buckets.BUCKET_CARTE;
import static com.vyping.masterlibrary.definitions.Instances.INSTANCE_CARTE;
import static com.vyping.masterlibrary.models.categories.Category.TAG_DESCRIPTION;
import static com.vyping.masterlibrary.models.categories.Category.TAG_IMAGE;
import static com.vyping.masterlibrary.models.categories.Category.TAG_NAME;
import static com.vyping.masterlibrary.models.categories.Category.TAG_POSITION;
import static com.vyping.masterlibrary.models.categories.Category.TAG_PRODUCTS;
import static com.vyping.masterlibrary.models.images.ImageMethods.PRIORITY_WEB;

import androidx.annotation.NonNull;
import androidx.databinding.Bindable;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.models.images.ImageMethods;
import com.vyping.masterlibrary.models.products.Product;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.binding.BindingMethods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CategoryMethods extends BindingMethods {

    public final Category category;


    // ----- SetUp ----- //

    public CategoryMethods() {

        this.category = new Category();
    }

    public CategoryMethods(Category category) {

        this.category = category;
    }

    public CategoryMethods(DataSnapshot dataSnapshot) {

        this.category = new Category(dataSnapshot);
    }


    // ----- Getters Methods ----- //

    public Category getCategory()
    {
        return category;
    }

    public String getId()
    {
        return category.Id;
    }

    @Bindable
    public String getName()
    {
        return category.Name;
    }

    @Bindable
    public String getDescription()
    {
        return category.Description;
    }

    @Bindable
    public String getPosition()
    {
        return category.Position;
    }

    public HashMap<String, Product> getProducts()
    {
        return category.Products;
    }

    @Bindable
    public ImageMethods getImage() {

        return new ImageMethods(category.Image).priority(PRIORITY_WEB).storage(BUCKET_CARTE);
    }

    public boolean getSelected() {

        return category.selected;
    }


    // ----- Getters Methods ----- //

    public void setId(String id) {

        this.category.Id = id;
    }

    public void setPosition(String position) {

        this.category.Position = position;
    }

    public void setName(String name) {

        this.category.Name = name;
       // notifyPropertyChanged(BR.name);
    }

    public void setImage(@NonNull ImageMethods imageMethods) {

        this.category.Image = imageMethods.getImage();
    }

    public void setDescription(String description) {

        this.category.Description = description;
    }

    public void setProducts(HashMap<String, Product> products) {

        this.category.Products = products;
    }

    public void setSelected(boolean selected) {

        this.category.selected = selected;
    }


    // ----- Search Methods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(category.Name, category.Description));
    }


    // ----- Compound Methods ----- //

    public HashMap<String, Object> setToUpload() {

        HashMap<String, Object> mapToUpload = new HashMap<>();
        mapToUpload.put(TAG_NAME, getName());
        mapToUpload.put(TAG_DESCRIPTION, getDescription());
        mapToUpload.put(TAG_IMAGE, category.Image);
        mapToUpload.put(TAG_POSITION, getPosition());
        mapToUpload.put(TAG_PRODUCTS, getProducts());

        return mapToUpload;
    }


    // ----- Firebase Methods ----- //


    // ----- Firebase Methods ----- //

    public String createToFirebase() {

        MyRealtime myRealtime = new MyRealtime(INSTANCE_CARTE);
        return myRealtime.pushValue(category);
    }

    public String createToFirebase(MyRealtime.CompleteListener listener) {

        MyRealtime myRealtime = new MyRealtime(INSTANCE_CARTE);
        return myRealtime.pushValue(category, listener);
    }

    public void modifyOnFirebase() {

        MyRealtime myRealtime = new MyRealtime(INSTANCE_CARTE);
        myRealtime.updateChild(getId(), setToUpload());
    }

    public void modifyOnFirebase(MyRealtime.CompleteListener listener) {

        MyRealtime myRealtime = new MyRealtime(INSTANCE_CARTE);
        myRealtime.updateChild(getId(), setToUpload(), listener);
    }

    public void eraseFromFirebase() {

        MyRealtime myRealtime = new MyRealtime(INSTANCE_CARTE);
        myRealtime.removeChild(getId());
    }

    public void eraseFromFirebase(MyRealtime.CompleteListener listener) {

        MyRealtime myRealtime = new MyRealtime(INSTANCE_CARTE);
        myRealtime.removeChild(getId(), listener);
    }
}
