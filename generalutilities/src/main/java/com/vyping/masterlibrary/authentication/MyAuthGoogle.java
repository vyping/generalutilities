package com.vyping.masterlibrary.authentication;

import static com.google.android.gms.common.ConnectionResult.INVALID_ACCOUNT;
import static com.google.android.gms.common.ConnectionResult.NETWORK_ERROR;
import static com.google.android.gms.common.ConnectionResult.SIGN_IN_REQUIRED;
import static com.google.android.gms.common.ConnectionResult.SUCCESS;

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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;
import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.Common.MyToast;
import com.vyping.masterlibrary.time.MyDelay;

public class MyAuthGoogle extends MyAuthFirebase {

    private final GoogleSignInOptions.Builder googleBuilder;
    private GoogleSignInClient googleClient;

    public static final int REQUEST_CODE_GOOGLE_AUTH = 10000;


    /*----- Setup -----*/

    public MyAuthGoogle() {

        super();

        googleBuilder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN);
    }

    public MyAuthGoogle(FirebaseAuth firebaseAuth) {

        super(firebaseAuth);

        googleBuilder = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN);
    }


    // ----- Sign Methods ----- //

    public MyAuthGoogle idToken(@NonNull Context context) {

        int intGoogleToken = context.getResources().getIdentifier("credentials_google_auth_id", "string", context.getPackageName());
        String idToken = new MyString().getStringResources(context, intGoogleToken);

        GoogleSignInOptions googleSignInOptions = googleBuilder.requestIdToken(idToken).build();
      //  GoogleSignInOptions googleSignInOptions = googleBuilder.requestIdToken(idToken).requestEmail().build();
        googleClient = GoogleSignIn.getClient(context, googleSignInOptions);

        return this;
    }

    public GoogleSignInAccount lastAuthSigned(Context context) {

        return GoogleSignIn.getLastSignedInAccount(context);
    }

    public void launchAuthDialog(Context context) {

        new MyDelay(1000).interfase(new MyDelay.Interfase() {

            @Override
            public void Execute() {

                Intent intent = googleClient.getSignInIntent();
                ((Activity) context).startActivityForResult(intent, REQUEST_CODE_GOOGLE_AUTH);
            }

            private void DummyVoid() {}
        });
    }

    public void onActivityResult(Context context, int requestCode, Intent data) {

        if(requestCode == REQUEST_CODE_GOOGLE_AUTH) {

            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);

            if (result != null) {

                handleSignInResult(context, result);

            } else {

                interfase.Failed();
                new MyToast(context, "¡No se detectó cuenta alguna!");
            }
        }
    }

    public void logInSilently(Context context) {

        googleClient.silentSignIn().addOnCompleteListener(((Activity) context), new OnCompleteListener<>() {

            @Override
            public void onComplete(@NonNull Task<GoogleSignInAccount> task) {

                if (task.isSuccessful()) {

                    GoogleSignInAccount googleSignInAccount = task.getResult();

                    if (googleSignInAccount != null) {

                        logInWithGoogle(context, googleSignInAccount);
                    }

                } else {

                    interfase.LoggedSilent();
                }
            }

            private void DummyVoid() {}
        });
    }

    public void logInWithGoogle(Context context, @NonNull GoogleSignInAccount googleAccount) {

        String token = googleAccount.getIdToken();
        AuthCredential credential = GoogleAuthProvider.getCredential(token, null);

        logInWithCredential(context, credential);
    }

    public void logOutFromGoogle() {

        googleClient.signOut().addOnCompleteListener(new OnCompleteListener<>() {

            public void onComplete(@NonNull Task<Void> task) {

                interfase.LogOutUser();
            }

            private void DummyVoid() {}
        });
    }


    /*----- Tools -----*/

    private void handleSignInResult(Context context, @NonNull GoogleSignInResult result) {

        int status = result.getStatus().getStatusCode();

        if (status == SUCCESS) {

            if (result.isSuccess()) {

                GoogleSignInAccount googleAccount = result.getSignInAccount();

                if (googleAccount != null) {

                    String token = googleAccount.getIdToken();
                    AuthCredential credential = GoogleAuthProvider.getCredential(token, null);
                    logInWithCredential(context, credential);

                } else {

                    String error = result.getStatus().getStatusMessage();
                    new MyToast(context, "Error: " + error);

                    interfase.Failed();
                }

            } else {

                String error = result.getStatus().getStatusMessage();
                new MyToast(context, "Error: " + error);

                interfase.Failed();
            }

        } else {

            if (status == SIGN_IN_REQUIRED) {

                new MyToast(context, "¡Sesión requerida!");

            } else if (status == NETWORK_ERROR) {

                new MyToast(context, "¡Error en la conexión!");

            } else if (status == INVALID_ACCOUNT) {

                new MyToast(context, "¡Cuenta invalida!");

            } else {

                new MyToast(context, "¡Error oódigo: " + status + "!");
            }

            interfase.Failed();
        }
    }
}
