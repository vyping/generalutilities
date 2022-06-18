package com.vyping.libraries.utilities.definitions;

import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Buckets {


    /*----- Firebase Realtime Instances -----*/

    public static final String BUCKET_NOTICES = "vyp-gym-notices";
    public static final String BUCKET_RUTINES = "vyp-gym-rutines";
    public static final String BUCKET_SHOP = "vyp-gym-shop";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({BUCKET_NOTICES, BUCKET_RUTINES, BUCKET_SHOP})
    public @interface RealtimeBucket {}


    /*----- Methods -----*/

    public String getMediaResource(@RealtimeBucket String bucket, String media, String token) {

        return "https://firebasestorage.googleapis.com/v0/b/" + bucket + "/o/" + media + ".png?alt=media&token=" + token;
    }
}
