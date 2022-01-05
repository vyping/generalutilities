package com.vyping.masterlibrary.PopUps;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Common.Animations;
import com.vyping.masterlibrary.Common.Views;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.Views.SetButton;
import com.vyping.masterlibrary.Views.SetLinearLayout;

import java.util.Objects;

public class PopUpBubbles {

    private final Context context;
    private static PopupWindow popup;

    private final LinearLayout Ll_Parent;


    //----- Setup - Section-----//

    @SuppressLint("InflateParams")
    public PopUpBubbles(@NonNull Context thisContext, @NonNull final View anchorView) {

        context = thisContext;
        DisplayMetrics metrics = Resources.getSystem().getDisplayMetrics();
        int width = metrics.widthPixels;
        int heightView = anchorView.getHeight();

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View inflate = Objects.requireNonNull(inflater).inflate(R.layout.popup_bubbles, null);
        Ll_Parent = inflate.findViewById(R.id.Ll_PopUp_Bubbles);

        popup = new PopupWindow(context);
        popup.setContentView(inflate);
        popup.setWidth(width);
        popup.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        popup.showAsDropDown(anchorView, 0, - 68 - heightView/2);
        popup.setOutsideTouchable(true);
        popup.setFocusable(false);
        popup.update();
    }


    //----- Proccess Tools - Section-----//

    public void setButtonStart(String icon, ClickInterface ClickInterface) {

        new SetLinearLayout(context, Ll_Parent, R.style.PopupContainerLeft) {

            @Override
            protected void getLayoutView(LinearLayout layout) {

                new SetButton(context, layout, icon, R.style.PopupButton) {

                    @Override
                    protected void getButtonView(Button button) { }

                    @Override
                    protected void clickOnButton(String text) {

                        ClickInterface.ButtonClick();
                        popUpDismiss();
                    }
                };
                new Animations().HorizontalTranslation(layout, 200, -80, 0);
            }

            @Override
            protected void clickOnLayout() {

                ClickInterface.ButtonClick();
                popUpDismiss();
            }
        };
        new Views().setFillSpace(context, Ll_Parent);
    }

    public void setButtonMid(String icon, ClickInterface ClickInterface) {

        new Views().setFillSpace(context, Ll_Parent);
        new SetLinearLayout(context, Ll_Parent, R.style.PopupContainerCenter) {

           @Override
            protected void getLayoutView(LinearLayout layout) {

               new SetButton(context, layout, icon, R.style.PopupButton) {

                   @Override
                   protected void getButtonView(Button button) { }

                   @Override
                   protected void clickOnButton(String text) {

                             ClickInterface.ButtonClick();
                       popUpDismiss();
                   }
               };
               new Animations().ScaleAmpliate(layout,200);
           }

            @Override
            protected void clickOnLayout() {

                      ClickInterface.ButtonClick();
                popUpDismiss();
            }
        };
        new Views().setFillSpace(context, Ll_Parent);
    }

    public void setButtonEnd(@NonNull String icon, ClickInterface ClickInterface) {

        new Views().setFillSpace(context, Ll_Parent);
        new SetLinearLayout(context, Ll_Parent, R.style.PopupContainerRight) {

            @Override
            protected void getLayoutView(LinearLayout layout) {

                new SetButton(context, layout, icon, R.style.PopupButton) {

                    @Override
                    protected void getButtonView(Button button) { }

                    @Override
                    protected void clickOnButton(String text) {

                              ClickInterface.ButtonClick();
                        popUpDismiss();
                    }
                };
                new Animations().HorizontalTranslation(layout, 200, 80, 0);
            }

            @Override
            protected void clickOnLayout() {

                ClickInterface.ButtonClick();
                popUpDismiss();
            }
        };
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
        void DummyVoid();
    }
}
