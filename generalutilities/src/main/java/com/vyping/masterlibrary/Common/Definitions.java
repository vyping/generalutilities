package com.vyping.masterlibrary.Common;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Definitions {

    /*----- Firebase -----*/

    public static final String AUTH_INVALID = "The password is invalid or the user does not have a password.";
    public static final String AUTH_DISABLED = "The user account has been disabled by an administrator.";
    public static final String AUTH_BLOCKED = "We have blocked all requests from this device due to unusual activity. Try again later.";
    public static final String AUTH_DELETED = "The user may have been deleted.";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({AUTH_INVALID, AUTH_DISABLED, AUTH_BLOCKED, AUTH_DELETED})
    public @interface LoginResponse {}

    public static final int ORDER_BYKEY = 0, ORDER_BYCHILD = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ORDER_BYKEY, ORDER_BYCHILD})
    public @interface OrderDatasnap {}

    /*----- RecyclerView -----*/

    public static final int LAYOUT_HORIZONTAL = 0, LAYOUT_VERTICAL = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LAYOUT_HORIZONTAL, LAYOUT_VERTICAL})
    public @interface LayoutOrientation {}

    /*----- Buttons -----*/

    public static final int ICON_START = 0, ICON_TOP = 1, ICON_END = 2, ICON_BOTTOM = 3;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ICON_START, ICON_TOP, ICON_END, ICON_BOTTOM})
    public @interface IconPosition {}


    /*----- Dialog Buttons -----*/

    public static final int BUTTONS_NONE = 0, BUTTONS_CANCEL = 1, BUTTONS_INFO = 2, BUTTONS_CANCEL_REFRESH = 3, BUTTONS_REFRESH_ACCEPT = 4, BUTTONS_CANCEL_ACCEPT = 5;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({BUTTONS_NONE, BUTTONS_CANCEL, BUTTONS_INFO, BUTTONS_CANCEL_REFRESH, BUTTONS_REFRESH_ACCEPT, BUTTONS_CANCEL_ACCEPT})
    public @interface ModeButtons {}


    /*----- DatePicker -----*/

    public static final int DATEPICKER_SPINNER = 0, DATEPICKER_CALENDAR = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DATEPICKER_SPINNER, DATEPICKER_CALENDAR})
     public @interface TypeCalendar {}


    /*----- DateNfcScan -----*/

    public static final int SENSOR_NONEXISTENT = 0, SENSOR_DISABLED = 1, SENSOR_ACTIVE = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SENSOR_NONEXISTENT, SENSOR_DISABLED, SENSOR_ACTIVE})
    public @interface SensorState {}


    /*----- TimePicker -----*/

    public static final int TIMEPICKER_SPINNER = 0, TIMEPICKER_CLOCK = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TIMEPICKER_SPINNER, TIMEPICKER_CLOCK})
    public @interface TypeClock {}
}
