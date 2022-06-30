package com.vyping.masterlibrary.views;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Math.abs;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.Animations.MyAnimation;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.MyDisplay;
import com.vyping.masterlibrary.views.recyclerview.adapter.BindingRecyclerViewAdapter;

public class MyView {

    private static final int ANIMATION_DURATION = 100;
    private static final float ANIMATION_SIZE_ORIGINAL = 1.0f;
    private static final float ANIMATION_SIZE_ANIMATED = 1.05f;


    public void setDimentionsFromPx(@NonNull View view, int pxWidth, int pxHeight) {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(pxWidth, pxHeight);
        view.setLayoutParams(layoutParams);
    }

    public void setDimentionsFromDp(@NonNull View view, float dpWidth, float dpHeight) {

        int pxWidth = (int) new MyDisplay().dpsToPxs(view.getContext(), dpWidth);
        int pxHeight = (int) new MyDisplay().dpsToPxs(view.getContext(), dpHeight);

        setDimentionsFromPx(view, pxWidth, pxHeight);
    }

    public String getName(@NonNull View view) {

        Context context = view.getContext();

        return context.getResources().getResourceEntryName(view.getId());
    }

    public View getViewFromHolder(@NonNull BindingRecyclerViewAdapter.ViewHolder holder, int resource) {

        return holder.itemView.findViewById(resource);
    }

    public View getViewFromHolder(@NonNull RecyclerView.ViewHolder holder, int resource) {

        return holder.itemView.findViewById(resource);
    }

    public View getViewFromParent(@NonNull View viewPerent, int resource, int parents) {

        View viewHolder = viewPerent;

        for (int i = 1; i < parents; ++i) {

            viewHolder = (View) viewHolder.getParent();
        }

        return viewHolder.findViewById(resource);
    }

    public int getPosition(@NonNull RecyclerView recyclerView, View view) {

        return recyclerView.getChildAdapterPosition(view);
    }

    public RecyclerView.ViewHolder getHolder(@NonNull RecyclerView recyclerView, View view) {

        int position = getPosition(recyclerView, view);

        return getHolder(recyclerView, position);
    }

    public RecyclerView.ViewHolder getHolder(@NonNull RecyclerView recyclerView, int position) {

        return recyclerView.findViewHolderForAdapterPosition(position);
    }

    public static RecyclerView getRecyclerView(@NonNull View view) {

        RecyclerView recyclerView = null;
        boolean hasPerent = TRUE;

        while (recyclerView == null && hasPerent == TRUE) {

            if (view instanceof RecyclerView) {

                recyclerView = (RecyclerView) view;

            } else {

                if(view.getParent() != null) {

                    view = (View) view.getParent();

                } else {

                    hasPerent = FALSE;
                }
            }
        }

        return recyclerView;
    }

    public BindingRecyclerViewAdapter getAdapter(@NonNull RecyclerView recyclerView) {

        return (BindingRecyclerViewAdapter) recyclerView.getAdapter();
    }

    public BindingRecyclerViewAdapter.ViewHolder getHolderFromViewChild(@NonNull RecyclerView recyclerView, View view) {

        BindingRecyclerViewAdapter.ViewHolder viewHolder = null;
        boolean hasPerent = TRUE;

        while (viewHolder == null && hasPerent == TRUE) {

            try  {

                viewHolder = (BindingRecyclerViewAdapter.ViewHolder) recyclerView.getChildViewHolder(view);

            } catch (Exception e) {

                if(view.getParent() != null) {

                    view = (View) view.getParent();

                } else {

                    hasPerent = FALSE;
                }
            }
        }

        return viewHolder;
    }





    public MotionEvent setTouchAnimation(View selectedView, @NonNull MotionEvent event, @NonNull MotionEvent prevEvent) {

        int action = event.getActionMasked();

        if (action == MotionEvent.ACTION_DOWN) {

            new MyAnimation().ScaleAmpliate(selectedView, ANIMATION_DURATION, ANIMATION_SIZE_ORIGINAL, ANIMATION_SIZE_ANIMATED, ANIMATION_SIZE_ORIGINAL, ANIMATION_SIZE_ANIMATED);

        } else if (action == MotionEvent.ACTION_OUTSIDE) {

            new MyAnimation().ScaleAmpliate(selectedView, ANIMATION_DURATION, ANIMATION_SIZE_ANIMATED, ANIMATION_SIZE_ORIGINAL, ANIMATION_SIZE_ANIMATED, ANIMATION_SIZE_ORIGINAL);

        } else if (action == MotionEvent.ACTION_MOVE) {

            float prevPosX = prevEvent.getRawX();
            float prevPosY = prevEvent.getRawY();

            if (prevEvent.getActionMasked() != MotionEvent.ACTION_MOVE) {

                prevPosX = event.getRawX();
                prevPosY = event.getRawY();
            }

            if ((abs(prevPosX - event.getRawX()) > 200) && (abs(prevPosY - event.getRawY()) > 200))  {

                new MyAnimation().ScaleAmpliate(selectedView, ANIMATION_DURATION, ANIMATION_SIZE_ANIMATED, ANIMATION_SIZE_ORIGINAL, ANIMATION_SIZE_ANIMATED, ANIMATION_SIZE_ORIGINAL);
            }

        } else if (action == MotionEvent.ACTION_CANCEL) {

            new MyAnimation().ScaleAmpliate(selectedView, ANIMATION_DURATION, ANIMATION_SIZE_ANIMATED, ANIMATION_SIZE_ORIGINAL, ANIMATION_SIZE_ANIMATED, ANIMATION_SIZE_ORIGINAL);

        } else if (action == MotionEvent.ACTION_UP) {

            new MyAnimation().ScaleAmpliate(selectedView, ANIMATION_DURATION, ANIMATION_SIZE_ANIMATED, ANIMATION_SIZE_ORIGINAL, ANIMATION_SIZE_ANIMATED, ANIMATION_SIZE_ORIGINAL);
        }

        return event;
    }
}
