package com.vyping.masterlibrary.Models;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.Firebase.MyRealtimeReader;
import com.vyping.masterlibrary.Images.MyColor;
import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.R;

public class MenuParams {

    public String Title, SubTitle, Description;
    public Drawable Background, Icon;
    public int BackgroundColor, IconColor, SubTitleColor;


    /*----- Main Model -----*/

    public MenuParams(Context context, int BackgroundColor, int Icon, int Title, String TitleColor, int SubTitleColor, String Description) {

        this.Background = new MyDrawable().extractFromResources(context, R.drawable.background_window);
        this.BackgroundColor = BackgroundColor;
        this.Icon = new MyDrawable().extractFromResources(context, Icon);
        this.Title = new MyString().getStringResources(context, Title);
        this.SubTitle = TitleColor;
        this.SubTitleColor = SubTitleColor;
        this.Description = Description;
    }

    public MenuParams(Context context, int Background, int BackgroundColor, int Icon, int Name, String Traduction, int TraductionColor, String Description) {

        this.Background = new MyDrawable().extractFromResources(context, Background);
        this.BackgroundColor = BackgroundColor;
        this.Icon = new MyDrawable().extractFromResources(context, Icon);
        this.IconColor = TraductionColor;
        this.Title = new MyString().getStringResources(context, Name);
        this.SubTitle = Traduction;
        this.Description = Description;
    }

    public MenuParams(Drawable Background, int BackgroundColor, Drawable Icon, String Name, String Traduction, int TraductionColor, String Description) {

        this.Background = Background;
        this.BackgroundColor = BackgroundColor;
        this.Icon = Icon;
        this.IconColor = TraductionColor;
        this.Title = Name;
        this.SubTitle = Traduction;
        this.Description = Description;
    }

    public MenuParams(Context context, @NonNull DataSnapshot snapshot) {

        String background = new MyRealtimeReader().getString(snapshot.child("Background"));
        String backgroundColor = new MyRealtimeReader().getString(snapshot.child("BackgroundColor"));
        String icon = new MyRealtimeReader().getString(snapshot.child("Icon"));
        String iconColor = new MyRealtimeReader().getString(snapshot.child("TraductionColor"));

        this.Background = new MyDrawable().extractFromString(context, background);
        this.BackgroundColor = new MyColor().extractFromString(context, backgroundColor);
        this.Icon = new MyDrawable().extractFromString(context, icon);
        this.IconColor = new MyColor().extractFromString(context, iconColor);
        this.Title = new MyRealtimeReader().getString(snapshot.child("Name"));
        this.SubTitle = new MyRealtimeReader().getString(snapshot.child("Traduction"));
        this.Description = new MyRealtimeReader().getString(snapshot.child("Description"));
    }
}
