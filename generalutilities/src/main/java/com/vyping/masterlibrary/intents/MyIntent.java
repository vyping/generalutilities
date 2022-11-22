package com.vyping.masterlibrary.intents;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;

import androidx.annotation.IntDef;

import com.vyping.masterlibrary.Bucles.MyBucleFor;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MyIntent {

    public static final String ACTION_PICK_ON_DATA = Intent.ACTION_PICK; // <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"/>
    public static final String ACTION_CAPTURE_IMAGE = MediaStore.ACTION_IMAGE_CAPTURE;//     <uses-permission android:name="android.permission.CAMERA" />

    public static final Uri URI_DATA_IMAGES = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

    public static final int REQUEST_CODE_GALLERY = 21000;
    public static final int REQUEST_CODE_CAPTURE = 22000;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({REQUEST_CODE_GALLERY, REQUEST_CODE_CAPTURE})
    public @interface RequestCode {}

    private final Context context;
    private Intent intent;
    private Interfase interfase;

    private String action;
    private int requestCode;


    // ----- SetUp ----- //

    public MyIntent(Context context) {

        this.context = context;
    }


    // ----- Methods ----- //

    public MyIntent action(String action) {

        this.action = action;
        this.intent = new Intent(action);

        return this;
    }

    public MyIntent actionAndUri(String action, Uri uri) {

        this.action = action;
        this.intent = new Intent(action, uri);

        return this;
    }

    public MyIntent activity(Class activity) {

        this.intent = new Intent(context, activity);
        ((Activity) context).startActivity(intent);

        return this;
    }

    @SafeVarargs
    public final MyIntent activity(Class activity, int... flags) {

        this.intent = new Intent(context, activity);

        new MyBucleFor().integerList(flags, new MyBucleFor.IntInterface() {

            @Override
            public void intPosition(int flag) {

                intent.addFlags(flag);
            }

            private void DummyVoid() {};
        });

        ((Activity) context).startActivity(intent);

        return this;
    }

    public final MyIntent activity(Class activity, Bundle bundle) {

        this.intent = new Intent(context, activity);
        this.intent.putExtras(bundle);
        ((Activity) context).startActivity(intent);

        return this;
    }

    public void start(Interfase interfase) {

        this.interfase = interfase;
        this.requestCode = getRequestCode();

        ((Activity) context).startActivityForResult(intent, requestCode);
    }

    public void activityResult(@RequestCode int requestCode, int resultCode, Intent data) {

       if (this.requestCode == requestCode) {

           interfase.ActivityResult(data);
       }
    }


    // ----- Tools ----- //

    private int getRequestCode() {

        switch (action) {

            case ACTION_PICK_ON_DATA:

                return REQUEST_CODE_GALLERY;

            case ACTION_CAPTURE_IMAGE:

                return REQUEST_CODE_CAPTURE;

            default:

                return 0;
        }
    }

    // ----- Interface ----- //

    public interface Interfase {

        void ActivityResult(Intent data);
    }


    public PendingIntent Pending(Context context) {

        return PendingIntent.getActivity(context, 0, new Intent(context, context.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }
}
