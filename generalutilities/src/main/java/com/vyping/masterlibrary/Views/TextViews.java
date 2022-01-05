package com.vyping.masterlibrary.Views;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.vyping.masterlibrary.Common.Strings;

public class TextViews {


    /**
     * -------- TextView from Context - Section
     */

    public TextView setTextView(@NonNull Context context, int view) {

        return  ((Activity) context).findViewById(view);
    }

    public TextView setTextView(@NonNull Context context, int view, String text) {

        TextView textView = ((Activity) context).findViewById(view);
        textView.setText(text);

        return textView;
    }

    public TextView setTextView(@NonNull Context context, int view, View.OnClickListener listener, String text) {

        TextView textView = ((Activity) context).findViewById(view);
        textView.setText(text);
        textView.setOnClickListener(listener);

        return textView;
    }

    public TextView setTextView(@NonNull Context context, int view, View.OnClickListener listener, String text, int visibility) {

        TextView textView = ((Activity) context).findViewById(view);
        textView.setText(text);
        textView.setOnClickListener(listener);

        if (visibility == VISIBLE) {

            textView.setVisibility(VISIBLE);

        } else {

            textView.setVisibility(GONE);
        }

        return textView;
    }


    /**
     * -------- TextView from Dialogs - Section
     */

    public TextView setTextView(@NonNull Dialog dialog, int view, String text) {

        TextView textView = dialog.findViewById(view);
        textView.setText(text);

        return textView;
    }


    public TextView setTextView(@NonNull Dialog dialog, int view, int text) {

        Context context = dialog.getContext();
        String Text = new Strings().getString(context, text);

        TextView textView = dialog.findViewById(view);
        textView.setText(Text);

        return textView;
    }

    public TextView setTextView(@NonNull Dialog dialog, int view, String text, int visibility) {

        TextView textView = dialog.findViewById(view);
        textView.setText(text);

        if (visibility == VISIBLE) {

            textView.setVisibility(VISIBLE);

        } else {

            textView.setVisibility(GONE);
        }

        return textView;
    }

    public TextView setTextView(@NonNull Dialog dialog, int view, int resText, int visibility) {

        Context context = dialog.getContext();
        String text = context.getResources().getString(resText);

        TextView textView = dialog.findViewById(view);
        textView.setText(text);

        if (visibility == VISIBLE) {

            textView.setVisibility(VISIBLE);

        } else {

            textView.setVisibility(GONE);
        }

        return textView;
    }

    public TextView setTextView(@NonNull Dialog dialog, int view, View.OnClickListener listener, String text) {

        TextView textView = dialog.findViewById(view);
        textView.setText(text);
        textView.setOnClickListener(listener);

        return textView;
    }


    /**
     * -------- TextView from ParentView - Section
     */

    public TextView setTextView(@NonNull View parentView, int view, String text) {

        TextView textView = parentView.findViewById(view);
        textView.setText(text);

        return textView;
    }


    /**
     * -------- AppCompatTextView from Context Section
     */

    public AppCompatTextView setAppCompatTextView(@NonNull Context context, int view, View.OnClickListener listener, String text) {

        AppCompatTextView textView = ((Activity) context).findViewById(view);
        textView.setText(text);
        textView.setOnClickListener(listener);

        return textView;
    }


    /**
     * -------- AppCompatTextView from Dialog Section
     */

    public AppCompatTextView setAppCompatTextView(@NonNull Dialog dialog, int view, String text) {

        AppCompatTextView textView = dialog.findViewById(view);
        textView.setText(text);

        return textView;
    }

    public AppCompatTextView setAppCompatTextView(@NonNull Dialog dialog, int view, View.OnClickListener listener, String text) {

        AppCompatTextView textView = dialog.findViewById(view);
        textView.setText(text);
        textView.setOnClickListener(listener);

        return textView;
    }
}

