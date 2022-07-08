package com.vyping.business.utilities.definitions;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Instances {


    /*----- Firebase Realtime Instances -----*/

    public static final String INSTANCE_USERS = "vyp-business-users";
    public static final String INSTANCE_PREVIEWS = "vyp-business-previews";
    public static final String INSTANCE_MENU = "vyp-business-menu";
    public static final String INSTANCE_OFFERS = "vyp-business-offers";
    public static final String INSTANCE_SHOP = "vyp-business-shop";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({INSTANCE_USERS, INSTANCE_PREVIEWS, INSTANCE_MENU, INSTANCE_OFFERS, INSTANCE_SHOP})
    public @interface RealtimeInstance {}
}
