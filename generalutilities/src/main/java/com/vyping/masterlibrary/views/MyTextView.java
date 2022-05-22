package com.vyping.masterlibrary.views;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Common.MyStrings;

public class MyTextView {


    /**
     * -------- Created By Attributes - Section
     */

    @NonNull
    public TextView createByAttributes(@NonNull Context context, int attr, int style, String text) {

        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        TextView textView = new TextView(context, null, attr, style);
        textView.setText(text);

        Paris.style(textView).apply(style);

        return textView;
    }

    @NonNull
    public TextView createByAttributes(@NonNull Context context, int attr, int style, int text) {

        String Text = new MyStrings().getStringResources(context, text);

        return createByAttributes(context, attr, style, Text);
    }

    /**
     * -------- TextView from Context - Section
     */

    public TextView setTextView(@NonNull Context context, int view) {

        return ((Activity) context).findViewById(view);
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
        String Text = new MyStrings().getStringResources(context, text);

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

    public TextView setTextView(@NonNull Dialog dialog, int view, String text, View.OnClickListener listener) {

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

