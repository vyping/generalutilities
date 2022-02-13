package com.vyping.libraries.Incomes;

import static com.vyping.libraries.general.Constants.INSTANCE_MAIN;
import static com.vyping.libraries.general.Definitions.PERIOD_HOURS;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL_ACCEPT;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vyping.libraries.general.Models.Income;
import com.vyping.libraries.general.Models.Vehicle;
import com.vyping.masterlibrary.Common.DateTools;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.Numbers;
import com.vyping.masterlibrary.Common.Strings;
import com.vyping.masterlibrary.Firebase.Realtime.RealTimestamp;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.dialogs.CreateDialog;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).

public abstract class IncomesDialogInfo extends CreateDialog {

    private Context context;

    private Income income;


    /*----- SetUp -----*/

    public IncomesDialogInfo(@NonNull Context context, int parameters, Income income) {

        super(context, parameters);

        setParameters(context, income);
    }

    private void setParameters(@NonNull Context context, Income income) {

        this.context = context;
        this.income = income;

        new RealTimestamp(INSTANCE_MAIN, new RealTimestamp.Interfase() {

            @Override
            public void Timestamp(Long timestamp) {

                setDialogViews();
                setModeButtons(setModeButtons());
                setDialogListener(new DialogInterface() {

                    @Override
                    public void NegativeClick() { }

                    @Override
                    public void RefreshClick() { }

                    @Override
                    public void PositiveClick() { }
                });
            }

            private void DummyVoid() {}
        });
    }

    private void setDialogViews() {

        int style = R.style.DialogInputTextSerie;
        int attr = R.attr.dialogInputTextSerie;

        View child = ((Activity) context).getLayoutInflater().inflate(com.vyping.libraries.R.layout.dialog_income_info, null);
        addCustomView(child);

        TextView Tv_Register = child.findViewById(com.vyping.libraries.R.id.TextVehicle);
        Tv_Register.setText(income.Vehicle);

        ImageView Iv_IconVehicle = child.findViewById(com.vyping.libraries.R.id.iconTypeVehicle);
        Iv_IconVehicle.setImageResource(income.getVehicleIcon());

        TextView Tv_TypeVehicle = child.findViewById(com.vyping.libraries.R.id.TextTypeVehicle);
        Tv_TypeVehicle.setText(income.getType());

        TextView Tv_TypeTariff = child.findViewById(com.vyping.libraries.R.id.TextTariff);
        Tv_TypeTariff.setText(income.getTariff());

        TextView Tv_TextCard = child.findViewById(com.vyping.libraries.R.id.TextCard);
        Tv_TextCard.setText(income.Card);

        TextView Tv_HourEnter = child.findViewById(com.vyping.libraries.R.id.TextHourEnter);
        Tv_HourEnter.setText(income.getHourIn());

        TextView Tv_TextHourOut = child.findViewById(com.vyping.libraries.R.id.TextHourOut);
        Tv_TextHourOut.setText(income.getHourOut());

        TextView Tv_TextHours = child.findViewById(com.vyping.libraries.R.id.TextHours);
        Tv_TextHours.setText(income.getHourDiff());

        TextView Tv_TextPayment = child.findViewById(com.vyping.libraries.R.id.TextPayment);
        Tv_TextPayment.setText(income.getPayment());
    }


    /*----- Utilities -----*/

    private int setModeButtons() {

        return BUTTONS_CANCEL_ACCEPT;
    }
}
