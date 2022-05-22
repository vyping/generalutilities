package com.vyping.masterlibrary.Common;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Bucles.MyBucleFor;

public class MyIntent {

    @NonNull
    public Intent Basic(Context context, Class activity) {

        return new Intent(context, activity);
    }

    @NonNull
    public Intent WithFlags(Context context, Class activity, int flags) {

        Intent intent = new Intent(context, activity);
        intent.addFlags(flags);

        return intent;
    }

    @NonNull
    public Intent WithFlags(Context context, Class activity, int[] flags) {

        Intent intent = new Intent(context, activity);

        new MyBucleFor().integerList(flags, new MyBucleFor.IntInterface() {

            @Override
            public void intPosition(int flag) {

                intent.addFlags(flag);
            }

            private void DummyVoid() {
            }

            ;
        });

        return intent;
    }

    @NonNull
    public Intent WithBundles(Context context, Class activity, Bundle bundle) {

        Intent intent = new Intent(context, activity);
        intent.putExtras(bundle);

        return intent;
    }

    public PendingIntent Pending(Context context) {

        return PendingIntent.getActivity(context, 0, new Intent(context, context.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
    }
}
