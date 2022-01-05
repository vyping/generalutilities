package com.vyping.masterlibrary.Views;

import android.app.Dialog;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

public class EditTexts {


    /* ---------- EditText - Section ---------- */

    @NonNull
    public EditText setEditText(@NonNull Dialog dialog, int view) {

        return dialog.findViewById(view);
    }

    @NonNull
    public EditText setEditText(@NonNull Dialog dialog, int view, TextWatcher listener) {

        EditText editText = dialog.findViewById(view);

        editText.addTextChangedListener(listener);

        return editText;
    }

    @NonNull
    public EditText setEditText(@NonNull Dialog dialog, int view, View.OnKeyListener listener) {

        EditText editText = dialog.findViewById(view);

        editText.setOnKeyListener(listener);

        return editText;
    }

    public EditText setEditText(@NonNull Dialog dialog, int view, View.OnKeyListener listener, TextWatcher textWatcher) {

        EditText editText = dialog.findViewById(view);

        editText.addTextChangedListener(textWatcher);
        editText.setOnKeyListener(listener);

        return editText;
    }


    /* ---------- EditText: hint - Section ---------- */

    @NonNull
    public EditText setEditText(@NonNull Dialog dialog, int view, String hint) {

        EditText editText = dialog.findViewById(view);
        editText.setHint(hint);

        return editText;
    }

    @NonNull
    public EditText setEditText(@NonNull Dialog dialog, int view, String hint, TextWatcher listener) {

        EditText editText = dialog.findViewById(view);

        editText.setHint(hint);
        editText.addTextChangedListener(listener);

        return editText;
    }

    @NonNull
    public EditText setEditText(@NonNull Dialog dialog, int view, String hint, View.OnKeyListener listener) {

        EditText editText = dialog.findViewById(view);

        editText.setHint(hint);
        editText.setOnKeyListener(listener);

        return editText;
    }

    public EditText setEditText(@NonNull Dialog dialog, int view, String hint, View.OnKeyListener listener, TextWatcher textWatcher) {

        EditText editText = dialog.findViewById(view);
        editText.setHint(hint);
        editText.addTextChangedListener(textWatcher);
        editText.setOnKeyListener(listener);

        return editText;
    }


    /* ---------- EditText: hint & type - Section ---------- */

    @NonNull
    public EditText setEditText(@NonNull Dialog dialog, int view, String hint, int type) {

        EditText editText = dialog.findViewById(view);
        editText.setHint(hint);
        editText.setInputType(type);

        return editText;
    }

    public EditText setEditText(@NonNull Dialog dialog, int view, String hint, int type, TextWatcher textWatcher) {

        EditText editText = dialog.findViewById(view);
        editText.setHint(hint);
        editText.setInputType(type);
        editText.addTextChangedListener(textWatcher);

        return editText;
    }

    public EditText setEditText(@NonNull Dialog dialog, int view, String hint, int type, View.OnKeyListener listener) {

        EditText editText = dialog.findViewById(view);
        editText.setHint(hint);
        editText.setInputType(type);
        editText.setOnKeyListener(listener);

        return editText;
    }

    public EditText setEditText(@NonNull Dialog dialog, int view, String hint, int type, View.OnKeyListener listener, TextWatcher textWatcher) {

        EditText editText = dialog.findViewById(view);
        editText.setHint(hint);
        editText.setInputType(type);
        editText.addTextChangedListener(textWatcher);
        editText.setOnKeyListener(listener);

        return editText;
    }
}
