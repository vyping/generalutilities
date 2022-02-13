package com.vyping.libraries.vehicles;

import static com.vyping.libraries.general.Definitions.INSIDE;
import static com.vyping.libraries.general.Definitions.PERIOD_HOURS;
import static com.vyping.libraries.general.Definitions.PERIOD_MONTHLY;
import static com.vyping.libraries.general.Definitions.TYPE_CAR;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_REFRESH_ACCEPT;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;

import com.google.firebase.database.ServerValue;
import com.vyping.libraries.general.Models.Tariff;
import com.vyping.libraries.general.Models.Vehicle;
import com.vyping.masterlibrary.Common.Strings;
import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.MaskedEditText.MaskedEditText;
import com.vyping.masterlibrary.dialogs.CreateDialog;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.spinners.AdapterSingleIcon;

import java.util.ArrayList;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).

public abstract class VehicleDialogCreate extends CreateDialog {

    private Context context;
    private AdapterSingleIcon adapterType;
    private AdapterSingleIcon adapterCard;

    private ImageView Iv_IconPeriod;
    private SwitchCompat Sw_Period;
    private Spinner Sp_Vehicle, Sp_Cards;
    private MaskedEditText Et_Register, Et_Phone;
    private EditText Et_Tariff, Et_User, Et_Document;

    private String register, payment, user, document, phone, idCard;
    private int tariff, type;
    private ArrayList<AdapterSingleIcon.Item> arrayFreeCards;
    private ArrayList<Tariff> arrayTariffs;


    /*----- SetUp -----*/

    public VehicleDialogCreate(@NonNull Context context, int parameters, ArrayList<Tariff> arrayTariffs, String idCard) {

        super(context, parameters);

        setParameters(context, arrayTariffs, idCard);
        setDialogViews();
        setModeButtons(setModeButtons());
        setDialogListener(new DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() {

                Sw_Period.setChecked(FALSE);

                register = "";
                type = TYPE_CAR;
                tariff = PERIOD_HOURS;
                payment = "";
                user = "";
                document = "";
                phone = "";

                Sp_Vehicle.setSelection(0);
                Et_Register.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                Et_Register.setText(register);
                Et_User.setText(user);
                Et_Phone.setText(phone);

                //setTariff(0);
                setModeButtons(BUTTONS_CANCEL);
            }

            @Override
            public void PositiveClick() {

                String Register = String.valueOf(register.subSequence(0, 7));
                payment = payment.replace("$", "").replace(".","");
                int Payment = Integer.parseInt(payment);

                Vehicle vehicle = new Vehicle(Register, idCard, document, ServerValue.TIMESTAMP, 0L, Payment, phone, INSIDE, tariff, type, user);

                ButtonConfirm(vehicle);
            }
        });
    }

    public VehicleDialogCreate(@NonNull Context context, int parameters, ArrayList<Tariff> arrayTariffs, ArrayList<AdapterSingleIcon.Item> arrayFreeCards) {

        super(context, parameters);

        setParameters(context, arrayTariffs, arrayFreeCards);
        setDialogViews();
        setModeButtons(setModeButtons());
        setDialogListener(new DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() {

                Sw_Period.setChecked(FALSE);

                idCard = "";
                register = "";
                type = TYPE_CAR;
                tariff = PERIOD_HOURS;
                payment = "";
                user = "";
                document = "";
                phone = "";

                Sp_Vehicle.setSelection(0);
                Sp_Cards.setSelection(0);
                Et_Register.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);
                Et_Register.setText(register);
                Et_User.setText(user);
                Et_Phone.setText(phone);

                //setTariff(0);
                setModeButtons(BUTTONS_CANCEL);
            }

            @Override
            public void PositiveClick() {

                String Register = String.valueOf(register.subSequence(0, 7));
                payment = payment.replace("$", "").replace(".","");
                int Payment = Integer.parseInt(payment);

                Vehicle vehicle = new Vehicle(Register, idCard, document, ServerValue.TIMESTAMP, 0L, Payment, phone, INSIDE, tariff, type, user);

                ButtonConfirm(vehicle);
            }
        });
    }

    private void setParameters(@NonNull Context Context, @NonNull ArrayList<Tariff> arrayTariffs, String idCard) {

        this.context = Context;
        this.arrayTariffs = arrayTariffs;
        this.idCard = idCard;

        register = "";
        type = TYPE_CAR;
        tariff = PERIOD_HOURS;
        payment = "";
        user = "";
        document = "";
        phone = "";

        ArrayList<AdapterSingleIcon.Item> ArrayTariffs = new ArrayList<>();

        for (Tariff tariff : arrayTariffs) {

            AdapterSingleIcon.Item item = new AdapterSingleIcon.Item(tariff.icon, tariff.type);
            ArrayTariffs.add(item);
        }

        adapterType = new AdapterSingleIcon(context, ArrayTariffs);
    }

    private void setParameters(@NonNull Context Context, @NonNull ArrayList<Tariff> arrayTariffs, ArrayList<AdapterSingleIcon.Item> arrayFreeCards) {

        this.context = Context;
        this.arrayTariffs = arrayTariffs;
        this.arrayFreeCards = arrayFreeCards;

        register = "";
        type = TYPE_CAR;
        tariff = PERIOD_HOURS;
        payment = "";
        user = "";
        document = "";
        phone = "";

        ArrayList<AdapterSingleIcon.Item> itemsArrayTariffs = new ArrayList<>();

        for (Tariff tariff : arrayTariffs) {

             AdapterSingleIcon.Item item = new AdapterSingleIcon.Item(tariff.icon, tariff.type);
             itemsArrayTariffs.add(item);
        }

        adapterType = new AdapterSingleIcon(context, itemsArrayTariffs);
        adapterCard = new AdapterSingleIcon(context, arrayFreeCards);
    }

    private void setDialogViews() {

        int style = R.style.DialogInputTextSerie;
        int attr = R.attr.dialogInputTextSerie;

        View child = ((Activity) context).getLayoutInflater().inflate(com.vyping.libraries.R.layout.dialog_vehicle_create, null);
        addCustomView(child);

        Iv_IconPeriod = child.findViewById(com.vyping.libraries.R.id.iconPeriod);

        Sw_Period = child.findViewById(com.vyping.libraries.R.id.switch2);
        Sw_Period.setText("Diario x Horas");
        Sw_Period.setOnCheckedChangeListener(typeTariffListener);

        Sp_Vehicle = child.findViewById(com.vyping.libraries.R.id.SelectType);
        Sp_Vehicle.setAdapter(adapterType);
        Sp_Vehicle.setOnItemSelectedListener(vehicleListener);

        Et_Tariff = child.findViewById(com.vyping.libraries.R.id.InputTariff);
        Et_Tariff.addTextChangedListener(tariffWatcher);

        LinearLayout Ll_Tags = child.findViewById(com.vyping.libraries.R.id.ContainerTags);

        if (adapterCard != null) {

            Ll_Tags.setVisibility(View.VISIBLE);

            Sp_Cards = child.findViewById(com.vyping.libraries.R.id.SelectCard);
            Sp_Cards.setAdapter(adapterCard);
            Sp_Cards.setOnItemSelectedListener(cardListener);


        } else {

            Ll_Tags.setVisibility(View.GONE);
        }

        Et_Register = child.findViewById(com.vyping.libraries.R.id.InputRegister);
        Et_Register.addTextChangedListener(registerWatcher);

        Et_User = child.findViewById(com.vyping.libraries.R.id.InputUser);
        Et_User.addTextChangedListener(userWatcher);

        Et_Document = child.findViewById(com.vyping.libraries.R.id.InputDocument);
        Et_Document.addTextChangedListener(documentWatcher);

        Et_Phone = child.findViewById(com.vyping.libraries.R.id.InputPhone);
        Et_Phone.addTextChangedListener(phoneWatcher);
    }


    /*----- Listeners -----*/

    private final AdapterView.OnItemSelectedListener cardListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView adapterView, View view, int i, long l) {

            idCard = arrayFreeCards.get(i).text;
        }

        @Override
        public void onNothingSelected(AdapterView adapterView) {}
    };

    private final CompoundButton.OnCheckedChangeListener typeTariffListener = new CompoundButton.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

            Drawable iconDrawable;

            if (isChecked) {

                tariff = PERIOD_MONTHLY;

                iconDrawable = new MyDrawable().extractFromResources(context, R.drawable.icon_calendar);
                Sw_Period.setText("Mensualidad");

            } else {

                tariff = PERIOD_HOURS;

                iconDrawable = new MyDrawable().extractFromResources(context, R.drawable.icon_clock);
                Sw_Period.setText("Diario x Horas");
            }

            Iv_IconPeriod.setImageDrawable(iconDrawable);

            setTariff(type);
        }
    };

    private final AdapterView.OnItemSelectedListener vehicleListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView adapterView, View view, int i, long l) {

            type = i;

            setTariff(type);
        }

        @Override
        public void onNothingSelected(AdapterView adapterView) {}
    };

    private final TextWatcher tariffWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(@NonNull Editable s) {

            Et_Tariff.removeTextChangedListener(this);

            try {

                String originalString = s.toString();

                if (originalString.contains("$")) {

                    originalString = originalString.replace("$", "");
                }

                if (originalString.contains(",")) {

                    originalString = originalString.replaceAll(",", "");
                }

                payment = originalString;
                String formattedString = new Strings().formatToMoney(payment);

                Et_Tariff.setText(formattedString);
                Et_Tariff.setSelection(Et_Tariff.getText().length());

            } catch (NumberFormatException ignored) { }

            Et_Tariff.addTextChangedListener(this);
            setModeButtons(setModeButtons());
        }
    };

    private final TextWatcher registerWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override
        public void onTextChanged(@NonNull CharSequence charSequence, int i, int i1, int i2) {

            if ((i != 0 && i1 != 8 && i2 != 7) || (i != 0 && i1 != 6 && i2 != 7)) {

                register = charSequence.toString().trim();

                if ((i < 2 && i1 == 0 && i2 == 1) || (i < 4 && i1 == 1 && i2 == 0)) {

                    Et_Register.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS);

                } else {

                    Et_Register.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_VARIATION_NORMAL);
                }

                setModeButtons(setModeButtons());
            }
        }

        @Override
        public void afterTextChanged(Editable editable) { }
    };

    private final TextWatcher userWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override
        public void onTextChanged(@NonNull CharSequence charSequence, int i, int i1, int i2) {

            user = charSequence.toString().trim();

            setModeButtons(setModeButtons());
        }

        @Override
        public void afterTextChanged(Editable editable) { }
    };

    private final TextWatcher documentWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) { }

        @Override
        public void afterTextChanged(@NonNull Editable s) {

            Et_Document.removeTextChangedListener(this);

            try {

                String originalString = s.toString();

                if (originalString.contains(",")) {

                    originalString = originalString.replaceAll(",", "");
                }

                document = originalString;
                String formattedString = new Strings().formatMiles(originalString);

                Et_Document.setText(formattedString);
                Et_Document.setSelection(Et_Document.getText().length());

            } catch (NumberFormatException ignored) { }

            Et_Document.addTextChangedListener(this);
            setModeButtons(setModeButtons());
        }
    };

    private final TextWatcher phoneWatcher = new TextWatcher() {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }

        @Override
        public void onTextChanged(@NonNull CharSequence charSequence, int i, int i1, int i2) {

            phone = charSequence.toString().trim();

            setModeButtons(setModeButtons());
        }

        @Override
        public void afterTextChanged(Editable editable) { }
    };


    /*----- Utilities -----*/

    private int setModeButtons() {

        boolean existRegister = !register.equals("") && !register.equals("AAA-000");
        boolean existUser = !user.equals("") && !user.equals("Pedro Pérez");
        boolean existDocument = !document.equals("") && !document.equals("80,123,456");
        boolean existPhone = !phone.equals("") && !phone.equals("300 000 0000");

        if (existRegister && existUser && existDocument && existPhone) {

            return BUTTONS_REFRESH_ACCEPT;

        } else {

            return BUTTONS_CANCEL;
        }
    }

    private void setTariff(int position) {

        if (tariff == PERIOD_HOURS) {

            payment = new Strings().formatToMoney(arrayTariffs.get(position).perHours);

        } else if (tariff == PERIOD_MONTHLY) {

            payment = new Strings().formatToMoney(arrayTariffs.get(position).perMonth);
        }

        Et_Tariff.setText(payment);

        if (position == arrayTariffs.size() - 1) {

            Et_Tariff.setEnabled(TRUE);

        } else {

            Et_Tariff.setEnabled(FALSE);
        }
    }


    /*----- Return -----*/

    protected abstract void ButtonConfirm(Vehicle vehicle);
}
