package com.vyping.masterlibrary.models.orders;

import static java.lang.Boolean.FALSE;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.RealData;
import com.vyping.masterlibrary.models.orderproducts.OrderProduct;

import java.io.Serializable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.HashMap;

public class Order implements Serializable {

    @Exclude
    public static final String TAG_CLIENT = "Client", TAG_GENERAL = "General", TAG_PRODUCTS = "Products", TAG_TRASABILITY = "Trasability";
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_CLIENT, TAG_GENERAL, TAG_PRODUCTS, TAG_TRASABILITY})
    public @interface Tags {}

    @Exclude
    public String Id;
    public HashMap<String, Object> Client, General, Trasability;
    public HashMap<String, OrderProduct> Products;
    @Exclude
    public boolean selected;


    /*----- Main Model -----*/

    public Order() {

        this.Id = "";
        this.Client = new HashMap<>();
        this.General = new HashMap<>();
        this.Products = new HashMap<>();
        this.Trasability = new HashMap<>();
        this.Products = new HashMap<>();
        this.selected = FALSE;
    }

    public Order(@NonNull DataSnapshot dataSnapshot) {

        RealData realData = new RealData(dataSnapshot);

        this.Id = realData.getKeyString();
        this.Client = realData.getHashMap(TAG_CLIENT);
        this.General = realData.getHashMap(TAG_GENERAL);
        this.Trasability = realData.getHashMap(TAG_TRASABILITY);
        this.Products = new HashMap<>();

        for (DataSnapshot subDataSnapshot : dataSnapshot.child(TAG_PRODUCTS).getChildren()) {

            String id = subDataSnapshot.getKey();
            OrderProduct subOrder = new OrderProduct(subDataSnapshot);
            subOrder.Id = id;

            Products.put(subOrder.Id, subOrder);
        }

        this.selected = FALSE;
    }
}
