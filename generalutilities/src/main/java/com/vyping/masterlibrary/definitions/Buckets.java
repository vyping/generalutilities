package com.vyping.masterlibrary.definitions;

import android.content.Context;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Common.MyString;

public class Buckets {


    /*----- Firebase Storage Buckets -----*/

    public static String MAIN_BUCKET, BUCKET_INSTANCE_MAIN;
    public static String BUCKET_CARTE;


    // ----- Setup ----- //

    public void setBuckets(@NonNull Context context, String restaurant) {

        int mainBucket = context.getResources().getIdentifier("firebase_main_datbase", "string", context.getPackageName());

        MAIN_BUCKET = mainBucket != 0 ? new MyString().getStringResources(context, mainBucket) : "";
        BUCKET_CARTE = "chicagowings-" + restaurant + "-carte";
    }

    public String extractToken(String url) {

        return url.split("alt=media&token=")[0];
    }
}
