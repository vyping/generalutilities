package com.vyping.masterlibrary.dialogs;

import static android.view.View.VISIBLE;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.vyping.masterlibrary.Animations.Animations;
import com.vyping.masterlibrary.Common.GeneralTools;
import com.vyping.masterlibrary.R;

import java.util.Objects;

public abstract class DialogProgress {

    private static Dialog dialog;

    public void showDialog(Context context, String Text) {

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_progress);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT;
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT;

        LinearLayout Ll_Loading = dialog.findViewById(R.id.Ll_Usr_Loading);
        ImageView Iv_Loading = dialog.findViewById(R.id.Iv_Usr_Loading);
        TextView Tv_Loading = dialog.findViewById(R.id.Tv_Usr_Loading);

        Ll_Loading.setVisibility(VISIBLE);
        Tv_Loading.setText(Text);
        Iv_Loading.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.progresbar_van));

        float startPoint = -76;
        float endPoint = new GeneralTools().windowWidth(context) - 38;

        animationForward(context, Iv_Loading, startPoint, endPoint);

        dialog.setCancelable(false);
        dialog.show();
    }

    private void animationForward(Context context, @NonNull ImageView Iv_Loading, float startPoint, float endPoint) {

        Iv_Loading.setX(startPoint);
        Iv_Loading.setScaleX(1);
        Iv_Loading.animate().translationX(endPoint).setDuration(2000).setListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) { }

            @Override
            public void onAnimationEnd(Animator animation) {

                animationBackward(context, Iv_Loading, endPoint, startPoint);
            }

            @Override
            public void onAnimationCancel(Animator animation) { }

            @Override
            public void onAnimationRepeat(Animator animation) { }
        }).start();
    }

    private void animationBackward(Context context, @NonNull ImageView Iv_Loading, float endPoint, float startPoint) {

        Iv_Loading.setX(endPoint);
        Iv_Loading.setScaleX(-1);
        Iv_Loading.animate().translationX(startPoint).setDuration(2000).setListener(new Animator.AnimatorListener() {

            @Override
            public void onAnimationStart(Animator animation) { }

            @Override
            public void onAnimationEnd(Animator animation) {

                animationForward(context, Iv_Loading, startPoint, endPoint);
            }

            @Override
            public void onAnimationCancel(Animator animation) { }

            @Override
            public void onAnimationRepeat(Animator animation) { }
        }).start();
    }

    public void updateDialog(Context context, String Text) {

        if (dialog != null) {

            if (dialog.isShowing() ) {

               TextView Tv_Loading = dialog.findViewById(R.id.Tv_Usr_Loading);
               Tv_Loading.setText(Text);

            } else {

                showDialog(context, Text);
            }

        } else {

            showDialog(context, Text);
        }
    }

    public void hideDialog() {

        if (dialog != null) {

            if (dialog.isShowing() ) {

                new Animations().stopAnimation();

                dialog.dismiss();
                dialog.cancel();
            }
        }
    }
}
