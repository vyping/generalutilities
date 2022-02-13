package com.vyping.libraries.general;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Definitions {

    /*----- RecyclerView -----*/

    public static final int FROM_NULL = 0, FROM_CREATE = 1, FROM_OUTSIDE = 2, FROM_INSIDE = 3;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({FROM_NULL, FROM_CREATE, FROM_OUTSIDE, FROM_INSIDE})
    public @interface Source {}


    public static final int TO_NULL = 0, TO_ERASE = 1, TO_OUTSIDE = 2, TO_INSIDE = 3;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TO_NULL, TO_ERASE, TO_OUTSIDE, TO_INSIDE})
    public @interface Destin {}


    public static final int OUTSIDE = 0, INSIDE = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({OUTSIDE, INSIDE})
    public @interface State {}


    /*----- Adapert Vehicles -----*/

    public static final int TYPE_BUS = 0, TYPE_CAR = 1, TYPE_TRUCK = 2, TYPE_PICKUP = 3, TYPE_MOTORCYCLE = 4, TYPE_ARTICULATE = 5, TYPE_VAN = 6;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TYPE_BUS, TYPE_CAR , TYPE_TRUCK, TYPE_PICKUP, TYPE_MOTORCYCLE, TYPE_ARTICULATE, TYPE_VAN})
    public @interface TypeVehicle {}


    /*----- Adapert Vehicles -----*/

    public static final int PERIOD_HOURS = 0, PERIOD_MONTHLY = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({PERIOD_HOURS, PERIOD_MONTHLY})
    public @interface PeriodTariff {}

}
