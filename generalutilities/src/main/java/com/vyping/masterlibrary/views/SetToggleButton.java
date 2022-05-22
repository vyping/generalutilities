package com.vyping.masterlibrary.views;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ImageSpan;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;

public abstract class SetToggleButton {

    private final Context context;

    private final ToggleButton toggleButton;


    //----- Setup - Section-----//

    /**
     * Crea un ImageButton mediante codigo y retorna el View correspondiente.
     *
     * @param Context  : Contexto desde el cual se llamo al ImageButton (Context).
     * @param drawable : Imagen a mostrar en el ImageButton (R.drawable).
     * @param Style    : Estilo que tomara el ImageButton (R.style).
     */
    public SetToggleButton(Context Context, @NonNull LinearLayout container, int drawable, int attr, int Style) {

        context = Context;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(Context, Style);

        toggleButton = new ToggleButton(contextThemeWrapper, null, attr, Style);

        Paris.style(toggleButton).apply(Style);

        container.addView(toggleButton);

        getButtonView(setAttributes(drawable));
    }

    private ToggleButton setAttributes(int drawable) {

        ImageSpan imageSpan = new ImageSpan(context, drawable);
        SpannableString content = new SpannableString("");
        content.setSpan(imageSpan, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        toggleButton.setTextOn(content);
        toggleButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                clickOnButton(toggleButton);
            }

            private void DummyVoid() {
            }
        });

        return toggleButton;
    }


    //----- Proccess Tools - Section-----//

    //----- Return - Section-----//

    protected abstract void getButtonView(ToggleButton toggleButton);

    protected abstract void clickOnButton(View imageButton);
}
