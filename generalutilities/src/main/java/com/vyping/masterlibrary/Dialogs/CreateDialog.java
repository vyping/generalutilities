package com.vyping.masterlibrary.Dialogs;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_BOTH;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_NONE;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_SINGLE;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_THREE;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Common.Definitions;
import com.vyping.masterlibrary.Common.GeneralTools;
import com.vyping.masterlibrary.R;

import java.util.Objects;

public abstract class CreateDialog {

    private final Context context;
    private static Dialog dialog;

    private final LinearLayout Ll_Container;
    private final TextView Tv_IconTitle, Tv_TextTitle, Tv_Info;
    private final Button Btn_Info, Btn_Negative, Btn_Positive;

    private static String IconTitle, TextTitle, IconInfo, IconBack, Information, Description, Positive, Refresh, Negative;
    private static int prevMode;


    /*----- SetUp -----*/

    public CreateDialog(@NonNull Context context, int parameters, boolean wrap) {

        this.context = context;

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        Objects.requireNonNull(dialog.getWindow()).setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_custom);
        dialog.setCancelable(false);

        setViewAttributes(context, dialog, wrap);

        Tv_IconTitle = dialog.findViewById(R.id.Tv_Custom_IconTitle);
        Tv_TextTitle = dialog.findViewById(R.id.Tv_Custom_TextTitle);
        Btn_Info = dialog.findViewById(R.id.Btn_Custom_IconInfo);
        Tv_Info = dialog.findViewById(R.id.Tv_Custom_Information);
        Ll_Container = dialog.findViewById(R.id.Ll_Custom_MainContainer);
        Btn_Negative = dialog.findViewById(R.id.Btn_Custom_Negative);
        Btn_Positive = dialog.findViewById(R.id.Btn_Custom_Positive);

        SetDialog(dialog);
        setLabels(parameters);
    }

    public void setViewAttributes(Context context, @NonNull Dialog dialog, boolean wrap) {

        View viewGroup = ((ViewGroup) dialog.getWindow().getDecorView()).getChildAt(0);
        viewGroup.setAlpha(0f);
        viewGroup.setScaleX(0f);
        viewGroup.setScaleY(0f);
        viewGroup.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(500).start();

        boolean isLandScape = new GeneralTools().isLandScape(context);
        int windowWidth = new GeneralTools().windowWidth(context);
        int windowHeight = new GeneralTools().windowHeight(context);

        WindowManager.LayoutParams layoutParams = Objects.requireNonNull(dialog.getWindow()).getAttributes();

        layoutParams.gravity = Gravity.CENTER;

        if (isLandScape) {

            layoutParams.width = 640;

        } else {

            layoutParams.width = windowWidth - 80;
        }

        if (wrap) {

            layoutParams.height = WindowManager.LayoutParams.WRAP_CONTENT;

        } else {

            layoutParams.height = windowHeight- 80;
        }

        dialog.getWindow().setAttributes(layoutParams);
    }

    public void setLabels(int parameters) {

        String[] ListParams = context.getResources().getStringArray(parameters);
        IconTitle = ListParams[0];
        TextTitle = ListParams[1];
        Information = ListParams[2];
        Description = ListParams[3];
        Negative = ListParams[4];
        Positive = ListParams[5];

        Refresh = context.getResources().getString(R.string.Refresh);

        IconInfo = context.getResources().getString(R.string.iconinfo);
        IconBack = context.getResources().getString(R.string.iconback);

        Tv_IconTitle.setText(IconTitle);
        Tv_TextTitle.setText(TextTitle);
        Btn_Info.setText(IconInfo);
        Tv_Info.setText(Information);
        Btn_Negative.setText(Negative);
        Btn_Positive.setText(Positive);

        Btn_Info.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                String icon = String.valueOf(Btn_Info.getText());

                if (icon.equals(IconInfo)) {

                    Btn_Info.setText(IconBack);
                    Tv_Info.setText(Description);
                    Ll_Container.setVisibility(GONE);

                } else {

                    Btn_Info.setText(IconInfo);
                    Tv_Info.setText(Information);
                    Ll_Container.setVisibility(VISIBLE);
                }
            }

            private void DummyVoid() {}
        });
    }


    /*----- Methods -----*/

    public void setModeButtons(@Definitions.ModeButtons int mode) {

        if (mode == BUTTONS_NONE && prevMode != BUTTONS_NONE) {

            Btn_Negative.setVisibility(GONE);
            Btn_Positive.setVisibility(GONE);

        } else if (mode == BUTTONS_SINGLE && prevMode != BUTTONS_SINGLE) {

            Btn_Positive.setText(Negative);

            Btn_Negative.setVisibility(GONE);
            Btn_Positive.setVisibility(VISIBLE);

            Btn_Positive.setOnClickListener(NegativeClickListener);

        } else if (mode == BUTTONS_BOTH && prevMode != BUTTONS_BOTH) {

            Btn_Negative.setText(Negative);
            Btn_Positive.setText(Positive);

            Btn_Negative.setVisibility(VISIBLE);
            Btn_Positive.setVisibility(VISIBLE);

            Btn_Negative.setOnClickListener(NegativeClickListener);
            Btn_Positive.setOnClickListener(PositiveClickListener);

        } else if (mode == BUTTONS_THREE && prevMode != BUTTONS_THREE) {

            Btn_Negative.setText(Refresh);
            Btn_Positive.setText(Positive);

            Btn_Negative.setVisibility(VISIBLE);
            Btn_Positive.setVisibility(VISIBLE);

            Btn_Negative.setOnClickListener(RefreshClickListener);
            Btn_Positive.setOnClickListener(PositiveClickListener);
        }

        prevMode = mode;
    }

    public void dismissDialog() {

        View viewGroup = ((ViewGroup) Objects.requireNonNull(dialog.getWindow()).getDecorView()).getChildAt(0);
        viewGroup.setAlpha(1f);
        viewGroup.setScaleX(1f);
        viewGroup.setScaleY(1f);

        viewGroup.animate().alpha(0f).scaleX(0f).scaleY(0f).setDuration(500).setListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {

                new GeneralTools().hideSoftInput(context);

                viewGroup.setVisibility(GONE);
                dialog.dismiss();
            }

            private void DummyVoid() {}
        });
    }


    /*----- Listeners -----*/

    private final View.OnClickListener NegativeClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            dismissDialog();
        }

        private void DummyVoid(){ }
    };

    private final View.OnClickListener RefreshClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            RefreshClick();
        }

        private void DummyVoid(){ }
    };

    private final View.OnClickListener PositiveClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            PositiveClick();
            dismissDialog();
        }

        private void DummyVoid(){ }
    };


    /*----- Return -----*/

    protected abstract void SetDialog(Dialog dialog);
    protected abstract void RefreshClick();
    protected abstract void PositiveClick();
}
