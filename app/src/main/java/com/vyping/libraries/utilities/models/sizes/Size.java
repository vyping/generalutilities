package com.vyping.libraries.utilities.models.sizes;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.RealData;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

public class Size extends RealData {

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
    
    public Size(@NonNull DataSnapshot dataSnapshot) {

        super(dataSnapshot);

        this.BarCode = getKeyString();
        this.Image = getString(TAG_IMAGE);
        this.Name = getString(TAG_NAME);
        this.Mark = getString(TAG_MARK);
        this.Description = getString(TAG_DESCRIPTION);
        this.Units = getString(TAG_UNITS);
        this.Size = getInteger(TAG_SIZE);
        this.PrevPrice = getInteger(TAG_PREVPRICE);
        this.Price = getInteger(TAG_PRICE);
        this.StockMin = getInteger(TAG_STOCKMIN);
        this.Stock = getInteger(TAG_STOCK);
        this.StockMax = getInteger(TAG_STOCKMAX);
        this.Labels = getArrayString(TAG_LABELS);
    }
}
