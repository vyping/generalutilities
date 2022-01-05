package com.vyping.masterlibrary.Firebase;

import android.app.Activity;
import android.content.Context;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

public class RemoteConfig {

    // ----- Setup ----- //

    public RemoteConfig(Context context, int defaultValues, SuccessListener listener) {

        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0).build();

        FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener((Activity) context, new OnCompleteListener<>() {

            @Override
            public void onComplete(@NonNull Task<Boolean> task) {

                if (task.isSuccessful()) {

                    firebaseRemoteConfig.setDefaultsAsync(defaultValues);

                    listener.Success(firebaseRemoteConfig);
                }
            }

            private void DummyVoid() {
            }
        });
    }


    // ----- Interface ----- //

    public interface SuccessListener {

        void Success(FirebaseRemoteConfig firebaseRemoteConfig);
    }
}
