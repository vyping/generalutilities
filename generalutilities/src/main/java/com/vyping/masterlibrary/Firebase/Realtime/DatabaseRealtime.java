package com.vyping.masterlibrary.Firebase.Realtime;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class DatabaseRealtime extends Readers {

    public static DatabaseReference DATABASEREFERENCE;
    public static DataSnapshot DATASNAPSHOT;


    // ----- Setup ----- //
    
    public DatabaseRealtime() {

        if (DATABASEREFERENCE == null) {

            FirebaseDatabase database = FirebaseDatabase.getInstance();
            database.setPersistenceEnabled(true);
            DATABASEREFERENCE = database.getReference();
        }
    }

    public DatabaseRealtime(String instance) {

        String Instance = "https://" + instance + ".firebaseio.com/";

        DATABASEREFERENCE = FirebaseDatabase.getInstance(Instance).getReference();
        DATABASEREFERENCE.keepSynced(true);
    }

    public DatabaseRealtime(String instance, String child1) {

        String Instance = "https://" + instance + ".firebaseio.com/";

        DATABASEREFERENCE = FirebaseDatabase.getInstance(Instance).getReference().child(child1);
        DATABASEREFERENCE.keepSynced(true);
    }

    public DatabaseRealtime(String instance, String child1, String child2) {

        String Instance = "https://" + instance + ".firebaseio.com/";

        DATABASEREFERENCE = FirebaseDatabase.getInstance(Instance).getReference().child(child1).child(child2);
        DATABASEREFERENCE.keepSynced(true);
    }

    public DatabaseRealtime(String instance, String child1, String child2, String child3) {

        String Instance = "https://" + instance + ".firebaseio.com/";

        DATABASEREFERENCE = FirebaseDatabase.getInstance(Instance).getReference().child(child1).child(child2).child(child3);
        DATABASEREFERENCE.keepSynced(true);
    }

    public DatabaseRealtime(String instance, String child1, String child2, String child3, String child4) {

        String Instance = "https://" + instance + ".firebaseio.com/";

        DATABASEREFERENCE = FirebaseDatabase.getInstance(Instance).getReference().child(child1).child(child2).child(child3).child(child4);
        DATABASEREFERENCE.keepSynced(true);
    }

    public DatabaseRealtime(String instance, String child1, String child2, String child3, String child4, String child5) {

        String Instance = "https://" + instance + ".firebaseio.com/";

        DATABASEREFERENCE = FirebaseDatabase.getInstance(Instance).getReference().child(child1).child(child2).child(child3).child(child4).child(child5);
        DATABASEREFERENCE.keepSynced(true);
    }


    // ---------- Methods - Section ---------- //

    public DatabaseReference getReference() {

        return DATABASEREFERENCE;
    }

    public void setValue(Object object) {

        if (object != null) {

            DATABASEREFERENCE.setValue(object);
        }
    }

    public void setValue(Object object, SuccessListener listener) {

        if (object != null) {

            DATABASEREFERENCE.setValue(object).addOnSuccessListener(new OnSuccessListener<>() {

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

            DATABASEREFERENCE.setValue(object).addOnSuccessListener(new OnSuccessListener<>() {

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

            DATABASEREFERENCE.child(child).setValue(object);
        }
    }

    public void setValue(String child, Object object, SuccessListener listener) {

        if (object != null) {

            DATABASEREFERENCE.child(child).setValue(object).addOnSuccessListener(new OnSuccessListener<>() {

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

            DATABASEREFERENCE.child(child).setValue(object).addOnSuccessListener(new OnSuccessListener<>() {

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

                DATABASEREFERENCE.updateChildren(hashMap);
            }
        }
    }

    public void updateChild(HashMap<String, Object> hashMap, SuccessListener listener) {

        if (hashMap != null) {

            if (!hashMap.isEmpty()) {

                DATABASEREFERENCE.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<>() {

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

                DATABASEREFERENCE.updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<>() {

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

                DATABASEREFERENCE.child(child).updateChildren(hashMap);
            }
        }
    }

    public void updateChild(String child, HashMap<String, Object> hashMap, SuccessListener listener) {

        if (hashMap != null) {

            if (!hashMap.isEmpty()) {

                DATABASEREFERENCE.child(child).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<>() {

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

                DATABASEREFERENCE.child(child).updateChildren(hashMap).addOnSuccessListener(new OnSuccessListener<>() {

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

        DATABASEREFERENCE.child(child).removeValue();
    }

    public void removeValue(String child, SuccessListener listener) {

        DATABASEREFERENCE.child(child).removeValue().addOnSuccessListener(new OnSuccessListener<>() {

            @Override
            public void onSuccess(Void aVoid) {

                listener.Success();
            }

            private void DummyVoid() {
            }

        });
    }

    public void removeValue(String child, CompleteListener listener) {

        DATABASEREFERENCE.child(child).removeValue().addOnSuccessListener(new OnSuccessListener<>() {

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


    // ----- Methods ----- //

    public void getListValues(ListValues listener) {}

    public void getListChanges(ListChanges listChanges) {}

    public void getValueChanges(ValueChanges valueChanges) {}


    // ----- Aditional Tools ----- //

    public void RemoveListListener(@NonNull ListChanges listChanges, String child) {

        listChanges.removeListListener(child);
    }

    public void RemoveValueListener(@NonNull ValueChanges valueChanges, String child) {

        valueChanges.removeValueListener(child);
    }


    // ----- Interface ----- //

    public interface SuccessListener {

        void Success();
    }

    public interface CompleteListener {

        void Success();
        void Failure();
    }
}
