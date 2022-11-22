package com.vyping.masterlibrary.handlers;

import android.content.Context;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.lists.MySorting;

import java.util.ArrayList;

public class MySpinnerHandler<model> {

    private final Context context;
    private final Spinner spinner;
    private Interfase<model> interfase;

    private ArrayAdapter<String> adapter;
    private ArrayList<model> arrayModel;
    private ArrayList<String> arrayTags;


    private String title;


    // ----- SetUp ----- //

    public MySpinnerHandler(@NonNull Spinner spinner) {

        this.context = spinner.getContext();
        this.spinner = spinner;
    }

    public MySpinnerHandler(@NonNull Spinner spinner, String title) {

        this.context = spinner.getContext();
        this.spinner = spinner;
        this.title = title;
    }


    // ----- Methods ----- //

    public void setByTag(int layout, String tag) {

        ArrayList<String> arrayTag = new ArrayList<>();
        arrayTag.add(tag);

        adapter = new ArrayAdapter<>(context, layout, arrayTag);
        spinner.setAdapter(adapter);
        spinner.setSelection(0);
    }

    public void setAdapter(int layout, ArrayList<model> arrayModel, Interfase<model> interfase) {

        this.arrayModel = arrayModel;
        this.interfase = interfase;

        setArrayTags();

        adapter = new ArrayAdapter<>(context, layout, arrayTags);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(adapterListener);
    }

    public void setArrayTags() {

        this.arrayTags = new ArrayList<>();

        for (model tempModel : arrayModel) {

            String tempTag = interfase.MainParameter(tempModel);
            arrayTags.add(tempTag);
        }

        new MySorting<model>().sortByName(arrayModel, new MySorting.Interfase<>() {

            @Override
            public String GetName(model compareModel) {

                return interfase.MainParameter(compareModel);
            }

            private void DummyVOid() {}
        });
        new MySorting<String>().sortStrings(arrayTags);

        arrayModel.add(0, interfase.TitleModel());
        arrayTags.add(0, title);
    }


    // ----- Listeners ----- //

    public final AdapterView.OnItemSelectedListener adapterListener = new AdapterView.OnItemSelectedListener() {

        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int index, long id) {

            model selectedModel = arrayModel.get(index);
            String mainParameter = interfase.MainParameter(selectedModel);

            if (!mainParameter.equals(title)) {

                interfase.SelectedItem(selectedModel);
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    };


    // ----- Interfase ----- //

    public interface Interfase<model> {

        public model TitleModel();
        public String MainParameter(model model);
        public void SelectedItem(model model);
    }
}
