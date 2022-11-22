package com.vyping.masterlibrary.Common;

import android.util.Log;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Bucles.MyBucleFor;

public class LogCat {

    private final String tag = "Desarrollo";
    private String body = "";


    // ----- SetUp ----- //

    public LogCat() {}

    public LogCat(String key, Object body) {

        Log.i(tag, key + ": " + body);
    }

    @SafeVarargs
    public LogCat(@NonNull Object... points) {

        body = "";

        new MyBucleFor().integersUntil(points.length, new MyBucleFor.IntInterface() {

            @Override
            public void intPosition(int item) {

                if (item < points.length -1) {

                    boolean pair = new MyNumbers().isNumberPair(item);

                    if (pair) {

                        if (body.equals("")) {

                            body = String.valueOf(points[item]) + ": ";

                        } else {

                            body = body + String.valueOf(points[item]) + ": ";
                        }

                    } else {

                        body = body + String.valueOf(points[item]) + ", ";
                    }

                } else {

                    if (body.equals("")) {

                        body = String.valueOf(points[item]);

                    } else {

                        body = body + String.valueOf(points[item]);
                    }

                    write();
                }
            }

            private void DummyVoid() {}
        });
    }


    // ----- Methods ----- //

    public LogCat point(String point) {

        if (body.equals("")) {

            body = point + "/";

        } else {

            body = body + point + "/";
        }

        return this;
    }

    public LogCat test(String key, Object value) {

        if (body.equals("")) {

            body = key + value;

        } else {

            body = body + ", " + key + ": " + value;
        }

        return this;
    }

    public void write() {

        Log.i(tag, body);
    }
}
