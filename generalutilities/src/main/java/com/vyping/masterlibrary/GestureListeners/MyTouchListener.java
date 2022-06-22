package com.vyping.masterlibrary.GestureListeners;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.Animations.MyAnimation;
import com.vyping.masterlibrary.views.MyView;

public class MyTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    private final TouchInterface touchInterface;

    private MotionEvent prevEvent;


    /*----- SetUp -----*/

    public MyTouchListener(Context context, TouchInterface touchInterface) {

        this.touchInterface = touchInterface;

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                return true;
            }

            public void DummyVoid() {}
        });
    }


    /*----- ModelMethods -----*/

    @Override @SuppressLint("ClickableViewAccessibility")
    public boolean onTouch(View selectedView, @NonNull MotionEvent motionEvent) {

        prevEvent = new MyView().setTouchAnimation(selectedView, motionEvent, prevEvent);

        if (gestureDetector.onTouchEvent(motionEvent)) {

            int view = selectedView.getRootView().getVerticalScrollbarPosition();

            touchInterface.TouchEvent(selectedView);
        }

        return true;
    }


    //----- Interface - Section-----//

    public interface TouchInterface {

        void TouchEvent(View selectedView);
    }
}