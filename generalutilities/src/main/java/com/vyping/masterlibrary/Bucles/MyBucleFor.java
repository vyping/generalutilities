package com.vyping.masterlibrary.Bucles;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MyBucleFor {


    public void integersUntil(int number, IntInterface Interface) {

        for (int i = 0; i < number; ++i) {

            Interface.intPosition(i);
        }

        Interface.Finish();
    }

    public void integerList(@NonNull int[] numberList, IntInterface Interface) {

        for (int n : numberList) {

            Interface.intPosition(n);
        }

        Interface.Finish();
    }

    public void charsList(@NonNull char[] charList, CharsInterface Interface) {

        for (char c : charList) {

            Interface.itemFromArray(c);
        }

        Interface.Finish();
    }

    public void stringsArray(@NonNull ArrayList<String> arrayList, StringInterface Interface) {

        for (int i = 0; i < arrayList.size(); ++i) {

            Interface.itemFromArray(arrayList.get(i));
        }

        Interface.Finish();
    }

    public void integersArray(@NonNull ArrayList<Integer> arrayList, IntInterface Interface) {

        for (int i = 0; i < arrayList.size(); ++i) {

            Interface.intPosition(arrayList.get(i));
        }

        Interface.Finish();
    }

    public interface IntInterface {

        public void intPosition(int item);
        default public void Finish() {};
    }

    public interface CharsInterface {

        public void itemFromArray(char item);
        default public void Finish() {};
    }

    public interface StringInterface {

        public void itemFromArray(String item);
        default public void Finish() {};
    }
}
