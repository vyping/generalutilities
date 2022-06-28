package com.vyping.masterlibrary.aplication;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MyApplication extends MultiDexApplication {

    public static MyApplication myApplication;
    private static Context context;

    private static List<Activity> activitys;

    private static String LOG;


   // ----- Setup -----//

    @Override
    public void onCreate() {

        super.onCreate();

        myApplication = this;
        context = getApplicationContext();
        activitys = Collections.synchronizedList(new LinkedList());

        MultiDex.install(this);
        initActivityLifeCallback();
    }

    @Override
    protected void attachBaseContext(Context base) {

        super.attachBaseContext(base);

        MultiDex.install(context);
    }


    // ----- ModelMethods -----//

    public static Context getContext() {

        return context;
    }


    // ----- ModelMethods -----//

    public void addActivity(Activity activity) {

        if (!activitys.contains(activity)) {

            activitys.add(activity);
        }
    }

    public static void removeActivity(Activity activity) {

        if (activitys.contains(activity)) {

            activitys.remove(activity);
            activity.finish();
        }
    }

    public static void removeAllActivity() {

        for (Activity activity : activitys) {

            activity.finish();
        }

        activitys.clear();
    }

    public void startLog(String string) {

        LOG = string;

        Log.i("Desarrollo",  LOG);
    }

    public void startLog(String tag, String value) {

        LOG = tag + ": " + value;

        Log.i("Desarrollo",  LOG);
    }

    public void addLog(String string) {

        LOG = LOG + ", " + string;

        Log.i("Desarrollo",  LOG);
    }

    public void addLog(String tag, String value) {

        LOG = LOG + ", " + tag + ": " + value;

        Log.i("Desarrollo",  LOG);
    }

    private void initActivityLifeCallback() {

        registerActivityLifecycleCallbacks(new LifecycleCallback());
    }


    // ----- Callbacks -----//

    public static class LifecycleCallback implements Application.ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(@NonNull Activity activity, Bundle bundle) {

            String name  = activity.getClass().getSimpleName();
        }

        @Override
        public void onActivityStarted(@NonNull final Activity activity) {

            String name  = activity.getClass().getSimpleName();
        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

            String name  = activity.getClass().getSimpleName();
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {}

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

            String name  = activity.getClass().getSimpleName();
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

            String name  = activity.getClass().getSimpleName();
        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

            String name  = activity.getClass().getSimpleName();
        }
    }


    // ----- Inherents -----//

    @Override
    public void onTerminate() {

        super.onTerminate();
    }
}