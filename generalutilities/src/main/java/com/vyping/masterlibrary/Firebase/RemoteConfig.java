package com.vyping.masterlibrary.Firebase;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class RemoteConfig {

    public static final String VERSION_RESTRICTION = "General_Version_Restriction", VERSION_CODE = "General_Version_Code";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({VERSION_RESTRICTION, VERSION_CODE})
    public @interface Version {
    }

    public static final String FCM_URLSERVER = "General_Fcm_UrlServer", FCM_KEYSERVER = "General_Fcm_KeyServer";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({FCM_URLSERVER, FCM_KEYSERVER})
    public @interface CloudMessaging {
    }

    public static final String SOCIAL_PAGE = "General_Social_Page", SOCIAL_YOUTUBE = "General_Social_YouTube", SOCIAL_FACEBOOK_ID = "General_Social_FacebookId", SOCIAL_FACEBOOK_URL = "General_Social_FacebookUrl";
    public static final String SOCIAL_INSTAGRAM = "General_Social_Instagram", SOCIAL_TWITTER = "General_Social_Twitter", SOCIAL_WHATSAPP = "General_Social_Whatsapp", SOCIAL_PHONE = "General_Social_Phone", SOCIAL_EMAIL = "General_Social_Email";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({SOCIAL_PAGE, SOCIAL_YOUTUBE, SOCIAL_FACEBOOK_ID, SOCIAL_FACEBOOK_URL, SOCIAL_INSTAGRAM, SOCIAL_TWITTER, SOCIAL_WHATSAPP, SOCIAL_PHONE, SOCIAL_EMAIL})
    public @interface SocialMedia {
    }

    // ----- Setup ----- //

    public void BasicRerquest(Context context, SuccessListener listener) {

        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0).build();

        FirebaseRemoteConfig firebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        firebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        firebaseRemoteConfig.fetchAndActivate().addOnCompleteListener((Activity) context, new OnCompleteListener<>() {

            @Override
            public void onComplete(@NonNull Task<Boolean> task) {

                if (task.isSuccessful()) {

                    listener.Success(firebaseRemoteConfig);
                }
            }

            private void DummyVoid() {
            }
        });
    }

    public void BasicRerquest(Context context, int defaultValues, SuccessListener listener) {

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

    public void UpdateApp(Context context, int defaults, versionListener listener) {

        FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0).build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener((Activity) context, new OnCompleteListener<Boolean>() {

            @Override
            public void onComplete(@NonNull Task<Boolean> task) {

                if (task.isSuccessful()) {

                    mFirebaseRemoteConfig.setDefaultsAsync(defaults);

                    boolean restrictVersion = mFirebaseRemoteConfig.getBoolean(VERSION_RESTRICTION);
                    long appVersion = mFirebaseRemoteConfig.getLong(VERSION_CODE);

                    try {

                        PackageInfo packageInfo = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
                        final int phoneVersion = packageInfo.versionCode;

                        if (restrictVersion && (appVersion > phoneVersion)) {

                            listener.receiveVersionUpgrade(TRUE);

                        } else {

                            listener.receiveVersionUpgrade(FALSE);
                        }

                    } catch (PackageManager.NameNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }

            private void DummyVoid() {
            }
        });
    }

    public void CloudMessaging(Context context, int defaults, MessagingListener listener) {

        FirebaseRemoteConfig mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0).build();
        mFirebaseRemoteConfig.setConfigSettingsAsync(configSettings);

        mFirebaseRemoteConfig.fetchAndActivate().addOnCompleteListener((Activity) context, new OnCompleteListener<Boolean>() {

            @Override
            public void onComplete(@NonNull Task<Boolean> task) {

                if (task.isSuccessful()) {

                    mFirebaseRemoteConfig.setDefaultsAsync(defaults);

                    String exportEmail = mFirebaseRemoteConfig.getString(FCM_URLSERVER);
                    String exportPassword = mFirebaseRemoteConfig.getString(FCM_KEYSERVER);

                    listener.ServerData(exportEmail, exportPassword);
                }
            }

            private void DummyVoid() {
            }
        });
    }

    public void SocialMedia(Context context, int defaults, SocialListener listener) {

        FirebaseRemoteConfig remoteConfig = FirebaseRemoteConfig.getInstance();
        FirebaseRemoteConfigSettings configSettings = new FirebaseRemoteConfigSettings.Builder().setMinimumFetchIntervalInSeconds(0).build();
        remoteConfig.setConfigSettingsAsync(configSettings);

        remoteConfig.fetchAndActivate().addOnCompleteListener((Activity) context, new OnCompleteListener<Boolean>() {

            @Override
            public void onComplete(@NonNull Task<Boolean> task) {

                if (task.isSuccessful()) {

                    remoteConfig.setDefaultsAsync(defaults);

                    String page = remoteConfig.getString(SOCIAL_PAGE);
                    String facebookId = remoteConfig.getString(SOCIAL_FACEBOOK_ID);
                    String facebookUrl = remoteConfig.getString(SOCIAL_FACEBOOK_URL);
                    String instagram = remoteConfig.getString(SOCIAL_INSTAGRAM);
                    String twitter = remoteConfig.getString(SOCIAL_TWITTER);
                    String whatsapp = remoteConfig.getString(SOCIAL_WHATSAPP);
                    String phone = remoteConfig.getString(SOCIAL_PHONE);
                    String email = remoteConfig.getString(SOCIAL_EMAIL);

                    listener.ServerData(page, facebookId, facebookUrl, instagram, twitter, whatsapp, phone, email);
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

    public interface versionListener {

        void receiveVersionUpgrade(boolean upgrade);

        void errorOnReceive();
    }

    public interface MessagingListener {

        void ServerData(String urlServer, String keyServer);

        void errorOnReceive();
    }

    public interface SocialListener {

        void ServerData(String Page, String FacebookId, String FacebookUrl, String Instagram, String Twitter, String Whatsapp, String Phone, String Email);

        void errorOnReceive();
    }
}
