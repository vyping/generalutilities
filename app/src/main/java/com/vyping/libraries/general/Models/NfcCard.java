package com.vyping.libraries.general.Models;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Firebase.Realtime.Readers;

public class NfcCard extends Readers {

    public String idCard, code, vehicle;


    public NfcCard(@NonNull DataSnapshot DataEvent) {

        this.idCard = getKeyString(DataEvent);
        this.code = getString(DataEvent.child("0"));
        this.vehicle = getString(DataEvent.child("1"));
    }

    public NfcCard(String idCard, String code, String vehicle) {
        
        this.idCard = idCard;
        this.code = code;
        this.vehicle = vehicle;
    }
}
