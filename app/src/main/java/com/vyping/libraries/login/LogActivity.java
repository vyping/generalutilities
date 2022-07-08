package com.vyping.libraries.login;

import static android.Manifest.permission.READ_CONTACTS;
import static android.Manifest.permission.READ_PHONE_NUMBERS;
import static android.Manifest.permission.READ_PHONE_STATE;
import static android.Manifest.permission.READ_SMS;
import static android.Manifest.permission.WRITE_CONTACTS;
import static android.content.ContentValues.TAG;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_ICON_MAIN;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_NAME_MAIN;
import static com.vyping.masterlibrary.Common.MyPermissions.PERMISSION_ACCOUNTS_GET;
import static com.vyping.masterlibrary.Common.MyPermissions.PERMISSION_CONTACTS_READ;
import static com.vyping.masterlibrary.Common.MyPermissions.PERMISSION_CONTACTS_WRITE;
import static com.vyping.masterlibrary.Common.MyPermissions.PERMISSION_PHONE_NUMBERS;
import static com.vyping.masterlibrary.Common.MyPermissions.PERMISSION_PHONE_STATE;

import static java.lang.Boolean.TRUE;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.IntentSender;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.telephony.TelephonyManager;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.credentials.HintRequest;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.firebase.auth.FirebaseUser;
import com.vyping.libraries.R;
import com.vyping.libraries.databinding.LogActivityBinding;

import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.Common.MyPermissions;
import com.vyping.masterlibrary.GestureListeners.MyTouchListener;
import com.vyping.masterlibrary.activities.MyLoginActivity;
import com.vyping.masterlibrary.activities.MyMenuActivity;
import com.vyping.masterlibrary.authentication.MyAuthBiometric;
import com.vyping.masterlibrary.authentication.MyAuthGoogle;

public class LogActivity extends MyLoginActivity {

    public LogActivityBinding binding;

    String googleToken = "299303784824-t9a6b2dm1vkokcrla1sl5kk3j0m3ou7a.apps.googleusercontent.com";
    String phone = "+573014114388";

    // ----- SetUp ----- //

    @Override
    protected void CreateActivity() {

        this.binding = (LogActivityBinding) activity(LogActivity.this, R.layout.log_activity).actionBar(MODULE_ICON_MAIN, MODULE_NAME_MAIN).binding();
    }

    @Override
    protected void StartProcess() {

        //emailLogUp("oscar_villabon@hotmail.com", "PULGRAZ");
        //sendVerificationSMS("+573014114388");

        startFirebaseAuth();
        googleStart(googleToken);
    }

    @Override
    protected void InherentViews() {}

    @Override @SuppressLint("ClickableViewAccessibility")
    protected void ActivityViews() {

        binding.BtnLogup.setOnTouchListener(new MyTouchListener(context, new MyTouchListener.TouchInterface() {

            @Override
            public void TouchEvent(View selectedView) {

                new MyActivity().Start(context, LogupActivity.class, TRUE);
            }

            private void DummyVoid() {}
        }));
        binding.BtnLogin.setOnTouchListener(new MyTouchListener(context, new MyTouchListener.TouchInterface() {

            @Override
            public void TouchEvent(View selectedView) {

                new MyActivity().Start(context, LoginActivity.class, TRUE);
            }

            private void DummyVoid() {}
        }));
        binding.BtnGoogle.setOnTouchListener(new MyTouchListener(context, new MyTouchListener.TouchInterface() {

            @Override
            public void TouchEvent(View selectedView) {

                googleLogin();
            }

            private void DummyVoid() {}
        }));
        binding.BtnPhone.setOnTouchListener(new MyTouchListener(context, new MyTouchListener.TouchInterface() {

            @Override
            public void TouchEvent(View selectedView) {

                phoneLogin(phone);
            }

            private void DummyVoid() {}
        }));
        binding.BtnFacebook.setOnTouchListener(new MyTouchListener(context, new MyTouchListener.TouchInterface() {

            @Override
            public void TouchEvent(View selectedView) {


            }

            private void DummyVoid() {}
        }));
    }

    @Override
    protected void LaunchProcess() {

        isLogged();
    }

    @Override
    protected void LogSuccess(@NonNull FirebaseUser firebaseUser) {

        new LogCat("LogSuccess", firebaseUser.getDisplayName());

        new MyActivity().Start(context, MenuActivity.class, TRUE);
    }
}