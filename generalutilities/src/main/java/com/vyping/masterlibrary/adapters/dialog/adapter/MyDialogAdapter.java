package com.vyping.masterlibrary.adapters.dialog.adapter;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.vyping.masterlibrary.Common.MyDisplay;
import com.vyping.masterlibrary.Images.MyColor;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.adapters.dialog.binding.DialogBinder;

import java.util.Objects;

public class MyDialogAdapter<T, K> {

    public Context context;
    public Dialog dialog;
    public ViewDataBinding binding;
    public DialogBinder<T, K> dialogBinder;

    /*----- SetUp -----*/

    public MyDialogAdapter(@NonNull Context context, @NonNull DialogBinder<T, K> dialogBinder) {

        this.context = context;
        this.dialogBinder = dialogBinder;

        this.binding = DataBindingUtil.inflate(LayoutInflater.from(context), dialogBinder.getLayoutRes(), null, false);
        this.dialogBinder.setViewBinding(binding);
    }

    public void setVariable(int nameInterfase, K interfase) {

        this.dialogBinder.setVariable(nameInterfase);
        this.dialogBinder.setInterfase(interfase);
    }

    public void show() {

        this.dialog = new Dialog(context);
        this.dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.dialog.setContentView(binding.getRoot());
        this.dialog.setCancelable(true);

        this.dialogBinder.setDialog(dialog);

        this.binding.setVariable(dialogBinder.getVariable(), dialogBinder);
        this.binding.executePendingBindings();

        setDialogViews();
    }

    private void setDialogViews() {

        int color = new MyColor().extractFromResources(context, R.color.BlackUltraDark080);

        Window window = Objects.requireNonNull(dialog.getWindow());
        window.setBackgroundDrawable(new ColorDrawable(color));

        View viewGroup = ((ViewGroup) window.getDecorView()).getChildAt(0);
        viewGroup.setAlpha(0f);
        viewGroup.animate().alpha(1f).setDuration(500).setListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {

                boolean isLandScape = new MyDisplay().isLandScape(context);
                int windowWidth = new MyDisplay().displayWidth(context);
                int windowHeight = new MyDisplay().displayHeight(context);

                WindowManager.LayoutParams layoutParams = window.getAttributes();
                layoutParams.height = windowHeight;
                layoutParams.gravity = Gravity.CENTER;

                if (isLandScape) {

                    layoutParams.width = 640;

                } else {

                    layoutParams.width = windowWidth;
                }

                dialog.getWindow().setAttributes(layoutParams);
                dialog.show();
            }

            private void DummyVoid() {};
        });
        viewGroup.animate().start();
    }

    public void dismiss() {

        if (dialog.isShowing()) {

            dialog.dismiss();
            dialog.hide();
        }
    }
}
