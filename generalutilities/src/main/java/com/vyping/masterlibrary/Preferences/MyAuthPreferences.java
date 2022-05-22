package com.vyping.masterlibrary.Preferences;

import static com.vyping.masterlibrary.Common.Definitions.PREFERENCES_AUTH;
import static com.vyping.masterlibrary.Models.Session.TAG_DOCUMENT;
import static com.vyping.masterlibrary.Models.Session.TAG_EMAIL;
import static com.vyping.masterlibrary.Models.Session.TAG_NAME;
import static com.vyping.masterlibrary.Models.Session.TAG_PASSWORD;
import static com.vyping.masterlibrary.Models.Session.TAG_TYPE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Models.Session;

public class MyAuthPreferences {

    private SharedPreferences.Editor editor;


    /*----- Common Methods -----*/

    private SharedPreferences GetPreferences(@NonNull Context context) {

        return context.getSharedPreferences(PREFERENCES_AUTH, Context.MODE_PRIVATE);
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
    public void SaveAuthData(Context context, @NonNull Session session) {

        EditPreferences(context);

        editor.putString(TAG_DOCUMENT, session.Document);
        editor.putString(TAG_EMAIL, session.Email);
        editor.putString(TAG_NAME, session.Name);
        editor.putString(TAG_PASSWORD, session.Password);
        editor.putString(TAG_TYPE, session.Type);

        ApplyChanges();
    }

    public void ResetAuthData(@NonNull Context context) {

        EditPreferences(context);

        editor.remove(TAG_DOCUMENT);
        editor.remove(TAG_EMAIL);
        editor.remove(TAG_NAME);
        editor.remove(TAG_PASSWORD);
        editor.remove(TAG_TYPE);

        ClearEditor();
    }

    public boolean IsSaved(Context context) {

        String email = GetPreferences(context).getString(TAG_EMAIL, "");

        if (email.equals("")) {

            return FALSE;

        } else {

            return TRUE;
        }
    }

    public Session ObtainSession(Context context) {

        String Document = GetPreferences(context).getString(TAG_DOCUMENT, "");
        String Email = GetPreferences(context).getString(TAG_EMAIL, "");
        String Name = GetPreferences(context).getString(TAG_NAME, "");
        String Password = GetPreferences(context).getString(TAG_PASSWORD, "");
        String Type = GetPreferences(context).getString(TAG_TYPE, "");
        boolean registered = FALSE;

        if (!Document.equals("")) {

            registered = TRUE;
        }

        return new Session(Document, Email, Name, Password, Type, registered);
    }


    /*----- Set Methods -----*/

    @SuppressLint("ApplySharedPref")
    public void SetEmail(@NonNull Context context, @NonNull Session session) {

        EditPreferences(context).putString(TAG_EMAIL, session.Email);
        ApplyChanges();
    }

    @SuppressLint("ApplySharedPref")
    public void SetPassword(@NonNull Context context, @NonNull Session session) {

        EditPreferences(context).putString(TAG_PASSWORD, session.Password);
        ApplyChanges();
    }

    @SuppressLint("ApplySharedPref")
    public void SetName(@NonNull Context context, @NonNull Session session) {

        EditPreferences(context).putString(TAG_NAME, session.Name);
        ApplyChanges();
    }

    @SuppressLint("ApplySharedPref")
    public void SetDocument(@NonNull Context context, @NonNull Session session) {

        EditPreferences(context).putString(TAG_DOCUMENT, session.Document);
        ApplyChanges();
    }


    /*----- Get Methods -----*/

    public String GetEmail(@NonNull Context context) {

        return GetPreferences(context).getString(TAG_EMAIL, "");
    }

    public String GetPassword(@NonNull Context context) {

        return GetPreferences(context).getString(TAG_PASSWORD, "");
    }

    public String GetName(@NonNull Context context) {

        return GetPreferences(context).getString(TAG_NAME, "");
    }

    public String GetDocument(@NonNull Context context) {

        return GetPreferences(context).getString(TAG_DOCUMENT, "");
    }
}
