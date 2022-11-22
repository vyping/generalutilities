package com.vyping.masterlibrary.views;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.lang.Math.abs;

import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.Animations.MyAnimation;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.MyDisplay;
import com.vyping.masterlibrary.Common.MyGeneralTools;
import com.vyping.masterlibrary.adapters.recyclerview.adapter.MyRecyclerAdapter;
import com.vyping.masterlibrary.time.MyDelay;

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

    public View getViewFromHolder(@NonNull MyRecyclerAdapter.ViewHolder holder, int resource) {

        return holder.itemView.findViewById(resource);
    }

    public View getViewFromHolder(@NonNull RecyclerView.ViewHolder holder, int resource) {

        return holder.itemView.findViewById(resource);
    }

    public View getViewFromParent(@NonNull ViewParent viewParent, int resource) {

        return ((View) viewParent).findViewById(resource);
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

        if (hasPerent) {

            while (recyclerView == null && hasPerent == TRUE) {

                if (view instanceof RecyclerView) {

                    recyclerView = (RecyclerView) view;

                } else {

                    if (view.getParent() != null) {

                        view = (View) view.getParent();

                    } else {

                        hasPerent = FALSE;
                    }
                }
            }

        } else {

            if (view instanceof RecyclerView) {

                recyclerView = (RecyclerView) view;
            }
        }

        return recyclerView;
    }

    public MyRecyclerAdapter getAdapter(@NonNull RecyclerView recyclerView) {

        return (MyRecyclerAdapter) recyclerView.getAdapter();
    }

    public MyRecyclerAdapter.ViewHolder getHolderFromViewChild(@NonNull RecyclerView recyclerView, View view) {

        MyRecyclerAdapter.ViewHolder viewHolder = null;
        boolean hasPerent = TRUE;

        while (viewHolder == null && hasPerent == TRUE) {

            try  {

                viewHolder = (MyRecyclerAdapter.ViewHolder) recyclerView.getChildViewHolder(view);

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

    public MyRecyclerAdapter.ViewHolder getHolderFromViewChild(View view) {

        RecyclerView recyclerView = getRecyclerView(view);
        MyRecyclerAdapter.ViewHolder viewHolder = null;
        boolean hasPerent = TRUE;

        while (viewHolder == null && hasPerent == TRUE) {

            try  {

                viewHolder = (MyRecyclerAdapter.ViewHolder) recyclerView.getChildViewHolder(view);

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

    public View getViewFromViewChild(View view, int resourceView) {

        RecyclerView recyclerView = getRecyclerView(view);
        MyRecyclerAdapter.ViewHolder viewHolder = null;
        boolean hasPerent = TRUE;

        while (viewHolder == null && hasPerent == TRUE) {

            try  {

                viewHolder = (MyRecyclerAdapter.ViewHolder) recyclerView.getChildViewHolder(view);

            } catch (Exception e) {

                if(view.getParent() != null) {

                    view = (View) view.getParent();

                } else {

                    hasPerent = FALSE;
                }
            }
        }

        if (viewHolder != null) {

            return viewHolder.itemView.findViewById(resourceView);

        } else {

            return null;
        }
    }

    public RecyclerView.ViewHolder desplegateHolder(RecyclerView.ViewHolder prevViewHolder, RecyclerView.ViewHolder viewHolder, int layout) {

        if (prevViewHolder == null) {

            View view = viewHolder.itemView.findViewById(layout);
            view.setVisibility(VISIBLE);

        } else {

            if (prevViewHolder == viewHolder) {

                View view = viewHolder.itemView.findViewById(layout);

                int visibility = view.getVisibility();

                if (visibility == GONE) {

                    view.setVisibility(VISIBLE);

                } else {

                    view.setVisibility(GONE);
                }

            } else {

                View prevView = prevViewHolder.itemView.findViewById(layout);
                prevView.setVisibility(GONE);

                View view = viewHolder.itemView.findViewById(layout);
                view.setVisibility(VISIBLE);
            }
        }

        return viewHolder;
    }

    public int extractFromText(Context context, String idString) {

        return context.getResources().getIdentifier(idString, "id", context.getPackageName());
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

    public void setClickAnimation(View selectedView) {

         new MyAnimation().ScaleAmpliate(selectedView, ANIMATION_DURATION, ANIMATION_SIZE_ORIGINAL, ANIMATION_SIZE_ANIMATED, ANIMATION_SIZE_ORIGINAL, ANIMATION_SIZE_ANIMATED);

         new MyDelay(50).interfase(new MyDelay.Interfase() {

             @Override
             public void Execute() {

                 new MyAnimation().ScaleAmpliate(selectedView, ANIMATION_DURATION, ANIMATION_SIZE_ANIMATED, ANIMATION_SIZE_ORIGINAL, ANIMATION_SIZE_ANIMATED, ANIMATION_SIZE_ORIGINAL);
             }
         });
    }

    public void dragView(@NonNull View view) {

        new MyGeneralTools().startVibration(view.getContext(), 100);

        ClipData data = ClipData.newPlainText("", "");
        View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

        view.setVisibility(INVISIBLE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

            view.startDragAndDrop(data, shadowBuilder, view, 0);

        } else {

            view.startDrag(data, shadowBuilder, view, 0);
        }
    }

    public int getViewHeight(@NonNull View view) {

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        return view.getMeasuredHeight();
    }

    public int getViewHeight(RecyclerView.ViewHolder holder, int resource) {

        View view = getViewFromHolder(holder, resource);

        if (view != null) {

            view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

            return view.getMeasuredHeight();

        } else {

            return 0;
        }
    }

    public int getViewWidth(@NonNull View view) {

        view.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        return view.getMeasuredWidth();
    }
}
