package com.vyping.business.utilities.definitions;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Buckets {


    /*----- Firebase Storage Buckets -----*/

    public static final String BUCKET_USERS = "vyp-business-users";
    public static final String BUCKET_MENU = "vyp-business-menu";
    public static final String BUCKET_NOTICES = "vyp-business-notices";
    public static final String BUCKET_RUTINES = "vyp-business-rutines";
    public static final String BUCKET_SHOP = "vyp-business-shop";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({BUCKET_NOTICES, BUCKET_RUTINES, BUCKET_SHOP})
    public @interface RealtimeBucket {}
}
