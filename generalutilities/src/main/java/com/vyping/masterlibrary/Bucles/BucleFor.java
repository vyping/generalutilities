package com.vyping.masterlibrary.Bucles;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class BucleFor {


    public void foNumber(int number, IntInterface Interface) {

        for (int i = 0; i < number; ++i) {

            Interface.intPosition(i);
        }
    }

    public void forInts(@NonNull int[] numberList, IntInterface Interface) {

        for (int n : numberList) {

            Interface.intPosition(n);
        }
    }

    public void forChars(@NonNull char[] charList, CharsInterface Interface) {

        for (char c : charList) {

            Interface.itemFromArray(c);
        }
    }

    public void forStrings(@NonNull ArrayList<String> arrayList, StringInterface Interface) {

        for (int i = 0; i < arrayList.size(); ++i) {

            Interface.itemFromArray(arrayList.get(i));
        }
    }

    public interface IntInterface {

        void intPosition(int item);
    }

    public interface CharsInterface {

        void itemFromArray(char item);
    }

    public interface StringInterface {

        void itemFromArray(String item);
    }
}
