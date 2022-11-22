package com.vyping.masterlibrary.models.accounts;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Common.MyPhone;
import com.vyping.masterlibrary.Firebase.RealData;
import com.vyping.masterlibrary.models.images.Image;
import com.vyping.masterlibrary.time.MyTime;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Account implements Serializable {

    @Exclude public static final String TAG_ID = "Id", TAG_IMAGE = "Image", TAG_DOCUMENT = "Document", TAG_UID = "Uid", TAG_EMAIL = "Email", TAG_PHONE = "Phone", TAG_ADDRESS = "Address", TAG_NAME = "Name", TAG_TYPE = "Type", TAG_STATUS = "Status", TAG_DISPOSITIVE = "Dispositive", TAG_TOKEN = "Token", TAG_LASTLOGIN = "LastLogin";
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_ID, TAG_IMAGE, TAG_DOCUMENT, TAG_UID, TAG_EMAIL, TAG_PHONE, TAG_ADDRESS, TAG_NAME, TAG_TYPE, TAG_STATUS, TAG_DISPOSITIVE, TAG_TOKEN})
    public @interface Tags {}

    public static final String ACCOUNT_SUPPORT = "Soporte Técnico", ACCOUNT_OWNER = "Propietario", ACCOUNT_ADMIN = "Administrador", ACCOUNT_CLIENT = "Cliente", ACCOUNT_GUEST = "Invitado";

    @Exclude
    public String Id;
    public String Document, Uid, Email, Phone, Address, Name, Type, Dispositive, Token;
    public long LastLogin;
    public boolean Status;
    public Image Image;


    /*----- Main Model -----*/

    public Account() {

        this.Id = "";
        this.Document = "";
        this.Uid = "";
        this.Email = "";
        this.Phone = "";
        this.Address = "";
        this.Name = "";
        this.Type = ACCOUNT_GUEST;
        this.Dispositive = "";
        this.Token = "";
        this.Status = FALSE;
        this.Image = new Image();
        this.LastLogin = new MyTime().getTimestamp();
    }

    public Account(@NonNull DataSnapshot dataSession) {

        RealData realData = new RealData(dataSession);

        this.Id = realData.getKeyString();
        this.Document = realData.getString(TAG_DOCUMENT);
        this.Uid = realData.getString(TAG_UID);
        this.Email = realData.getString(TAG_EMAIL);
        this.Phone = realData.getString(TAG_PHONE);
        this.Address = realData.getString(TAG_ADDRESS);
        this.Name = realData.getString(TAG_NAME);
        this.Type = realData.getStringOrDefault(TAG_TYPE, ACCOUNT_GUEST);
        this.Dispositive = realData.getString(TAG_DISPOSITIVE);
        this.Token = realData.getString(TAG_TOKEN);
        this.Status = realData.getBooleanOrDefault(TAG_STATUS, FALSE);
        this.Image = realData.getImage(TAG_IMAGE);
        this.LastLogin = realData.getLong(TAG_LASTLOGIN);
    }

    public Account(@NonNull FirebaseUser firebaseUser) {

        this.Id = firebaseUser.getUid();
        this.Document = "";
        this.Uid = firebaseUser.getUid();
        this.Email = firebaseUser.getEmail() != null ? firebaseUser.getEmail() : "";
        this.Phone = firebaseUser.getPhoneNumber() != null ? firebaseUser.getPhoneNumber() : "";
        this.Address = "";
        this.Name = firebaseUser.getDisplayName() != null ? firebaseUser.getDisplayName() : "";
        this.Type = ACCOUNT_GUEST;
        this.Dispositive = new MyPhone().phoneModel();
        this.Token = "";
        this.Status = TRUE;
        this.Image = new Image();
        this.LastLogin = new MyTime().getTimestamp();

        if (firebaseUser.getPhotoUrl() != null) {

            this.Image.Url = firebaseUser.getPhotoUrl().toString();
        }
    }
}
