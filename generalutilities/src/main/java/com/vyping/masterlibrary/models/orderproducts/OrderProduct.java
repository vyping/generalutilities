package com.vyping.masterlibrary.models.orderproducts;

import static java.lang.Boolean.FALSE;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.RealData;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class OrderProduct implements Serializable {

    @Exclude
    public static final String TAG_NAME = "Name", TAG_PROPERTIES = "Properties", TAG_QUANTITY = "Quantity", TAG_VALUE_UNITARY = "Price", TAG_VALUE_TOTAL = "PriceTotal", TAG_VALUE_DELIVERED= "Delivered", TAG_DESCRIPTION = "Description", TAG_SIZE= "Size", TAG_POTATOES = "Potatoes", TAG_POTATOES_FRENCH = "French", TAG_POTATOES_CREOLE = "Creole";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_NAME, TAG_PROPERTIES, TAG_QUANTITY, TAG_VALUE_UNITARY, TAG_VALUE_TOTAL, TAG_POTATOES, TAG_POTATOES_FRENCH, TAG_POTATOES_CREOLE})
    public @interface Tags {}

    @Exclude
    public String Id, Description, Size;
    public String Name, Properties, Quantity, Price, PriceTotal, Delivered;
    public int French, Creole;
    public int Potatoes;
    @Exclude
    public boolean selected;

    /*----- Main Model -----*/

    public OrderProduct() {

        this.Id = "";
        this.Name = "";
        this.Properties = "";
        this.Quantity = "0";
        this.Price = "0";
        this.PriceTotal = "0";
        this.Delivered = "0";
        this.Description = "";
        this.Size = "";
        this.French = 0;
        this.Creole = 0;
        this.Potatoes = 0;
        this.selected = FALSE;
    }

    public OrderProduct(@NonNull DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Name = realData.getString(TAG_NAME);
        this.Properties = realData.getString(TAG_PROPERTIES);
        this.Quantity = realData.getStringOrDefault(TAG_QUANTITY, "0");
        this.Price = realData.getStringOrDefault(TAG_VALUE_UNITARY, "0");
        this.PriceTotal = realData.getStringOrDefault(TAG_VALUE_TOTAL, Price);
        this.Delivered = realData.getStringOrDefault(TAG_VALUE_DELIVERED, "0");
        this.Description = realData.getStringOrDefault(TAG_DESCRIPTION, "");
        this.Size = realData.getStringOrDefault(TAG_SIZE, "");
        this.Potatoes = realData.getIntegerOrDefault(TAG_POTATOES, 0);
        this.French = realData.getIntegerOrDefault(TAG_POTATOES_FRENCH, 0);
        this.Creole = realData.getIntegerOrDefault(TAG_POTATOES_CREOLE, 0);
        this.selected = FALSE;
    }
}
