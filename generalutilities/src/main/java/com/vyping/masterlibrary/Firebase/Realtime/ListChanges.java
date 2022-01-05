package com.vyping.masterlibrary.Firebase.Realtime;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public abstract class ListChanges extends DatabaseRealtime {


    // ----- Setup ----- //

    public ListChanges(String child) {

        DATABASEREFERENCE.child(child).addValueEventListener(listListener);
    }


    // ----- Listeners ----- //

    private final ValueEventListener listListener = new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            int count = 0;

            StartListen(dataSnapshot.exists(), dataSnapshot.getChildrenCount());

            if (dataSnapshot.exists()) {

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {

                    count = count + 1;

                    DATASNAPSHOT = childSnapshot;
                    String key = getKeyString(DATASNAPSHOT);

                    ValueListen(key, childSnapshot, count);
                }
            }

            FinishListen();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) { }
    };


    // ----- Aditional Tools ----- //

    public void removeListListener(String child) {

        DATABASEREFERENCE.child(child).removeEventListener(listListener);
    }


    //----- Return -----//

    protected abstract void StartListen(boolean exist, long childCount);
    protected abstract void ValueListen(String key, @NonNull DataSnapshot dataSnapshot, int count);
    protected abstract void FinishListen();
}
