package com.vyping.masterlibrary.Common;

import android.text.InputType;

import androidx.annotation.IntDef;
import androidx.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Definitions {

    /*----- Preference Tags -----*/

    public static final String PREFERENCES_CONFIG = "Configuration";
    public static final String PREFERENCES_AUTH = "Authentication";
    public static final String PREFERENCES_ACCESS = "Access";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({PREFERENCES_CONFIG, PREFERENCES_AUTH, PREFERENCES_ACCESS})
    public @interface PreferenceTags {
    }


    public static final int ACCESS_DENIED = 0, ACCESS_VIEW = 1, ACCESS_MODIFY = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ACCESS_DENIED, ACCESS_VIEW, ACCESS_MODIFY})
    public @interface Access {
    }

    /*----- Firebase -----*/

    public static final String AUTH_INVALID = "The password is invalid or the user does not have a password.";
    public static final String AUTH_DISABLED = "The user account has been disabled by an administrator.";
    public static final String AUTH_BLOCKED = "We have blocked all requests from this device due to unusual activity. Try again later.";
    public static final String AUTH_DELETED = "The user may have been deleted.";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({AUTH_INVALID, AUTH_DISABLED, AUTH_BLOCKED, AUTH_DELETED})
    public @interface LoginResponse {
    }

    public static final int ORDER_BYKEY = 0, ORDER_BYCHILD = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ORDER_BYKEY, ORDER_BYCHILD})
    public @interface OrderDatasnap {
    }

    /*----- RecyclerView -----*/

    public static final int LAYOUT_HORIZONTAL = 0, LAYOUT_VERTICAL = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({LAYOUT_HORIZONTAL, LAYOUT_VERTICAL})
    public @interface LayoutOrientation {
    }

    /*----- Buttons -----*/

    public static final int ICON_START = 0, ICON_TOP = 1, ICON_END = 2, ICON_BOTTOM = 3;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ICON_START, ICON_TOP, ICON_END, ICON_BOTTOM})
    public @interface IconPosition {
    }


    /*----- Type Input EdiText -----*/

    public static final int TEXT_INPUT_NORMAL = InputType.TYPE_CLASS_TEXT;
    public static final int TEXT_INPUT_DOCUMENT = InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL;
    public static final int TEXT_INPUT_EMAIL = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS;
    public static final int TEXT_INPUT_PASSWORD = InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TEXT_INPUT_NORMAL, TEXT_INPUT_DOCUMENT, TEXT_INPUT_EMAIL, TEXT_INPUT_PASSWORD})
    public @interface TextInput {
    }


    /*----- Type Answer -----*/

    public static final int ANSWER_VOID = 0, ANSWER_EQUALS = 1, ANSWER_INCORRECT = 2, ANSWER_CORRECT = 3, ANSWER_FINAL = 4;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({ANSWER_VOID, ANSWER_EQUALS, ANSWER_INCORRECT, ANSWER_CORRECT, ANSWER_FINAL})
    public @interface Answer {
    }


    /*----- DatePicker -----*/

    public static final int DATEPICKER_SPINNER = 0, DATEPICKER_CALENDAR = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DATEPICKER_SPINNER, DATEPICKER_CALENDAR})
    public @interface TypeCalendar {
    }


    /*----- DateNfcScan -----*/

    public static final int SENSOR_NONEXISTENT = 0, SENSOR_DISABLED = 1, SENSOR_ACTIVE = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({SENSOR_NONEXISTENT, SENSOR_DISABLED, SENSOR_ACTIVE})
    public @interface SensorState {
    }


    /*----- TimePicker -----*/

    public static final int TIMEPICKER_SPINNER = 0, TIMEPICKER_CLOCK = 1;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TIMEPICKER_SPINNER, TIMEPICKER_CLOCK})
    public @interface TypeClock {
    }
}
