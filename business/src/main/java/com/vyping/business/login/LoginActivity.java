package com.vyping.business.login;

import static com.vyping.business.utilities.definitions.Modules.MODULE_ICON_LOGIN;
import static com.vyping.business.utilities.definitions.Modules.MODULE_NAME_LOGIN;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.annotation.NonNull;

import com.google.firebase.auth.FirebaseUser;
import com.vyping.business.R;
import com.vyping.business.databinding.LoginActivityBinding;
import com.vyping.business.menu.MenuActivity;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.GestureListeners.MyTouchListener;
import com.vyping.masterlibrary.activities.MyLoginActivity;

public class LoginActivity extends MyLoginActivity {

    public LoginActivityBinding binding;


    // ----- SetUp ----- //

    @Override
    protected void CreateActivity() {

        this.binding = (LoginActivityBinding) activity(LoginActivity.this, R.layout.login_activity).actionBar(MODULE_ICON_LOGIN, MODULE_NAME_LOGIN).onBack(LogActivity.class).binding();
    }

    @Override
    protected void StartProcess() {

        startFirebaseAuth();
    }

    @Override
    protected void InherentViews() {

        actionBar.setButtonOption(R.drawable.icon_configuration, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        actionBar.showButtonTools();
    }


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