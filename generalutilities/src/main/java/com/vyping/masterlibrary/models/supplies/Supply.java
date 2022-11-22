package com.vyping.masterlibrary.models.supplies;

import static java.lang.Boolean.FALSE;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.RealData;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class Supply {

    @Exclude
    public static final String TAG_NAME = "Name", TAG_DESCRIPTION = "Description", TAG_UNITS = "Units", TAG_VALUE = "Value", TAG_QUANTITY = "Quantity", TAG_PROVIDER = "Provider", TAG_PHONE = "Phone";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_NAME, TAG_DESCRIPTION, TAG_UNITS, TAG_VALUE, TAG_QUANTITY})
    public @interface Tags {}

    @Exclude
    public String Id;
    public String Name, Description, Units, Provider, Phone;
    public int Value, Quantity;
    @Exclude
    public boolean selected;

    /*----- Main Model -----*/

    public Supply() {

        this.Id = "";
        this.Name = "";
        this.Description = "";
        this.Units = "";
        this.Provider = "";
        this.Phone = "";
        this.Quantity = 0;
        this.Value = 0;
        this.selected = FALSE;
    }

    public Supply(@NonNull DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Name = realData.getString(TAG_NAME);
        this.Description = realData.getString(TAG_DESCRIPTION);
        this.Units = realData.getString(TAG_UNITS);
        this.Provider = realData.getString(TAG_PROVIDER);
        this.Phone = realData.getString(TAG_PHONE);
        this.Quantity = realData.getIntegerOrDefault(TAG_QUANTITY, 0);
        this.Value = realData.getIntegerOrDefault(TAG_VALUE, 0);
        this.selected = FALSE;
    }
}
