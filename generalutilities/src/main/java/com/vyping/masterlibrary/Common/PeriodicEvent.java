package com.vyping.masterlibrary.Common;

import android.app.Activity;
import android.content.Context;

import java.util.Timer;
import java.util.TimerTask;

public abstract class PeriodicEvent {

    private Timer myTimer;


    /**
     *-------- SetUp Section --------
     */

    public PeriodicEvent(Context context, int delay, int period) {

        myTimer = new Timer();
        myTimer.scheduleAtFixedRate(new TimerTask() {

            @Override
            public void run() {

                Activity activity = (Activity) context;
                activity.runOnUiThread(() ->  {

                    boolean active = startEvent();

                    stopEvent(active);
                });
            }

        }, delay, period);
    }


    /**
     * -------- Aditional Tools - Section
     */

    public void stopEvent(boolean active) {

        if (!active) {

            if (myTimer != null) {

                myTimer.cancel();
                myTimer.purge();
                myTimer = null;
            }
        }
    }


    /**
     * -------- Return - Section
     */

    protected abstract boolean startEvent();
}
