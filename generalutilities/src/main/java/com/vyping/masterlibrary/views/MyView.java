package com.vyping.masterlibrary.views;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.Bucles.MyBucleFor;
import com.vyping.masterlibrary.Common.MyDisplay;
import com.vyping.masterlibrary.views.recyclerview.adapter.BindingRecyclerViewAdapter;

public class MyView {

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
}
