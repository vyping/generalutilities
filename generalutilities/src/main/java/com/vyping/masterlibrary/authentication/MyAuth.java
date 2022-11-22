package com.vyping.masterlibrary.authentication;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MyAuth {

    private final Activity activity;
    public final FirebaseAuth firebaseAuth;
    public Interfase interfase;


    /*----- Setup -----*/

    public MyAuth(@NonNull Context context, Interfase interfase) {

        this.activity = (Activity) context;
        this.firebaseAuth = FirebaseAuth.getInstance();
        this.interfase = interfase;
    }

    public MyAuth(@NonNull Context context, FirebaseAuth firebaseAuth, Interfase interfase) {

        this.activity = (Activity) context;
        this.firebaseAuth = firebaseAuth;
        this.interfase = interfase;
    }


    /*----- Methods -----*/

    public void linkWithCredential(AuthCredential credential) {

        if (firebaseAuth.getCurrentUser() != null) {

            firebaseAuth.getCurrentUser().linkWithCredential(credential).addOnCompleteListener(activity, new OnCompleteListener<>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        FirebaseUser user = task.getResult().getUser();

                        if (user != null && interfase != null) {

                            interfase.Success(user);
                        }

                    } else {

                        String error = String.valueOf(task.getException());

                        if (error.contains("User has already been linked to the given provider")) {

                            logInWithCredential(credential);

                        } else {

                            interfase.Failed(error);
                        }
                    }
                }

                private void DummyVoid() {}
            });
        }
    }

    public void logInWithCredential(AuthCredential credential) {

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(activity, new OnCompleteListener<>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (interfase != null) {

                    if (task.isSuccessful()) {

                        FirebaseUser user = task.getResult().getUser();

                        if (user != null && interfase != null) {

                            interfase.Success(user);
                        }

                    } else {

                        String error = String.valueOf(task.getException());
                        interfase.Failed(error);
                    }
                }
            }

            private void DummyVoid() {}
        });
    }


    // ----- Interface ----- //

    public interface Interfase {

        void Success(FirebaseUser firebaseUser);
        default void Failed(String error) {};
    }
}
