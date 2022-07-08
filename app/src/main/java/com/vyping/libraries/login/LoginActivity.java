package com.vyping.libraries.login;

import static com.vyping.libraries.utilities.definitions.Modules.MODULE_ICON_MAIN;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_NAME_MAIN;

import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;
import com.vyping.libraries.R;
import com.vyping.libraries.databinding.LoginActivityBinding;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.GestureListeners.MyTouchListener;
import com.vyping.masterlibrary.activities.MyLoginActivity;
import com.vyping.masterlibrary.activities.MyMenuActivity;
import com.vyping.masterlibrary.authentication.MyAuthBiometric;

public class LoginActivity extends MyLoginActivity {

    public LoginActivityBinding binding;


    // ----- SetUp ----- //

    @Override
    protected void CreateActivity() {

        this.binding = (LoginActivityBinding) activity(LoginActivity.this, R.layout.login_activity).actionBar(MODULE_ICON_MAIN, MODULE_NAME_MAIN).onBack(LogActivity.class).binding();
    }

    @Override
    protected void StartProcess() {

        startFirebaseAuth();
    }

    @Override
    protected void InherentViews() {}


    @Override @SuppressLint("ClickableViewAccessibility")
    protected void ActivityViews() {

        binding.BtnLogin.setOnTouchListener(new MyTouchListener(context, new MyTouchListener.TouchInterface() {

            @Override
            public void TouchEvent(View selectedView) {

                String email = binding.EtEmail.getText().toString();
                String password = binding.EtPassword.getText().toString();
                String phone = binding.EtPhone.getText().toString();

                if (!email.equals("") && !password.equals("")) {

                    emailLogIn(email, password);
                }

                if (!phone.equals("")) {

                    phoneLogin("+57" + phone);
                }
            }

            private void DummyVoid() {}
        }));
        binding.BtnLogup.setOnTouchListener(new MyTouchListener(context, new MyTouchListener.TouchInterface() {

            @Override
            public void TouchEvent(View selectedView) {

                new MyActivity().Start(context, LogupActivity.class, TRUE);
            }

            private void DummyVoid() {}
        }));
    }

    @Override
    protected void LaunchProcess() {}

    @Override
    protected void LogSuccess(@NonNull FirebaseUser firebaseUser) {

        new LogCat("LogSuccess", firebaseUser.getDisplayName());

        new MyActivity().Start(context, MenuActivity.class, TRUE);
    }
}