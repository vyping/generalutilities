package com.vyping.masterlibrary.Common;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class MyArrayList {

    public List<?> objectToList(@NonNull Object object) {

        List<?> list = new ArrayList<>();

        if (object.getClass().isArray()) {

            list = Arrays.asList((Object[]) object);

        } else if (object instanceof Collection) {

            list = new ArrayList<>((Collection<?>) object);
        }

        return list;
    }

    public ArrayList<?> objectToArrayList(@NonNull Object object) {

        ArrayList<?> list = new ArrayList<>();

        if (object.getClass().isArray()) {

            list.toArray((Object[]) object);

        } else if (object instanceof Collection) {

            list = new ArrayList<>((Collection<?>) object);
        }

        return list;
    }

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
