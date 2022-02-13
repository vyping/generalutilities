package com.vyping.masterlibrary.Views;

import static java.lang.Boolean.FALSE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.IntRange;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Common.Definitions;

public class RecyclerViews {

    private final Context context;
    private selectInterface selectInterface;
    private TouchInterface touchInterface;
    private GestureDetector gestureDetector;
    private RecyclerView.LayoutManager layoutManager;
    private GridLayoutManager gridLayoutManager;
    private final RecyclerView.Adapter adapter;

    private RecyclerView recyclerView;

    // ----- SetUp ----- //


    public RecyclerViews(@NonNull Context context, @NonNull LinearLayout layout, int attr, int style, RecyclerView.Adapter adapter) {

        this.context = context;
        this.adapter = adapter;

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                return true;
            }

            public void DummyVoid() {
            }
        });
        layoutManager = new LinearLayoutManager(context);

        recyclerView = new RecyclerView(context, null, attr);
        recyclerView.setHasFixedSize(FALSE);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(onItemTouchListener);

        Paris.style(recyclerView).apply(style);

        layout.addView(recyclerView);
    }

    public RecyclerViews(@NonNull Dialog dialog, @NonNull LinearLayout layout, int attr, int style, RecyclerView.Adapter adapter) {

        this.context = dialog.getContext();
        this.adapter = adapter;

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                return true;
            }

            public void DummyVoid() {
            }
        });
        layoutManager = new LinearLayoutManager(context);

        recyclerView = new RecyclerView(context, null, attr);
        recyclerView.setHasFixedSize(FALSE);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(onItemTouchListener);

        Paris.style(recyclerView).apply(style);

        layout.addView(recyclerView);
    }

    public RecyclerViews(Context context, int RecyclerView, RecyclerView.Adapter adapter) {

        this.context = context;
        this.adapter = adapter;

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                return true;
            }

            public void DummyVoid() {
            }
        });
        layoutManager = new LinearLayoutManager(context);

        recyclerView = ((Activity) context).findViewById(RecyclerView);
        recyclerView.setHasFixedSize(FALSE);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(onItemTouchListener);
    }

    public RecyclerViews(Context context, int RecyclerView, RecyclerView.Adapter adapter, @IntRange(from=1,to=255) int spanCount, @Definitions.LayoutOrientation int orientation) {

        this.context = context;
        this.adapter = adapter;

        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {

            @Override
            public boolean onSingleTapUp(MotionEvent e) {

                return true;
            }

            public void DummyVoid() {
            }
        });
        gridLayoutManager = new GridLayoutManager(context, spanCount, orientation, false);

        recyclerView = ((Activity) context).findViewById(RecyclerView);
        recyclerView.setHasFixedSize(FALSE);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.setAdapter(adapter);
        recyclerView.addOnItemTouchListener(onItemTouchListener);
    }


    //----- Methods -----//

    public RecyclerView getRecyclerView() {

        return recyclerView;
    }

    public void selectListener(selectInterface selectInterface) {

        this.selectInterface = selectInterface;
    }

    public void setTouchListener(TouchInterface touchInterface) {

        this.touchInterface = touchInterface;
    }

    public void setDragListener(View.OnDragListener onDragListener) {

        recyclerView.setOnDragListener(onDragListener);
    }

    public void setSpanCount(int count) {

        gridLayoutManager.setSpanCount(count);
    }

    public void smoothScroll(int position) {

        recyclerView.smoothScrollToPosition(position);
    }


    //----- Listeners - Section-----//

    private final RecyclerView.OnItemTouchListener onItemTouchListener = new RecyclerView.OnItemTouchListener() {

        @Override
        public boolean onInterceptTouchEvent(@NonNull final RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            if (touchInterface != null) {

                touchInterface.InterceptTouchEvent(motionEvent);
            }

            if (selectInterface != null) {

                try {

                    View selectedView = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                    if (selectedView != null && gestureDetector.onTouchEvent(motionEvent)) {

                        int indexSelected = recyclerView.getChildAdapterPosition(selectedView);
                        RecyclerView.ViewHolder viewHolder = recyclerView.findViewHolderForAdapterPosition(indexSelected);

                        selectInterface.SelectedItem(selectedView, indexSelected, viewHolder);
                    }

                } catch (Exception ignored) {}
            }

            return false;
        }

        @Override
        public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {

            if (touchInterface != null) {

                touchInterface.TouchEvent(motionEvent);
            }
        }

        @Override
        public void onRequestDisallowInterceptTouchEvent(boolean b) {}
    };


    //----- Interface - Section-----//

    public interface selectInterface {

        void SelectedItem(View selectedView, int position, RecyclerView.ViewHolder viewHolder);
    }

    public interface TouchInterface {

        void InterceptTouchEvent(@NonNull MotionEvent motionEvent);
        void TouchEvent(@NonNull MotionEvent motionEvent);
    }
}
