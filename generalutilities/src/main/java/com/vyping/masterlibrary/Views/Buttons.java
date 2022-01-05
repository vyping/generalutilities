package com.vyping.masterlibrary.Views;

import static android.view.View.VISIBLE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;

public class Buttons {


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
