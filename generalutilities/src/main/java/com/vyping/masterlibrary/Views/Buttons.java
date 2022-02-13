package com.vyping.masterlibrary.Views;

import static android.view.View.VISIBLE;

import static com.vyping.masterlibrary.Common.Definitions.ICON_BOTTOM;
import static com.vyping.masterlibrary.Common.Definitions.ICON_END;
import static com.vyping.masterlibrary.Common.Definitions.ICON_START;
import static com.vyping.masterlibrary.Common.Definitions.ICON_TOP;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Common.Definitions;
import com.vyping.masterlibrary.Images.MyDrawable;

public class Buttons {


    /**
     * -------- Buttons from Layouts - Section
     */

    @NonNull
    public Button setButton(@NonNull Context context, @NonNull LinearLayout layout, int attr, int style, int icon, @Definitions.IconPosition int iconPos, String text, View.OnClickListener listener) {

        Drawable drawable = new MyDrawable().changeSizeBounds(context, icon, 108, 108);

        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        Button button = new Button(wrapper, null, attr, style);

        button.setText(text);
        button.setOnClickListener(listener);

        Paris.style(button).apply(style);

        if (iconPos == ICON_START) {

            button.setCompoundDrawables(drawable, null, null, null);

        } else if (iconPos == ICON_TOP) {

            button.setCompoundDrawables( null, drawable,null, null);

        } else if (iconPos == ICON_END) {

            button.setCompoundDrawables( null, drawable,null, null);

        } else if (iconPos == ICON_BOTTOM) {

            button.setCompoundDrawables( null, drawable,null, null);
        }

        layout.addView(button);

        return button;
    }



    /**
     * -------- Buttons from ParentViews - Section
     */

    public Button setButton(@NonNull Context context, int view) {

        return ((Activity) context).findViewById(view);
    }

    public Button setButton(@NonNull Context context, int view, int visibility) {

        Button button = ((Activity) context).findViewById(view);
        button.setVisibility(visibility);

        return button;
    }

    public Button setButton(@NonNull Context context, int view, String text, int visibility) {

        Button button = ((Activity) context).findViewById(view);
        button.setVisibility(visibility);

        if (visibility == VISIBLE) {

            button.setText(text);
        }

        return button;
    }

    public Button setButton(@NonNull Context context, int view, String text, View.OnClickListener listener) {

        Button button = ((Activity) context).findViewById(view);
        button.setText(text);
        button.setOnClickListener(listener);

        return button;
    }

    public Button setButton(@NonNull Context context, int view, String text, int visibility, View.OnClickListener listener) {

        Button button = ((Activity) context).findViewById(view);
        button.setVisibility(visibility);

        if (visibility == VISIBLE) {

            button.setText(text);
            button.setOnClickListener(listener);
        }

        return button;
    }


    /**
     * -------- Buttons from Dialogs - Section
     */

    public Button setButton(@NonNull Dialog dialog, int view, int visibility) {

        Button button = dialog.findViewById(view);
        button.setVisibility(visibility);

        return button;
    }

    public Button setButton(@NonNull Dialog dialog, int view, String text, int visibility) {

        Button button = dialog.findViewById(view);
        button.setVisibility(visibility);

        if (visibility == VISIBLE) {

            button.setText(text);
        }

        return button;
    }

    public Button setButton(@NonNull Dialog dialog, int view, String text, View.OnClickListener listener) {

        Button button = dialog.findViewById(view);
        button.setText(text);
        button.setOnClickListener(listener);

        return button;
    }

    public Button setButton(@NonNull Dialog dialog, int view, int text, View.OnClickListener listener) {

        Button button = dialog.findViewById(view);
        button.setText(text);
        button.setOnClickListener(listener);

        return button;
    }

    public Button setButton(@NonNull Dialog dialog, int view, String text, int visibility, View.OnClickListener listener) {

        Button button = dialog.findViewById(view);
        button.setText(text);
        button.setVisibility(visibility);
        button.setOnClickListener(listener);

        return button;
    }

    public Button setButton(@NonNull Dialog dialog, int view, int text, int visibility, View.OnClickListener listener) {

        Button button = dialog.findViewById(view);
        button.setText(text);
        button.setVisibility(visibility);
        button.setOnClickListener(listener);

        return button;
    }


    /**
     * -------- AppCompatButton from ParentViews - Section
     */

    public AppCompatButton setAppCompatButton(@NonNull Context context, int view, int visibility) {

        AppCompatButton button = ((Activity) context).findViewById(view);
        button.setVisibility(visibility);

        return button;
    }

    public AppCompatButton setAppCompatButton(@NonNull Context context, int view, String text, int visibility) {

        AppCompatButton button = ((Activity) context).findViewById(view);
        button.setVisibility(visibility);

        if (visibility == VISIBLE) {

            button.setText(text);
        }

        return button;
    }

    public AppCompatButton setAppCompatButton(@NonNull Context context, int view, String text, View.OnClickListener listener) {

        AppCompatButton button = ((Activity) context).findViewById(view);
        button.setText(text);
        button.setOnClickListener(listener);

        return button;
    }

    public AppCompatButton setAppCompatButton(@NonNull Context context, int view, String text, int visibility, View.OnClickListener listener) {

        AppCompatButton button = ((Activity) context).findViewById(view);
        button.setVisibility(visibility);

        if (visibility == VISIBLE) {

            button.setText(text);
            button.setOnClickListener(listener);
        }

        return button;
    }


    /**
     * -------- AppCompatButton from Dialogs - Section
     */

    public AppCompatButton setAppCompatButton(@NonNull Dialog dialog, int view, int visibility) {

        AppCompatButton button = dialog.findViewById(view);
        button.setVisibility(visibility);

        return button;
    }

    public AppCompatButton setAppCompatButton(@NonNull Dialog dialog, int view, String text, int visibility) {

        AppCompatButton button = dialog.findViewById(view);
        button.setVisibility(visibility);

        if (visibility == VISIBLE) {

            button.setText(text);
        }

        return button;
    }

    public AppCompatButton setAppCompatButton(@NonNull Dialog dialog, int view, String text, View.OnClickListener listener) {

        AppCompatButton button = dialog.findViewById(view);
        button.setText(text);
        button.setOnClickListener(listener);

        return button;
    }

    public AppCompatButton setAppCompatButton(@NonNull Dialog dialog, int view, int text, View.OnClickListener listener) {

        AppCompatButton button = dialog.findViewById(view);
        button.setText(text);
        button.setOnClickListener(listener);

        return button;
    }

    public AppCompatButton setAppCompatButton(@NonNull Dialog dialog, int view, String text, int visibility, View.OnClickListener listener) {

        AppCompatButton button = dialog.findViewById(view);
        button.setText(text);
        button.setVisibility(visibility);
        button.setOnClickListener(listener);

        return button;
    }

    public AppCompatButton setAppCompatButton(@NonNull Dialog dialog, int view, int text, int visibility, View.OnClickListener listener) {

        AppCompatButton button = dialog.findViewById(view);
        button.setText(text);
        button.setVisibility(visibility);
        button.setOnClickListener(listener);

        return button;
    }
}
