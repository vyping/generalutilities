package com.vyping.masterlibrary.Timers;

import android.os.Handler;

public class MyDelay {

    private final Handler handler;
    private final int delay;
    private Interfase interfase;

    // ----- SetUp ----- //

    public MyDelay(int delay) {

        this.handler = new Handler();
        this.delay = delay;
    }

    // ----- Interface ----- //

    public void interfase(Interfase interfase) {

        this.interfase = interfase;

        execute();
    }

    public void execute() {

        handler.postDelayed(new Runnable() {

            @Override
            public void run() {

                interfase.Execute();
            }

            private void DummyVoid() {}

        }, delay);
    }

    public void restart() {

        execute();
    }


    // ----- Interface ----- //

    public interface Interfase {

        void Execute();
    }
}
