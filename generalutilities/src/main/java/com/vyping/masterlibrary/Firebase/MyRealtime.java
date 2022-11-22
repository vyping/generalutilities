package com.vyping.masterlibrary.Firebase;

import static com.vyping.masterlibrary.aplication.MyApplication.USED_INSTANCES;
import static java.lang.Boolean.FALSE;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vyping.masterlibrary.Timers.MyCounter;

import java.util.HashMap;

public class MyRealtime {

    private DatabaseReference mainReference, secondaryReference;
    private MyCounter myCounter;

    private ListListener listListener;
    private ValueListener valueListener;


    // ----- Setup ----- //

    public MyRealtime() {

        if (mainReference == null) {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
            mainReference = database.getReference();
            mainReference.keepSynced(true);
        }
    }

    public MyRealtime(String instance) {

        String Instance = "https://" + instance + ".firebaseio.com/";

        FirebaseDatabase database = FirebaseDatabase.getInstance(Instance);

        if (!USED_INSTANCES.contains(Instance)) {

            database.setPersistenceEnabled(true);

            USED_INSTANCES.add(Instance);
        }

        mainReference = database.getReference();
        mainReference.keepSynced(true);
    }

    @SafeVarargs
    public MyRealtime(String instance, @NonNull String... childs) {

        String Instance = "https://" + instance + ".firebaseio.com/";

        FirebaseDatabase database = FirebaseDatabase.getInstance(Instance);

        if (!USED_INSTANCES.contains(Instance)) {

            database.setPersistenceEnabled(true);

            USED_INSTANCES.add(Instance);
        }

        mainReference = database.getReference();
        mainReference.keepSynced(true);

        for (String child : childs) {

            reference(child);
        }
    }


    // ----- Readers ----- //

    public MyRealtime getSingleValue(SingleListener listener) {

        mainReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    listener.ValueListen(dataSnapshot);

                } else {

                    listener.ValueNonExist("¡No existe la información requerida!");
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                listener.Failure(error.getMessage());
            }
        });

        return this;
    }

    public MyRealtime getListValues(ListListener listener) {

        mainReference.addListenerForSingleValueEvent(new ValueEventListener() {

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

        return this;
    }

    public MyRealtime getListChanges(ListListener listener) {

        this.listListener = listener;

        mainReference.addValueEventListener(valueListListener);

        return this;
    }

    public MyRealtime getListChanges(String child, ListListener listener) {

        this.listListener = listener;

        mainReference.child(child).addValueEventListener(valueListListener);

        return this;
    }

    public MyRealtime getValueChanges(ValueListener listener) {

        this.valueListener = listener;
        this.myCounter = new MyCounter(500, 100, FALSE, counterInterfase);

        mainReference.addChildEventListener(childValuesListener);

        return this;
    }


    // ---------- ModelMethods  ---------- //

    public DatabaseReference getReference() {

        return mainReference;
    }

    public MyRealtime reference(String child) {

        mainReference = mainReference.child(child);

        return this;
    }

    public MyRealtime child(String child1) {

        if (mainReference != null) {

            mainReference.child(child1);
        }

        return this;
    }

    public String pushValue(Object object) {

        if (object != null) {

            DatabaseReference databaseReference = mainReference.push();
            databaseReference.setValue(object);

            return databaseReference.getKey();

        } else {

            return "";
        }
    }

    public String pushValue(String child, Object object) {

        if (object != null) {

            DatabaseReference databaseReference = mainReference.child(child).push();
            databaseReference.setValue(object);

            return databaseReference.getKey();

        } else {

            return "";
        }
    }

    public String pushValue(Object object, CompleteListener listener) {

        if (object != null) {

            DatabaseReference databaseReference = mainReference.push();
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

                    listener.Failure(e.getMessage());
                }

                private void DummyVoid() {
                }
            });

            return databaseReference.getKey();

        } else {

            listener.Failure("Sin información para crear");

            return "";
        }
    }

    public String pushValue(String child, Object object, CompleteListener listener) {

        if (object != null) {

            DatabaseReference databaseReference = mainReference.child(child).push();
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

                    listener.Failure(e.getMessage());
                }

                private void DummyVoid() {
                }
            });

            return databaseReference.getKey();

        } else {

            listener.Failure("Sin información para crear");

            return "";
        }
    }

    public void setValue(Object object) {

        if (object != null) {

            mainReference.setValue(object);
        }
    }

    public void setValue(String child, Object object) {

        if (object != null) {

            mainReference.child(child).setValue(object);
        }
    }

    public void setValue(Object object, CompleteListener listener) {

        if (object != null) {

            mainReference.setValue(object).addOnSuccessListener(new OnSuccessListener<>() {

                @Override
                public void onSuccess(Void aVoid) {

                    listener.Success();
                }

                private void DummyVoid() {
                }

            }).addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception e) {

                    listener.Failure(e.getMessage());
                }

                private void DummyVoid() {
                }
            });

        } else {

            listener.Failure("Sin información para guardar");
        }
    }

    public void setValue(String child, Object object, CompleteListener listener) {

        if (object != null) {

            mainReference.child(child).setValue(object).addOnSuccessListener(new OnSuccessListener<>() {

                @Override
                public void onSuccess(Void aVoid) {

                    listener.Success();
                }

                private void DummyVoid() {
                }

            }).addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception e) {

                    listener.Failure(e.getMessage());
                }

                private void DummyVoid() {
                }
            });

        } else {

            listener.Failure("Sin información para guardar");
        }
    }

    public void updateChild(String child, HashMap<String, Object> hashMap) {

        if (hashMap != null) {

            if (!hashMap.isEmpty()) {

                mainReference.child(child).updateChildren(hashMap);
            }
        }
    }

    public void updateChild(HashMap<String, Object> hashMap) {

        if (hashMap != null) {

            if (!hashMap.isEmpty()) {

                mainReference.updateChildren(hashMap);
            }
        }
    }

    public void updateChild(HashMap<String, Object> hashMap, CompleteListener listener) {

        if (hashMap != null) {

            if (!hashMap.isEmpty()) {

                mainReference.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<>() {

                    @Override
                    public void onSuccess(Void aVoid) {

                        listener.Success();
                    }

                    private void DummyVoid() {
                    }

                }).addOnFailureListener(new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception e) {

                        listener.Failure(e.getMessage());
                    }

                    private void DummyVoid() {
                    }
                });

            } else {

                listener.Failure("Sin información para actualizar");
            }

        } else {

            listener.Failure("Sin información para actualizar");
        }
    }

    public void updateChild(String child, HashMap<String, Object> hashMap, CompleteListener listener) {

        if (hashMap != null) {

            if (!hashMap.isEmpty()) {

                mainReference.child(child).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<>() {

                    @Override
                    public void onSuccess(Void aVoid) {

                        listener.Success();
                    }

                    private void DummyVoid() {}

                }).addOnFailureListener(new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception e) {

                        listener.Failure(e.getMessage());
                    }

                    private void DummyVoid() {}
                });

            } else {

                listener.Failure("Sin información para actualizar");
            }

        } else {

            listener.Failure("Sin información para actualizar");
        }
    }

    public void removeChild(String child) {

        mainReference.child(child).removeValue();
    }

    public void removeChild(String path, String child) {

        mainReference.child(path).child(child).removeValue();
    }

    public void removeChild(String child, CompleteListener listener) {

        mainReference.child(child).removeValue().addOnSuccessListener(new OnSuccessListener<>() {

            @Override
            public void onSuccess(Void aVoid) {

                listener.Success();
            }

            private void DummyVoid() {}

        }).addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {

                listener.Failure(e.getMessage());
            }

            private void DummyVoid() {}
        });
    }

    public void removeChild(String path, String child, CompleteListener listener) {

        mainReference.child(path).child(child).removeValue().addOnSuccessListener(new OnSuccessListener<>() {

            @Override
            public void onSuccess(Void aVoid) {

                listener.Success();
            }

            private void DummyVoid() {
            }

        }).addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception e) {

                listener.Failure(e.getMessage());
            }

            private void DummyVoid() {}
        });
    }

    public MyRealtime orderByChild(String child1) {

        if (mainReference != null) {

            mainReference.orderByChild(child1);
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

            } else {

                listListener.Failure("!No existe la información Requerida¡");
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

        if (mainReference != null) {

            mainReference.removeEventListener(valueListListener);
        }
    }

    public void removeListListener(String child) {

        if (mainReference != null) {

            mainReference.child(child).removeEventListener(valueListListener);
        }
    }

    public void removeValueListener() {

        if (mainReference != null) {

            mainReference.removeEventListener(childValuesListener);
        }
    }

    public void removeValueListener(String child) {

        if (mainReference != null) {

            mainReference.child(child).removeEventListener(childValuesListener);
        }
    }


    // ----- Interface ----- //

    public interface SingleListener {

        void ValueListen(DataSnapshot dataSnapshot);
        default void ValueNonExist(String error) {};
        default void Failure(String error) {};
    }

    public interface ListListener {

        default void StartListen(boolean exist, long childCount) {};
        void ValueListen(DataSnapshot dataSnapshot);
        default void FinishListen(){};
        default void Failure(String error) {};
    }

    public interface ValueListener {

        default void ChildAdded(DataSnapshot dataSnapshot) {};
        default void ChildChanged(DataSnapshot dataSnapshot) {};
        default void ChildRemoved(DataSnapshot dataSnapshot) {};
        default void finishAdded() {};
    }

    public interface CompleteListener {

        default void Success() {};
        default void Failure(String error) {};
    }
}
