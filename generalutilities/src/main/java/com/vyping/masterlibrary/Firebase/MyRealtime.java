package com.vyping.masterlibrary.Firebase;

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
import com.vyping.masterlibrary.Timers.MyCounter;

import java.util.ArrayList;
import java.util.HashMap;

public class MyRealtime extends Readers {

    private DatabaseReference databaseReference;
    private DataSnapshot dataSnapshot;
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

    public MyRealtime(String instance, String child1) {

        String Instance = "https://" + instance + ".firebaseio.com/";

        databaseReference = FirebaseDatabase.getInstance(Instance).getReference().child(child1);
        databaseReference.keepSynced(true);
    }

    public MyRealtime(String instance, String child1, String child2) {

        String Instance = "https://" + instance + ".firebaseio.com/";

        databaseReference = FirebaseDatabase.getInstance(Instance).getReference().child(child1).child(child2);
        databaseReference.keepSynced(true);
    }

    public MyRealtime(String instance, String child1, String child2, String child3) {

        String Instance = "https://" + instance + ".firebaseio.com/";

        databaseReference = FirebaseDatabase.getInstance(Instance).getReference().child(child1).child(child2).child(child3);
        databaseReference.keepSynced(true);
    }

    public MyRealtime(String instance, String child1, String child2, String child3, String child4) {

        String Instance = "https://" + instance + ".firebaseio.com/";

        databaseReference = FirebaseDatabase.getInstance(Instance).getReference().child(child1).child(child2).child(child3).child(child4);
        databaseReference.keepSynced(true);
    }

    public MyRealtime(String instance, String child1, String child2, String child3, String child4, String child5) {

        String Instance = "https://" + instance + ".firebaseio.com/";

        databaseReference = FirebaseDatabase.getInstance(Instance).getReference().child(child1).child(child2).child(child3).child(child4).child(child5);
        databaseReference.keepSynced(true);
    }


    // ----- Child Managers ----- //

    public DatabaseReference getDatabaseChild(String child1) {

        if (databaseReference != null) {

            return databaseReference.child(child1);

        } else {

            return null;
        }
    }

    public DatabaseReference getDatabaseChild(String child1, String child2) {

        if (databaseReference != null) {

            return databaseReference.child(child1).child(child2);

        } else {

            return null;
        }
    }

    public DatabaseReference getDatabaseChild(String child1, String child2, String child3) {

        if (databaseReference != null) {

            return databaseReference.child(child1).child(child2).child(child3);

        } else {

            return null;
        }
    }

    public DatabaseReference getDatabaseChild(String child1, String child2, String child3, String child4) {

        if (databaseReference != null) {

            return databaseReference.child(child1).child(child2).child(child3).child(child4);

        } else {

            return null;
        }
    }

    public DatabaseReference getDatabaseChild(String child1, String child2, String child3, String child4, String child5) {

        if (databaseReference != null) {

            return databaseReference.child(child1).child(child2).child(child3).child(child4).child(child5);

        } else {

            return null;
        }
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

    public void getSingleValue(String child, SingleListener listener) {

        databaseReference.child(child).addListenerForSingleValueEvent(new ValueEventListener() {

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

                int count = 0;

                listener.StartListen(dataSnapshot.exists(), dataSnapshot.getChildrenCount());

                if (dataSnapshot.exists()) {

                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {

                        count = count + 1;

                        MyRealtime.this.dataSnapshot = childSnapshot;
                        String key = getKeyString(MyRealtime.this.dataSnapshot);

                        listener.ValueListen(key, childSnapshot, count);
                    }
                }

                listener.FinishListen();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void getListValues(String child, ListListener listener) {

        databaseReference.child(child).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int count = 0;

                listener.StartListen(dataSnapshot.exists(), dataSnapshot.getChildrenCount());

                if (dataSnapshot.exists()) {

                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {

                        count = count + 1;

                        MyRealtime.this.dataSnapshot = childSnapshot;
                        String key = getKeyString(MyRealtime.this.dataSnapshot);

                        listener.ValueListen(key, childSnapshot, count);
                    }
                }

                listener.FinishListen();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void getListValuesOrganized(String order, ListListener listener) {

        databaseReference.orderByChild(order).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int count = 0;

                listener.StartListen(dataSnapshot.exists(), dataSnapshot.getChildrenCount());

                if (dataSnapshot.exists()) {

                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {

                        count = count + 1;

                        MyRealtime.this.dataSnapshot = childSnapshot;
                        String key = getKeyString(MyRealtime.this.dataSnapshot);

                        listener.ValueListen(key, childSnapshot, count);
                    }
                }

                listener.FinishListen();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    public void getListValuesOrganized(String child, String order, ListListener listener) {

        databaseReference.child(child).orderByChild(order).addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                int count = 0;

                listener.StartListen(dataSnapshot.exists(), dataSnapshot.getChildrenCount());

                if (dataSnapshot.exists()) {

                    for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {

                        count = count + 1;

                        MyRealtime.this.dataSnapshot = childSnapshot;
                        String key = getKeyString(MyRealtime.this.dataSnapshot);

                        listener.ValueListen(key, childSnapshot, count);
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

    public void getListChanges(String child, ListListener listener) {

        this.listListener = listener;

        databaseReference.child(child).addValueEventListener(valueListListener);
    }

    public void getListChangesOrganized(String order, ListListener listener) {

        this.listListener = listener;

        databaseReference.orderByChild(order).addValueEventListener(valueListListener);
    }

    public void getListChangesOrganized(String child, String order, ListListener listener) {

        this.listListener = listener;

        databaseReference.child(child).orderByChild(order).addValueEventListener(valueListListener);
    }

    public void getValueChanges(ValueListener listener) {

        this.valueListener = listener;
        this.myCounter = new MyCounter(500, 100, FALSE, counterInterfase);

        databaseReference.addChildEventListener(childValuesListener);
    }

    public void getValueChanges(String child, ValueListener listener) {

        this.valueListener = listener;
        this.myCounter = new MyCounter(500, 100, FALSE, counterInterfase);

        databaseReference.child(child).addChildEventListener(childValuesListener);
    }

    public void getValueChangesOrganized(String order, ValueListener listener) {

        this.valueListener = listener;
        this.myCounter = new MyCounter(500, 100, FALSE, counterInterfase);

        databaseReference.orderByChild(order).addChildEventListener(childValuesListener);
    }

    public void getValueChangesOrganized(String child, String order, ValueListener listener) {

        this.valueListener = listener;
        this.myCounter = new MyCounter(500, 100, FALSE, counterInterfase);

        databaseReference.child(child).orderByChild(order).addChildEventListener(childValuesListener);
    }


    // ---------- Methods  ---------- //

    public DatabaseReference getReference() {

        return databaseReference;
    }

    public void pushValue(Object object) {

        if (object != null) {

            databaseReference.push().setValue(object);
        }
    }

    public void pushValue(Object object, SuccessListener listener) {

        if (object != null) {

            databaseReference.push().setValue(object).addOnSuccessListener(new OnSuccessListener<>() {

                @Override
                public void onSuccess(Void aVoid) {

                    listener.Success();
                }

                private void DummyVoid() {
                }

            });
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

    public void pushValue(String child, Object object) {

        if (object != null) {

            databaseReference.push().child(child).setValue(object);
        }
    }

    public void pushValue(String child, Object object, SuccessListener listener) {

        if (object != null) {

            databaseReference.push().child(child).setValue(object).addOnSuccessListener(new OnSuccessListener<>() {

                @Override
                public void onSuccess(Void aVoid) {

                    listener.Success();
                }

                private void DummyVoid() {
                }

            });
        }
    }

    public void pushValue(String child, Object object, CompleteListener listener) {

        if (object != null) {

            databaseReference.push().child(child).setValue(object).addOnSuccessListener(new OnSuccessListener<>() {

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

    public void setValue(Object object, SuccessListener listener) {

        if (object != null) {

            databaseReference.setValue(object).addOnSuccessListener(new OnSuccessListener<>() {

                @Override
                public void onSuccess(Void aVoid) {

                    listener.Success();
                }

                private void DummyVoid() {
                }
            });
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

    public void setValue(String child, Object object) {

        if (object != null) {

            databaseReference.child(child).setValue(object);
        }
    }

    public void setValue(String child, Object object, SuccessListener listener) {

        if (object != null) {

            databaseReference.child(child).setValue(object).addOnSuccessListener(new OnSuccessListener<>() {

                @Override
                public void onSuccess(Void aVoid) {

                    listener.Success();
                }

                private void DummyVoid() {
                }
            });
        }
    }

    public void setValue(String child, Object object, CompleteListener listener) {

        if (object != null) {

            databaseReference.child(child).setValue(object).addOnSuccessListener(new OnSuccessListener<>() {

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

    public void updateChild(HashMap<String, Object> hashMap, SuccessListener listener) {

        if (hashMap != null) {

            if (!hashMap.isEmpty()) {

                databaseReference.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<>() {

                    @Override
                    public void onSuccess(Void aVoid) {

                        listener.Success();
                    }

                    private void DummyVoid() {
                    }
                });
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

    public void updateChild(String child, HashMap<String, Object> hashMap) {

        if (hashMap != null) {

            if (!hashMap.isEmpty()) {

                databaseReference.child(child).updateChildren(hashMap);
            }
        }
    }

    public void updateChild(String child, HashMap<String, Object> hashMap, SuccessListener listener) {

        if (hashMap != null) {

            if (!hashMap.isEmpty()) {

                databaseReference.child(child).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<>() {

                    @Override
                    public void onSuccess(Void aVoid) {

                        listener.Success();
                    }

                    private void DummyVoid() {
                    }

                });
            }
        }
    }

    public void updateChild(String child, HashMap<String, Object> hashMap, CompleteListener listener) {

        if (hashMap != null) {

            if (!hashMap.isEmpty()) {

                databaseReference.child(child).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<>() {

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

    public void removeValue(String child) {

        databaseReference.child(child).removeValue();
    }

    public void removeValue(String child, SuccessListener listener) {

        databaseReference.child(child).removeValue().addOnSuccessListener(new OnSuccessListener<>() {

            @Override
            public void onSuccess(Void aVoid) {

                listener.Success();
            }

            private void DummyVoid() {
            }

        });
    }

    public void removeValue(String child, CompleteListener listener) {

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


    // ----- Listeners ----- //

    private final ValueEventListener valueListListener = new ValueEventListener() {

        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

            int count = 0;

            listListener.StartListen(dataSnapshot.exists(), dataSnapshot.getChildrenCount());

            if (dataSnapshot.exists()) {

                for (DataSnapshot childSnapshot : dataSnapshot.getChildren()) {

                    count = count + 1;

                    MyRealtime.this.dataSnapshot = childSnapshot;
                    String key = getKeyString(MyRealtime.this.dataSnapshot);

                    listListener.ValueListen(key, childSnapshot, count);
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

            dataSnapshot = childSnapshot;
            valueListener.ChildAdded(childSnapshot);

            myCounter.startCounter();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot childSnapshot, @Nullable String previousChildName) {

            dataSnapshot = childSnapshot;
            valueListener.ChildChanged(childSnapshot);
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot childSnapshot) {

            dataSnapshot = childSnapshot;
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
        public void onTick(long remain) {
        }

        @Override
        public void onFinish() {

            valueListener.finishAdded();
        }
    };


    // ----- Aditional Tools ----- //

    public void removeListListener() {

        databaseReference.removeEventListener(valueListListener);
    }

    public void removeListListener(String child) {

        databaseReference.child(child).removeEventListener(valueListListener);
    }

    public void removeValueListener() {

        databaseReference.removeEventListener(childValuesListener);
    }

    public void removeValueListener(String child) {

        databaseReference.child(child).removeEventListener(childValuesListener);
    }


    // ----- Interface ----- //

    public interface TimeListener {

        void StartListen(boolean exist, long childCount);
    }

    public interface SingleListener {

        void ValueListen(DataSnapshot dataSnapshot);

        void ValueNonExist();
    }

    public interface ListListener {

        void StartListen(boolean exist, long childCount);

        void ValueListen(String key, @NonNull DataSnapshot dataSnapshot, int count);

        void FinishListen();
    }

    public interface ValueListener {

        void ChildAdded(@NonNull DataSnapshot snapChild);

        void ChildChanged(@NonNull DataSnapshot snapChild);

        void ChildRemoved(@NonNull DataSnapshot snapChild);

        void finishAdded();
    }

    public interface SuccessListener {

        void Success();
    }

    public interface CompleteListener {

        void Success();

        void Failure();
    }


    // ----- Key Readers ----- //

    public String getKeyString() {

        return getKeyString(dataSnapshot);
    }

    public Long getKeyLong() {

        return getKeyLong(dataSnapshot);
    }


    // ----- ChildCount Readers ----- //

    public long getChildrenCount() {

        return getChildrenCount(dataSnapshot);
    }

    public long getChildrenCount(String child) {

        if (dataSnapshot.exists()) {

            return getChildrenCount(dataSnapshot.child(child));

        } else {

            return 0L;
        }
    }


    // ----- Boolean Readers ----- //

    public boolean getBoolean() {

        return getBooleanOrDefault(dataSnapshot, FALSE);
    }

    public boolean getBoolean(String child) {

        return getBooleanOrDefault(dataSnapshot.child(child), FALSE);
    }

    public boolean getBooleanOrDefault(boolean boolDefault) {

        return getBooleanOrDefault(dataSnapshot, boolDefault);
    }

    public boolean getBooleanOrDefault(String child, boolean boolDefault) {

        if (dataSnapshot.exists()) {

            return getBooleanOrDefault(dataSnapshot.child(child), boolDefault);

        } else {

            return boolDefault;
        }
    }


    // ----- String Readers ----- //

    public String getString() {

        return getStringOrDefault(dataSnapshot, "");
    }

    public String getString(String child) {

        return getStringOrDefault(dataSnapshot.child(child), "");
    }

    public String getStringOrDefault(String textDefault) {

        return getStringOrDefault(dataSnapshot, textDefault);
    }

    public String getStringOrDefault(String child, String textDefault) {

        if (dataSnapshot.exists()) {

            return getStringOrDefault(dataSnapshot.child(child), textDefault);

        } else {

            return textDefault;
        }
    }


    // ----- Integer Readers ----- //

    public int getInteger() {

        return getIntegerOrDefault(dataSnapshot, 0);
    }

    public int getInteger(String child) {

        return getIntegerOrDefault(dataSnapshot.child(child), 0);
    }

    public int getIntegerOrDefault(int numDefault) {

        return getIntegerOrDefault(dataSnapshot, numDefault);
    }

    public int getIntegerOrDefault(String child, int numDefault) {

        if (dataSnapshot.exists()) {

            return getIntegerOrDefault(dataSnapshot.child(child), numDefault);

        } else {

            return numDefault;
        }
    }


    // ----- Long Readers ----- //

    public long getLong() {

        return getLongOrDefault(dataSnapshot, 0L);
    }

    public long getLong(String child) {

        return getLongOrDefault(dataSnapshot.child(child), 0L);
    }

    public long getLongOrDefault(long numDefault) {

        return getLongOrDefault(dataSnapshot, numDefault);
    }

    public long getLongOrDefault(String child, long numDefault) {

        if (dataSnapshot.exists()) {

            return getLongOrDefault(dataSnapshot.child(child), numDefault);

        } else {

            return numDefault;
        }
    }


    // ----- Float Readers ----- //

    public float getFloat() {

        return getFloatOrDefault(dataSnapshot, 0f);
    }

    public float getFloat(String child) {

        return getFloatOrDefault(dataSnapshot.child(child), 0f);
    }

    public float getFloatOrDefault(float numDefault) {

        return getFloatOrDefault(dataSnapshot, numDefault);
    }

    public float getFloatOrDefault(String child, float numDefault) {

        if (dataSnapshot.exists()) {

            return getFloatOrDefault(dataSnapshot.child(child), numDefault);

        } else {

            return numDefault;
        }
    }

    // ----- Double Readers ----- //

    public double getDouble() {

        return getDoubleOrDefault(dataSnapshot, 0.0);
    }

    public double getDouble(String child) {

        return getDoubleOrDefault(dataSnapshot.child(child), 0.0);
    }

    public double getDoubleOrDefault(double numDefault) {

        return getDoubleOrDefault(dataSnapshot, numDefault);
    }

    public double getDoubleOrDefault(String child, double numDefault) {

        if (dataSnapshot.exists()) {

            return getDoubleOrDefault(dataSnapshot.child(child), numDefault);

        } else {

            return numDefault;
        }
    }


    // ----- Hour Readers ----- //

    public String getHour() {

        return getHourOrDefault(dataSnapshot, "00:00");
    }

    public String getHour(String child) {

        return getHourOrDefault(dataSnapshot.child(child), "00:00");
    }

    public String getHourOrDefault(String hourDefault) {

        return getHourOrDefault(dataSnapshot, hourDefault);
    }

    public String getHourOrDefault(String child, String hourDefault) {

        if (dataSnapshot.exists()) {

            return getHourOrDefault(dataSnapshot.child(child), hourDefault);

        } else {

            return hourDefault;
        }
    }


    // ----- Date Readers ----- //

    public String getDate() {

        return getDateOrDefault(dataSnapshot, "00-00-00");
    }

    public String getDate(String child) {

        return getDateOrDefault(dataSnapshot.child(child), "00-00-00");
    }

    public String getDateOrDefault(String dateDefault) {

        return getDateOrDefault(dataSnapshot, dateDefault);
    }

    public String getDateOrDefault(String child, String dateDefault) {

        if (dataSnapshot.exists()) {

            return getDateOrDefault(dataSnapshot.child(child), dateDefault);

        } else {

            return dateDefault;
        }
    }


    // ----- Time Readers ----- //

    public String getTime(String format) {

        return getTimeOrDefault(dataSnapshot, "-", format);
    }

    public String getTime(String child, String format) {

        return getTimeOrDefault(dataSnapshot.child(child), "-", format);
    }

    public String getTimeOrDefault(String timeDefault, String format) {

        return getTimeOrDefault(dataSnapshot, timeDefault, format);
    }

    public String getTimeOrDefault(String child, String timeDefault, String format) {

        if (dataSnapshot.exists()) {

            return getTimeOrDefault(dataSnapshot.child(child), timeDefault, format);

        } else {

            return timeDefault;
        }
    }


    // ----- LatLng Readers ----- //

    public LatLng getLatLng() {

        double latitude = 0.0, longitude = 0.0;

        if (dataSnapshot.exists()) {

            latitude = getDouble(dataSnapshot.child("latitude"));
            longitude = getDouble(dataSnapshot.child("longitude"));
        }

        return new LatLng(latitude, longitude);
    }

    public LatLng getLatLng(String child) {

        double latitude = 0.0, longitude = 0.0;

        if (dataSnapshot.exists()) {

            latitude = getDouble(dataSnapshot.child("latitude"));
            longitude = getDouble(dataSnapshot.child("longitude"));
        }

        return new LatLng(latitude, longitude);
    }

    public LatLng getLatLngOrDefault(LatLng latLngDefault) {

        return getLatLngOrDefault(dataSnapshot, latLngDefault);
    }

    public LatLng getLatLngOrDefault(String child, LatLng latLngDefault) {

        return getLatLngOrDefault(dataSnapshot.child(child), latLngDefault);
    }

    public LatLng getLatLngOrDefault(double latDefault, double lngDefault) {

        return getLatLngOrDefault(dataSnapshot, latDefault, lngDefault);
    }

    public LatLng getLatLngOrDefault(String child, double latDefault, double lngDefault) {

        if (dataSnapshot.child(child).exists()) {

            return getLatLngOrDefault(dataSnapshot.child(child), latDefault, lngDefault);

        } else {

            return new LatLng(latDefault, lngDefault);
        }
    }


    // ----- Location Readers ----- //

    public Location getLocation() {

        return getLocationOrDefault(dataSnapshot, 0.0, 0.0);
    }

    public Location getLocation(String child) {

        return getLocationOrDefault(dataSnapshot.child(child), 0.0, 0.0);
    }

    public Location getLocationOrDefault(Location locDefault) {

        return getLocationOrDefault(dataSnapshot, locDefault);
    }

    public Location getLocationOrDefault(String child, Location locDefault) {

        return getLocationOrDefault(dataSnapshot.child(child), locDefault);
    }

    public Location getLocationOrDefault(double latDefault, double lngDefault) {

        return getLocationOrDefault(dataSnapshot, latDefault, lngDefault);
    }

    public Location getLocationOrDefault(String child, double latDefault, double lngDefault) {

        return getLocationOrDefault(dataSnapshot.child(child), latDefault, lngDefault);
    }


    // ----- List Readers ----- //

    public String getList() {

        return getList(dataSnapshot);
    }

    public String getList(String child) {

        return getList(dataSnapshot.child(child));
    }


    // ----- Array Readers ----- //

    public ArrayList<String> getArrayString() {

        return getArrayString(dataSnapshot);
    }

    public ArrayList<String> getArrayString(String child) {

        if (dataSnapshot.exists()) {

            return getArrayString(dataSnapshot.child(child));

        } else {

            return new ArrayList<>();
        }
    }


    // ----- HashMap Readers ----- //

    public HashMap<String, Object> getHashMap() {

        return getHashMap(dataSnapshot);
    }

    public HashMap<String, Object> getHashMap(String child) {

        if (dataSnapshot.exists()) {

            return getHashMap(dataSnapshot.child(child));

        } else {

            return new HashMap<>();
        }
    }

    public HashMap<String, String> getHashMapString() {

        return getHashMapString(dataSnapshot);
    }

    public HashMap<String, String> getHashMapString(String child) {

        if (dataSnapshot.exists()) {

            return getHashMapString(dataSnapshot.child(child));

        } else {

            return new HashMap<>();
        }
    }
}
