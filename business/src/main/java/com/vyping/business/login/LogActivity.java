package com.vyping.business.login;

import static com.vyping.business.utilities.definitions.Modules.MODULE_ICON_START;
import static com.vyping.business.utilities.definitions.Modules.MODULE_NAME_START;
import static java.lang.Boolean.TRUE;

import androidx.annotation.NonNull;

import android.annotation.SuppressLint;
import android.view.View;

import com.google.firebase.auth.FirebaseUser;
import com.vyping.business.R;
import com.vyping.business.databinding.LogActivityBinding;
import com.vyping.business.menu.MenuActivity;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.GestureListeners.MyTouchListener;
import com.vyping.masterlibrary.activities.MyLoginActivity;

public class LogActivity extends MyLoginActivity {

    public LogActivityBinding binding;

    String googleToken;
    String phone = "+573014114388";

    // ----- SetUp ----- //

    @Override
    protected void CreateActivity() {

        this.binding = (LogActivityBinding) activity(LogActivity.this, R.layout.log_activity).actionBar(MODULE_ICON_START, MODULE_NAME_START).binding();
    }

    @Override
    protected void StartProcess() {

        googleToken = new MyString().getStringResources(context, R.string.credentials_google_auth_id);

        startFirebaseAuth();
        googleStart(googleToken);
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