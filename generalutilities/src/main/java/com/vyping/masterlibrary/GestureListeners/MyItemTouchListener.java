package com.vyping.masterlibrary.GestureListeners;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.Animations.MyAnimation;
import com.vyping.masterlibrary.views.MyView;
import com.vyping.masterlibrary.views.RecyclerViews;

public class MyItemTouchListener implements RecyclerView.OnItemTouchListener {

    private final GestureDetector gestureDetector;
    private final Interfase interfase;

    private MotionEvent prevEvent;


    /*----- SetUp -----*/

    public MyItemTouchListener(Context context, Interfase interfase) {

        this.interfase = interfase;

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                return true;
            }

            public void DummyVoid() {}
        });
    }


    /*----- ModelMethods -----*/

    @Override
    public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

        if (interfase != null) {

            interfase.InterceptTouchEvent(motionEvent);

            try {

                View selectedView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                prevEvent = new MyView().setTouchAnimation(selectedView, motionEvent, prevEvent);

                if (selectedView != null && gestureDetector.onTouchEvent(motionEvent)) {

                    int indexSelected = recyclerView.getChildAdapterPosition(selectedView);
                    RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(indexSelected);

                    interfase.SelectedItem(selectedView, indexSelected, viewHolder);
                }

            } catch (Exception ignored) {}
        }

        return false;
    }

    @Override
    public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

        if (interfase != null) {

            interfase.InterceptTouchEvent(motionEvent);

            interfase.TouchEvent(motionEvent);
        }
    }

    @Override
    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {}


    //----- Interface - Section-----//

    public interface Interfase {

        default void InterceptTouchEvent(@NonNull MotionEvent motionEvent) {};
        default void TouchEvent(@NonNull MotionEvent motionEvent) {};
        default void SelectedItem(View selectedView, int position, RecyclerView.ViewHolder viewHolder) {};
    }
}