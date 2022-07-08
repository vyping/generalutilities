package com.vyping.masterlibrary.authentication;

import static java.lang.Boolean.FALSE;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.vyping.masterlibrary.Common.LogCat;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MyAuthMail {

    public static final String RESPONSE_SUCCESS = "Success", RESPONSE_UNVERIFIED = "Unverified";
    @Retention(RetentionPolicy.SOURCE)
    @StringDef({RESPONSE_SUCCESS, RESPONSE_UNVERIFIED})
    public @interface Response {}

    private final Activity activity;
    private final FirebaseAuth firebaseAuth;


    /*----- Setup -----*/

    public MyAuthMail(Context context) {

        this.activity = (Activity) context;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public MyAuthMail(Context context, FirebaseAuth firebaseAuth) {

        this.activity = (Activity) context;
        this.firebaseAuth = firebaseAuth;
    }


    /*----- Methods -----*/


    public void logUp(String email, String password, LogUpInterfase interfase) {

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    FirebaseUser user = getCurrentUser();
                    verificationMail(user, interfase);

                    if (interfase != null) {

                        interfase.LogUpSuccess(user);
                    }

                } else {

                    if (interfase != null) {

                        String error = String.valueOf(task.getException());
                        interfase.LogUpFailed(error);
                    }
                }
            }

            private void DummyVoid() {};
        });
    }

    public void logIn(String email, String password, LogInInterfase interfase) {

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    boolean verified = checkIfEmailVerified();

                    if (verified) {

                        FirebaseUser user = getCurrentUser();

                        if (user != null && interfase != null) {

                           interfase.LogInSuccess(user);
                        }

                    } else {

                        logOut();

                        if (interfase != null) {

                            interfase.LogInFailed(RESPONSE_UNVERIFIED);
                        }
                    }

                } else {

                    if (interfase != null) {

                        String error = String.valueOf(task.getException());
                        interfase.LogInFailed(error);
                    }
                }
            };

            private void DummyVoid() {};
        });
    }

    public void logOut() {

        firebaseAuth.signOut();
    }

    public void resetPassword(String email, ResetInterfase interfase) {

        firebaseAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    interfase.ResetPassword(RESPONSE_SUCCESS);

                } else {

                    String error = String.valueOf(task.getException());
                    interfase.ResetPassword(error);
                }
            }

            private void DummyVoid() {};

        }).addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception exception) {

                interfase.ResetPassword(exception.getMessage());
            }

            private void DummyVoid() {};
        });
    }


    /*----- Tools -----*/

    public FirebaseUser getCurrentUser() {

        return firebaseAuth.getCurrentUser();
    }

    private boolean checkIfEmailVerified() {

        boolean verified = FALSE;

        if (firebaseAuth.getCurrentUser() != null) {

            verified = firebaseAuth.getCurrentUser().isEmailVerified();
        }

        return verified;
    }

    private void verificationMail(@NonNull FirebaseUser firebaseUser, LogUpInterfase interfase) {

        firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {

            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if (task.isSuccessful()) {

                    FirebaseAuth.getInstance().signOut();

                    if (interfase != null) {

                        interfase.VerifyMail(RESPONSE_SUCCESS);
                    }

                } else {

                    if (interfase != null) {

                        String error = String.valueOf(task.getException());
                        interfase.VerifyMail(error);
                    }
                }
            }

            private void DummyVoid() {};
        });
    }


    // ----- Interface ----- //

    public interface LogUpInterfase {

        void LogUpSuccess(FirebaseUser firebaseUser);
        void LogUpFailed(String error);
        void VerifyMail(String response);;
    }
    public interface LogInInterfase {

        void LogInSuccess(FirebaseUser firebaseUser);
        void LogInFailed(String response);
    }
    public interface ResetInterfase {
        void ResetPassword(String response);
    }
}
