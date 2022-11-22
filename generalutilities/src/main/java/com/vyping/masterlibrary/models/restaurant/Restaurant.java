package com.vyping.masterlibrary.models.restaurant;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.RealData;
import com.vyping.masterlibrary.models.accounts.Account;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

public class Restaurant {

    @Exclude
    public static final String TAG_CONTACT = "Contact", TAG_ACCOUNTS = "Accounts", TAG_LICENSE = "License", TAG_RESTAURANT = "Restaurant";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_CONTACT, TAG_ACCOUNTS, TAG_LICENSE, TAG_RESTAURANT})
    public @interface Tags {}

    @Exclude
    public String Id;
    public HashMap<String, Object> Contact, License, Restaurant;
    public HashMap<String, Account> Accounts;

    /*----- Main Model -----*/

    public Restaurant() {

        this.Id = "";
        this.Accounts = new HashMap<>();
        this.Contact = new HashMap<>();
        this.License = new HashMap<>();
        this.Restaurant = new HashMap<>();
    }

    public Restaurant(@NonNull DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Accounts = new HashMap<>();
        this.Contact = realData.getHashMap(TAG_CONTACT);
        this.License = realData.getHashMap(TAG_LICENSE);
        this.Restaurant = realData.getHashMap(TAG_RESTAURANT);

        for (DataSnapshot dataAccount : dataSnapshot.child(TAG_ACCOUNTS).getChildren()) {

            Account account = new Account(dataAccount);
            Accounts.put(account.Id, account);
        }
    }
}
