package com.vyping.masterlibrary.resources;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;

public class MyResources {

    public boolean exist(@NonNull Context context, int resource) {

        boolean exist = FALSE;

        try {

            String name = context.getResources().getResourceName(resource);

            if (name != null) {

                exist = TRUE;
            }

        } catch (Resources.NotFoundException ignore) {}

        return exist;
    }

    public String getName(@NonNull Context context, int resource) {

        return context.getResources().getResourceName(resource);
    }
}
