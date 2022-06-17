package com.vyping.masterlibrary.views;

import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Common.MyDisplay;

public class MyViews {

    private void setDimentionsFromPx(@NonNull View view, int pxWidth, int pxHeight) {

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(pxWidth, pxHeight);
        view.setLayoutParams(layoutParams);
    }

    private void setDimentionsFromDp(@NonNull View view, float dpWidth, float dpHeight) {

        int pxWidth = (int) new MyDisplay().dpsToPxs(view.getContext(), dpWidth);
        int pxHeight = (int) new MyDisplay().dpsToPxs(view.getContext(), dpHeight);

        setDimentionsFromPx(view, pxWidth, pxHeight);
    }
}
