package com.vyping.masterlibrary.Preferences;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

public class Preferences {


    /*----- Authentication -----*/

    @SuppressLint("ApplySharedPref")
    public void setAuthData(@NonNull Context context, String email, String password, String name, String document) {

        SharedPreferences myPreferences = context.getSharedPreferences("Auth", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        myEditor.putString("Email", email);
        myEditor.putString("Password", password);
        myEditor.putString("Name", name);
        myEditor.putString("Document", document);

        myEditor.apply();
        myEditor.commit();
    }

    @SuppressLint("ApplySharedPref")
    public void setUserEmail(@NonNull Context context, String email) {

        SharedPreferences myPreferences = context.getSharedPreferences("Auth", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        myEditor.putString("Email", email);

        myEditor.apply();
        myEditor.commit();
    }

    @SuppressLint("ApplySharedPref")
    public void setUserPassword(@NonNull Context context, String password) {

        SharedPreferences myPreferences = context.getSharedPreferences("Auth", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        myEditor.putString("Password", password);

        myEditor.apply();
        myEditor.commit();
    }

    @SuppressLint("ApplySharedPref")
    public void setUserName(@NonNull Context context, String name) {

        SharedPreferences myPreferences = context.getSharedPreferences("Auth", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        myEditor.putString("Name", name);

        myEditor.apply();
        myEditor.commit();
    }

    @SuppressLint("ApplySharedPref")
    public void setUserDocument(@NonNull Context context, String document) {

        SharedPreferences myPreferences = context.getSharedPreferences("Auth", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        myEditor.putString("Document", document);

        myEditor.apply();
        myEditor.commit();
    }

    public String getUserEmail(@NonNull Context context) {

        SharedPreferences myPreferences = context.getSharedPreferences("Auth", Context.MODE_PRIVATE);

        return myPreferences.getString("Email", "");
    }

    public String getUserPassword(@NonNull Context context) {

        SharedPreferences myPreferences = context.getSharedPreferences("Auth", Context.MODE_PRIVATE);

        return myPreferences.getString("Password", "");
    }

    public String getUserName(@NonNull Context context) {

        SharedPreferences myPreferences = context.getSharedPreferences("Auth", Context.MODE_PRIVATE);

        return myPreferences.getString("Name", "");
    }

    public String getUserDocument(@NonNull Context context) {

        SharedPreferences myPreferences = context.getSharedPreferences("Auth", Context.MODE_PRIVATE);

        return myPreferences.getString("Document", "");
    }

    public void resetUserAuth(@NonNull Context context) {

        SharedPreferences myPreferences = context.getSharedPreferences("Auth", Context.MODE_PRIVATE);
        SharedPreferences.Editor myEditor = myPreferences.edit();

        myEditor.remove("Email");
        myEditor.remove("Password");
        myEditor.remove("Name");
        myEditor.remove("Document");

        myEditor.clear();
        myEditor.apply();
    }
}
