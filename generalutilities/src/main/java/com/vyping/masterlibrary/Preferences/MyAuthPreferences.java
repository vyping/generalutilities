package com.vyping.masterlibrary.Preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class MyAuthPreferences {

    private SharedPreferences.Editor editor;

    private static final String AUTH = "Auth";
    private static final String EMAIL = "Email";
    private static final String PASSWORD = "Password";
    private static final String NAME = "Name";
    private static final String DOCUMENT = "Document";


    /*----- Common Methods -----*/

    private SharedPreferences GetPreferences(@NonNull Context context) {

        return context.getSharedPreferences(AUTH, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor EditPreferences(@NonNull Context context) {

        editor = GetPreferences(context).edit();

        return editor;
    }

    private void ApplyChanges() {

        editor.apply();
        editor.commit();
    }

    private void ClearEditor() {

        editor.clear();
        editor.apply();
    }


    /*----- Main Methods -----*/

    @SuppressLint("ApplySharedPref")
    public void SaveAuthData(@NonNull Context context, String email, String password, String name, String document) {

        EditPreferences(context);

        editor.putString(EMAIL, email);
        editor.putString(PASSWORD, password);
        editor.putString(NAME, name);
        editor.putString(DOCUMENT, document);

        ApplyChanges();
    }

    public void ResetAuthData(@NonNull Context context) {

        EditPreferences(context);

        editor.remove(EMAIL);
        editor.remove(PASSWORD);
        editor.remove(NAME);
        editor.remove(DOCUMENT);

        ClearEditor();
    }


    /*----- Set Methods -----*/

    @SuppressLint("ApplySharedPref")
    public void SetEmail(@NonNull Context context, String email) {

        EditPreferences(context).putString(EMAIL, email);
        ApplyChanges();
    }

    @SuppressLint("ApplySharedPref")
    public void SetPassword(@NonNull Context context, String password) {

        EditPreferences(context).putString(PASSWORD, password);
        ApplyChanges();
    }

    @SuppressLint("ApplySharedPref")
    public void SetName(@NonNull Context context, String name) {

        EditPreferences(context).putString(NAME, name);
        ApplyChanges();
    }

    @SuppressLint("ApplySharedPref")
    public void SetDocument(@NonNull Context context, String document) {

        EditPreferences(context).putString(DOCUMENT, document);
        ApplyChanges();
    }


    /*----- Get Methods -----*/

    public String GetEmail(@NonNull Context context) {

        return GetPreferences(context).getString(EMAIL, "");
    }

    public String GetPassword(@NonNull Context context) {

        return GetPreferences(context).getString(PASSWORD, "");
    }

    public String GetName(@NonNull Context context) {

        return GetPreferences(context).getString(NAME, "");
    }

    public String GetDocument(@NonNull Context context) {

        return GetPreferences(context).getString(DOCUMENT, "");
    }
}
