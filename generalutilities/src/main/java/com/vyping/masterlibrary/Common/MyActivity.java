package com.vyping.masterlibrary.Common;

import static android.content.Context.ACTIVITY_SERVICE;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.datatransport.backend.cct.BuildConfig;
import com.vyping.masterlibrary.Preferences.MyConfigPreferences;
import com.vyping.masterlibrary.R;

import java.util.List;

public class MyActivity {


    public Context setTheme(Context context) {

        int theme = new MyConfigPreferences().getAppTheme(context);
        context.setTheme(theme);

        return context;
    }

    public void Start(Context context, Class activity, boolean finish) {

        Intent intent = new MyIntent().Basic(context, activity);
        context.startActivity(intent);

        ((Activity) context).overridePendingTransition(R.anim.blink_in, R.anim.blink_out);

        if (finish) {

            ((AppCompatActivity) context).finish();
        }
    }

    public void Restart(Context context) {

        Activity activity = ((AppCompatActivity) context);
        activity.overridePendingTransition(0, 0);
        activity.finish();

        Intent intent = new MyIntent().WithFlags(context, context.getClass(), Intent.FLAG_ACTIVITY_NO_ANIMATION);
        activity.startActivity(intent);
    }

    public void RestartAndApplyChanges(Context context, Class classActivity) {

        TaskStackBuilder.create((Activity) context)
                .addNextIntent(new Intent((Activity) context, classActivity))
                .addNextIntent(((Activity) context).getIntent())
                .startActivities();
    }

    public void StartFromService(Context context, Class activity) {

        boolean isForeground = new MyActivity().IsForeground(context);

        if (!isForeground) {

            int[] flags = {Intent.FLAG_ACTIVITY_NEW_TASK, Intent.FLAG_ACTIVITY_REORDER_TO_FRONT};

            Intent intent = new MyIntent().WithFlags(context, activity, flags);
            context.startActivity(intent);
        }
    }

    public void StartWithBundles(Context context, Class activity, String extraKey, String extraValue, boolean finish) {

        Bundle bundle = new Bundle();
        bundle.putString(extraKey, extraValue);

        Intent intent = new MyIntent().WithBundles(context, activity, bundle);
        context.startActivity(intent);

        if (finish) {

            ((AppCompatActivity) context).finish();
        }
    }

    public String SearchStringBundles(Context context, String extraKey) {

        Intent intent = ((Activity) context).getIntent();

        String Return = intent.getStringExtra(extraKey);

        if (Return != null) {

            return Return;

        } else {

            return "";
        }
    }

    public boolean IsForeground(@NonNull Context context) {

        ActivityManager activityManager = (ActivityManager) context.getSystemService(ACTIVITY_SERVICE);

        if (activityManager != null) {

            List<ActivityManager.RunningAppProcessInfo> processInfos = activityManager.getRunningAppProcesses();

            for (ActivityManager.RunningAppProcessInfo info : processInfos) {

                if (BuildConfig.APPLICATION_ID.equalsIgnoreCase(info.processName) && ActivityManager.RunningAppProcessInfo.IMPORTANCE_FOREGROUND == info.importance) {

                    return true;
                }
            }
        }

        return false;
    }
}
