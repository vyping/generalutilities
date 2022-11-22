package com.vyping.masterlibrary.Common;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEvent;
import net.yslibrary.android.keyboardvisibilityevent.KeyboardVisibilityEventListener;

public class MyKeyboard {



    public void show(Context context) {

        AppCompatActivity activity = (AppCompatActivity) context;
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
    }

    public void show(@NonNull View view) {

        Context context = view.getContext();

        InputMethodManager imputManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        if (imputManager != null) {

            imputManager.showSoftInputFromInputMethod(view.getWindowToken(), 0);
        }
    }

    public void hide(Context context, View view) {

        AppCompatActivity activity = (AppCompatActivity) context;
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void hide(@NonNull View view) {

        Context context = view.getContext();

        InputMethodManager imputManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

        if (imputManager != null) {

            imputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    public boolean done(int keyCode) {

        return keyCode == EditorInfo.IME_ACTION_DONE;
    }

    public void visibilityListener(Context context, Interfase interfase) {

        KeyboardVisibilityEvent.setEventListener((Activity) context, new KeyboardVisibilityEventListener() {

            @Override
            public void onVisibilityChanged(boolean isOpen) {

                if (isOpen) {

                    interfase.Open();

                } else {

                    interfase.Close();
                }
            }

            private void DummyVoid() {}
        });
    }

    public interface Interfase {

        default void Open() {};
        default void Close() {};
    }
}
