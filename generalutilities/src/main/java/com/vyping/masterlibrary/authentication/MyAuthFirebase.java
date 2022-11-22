package com.vyping.masterlibrary.authentication;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.vyping.masterlibrary.Common.MyToast;
import com.vyping.masterlibrary.aplication.MyApplication;
import java.util.List;

public class MyAuthFirebase {

    public MyApplication.UidInterfase interfase;

    private FirebaseAuth authFirebase;
    private FirebaseUser userFirebase;


    /*----- Setup -----*/

    public MyAuthFirebase() {

        setParameters(FirebaseAuth.getInstance());
    }

    public MyAuthFirebase(FirebaseAuth firebaseAuth) {

        setParameters(firebaseAuth);
    }


    // ----- Methods ----- //

    private void setParameters(FirebaseAuth firebaseAuth) {

        authFirebase = firebaseAuth;
        userFirebase = authFirebase.getCurrentUser();
    }

    public MyAuthFirebase getLoggedStatus(MyApplication.UidInterfase interfase) {

        this.interfase = interfase;
        this.authFirebase.addAuthStateListener(authStateListener);

        if (authFirebase.getCurrentUser() == null) {

            this.interfase.LoggedStatus(FALSE);

        } else {

            this.interfase.LoggedStatus(TRUE);
        }

        return this;
    }


    // ----- Sign Methods ----- //

    public FirebaseUser getCurrentUser() {

        return userFirebase;
    }

    public void getSigningMethodsList() {

        authFirebase.fetchSignInMethodsForEmail("oscaravillabon@hotmail.com").addOnCompleteListener(new OnCompleteListener<>() {

            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                if (task.isSuccessful()) {

                    SignInMethodQueryResult result = task.getResult();
                    List<String> signInMethods = result.getSignInMethods();

                    if (signInMethods.contains(EmailAuthProvider.EMAIL_PASSWORD_SIGN_IN_METHOD)) {

                    } else if (signInMethods.contains(EmailAuthProvider.EMAIL_LINK_SIGN_IN_METHOD)) {

                    }
                }
            }

            private void DummyVoid() {};
        });
    }

    public void logInAnonymously(Context context) {

        authFirebase.signInAnonymously().addOnCompleteListener(((Activity) context), new OnCompleteListener<>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    userFirebase = authFirebase.getCurrentUser();
                    interfase.LoggedAnonimously(userFirebase);

                } else {

                    if (task.getException() != null) {

                        String error = String.valueOf(task.getException().getMessage());
                        new MyToast(context, "¡Error:" + error + "!");

                    } else {

                        new MyToast(context, "¡Error al iniciar anonimamente!");
                    }

                    interfase.Failed();
                }
            }

            private void DummyVoid() {}
        });
    }

    public void linkWithCredential(Context context, AuthCredential credential) {

        if (authFirebase.getCurrentUser() != null) {

            authFirebase.getCurrentUser().linkWithCredential(credential).addOnCompleteListener((Activity) context, new OnCompleteListener<>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        FirebaseUser firebaseUser = task.getResult().getUser();

                        if (firebaseUser != null && interfase != null) {

                            userFirebase = firebaseUser;
                            interfase.LoggedSuccess(userFirebase);

                            new MyToast(context, "¡Sesión iniciada con éxito!");
                        }

                    } else {

                        if (task.getException() != null) {

                            String error = String.valueOf(task.getException());

                            if (error.contains("User has already been linked to the given provider")) {

                                logInWithCredential(context, credential);

                            } else {

                                String errorMessage = String.valueOf(task.getException().getMessage());
                                new MyToast(context, "Error: " + errorMessage);
                                interfase.Failed();
                            }

                        } else {

                            new MyToast(context, "¡Imposible iniciar sesión!");
                            interfase.Failed();
                        }
                    }
                }

                private void DummyVoid() {}
            });
        }
    }

    public void logInWithCredential(Context context, AuthCredential credential) {

        authFirebase.signInWithCredential(credential).addOnCompleteListener((Activity) context, new OnCompleteListener<>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {

                    FirebaseUser firebaseUser = task.getResult().getUser();

                    if (firebaseUser != null) {

                        userFirebase = firebaseUser;
                        interfase.LoggedSuccess(firebaseUser);

                        new MyToast(context, "¡Sesión iniciada con éxito!");
                    }

                } else {

                    if (task.getException() != null) {

                        String error = String.valueOf(task.getException().getMessage());
                        new MyToast(context, "Error: " + error);

                    } else {

                        new MyToast(context, "¡Imposible iniciar sesión!");
                    }

                    interfase.Failed();
                }
            }

            private void DummyVoid() {}
        });
    }

    public void logOutFirebase(@NonNull Context context) {

        authFirebase.signOut();
        userFirebase = null;
    }


    // ----- Listener Methods ----- //

    private final FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {

        @Override
        public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {

            FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        }

        private void DummyVOid(){}
    };


    // ----- Tools Methods ----- //

    public void removeAuthListener() {

        authFirebase.removeAuthStateListener(authStateListener);
    }
}
