package com.vyping.libraries.general.Managers;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import androidx.annotation.NonNull;

import com.vyping.libraries.general.Models.Tariff;
import com.vyping.masterlibrary.spinners.AdapterSingleIcon;

import java.util.ArrayList;

public class TariffsManager {

    public static ArrayList<Tariff> arrayTariffs;


    /**
     * -------- SetUp Section
     */

    public TariffsManager() {

        arrayTariffs = new ArrayList<>();
    }


    /**
     * -------- Adapter Process Section
     */

    public ArrayList<Tariff> getArrayTariff() {

        return arrayTariffs;
    }

    public long getTariffsCount() {

        return arrayTariffs.size();
    }

    public Tariff getTariffByPosition(int position) {

        return arrayTariffs.get(position);
    }

    public Tariff getTariffById(String type) {

        Tariff Tariff = null;

        for (Tariff tariff : arrayTariffs) {

            if (type.equals(tariff.type)) {

                Tariff = tariff;
                break;
            }
        }

        return Tariff;
    }


    public boolean existTariff(final String type) {

        boolean exist = FALSE;

        for (final Tariff Tariff : arrayTariffs) {

            String keyTraslate = Tariff.type;

            if (type.equals(keyTraslate)) {

                exist = TRUE;
                break;
            }
        }

        return exist;
    }

    private int setTariffPosition(@NonNull final Tariff tariff) {

        String type = tariff.type;
        long arraySize = arrayTariffs.size();
        int Return = 0;

        if (!arrayTariffs.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String comparetype = arrayTariffs.get(position).type;
                int compareMarge = type.compareTo(comparetype);

                if (compareMarge < 0) {

                    Return = position;
                    break;

                } else if (compareMarge == 0) {

                    Return = position + 1;

                } else if (compareMarge > 0) {

                    if (position == arraySize - 1) {

                        Return = position + 1;

                    } else {

                        String nexttype = arrayTariffs.get(position).type;
                        int nextMarge = type.compareTo(nexttype);

                        if (nextMarge < 0) {

                            Return = position + 1;
                            break;
                        }
                    }
                }
            }
        }

        return Return;
    }

    public int getTariffPosition(@NonNull Tariff tariff) {

        int position = -1, traslatePosition = -1;
        String idVehicle = tariff.type;

        for (final Tariff getTariff : arrayTariffs) {

            position = position + 1;
            String getIdVehicle = getTariff.type;

            if (idVehicle.equals(getIdVehicle)) {

                traslatePosition = position;
                break;
            }
        }

        return traslatePosition;
    }

    public void addTariffByLoad(@NonNull final Tariff tariff) {

        int position = arrayTariffs.size();
        arrayTariffs.add(position, tariff);
    }

    public void addTariffByChange(@NonNull final Tariff tariff) {

        int position = setTariffPosition(tariff);
        arrayTariffs.add(position, tariff);
    }

    public void modifyTariff(@NonNull final Tariff tariff) {

        int position = getTariffPosition(tariff);
        arrayTariffs.set(position, tariff);
    }

    public void removeTariff(Tariff tariff) {

        int position = getTariffPosition(tariff);

        if (position != -1) {

            arrayTariffs.remove(position);
        }
    }

}
