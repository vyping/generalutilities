package com.vyping.masterlibrary.GestureListeners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.Animations.MyAnimation;

public class MyTouchListener implements View.OnTouchListener {

    private final GestureDetector gestureDetector;
    private final TouchInterface touchInterface;


    /*----- SetUp -----*/

    public MyTouchListener(Context context, TouchInterface touchInterface) {

        this.touchInterface = touchInterface;

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                return true;
            }

            public void DummyVoid() {
            }
        });
    }


    /*----- Methods -----*/


    @Override
    public boolean onTouch(View selectedView, @NonNull MotionEvent motionEvent) {

        if (gestureDetector.onTouchEvent(motionEvent)) {

            selectedView.getRootView().getVerticalScrollbarPosition()
            setAnimation(selectedView, motionEvent);

            touchInterface.TouchEvent();
        }

        return true;
    }


    //----- Tools - Section-----//

    private void setAnimation(View selectedView, @NonNull MotionEvent motionEvent) {

        int action = motionEvent.getActionMasked();

        if (action == MotionEvent.ACTION_DOWN) {

            new MyAnimation().ScaleAmpliate(selectedView, 100, 0.95f, 1.05f, 0.95f, 1.05f);

        } else if (action == MotionEvent.ACTION_OUTSIDE) {

            new MyAnimation().ScaleAmpliate(selectedView, 100, 1.05f, 1.0f, 1.05f, 1.0f);

        } else if (action == MotionEvent.ACTION_CANCEL) {

            new MyAnimation().ScaleAmpliate(selectedView, 100, 1.05f, 1.0f, 1.05f, 1.0f);

        } else if (action == MotionEvent.ACTION_UP) {

            new MyAnimation().ScaleAmpliate(selectedView, 100, 1.05f, 1.0f, 1.05f, 1.0f);
        }
    }


    //----- Interface - Section-----//

    public interface TouchInterface {

        void TouchEvent();
    }
}