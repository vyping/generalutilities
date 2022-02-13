package com.vyping.masterlibrary.Animations;

import static android.view.animation.Animation.INFINITE;

import android.view.View;

import androidx.annotation.NonNull;

public class TraslationCircular {

    private CircularTraslation circularRotate;
    private View selectedView;

    public TraslationCircular(@NonNull View view, View container, int duration, int radious) {

        selectedView = view;

        circularRotate = new CircularTraslation(container, radious);
        circularRotate.setRepeatCount(INFINITE);
        circularRotate.setDuration(duration);
        circularRotate.setFillAfter(false);

        selectedView.startAnimation(circularRotate);
    }

    public void stopCircularTraslation() {

        circularRotate.cancel();

        if (selectedView != null) {

            if (selectedView.getAnimation() != null) {

                selectedView.clearAnimation();
            }
        }
    }
}
