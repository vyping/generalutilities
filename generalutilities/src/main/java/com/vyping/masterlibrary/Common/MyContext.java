package com.vyping.masterlibrary.Common;

import android.annotation.SuppressLint;
import android.content.Context;

public class MyContext {

    @SuppressLint("StaticFieldLeak")
    public static Context CONTEXT;


    // ----- SetUp ----- //

    public MyContext(Context myContext) {

        CONTEXT = myContext;
    }
}
