package com.vyping.masterlibrary.Common;

import androidx.annotation.NonNull;

public class MyNumbers {


    public boolean isNumber(@NonNull String text) {

        return text.matches("(\\-)?+[0-9]+(\\.[0-9]+)?");
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

    public double objectToDouble(Object object) {

        String text = String.valueOf(object);

        return stringToDouble(text);
    }

    public double stringToDouble(String text) {

        if (isNumber(text)) {

            return Double.parseDouble(text);

        } else {

            return 0;
        }
    }

    public long stringChangeToNumber(String text) {

        String Text = new MyStrings().translateToTag(text);
        long result = 0;

        for (int i = 0; i < Text.length(); i++) {

            final char ch = Text.charAt(i);
            result += (int) ch;
        }

        return result;
    }
}
