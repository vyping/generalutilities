package com.vyping.masterlibrary.resources;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.content.res.Resources;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Common.MyFile;
import com.vyping.masterlibrary.R;

public class MyResource {

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

    public int getIdentifier(@NonNull Context context, String folder, String name, @MyFile.Type String type) {

        String Name = new MyFile().setNameLower(name, type);
        String Package = context.getPackageName();

        int imageId = context.getResources().getIdentifier(Name, folder, Package);

        if (imageId != 0) {

            return imageId;

        } else {

            return R.drawable.icon_image;
        }
    }
}
