package com.vyping.masterlibrary.Views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

public abstract class SetLinearLayout {

    private final LinearLayout linearLayout;


    //----- Setup - Section-----//

    public SetLinearLayout(@NonNull Context Context, @NonNull LinearLayout layout, int Style) {

        linearLayout = new LinearLayout(new ContextThemeWrapper(Context, Style), null, Style);
        linearLayout.setOnClickListener(view -> clickOnLayout());

        layout.addView(linearLayout);

        getLayoutView(linearLayout);
    }

    public SetLinearLayout(@NonNull Context Context, int View) {

        linearLayout = ((Activity) Context).findViewById(View);
        linearLayout.setOnClickListener(view -> clickOnLayout());

        getLayoutView(linearLayout);
    }

    public SetLinearLayout(@NonNull Dialog Dialog, int View) {

        linearLayout = Dialog.findViewById(View);
        linearLayout.setOnClickListener(view -> clickOnLayout());

        getLayoutView(linearLayout);
    }

    public SetLinearLayout(@NonNull View ParentView, int View) {

        linearLayout = ParentView.findViewById(View);
        linearLayout.setOnClickListener(view -> clickOnLayout());

        getLayoutView(linearLayout);
    }


    //----- Proccess Tools - Section-----//

    public void setVisibility(int visibility) {

        linearLayout.setVisibility(visibility);
    }


    //----- Return - Section-----//

    protected abstract void getLayoutView(LinearLayout layout);

    protected abstract void clickOnLayout();
}
