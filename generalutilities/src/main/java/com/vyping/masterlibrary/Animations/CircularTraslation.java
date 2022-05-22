package com.vyping.masterlibrary.Animations;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class CircularTraslation extends Animation {

    private View view;
    private float cx, cy;
    private float prevX, prevY;
    private float r;
    private float prevDx, prevDy;


    public CircularTraslation(View view, float r) {

        this.view = view;
        this.r = r;
    }

    @Override
    public boolean willChangeBounds() {

        return true;
    }

    @Override
    public void initialize(int width, int height, int parentWidth, int parentHeight) {

        int cxImage = width / 2;
        int cyImage = height / 2;
        cx = view.getLeft() + cxImage;
        cy = view.getTop() + cyImage;

        prevX = cx;
        prevY = cy;
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {

        if (interpolatedTime == 0) {

            t.getMatrix().setTranslate(prevDx, prevDy);
            return;
        }

        float angleDeg = (interpolatedTime * 360f + 270) % 360;
        float angleRad = (float) Math.toRadians(angleDeg);

        float x = (float) (cx + r * Math.cos(angleRad));
        float y = (float) (cy + r * Math.sin(angleRad));


        float dx = prevX - x;
        float dy = prevY - y;

        prevX = x;
        prevY = y;

        prevDx = dx;
        prevDy = dy;

        t.getMatrix().setTranslate(dx, dy);
    }
}