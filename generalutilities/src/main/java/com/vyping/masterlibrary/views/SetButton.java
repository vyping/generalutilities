package com.vyping.masterlibrary.views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

public abstract class SetButton {

    private final Button button;


    //----- Setup - Section-----//

    public SetButton(Context Context, @NonNull LinearLayout layout, String Text, int Style) {

        button = new Button(new ContextThemeWrapper(Context, Style), null, Style);
        button.setText(Text);
        button.setOnClickListener(view -> clickOnButton(Text));

        layout.addView(button);

        getButtonView(button);
    }

    public SetButton(@NonNull Context Context, int View, String Text) {

        button = ((Activity) Context).findViewById(View);
        button.setText(Text);
        button.setOnClickListener(view -> clickOnButton(Text));

        getButtonView(button);
    }

    public SetButton(@NonNull Dialog Dialog, int View, String Text) {

        button = Dialog.findViewById(View);
        button.setText(Text);
        button.setOnClickListener(view -> clickOnButton(Text));

        getButtonView(button);
    }

    public SetButton(@NonNull View Parent, int View, String Text) {

        button = Parent.findViewById(View);
        button.setText(Text);
        button.setOnClickListener(view -> clickOnButton(Text));

        getButtonView(button);
    }


    //----- Proccess Tools - Section-----//

    public void setVisibility(int visibility) {

        button.setVisibility(visibility);
    }

    public void setText(String Text) {

        button.setText(Text);
    }

    public String getText() {

        return String.valueOf(button.getText());
    }


    //----- Return - Section-----//

    protected abstract void getButtonView(Button button);

    protected abstract void clickOnButton(String text);
}
