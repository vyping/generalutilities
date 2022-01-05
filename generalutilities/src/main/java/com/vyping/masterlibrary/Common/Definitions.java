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


    /*----- Dialog Buttons -----*/

    public static final int BUTTONS_NONE = 0, BUTTONS_SINGLE = 1, BUTTONS_BOTH = 2, BUTTONS_THREE = 3;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({BUTTONS_NONE, BUTTONS_SINGLE, BUTTONS_BOTH, BUTTONS_THREE})
    public @interface ModeButtons {}


    /*----- DatePicker -----*/

    public static final int DATEPICKER_SPINNER = 0, DATEPICKER_CALENDAR = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DATEPICKER_SPINNER, DATEPICKER_CALENDAR})
     public @interface TypeCalendar {}


    /*----- TimePicker -----*/

    public static final int TIMEPICKER_SPINNER = 0, TIMEPICKER_CLOCK = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TIMEPICKER_SPINNER, TIMEPICKER_CLOCK})
    public @interface TypeClock {}
}
