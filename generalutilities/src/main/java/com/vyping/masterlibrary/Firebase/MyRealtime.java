package com.vyping.masterlibrary.Firebase;

import static com.vyping.masterlibrary.time.Definitions.FORMAT_DATE_01;
import static com.vyping.masterlibrary.time.Definitions.FORMAT_HOUR_01;
import static java.lang.Boolean.FALSE;

import android.location.Location;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vyping.masterlibrary.Common.MyNumbers;
import com.vyping.masterlibrary.Timers.MyCounter;
import com.vyping.masterlibrary.time.MyTime;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;

public class MyRealtime {

    private DatabaseReference databaseReference;
    private MyCounter myCounter;

    private ListListener listListener;
    private ValueListener valueListener;


    // ----- Setup ----- //

    public MyRealtime() {

        if (databaseReference == null) {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            // database.setPersistenceEnabled(true);
            databaseReference = database.getReference();
        }
    }

    public MyRealtime(String instance) {

        String Instance = "https://" + instance + ".firebaseio.com/";

        databaseReference = FirebaseDatabase.getInstance(Instance).getReference();
        databaseReference.keepSynced(true);
    }

    public MyRealtime(String instance, String child) {

        String Instance = "https://" + instance + ".firebaseio.com/";

        databaseReference = FirebaseDatabase.getInstance(Instance).getReference().child(child);
        databaseReference.keepSynced(true);
    }


    // ----- Readers ----- //

    public void getSingleValue(SingleListener listener) {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    listener.ValueListen(dataSnapshot);

                } else {

                    listener.ValueNonExist();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void getListValues(ListListener listener) {

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                listener.StartListen(dataSnapshot.exists(), dataSnapshot.getChildrenCount());
                int count = 0;

                if (dataSnapshot.exists()) {

                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {

                        count = count + 1;
                        listener.ValueListen(childSnapshot);
                    }
                }

                listener.FinishListen();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void getListChanges(ListListener listener) {

        this.listListener = listener;

        databaseReference.addValueEventListener(valueListListener);
    }

    public void getValueChanges(ValueListener listener) {

        this.valueListener = listener;
        this.myCounter = new MyCounter(500, 100, FALSE, counterInterfase);

        databaseReference.addChildEventListener(childValuesListener);
    }


    // ---------- ModelMethods  ---------- //

    public DatabaseReference getReference() {

        return databaseReference;
    }

    public MyRealtime reference(String child) {

        if (databaseReference != null) {

            databaseReference = databaseReference.child(child);
        }

        return this;
    }

    public MyRealtime child(String child1) {

        if (databaseReference != null) {

            databaseReference.child(child1);
        }

        return this;
    }

    public void pushValue(Object object) {

        if (object != null) {

            databaseReference.push().setValue(object);
        }
    }

    public void pushValue(Object object, CompleteListener listener) {

        if (object != null) {

            databaseReference.push().setValue(object).addOnSuccessListener(new OnSuccessListener<>() {

                @Override
                public void onSuccess(Void aVoid) {

                    listener.Success();
                }

                private void DummyVoid() {
                }

            }).addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception e) {

                    listener.Failure();
                }

                private void DummyVoid() {
                }
            });

        } else {

            listener.Failure();
        }
    }

    public void setValue(Object object) {

        if (object != null) {

            databaseReference.setValue(object);
        }
    }

    public void setValue(Object object, CompleteListener listener) {

        if (object != null) {

            databaseReference.setValue(object).addOnSuccessListener(new OnSuccessListener<>() {

                @Override
                public void onSuccess(Void aVoid) {

                    listener.Success();
                }

                private void DummyVoid() {
                }

            }).addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception e) {

                    listener.Failure();
                }

                private void DummyVoid() {
                }
            });

        } else {

            listener.Failure();
        }
    }

    public void updateChild(HashMap<String, Object> hashMap) {

        if (hashMap != null) {

            if (!hashMap.isEmpty()) {

                databaseReference.updateChildren(hashMap);
            }
        }
    }

    public void updateChild(HashMap<String, Object> hashMap, CompleteListener listener) {

        if (hashMap != null) {

            if (!hashMap.isEmpty()) {

                databaseReference.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<>() {

                    @Override
                    public void onSuccess(Void aVoid) {

                        listener.Success();
                    }

                    private void DummyVoid() {
                    }

                }).addOnFailureListener(new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception e) {

                        listener.Failure();
                    }

                    private void DummyVoid() {
                    }
                });

            } else {

                listener.Failure();
            }

        } else {

            listener.Failure();
        }
    }

    public void removeChild(String child) {

        databaseReference.child(child).removeValue();
    }

    public void removeChild(String child, CompleteListener listener) {

        databaseReference.child(child).removeValue().addOnSuccessListener(new OnSuccessListener<>() {

            @Override
            public void onSuccess(Void aVoid) {

                listener.Success();
            }

            private void DummyVoid() {
            }

        }).addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {

                listener.Failure();
            }

            private void DummyVoid() {
            }
        });
    }

    public MyRealtime orderByChild(String child1) {

        if (databaseReference != null) {

            databaseReference.orderByChild(child1);
        }

        return this;
    }


    // ----- Listeners ----- //

    private final ValueEventListener valueListListener = new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            int count = 0;

            listListener.StartListen(dataSnapshot.exists(), dataSnapshot.getChildrenCount());

            if (dataSnapshot.exists()) {

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {

                    count = count + 1;
                    listListener.ValueListen(childSnapshot);
                }
            }

            listListener.FinishListen();
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };

    private final ChildEventListener childValuesListener = new ChildEventListener() {

        @Override
        public void onChildAdded(@NonNull DataSnapshot childSnapshot, @Nullable String previousChildName) {

            myCounter.cancelCounter();
            valueListener.ChildAdded(childSnapshot);
            myCounter.startCounter();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot childSnapshot, @Nullable String previousChildName) {

            valueListener.ChildChanged(childSnapshot);
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot childSnapshot) {

            valueListener.ChildRemoved(childSnapshot);
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {
        }
    };

    private final MyCounter.Interfase counterInterfase = new MyCounter.Interfase() {

        @Override
        public void onFinish() {

            valueListener.finishAdded();
        }

        private void DummyVoid() {}
    };


    // ----- Aditional Tools ----- //

    public void removeListListener() {

        if (databaseReference != null) {

            databaseReference.removeEventListener(valueListListener);
        }
    }

    public void removeListListener(String child) {

        if (databaseReference != null) {

            databaseReference.child(child).removeEventListener(valueListListener);
        }
    }

    public void removeValueListener() {

        if (databaseReference != null) {

            databaseReference.removeEventListener(childValuesListener);
        }
    }

    public void removeValueListener(String child) {

        if (databaseReference != null) {

            databaseReference.child(child).removeEventListener(childValuesListener);
        }
    }


    // ----- Interface ----- //


    public interface SingleListener {

        void ValueListen(DataSnapshot dataSnapshot);
        default void ValueNonExist() {};
    }

    public interface ListListener {

        default void StartListen(boolean exist, long childCount) {};
        void ValueListen(DataSnapshot dataSnapshot);
        default void FinishListen(){};
    }

    public interface ValueListener {

        void ChildAdded(DataSnapshot dataSnapshot);
        void ChildChanged(DataSnapshot dataSnapshot);
        void ChildRemoved(DataSnapshot dataSnapshot);
        default void finishAdded() {};
    }

    public interface CompleteListener {

        default void Success() {};
        default void Failure() {};
    }
}
