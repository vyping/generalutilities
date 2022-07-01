package com.vyping.masterlibrary.dialogs;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.ColorInt;
import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.vyping.masterlibrary.Common.MyDisplay;
import com.vyping.masterlibrary.Common.MyGeneralTools;
import com.vyping.masterlibrary.Images.MyColor;
import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.models.DialogParams;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.databinding.DialogBinding;
import com.vyping.masterlibrary.views.MyButton;
import com.vyping.masterlibrary.views.MyImageButton;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Objects;

public class CreateDialog extends DialogParams {

    public DialogBinding binding;
    private Dialog dialog;

    private View viewGroup;

    public static final int DIALOG_NORMAL = 0, DIALOG_STEP_INITIAL = 1, DIALOG_STEP_INTERMEDIATE = 2, DIALOG_STEP_FINAL = 3, DIALOG_CUSTOM = 4;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({DIALOG_NORMAL, DIALOG_STEP_INITIAL, DIALOG_STEP_INTERMEDIATE, DIALOG_STEP_FINAL, DIALOG_CUSTOM})
    public @interface DialogMode {
    }

    public static final int BUTTON_LEFT = 0, BUTTON_CENTER = 1, BUTTON_RIGHT = 2;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({BUTTON_LEFT, BUTTON_CENTER, BUTTON_RIGHT})
    public @interface ModeButton {
    }


    /*----- SetUp -----*/

    public CreateDialog(@NonNull Context context, int arrayParameters) {

        super(context, arrayParameters);

        SetParameters();
        SetDialog();
        SetDialogViews();
    }

    public CreateDialog(@NonNull Context context, @DialogMode int dialogMode, int arrayParameters) {

        super(context, dialogMode, arrayParameters);

        SetParameters();
        SetDialog();
        SetDialogViews();
    }

    public CreateDialog(@NonNull Context context, Drawable icon, String title, String description, String help, String error, String success) {

        super(context, icon, title, description, help, error, success);

        SetParameters();
        SetDialog();
        SetDialogViews();
    }

    public CreateDialog(@NonNull Context context, @DialogMode int dialogMode, Drawable icon, String title, String description, String help, String error, String success) {

        super(context, dialogMode, icon, title, description, help, error, success);

        SetParameters();
        SetDialog();
        SetDialogViews();
    }

    public CreateDialog(@NonNull Context context, int icon, String title, String description, String help, String error, String success) {

        super(context, icon, title, description, help, error, success);

        SetParameters();
        SetDialog();
        SetDialogViews();
    }

    public CreateDialog(@NonNull Context context, @DialogMode int dialogMode, int icon, String title, String description, String help, String error, String success) {

        super(context, dialogMode, icon, title, description, help, error, success);

        SetParameters();
        SetDialog();
        SetDialogViews();
    }

    private void SetParameters() {

        binding = DataBindingUtil.inflate(LayoutInflater.from(CONTEXT), R.layout.dialog_custom, null, false);
        binding.setCreateDialog(this);
        binding.setContext(CONTEXT);
    }

    private void SetDialog() {

        dialog = new Dialog(CONTEXT);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(binding.getRoot());
        dialog.setCancelable(false);
    }

    private void SetDialogViews() {

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

                boolean isLandScape = new MyDisplay().isLandScape(CONTEXT);
                int windowWidth = new MyDisplay().displayWidth(CONTEXT);
                int windowHeight = new MyDisplay().displayHeight(CONTEXT);

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

            private void DummyVoid() {
            }

            ;
        });
        viewGroup.animate().start();
    }


    /*----- SetUp ModelMethods -----*/

    public void ReloadDialog() {

        SetIconImage();
        SetTitleText();
        SetHelpInfo();
        setDescriptionInfo();
    }

    public void SetIconImage() {

        binding.IvIcon.setImageDrawable(ICON);
    }

    public void SetTitleText() {

        binding.TvTextTitle.setText(TITLE);
    }

    public void SetHelpInfo() {

        Drawable infoDrawable = new MyDrawable().extractFromResources(CONTEXT, R.drawable.icon_info);
        Drawable backDrawable = new MyDrawable().extractFromResources(CONTEXT, R.drawable.icon_back);

        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = CONTEXT.getTheme();
        theme.resolveAttribute(R.attr.colorDialogDescriptionText, typedValue, true);
        @ColorInt int color = typedValue.data;

        binding.DialogInfoButton.setImageDrawable(infoDrawable);
        binding.DialogInfoButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Drawable currentDrawable = binding.DialogInfoButton.getDrawable();

                if (currentDrawable == infoDrawable) {

                    binding.DialogInfoButton.setImageDrawable(backDrawable);
                    binding.TvDescription.setText(HELP);

                } else if (currentDrawable == backDrawable) {

                    binding.DialogInfoButton.setImageDrawable(infoDrawable);
                    binding.TvDescription.setText(DESCRIPTION);
                }

                binding.TvDescription.setTextColor(color);
            }

            private void DummyVoid() {
            }
        });
    }

    public void setDescriptionInfo() {

        TypedValue typedValue = new TypedValue();
        Resources.Theme theme = CONTEXT.getTheme();
        theme.resolveAttribute(R.attr.colorDialogDescriptionText, typedValue, true);
        @ColorInt int color = typedValue.data;

        binding.TvDescription.setText(DESCRIPTION);
        binding.TvDescription.setTextColor(color);
    }

    public void SetErrorMessage() {

        int color = new MyColor().extractFromResources(CONTEXT, R.color.RedMid100);

        binding.TvDescription.setText(ERROR);
        binding.TvDescription.setTextColor(color);
    }

    public void SetErrorMessage(String error) {

        int color = new MyColor().extractFromResources(CONTEXT, R.color.RedMid100);

        binding.TvDescription.setText(error);
        binding.TvDescription.setTextColor(color);
    }

    public ImageButton SetButtonCancel(Interfase interfase) {

        int attr = R.attr.dialogButtonCancel;
        int style = R.style.DialogButtonCancel;

        ImageButton Ib_Cancel = new MyImageButton().createByAttributes(CONTEXT, attr, style, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                evaluateDissmiss(interfase);
            }

            private void DummyVoid() {
            }
        });
        setButtonPosition(Ib_Cancel, BUTTON_LEFT);

        return Ib_Cancel;
    }

    public ImageButton SetButtonCancel(@ModeButton int position, Interfase interfase) {

        int attr = R.attr.dialogButtonCancel;
        int style = R.style.DialogButtonCancel;

        ImageButton Ib_Cancel = new MyImageButton().createByAttributes(CONTEXT, attr, style, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                evaluateDissmiss(interfase);
            }

            private void DummyVoid() {
            }
        });
        setButtonPosition(Ib_Cancel, position);

        return Ib_Cancel;
    }

    public ImageButton SetButtonBack(Interfase interfase) {

        int attr = R.attr.dialogButtonBack;
        int style = R.style.DialogButtonBack;

        ImageButton Ib_Back = new MyImageButton().createByAttributes(CONTEXT, attr, style, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                evaluateDissmiss(interfase);
            }

            private void DummyVoid() {
            }
        });
        setButtonPosition(Ib_Back, BUTTON_LEFT);

        return Ib_Back;
    }

    public ImageButton SetButtonBack(@ModeButton int position, Interfase interfase) {

        int attr = R.attr.dialogButtonBack;
        int style = R.style.DialogButtonBack;

        ImageButton Ib_Back = new MyImageButton().createByAttributes(CONTEXT, attr, style, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                evaluateDissmiss(interfase);
            }

            private void DummyVoid() {
            }
        });
        setButtonPosition(Ib_Back, position);

        return Ib_Back;
    }

    public ImageButton SetButtonNeutral(Interfase interfase) {

        int attr = R.attr.dialogButtonNeutral;
        int style = R.style.DialogButtonNeutral;

        ImageButton Ib_Neutral = new MyImageButton().createByAttributes(CONTEXT, attr, style, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                evaluateDissmiss(interfase);
            }

            private void DummyVoid() {
            }
        });
        setButtonPosition(Ib_Neutral, BUTTON_CENTER);

        return Ib_Neutral;
    }

    public ImageButton SetButtonNeutral(@ModeButton int position, Interfase interfase) {

        int attr = R.attr.dialogButtonNeutral;
        int style = R.style.DialogButtonNeutral;

        ImageButton Ib_Neutral = new MyImageButton().createByAttributes(CONTEXT, attr, style, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                evaluateDissmiss(interfase);
            }

            private void DummyVoid() {
            }
        });
        setButtonPosition(Ib_Neutral, position);

        return Ib_Neutral;
    }

    public ImageButton SetButtonNext(Interfase interfase) {

        int attr = R.attr.dialogButtonNext;
        int style = R.style.DialogButtonNext;

        ImageButton Ib_Next = new MyImageButton().createByAttributes(CONTEXT, attr, style, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                evaluateDissmiss(interfase);
            }

            private void DummyVoid() {
            }
        });
        setButtonPosition(Ib_Next, BUTTON_RIGHT);

        return Ib_Next;
    }

    public ImageButton SetButtonNext(@ModeButton int position, Interfase interfase) {

        int attr = R.attr.dialogButtonNext;
        int style = R.style.DialogButtonNext;

        ImageButton Ib_Next = new MyImageButton().createByAttributes(CONTEXT, attr, style, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                evaluateDissmiss(interfase);
            }

            private void DummyVoid() {
            }
        });
        setButtonPosition(Ib_Next, position);

        return Ib_Next;
    }

    public ImageButton SetButtonConfirm(Interfase interfase) {

        int attr = R.attr.dialogButtonConfirm;
        int style = R.style.DialogButtonConfirm;

        ImageButton Ib_Confirm = new MyImageButton().createByAttributes(CONTEXT, attr, style, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                evaluateDissmiss(interfase);
            }

            private void DummyVoid() {
            }
        });
        setButtonPosition(Ib_Confirm, BUTTON_RIGHT);

        return Ib_Confirm;
    }

    public ImageButton SetButtonConfirm(@ModeButton int position, Interfase interfase) {

        int attr = R.attr.dialogButtonConfirm;
        int style = R.style.DialogButtonConfirm;

        ImageButton Ib_Confirm = new MyImageButton().createByAttributes(CONTEXT, attr, style, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                evaluateDissmiss(interfase);
            }

            private void DummyVoid() {
            }
        });
        setButtonPosition(Ib_Confirm, position);

        return Ib_Confirm;
    }

    public ImageButton SetImageButtonCustom(@ModeButton int position, int attr, int style, Interfase interfase) {

        ImageButton Ib_Custom = new MyImageButton().createByAttributes(CONTEXT, attr, style, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                evaluateDissmiss(interfase);
            }

            private void DummyVoid() {
            }
        });
        setButtonPosition(Ib_Custom, position);

        return Ib_Custom;
    }

    public ImageButton SetImageButtonCustom(@ModeButton int position, int attr, int style, Drawable drawable, Interfase interfase) {

        ImageButton Ib_Custom = new MyImageButton().createByAttributes(CONTEXT, attr, style, drawable, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                evaluateDissmiss(interfase);
            }

            private void DummyVoid() {
            }
        });
        setButtonPosition(Ib_Custom, position);

        return Ib_Custom;
    }

    public ImageButton SetImageButtonCustom(@ModeButton int position, int attr, int style, int drawResource, Interfase interfase) {

        ImageButton Ib_Custom = new MyImageButton().createByAttributes(CONTEXT, attr, style, drawResource, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                evaluateDissmiss(interfase);
            }

            private void DummyVoid() {
            }
        });
        setButtonPosition(Ib_Custom, position);

        return Ib_Custom;
    }

    public Button SetTextButtonCustom(@ModeButton int position, int attr, int style, Interfase interfase) {

        Button Btn_Custom = new MyButton().createByAttributes(CONTEXT, attr, style, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                evaluateDissmiss(interfase);
            }

            private void DummyVoid() {
            }
        });
        setButtonPosition(Btn_Custom, position);

        return Btn_Custom;
    }

    public Button SetTextButtonCustom(@ModeButton int position, int attr, int style, String text, Interfase interfase) {

        Button Btn_Custom = new MyButton().createByAttributes(CONTEXT, attr, style, text, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                evaluateDissmiss(interfase);
            }

            private void DummyVoid() {
            }
        });
        setButtonPosition(Btn_Custom, position);

        return Btn_Custom;
    }


    /*----- Other ModelMethods -----*/

    public Dialog GetDialog() {

        return dialog;
    }

    public LinearLayout GetContainer() {

        return binding.LlMainContainer;
    }

    public void SetContainerOrientation(int orientation) {

        binding.LlMainContainer.setOrientation(orientation);
    }

    public void AddCustomView(View view) {

        binding.LlMainContainer.addView(view);
    }

    public void DismissDialog() {

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

                            new MyGeneralTools().hideSoftInput(CONTEXT);

                            if (dialog != null) {

                                if (dialog.isShowing()) {

                                    dialog.dismiss();
                                    dialog.hide();
                                }
                            }
                        }

                        private void DummyVoid() {
                        }

                        ;
                    });
                }

                private void DummyVoid() {
                }

                ;
            });
        }
    }


    /*----- Tools -----*/

    private void setButtonPosition(Button button, @ModeButton int position) {

        if (position == BUTTON_LEFT) {

            binding.LlLeft.addView(button);

        } else if (position == BUTTON_CENTER) {

            binding.LlCenter.addView(button);

        } else if (position == BUTTON_RIGHT) {

            binding.LlRight.addView(button);
        }
    }

    private void setButtonPosition(ImageButton imageButton, @ModeButton int position) {

        if (position == BUTTON_LEFT) {

            binding.LlLeft.addView(imageButton);

        } else if (position == BUTTON_CENTER) {

            binding.LlCenter.addView(imageButton);

        } else if (position == BUTTON_RIGHT) {

            binding.LlRight.addView(imageButton);
        }
    }

    public void removeButtons() {

        binding.LlLeft.removeAllViews();
        binding.LlCenter.removeAllViews();
        binding.LlRight.removeAllViews();

    }

    private void evaluateDissmiss(@NonNull Interfase interfase) {

        boolean dissmis = interfase.ClickButton();

        if (dissmis) {

            DismissDialog();
        }
    }


    // ----- Interface ----- //

    public interface Interfase {

        boolean ClickButton();
    }
}
