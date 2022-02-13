package com.vyping.masterlibrary.Common;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.graphics.Typeface;
import android.text.TextUtils;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import java.util.regex.Pattern;

public class Numbers {


    public boolean isNumber(String text) {

        return TextUtils.isDigitsOnly(text);
    }

    public long objectToLong(Object object) {

        String text = String.valueOf(object);

        return stringToLong(text);
    }

    public long stringToLong(String text) {

        if (isNumber(text)) {

            return Long.parseLong(text);

        } else {

            return 0L;
        }
    }

    public int objectToInteger(Object object) {

        String text = String.valueOf(object);

        return stringToInteger(text);
    }

    public int stringToInteger(String text) {

        if (isNumber(text)) {

            return Integer.parseInt(text);

        } else {

            return 0;
        }
    }

    public long stringChangeToNumber(String text) {

        String Text = new Strings().translateToTag(text);
        long result = 0;

        for (int i = 0; i < Text.length(); i++) {

            final char ch = Text.charAt(i);
            result += (int) ch;
        }

        return result;
    }
}
