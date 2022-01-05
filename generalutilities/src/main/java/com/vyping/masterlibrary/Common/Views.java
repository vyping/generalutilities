package com.vyping.masterlibrary.Common;

import android.app.Dialog;
import android.content.Context;
import android.text.TextWatcher;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.Space;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.R;

public class Views {


    public void setImageButtonMargins(@NonNull ImageButton imageButton, int left, int top, int right, int bottom) {

        LinearLayout.LayoutParams lp = (LinearLayout.LayoutParams) imageButton.getLayoutParams();
        lp.setMargins(left, top, right, bottom);
        imageButton.setLayoutParams(lp);
    }

   public void setFillSpace(Context context, @NonNull LinearLayout layout) {

       int styleSpace = R.style.PopupSpace;

       Space space = new Space(new ContextThemeWrapper(context, styleSpace), null, styleSpace);
       layout.addView(space);

       LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 2.0f);
       space.setLayoutParams(param);
   }



    /**
     * -------- TextView Section
     */

    public void setTextView(@NonNull Dialog dialog, int view, String text) {

        TextView textView = dialog.findViewById(view);
        textView.setText(text);
    }

    public void setTextView(@NonNull Dialog dialog, int view, View.OnClickListener listener, String text) {

        TextView textView = dialog.findViewById(view);
        textView.setText(text);
        textView.setOnClickListener(listener);
    }


    /**
     * -------- EditText Section
     */

    public EditText setEditText(@NonNull Dialog dialog, int view, String text) {

        EditText editText = dialog.findViewById(view);
        editText.setText(text);

        return editText;
    }


    public EditText setEditText(@NonNull Dialog dialog, int view, String hint, String text) {

        EditText editText = dialog.findViewById(view);
        editText.setHint(hint);
        editText.setText(text);

        return editText;
    }


    public EditText setEditText(@NonNull Dialog dialog, int view, @NonNull EditTextListener listener, String text) {

        EditText editText = dialog.findViewById(view);
        editText.setText(text);

        listener.OtherActions(editText);

        return editText;
    }

    public EditText setEditText(@NonNull Dialog dialog, int view, TextWatcher listener, String hint, String text) {

        EditText editText = dialog.findViewById(view);

        editText.setHint(hint);
        editText.setText(text);
        editText.addTextChangedListener(listener);

        return editText;
    }


    /**
     * -------- Buttons Section
     */

    public Button setButton(@NonNull Dialog dialog, int view, String text, int visibility) {

        Button button = dialog.findViewById(view);
        button.setText(text);
        button.setVisibility(visibility);

        return button;
    }

    public void setButton(@NonNull Dialog dialog, int view, View.OnClickListener listener, String text, int visibility) {

        Button button = dialog.findViewById(view);
        button.setText(text);
        button.setVisibility(visibility);
        button.setOnClickListener(listener);
    }


    /**
     * -------- Others Section
     */

    public Spinner setSpinner(@NonNull Dialog dialog, int view, AdapterView.OnItemSelectedListener listener, @NonNull SpinnerListener otherListener, ArrayAdapter<String> adapter) {

        Spinner spinner = dialog.findViewById(view);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(listener);

        otherListener.OtherActions(spinner);

        return spinner;
    }

    public void setCheckBox(@NonNull Dialog dialog, int view, CompoundButton.OnCheckedChangeListener listener, String text, boolean checked) {

        CheckBox checkBox = dialog.findViewById(view);
        checkBox.setText(text);
        checkBox.setChecked(checked);
        checkBox.setOnCheckedChangeListener(listener);
    }


    public SearchView setSearchView(@NonNull Dialog dialog, int view, SearchView.OnQueryTextListener listener, String query, boolean submit) {

        final SearchView searchView = dialog.findViewById(view);
        searchView.setQuery(query, submit);
        searchView.setOnQueryTextListener(listener);

        return searchView;
    }


    /**
     * ------  Interface Section
     */

    public interface EditTextListener {

        void OtherActions(EditText editText);
        void DummyVoid();
    }

    public interface SpinnerListener {

        void OtherActions(Spinner spinner);
        void DummyVoid();
    }
}
