package com.vyping.masterlibrary.Common;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Editable;
import android.util.Patterns;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.vyping.masterlibrary.R;

import java.util.regex.Pattern;

public class Strings {

    public String getString(@NonNull Context context, int resString) {

        return context.getResources().getString(resString);
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

    public boolean isValidEmail(String email) {

        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    public boolean stringLengthLess(@NonNull String text, int  length) {

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
                    .replace("[","")
                    .replace("]","");

        } else {

            return "";
        }
    }

    public String translateToTag(String Text) {

        if (Text != null) {

            return Text.trim().toLowerCase()
                .replace(" ", "")
                .replace("Ñ","N")
                .replace("Á","A")
                .replace("É","E")
                .replace("Í","I")
                .replace("Ó","O")
                .replace("Ú","U")
                .replace("ñ","n")
                .replace("á","a")
                .replace("é","e")
                .replace("í","i")
                .replace("ó","o")
                .replace("ú","u");

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
                .replace("Ñ","N")
                .replace("Á","A")
                .replace("É","E")
                .replace("Í","I")
                .replace("Ó","O")
                .replace("Ú","U");

        } else {

            return "";
        }
    }

    public String removeAccentMark(String Text) {

        if (Text != null) {

            return Text.trim()
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
}
