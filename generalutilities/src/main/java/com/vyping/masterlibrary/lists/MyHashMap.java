package com.vyping.masterlibrary.lists;

import androidx.annotation.NonNull;

import java.util.HashMap;
import java.util.Map;

public class MyHashMap<model> {

    private HashMap<String, model> hashMap;


    // ----- SetUp ----- //

    public MyHashMap(@NonNull HashMap<String, model> hashMap) {

        this.hashMap = hashMap;
    }


    // ----- Methods ----- //

    public void iterate(Interfase<model> interfase) {

        int count = 0;

        for (Map.Entry<String, model> modelEntry : hashMap.entrySet()) {

            String idModel = modelEntry.getKey();
            model modelValue = modelEntry.getValue();
            count = count + 1;

            interfase.ModelIterated(idModel, modelValue, count);
        }

        interfase.Finish();
    }

    public void incremental(int value, Interfase<model> interfase) {

        int count = 0;
        int increment = value;

        for (Map.Entry<String, model> modelEntry : hashMap.entrySet()) {

            String idModel = modelEntry.getKey();
            model modelValue = modelEntry.getValue();
            count = count + 1;

            increment = increment + interfase.ModelIncrement(idModel, modelValue, count);
        }

        interfase.Finish(increment);
    }


    //----- Interface -----//

    public interface Interfase<model> {

        public default void ModelIterated(String id, model modelIterated, int position) {};
        public default int ModelIncrement(String id, model modelIterated, int position) {

            return 0;
        };
        public default void Finish() {};
        public default void Finish(int finalValue) {};
    }
}
