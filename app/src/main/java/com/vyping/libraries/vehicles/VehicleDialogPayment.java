package com.vyping.libraries.vehicles;

import static com.vyping.libraries.general.Constants.INSTANCE_MAIN;
import static com.vyping.libraries.general.Definitions.PERIOD_HOURS;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL_ACCEPT;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;
import com.vyping.libraries.general.Models.Vehicle;
import com.vyping.masterlibrary.Common.DateTools;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.Numbers;
import com.vyping.masterlibrary.Common.Strings;
import com.vyping.masterlibrary.Firebase.Realtime.RealTimestamp;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.dialogs.CreateDialog;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).

public abstract class VehicleDialogPayment extends CreateDialog {

    private Context context;

    private long currentHour, payment;
    private Vehicle vehicle;


    /*----- SetUp -----*/

    public VehicleDialogPayment(@NonNull Context context, int parameters, Vehicle vehicle) {

        super(context, parameters);

        setParameters(context, vehicle);

    }

    private void setParameters(@NonNull Context context, Vehicle vehicle) {

        this.context = context;
        this.vehicle = vehicle;
        this.currentHour = 0L;

        new RealTimestamp(INSTANCE_MAIN, new RealTimestamp.Interfase() {

            @Override
            public void Timestamp(Long timestamp) {

                currentHour = timestamp;

                setDialogViews();
                setModeButtons(setModeButtons());
                setDialogListener(new DialogInterface() {

                    @Override
                    public void NegativeClick() {

                        ButtonCancel();
                    }

                    @Override
                    public void RefreshClick() { }

                    @Override
                    public void PositiveClick() {

                        vehicle.setHourOut(currentHour);

                        ButtonConfirm(vehicle, payment);
                    }
                });
            }

            private void DummyVoid() {}
        });
    }

    private void setDialogViews() {

        int style = R.style.DialogInputTextSerie;
        int attr = R.attr.dialogInputTextSerie;

        View child = ((Activity) context).getLayoutInflater().inflate(com.vyping.libraries.R.layout.dialog_vehicle_payment, null);
        addCustomView(child);

        TextView Tv_Register = child.findViewById(com.vyping.libraries.R.id.TextVehicle);
        Tv_Register.setText(vehicle.IdVehicle);

        ImageView Iv_IconVehicle = child.findViewById(com.vyping.libraries.R.id.iconTypeVehicle);
        Iv_IconVehicle.setImageResource(vehicle.getVehicleIcon());

        TextView Tv_TypeVehicle = child.findViewById(com.vyping.libraries.R.id.TextTypeVehicle);
        Tv_TypeVehicle.setText(vehicle.getVehicleType());

        TextView Tv_TextCard = child.findViewById(com.vyping.libraries.R.id.TextCard);
        Tv_TextCard.setText(vehicle.Card);

        TextView Tv_HourEnter = child.findViewById(com.vyping.libraries.R.id.TextHourEnter);
        Tv_HourEnter.setText(new DateTools().getTime("HH:mm - dd/MM/yy", vehicle.HourIn));

        TextView Tv_TextHourOut = child.findViewById(com.vyping.libraries.R.id.TextHourOut);
        Tv_TextHourOut.setText(new DateTools().getTime("HH:mm - dd/MM/yy", currentHour));

        TextView Tv_TextHours = child.findViewById(com.vyping.libraries.R.id.TextHours);
        Tv_TextHours.setText(getTotalHours());

        TextView Tv_TextPayment = child.findViewById(com.vyping.libraries.R.id.TextPayment);
        Tv_TextPayment.setText(getPayment());

        TextView Tv_TextUser = child.findViewById(com.vyping.libraries.R.id.TextUser);
        Tv_TextUser.setText(vehicle.User);

        TextView Tv_TextDocument = child.findViewById(com.vyping.libraries.R.id.TextDocument);
        Tv_TextDocument.setText(vehicle.getDocument());

        TextView Tv_TextPhone = child.findViewById(com.vyping.libraries.R.id.TextPhone);
        Tv_TextPhone.setText(vehicle.Phone);
    }


    /*----- Utilities -----*/

    private int setModeButtons() {

        return BUTTONS_CANCEL_ACCEPT;
    }

    private String getTotalHours() {

        long hour = new Numbers().objectToLong(vehicle.HourIn);

        return new DateTools().timeSince(hour, currentHour);
    }

    public String getPayment() {

        if (vehicle.Tariff == PERIOD_HOURS) {

            long hourIn = new Numbers().objectToLong(vehicle.HourIn);
            long hourOut = new Numbers().objectToLong(currentHour);
            int hours = new DateTools().roundedHoursSince(hourIn, hourOut);
            payment = hours*vehicle.Payment;

            return new Strings().formatToMoney(payment);

        } else {

            return String.valueOf(0);
        }
    }


    /*----- Return -----*/

    protected abstract void ButtonConfirm(Vehicle vehicle, long payment);
    protected abstract void ButtonCancel();
}
