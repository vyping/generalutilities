package com.vyping.masterlibrary.dialogs;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL_ACCEPT;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL_REFRESH;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_INFO;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_NONE;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_REFRESH_ACCEPT;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Common.Definitions;
import com.vyping.masterlibrary.Common.GeneralTools;
import com.vyping.masterlibrary.Images.MyColor;
import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.databinding.DialogBinding;

import java.util.Objects;

public class CreateDialog {

    private Context context;
    private Dialog dialog;
    public DialogBinding binding;
    private DialogInterface dialogInterface;

    private View viewGroup;

    public static String title, info, help;
    public static int prevMode;
    public static Drawable icon;
    public static String[] listParams;


    /*----- SetUp -----*/

    public CreateDialog(@NonNull Context context, int parameters) {

        setParameters(context, parameters);

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.dialog_custom, null, false);
        binding.setCreateDialog(this);

        dialog = new Dialog(context);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(binding.getRoot());
        dialog.setCancelable(false);

        setDialogView();
    }

    private void setParameters(@NonNull Context context, int parameters) {

        this.context = context;
        prevMode = BUTTONS_NONE;

        listParams = context.getResources().getStringArray(parameters);

        icon = new MyDrawable().extractFromString(context, listParams[0]);
        title = listParams[1];
        info = listParams[2];
        help = listParams[3];
    }

    private void setDialogView() {

        Drawable infoDrawable = new MyDrawable().extractFromResources(context, R.drawable.icon_info);
        Drawable backDrawable = new MyDrawable().extractFromResources(context, R.drawable.icon_back);

        Window window = Objects.requireNonNull(dialog.getWindow());
        window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        viewGroup = ((ViewGroup) window.getDecorView()).getChildAt(0);
        viewGroup.setAlpha(0f);
        viewGroup.animate().alpha(1f).setDuration(500).setListener(new AnimatorListenerAdapter() {

            @Override
            public void onAnimationEnd(Animator animation) {

                binding.DialogContainer.setAlpha(0f);
                binding.DialogContainer.setScaleX(0f);
                binding.DialogContainer.setScaleY(0f);
                binding.DialogContainer.animate().alpha(1f).scaleX(1f).scaleY(1f).setDuration(500).start();

                boolean isLandScape = new GeneralTools().isLandScape(context);
                int windowWidth = new GeneralTools().windowWidth(context);
                int windowHeight = new GeneralTools().windowHeight(context);

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

        binding.DialogInfoButton.setImageDrawable(infoDrawable);
        binding.DialogInfoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Drawable currentDrawable = binding.DialogInfoButton.getDrawable();

                if (currentDrawable == infoDrawable) {

                    binding.DialogInfoButton.setImageDrawable(backDrawable);
                    binding.TvInformation.setText(help);

                } else if (currentDrawable == backDrawable) {

                    binding.DialogInfoButton.setImageDrawable(infoDrawable);
                    binding.TvInformation.setText(info);
                }
            }

            private void DummyVoid() {}
        });
    }

    public void setDialogListener(DialogInterface dialogInterface) {

        this.dialogInterface =  dialogInterface;
    }


    /*----- Methods -----*/

    public void setModeButtons(@Definitions.ModeButtons int mode) {

        int styleNegative = R.style.DialogButtonNegative;
        int styleRefresh = R.style.DialogButtonNeutral;
        int stylePositive = R.style.DialogButtonPositive;

        if (mode == BUTTONS_NONE && prevMode != BUTTONS_NONE) {

            binding.BtnNegative.setVisibility(GONE);

            binding.BtnPositive.setVisibility(GONE);

        } else if (mode == BUTTONS_CANCEL && prevMode != BUTTONS_CANCEL) {

            binding.BtnNegative.setVisibility(GONE);

            Paris.style(binding.BtnPositive).apply(styleNegative);
            binding.BtnPositive.setVisibility(VISIBLE);
            binding.BtnPositive.setOnClickListener(NegativeClickListener);

        } else if (mode == BUTTONS_INFO && prevMode != BUTTONS_INFO) {

            binding.BtnNegative.setVisibility(GONE);

            Paris.style(binding.BtnPositive).apply(stylePositive);
            binding.BtnPositive.setVisibility(VISIBLE);
            binding.BtnPositive.setOnClickListener(PositiveClickListener);

        } else if (mode == BUTTONS_CANCEL_REFRESH && prevMode != BUTTONS_CANCEL_REFRESH) {

            Paris.style(binding.BtnNegative).apply(styleNegative);
            binding.BtnNegative.setVisibility(VISIBLE);
            binding.BtnNegative.setOnClickListener(NegativeClickListener);

            Paris.style(binding.BtnPositive).apply(styleRefresh);
            binding.BtnPositive.setVisibility(VISIBLE);
            binding.BtnPositive.setOnClickListener(RefreshClickListener);

        } else if (mode == BUTTONS_REFRESH_ACCEPT && prevMode != BUTTONS_REFRESH_ACCEPT) {

            Paris.style(binding.BtnNegative).apply(styleRefresh);
            binding.BtnNegative.setVisibility(VISIBLE);
            binding.BtnNegative.setOnClickListener(RefreshClickListener);

            Paris.style(binding.BtnPositive).apply(stylePositive);
            binding.BtnPositive.setVisibility(VISIBLE);
            binding.BtnPositive.setOnClickListener(PositiveClickListener);

        } else if (mode == BUTTONS_CANCEL_ACCEPT && prevMode != BUTTONS_CANCEL_ACCEPT) {

            Paris.style(binding.BtnNegative).apply(styleNegative);
            binding.BtnNegative.setVisibility(VISIBLE);
            binding.BtnNegative.setOnClickListener(NegativeClickListener);

            Paris.style(binding.BtnPositive).apply(stylePositive);
            binding.BtnPositive.setVisibility(VISIBLE);
            binding.BtnPositive.setOnClickListener(PositiveClickListener);
        }

        prevMode = mode;
    }

    public void showInstructions(String text) {

        //TypedValue typedValue = new TypedValue();
        //Resources.Theme theme = context.getTheme();
        //theme.resolveAttribute(R.attr.colorDialogInstructionsText, typedValue, true);
       // @ColorInt int color = typedValue.data;

        int color = new MyColor().getColor(context, R.color.Primary);

        binding.TvInformation.setText(text);
        binding.TvInformation.setTextColor(color);
    }

    public void showError(String error) {

        int color = new MyColor().getColor(context, R.color.RedMid100);

        binding.TvInformation.setText(error);
        binding.TvInformation.setTextColor(color);
    }

    public LinearLayout getContainer() {

        return binding.LlMainContainer;
    }

    public void setContainerOrientation(int orientation) {

        binding.LlMainContainer.setOrientation(orientation);
    }

    public void addCustomView(View view) {

        binding.LlMainContainer.addView(view);
    }

    public Dialog getDialog() {

        return dialog;
    }

    public void dismissDialog() {

        if (dialog.isShowing()) {

            binding.DialogContainer.setAlpha(1f);
            binding.DialogContainer.setScaleX(1f);
            binding.DialogContainer.setScaleY(1f);
            binding.DialogContainer.animate().alpha(0f).scaleX(0f).scaleY(0f).setDuration(500).setListener(new AnimatorListenerAdapter() {

                @Override
                public void onAnimationEnd(Animator animation) {

                    viewGroup.setAlpha(1f);
                    viewGroup.animate().alpha(0f).setDuration(500).setListener(new AnimatorListenerAdapter() {

                        @Override
                        public void onAnimationEnd(Animator animation) {

                            new GeneralTools().hideSoftInput(context);

                            dialog.dismiss();
                            dialog.hide();
                        }

                        private void DummyVoid() {};
                    });
                }

                private void DummyVoid() {};
            });
        }
    }


    /*----- Listeners -----*/

    private final View.OnClickListener NegativeClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            dialogInterface.NegativeClick();

            dismissDialog();
        }

        private void DummyVoid(){ }
    };

    private final View.OnClickListener RefreshClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            dialogInterface.RefreshClick();

            setModeButtons(BUTTONS_CANCEL);
        }

        private void DummyVoid(){ }
    };

    private final View.OnClickListener PositiveClickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {

            dialogInterface.PositiveClick();

            dismissDialog();
        }

        private void DummyVoid(){ }
    };


    /*----- Return -----*/

    public abstract interface DialogInterface {

        void NegativeClick();
        void RefreshClick();
        void PositiveClick();
    }
}
