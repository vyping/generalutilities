package com.vyping.masterlibrary.Common;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;

import com.vyping.masterlibrary.Services.ServiceCloudMessaging;

public class MyServices {

    public void startService(Context context, Class backgroundService) {

        if (!isRouterServiceProgramRunning(context, backgroundService)) {

            context.startService(new Intent(context, backgroundService));
        }
    }

    public void closeServices(final Context context) {

        if (isRouterServiceProgramRunning(context, ServiceCloudMessaging.class)) {

            context.stopService(new Intent(context, ServiceCloudMessaging.class));
        }
    }

    private boolean isRouterServiceProgramRunning(Context context, Class backgroundService) {

        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);

        if (manager != null) {

            for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {

                if (backgroundService.getName().equals(service.service.getClassName())) {

                    return true;
                }
            }
        }

        return false;
    }
}
