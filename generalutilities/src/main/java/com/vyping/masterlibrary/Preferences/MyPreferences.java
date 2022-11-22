package com.vyping.masterlibrary.Preferences;

import static com.vyping.masterlibrary.Common.Definitions.PREFERENCES_ACCESS;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class MyPreferences {

    private SharedPreferences.Editor editor;


    /*----- Setup -----*/

    private MyPreferences(@NonNull Context context) {


    }

    private SharedPreferences GetPreferences(@NonNull Context context) {

        return context.getSharedPreferences(PREFERENCES_ACCESS, Context.MODE_PRIVATE);
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


    /*----- Set ModelMethods -----*/

    @SuppressLint("ApplySharedPref")
    public void SetAccessPermission(@NonNull Context context, String module, int access) {

        EditPreferences(context).putInt(module, access);
        ApplyChanges();
    }


    /*----- Get ModelMethods -----*/

    public int GetAccessPermission(@NonNull Context context, String module) {

        return GetPreferences(context).getInt(module, 0);
    }
}
