package com.vyping.masterlibrary.Common;

import android.content.Context;
import android.widget.Toast;

public class MyToast {

    public MyToast(Context context, String text) {

        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
