package com.vyping.masterlibrary.Firebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.ServerValue;

public class Timestamp extends MyRealtimeReader {

    private final MyRealtime databaseRealtime;
    private final Interfase interfase;


    // ----- Setup ----- //

    public Timestamp(String instance, Interfase interfase) {

        this.interfase = interfase;

        databaseRealtime = new MyRealtime(instance, "Timestamp") {
        };
        databaseRealtime.setValue(ServerValue.TIMESTAMP, new MyRealtime.SuccessListener() {

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

                long timestamp = getLong(dataSnapshot);

                interfase.Timestamp(timestamp);
            }

            @Override
            public void ValueNonExist() {
            }
        });
    }


    // ----- Interface ----- //

    public interface Interfase {

        void Timestamp(Long timestamp);
    }
}
