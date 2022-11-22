package com.vyping.masterlibrary.definitions;

import android.content.Context;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Common.MyString;

public class Instances {

    public static String RESTAURANT, MAIN_DATABASE, INSTANCE_MAIN;
    public static String INSTANCE_RESTAURANT, INSTANCE_CARTE, INSTANCE_ORDERS, INSTANCE_INVENTARY, INSTANCE_RECIPES, INSTANCE_TRANSACTIONS, INSTANCE_ACCOUNTS;


    // ----- Setup ----- //
    
    public void setInstances(@NonNull Context context, String database) {

        int mainDatabase = context.getResources().getIdentifier("firebase_main_datbase", "string", context.getPackageName());

        RESTAURANT = database;
        MAIN_DATABASE = mainDatabase != 0 ? new MyString().getStringResources(context, mainDatabase) : "";
        INSTANCE_MAIN = "chicago-wings-default-rtdb";
        INSTANCE_RESTAURANT = "chicagowings-" + database + "-restaurant";
        INSTANCE_CARTE = "chicagowings-" + database + "-carte";
        INSTANCE_ORDERS = "chicagowings-" + database + "-orders";
        INSTANCE_INVENTARY = "chicagowings-" + database + "-inventary";
        INSTANCE_RECIPES = "chicagowings-" + database + "-recipes";
        INSTANCE_TRANSACTIONS = "chicagowings-" + database + "-transactions";
        INSTANCE_ACCOUNTS = "chicagowings-" + database + "-accounts";
    }
}
