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
import com.vyping.libraries.R;
import com.vyping.masterlibrary.Firebase.Realtime.Readers;

public class Tariff extends Readers {

    public String type;
    public int icon, perHours, perMonth;


    public Tariff(@NonNull DataSnapshot DataEvent) {

        this.type = getKeyString(DataEvent);
        this.perHours = getInteger(DataEvent.child("0"));
        this.perMonth = getInteger(DataEvent.child("1"));
        this.icon = getDrawableType(type);
    }

    public Tariff(String type, int perHours, int vehicle) {
        
        this.type = type;
        this.perHours = perHours;
        this.perMonth = vehicle;
        this.icon = getDrawableType(type);
    }

    private int getDrawableType(String type) {

        switch (type) {

            case "Autobús":

                return R.drawable.icon_vehicle_bus;

            case "Automóvil":

                return R.drawable.icon_vehicle_automobile;

            case "Camión":

                return R.drawable.icon_vehicle_truck;

            case "Camioneta":

                return R.drawable.icon_vehicle_pickup;

            case "Motocicleta":

                return R.drawable.icon_vehicle_motorcycle;

            case "Tractomula":

                return R.drawable.icon_vehicle_articulate;

            case "Van":

                return R.drawable.icon_vehicle_van;

            default:

                return R.drawable.icon_vehicle_others;
        }
    }
}
