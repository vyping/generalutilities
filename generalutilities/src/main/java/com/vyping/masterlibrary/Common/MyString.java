package com.vyping.masterlibrary.Common;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import com.vyping.masterlibrary.Bucles.MyBucleFor;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

public class MyString {

    public String getStringResources(@NonNull Context context, int resString) {

        return context.getResources().getString(resString);
    }

    public String objectToString(Object object) {

        return String.valueOf(object);
    }

    public Typeface getFontInResources(Context context, int font) {

        return ResourcesCompat.getFont(context, font);
    }

    public String readInputText(final CharSequence Input) {

        return String.valueOf(Input).trim();
    }

    public String firstLetterUpperCase(@NonNull String word) {

        return word.substring(0, 1).toUpperCase() + word.substring(1);
    }

    public char[] getCharList(@NonNull CharSequence input) {

        return input.toString().toCharArray();
    }

    public ArrayList<String> getCharArray(@NonNull CharSequence input) {

        char[] charList = getCharList(input);
        ArrayList<String> arrayList = new ArrayList<>();

        new MyBucleFor().charsList(charList, new MyBucleFor.CharsInterface() {

            @Override
            public void itemFromArray(char item) {

                arrayList.add(String.valueOf(item));
            }

            private void DummyVoid() {
            }
        });

        return arrayList;
    }

    public String insertCharOnString(@NonNull CharSequence text, int position, String character) {

        String Text = String.valueOf(text);

        return insertCharOnString(Text, position, character);
    }

    public String insertCharOnString(@NonNull String text, int position, String character) {

        String[] array = new String[text.length() + 1];
        String[] cadenaArray = text.split("");
        String res = "La posición se encuentra fuera de la longitud de la cadena";

        if (position < text.length()) {

            for (int i = 0; i < text.length(); i++) {

                String letraCadena = cadenaArray[i];

                if (i == position) {

                    array[i] = character;
                    array[i + 1] = letraCadena;
                    i += 1;

                } else {

                    array[i] = letraCadena;
                }

                res = String.join("", array);
            }

        } else {

            res = text + character;
        }

        return res;
    }

    public boolean isValidEmail(String email) {

        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public boolean stringLengthLess(@NonNull String text, int length) {

        if (text.length() < length) {

            return TRUE;

        } else {

            return FALSE;
        }
    }

    public String translateToFirebaseKey(String Text) {

        if (Text != null) {

            return Text.trim()
                    .replace(".", "")
                    .replace("#", "")
                    .replace("$", "")
                    .replace("[", "")
                    .replace("]", "");

        } else {

            return "";
        }
    }

    public String translateToTag(String Text) {

        if (Text != null) {

            return Text.trim().toLowerCase()
                    .replace(" ", "")
                    .replace("Ñ", "N")
                    .replace("Á", "A")
                    .replace("É", "E")
                    .replace("Í", "I")
                    .replace("Ó", "O")
                    .replace("Ú", "U")
                    .replace("ñ", "n")
                    .replace("á", "a")
                    .replace("é", "e")
                    .replace("í", "i")
                    .replace("ó", "o")
                    .replace("ú", "u");

        } else {

            return "";
        }
    }

    public String toLowerCaseAndRemoveAccentMark(String Text) {

        if (Text != null) {

            return Text.trim().toLowerCase()
                    .replace("ñ", "n")
                    .replace("á", "a")
                    .replace("é", "e")
                    .replace("í", "i")
                    .replace("ó", "o")
                    .replace("ú", "u");

        } else {

            return "";
        }
    }

    public String toUpperCaseAndRemoveAccentMark(String Text) {

        if (Text != null) {

            return Text.trim().toUpperCase()
                    .replace("Ñ", "N")
                    .replace("Á", "A")
                    .replace("É", "E")
                    .replace("Í", "I")
                    .replace("Ó", "O")
                    .replace("Ú", "U");

        } else {

            return "";
        }
    }

    public String trimText(String Text) {

        String Return = "";

        if (Text != null) {

            Return = Text.trim();
        }

        return Return;
    }

    public String replaceSpaceByUnderScore(String text) {

        String Return = "";

        if (text != null) {

            String trimedText = trimText(text);

            Return = trimedText.replace(" ", "_");
        }

        return Return;
    }

    public String changeToFileName(String text) {

        String Return = "";

        if (text != null) {

            String lowerText = text.toLowerCase();
            String trimedText = trimText(lowerText);
            String replacedText = replaceSpaceByUnderScore(trimedText);

            Return = removeAccentMark(replacedText);
        }

        return Return;
    }

    public String removeAccentMark(String text) {

        String Return = "";

        if (text != null) {

            String trimedText = trimText(text);

            Return = trimedText
                    .replace("Ñ", "N")
                    .replace("Á", "A")
                    .replace("É", "E")
                    .replace("Í", "I")
                    .replace("Ó", "O")
                    .replace("Ú", "U")
                    .replace("ñ", "n")
                    .replace("á", "a")
                    .replace("é", "e")
                    .replace("í", "i")
                    .replace("ó", "o")
                    .replace("ú", "u");
        }

        return Return;
    }

    public String reduceToComparate(String Text) {

        if (Text != null) {

            return Text.trim().toLowerCase()
                    .replace(".", "")
                    .replace("-", "")
                    .replace(" ", "")
                    .replace("ñ", "n")
                    .replace("á", "a")
                    .replace("é", "e")
                    .replace("í", "i")
                    .replace("ó", "o")
                    .replace("ú", "u");
        } else {

            return "";
        }
    }

    public String formatToMoney(long number) {

        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
        formatter.applyPattern("$#,###,###,###");

        return formatter.format(number);
    }

    public String formatToMoney(@NonNull String number) {


        String Return = "";

        if (!number.equals("")) {

            boolean isNumber = new MyNumbers().isNumber(number);

            if (isNumber) {

                long Number = Long.parseLong(number);

                Return = formatToMoney(Number);
            }
        }

        return Return;
    }

    public String formatMiles(long number) {

        DecimalFormat formatter = (DecimalFormat) NumberFormat.getInstance(Locale.getDefault());
        formatter.applyPattern("#,###,###,###");

        return formatter.format(number);
    }

    public String formatMiles(@NonNull String number) {

        String Return = "";

        if (!number.equals("")) {

            boolean isNumber = new MyNumbers().isNumber(number);

            if (isNumber) {

                long Number = Long.parseLong(number);

                Return = formatMiles(Number);
            }
        }

        return Return;
    }

    public String formatDigits(long number, int digits) {

        return String.format(Locale.getDefault(), "%0" + digits + "d", number);
    }

    public String formatDigits(String number, int digits) {

        String Return = "";

        if (!number.equals("")) {

            boolean isNumber = new MyNumbers().isNumber(number);

            if (isNumber) {

                long Number = Long.parseLong(number);

                Return = formatDigits(Number, digits);
            }
        }

        return Return;
    }
}
