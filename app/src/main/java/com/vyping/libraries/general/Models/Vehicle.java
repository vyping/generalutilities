package com.vyping.libraries.general.Models;

import static com.vyping.libraries.general.Definitions.TYPE_ARTICULATE;
import static com.vyping.libraries.general.Definitions.TYPE_BUS;
import static com.vyping.libraries.general.Definitions.TYPE_CAR;
import static com.vyping.libraries.general.Definitions.TYPE_MOTORCYCLE;
import static com.vyping.libraries.general.Definitions.TYPE_PICKUP;
import static com.vyping.libraries.general.Definitions.TYPE_TRUCK;
import static com.vyping.libraries.general.Definitions.TYPE_VAN;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import com.google.firebase.database.ServerValue;
import com.vyping.libraries.R;
import com.vyping.libraries.general.Definitions;
import com.vyping.masterlibrary.Common.DateTools;
import com.vyping.masterlibrary.Common.Numbers;
import com.vyping.masterlibrary.Common.Strings;
import com.vyping.masterlibrary.Firebase.Realtime.Readers;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@IgnoreExtraProperties
public class Vehicle extends Readers implements Serializable {

   // @PropertyName("some_field_name")
    public @Exclude String IdVehicle;
    public String Card, Document, User, Phone;
    public int Type, Tariff, State;
    public long Payment;
    public Object HourIn, HourOut;

    public Vehicle(@NonNull DataSnapshot DataEvent)  {
        
        this.IdVehicle = getKeyString(DataEvent);
        this.Card = getString(DataEvent.child("Card"));
        this.Document = getString(DataEvent.child("Document"));
        this.HourIn = getLong(DataEvent.child("HourIn"));
        this.HourOut = getLong(DataEvent.child("HourOut"));
        this.Payment = getInteger(DataEvent.child("Payment"));
        this.Phone = getString(DataEvent.child("Phone"));
        this.State = getInteger(DataEvent.child("State"));
        this.Tariff = getInteger(DataEvent.child("Tariff"));
        this.Type = getInteger(DataEvent.child("Type"));
        this.User = getString(DataEvent.child("User"));
    }

    public Vehicle(String idVehicle, String card, String document, Object hourIn, Object hourOut, int payment, String phone, int state, int tariff, int type, String user) {

        this.IdVehicle = idVehicle;
        this.Card = card;
        this.Document = document;
        this.HourIn = hourIn;
        this.HourOut = hourOut;
        this.Payment = payment;
        this.Phone = phone;
        this.State = state;
        this.Tariff = tariff;
        this.Type = type;
        this.User = user;
    }

    @Exclude
    public HashMap<String, Object> getVehicleToUpdate() {

        HashMap<String, Object> map = new HashMap<>();
        map.put("Card", Card);
        map.put("Document", Document);
        map.put("HourIn", HourIn);
        map.put("HourOut", HourOut);
        map.put("Payment", Payment);
        map.put("Phone", Phone);
        map.put("State", State);
        map.put("Tariff", Tariff);
        map.put("Type", Type);
        map.put("User", User);

        return map;
    }

    @Exclude
    public void setDocument(String document) {

        Document = document;
    }

    @Exclude
    public String getDocument() {

        return new Strings().formatMiles(Document);
    }

    @Exclude
    public void setHourIn(long hourIn) {

        HourIn = hourIn;
    }

    @Exclude
    public String getHourIn() {

        return new DateTools().getTime("hh:mm", HourIn);
    }

    @Exclude
    public void setHourOut(long hourOut) {

        HourOut = hourOut;
    }

    @Exclude
    public String getHourOut() {

        return new DateTools().getTime("hh:mm", HourOut);
    }

    @Exclude
    public void setPayment(long payment) {

        Payment = payment;
    }

    @Exclude
    public String getPayment() {

        return new Strings().formatToMoney(Payment);
    }

    @Exclude
    public String getVehicleType() {

        if (Type == TYPE_BUS)  {

            return "Autobús";

        } else if (Type == TYPE_CAR) {

            return "Automóvil";

        } else if (Type == TYPE_TRUCK) {

            return "Camión";

        } else if (Type == TYPE_PICKUP) {

            return "Camioneta";

        } else if (Type == TYPE_MOTORCYCLE) {

            return "Motocicleta";

        } else if (Type == TYPE_ARTICULATE) {

            return "Tractomula";

        } else if (Type == TYPE_VAN) {

            return "Van";

        } else {

            return "Varios";
        }
    }

    @Exclude
    public int getVehicleIcon() {

        if (Type == TYPE_BUS)  {

            return R.drawable.icon_vehicle_bus;

        } else if (Type == TYPE_CAR) {

            return R.drawable.icon_vehicle_automobile;

        } else if (Type == TYPE_TRUCK) {

            return R.drawable.icon_vehicle_truck;

        } else if (Type == TYPE_PICKUP) {

            return R.drawable.icon_vehicle_pickup;

        } else if (Type == TYPE_MOTORCYCLE) {

            return R.drawable.icon_vehicle_motorcycle;

        } else if (Type == TYPE_ARTICULATE) {

            return R.drawable.icon_vehicle_articulate;

        } else if (Type == TYPE_VAN) {

            return R.drawable.icon_vehicle_van;

        } else {

            return R.drawable.icon_vehicle_others;
        }
    }

    @Exclude
    public void setState(@Definitions.State int state) {

        this.State = state;
    }
}
