package com.vyping.masterlibrary.GestureListeners;

import android.view.GestureDetector;
import android.view.MotionEvent;

public class FlingVertical extends GestureDetector.SimpleOnGestureListener {

    private final Interface Interface;


    /*----- SetUp -----*/

    public FlingVertical(Interface Interface) {

        this.Interface = Interface;
    }


    /*----- Methods -----*/

    @Override
    public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

        float xVel = Math.abs(velocityX), yVel = Math.abs(velocityY);
        int minVel = 2000, offPath = 2000;

        if (yVel > minVel && xVel < offPath) {

            Interface.FlingSuccess();
        }
        return true;
    }


    /*----- Interfaces -----*/

    public interface Interface {

        void FlingSuccess();
    }
}