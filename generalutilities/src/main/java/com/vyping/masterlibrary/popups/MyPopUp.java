package com.vyping.masterlibrary.popups;

import android.annotation.SuppressLint;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.databinding.PopUpCustomBinding;

public class MyPopUp {

    public PopUpCustomBinding binding;
    public static PopupWindow popup;

    public View anchorView;


    //----- Setup - Section-----//

    @SuppressLint("InflateParams")
    public MyPopUp(@NonNull final View anchorView) {

        SetParameters(anchorView);
        SetPopUp();
    }

    private void SetParameters(@NonNull View anchorView) {

        this.anchorView = anchorView;

        binding = DataBindingUtil.inflate(LayoutInflater.from(anchorView.getContext()), R.layout.popup_custom, null, false);
        binding.setCreatePopUp(this);
    }

    private void SetPopUp() {

        popup = new PopupWindow(anchorView.getContext());
        popup.setContentView(binding.getRoot());
        popup.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        popup.showAsDropDown(anchorView, 0, -anchorView.getHeight() + popup.getHeight(), Gravity.TOP);
        popup.showAsDropDown(anchorView, 0, +100);

        popup.setOutsideTouchable(true);
        popup.setFocusable(false);
        popup.update();
    }


    //----- Proccess Tools - Section-----//

    public void setContainerOrientation(int orientation) {

        binding.LlPopContainer.setOrientation(orientation);
    }

    public void AddCustomView(View view) {

        binding.LlPopContainer.addView(view);
    }

    public void popUpDismiss() {

        if (popup != null) {

            if (popup.isShowing()) {

                popup.dismiss();
            }
        }
    }
}
