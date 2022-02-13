package com.vyping.libraries.general.Managers;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import androidx.annotation.NonNull;

import com.vyping.libraries.general.Models.NfcCard;

import java.util.ArrayList;

public class NfcCardManager {

    public static ArrayList<NfcCard> arrayNfcCard;


    /**
     * -------- SetUp Section
     */

    public NfcCardManager() {

        arrayNfcCard = new ArrayList<>();
    }


    /**
     * -------- Adapter Process Section
     */

    public ArrayList<NfcCard> getArrayCards() {

        return arrayNfcCard;
    }

    public ArrayList<String> getArrayFreeCards() {

        ArrayList<String> arrayCards = new ArrayList<>();

        for (NfcCard nfcCard : arrayNfcCard) {

            String statusCard = nfcCard.vehicle;

            if (statusCard.equals("")) {

                arrayCards.add(nfcCard.idCard);
            }
        }

        return arrayCards;
    }

    public ArrayList<String> getArrayCodes() {

        ArrayList<String> arrayCards = new ArrayList<>();

        for (NfcCard nfcCard : arrayNfcCard) {

            String RfidCard = nfcCard.code;
            arrayCards.add(RfidCard);
        }

        return arrayCards;
    }

    public long getCardsCount() {

        return arrayNfcCard.size();
    }

    public NfcCard getCardByPosition(int position) {

        return arrayNfcCard.get(position);
    }

    public NfcCard getCardById(String idNfcCard) {

        NfcCard NfcCard = null;

        for (NfcCard nfcCard : arrayNfcCard) {

            if (idNfcCard.equals(nfcCard.idCard)) {

                NfcCard = nfcCard;
                break;
            }
        }

        return NfcCard;
    }

    public NfcCard getCardByCode(String code) {

        NfcCard NfcCard = null;

        for (NfcCard nfcCard : arrayNfcCard) {

            if (code.equals(nfcCard.code)) {

                NfcCard = nfcCard;
                break;
            }
        }

        return NfcCard;
    }

    public boolean existCard(final String IdNfcCard) {

        boolean exist = FALSE;

        for (final NfcCard nfcCard : arrayNfcCard) {

            String keyTraslate = nfcCard.idCard;

            if (IdNfcCard.equals(keyTraslate)) {

                exist = TRUE;
                break;
            }
        }

        return exist;
    }

    private int setCardPosition(@NonNull final NfcCard nfcCard) {

        String idCard = nfcCard.idCard;
        long arraySize = arrayNfcCard.size();
        int Return = 0;

        if (!arrayNfcCard.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String compareIdCard = arrayNfcCard.get(position).idCard;
                int compareMarge = idCard.compareTo(compareIdCard);

                if (compareMarge < 0) {

                    Return = position;
                    break;

                } else if (compareMarge == 0) {

                    Return = position + 1;

                } else if (compareMarge > 0) {

                    if (position == arraySize - 1) {

                        Return = position + 1;

                    } else {

                        String nextIdCard = arrayNfcCard.get(position).idCard;
                        int nextMarge = idCard.compareTo(nextIdCard);

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

    public int getCardPosition(@NonNull NfcCard nfcCard) {

        int position = -1, traslatePosition = -1;
        String idVehicle = nfcCard.idCard;

        for (final NfcCard getNfcCard : arrayNfcCard) {

            position = position + 1;
            String getIdVehicle = getNfcCard.idCard;

            if (idVehicle.equals(getIdVehicle)) {

                traslatePosition = position;
                break;
            }
        }

        return traslatePosition;
    }

    public void addCardByLoad(@NonNull final NfcCard nfcCard) {

        int position = arrayNfcCard.size();
        arrayNfcCard.add(position, nfcCard);
    }

    public void addCardByChange(@NonNull final NfcCard nfcCard) {

        int position = setCardPosition(nfcCard);
        arrayNfcCard.add(position, nfcCard);
    }

    public void modifyCard(@NonNull final NfcCard nfcCard) {

        int position = getCardPosition(nfcCard);
        arrayNfcCard.set(position, nfcCard);
    }

    public void removeCard(NfcCard nfcCard) {

        int position = getCardPosition(nfcCard);

        if (position != -1) {

            arrayNfcCard.remove(position);
        }
    }


    public String getStatusCard(String idCard) {

        NfcCard nfcCard = getCardById(idCard);

         return nfcCard.vehicle;
    }

    public String getVehicleCard(String idCard) {

        NfcCard nfcCard = getCardById(idCard);

        return nfcCard.vehicle;
    }

    public String getCodeByIdCard(String idCard) {

        NfcCard card = getCardById(idCard);

        return card.code;
    }
}
