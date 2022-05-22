package com.vyping.masterlibrary.views;

import static android.view.View.VISIBLE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

public class LinearLayouts {


    /**
     * -------- LinearLayout from Activity Section
     */

    public LinearLayout setLinearLayout(@NonNull Context context, int view, int visibility) {

        LinearLayout linearLayout = ((Activity) context).findViewById(view);
        linearLayout.setVisibility(visibility);

        return linearLayout;
    }

    public LinearLayout setLinearLayout(@NonNull Context context, int view, View.OnClickListener listener) {

        LinearLayout linearLayout = ((Activity) context).findViewById(view);
        linearLayout.setOnClickListener(listener);

        return linearLayout;
    }

    public LinearLayout setLinearLayout(@NonNull Context context, int view, View.OnClickListener listener, int visibility) {

        LinearLayout linearLayout = ((Activity) context).findViewById(view);
        linearLayout.setVisibility(visibility);
        linearLayout.setOnClickListener(listener);

        return linearLayout;
    }


    /**
     * -------- LinearLayout from Dialog Section
     */

    public LinearLayout setLinearLayout(@NonNull Dialog dialog, int view, int visibility) {

        LinearLayout linearLayout = dialog.findViewById(view);
        linearLayout.setVisibility(visibility);

        return linearLayout;
    }

    public LinearLayout setLinearLayout(@NonNull Dialog dialog, int view, View.OnClickListener listener) {

        LinearLayout linearLayout = dialog.findViewById(view);
        linearLayout.setOnClickListener(listener);

        return linearLayout;
    }

    public LinearLayout setLinearLayout(@NonNull Dialog dialog, int view, View.OnClickListener listener, int visibility) {

        LinearLayout linearLayout = dialog.findViewById(view);
        linearLayout.setVisibility(visibility);
        linearLayout.setOnClickListener(listener);

        return linearLayout;
    }


    /**
     * -------- LinearLayout from ParentView Section
     */

    public LinearLayout setLinearLayout(@NonNull View parentView, int view) {

        LinearLayout linearLayout = parentView.findViewById(view);
        linearLayout.setVisibility(VISIBLE);

        return linearLayout;
    }

    public LinearLayout setLinearLayout(@NonNull View parentView, int view, int visibility) {

        LinearLayout linearLayout = parentView.findViewById(view);
        linearLayout.setVisibility(visibility);

        return linearLayout;
    }

    public LinearLayout setLinearLayout(@NonNull View parentView, int view, View.OnClickListener listener) {

        LinearLayout linearLayout = parentView.findViewById(view);
        linearLayout.setOnClickListener(listener);

        return linearLayout;
    }

    public LinearLayout setLinearLayout(@NonNull View parentView, int view, View.OnClickListener listener, int visibility) {

        LinearLayout linearLayout = parentView.findViewById(view);
        linearLayout.setVisibility(visibility);
        linearLayout.setOnClickListener(listener);

        return linearLayout;
    }
}
