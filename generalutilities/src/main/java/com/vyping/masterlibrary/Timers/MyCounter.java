package com.vyping.masterlibrary.Timers;

import android.os.CountDownTimer;

public class MyCounter {

    private CountDownTimer CountDownTimer;
    private Interfase interfase;

    private long period, intervale;


    // ----- SetUp ----- //

    public MyCounter(long period, long intervale, boolean start, Interfase interfase) {

        this.period = period;
        this.intervale = intervale;
        this.interfase = interfase;

        if (start) {

            startCounter();
        }
    }

    public void startCounter(long period, long intervale, boolean start, Interfase interfase) {

        CountDownTimer = new CountDownTimer(period, intervale) {

            public void onTick(long remain) {

                interfase.onTick(remain);
            }

            public void onFinish() {

                interfase.onFinish();
            }

        }.start();
    }


    // ----- Methods ----- //

    public void startCounter() {

        CountDownTimer = new CountDownTimer(period, intervale) {

            public void onTick(long remain) {

                interfase.onTick(remain);
            }

            public void onFinish() {

                interfase.onFinish();
            }

        }.start();
    }

    public void cancelCounter() {

        if (CountDownTimer != null) {

            CountDownTimer.cancel();
            CountDownTimer = null;
        }
    }


    // ----- Interface ----- //

    public interface Interfase {

        void onTick(long remain);

        void onFinish();
    }
}
