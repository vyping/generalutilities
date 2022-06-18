package com.vyping.libraries.utilities.definitions;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Instances {


    /*----- Firebase Realtime Instances -----*/

    public static final String INSTANCE_USERS = "vyp-gym-users";
    public static final String INSTANCE_NOTICES = "vyp-gym-notices";
    public static final String INSTANCE_RUTINES = "vyp-gym-rutines";
    public static final String INSTANCE_SHOP = "vyp-gym-shop";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({INSTANCE_USERS, INSTANCE_NOTICES, INSTANCE_RUTINES, INSTANCE_SHOP})
    public @interface RealtimeInstance {}
}
