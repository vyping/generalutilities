package com.vyping.masterlibrary.lists;

import com.vyping.masterlibrary.adapters.recyclerview.interfase.RecyclerInterfase;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MySorting<model> implements Comparator<model> {

    private Interfase<model> interfase;


    // ----- SetUp ----- //

    public void sortStrings(ArrayList<String> arrayStrings) {

        Collections.sort(arrayStrings);
    }

    public void sortByName(ArrayList<model> arrayModels, Interfase<model> interfase) {

        this.interfase = interfase;

        Collections.sort(arrayModels, this);
    }


    // ----- Methods ----- //

    @Override
    public int compare(model model1, model model2) {

        String name1 = interfase.GetName(model1);
        String name2 = interfase.GetName(model2);

        return name1.compareTo(name2);
    }


    // ----- Interfase ----- //

    public interface Interfase<model> extends RecyclerInterfase {

        public String GetName(model model);
    }
}
