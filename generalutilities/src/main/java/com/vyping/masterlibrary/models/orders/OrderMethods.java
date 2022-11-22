package com.vyping.masterlibrary.models.orders;

import static com.vyping.masterlibrary.aplication.MyApplication.ACCOUNT;
import static com.vyping.masterlibrary.definitions.Instances.INSTANCE_ORDERS;
import static com.vyping.masterlibrary.time.Definitions.FORMAT_PATH;
import static com.vyping.masterlibrary.models.orders.Order.TAG_CLIENT;
import static com.vyping.masterlibrary.models.orders.Order.TAG_GENERAL;
import static com.vyping.masterlibrary.models.orders.Order.TAG_PRODUCTS;
import static com.vyping.masterlibrary.models.orders.Order.TAG_TRASABILITY;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.binding.BindingMethods;
import com.vyping.masterlibrary.models.transactions.TransactionMethods;
import com.vyping.masterlibrary.time.MyTime;
import com.vyping.masterlibrary.models.orderproducts.OrderProduct;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class OrderMethods extends BindingMethods {

    public static final String TAG_TABLE = "Table", TAG_ADDRESS = "Address", TAG_NAME = "Name", TAG_PHONE = "Phone";
    public static final String TAG_NUMBER = "Number", TAG_COST = "Cost", TAG_TYPE = "Type", TAG_RESPONSIBLE = "Responsible", TAG_OBSERVATIONS = "Observations", TAG_REFERENCE = "Reference", TAG_METHOD = "Method";
    public static final String TAG_REQUEST = "Request", TAG_ACCEPTED = "Accepted", TAG_DISPATCHED = "Dispatched", TAG_DELIVERED = "Delivered", TAG_PAIDOUT = "Paidout";

    @Exclude
    public static final String TYPE_DOMICILE = "Domicilio", TYPE_TOGO = "Para Llevar", TYPE_RESTAURANT = "Restaurante";
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_CLIENT, TAG_GENERAL, TAG_PRODUCTS, TAG_TRASABILITY})
    public @interface Type {}

    public Order order;


    // ----- SetUp ----- //

    public OrderMethods() {

        this.order = new Order();

        setId(String.valueOf(new MyTime().getTimestamp()));
    }

    public OrderMethods(Order order) {

        this.order = order;
    }

    public OrderMethods(DataSnapshot dataSnapshot) {

        this.order = new Order(dataSnapshot);
    }


    // ----- Getters Methods ----- //

    public Order getOrder() {

        return order;
    }

    public String getId() {

        return order.Id;
    }

    public String getTable() {

        return (String) order.Client.get(TAG_TABLE);
    }

    public String getAddress() {

        String address = (String) order.Client.get(TAG_ADDRESS);

        if (address == null) {

            address = "";
        }

        return address;
    }

    public String getName() {

        String name = (String) order.Client.get(TAG_NAME);

        if (name == null) {

            name = "";
        }

        return name;
    }

    public String getPhone() {

        String phone = (String) order.Client.get(TAG_PHONE);

        if (phone == null) {

            phone = "";
        }

        return phone;
    }

    public String getNumber() {

        String number = (String) order.General.get(TAG_NUMBER);

        if (number == null) {

            number = "0";
        }

        return number;
    }

    public String getCost() {

        String cost = (String) order.General.get(TAG_COST);

        if (cost == null) {

            cost = "0";
        }

        return cost;
    }

    public String getType() {

        String type = (String) order.General.get(TAG_TYPE);

        if (type == null) {

            type = "Domicilio";
        }

        return type;
    }

    public String getResponsible() {

        String responsible = (String) order.General.get(TAG_RESPONSIBLE);

        if (responsible == null) {

            responsible = "0";
        }

        return responsible;
    }

    public String getObservations() {

        String observations = (String) order.General.get(TAG_OBSERVATIONS);

        if (observations == null) {

            observations = "";
        }

        return observations;
    }

    public String getReference() {

        String reference = (String) order.General.get(TAG_REFERENCE);

        if (reference == null) {

            reference = "";
        }

        return reference;
    }

    public String getMethod() {

        String method = (String) order.General.get(TAG_METHOD);

        if (method == null) {

            method = "";
        }

        return method;
    }

    public String getRequest() {

        String request = (String) order.Trasability.get(TAG_REQUEST);

        if (request == null) {

            request = "0";
        }

        return request;
    }

    public String getAccepted() {

        String accepted = (String) order.Trasability.get(TAG_ACCEPTED);

        if (accepted == null) {

            accepted = "0";
        }

        return accepted;
    }

    public String getDispatched() {

        String dispatched = (String) order.Trasability.get(TAG_DISPATCHED);

        if (dispatched == null) {

            dispatched = "0";
        }

        return dispatched;
    }

    public String getDelivered() {

        String delivered = (String) order.Trasability.get(TAG_DELIVERED);

        if (delivered == null) {

            delivered = "0";
        }

        return delivered;
    }

    public String getPaidOut() {

        String paidOut = (String) order.Trasability.get(TAG_PAIDOUT);

        if (paidOut == null) {

            paidOut = "0";
        }

        return paidOut;
    }

    public HashMap<String, OrderProduct> getProducts() {

        return order.Products;
    }

    public boolean getSelected() {

        return order.selected;
    }


    // ----- Getters Methods ----- //

    public void setOrder(Order order) {

        this.order = order;
    }

    public void setId(String id) {

        this.order.Id = id;
    }

    public void setTable(String table) {

        this.order.Client.put(TAG_TABLE, table);
    }

    public void setAddress(String address) {

        this.order.Client.put(TAG_ADDRESS, address);
    }

    public void setName(String name) {

        this.order.Client.put(TAG_NAME, name);
    }

    public void setPhone(String imageName) {

        this.order.Client.put(TAG_PHONE, imageName);
    }

    public void setNumber(Object number) {

        this.order.General.put(TAG_NUMBER, number);
    }

    public void setCost(String cost) {

        this.order.General.put(TAG_COST, cost);
    }

    public void setType(String type) {

        this.order.General.put(TAG_TYPE, type);
    }

    public void setResponsible(String responsible) {

        this.order.General.put(TAG_RESPONSIBLE, responsible);
    }

    public void setObservations(String observations) {

        this.order.General.put(TAG_OBSERVATIONS, observations);
    }

    public void setReference(String reference) {

        this.order.General.put(TAG_REFERENCE, reference);
    }

    public void setMethod(@TransactionMethods.Method String method) {

        this.order.General.put(TAG_METHOD, method);
    }

    public void setRequest(String request) {

        this.order.Trasability.put(TAG_REQUEST, request);
    }

    public void setAccepted(String accepted) {

        this.order.Trasability.put(TAG_ACCEPTED, accepted);
    }

    public void setDispatched(String dispatched) {

        this.order.Trasability.put(TAG_DISPATCHED, dispatched);
    }

    public void setDelivered(String delivered) {

        this.order.Trasability.put(TAG_DELIVERED, delivered);
    }

    public void setPaidOut(String paidOut) {

        this.order.Trasability.put(TAG_PAIDOUT, paidOut);
    }

    public void setProducts(HashMap<String, OrderProduct> products) {

        this.order.Products = products;
    }

    public void setSelected(boolean selected) {

        this.order.selected = selected;
    }


    // ----- Search ModelMethods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(getName(), getAddress(), getPhone()));
    }


    // ----- Compound ModelMethods ----- //


    public HashMap<String, Object> setToUpload() {

        HashMap<String, Object> mapOrder = new HashMap<>();
        mapOrder.put(TAG_CLIENT, order.Client);
        mapOrder.put(TAG_GENERAL, order.General);
        mapOrder.put(TAG_TRASABILITY, order.Trasability);
        mapOrder.put(TAG_PRODUCTS, order.Products);

        return mapOrder;
    }

    public OrderProduct getProduct(String idProduct) {

        OrderProduct orderProduct = null;

        for (Map.Entry<String, OrderProduct> entryOrderProduct : order.Products.entrySet()) {

            OrderProduct compareOrderProduct = entryOrderProduct.getValue();
            String idCompare = compareOrderProduct.Id;

            if (idProduct.equals(idCompare)) {

                orderProduct = compareOrderProduct;
            }
        }

        return orderProduct;
    }

    public void setProduct(OrderProduct products) {

        this.order.Products.put(products.Id, products);
    }

    public void removeProduct(OrderProduct products) {

        this.order.Products.remove(products.Id);
    }


    // ----- Firebase Methods ----- //

    public void saveOrder() {

        String path = new MyTime().getTime(FORMAT_PATH);

        setResponsible(ACCOUNT.getName());

        new MyRealtime(INSTANCE_ORDERS, path).setValue(getId(), getOrder());
    }

    public void saveOrder(MyRealtime.CompleteListener listener) {

        String path = new MyTime().getTime(FORMAT_PATH);

        setResponsible(ACCOUNT.getName());

        new MyRealtime(INSTANCE_ORDERS, path).setValue(getId(), getOrder(), listener);
    }

    public void uploadToFirebase() {

        String path = new MyTime().getTime(FORMAT_PATH);

        new MyRealtime(INSTANCE_ORDERS, path).updateChild(getId(), setToUpload());
    }

    public void uploadToFirebase(MyRealtime.CompleteListener listener) {

        String path = new MyTime().getTime(FORMAT_PATH);

        new MyRealtime(INSTANCE_ORDERS, path).updateChild(getId(), setToUpload(), listener);
    }

}
