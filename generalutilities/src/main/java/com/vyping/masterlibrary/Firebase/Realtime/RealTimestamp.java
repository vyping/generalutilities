package com.vyping.masterlibrary.Firebase.Realtime;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ServerValue;
import com.google.firebase.database.ValueEventListener;

public class RealTimestamp extends Readers {

    private final DatabaseRealtime databaseRealtime;
    private final Interfase interfase;


    // ----- Setup ----- //

    public RealTimestamp(String instance, Interfase interfase) {

        this.interfase = interfase;

        databaseRealtime = new DatabaseRealtime(instance, "Timestamp") {};
        databaseRealtime.setValue(ServerValue.TIMESTAMP, new DatabaseRealtime.SuccessListener() {

            @Override
            public void Success() {

                getTimestamp();
            }

            private void DummyVoid() {}
        });
    }

    private void getTimestamp() {

        databaseRealtime.getSingleValue(new DatabaseRealtime.SingleListener() {

            @Override
            public void ValueListen(DataSnapshot dataSnapshot) {

                long timestamp = getLong(dataSnapshot);

                interfase.Timestamp(timestamp);
            }

            @Override
            public void ValueNonExist() { }
        });
    }


    // ----- Interface ----- //

    public interface Interfase {

        void Timestamp(Long timestamp);
    }
}
