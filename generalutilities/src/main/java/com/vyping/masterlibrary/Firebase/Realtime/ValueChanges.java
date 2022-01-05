package com.vyping.masterlibrary.Firebase.Realtime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public abstract class ValueChanges extends DatabaseRealtime {


    // ----- Setup ----- //

    public ValueChanges(String child) {

        DATABASEREFERENCE.child(child).addChildEventListener(valuesListener);
    }


    // ----- Listeners ----- //

    private final ChildEventListener valuesListener = new ChildEventListener() {

        @Override
        public void onChildAdded(@NonNull DataSnapshot childSnapshot, @Nullable String previousChildName) {

            DATASNAPSHOT = childSnapshot;

            ChildAdded(childSnapshot);
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot childSnapshot, @Nullable String previousChildName) {

            DATASNAPSHOT = childSnapshot;

            ChildChanged(childSnapshot);
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot childSnapshot) {

            DATASNAPSHOT = childSnapshot;

            ChildRemoved(childSnapshot);
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) { }

        @Override
        public void onCancelled(@NonNull DatabaseError error) { }
    };


    // ----- Aditional Tools ----- //

    public void removeValueListener(String child) {

        DATABASEREFERENCE.child(child).removeEventListener(valuesListener);
    }


    //----- Return -----//

    protected abstract void ChildAdded(@NonNull DataSnapshot snapChild);
    protected abstract void ChildChanged(@NonNull DataSnapshot snapChild);
    protected abstract void ChildRemoved(@NonNull DataSnapshot snapChild);
}
