package com.vyping.libraries.utilities.models.users;

import static java.lang.Boolean.FALSE;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Firebase.RealData;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class User {

    public static final String TAG_UID = "Uid", TAG_TOKEN = "Token", TAG_DOCUMENT = "Document", TAG_NAME = "Name", TAG_PHONE = "Phone", TAG_EMAIL = "Email", TAG_PASSWORD = "Password", TAG_TYPE = "Type";
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_TOKEN, TAG_UID, TAG_DOCUMENT, TAG_NAME, TAG_EMAIL, TAG_PASSWORD, TAG_TYPE})
    public @interface Tags {}

    public String Id, Uid, Token, Document, Name, Phone, Email, Password, Type;


    /*----- Main Model -----*/

    public User(@NonNull FirebaseUser firebaseUser) {

        this.Id = "";
        this.Uid = firebaseUser.getUid();
        this.Token = "";
        this.Document = "";
        this.Name = firebaseUser.getDisplayName();
        this.Phone = firebaseUser.getPhoneNumber();
        this.Email = firebaseUser.getEmail();
        this.Password = "";
        this.Type = "";
    }

    public User(DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Uid = realData.getString(TAG_UID);
        this.Token = realData.getString(TAG_TOKEN);
        this.Document = realData.getString(TAG_DOCUMENT);
        this.Name = realData.getString(TAG_NAME);
        this.Phone = realData.getString(TAG_PHONE);
        this.Email = realData.getString(TAG_EMAIL);
        this.Password = realData.getString(TAG_PASSWORD);
        this.Type = realData.getString(TAG_TYPE);
    }
}
