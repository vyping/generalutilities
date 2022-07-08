package com.vyping.masterlibrary.authentication;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.SignInMethodQueryResult;
import com.vyping.masterlibrary.Common.LogCat;

import java.util.List;

public class MyAuthFirebase {

    protected final Context context;
    protected final FirebaseAuth firebaseAuth;
    private Interfase interfase;


    /*----- Setup -----*/

    public MyAuthFirebase(Context context) {

        this.context = context;
        this.firebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
    }

    public MyAuthFirebase(Context context, FirebaseAuth firebaseAuth) {

        this.context = context;
        this.firebaseAuth = firebaseAuth;
    }


    // ----- Methods ----- //

    public MyAuthFirebase interfase(Interfase interfase) {

        this.interfase = interfase;

        return this;
    }

    public void getSigninMethodsList() {

        firebaseAuth.fetchSignInMethodsForEmail("oscaravillabon@hotmail.com").addOnCompleteListener(new OnCompleteListener<>() {

            @Override
            public void onComplete(@NonNull Task<SignInMethodQueryResult> task) {

                if (task.isSuccessful()) {

                    SignInMethodQueryResult result = task.getResult();
                    List<String> signInMethods = result.getSignInMethods();

                    new LogCat( "signInMethods", signInMethods);

                    if (signInMethods.contains(EmailAuthProvider.EMAIL_PASSWORD_SIGN_IN_METHOD)) {

                    } else if (signInMethods.contains(EmailAuthProvider.EMAIL_LINK_SIGN_IN_METHOD)) {

                    }

                } else {

                    new LogCat( "Error getting sign in methods for user", task.getException());
                }
            }
        });
    }

    private void logInWithCredential(AuthCredential credential) {

        firebaseAuth.signInWithCredential(credential).addOnCompleteListener((Activity) context, new OnCompleteListener<>() {

            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (interfase != null) {

                    if (task.isSuccessful()) {

                        FirebaseUser user = task.getResult().getUser();

                        if (user != null && interfase != null) {

                            interfase.LoginSuccess(user);
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

    public void linkWithCredential(AuthCredential credential) {

        if (firebaseAuth.getCurrentUser() != null) {

            firebaseAuth.getCurrentUser().linkWithCredential(credential).addOnCompleteListener((Activity) context, new OnCompleteListener<AuthResult>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        FirebaseUser user = task.getResult().getUser();

                        if (user != null && interfase != null) {

                            interfase.AccounLinked(user);
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

        default void SigninMethods(List<String> listMethods) {};
        default void LoginSuccess(FirebaseUser firebaseUser) {};
        default void AccounLinked(FirebaseUser firebaseUser) {};
        default void Failed(String error) {};
    }
}
