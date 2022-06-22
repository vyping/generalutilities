package com.vyping.masterlibrary.GestureListeners;

import static android.view.DragEvent.ACTION_DRAG_ENDED;
import static android.view.DragEvent.ACTION_DRAG_ENTERED;
import static android.view.DragEvent.ACTION_DRAG_STARTED;
import static android.view.DragEvent.ACTION_DROP;
import static java.lang.Math.abs;

import android.annotation.SuppressLint;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Animations.MyAnimation;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.views.MyView;

public class MyGesturesListener implements View.OnTouchListener, View.OnDragListener {

    private final View view;
    private final GestureDetector gestureDetector;
    private final Interfase interfase;

    private MotionEvent prevEvent;


    /*----- SetUp -----*/

    public MyGesturesListener(@NonNull View view, Interfase interfase) {

        this.view = view;
        this.interfase = interfase;
        this.gestureDetector = new GestureDetector(view.getContext(), new MyGestureDetector());
        this.view.setOnTouchListener(this);
    }


    //----- ModelMethods -----//

    @Override @SuppressLint("ClickableViewAccessibility")
    public boolean onTouch(View view, @NonNull MotionEvent event) {

        prevEvent = new MyView().setTouchAnimation(view, event, prevEvent);

        return gestureDetector.onTouchEvent(event);
    }

    @Override
    public boolean onDrag(View view, @NonNull DragEvent event) {

        switch (event.getAction()) {

            case ACTION_DRAG_STARTED: {

                interfase.OnDragStart(view);
                break;

            } case ACTION_DRAG_ENTERED: {

                interfase.OnDragEntered(view);
                break;

            } case ACTION_DROP: {

                interfase.OnDragDrop(view);
                break;

            } case ACTION_DRAG_ENDED: {

                View draggedView = (View) event.getLocalState();

                interfase.OnDragEnded(view, draggedView);
                break;

            } default: { break; }
        }

        return true;
    }


    //----- SubClass -----//

    private class MyGestureDetector extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {

            if (interfase != null) {

               interfase.OnDown(view);
            }
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {

            if (interfase != null) {

                interfase.OnClick(view);
                interfase.OnUp(view);
            }

            return true;
        }

        @Override
        public void onLongPress(MotionEvent event) {

            if (interfase != null) {

                interfase.OnLongClick(view);
                interfase.OnUp(view);
            }
        }

        @Override
        public boolean onDoubleTap(MotionEvent event) {

            if (interfase != null) {

                interfase.OnDoubleClick(view);
                interfase.OnUp(view);
            }

            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

            float xVel = abs(velocityX), yVel = abs(velocityY);
            int minVel = 2000, offPath = 2000;

            if (xVel > minVel && yVel < offPath) {

                if (interfase != null) {

                    interfase.OnFlingHorizontal(view);
                    interfase.OnUp(view);
                }

            } else if (yVel > minVel && xVel < offPath) {

                if (interfase != null) {

                    interfase.OnFlingVertical(view);
                    interfase.OnUp(view);
                }
            }

            return true;
        }
    }


    //----- Interface -----//

    public interface Interfase {

        default void OnDown(@NonNull View view) {};
        default void OnClick(@NonNull View view) {};
        default void OnLongClick(@NonNull View view) {};
        default void OnDoubleClick(@NonNull View view) {};
        default void OnDragStart(@NonNull View view) {};
        default void OnDragEntered(@NonNull View view) {};
        default void OnDragDrop(@NonNull View view) {};
        default void OnDragEnded(@NonNull View view, @NonNull View draggedView) {};
        default void OnFlingHorizontal(@NonNull View view) {};
        default void OnFlingVertical(@NonNull View view) {};
        default void OnUp(@NonNull View view) {};
    }
}