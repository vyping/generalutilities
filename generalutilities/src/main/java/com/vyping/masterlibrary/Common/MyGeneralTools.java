package com.vyping.masterlibrary.Common;

import static android.content.Context.VIBRATOR_SERVICE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.multidex.BuildConfig;

import java.util.Objects;

public class MyGeneralTools {

    public void appUpdate(@NonNull Context context) {

        final String appPackageName = context.getPackageName();

        try {

            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));

        } catch (android.content.ActivityNotFoundException anfe) {

            context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
        }
    }

    public boolean isOnline(@NonNull Context context) {

        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = Objects.requireNonNull(connectivityManager).getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
    }

    public void hideSoftInput(Context context) {

        AppCompatActivity activity = (AppCompatActivity) context;
        activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    public void showSoftInput(Context context, View view) {

        if (view != null) {

            InputMethodManager imputManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

            if (imputManager != null) {

                imputManager.showSoftInputFromInputMethod(view.getWindowToken(), 0);
            }

        } else {

            AppCompatActivity activity = (AppCompatActivity) context;
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    public void hideSoftInput(Context context, View view) {

        if (view != null) {

            InputMethodManager imputManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);

            if (imputManager != null) {

                imputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }

        } else {

            AppCompatActivity activity = (AppCompatActivity) context;
            activity.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        }
    }

    public void startVibration(@NonNull Context context, int delay) {

        Vibrator vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);

        if (Objects.requireNonNull(vibrator).hasVibrator()) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                vibrator.vibrate(VibrationEffect.createOneShot(delay, VibrationEffect.DEFAULT_AMPLITUDE));

            } else {

                vibrator.vibrate(delay);
            }
        }
    }

    public void startVibration(@NonNull Context context, int delay, int effect) {

        Vibrator vibrator = (Vibrator) context.getSystemService(VIBRATOR_SERVICE);

        if (Objects.requireNonNull(vibrator).hasVibrator()) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                vibrator.vibrate(VibrationEffect.createOneShot(delay, effect));

            } else {

                vibrator.vibrate(delay);
            }
        }
    }

    public void startSoundAlarm(Context context, int sound) {

        MediaPlayer mediaPlayer = MediaPlayer.create(context, sound);
        mediaPlayer.start();
    }

    public void lighLedNotify(Context context) {

        Notification.Builder builder = new Notification.Builder(context);
        builder.setLights(Color.BLUE, 200, 300);

        builder.build();
    }
}
