package com.vyping.masterlibrary.Firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ServerValue;

public class Timestamp {

    private final MyRealtime databaseRealtime;
    private final Interfase interfase;


    // ----- Setup ----- //

    public Timestamp(String instance, Interfase interfase) {

        this.interfase = interfase;

        databaseRealtime = new MyRealtime(instance).child("Timestamp");
        databaseRealtime.setValue(ServerValue.TIMESTAMP, new MyRealtime.CompleteListener() {

            @Override
            public void Success() {

                getTimestamp();
            }

            private void DummyVoid() {
            }
        });

        databaseRealtime.setValue(ServerValue.TIMESTAMP, new MyRealtime.CompleteListener() {

            @Override
            public void Success() {

                getTimestamp();
            }

            private void DummyVoid() {
            }
        });
    }

    private void getTimestamp() {

        databaseRealtime.getSingleValue(new MyRealtime.SingleListener() {

            @Override
            public void ValueListen(DataSnapshot dataSnapshot) {

                long timestamp = new RealData(dataSnapshot).getLong();

                interfase.Timestamp(timestamp);
            }

            @Override
            public void ValueNonExist(String error) {
            }
        });
    }


    // ----- Interface ----- //

    public interface Interfase {

        void Timestamp(Long timestamp);
    }
}
