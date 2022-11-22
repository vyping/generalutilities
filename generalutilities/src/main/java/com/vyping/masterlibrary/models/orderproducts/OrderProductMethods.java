package com.vyping.masterlibrary.models.orderproducts;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.models.products.Product;
import com.vyping.masterlibrary.binding.BindingMethods;

import java.util.ArrayList;
import java.util.Arrays;

public class OrderProductMethods extends BindingMethods {

    public OrderProduct orderProduct;


    // ----- SetUp ----- //

    public OrderProductMethods() {

        this.orderProduct = new OrderProduct();
    }

    public OrderProductMethods(OrderProduct orderProduct) {

        this.orderProduct = orderProduct;
    }

    public OrderProductMethods(DataSnapshot dataSnapshot) {

        this.orderProduct = new OrderProduct(dataSnapshot);
    }


    // ----- Getters Methods ----- //

    public OrderProduct getOrder()
    {
        return orderProduct;
    }

    public String getId()
    {
        return orderProduct.Id;
    }

    public String getName()
    {
        return orderProduct.Name;
    }

    public String getProperties()
    {
        return orderProduct.Properties;
    }

    public String getQuantity()
    {
        return orderProduct.Quantity;
    }

    public String getValueUnitary()
    {
        return orderProduct.Price;
    }

    public String getValueTotal()
    {
        return orderProduct.PriceTotal;
    }

    public String getDescription()
    {
        return orderProduct.Description;
    }

    public String getSize()
    {
        return orderProduct.Size;
    }

    public String getDelivered()
    {
        return orderProduct.Delivered;
    }

    public int getFrenchs()
    {
        return orderProduct.French;
    }

    public int getCreole()
    {
        return orderProduct.Creole;
    }

    public boolean getSelected() {

        return orderProduct.selected;
    }

    public int getPotatoes() {

        return orderProduct.Potatoes;
    }


    // ----- Getters Methods ----- //

    public void setId(String id) {

        this.orderProduct.Id = id;
    }

    public void setName(String name) {

        this.orderProduct.Name = name;
    }

    public void setProperties(String properties) {

        this.orderProduct.Properties = properties;
    }

    public void setQuantity(String quantity) {

        this.orderProduct.Quantity = quantity;
    }

    public void setValueUnitary(String valueUnitary) {

        orderProduct.Price = valueUnitary;
    }

    public void setValueTotal(String valueTotal) {

        this.orderProduct.PriceTotal = valueTotal;
    }

    public void setDescription(String description) {

        this.orderProduct.Description = description;
    }

    public void setSize(String size) {

        this.orderProduct.Size = size;
    }

    public void setDelivered(String delivered) {

        this.orderProduct.Delivered = delivered;
    }

    public void setFrench(int frenchs) {

        this.orderProduct.French = frenchs;
    }

    public void setCreoles(int creoles) {

        this.orderProduct.Creole = creoles;
    }

    public void setSelected(boolean selected) {

        this.orderProduct.selected = selected;
    }

    public void setPotatoes(int potatoes) {

        this.orderProduct.Potatoes = potatoes;
    }

    // ----- Search ModelMethods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(getName()));
    }


    // ----- Converter Methods ----- //

    public OrderProduct convertFormProduct(Product product) {

        this.orderProduct.Id = product.Id;
        this.orderProduct.Name = product.Name;
        this.orderProduct.Description = product.Description;
        this.orderProduct.Price = product.Price;
        this.orderProduct.Size = product.Size;
        this.orderProduct.Potatoes = product.Potatoes;
        this.orderProduct.French = 0;
        this.orderProduct.Creole = 0;

        return orderProduct;
    }
}
