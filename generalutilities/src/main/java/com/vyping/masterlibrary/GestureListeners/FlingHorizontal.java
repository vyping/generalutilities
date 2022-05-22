package com.vyping.masterlibrary.GestureListeners;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class FlingHorizontal extends GestureDetector.SimpleOnGestureListener {

    private final Interface Interface;


    /*----- SetUp -----*/

    public FlingHorizontal(Interface Interface) {

        this.Interface = Interface;
    }


    /*----- Methods -----*/

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

        float xVel = Math.abs(velocityX), yVel = Math.abs(velocityY);
        int minVel = 2000, offPath = 2000;

        if (xVel > minVel && yVel < offPath) {

            Interface.FlingSuccess();
        }
        return true;
    }


    /*----- Interfaces -----*/

    public interface Interface {

        void FlingSuccess();
    }
}