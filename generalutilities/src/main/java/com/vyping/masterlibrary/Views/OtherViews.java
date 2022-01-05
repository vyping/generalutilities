package com.vyping.masterlibrary.Views;

import android.app.Dialog;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SearchView;
import android.widget.Spinner;

import androidx.annotation.NonNull;

public class OtherViews {


    /**
     * -------- Others Section
     */

    @NonNull
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

    public interface SpinnerListener {

        void OtherActions(Spinner spinner);
        void DummyVoid();
    }
}
