package com.vyping.masterlibrary.Common;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;

public class MyArrayList {


    public ArrayList<String> listToArray(String[] list) {

        return new ArrayList<>(Arrays.asList(list));
    }

    public ArrayList<Integer> listToArray(Integer[] list) {

        return new ArrayList<>(Arrays.asList(list));
    }

    public String[] arrayToList(@NonNull ArrayList<String> array) {

        return Arrays.copyOf(array.toArray(), array.size(), String[].class);
    }
}
