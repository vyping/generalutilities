package com.vyping.masterlibrary.GestureListeners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.Animations.MyAnimation;
import com.vyping.masterlibrary.views.RecyclerViews;

public class MyItemTouchListener implements RecyclerView.OnItemTouchListener {

    private final GestureDetector gestureDetector;
    private SelectInterface selectInterface;
    private TouchInterface touchInterface;


    /*----- SetUp -----*/

    public MyItemTouchListener(Context context, SelectInterface selectInterface) {

        this.selectInterface = selectInterface;

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                return true;
            }

            public void DummyVoid() {
            }
        });
    }

    public MyItemTouchListener(Context context, TouchInterface touchInterface) {

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
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

        if (touchInterface != null) {

            touchInterface.InterceptTouchEvent(motionEvent);
        }

        if (selectInterface != null) {

            try {

                View selectedView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if (selectedView != null && gestureDetector.onTouchEvent(motionEvent)) {

                    int indexSelected = recyclerView.getChildAdapterPosition(selectedView);
                    RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(indexSelected);

                    setAnimation(selectedView, motionEvent);

                    selectInterface.SelectedItem(selectedView, indexSelected, viewHolder);
                }

            } catch (Exception ignored) {}
        }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent motionEvent) {

        if (touchInterface != null) {

            touchInterface.TouchEvent(motionEvent);
        }
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}


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

    public interface SelectInterface {

        void SelectedItem(View selectedView, int position, RecyclerView.ViewHolder viewHolder);
    }

    public interface TouchInterface {

        void InterceptTouchEvent(@NonNull MotionEvent motionEvent);

        void TouchEvent(@NonNull MotionEvent motionEvent);
    }
}