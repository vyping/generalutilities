package com.vyping.masterlibrary.models.restaurant;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.binding.BindingMethods;
import com.vyping.masterlibrary.models.accounts.Account;
import com.vyping.masterlibrary.models.restaurant.Restaurant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class RestaurantMethods extends BindingMethods {

    public static final String TAG_CONTACT_DOCUMENT = "Document", TAG_CONTACT_NAME = "Name", TAG_CONTACT_PHONE = "Phone";
    public static final String TAG_LICENSE_COST = "Cost", TAG_LICENSE_TYPE = "Type", TAG_LICENSE_VIGENCY = "Vigency";
    public static final String TAG_RESTAURANT_ADDRESS = "Address", TAG_RESTAURANT_CELL = "Cell", TAG_RESTAURANT_NAME = "Name", TAG_RESTAURANT_NIT = "Nit", TAG_RESTAURANT_PHONE = "Phone";

    public Restaurant restaurant;


    // ----- SetUp ----- //

    public RestaurantMethods() {

        this.restaurant = new Restaurant();
    }

    public RestaurantMethods(Restaurant restaurant) {

        this.restaurant = restaurant;
    }

    public RestaurantMethods(@NonNull DataSnapshot dataSnapshot) {

        this.restaurant = new Restaurant(dataSnapshot);
    }


    // ----- Getters Methods ----- //

    public String getId() {

        return restaurant.Id;
    }

    public Restaurant getRestaurant() {

        return restaurant;
    }

    public String getContactDocument() {

        return (String) restaurant.Contact.get(TAG_CONTACT_DOCUMENT);
    }

    public String getContactName() {

        String name = (String) restaurant.Contact.get(TAG_CONTACT_NAME);

        if (name == null) {

            name = "";
        }

        return name;
    }

    public String getContactPhone() {

        String phone = (String) restaurant.Contact.get(TAG_CONTACT_PHONE);

        if (phone == null) {

            phone = "";
        }

        return phone;
    }

    public HashMap<String, Account> getAccounts() {

        return restaurant.Accounts;
    }

    public String getLicenseCost() {

        String cost = (String) restaurant.License.get(TAG_LICENSE_COST);

        if (cost == null) {

            cost = "";
        }

        return cost;
    }

    public String getLicenseType() {

        String type = (String) restaurant.License.get(TAG_LICENSE_TYPE);

        if (type == null) {

            type = "";
        }

        return type;
    }

    public String getLicenseVigency() {

        String vigency = (String) restaurant.License.get(TAG_LICENSE_VIGENCY);

        if (vigency == null) {

            vigency = "";
        }

        return vigency;
    }

    public String getRestaurantAddress() {

        String address = (String) restaurant.Restaurant.get(TAG_RESTAURANT_ADDRESS);

        if (address == null) {

            address = "";
        }

        return address;
    }

    public String getRestaurantCell() {

        String cell = (String) restaurant.Restaurant.get(TAG_RESTAURANT_CELL);

        if (cell == null) {

            cell = "";
        }

        return cell;
    }

    public String getRestaurantName() {

        String name = (String) restaurant.Restaurant.get(TAG_RESTAURANT_NAME);

        if (name == null) {

            name = "";
        }

        return name;
    }

    public String getRestaurantNit() {

        String nit = (String) restaurant.Restaurant.get(TAG_RESTAURANT_NIT);

        if (nit == null) {

            nit = "";
        }

        return nit;
    }

    public String getRestaurantPhone() {

        String phone = (String) restaurant.Restaurant.get(TAG_RESTAURANT_PHONE);

        if (phone == null) {

            phone = "";
        }

        return phone;
    }


    // ----- Getters Methods ----- //

    public void setId(String id) {

        this.restaurant.Id = id;
    }

    public void setRestaurant(Restaurant restaurant) {

        this.restaurant = restaurant;
    }

    public void setContactDocument(String document) {

        this.restaurant.Contact.put(TAG_CONTACT_DOCUMENT, document);
    }

    public void setContactName(String name) {

        this.restaurant.Contact.put(TAG_CONTACT_NAME, name);
    }

    public void setContactPhone(String phone) {

        this.restaurant.Contact.put(TAG_CONTACT_PHONE, phone);
    }

    public void setAccounts(HashMap<String, Account> accounts) {

        this.restaurant.Accounts = accounts;
    }

    public void setLicenseCost(String cost) {

        this.restaurant.License.put(TAG_LICENSE_COST, cost);
    }

    public void setLicenseType(String type) {

        this.restaurant.License.put(TAG_LICENSE_TYPE, type);
    }

    public void setLicenseVigency(String vigency) {

        this.restaurant.License.put(TAG_LICENSE_VIGENCY, vigency);
    }

    public void setRestaurantAddress(String address) {

        this.restaurant.Restaurant.put(TAG_RESTAURANT_ADDRESS, address);
    }

    public void setRestaurantCell(String cell) {

        this.restaurant.Restaurant.put(TAG_RESTAURANT_CELL, cell);
    }

    public void setRestaurantName(String name) {

        this.restaurant.Restaurant.put(TAG_RESTAURANT_NAME, name);
    }

    public void setRestaurantNit(String nit) {

        this.restaurant.Restaurant.put(TAG_RESTAURANT_NIT, nit);
    }

    public void setRestaurantPhone(String phone) {

        this.restaurant.Restaurant.put(TAG_RESTAURANT_PHONE, phone);
    }


    // ----- Compound Methods ----- //

    public Account getAccount(String idAccount) {

        return restaurant.Accounts.get(idAccount);
    }


    // ----- Search ModelMethods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(getRestaurantName()));
    }
}
