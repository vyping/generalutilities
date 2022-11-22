package com.vyping.masterlibrary.Preferences;

import static com.vyping.masterlibrary.Common.Definitions.PREFERENCES_AUTH;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_ADDRESS;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_DOCUMENT;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_EMAIL;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_NAME;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_PHONE;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_TYPE;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_UID;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.models.accounts.Account;

public class MyAuthPreferences {

    private SharedPreferences.Editor editor;


    /*----- Common ModelMethods -----*/

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


    /*----- Main ModelMethods -----*/

    @SuppressLint("ApplySharedPref")
    public void SaveAuthData(Context context, @NonNull Account session) {

        EditPreferences(context);

        editor.putString(TAG_DOCUMENT, session.Document);
        editor.putString(TAG_EMAIL, session.Email);
        editor.putString(TAG_NAME, session.Name);
        editor.putString(TAG_TYPE, session.Type);

        ApplyChanges();
    }

    public void ResetAuthData(@NonNull Context context) {

        EditPreferences(context);

        editor.remove(TAG_DOCUMENT);
        editor.remove(TAG_EMAIL);
        editor.remove(TAG_NAME);
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

    public Account ObtainSession(Context context) {

        //Uri Document = GetPreferences(context).getString(TAG_DOCUMENT, "");
        String Document = GetPreferences(context).getString(TAG_DOCUMENT, "");
        String Uid = GetPreferences(context).getString(TAG_UID, "");
        String Email = GetPreferences(context).getString(TAG_EMAIL, "");
        String Phone = GetPreferences(context).getString(TAG_PHONE, "");
        String Address = GetPreferences(context).getString(TAG_ADDRESS, "");
        String Name = GetPreferences(context).getString(TAG_NAME, "");
        String Type = GetPreferences(context).getString(TAG_TYPE, "");
        boolean registered = FALSE;

        if (!Document.equals("")) {

            registered = TRUE;
        }

        return new Account();
    }


    /*----- Set ModelMethods -----*/

    @SuppressLint("ApplySharedPref")
    public void SetEmail(@NonNull Context context, @NonNull Account session) {

        EditPreferences(context).putString(TAG_EMAIL, session.Email);
        ApplyChanges();
    }

    @SuppressLint("ApplySharedPref")
    public void SetName(@NonNull Context context, @NonNull Account session) {

        EditPreferences(context).putString(TAG_NAME, session.Name);
        ApplyChanges();
    }

    @SuppressLint("ApplySharedPref")
    public void SetDocument(@NonNull Context context, @NonNull Account session) {

        EditPreferences(context).putString(TAG_DOCUMENT, session.Document);
        ApplyChanges();
    }


    /*----- Get ModelMethods -----*/

    public String GetEmail(@NonNull Context context) {

        return GetPreferences(context).getString(TAG_EMAIL, "");
    }

    public String GetName(@NonNull Context context) {

        return GetPreferences(context).getString(TAG_NAME, "");
    }

    public String GetDocument(@NonNull Context context) {

        return GetPreferences(context).getString(TAG_DOCUMENT, "");
    }
}
