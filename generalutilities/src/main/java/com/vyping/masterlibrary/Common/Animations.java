package com.vyping.masterlibrary.Common;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import androidx.annotation.NonNull;

public class Animations {

    private RotateAnimation rotate;
    private TranslateAnimation translation;
    private ScaleAnimation scale;
    private View selectedView;

    public void Rotation(@NonNull View view, int duration) {

        selectedView = view;

        rotate = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotate.setInterpolator(new LinearInterpolator());
        rotate.setDuration(duration);
        rotate.setRepeatCount(Animation.INFINITE);
        rotate.setFillAfter(true);
        view.startAnimation(rotate);
    }

    public void HorizontalTranslation(@NonNull View view, int duration, int Xo, int Xf) {

        selectedView = view;

        translation = new TranslateAnimation(Xo, Xf, 0, 0);
        translation.setDuration(duration);
        translation.setFillAfter(true);
        view.startAnimation(translation);
    }

    public void HorizontalInfiniteTranslation(@NonNull View view, int duration, int Xo, int Xf) {

        selectedView = view;

        translation = new TranslateAnimation(Xo, Xf, 0, 0);
        translation.setDuration(duration);
        translation.setFillAfter(true);
        translation.setRepeatCount(Animation.INFINITE);
        view.startAnimation(translation);
    }

    public void ScaleAmpliate(@NonNull View view, int duration) {

        selectedView = view;

        scale = new ScaleAnimation(0f, 1f, 0f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(duration);
        scale.setFillAfter(true);
        view.startAnimation(scale);
    }

    public void ScaleReduce(@NonNull View view, int duration) {

        selectedView = view;

        scale = new ScaleAnimation(1f, 0f, 1f, 0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(duration);
        scale.setFillAfter(true);
        view.startAnimation(scale);
    }

    public void stopAnimation() {

        if (selectedView != null) {

            if (selectedView.getAnimation() != null) {

                selectedView.clearAnimation();
            }
        }
    }

    public void AlphaAmpliate(@NonNull View view, int duration) {

        selectedView = view;

        AlphaAnimation alpha = new AlphaAnimation(0f, 1f);
        alpha.setDuration(duration);
        alpha.setFillAfter(true);

        view.startAnimation(alpha);
    }

    public void AlphaReduce(@NonNull View view, int duration) {

        selectedView = view;

        AlphaAnimation alpha = new AlphaAnimation(1f, 0f);
        alpha.setDuration(duration);
        alpha.setFillAfter(true);

        view.startAnimation(alpha);
    }
}
