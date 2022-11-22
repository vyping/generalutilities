package com.vyping.masterlibrary.models.products;

import static com.vyping.masterlibrary.definitions.Buckets.BUCKET_CARTE;
import static com.vyping.masterlibrary.definitions.Instances.INSTANCE_CARTE;
import static com.vyping.masterlibrary.models.categories.Category.TAG_PRODUCTS;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.binding.BindingMethods;
import com.vyping.masterlibrary.models.products.Product;
import com.vyping.masterlibrary.models.images.ImageMethods;

import java.util.ArrayList;
import java.util.Arrays;

public class ProductMethods extends BindingMethods {

    public Product product;


    // ----- SetUp ----- //

    public ProductMethods()
    {
        this.product = new Product();
    }

    public ProductMethods(Product product) {

        this.product = product;
    }

    public ProductMethods(DataSnapshot dataSnapshot) {

        this.product = new Product(dataSnapshot);
    }


    // ----- Getters Methods ----- //

    public Product getProduct()
    {
        return product;
    }

    public String getId()
    {
        return product.Id;
    }

    public String getPosition()
    {
        return product.Position;
    }

    public String getName()
    {
        return product.Name;
    }

    public String getPrice()
    {
        return product.Price;
    }

    public String getSize()
    {
        return product.Size;
    }

    public String getDescription()
    {
        return product.Description;
    }

    public int getPotatoes()
    {
        return product.Potatoes;
    }

    public ImageMethods getImage() {

        return new ImageMethods(product.Image).priority(ImageMethods.PRIORITY_WEB).storage(BUCKET_CARTE);
    }

    public boolean getSelected() {

        return product.selected;
    }


    // ----- Getters Methods ----- //

    public void setId(String id) {

        this.product.Id = id;
    }

    public void setPosition(String position) {

        this.product.Position = position;
    }

    public void setName(String name) {

        this.product.Name = name;
    }

    public void setImage(@NonNull ImageMethods imageMethods) {

        this.product.Image = imageMethods.getImage();
    }

    public void setPrice(String price) {

        this.product.Price = price;
    }

    public void setSize(String size) {

        this.product.Size = size;
    }

    public void setDescription(String description) {

        this.product.Description = description;
    }

    public void setPotatoes(int potatoes) {

        this.product.Potatoes = potatoes;
    }

    public void setSelected(boolean selected) {

        this.product.selected = selected;
    }


    // ----- Search ModelMethods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(product.Name, product.Price, product.Size, product.Description));
    }


    // ----- Firebase Methods ----- //

    public String createToFirebase(String category) {

        String path = category + "/" + TAG_PRODUCTS;
        MyRealtime myRealtime = new MyRealtime(INSTANCE_CARTE, path);
        return myRealtime.pushValue(product);
    }

    public String createToFirebase(String category, MyRealtime.CompleteListener listener) {

        String path = category + "/" + TAG_PRODUCTS;
        MyRealtime myRealtime = new MyRealtime(INSTANCE_CARTE, path);
        return myRealtime.pushValue(product, listener);
    }

    public void modifyOnFirebase(String category) {

        String path = category + "/" + TAG_PRODUCTS;

        MyRealtime myRealtime = new MyRealtime(INSTANCE_CARTE, path);
        myRealtime.setValue(getId(), product);
    }

    public void modifyOnFirebase(String category, MyRealtime.CompleteListener listener) {

        String path = category + "/" + TAG_PRODUCTS;

        MyRealtime myRealtime = new MyRealtime(INSTANCE_CARTE, path);
        myRealtime.setValue(getId(), product, listener);
    }

    public void eraseFromFirebase(String category) {

        String path = category + "/" + TAG_PRODUCTS;

        MyRealtime myRealtime = new MyRealtime(INSTANCE_CARTE, path);
        myRealtime.removeChild(getId());
    }

    public void eraseFromFirebase(String category, MyRealtime.CompleteListener listener) {

        String path = category + "/" + TAG_PRODUCTS;

        MyRealtime myRealtime = new MyRealtime(INSTANCE_CARTE, path);
        myRealtime.removeChild(getId(), listener);
    }
}
