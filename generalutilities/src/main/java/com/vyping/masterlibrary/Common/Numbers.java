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

    public long stringToNumber(String text) {

        String Text = new Strings().translateToTag(text);
        long result = 0;

        for (int i = 0; i < Text.length(); i++) {

            final char ch = Text.charAt(i);
            result += (int) ch;
        }

        return result;
    }
}
