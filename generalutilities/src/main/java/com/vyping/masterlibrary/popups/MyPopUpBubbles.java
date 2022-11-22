package com.vyping.masterlibrary.popups;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Animations.MyAnimation;
import com.vyping.masterlibrary.Common.MyDisplay;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.views.SetButton;
import com.vyping.masterlibrary.views.SetImageButton;
import com.vyping.masterlibrary.views.SetLinearLayout;
import com.vyping.masterlibrary.views.Spaces;

import java.util.Objects;

public class MyPopUpBubbles {

    private final Context context;
    private static PopupWindow popup;

    private final LinearLayout Ll_Parent;


    //----- Setup - Section-----//

    @SuppressLint("InflateParams")
    public MyPopUpBubbles(@NonNull Context thisContext, @NonNull final View anchorView) {

        context = thisContext;
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
       // int width = metrics.widthPixels;
        int width = anchorView.getWidth();
        int heightView = anchorView.getHeight();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = Objects.requireNonNull(inflater).inflate(R.layout.popup_bubbles, null);
        Ll_Parent = inflate.findViewById(R.id.Ll_PopUp_Bubbles);

        popup = new PopupWindow(context);
        popup.setContentView(inflate);

        inflate.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
        int he=inflate.getMeasuredHeight();

        float heighPopUp = new MyDisplay().dpsToPxs(context, 52);
        int yoff = (int) (-heightView + ((heightView-heighPopUp)/2));

        popup.setWidth(width);
        popup.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        popup.showAsDropDown(anchorView, 0, yoff);
        popup.setOutsideTouchable(true);
        popup.setFocusable(false);
        popup.update();
    }


    //----- Proccess Tools - Section-----//

    public MyPopUpBubbles setButtonStart(int icon, ClickInterface ClickInterface) {

        new SetLinearLayout(context, Ll_Parent, R.style.PopupContainerLeft) {

            @Override
            protected void getLayoutView(LinearLayout layout) {

                new SetImageButton(context, layout, icon, R.style.PopupButton) {

                    @Override
                    protected void getButtonView(ImageButton imageButton) {}

                    @Override
                    protected void clickOnButton(View imageButton) {

                        ClickInterface.ButtonClick();
                        popUpDismiss();
                    }
                };

                new MyAnimation().HorizontalTranslation(layout, 200, -80, 0);
            }

            @Override
            protected void clickOnLayout() {

                ClickInterface.ButtonClick();
                popUpDismiss();
            }
        };
        new Spaces().setSpace(context, Ll_Parent);

        return this;
    }

    public MyPopUpBubbles setButtonMid(int icon, ClickInterface ClickInterface) {

        new Spaces().setSpace(context, Ll_Parent);
        new SetLinearLayout(context, Ll_Parent, R.style.PopupContainerCenter) {

            @Override
            protected void getLayoutView(LinearLayout layout) {

                new SetImageButton(context, layout, icon, R.style.PopupButton) {

                    @Override
                    protected void getButtonView(ImageButton imageButton) {}

                    @Override
                    protected void clickOnButton(View imageButton) {

                        ClickInterface.ButtonClick();
                        popUpDismiss();
                    }
                };
                new MyAnimation().ScaleAmpliate(layout, 200);
            }

            @Override
            protected void clickOnLayout() {

                ClickInterface.ButtonClick();
                popUpDismiss();
            }
        };
        new Spaces().setSpace(context, Ll_Parent);

        return this;
    }

    public MyPopUpBubbles setButtonEnd(int icon, ClickInterface ClickInterface) {

        new Spaces().setSpace(context, Ll_Parent);
        new SetLinearLayout(context, Ll_Parent, R.style.PopupContainerRight) {

            @Override
            protected void getLayoutView(LinearLayout layout) {

                new SetImageButton(context, layout, icon, R.style.PopupButton) {

                    @Override
                    protected void getButtonView(ImageButton imageButton) {}

                    @Override
                    protected void clickOnButton(View imageButton) {

                        ClickInterface.ButtonClick();
                        popUpDismiss();
                    }
                };
                new MyAnimation().HorizontalTranslation(layout, 200, 80, 0);
            }

            @Override
            protected void clickOnLayout() {

                ClickInterface.ButtonClick();
                popUpDismiss();
            }
        };

        return this;
    }

    public void popUpDismiss() {

        if (popup != null) {

            if (popup.isShowing()) {

                popup.dismiss();
            }
        }
    }


    //----- Interface - Section-----//

    public interface ClickInterface {

        void ButtonClick();
    }
}
