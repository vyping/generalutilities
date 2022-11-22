package com.vyping.masterlibrary.Animations;

import static android.view.View.GONE;
import static android.view.View.INVISIBLE;
import static android.view.animation.Animation.INFINITE;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

import androidx.annotation.NonNull;

public class MyAnimation {

    private RotateAnimation rotate;
    private CircularTraslation circularRotate;
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

    public void CircularTranslation(@NonNull View view, View container, int duration, int radious) {

        selectedView = view;

        circularRotate = new CircularTraslation(container, radious);
        circularRotate.setRepeatCount(INFINITE);
        circularRotate.setDuration(duration);
        circularRotate.setFillAfter(false);
        view.startAnimation(circularRotate);
    }

    public void stopCircularTraslation() {

        circularRotate.cancel();
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

    public void ScaleAmpliate(@NonNull View view, int duration, float fromX, float toX, float fromY, float toY) {

        selectedView = view;

        scale = new ScaleAnimation(fromX, toX, fromY, toY, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
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

    public void exchange(View layoutMain, @NonNull View layoutSecondary) {

        if (layoutSecondary.getVisibility() == INVISIBLE || layoutSecondary.getVisibility() == GONE) {

            ScaleAnimation scale = new ScaleAnimation(1f, 0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scale.setDuration(200);
            scale.setFillAfter(false);
            scale.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {}

                @Override
                public void onAnimationEnd(Animation animation) {

                    layoutMain.setVisibility(INVISIBLE);
                    layoutMain.setElevation(-4f);
                    layoutMain.setEnabled(false);

                    layoutSecondary.setVisibility(View.VISIBLE);
                    layoutSecondary.setElevation(4f);
                    layoutSecondary.setEnabled(true);

                    ScaleAnimation scale1 = new ScaleAnimation(0f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scale1.setDuration(200);
                    scale1.setFillAfter(true);
                    layoutSecondary.startAnimation(scale1);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
            layoutMain.startAnimation(scale);

        } else {

            ScaleAnimation scale = new ScaleAnimation(1f, 0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scale.setDuration(200);
            scale.setFillAfter(false);
            scale.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {}

                @Override
                public void onAnimationEnd(Animation animation) {

                    layoutSecondary.setVisibility(INVISIBLE);
                    layoutSecondary.setElevation(-4f);
                    layoutSecondary.setEnabled(false);

                    layoutMain.setVisibility(View.VISIBLE);
                    layoutMain.setElevation(4f);
                    layoutMain.setEnabled(true);

                    ScaleAnimation scale1 = new ScaleAnimation(0f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scale1.setDuration(200);
                    scale1.setFillAfter(true);
                    layoutMain.startAnimation(scale1);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
            layoutSecondary.startAnimation(scale);
        }
    }

    public void exchange(View layoutMain, @NonNull View layoutSecondary, ExchangeInterfase interfase) {

        if (layoutSecondary.getVisibility() == INVISIBLE || layoutSecondary.getVisibility() == GONE) {

            ScaleAnimation scale = new ScaleAnimation(1f, 0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scale.setDuration(200);
            scale.setFillAfter(false);
            scale.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {}

                @Override
                public void onAnimationEnd(Animation animation) {

                    layoutMain.setVisibility(INVISIBLE);
                    layoutMain.setElevation(-4f);
                    layoutMain.setEnabled(false);

                    layoutSecondary.setVisibility(View.VISIBLE);
                    layoutSecondary.setElevation(4f);
                    layoutSecondary.setEnabled(true);

                    ScaleAnimation scale1 = new ScaleAnimation(0f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scale1.setDuration(200);
                    scale1.setFillAfter(true);
                    layoutSecondary.startAnimation(scale1);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
            layoutMain.startAnimation(scale);

            interfase.Exchanged(TRUE);

        } else {

            ScaleAnimation scale = new ScaleAnimation(1f, 0f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            scale.setDuration(200);
            scale.setFillAfter(false);
            scale.setAnimationListener(new Animation.AnimationListener() {

                @Override
                public void onAnimationStart(Animation animation) {}

                @Override
                public void onAnimationEnd(Animation animation) {

                    layoutSecondary.setVisibility(INVISIBLE);
                    layoutSecondary.setElevation(-4f);
                    layoutSecondary.setEnabled(false);

                    layoutMain.setVisibility(View.VISIBLE);
                    layoutMain.setElevation(4f);
                    layoutMain.setEnabled(true);

                    ScaleAnimation scale1 = new ScaleAnimation(0f, 1f, 1f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                    scale1.setDuration(200);
                    scale1.setFillAfter(true);
                    layoutMain.startAnimation(scale1);
                }

                @Override
                public void onAnimationRepeat(Animation animation) {}
            });
            layoutSecondary.startAnimation(scale);

            interfase.Exchanged(FALSE);
        }
    }

    public void pulse(@NonNull View view) {

        ScaleAnimation scale = new ScaleAnimation(1f, 1.2f, 1f, 1.2f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        scale.setDuration(100);
        scale.setFillAfter(true);
        scale.setAnimationListener(new Animation.AnimationListener() {

            @Override
            public void onAnimationStart(Animation animation) {}

            @Override
            public void onAnimationEnd(Animation animation) {

                ScaleAnimation scale = new ScaleAnimation(1.2f, 1.0f, 1.2f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
                scale.setDuration(100);
                scale.setFillAfter(true);
                view.startAnimation(scale);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {}
        });

        view.startAnimation(scale);
    }


    // ----- Interfase ----- //

    public interface ExchangeInterfase {

        public void Exchanged(boolean front);
    }
}
