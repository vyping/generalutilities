package com.vyping.masterlibrary.models.accounts;

import static com.vyping.masterlibrary.definitions.Instances.INSTANCE_ACCOUNTS;
import static com.vyping.masterlibrary.definitions.Instances.INSTANCE_MAIN;
import static com.vyping.masterlibrary.definitions.Instances.RESTAURANT;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_ADDRESS;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_DISPOSITIVE;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_DOCUMENT;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_EMAIL;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_ID;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_IMAGE;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_LASTLOGIN;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_NAME;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_PHONE;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_STATUS;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_TOKEN;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_TYPE;
import static com.vyping.masterlibrary.models.accounts.Account.TAG_UID;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.binding.BindingMethods;
import com.vyping.masterlibrary.models.images.Image;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AccountMethods extends BindingMethods {

    public Account account;
    private boolean Logged;
    private Interfase interfase;


    // ----- SetUp ----- //

    public AccountMethods() {

        this.account = new Account();
    }

    public AccountMethods(Account account) {

        this.account = account;
    }

    public AccountMethods(DataSnapshot dataSnapshot) {

        this.account = new Account(dataSnapshot);
    }

    public AccountMethods(FirebaseUser firebaseUser) {

        this.account = new Account(firebaseUser);
    }


    // ----- Getters Methods ----- //

    public String getId() {

        return account.Id;
    }

    public Account getAccount()
    {
        return account;
    }

    public Image getImage()
    {
        return account.Image;
    }

    public String getDocument()
    {
        return account.Document;
    }

    public String getUid()
    {
        return account.Uid;
    }

    public String getEmail()
    {
        return account.Email;
    }

    public String getPhone()
    {
        return account.Phone;
    }

    public String getAddress()
    {
        return account.Address;
    }

    public String getName()
    {
        return account.Name;
    }

    public String getType()
    {
        return account.Type;
    }

    public String getDispositive()
    {
        return account.Dispositive;
    }

    public String getToken()
    {
        return account.Token;
    }

    public Boolean getStatus()
    {
        return account.Status;
    }

    public Boolean getLogged()
    {
        return Logged;
    }

    public long getLastLogin() { return account.LastLogin; }


    // ----- Setters Methods ----- //

    public void setId(String id) {

        this.account.Id = id;

        if (interfase != null) {

            interfase.AccountChanged(TAG_ID, id);
        }
    }

    public void setAccount(Account account) {

        this.account = account;

        if (interfase != null) {

            interfase.AccountChanged(account);
        }
    }

    public void setAccount(FirebaseUser firebaseUser) {

        this.account = new Account(firebaseUser);

        if (interfase != null) {

            interfase.AccountChanged(account);
        }
    }

    public void setImage(Image image) {

        this.account.Image = image;

        if (interfase != null) {

            interfase.ImageChanged(image);
        }
    }

    public void setDocument(String document) {

        this.account.Document = document;

        if (interfase != null) {

            interfase.AccountChanged(TAG_DOCUMENT, document);
        }
    }

    public void setUid(String uid) {

        this.account.Uid = uid;

        if (interfase != null) {

            interfase.AccountChanged(TAG_UID, uid);
        }
    }

    public void setEmail(String email) {

        this.account.Email = email;

        if (interfase != null) {

            interfase.AccountChanged(TAG_EMAIL, email);
        }
    }

    public void setPhone(String phone) {

        this.account.Phone = phone;

        if (interfase != null) {

            interfase.AccountChanged(TAG_PHONE, phone);
        }
    }

    public void setAddress(String address) {

        this.account.Address = address;

        if (interfase != null) {

            interfase.AccountChanged(TAG_ADDRESS, address);
        }
    }

    public void setName(String name) {

        this.account.Name = name;

        if (interfase != null) {

            interfase.AccountChanged(TAG_NAME, name);
        }
    }

    public void setType(String type) {

        this.account.Type = type;

        if (interfase != null) {

            interfase.AccountChanged(TAG_TYPE, type);
        }
    }

    public void setDispositive(String dispositive) {

        this.account.Dispositive = dispositive;

        if (interfase != null) {

            interfase.AccountChanged(TAG_DISPOSITIVE, dispositive);
        }
    }

    public void setToken(String token) {

        this.account.Token = token;

        if (interfase != null) {

            interfase.AccountChanged(TAG_TOKEN, token);
        }
    }

    public void setStatus(boolean status) {

        this.account.Status = status;

        if (interfase != null) {

            interfase.StatusChanged(status);
        }
    }

    public void setLogged(boolean logged) {

        setUid("");
        setEmail("");
        setPhone("");

        this.Logged = logged;

        if (interfase != null) {

            interfase.LoggedChanged(logged);
        }
    }

    public void setLastLogin(long lastlogin) {

        this.account.LastLogin = lastlogin;
    }


    // ----- Search Methods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(account.Name, account.Type, account.Address, account.Phone, account.Email, account.Document, account.Dispositive));
    }


    // ----- Tools ----- //

    public AccountMethods readChanges(Interfase interfase) {

        this.interfase = interfase;

        return this;
    }

    @NonNull
    public HashMap<String, Object> setToUpload() {

        HashMap<String, Object> mapAccount = new HashMap<String, Object>();

        mapAccount.put(TAG_IMAGE, getImage());
        mapAccount.put(TAG_DOCUMENT, getDocument());
        mapAccount.put(TAG_UID, getUid());
        mapAccount.put(TAG_EMAIL, getEmail());
        mapAccount.put(TAG_PHONE, getPhone());
        mapAccount.put(TAG_ADDRESS, getAddress());
        mapAccount.put(TAG_NAME, getName());
        mapAccount.put(TAG_TYPE, getType());
        mapAccount.put(TAG_STATUS, getStatus());
        mapAccount.put(TAG_DISPOSITIVE, getDispositive());
        mapAccount.put(TAG_TOKEN, getToken());
        mapAccount.put(TAG_LASTLOGIN, getLastLogin());

        return mapAccount;
    }


    // ----- Firebase Methods ----- //

    public void createToFirebase() {

        new MyRealtime(INSTANCE_ACCOUNTS).setValue(getId(),getAccount());
        new MyRealtime(INSTANCE_MAIN).setValue(getId(), RESTAURANT);
    }

    public void createToFirebase(MyRealtime.CompleteListener listener) {

        new MyRealtime(INSTANCE_ACCOUNTS).setValue(getId(),getAccount(), listener);
        new MyRealtime(INSTANCE_MAIN).setValue(getId(), RESTAURANT);
    }

    public void updateOnFirebase() {

        new MyRealtime(INSTANCE_ACCOUNTS).updateChild(getId(), setToUpload());
    }

    public void updateOnFirebase(MyRealtime.CompleteListener listener) {

        new MyRealtime(INSTANCE_ACCOUNTS).updateChild(getId(), setToUpload(), listener);
    }

    public void eraseFromFirebase() {

        new MyRealtime(INSTANCE_ACCOUNTS).removeChild(getId());
        new MyRealtime(INSTANCE_MAIN).removeChild(getId());
    }

    public void eraseFromFirebase(MyRealtime.CompleteListener listener) {

        new MyRealtime(INSTANCE_ACCOUNTS).removeChild(getId(), listener);
        new MyRealtime(INSTANCE_MAIN).removeChild(getId());
    }


    // ----- Interface ----- //

    public interface Interfase {

        public default void AccountChanged(Account account) {};
        public default void AccountChanged(String tag, String value) {};
        public default void ImageChanged(Image image) {};
        public default void StatusChanged(boolean log) {};
        public default void LoggedChanged(boolean log) {};
    }
}
