package com.vyping.masterlibrary.Views;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Images.MyDrawable;

public abstract class SetImageButton {

    private final Context context;

    private final ImageButton imageButton;


    //----- Setup - Section-----//

    /**
     *
     * Crea un ImageButton mediante codigo y retorna el View correspondiente.
     * @param Context : Contexto desde el cual se llamo al ImageButton (Context).
     * @param drawable : Imagen a mostrar en el ImageButton (R.drawable).
     * @param Style : Estilo que tomara el ImageButton (R.style).
     */
    public SetImageButton(Context Context, @NonNull LinearLayout container, int drawable, int attr, int Style) {

        context = Context;
        ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(Context, Style);

        imageButton = new ImageButton(contextThemeWrapper, null, attr, Style);

        Paris.style(imageButton).apply(Style);

        container.addView(imageButton);

        getButtonView(setAttributes(drawable));
    }

    public SetImageButton(@NonNull Context Context, int view, int drawable) {

        context = Context;
        imageButton = ((Activity) context).findViewById(view);

        getButtonView(setAttributes(drawable));
    }

    public SetImageButton(@NonNull Dialog dialog, int view, int drawable) {

        context = dialog.getContext();
        imageButton = dialog.findViewById(view);

        getButtonView(setAttributes(drawable));
    }

    public SetImageButton(@NonNull View parent, int view, int drawable) {

        context = parent.getContext();
        imageButton = parent.findViewById(view);

        getButtonView(setAttributes(drawable));
    }

    private ImageButton setAttributes(int drawable) {

        Drawable Drawable = ContextCompat.getDrawable(context, drawable);

        imageButton.setImageDrawable(Drawable);
        imageButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                clickOnButton(imageButton);
            }

           private void DummyVoid() {}
        });

        return imageButton;
    }


    //----- Proccess Tools - Section-----//

    public void setVisibility(int visibility) {

        imageButton.setVisibility(visibility);
    }

    public void setImage(int resDrawable, int color) {

        Drawable drawable;

        if (color == 0) {

            drawable = ContextCompat.getDrawable(context, resDrawable);

        } else {

            drawable = new MyDrawable().changeDrawableColor(context, resDrawable, color);
        }

        imageButton.setImageDrawable(drawable);
    }

    public void setBackground(int imageBackground) {

        Drawable background = ContextCompat.getDrawable(context, imageBackground);

        imageButton.setBackground(background);
    }

    public void setBackground(int imageBackground, int color) {

        Drawable background = new MyDrawable().changeDrawableColor(context, imageBackground, color);

        imageButton.setBackground(background);
    }


    //----- Aditional Tools - Section-----//

    public void setMargins(int left, int top, int right, int bottom) {

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageButton.getLayoutParams();
        lp.setMargins(left, top, right, bottom);
        imageButton.setLayoutParams(lp);
    }


    //----- Return - Section-----//

    protected abstract void getButtonView(ImageButton imageButton);

    protected abstract void clickOnButton(View imageButton);
}
