package com.vyping.libraries.utilities.models.users;

import androidx.annotation.NonNull;
import androidx.databinding.BaseObservable;

import java.util.ArrayList;
import java.util.Arrays;

public class UserMethods extends BaseObservable {

    private final User user;


    // ----- SetUp ----- //

    public UserMethods(User user)
    {
        this.user = user;
    }


    // ----- Basic Methods ----- //

    public User getUser()
    {
        return user;
    }

    public String getId()
    {
        return user.Id;
    }

    public void setId(String id) {

        user.Id = id;
    }

    public String getUid() { return user.Uid; }

    public void setUid(String Uid) { user.Uid = Uid; }

    public String getToken()
    {
        return user.Token;
    }

    public void setToken(String token) {

        user.Token = token;
    }

    public String getDocument()
    {
        return user.Document;
    }

    public void setDocument(String document) {

        user.Document = document;
    }

    public String getName()
    {
        return user.Name;
    }

    public void setName(String name) {

        user.Name = name;
    }

    public void setPhone(String phone) {

        user.Phone = phone;
    }

    public String getPhone()
    {
        return user.Phone;
    }

    public void setEmail(String email) {

        user.Email = email;
    }

    public String getEmail()
    {
        return user.Email;
    }

    public String getPassword()
    {
        return user.Password;
    }

    public void setPassword(String password) {

        user.Password = password;
    }

    public String getType()
    {
        return user.Type;
    }

    public void setType(String type) {

        user.Type = type;
    }


    // ----- Search Methods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(user.Id, user.Document, user.Name, user.Email, user.Password, user.Type));
    }
}
