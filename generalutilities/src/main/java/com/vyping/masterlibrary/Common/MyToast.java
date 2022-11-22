package com.vyping.masterlibrary.Common;

import android.content.Context;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

public class MyToast {

    Context context;


    // ----- SetUp ----- //

    public MyToast(@NonNull View view) {

        this.context = view.getContext();
    }

    public MyToast(@NonNull Context context) {

        this.context = context;
    }

    public MyToast(@NonNull View view, String text) {

        Context context = view.getContext();

        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public MyToast(Context context, String text) {

        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }


    // ----- Methods ----- //

    public void showShort(String text) {

        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    public void showLong(String text) {

        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
