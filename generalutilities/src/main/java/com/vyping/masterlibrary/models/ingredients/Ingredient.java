package com.vyping.masterlibrary.models.ingredients;

import static java.lang.Boolean.FALSE;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;
import androidx.databinding.BindingAdapter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.Firebase.RealData;
import com.vyping.masterlibrary.binding.BindingMethods;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;

public class Ingredient implements Serializable {

    @Exclude
    public static final String TAG_NAME = "Name", TAG_UNITS = "Units", TAG_QUANTITY = "Quantity", TAG_VALUE = "Value";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_NAME, TAG_UNITS, TAG_QUANTITY, TAG_VALUE})
    public @interface Tags {}

    @Exclude
    public String Id;
    public String Name, Units;
    public int Quantity, Value;
    @Exclude
    public boolean selected;


    /*----- Main Model -----*/

    public Ingredient() {

        this.Id = "";
        this.Name = "";
        this.Units = "";
        this.Quantity = 0;
        this.Value = 0;
        this.selected = FALSE;
    }

    public Ingredient(@NonNull DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Name = realData.getString(TAG_NAME);
        this.Units = realData.getString(TAG_UNITS);
        this.Quantity = realData.getInteger(TAG_QUANTITY);
        this.Value = realData.getIntegerOrDefault(TAG_VALUE, 0);
        this.selected = FALSE;
    }
}

