package com.vyping.masterlibrary.views;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.widget.LinearLayout;
import android.widget.Space;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.R;

public class Spaces {

    public void setSpace(Context context, @NonNull LinearLayout layout) {

        int styleSpace = R.style.PopupSpace;

        Space space = new Space(new ContextThemeWrapper(context, styleSpace), null, styleSpace);
        layout.addView(space);

        LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 2.0f);
        space.setLayoutParams(param);
    }
}

