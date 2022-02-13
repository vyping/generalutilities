package com.vyping.libraries.general.Models;

import static com.vyping.libraries.general.Definitions.INSIDE;
import static com.vyping.libraries.general.Definitions.OUTSIDE;
import static com.vyping.libraries.general.Definitions.PERIOD_HOURS;
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
import com.vyping.masterlibrary.Common.DateTools;
import com.vyping.masterlibrary.Common.Numbers;
import com.vyping.masterlibrary.Common.Strings;
import com.vyping.masterlibrary.Firebase.Realtime.Readers;

import java.io.Serializable;

@IgnoreExtraProperties
public class Income extends Readers implements Serializable {

    public @Exclude String IdIncome;
    public String Manager, Vehicle, Card;
    public int Action, Type, Tariff;
    public long Payment;
    public Object HourIn, HourOut;

    public Income(@NonNull DataSnapshot dataSnapshot) {

        this.IdIncome = getKeyString(dataSnapshot);
        this.Action = getInteger(dataSnapshot.child("Action"));
        this.HourIn = getLong(dataSnapshot.child("HourIn"));
        this.HourOut = getLong(dataSnapshot.child("HourOut"));
        this.Manager = getString(dataSnapshot.child("Manager"));
        this.Vehicle = getString(dataSnapshot.child("Vehicle"));
        this.Type = getInteger(dataSnapshot.child("Type"));
        this.Card = getString(dataSnapshot.child("Card"));
        this.Tariff = getInteger(dataSnapshot.child("Tariff"));
        this.Payment = getLong(dataSnapshot.child("Payment"));
    }

    public Income(String idIncome, int action, String card, Object hour, String manager, long payment, int tariff, int type, String vehicle) {

        this.IdIncome = idIncome;
        this.Action = action;
        this.Card = card;
        this.HourIn = getHourIn(hour);
        this.HourOut = getHourOut(hour);
        this.Manager = manager;
        this.Payment = payment;
        this.Tariff = tariff;
        this.Type = type;
        this.Vehicle = vehicle;
    }

    @Exclude
    private Object getHourIn(Object hour) {

        if (Action == INSIDE) {

            return ServerValue.TIMESTAMP;

        } else {

            return hour;
        }
    }

    @Exclude
    private Object getHourOut(Object hour) {

        if (Action == OUTSIDE) {

            return ServerValue.TIMESTAMP;

        } else {

            return 0L;
        }
    }

    @Exclude
    public int getBackground() {

        if (Tariff== PERIOD_HOURS) {

            return R.drawable.button_action_exit;

        } else {

            return R.drawable.button_action_enter;
        }
    }

    @Exclude
    public String getHourRound() {

        long hourIn = new Numbers().objectToLong(HourIn);
        long hourOut = new Numbers().objectToLong(HourOut);
        int hour = new DateTools().roundedHoursSince(hourIn, hourOut);

        return new DateTools().setHoursUnits(hour);
    }

    @Exclude
    public String getHourDiff() {

        long hourIn = new Numbers().objectToLong(HourIn);
        long hourOut = new Numbers().objectToLong(HourOut);

        return new DateTools().timeSince(hourIn, hourOut);
    }

    @Exclude
    public String getHourIn() {

        return new DateTools().getTime("HH:mm - dd/MM/yy", HourIn);
    }

    @Exclude
    public String getHourOut() {

        return new DateTools().getTime("HH:mm - dd/MM/yy", HourOut);
    }

    @Exclude
    public int getEventIcon() {

        if (Action == OUTSIDE) {

            return R.drawable.icon_exit;

        } else {

            return R.drawable.icon_enter;
        }
    }

    @Exclude
    public String getEventType() {

        if (Action == OUTSIDE) {

            return "Retiro";

        } else {

            return "Ingreso";
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
    public String getType() {

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
    public int getTariffIcon() {

        if (Tariff == PERIOD_HOURS) {

            return R.drawable.icon_clock;

        } else {

            return R.drawable.icon_calendar;
        }
    }

    @Exclude
    public String getTariff() {

        if (Tariff == PERIOD_HOURS) {

            return "Diario x Horas";

        } else {

            return "Mensualidad";
        }
    }

    @Exclude
    public String getPayment() {

        return new Strings().formatMiles(Payment);
    }
}



