package com.vyping.masterlibrary.models.transactions;

import static com.vyping.masterlibrary.aplication.MyApplication.ACCOUNT;
import static com.vyping.masterlibrary.definitions.Instances.INSTANCE_TRANSACTIONS;
import static com.vyping.masterlibrary.time.Definitions.FORMAT_HOUR_01;
import static com.vyping.masterlibrary.time.Definitions.FORMAT_PATH;
import static com.vyping.masterlibrary.models.orders.OrderMethods.TAG_RESPONSIBLE;
import static com.vyping.masterlibrary.models.orders.OrderMethods.TAG_TYPE;
import static com.vyping.masterlibrary.models.transactions.Transaction.TAG_DESCRIPTION;
import static com.vyping.masterlibrary.models.transactions.Transaction.TAG_HOUR;
import static com.vyping.masterlibrary.models.transactions.Transaction.TAG_METHOD;
import static com.vyping.masterlibrary.models.transactions.Transaction.TAG_MOUNT;
import static com.vyping.masterlibrary.models.transactions.Transaction.TAG_REFERENCE;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.binding.BindingMethods;
import com.vyping.masterlibrary.models.orders.OrderMethods;
import com.vyping.masterlibrary.time.MyTime;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class TransactionMethods extends BindingMethods {

    @Exclude
    public static final String TYPE_SPENT = "Gasto", TYPE_ENTRY = "Venta";
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TYPE_SPENT, TYPE_ENTRY})
    public @interface Type {}

    @Exclude
    public static final String METHOD_EFECTIVE = "Efectivo", METHOD_CARD = "Tarjeta", METHOD_TRASFERENCE = "Trasferencia", METHOD_NEQUI = "Nequi", METHOD_DAVIPLATA = "Daviplata";
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({METHOD_EFECTIVE, METHOD_CARD, METHOD_TRASFERENCE, METHOD_NEQUI, METHOD_DAVIPLATA})
    public @interface Method {}

    public Transaction transaction;


    // ----- SetUp ----- //

    public TransactionMethods() {

        this.transaction = new Transaction();

        setId(String.valueOf(new MyTime().getTimestamp()));
    }

    public TransactionMethods(Transaction transaction) {

        this.transaction = transaction;
    }

    public TransactionMethods(DataSnapshot dataSnapshot) {

        this.transaction = new Transaction(dataSnapshot);
    }


    // ----- Getters Methods ----- //

    public Transaction getTransaction() {

        return transaction;
    }

    public String getId() {

        return transaction.Id;
    }

    public String getNumber() {

        return transaction.Number;
    }

    public String getHour() {

        return transaction.Hour;
    }

    public String getMount() {

        return transaction.Mount;
    }

    public String getResponsible() {

        return transaction.Responsible;
    }

    public String getDescription() {

        return transaction.Description;
    }

    public String getType() {

        return transaction.Type;
    }

    public String getReference() {

        return transaction.Reference;
    }

    public String getMethod() {

        return transaction.Method;
    }

    public boolean getSelected() {

        return transaction.Selected;
    }


    // ----- Getters Methods ----- //

    public void setTransaction(Transaction transaction) {

        this.transaction = transaction;
    }

    public void setId(String id) {

        this.transaction.Id = id;
    }

    public void setNumber(String number) {

        this.transaction.Number = number;
    }

    public void setHour(String hour) {

        this.transaction.Hour = hour;
    }

    public void setMount(String mount) {

        this.transaction.Mount = mount;
    }

    public void setResponsible(String responsible) {

        this.transaction.Responsible = responsible;
    }

    public void setDescription(String Description) {

        this.transaction.Description = Description;
    }

    public void setType(@Type String type) {

        this.transaction.Type = type;
    }

    public void setReference(String reference) {

        this.transaction.Reference = reference;
    }

    public void setMethod(@Method String method) {

        this.transaction.Method = method;
    }

    public void setSelected(boolean selected) {

        this.transaction.Selected = selected;
    }


    // ----- Search ModelMethods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(getResponsible()));
    }


    // ----- Converters Methods ----- //

    public TransactionMethods convertFromOrderMethods(@NonNull OrderMethods orderMethods) {

        String description = "";

        if (orderMethods.getType().equals("Restaurante")) {

            String table = orderMethods.getTable();
            String hour = new MyTime(orderMethods.getPaidOut()).getTime(FORMAT_HOUR_01);

            description = "Mesa " + table + " a las " + hour;
        }

        setId(orderMethods.getId());
        setNumber(orderMethods.getNumber());
        setHour(orderMethods.getPaidOut());
        setMount(orderMethods.getCost());
        setResponsible(orderMethods.getResponsible());
        setReference(orderMethods.getReference());
        setMethod(orderMethods.getMethod());
        setDescription(description);
        setType(TYPE_ENTRY);

        return this;
    }


    // ----- Firebase Methods ----- //

    public void saveToFirebase() {

        String hour = String.valueOf(new MyTime().getTimestamp());
        String path = new MyTime().getTime(FORMAT_PATH);

        setHour(hour);
        setResponsible(ACCOUNT.getName());

        new MyRealtime(INSTANCE_TRANSACTIONS, path).setValue(getId(), getTransaction());
    }

    public void saveToFirebase(MyRealtime.CompleteListener listener) {

        String hour = String.valueOf(new MyTime().getTimestamp());
        String path = new MyTime().getTime(FORMAT_PATH);
        setHour(hour);
        setResponsible(ACCOUNT.getName());

        new MyRealtime(INSTANCE_TRANSACTIONS, path).setValue(getId(), getTransaction(), listener);
    }

    public void uploadToFirebase() {

        String path = new MyTime().getTime(FORMAT_PATH);

        new MyRealtime(INSTANCE_TRANSACTIONS, path).updateChild(getId(), setToUpload());
    }

    public void uploadToFirebase(MyRealtime.CompleteListener listener) {

        String path = new MyTime().getTime(FORMAT_PATH);

        new MyRealtime(INSTANCE_TRANSACTIONS, path).updateChild(getId(), setToUpload(), listener);
    }


    // ----- Tools ----- //

    public HashMap<String, Object> setToUpload() {

        HashMap<String, Object> mapTransaction = new HashMap<>();
        mapTransaction.put(TAG_DESCRIPTION, getDescription());
        mapTransaction.put(TAG_HOUR, getHour());
        mapTransaction.put(TAG_MOUNT, getMount());
        mapTransaction.put(TAG_RESPONSIBLE, getResponsible());
        mapTransaction.put(TAG_TYPE, getType());
        mapTransaction.put(TAG_REFERENCE, getReference());
        mapTransaction.put(TAG_METHOD, getMethod());

        return mapTransaction;
    }
}
