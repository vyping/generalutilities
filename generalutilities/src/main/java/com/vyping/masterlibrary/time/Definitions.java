package com.vyping.masterlibrary.time;

import androidx.annotation.IntDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Definitions {

    public static final String FORMAT_HOUR_01 = "HH:mm", FORMAT_HOUR_02 = "HH:mm:ss", FORMAT_HOUR_03 = "HH:mm a", FORMAT_HOUR_04 = "HH:mm:ss a";

    public static final String FORMAT_DATE_01 = "dd-MM-yy", FORMAT_DATE_02 = "dd-MM-yyyy", FORMAT_DATE_03 = "dd-MMM-yy", FORMAT_DATE_04 = "dd-MMM-yyyy", FORMAT_DATE_05 = "dd-MMMM-yy", FORMAT_DATE_06 = "dd-MMMM-yyyy";
    public static final String FORMAT_DATE_07 = "dd/MM/yy", FORMAT_DATE_08 = "dd/MM/yyyy", FORMAT_DATE_09 = "dd/MMM/yy", FORMAT_DATE_10 = "dd/MMM/yyyy", FORMAT_DATE_11 = "dd/MMMM/yy", FORMAT_DATE_12 = "dd/MMMM/yyyy";
    public static final String FORMAT_DATE_13 = "EEE dd 'de' MMM", FORMAT_DATE_14 = "EEE dd 'de' MMMM", FORMAT_DATE_15 = "EEEE dd 'de' MMM", FORMAT_DATE_16 = "EEEE dd 'de' MMMM";
    public static final String FORMAT_DATE_17 = "MMM dd 'del' yy", FORMAT_DATE_18 = "MMMM dd 'del' yy", FORMAT_DATE_19 = "MMM dd 'del' yyyy", FORMAT_DATE_20 = "MMMM dd 'del' yyyy";
    public static final String FORMAT_DATE_21 = "dd 'de' MMM 'del' yy", FORMAT_DATE_22 = "dd 'de' MMMM 'del' yy", FORMAT_DATE_23 = "dd 'de' MMM 'del' yyyy", FORMAT_DATE_24 = "dd 'de' MMMM 'del' yyyy";

    public static final String FORMAT_TIME_01 = "dd-MM-yy HH:mm", FORMAT_TIME_02 = "dd-MM-yyyy HH:mm", FORMAT_TIME_03 = "dd-MMM-yy HH:mm", FORMAT_TIME_04 = "dd-MMM-yyyy HH:mm", FORMAT_TIME_05 = "dd-MMMM-yy HH:mm", FORMAT_TIME_06 = "dd-MMMM-yyyy HH:mm";
    public static final String FORMAT_TIME_07 = "dd 'de' MMM 'a las' HH:mm", FORMAT_TIME_08 = "dd 'de' MMMM 'a las' HH:mm", FORMAT_TIME_09 = "dd 'de' MMM 'del' yyyy 'a las' HH:mm", FORMAT_TIME_10 = "dd 'de' MMMM 'del' yyyy 'a las' HH:mm";
    public static final String FORMAT_TIME_11 = "MMM dd 'del' yyyy 'a las' HH:mm", FORMAT_TIME_12 = "MMMM dd 'del' yyyy 'a las' HH:mm";


    public static final int TIME_BEFORE = 0, TIME_EQUALS = 1, TIME_AFTER = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({TIME_BEFORE, TIME_EQUALS, TIME_AFTER})
    public @interface TypeClock { }
}
