package com.vyping.libraries.utilities.models.products;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.MyRealtimeReader;
import com.vyping.masterlibrary.Json.MyJsonReader;

import org.json.JSONObject;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class Product implements Serializable {

    @Exclude
    public static final String TAG_BARCODE = "BarCode", TAG_IMAGE = "Image", TAG_NAME = "Name", TAG_MARK = "Mark", TAG_DESCRIPTION = "Description", TAG_UNITS = "Units", TAG_SIZE = "Size", TAG_PREVPRICE = "PrevPrice", TAG_PRICE = "Price", TAG_STOCKMIN = "StockMin", TAG_STOCK = "Stock", TAG_STOCKMAX = "StockMax", TAG_LABELS = "Labels";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_BARCODE, TAG_IMAGE, TAG_NAME, TAG_MARK, TAG_DESCRIPTION, TAG_UNITS, TAG_SIZE, TAG_PREVPRICE, TAG_PRICE, TAG_STOCKMIN, TAG_STOCK, TAG_STOCKMAX, TAG_LABELS})
    public @interface Tags {}

    @Exclude
    public String BarCode;
    public String Image, Name, Mark, Description, Units;
    public int Size, PrevPrice, Price, StockMin, Stock, StockMax;
    public ArrayList<String> Labels;


    /*----- Main Model -----*/

    public Product() {}

    public Product(@NonNull DataSnapshot dataSnapshot) {

        this.BarCode = new MyRealtimeReader().getKeyString(dataSnapshot);
        this.Image = new MyRealtimeReader().getString(dataSnapshot.child(TAG_IMAGE));
        this.Name = new MyRealtimeReader().getString(dataSnapshot.child(TAG_NAME));
        this.Mark = new MyRealtimeReader().getString(dataSnapshot.child(TAG_MARK));
        this.Description = new MyRealtimeReader().getString(dataSnapshot.child(TAG_DESCRIPTION));
        this.Units = new MyRealtimeReader().getString(dataSnapshot.child(TAG_UNITS));
        this.Size = new MyRealtimeReader().getInteger(dataSnapshot.child(TAG_SIZE));
        this.PrevPrice = new MyRealtimeReader().getInteger(dataSnapshot.child(TAG_PREVPRICE));
        this.Price = new MyRealtimeReader().getInteger(dataSnapshot.child(TAG_PRICE));
        this.StockMin = new MyRealtimeReader().getInteger(dataSnapshot.child(TAG_STOCKMIN));
        this.Stock = new MyRealtimeReader().getInteger(dataSnapshot.child(TAG_STOCK));
        this.StockMax = new MyRealtimeReader().getInteger(dataSnapshot.child(TAG_STOCKMAX));
        this.Labels = new MyRealtimeReader().getArrayString(dataSnapshot.child(TAG_LABELS));
    }

    public Product(String BarCode, @NonNull JSONObject jsonObject) {

        this.BarCode = BarCode;
        this.Image = new MyJsonReader().getJsonString(jsonObject, TAG_IMAGE);
        this.Name = new MyJsonReader().getJsonString(jsonObject, TAG_IMAGE);
        this.Mark = new MyJsonReader().getJsonString(jsonObject, TAG_NAME);
        this.Units = new MyJsonReader().getJsonString(jsonObject, TAG_MARK);
        this.Description = new MyJsonReader().getJsonString(jsonObject, TAG_DESCRIPTION);
        this.Units = new MyJsonReader().getJsonString(jsonObject, TAG_UNITS);
        this.Size = new MyJsonReader().getJsonInt(jsonObject, TAG_SIZE);
        this.PrevPrice = new MyJsonReader().getJsonInt(jsonObject, TAG_PREVPRICE);
        this.Price = new MyJsonReader().getJsonInt(jsonObject, TAG_PRICE);
        this.StockMin = new MyJsonReader().getJsonInt(jsonObject, TAG_STOCKMIN);
        this.Stock = new MyJsonReader().getJsonInt(jsonObject, TAG_STOCK);
        this.StockMax = new MyJsonReader().getJsonInt(jsonObject, TAG_STOCKMAX);
        this.Labels = new MyJsonReader().getArrayList(jsonObject, TAG_LABELS);
    }
}
