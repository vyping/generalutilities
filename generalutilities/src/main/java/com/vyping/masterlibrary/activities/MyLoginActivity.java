package com.vyping.masterlibrary.activities;

import static java.lang.Boolean.TRUE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthProvider;
import com.vyping.masterlibrary.ActionBar.MyActionBar;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.Common.MyPermissions;
import com.vyping.masterlibrary.Common.MyToast;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.ToolBars.MyToolBars;
import com.vyping.masterlibrary.aplication.MyApplication;
import com.vyping.masterlibrary.authentication.MyAuthBiometric;
import com.vyping.masterlibrary.authentication.MyAuthGoogle;
import com.vyping.masterlibrary.authentication.MyAuthMail;
import com.vyping.masterlibrary.authentication.MyAuthPhone;

public abstract class MyLoginActivity extends AppCompatActivity {

    public MyApplication application;
    public Context context;
    public ViewDataBinding binding;
    private MyPermissions myPermissions;

    public MyActionBar actionBar;
    public MyToolBars myToolBars;
    public MyRealtime myRealtime;

    private FirebaseAuth firebaseAuth;
    private MyAuthMail myAuthMail;
    public MyAuthGoogle myAuthGoogle;
    public MyAuthPhone myAuthPhone;
    public MyAuthBiometric myAuthBiometric;

    private Class backActivity;


    // ----- SetUp ----- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (application == null) {

            application = (MyApplication) getApplication();
        }

        CreateActivity();
        StartProcess();
        InherentViews();
        ActivityViews();

        binding.executePendingBindings();

        LaunchProcess();
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }


    // ----- Start ----- //

    public MyLoginActivity activity(Activity activity, int layout) {

        this.context = new MyActivity().setTheme(activity);
        this.binding = DataBindingUtil.inflate(LayoutInflater.from(activity), layout, null, false);

        View view = binding.getRoot();
        setContentView(view);

        return this;
    }

    public MyLoginActivity actionBar(int icon, int module) {

        this.actionBar = new MyActionBar(context, icon, module) {};

        return this;
    }

    public MyLoginActivity onBack(Class backActivity) {

        this.backActivity = backActivity;

        return this;
    }

    public ViewDataBinding binding() {

        return binding;
    }

    public MyToolBars toolbar(int container) {

        if (myToolBars != null) {

            myToolBars = new MyToolBars(context, container) {};
        }

        return myToolBars;
    }


    // ----- Firebase ----- //

    public void setFirebaseService(String instance, Object listener)  {

        myRealtime = new MyRealtime(instance);

        if (listener instanceof MyRealtime.SingleListener) {

            MyRealtime.SingleListener singleListener = (MyRealtime.SingleListener) listener;
            myRealtime.getSingleValue(singleListener);

        } else if (listener instanceof  MyRealtime.ValueListener) {

            MyRealtime.ValueListener valueListener = (MyRealtime.ValueListener) listener;
            myRealtime.getValueChanges(valueListener);
        }
    }

    public void setFirebaseService(String instance, String child, Object listener) {

        myRealtime = new MyRealtime(instance, child);

        if (listener instanceof MyRealtime.SingleListener) {

            MyRealtime.SingleListener singleListener = (MyRealtime.SingleListener) listener;
            myRealtime.getSingleValue(singleListener);

        } else if (listener instanceof  MyRealtime.ValueListener) {

            MyRealtime.ValueListener valueListener = (MyRealtime.ValueListener) listener;
            myRealtime.getValueChanges(valueListener);
        }
    }


    // ----- Specific Methods ----- //

    public MyAuthGoogle googleStart(String appToken) {

        startFirebaseAuth();

        this.myAuthGoogle = new MyAuthGoogle(context, firebaseAuth).idToken(appToken);

        return myAuthGoogle;
    }

    public MyAuthGoogle googleLogin() {

        startFirebaseAuth();

        this.myAuthGoogle = myAuthGoogle.listener(new MyAuthGoogle.Interfase() {

            @Override
            public void LogIn(FirebaseUser firebaseUser) {

                LogSuccess(firebaseUser);
            }

            private void DummyVoid() {}
        });
        this.myAuthGoogle.launchAuthDialog();

        return myAuthGoogle;
    }

    public MyAuthMail emailLogUp(String email, String password, MyAuthMail.LogUpInterfase interfase) {

        startFirebaseAuth();
        serviceAuthEmail();

        myAuthMail.logUp(email, password, interfase);

        return myAuthMail;
    }

    public MyAuthMail emailLogIn(String email, String password) {

        startFirebaseAuth();
        serviceAuthEmail();

        myAuthMail.logIn(email, password, new MyAuthMail.LogInInterfase() {

            @Override
            public void LogInSuccess(FirebaseUser firebaseUser) {

                LogSuccess(firebaseUser);
            }

            @Override
            public void LogInFailed(String response) {}
        });

        return myAuthMail;
    }

    public MyAuthMail emailResetPassword(String email, MyAuthMail.ResetInterfase interfase) {

        startFirebaseAuth();
        serviceAuthEmail();

        myAuthMail.resetPassword(email, interfase);

        return myAuthMail;
    }

    public MyAuthPhone phoneLogin(String phone) {

        startFirebaseAuth();

        myAuthPhone = new MyAuthPhone(context, firebaseAuth).number(phone).login(new MyAuthPhone.Interfase() {

            @Override
            public void CodeSend(String code, PhoneAuthProvider.ForceResendingToken token) {

                new MyToast(context, "¡Código enviado por SMS!");
            }

            @Override
            public void Verified(FirebaseUser firebaseUser) {

                LogSuccess(firebaseUser);
            }
        });

        return myAuthPhone;
    }

    public MyAuthBiometric biometricLogIn(MyAuthBiometric.Interfase interfase) {

        myAuthBiometric = new MyAuthBiometric(context, interfase);

        return myAuthBiometric;
    }


    // ----- Tools----- //

    public void isLogged() {

        startFirebaseAuth();
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        firebaseAuth.signOut();
        if (firebaseUser != null) {

            biometricLogIn(new MyAuthBiometric.Interfase() {

                @Override
                public void Authenticated(int authType) {

                    LogSuccess(firebaseUser);
                }

                private void DummyVoid() {}
            });
        }
    }

    public void startFirebaseAuth() {

        if (firebaseAuth == null) {

            firebaseAuth = com.google.firebase.auth.FirebaseAuth.getInstance();
        }
    }

    private void serviceAuthEmail() {

        if (myAuthMail == null) {

            myAuthMail = new MyAuthMail(context, firebaseAuth);
        }
    }


    // ----- Interfase ----- //

    protected abstract void CreateActivity();
    protected abstract void StartProcess();
    protected void InherentViews() {};
    protected void ActivityViews() {};
    protected void LaunchProcess() {};
    protected abstract void LogSuccess(FirebaseUser firebaseUser);
    protected void StopProcess() {};
    protected void ActivityResults(int requestCode, int resultCode, Intent data) {};


    // ----- Permissions ----- //

    public void requestPermissions(String[] permissions, int arrayParameters, int code, MyPermissions.Interfase interfase) {

        myPermissions = new MyPermissions(context, arrayParameters, permissions, code);
        myPermissions.RequestPermissions(interfase);
    }

    public void requestPermissions(String[] permissions, int arrayParameters, int code, int minVersion, MyPermissions.Interfase interfase) {

        myPermissions = new MyPermissions(context, arrayParameters, permissions, code);
        myPermissions.RequestPermissions(interfase);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        myPermissions.PermissionsResult(requestCode, permissions, grantResults);
    }


    // ----- Inherents ----- //

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        myAuthGoogle.onActivityResult(requestCode, data);

        ActivityResults(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {

        if (backActivity != null) {

            new MyActivity().Start(context, backActivity, TRUE);
        }
    }

    @Override
    public void onLowMemory() {

        super.onLowMemory();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        if ( myRealtime != null) {

            myRealtime.removeValueListener();
        }

        StopProcess();
    }
}
