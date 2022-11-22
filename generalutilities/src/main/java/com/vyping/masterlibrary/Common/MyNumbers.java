package com.vyping.masterlibrary.Common;

import android.widget.EditText;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class MyNumbers {


    // ----- Main ----- //

    public boolean isNumber(@NonNull String text) {

        return text.matches("(\\-)?+[0-9]+(\\.[0-9]+)?");
    }

    public boolean isNumberPair(int number) {

        return number % 2 == 0;
    }


    // ----- Integer Converters ----- //

    public int objectToInteger(Object object) {

        String text = new MyString().objectToString(object);

        return stringToInteger(text);
    }

    public int stringToInteger(String text) {

        if (isNumber(text)) {

            return Integer.parseInt(text);

        } else {

            return 0;
        }
    }

    public int longToInteger(long number) {

        //return new BigDecimal(number).intValueExact();
        return Math.toIntExact(number);
    }

    public int doubleToInteger(double number) {

        BigDecimal bigDecimal = new BigDecimal(number);

        return bigDecimal.intValue();
    }

    public int floatToInteger(float number) {

        return Math.round(number);
    }

    public int editTextToInteger(EditText editText) {

        String number = new MyString().extractFromEditText(editText);

        return stringToInteger(number);
    }


    // ----- Long Converters ----- //

    public long objectToLong(Object object) {

        String text = new MyString().objectToString(object);

        return stringToLong(text);
    }

    public long stringToLong(String text) {

        try {

            return Long.parseLong(text);

        } catch (NumberFormatException e) {

            return 0L;
        }
    }

    public long intToLong(int number) {

        return (long) number;
    }

    public long doubleToLong(double number) {

        BigDecimal bigDecimal = new BigDecimal(number);

        return bigDecimal.longValue();
    }

    public long floatToLong(float number) {

        return (long) number;
    }


    // ----- Double Converters ----- //

    public double objectToDouble(Object object) {

        String text = new MyString().objectToString(object);

        return stringToDouble(text);
    }

    public double stringToDouble(String text) {

        try {

            return Double.parseDouble(text);

        } catch (NumberFormatException e) {

            return 0.0;
        }
    }

    public double integerToDouble(int number) {

        return (double) number;
    }

    public double longToDouble(long number) {

        BigDecimal bigDecimal = new BigDecimal(number);

        return bigDecimal.doubleValue();
    }

    public double floatToDouble(float number) {

        String numString = floatToString(number);

        return Double.parseDouble(numString);
    }


    // ----- Float Converters ----- //


    public float objectToFloat(Object object) {

        String text = new MyString().objectToString(object);

        return stringToFloat(text);
    }

    public float stringToFloat(String text) {

        try {

            return Float.parseFloat(text);

        } catch (NumberFormatException e) {

            return 0f;
        }
    }

    public float integerToFloat(int number) {

        return (float) number;
    }

    public float longToFloat(long number) {

        return (float) number;
    }

    public float doubleToFloat(double number) {

        BigDecimal bigDecimal = new BigDecimal(number);

        return bigDecimal.floatValue();
    }


    // ----- String Converters ----- //

    public String integerToString(int number) {

        return Integer.toString(number);
    }

    public String longToString(long number) {

        return Long.toString(number);
    }

    public String doubleToString(double number) {

        return Double.toString(number);
    }

    public String floatToString(float number) {

        return Float.toString(number);
    }


    // ----- Decimals Formater ----- //

    public double setDecimals(String number, int decimals) {

        String format = "0.";

        for (int i = 0; i < decimals; ++i) {

            format = format + "0";
        }

        DecimalFormat decimalFormat = new DecimalFormat(format);
        String stringNumber = decimalFormat.format(number);

        return stringToDouble(stringNumber);
    }

    public double setDecimals(int number, int decimals) {

        String format = "0.";

        for (int i = 0; i < decimals; ++i) {

            format = format + "0";
        }

        DecimalFormat decimalFormat = new DecimalFormat(format);
        String stringNumber = decimalFormat.format(number);

        return stringToDouble(stringNumber);
    }

    public double setDecimals(long number, int decimals) {

        String format = "0.";

        for (int i = 0; i < decimals; ++i) {

            format = format + "0";
        }

        DecimalFormat decimalFormat = new DecimalFormat(format);
        String stringNumber = decimalFormat.format(number);

        return stringToDouble(stringNumber);
    }

    public double setDecimals(double number, int decimals) {

        String format = "0.";

        for (int i = 0; i < decimals; ++i) {

            format = format + "0";
        }

        DecimalFormat decimalFormat = new DecimalFormat(format);
        String stringNumber = decimalFormat.format(number);

        return stringToDouble(stringNumber);
    }

    public double setDecimals(float number, int decimals) {

        String format = "0.";

        for (int i = 0; i < decimals; ++i) {

            format = format + "0";
        }

        DecimalFormat decimalFormat = new DecimalFormat(format);
        String stringNumber = decimalFormat.format(number);

        return stringToDouble(stringNumber);
    }

    public long stringChangeToNumber(String text) {

        String Text = new MyString().translateToTag(text);
        long result = 0;

        for (int i = 0; i < Text.length(); i++) {

            final char ch = Text.charAt(i);
            result += (int) ch;
        }

        return result;
    }


    // ----- Views Interaction ----- //
    public int extractFromEditText(@NonNull EditText editText) {

        String number = String.valueOf(editText.getText()).replace("$", "").replace(",", "").replace(".", "");

        return stringToInteger(number);
    }
}
