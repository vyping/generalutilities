package com.vyping.masterlibrary.authentication;

import static java.lang.Boolean.FALSE;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

public class MyAuthBiometric {

    private final Interfase interfase;


    /*----- Setup -----*/


    public MyAuthBiometric(Context context, Interfase interfase) {

        this.interfase = interfase;

        Executor executor = ContextCompat.getMainExecutor(context);

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Inicio de sesión por biometría")
                .setSubtitle("Autenticadores disponibles:")
                .setDescription("Ingrese usando sus datos biométricos")
                .setNegativeButtonText("Volver")
                .setConfirmationRequired(FALSE)
                .build();

        BiometricPrompt biometricPrompt = new BiometricPrompt((FragmentActivity) context, executor, callbacks);
        biometricPrompt.authenticate(promptInfo);
    }


    /*----- Listeners -----*/

    private final BiometricPrompt.AuthenticationCallback callbacks = new BiometricPrompt.AuthenticationCallback() {


        @Override
        public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {

            super.onAuthenticationSucceeded(result);

            interfase.Authenticated(result.getAuthenticationType());
        }

        @Override
        public void onAuthenticationFailed() {

            super.onAuthenticationFailed();

            interfase.Failed();
        }

        @Override
        public void onAuthenticationError(int errorCode, @NonNull CharSequence errString) {

            super.onAuthenticationError(errorCode, errString);

            interfase.Error(errorCode, errString);
        }
    };


    /*----- Tools -----*/



    // ----- Interface ----- //

    public interface Interfase {

        void Authenticated(int authType);
        default void Failed() {};
        default void Error(int errorCode, @NonNull CharSequence errString) {};
    }
}
