package com.vyping.masterlibrary.Images;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.vyping.masterlibrary.Common.Files;
import com.vyping.masterlibrary.Common.Strings;

public class MyImageTools {

    public int requestIcon(@NonNull Context context, String folder, String name, int nullIcon) {

        String Name = new Files().translateToFileName(name);
        String Package = context.getPackageName();

        int imageId = context.getResources().getIdentifier(Name, folder, Package);

        if (imageId != 0) {

            return imageId;

        } else {

            return nullIcon;
        }
    }

    public BitmapDescriptor iconToBitmap(Context context, @DrawableRes int vectorIcon) {

        Drawable background = ContextCompat.getDrawable(context, vectorIcon);

        assert background != null;
        background.setBounds(0, 0, background.getIntrinsicWidth(), background.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(background.getIntrinsicWidth(), background.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        background.draw(canvas);

        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
}
