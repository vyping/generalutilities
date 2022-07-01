package com.vyping.masterlibrary.models;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.Firebase.RealData;
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

        RealData realData = new RealData(snapshot);

        String background = realData.getString("Background");
        String backgroundColor = realData.getString("BackgroundColor");
        String icon = realData.getString("Icon");
        String iconColor = realData.getString("TraductionColor");

        this.Background = new MyDrawable().extractFromString(context, background);
        this.BackgroundColor = new MyColor().extractFromString(context, backgroundColor);
        this.Icon = new MyDrawable().extractFromString(context, icon);
        this.IconColor = new MyColor().extractFromString(context, iconColor);
        this.Title = realData.getString("Name");
        this.SubTitle = realData.getString("Traduction");
        this.Description = realData.getString("Description");
    }
}
