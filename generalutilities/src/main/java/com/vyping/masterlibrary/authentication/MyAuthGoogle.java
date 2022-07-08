package com.vyping.masterlibrary.authentication;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class MyAuthGoogle extends MyAuthFirebase {

    private GoogleSignInOptions.Builder googleBuilder;
    private GoogleSignInClient googleClient;
    private GoogleSignInAccount googleAccount;
    private Interfase interfase;

    private String idToken;
    private static final int RC_SIGN_IN = 1;


    /*----- Setup -----*/

    public MyAuthGoogle(Context context) {

        super(context);

        googleBuilder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN);
    }

    public MyAuthGoogle(Context context, FirebaseAuth firebaseAuth) {

        super(context, firebaseAuth);

        googleBuilder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN);
    }


    // ----- Methods ----- //

    public MyAuthGoogle idToken(String idToken) {

        this.idToken = idToken;

        GoogleSignInOptions googleSignInOptions = googleBuilder.requestIdToken(idToken).requestEmail().build();
        googleClient = GoogleSignIn.getClient(context, googleSignInOptions);

        return this;
    }

    public MyAuthGoogle lastSigned() {

        googleAccount = GoogleSignIn.getLastSignedInAccount(context);

        return this;
    }

    public MyAuthGoogle listener(Interfase interfase) {

        this.interfase = interfase;

        return this;
    }

    public void launchAuthDialog() {

        Intent intent = googleClient.getSignInIntent();
        ((Activity) context).startActivityForResult(intent, RC_SIGN_IN);
    }

    public void onActivityResult(int requestCode, Intent data) {

        if(requestCode == RC_SIGN_IN) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result != null) {

                handleSignInResult(result);
            }
        }
    }

    public MyAuthGoogle logOut() {

        googleClient.signOut();

        if (interfase != null) {

            interfase.LogOut();
        }

        return this;
    }


    /*----- Tools -----*/

    private void handleSignInResult(@NonNull GoogleSignInResult result) {

        if (result.isSuccess()) {

            googleAccount = result.getSignInAccount();

            if (googleAccount != null) {

                String token = googleAccount.getIdToken();

                AuthCredential credential = GoogleAuthProvider.getCredential(token, null);
                logInWithCredential(credential);

            } else {

                if (interfase != null) {

                    String error = result.getStatus().getStatusMessage();
                    interfase.Failed(error );
                }
            }

        } else {

            if (interfase != null) {

                String error = String.valueOf(result);
                interfase.Failed(error);
            }
        }
    }

    private void logInWithCredential(AuthCredential credential) {

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener((Activity) context, new OnCompleteListener<>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (interfase != null) {

                    if (task.isSuccessful()) {

                        FirebaseUser user = task.getResult().getUser();

                        if (user != null && interfase != null) {

                            interfase.LogIn(user);
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

    public void linkWithCredential() {

        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);

        if (firebaseAuth.getCurrentUser() != null) {

            firebaseAuth.getCurrentUser().linkWithCredential(credential).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        FirebaseUser user = task.getResult().getUser();

                        if (user != null && interfase != null) {

                            interfase.Linked(user);
                        }

                    } else {

                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                            if (interfase != null) {

                                interfase.Failed(task.getException().getMessage());
                            }
                        }
                    }
                }

                private void DummyVoid() {}
            });
        }
    }


    // ----- Interface ----- //

    public interface Interfase {

        void LogIn(FirebaseUser firebaseUser);
        default void LogOut() {};
        default void Linked(FirebaseUser firebaseUser) {};
        default void Failed(String error) {};
    }
}
