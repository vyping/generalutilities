package com.vyping.masterlibrary.authentication;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.FirebaseTooManyRequestsException;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class MyAuthPhone {

    private final Activity activity;
    private final FirebaseAuth firebaseAuth;
    private PhoneAuthOptions.Builder builder;
    private PhoneAuthOptions phoneAuthOptions;
    private Interfase interfase;

    public String VerificationId, SmsCode;


    /*----- Setup -----*/

    public MyAuthPhone(Context context) {

        this.activity = (Activity) context;
        this.firebaseAuth = FirebaseAuth.getInstance();
    }

    public MyAuthPhone(Context context, FirebaseAuth firebaseAuth) {

        this.activity = (Activity) context;
        this.firebaseAuth = firebaseAuth;
    }


    /*----- Methods -----*/

    public MyAuthPhone number(String phone) {

        builder = PhoneAuthOptions.newBuilder(firebaseAuth).setPhoneNumber(phone).setTimeout(60L, TimeUnit.SECONDS).setActivity(activity);

        return this;
    }

    public MyAuthPhone login(Interfase interfase) {

        this.interfase = interfase;
        this.phoneAuthOptions = builder.setCallbacks(loginCallbacks).build();

        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);

        return this;
    }

    public MyAuthPhone link(Interfase interfase) {

        this.interfase = interfase;
        this.phoneAuthOptions = builder.setCallbacks(linkCallbacks).build();

        PhoneAuthProvider.verifyPhoneNumber(phoneAuthOptions);

        return this;
    }


    /*----- Listeners -----*/

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks loginCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {

            SmsCode = credential.getSmsCode();
            logInWithCredential(credential);
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException exception) {

            if (exception instanceof FirebaseAuthInvalidCredentialsException) {

                interfase.CredentialsInvalid((FirebaseAuthInvalidCredentialsException) exception);

            } else if (exception instanceof FirebaseTooManyRequestsException) {

                interfase.TooManyRequests((FirebaseTooManyRequestsException) exception);
            }
        }

        @Override
        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {

            VerificationId = verificationId;
            interfase.CodeSend(verificationId, token);
        }
    };

    private final PhoneAuthProvider.OnVerificationStateChangedCallbacks linkCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onVerificationCompleted(@NonNull PhoneAuthCredential credential) {

            SmsCode = credential.getSmsCode();
            linkWithCredential();
        }

        @Override
        public void onVerificationFailed(@NonNull FirebaseException exception) {

            if (exception instanceof FirebaseAuthInvalidCredentialsException) {

                interfase.CredentialsInvalid((FirebaseAuthInvalidCredentialsException) exception);

            } else if (exception instanceof FirebaseTooManyRequestsException) {

                interfase.TooManyRequests((FirebaseTooManyRequestsException) exception);
            }
        }

        @Override
        public void onCodeSent(@NonNull String verificationId, @NonNull PhoneAuthProvider.ForceResendingToken token) {

            VerificationId = verificationId;
            interfase.CodeSend(verificationId, token);
        }
    };


    /*----- Tools -----*/

    private void logInWithCredential(PhoneAuthCredential credential) {

        if (firebaseAuth != null) {

            firebaseAuth.signInWithCredential(credential).addOnCompleteListener(activity, new OnCompleteListener<>() {

                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {

                    if (task.isSuccessful()) {

                        FirebaseUser user = task.getResult().getUser();

                        if (user != null && interfase != null) {

                            interfase.Verified(user);
                        }

                    } else {

                        if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {

                            if (interfase != null) {

                                interfase.CredentialsInvalid((FirebaseAuthInvalidCredentialsException) task.getException());
                            }
                        }
                    }
                }

                private void DummyVoid() {}
            });
        }
    }

    private void linkWithCredential() {

        AuthCredential credential = PhoneAuthProvider.getCredential(VerificationId, SmsCode);

        if (firebaseAuth.getCurrentUser() != null) {

            firebaseAuth.getCurrentUser().linkWithCredential(credential).addOnCompleteListener(activity, new OnCompleteListener<AuthResult>() {

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

                                interfase.CredentialsInvalid((FirebaseAuthInvalidCredentialsException) task.getException());
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

        void CodeSend(String code, PhoneAuthProvider.ForceResendingToken token);
        default void Verified(FirebaseUser firebaseUser) {};
        default void Linked(FirebaseUser firebaseUser) {};
        default void CredentialsInvalid(FirebaseAuthInvalidCredentialsException exception) {};
        default void TooManyRequests(FirebaseTooManyRequestsException exception) {};
    }
}
