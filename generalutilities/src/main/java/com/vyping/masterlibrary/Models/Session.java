package com.vyping.masterlibrary.Models;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Common.MyStrings;
import com.vyping.masterlibrary.Firebase.Readers;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

public class Session extends Readers implements Serializable {

    public String Document, Email, Name, Password, Type;
    @Exclude
    public boolean Registered;

    public static final String TAG_DOCUMENT = "Document", TAG_EMAIL = "Email", TAG_NAME = "Name", TAG_PASSWORD = "Password", TAG_TYPE = "Type";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_DOCUMENT, TAG_EMAIL, TAG_NAME, TAG_PASSWORD, TAG_TYPE})
    public @interface Tags {
    }


    /*----- Main Model -----*/

    public Session() {

        this.Document = "";
        this.Email = "";
        this.Name = "";
        this.Password = "";
        this.Type = "";
        this.Registered = FALSE;
    }

    public Session(String Document, String Email, String Name, String Password, String Type, boolean Registered) {

        this.Document = Document;
        this.Email = Email;
        this.Name = Name;
        this.Password = Password;
        this.Type = Type;
        this.Registered = Registered;
    }

    public Session(@NonNull DataSnapshot dataSession) {

        Document = getKeyString(dataSession);
        Email = getString(dataSession.child(TAG_EMAIL));
        Name = getString(dataSession.child(TAG_NAME));
        Password = getString(dataSession.child(TAG_PASSWORD));
        Type = getString(dataSession.child(TAG_TYPE));

        Registered = isRegistered();
    }

    public Session(@NonNull HashMap<String, Object> mapSession) {

        Document = Email = Password = Type = "";

        if (mapSession.containsKey(TAG_DOCUMENT)) {

            Document = new MyStrings().objectToString(mapSession.get(TAG_DOCUMENT));
        }

        if (mapSession.containsKey(TAG_EMAIL)) {

            Email = new MyStrings().objectToString(mapSession.get(TAG_EMAIL));
        }

        if (mapSession.containsKey(TAG_NAME)) {

            Name = new MyStrings().objectToString(mapSession.get(TAG_NAME));
        }

        if (mapSession.containsKey(TAG_PASSWORD)) {

            Password = new MyStrings().objectToString(mapSession.get(TAG_PASSWORD));
        }

        if (mapSession.containsKey(TAG_TYPE)) {

            Type = new MyStrings().objectToString(mapSession.get(TAG_TYPE));
        }

        Registered = isRegistered();
    }

    @Exclude
    public HashMap<String, Object> getSessionToUpdate() {

        HashMap<String, Object> mapSession = new HashMap<>();

        mapSession.put(TAG_DOCUMENT, Document);
        mapSession.put(TAG_EMAIL, Email);
        mapSession.put(TAG_NAME, Name);
        mapSession.put(TAG_PASSWORD, Password);
        mapSession.put(TAG_TYPE, Type);

        return mapSession;
    }


    /*----- Methods -----*/

    @Exclude
    private boolean isRegistered() {

        if (Email.equals("")) {

            return FALSE;

        } else {

            return TRUE;
        }
    }


    /*----- Get Parameters -----*/

    @Exclude
    public String getDocument() {

        return Document;
    }

    @Exclude
    public String getName() {

        return Name;
    }

    @Exclude
    public String getEmail() {

        return Email;
    }

    @Exclude
    public String getPassword() {

        return Password;
    }

    @Exclude
    public String getType() {

        return Type;
    }


    /*----- Set Parameters -----*/

    @Exclude
    public void setDocument(String Document) {

        this.Document = Document;
    }

    @Exclude
    public void setName(String Name) {

        this.Name = Name;
    }

    @Exclude
    public void setEmail(String Email) {

        this.Email = Email;
    }

    @Exclude
    public void setPassword(String Password) {

        this.Password = Password;
    }

    @Exclude
    public void setType(String Type) {

        this.Type = Type;
    }
}
