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
import com.vyping.libraries.databinding.LogupActivityBinding;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.GestureListeners.MyTouchListener;
import com.vyping.masterlibrary.activities.MyLoginActivity;
import com.vyping.masterlibrary.activities.MyMenuActivity;
import com.vyping.masterlibrary.authentication.MyAuthBiometric;

public class LogupActivity extends MyLoginActivity {

    public LogupActivityBinding binding;


    // ----- SetUp ----- //

    @Override
    protected void CreateActivity() {

        this.binding = (LogupActivityBinding) activity(LogupActivity.this, R.layout.logup_activity).actionBar(MODULE_ICON_MAIN, MODULE_NAME_MAIN).onBack(LogActivity.class).binding();
    }

    @Override
    protected void StartProcess() {

        startFirebaseAuth();
    }

    @Override
    protected void InherentViews() {}


    @Override @SuppressLint("ClickableViewAccessibility")
    protected void ActivityViews() {

        binding.BtnLogup.setOnTouchListener(new MyTouchListener(context, new MyTouchListener.TouchInterface() {

            @Override
            public void TouchEvent(View selectedView) {}

            private void DummyVoid() {}
        }));
        binding.BtnLogin.setOnTouchListener(new MyTouchListener(context, new MyTouchListener.TouchInterface() {

            @Override
            public void TouchEvent(View selectedView) {

                new MyActivity().Start(context, LoginActivity.class, TRUE);
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