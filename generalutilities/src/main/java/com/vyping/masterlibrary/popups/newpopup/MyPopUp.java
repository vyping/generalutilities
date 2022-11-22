package com.vyping.masterlibrary.popups.newpopup;

import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.MyDisplay;

public abstract class MyPopUp<T> {

    public PopupWindow popup;
    private final View anchorView;
    private final ViewDataBinding binding;


    //----- Setup -----//

    public MyPopUp(@NonNull final View anchorView, int layout, int bindingVariable) {

        this.anchorView = anchorView;
        this.binding = DataBindingUtil.inflate(LayoutInflater.from(anchorView.getContext()), layout, null, false);
        this.binding.setVariable(bindingVariable, binding);

        setViews(binding);
    }

    // ----- Methods ----- //

    public MyPopUp<T> binding(int bindingVariable) {

        this.binding.setVariable(bindingVariable, binding);
        this.binding.executePendingBindings();

        return this;
    }

    public MyPopUp<T> setVariable(int modelVariable, T item) {

        this.binding.setVariable(modelVariable, item);
        this.binding.executePendingBindings();

        return this;
    }

    public Build<T> build() {

        return new Build<>(anchorView, binding);
    }

    public Build<T> buildTop() {

        return new Build<>(anchorView, binding, 0);
    }

    public Build<T> buildBottom() {

        return new Build<>(anchorView, binding);
    }

    public void buildOnTop(int width, int height) {

        binding.getRoot().measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        int xOff = (anchorView.getWidth() - width)/2;
        int yOff = - height - anchorView.getHeight() - 8;

        popup = new PopupWindow(anchorView.getContext());
        popup.setContentView(binding.getRoot());
        popup.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        popup.setWidth(width);
        popup.setHeight(height);
        popup.showAsDropDown(anchorView, xOff, yOff);
        popup.setOutsideTouchable(true);
        popup.setFocusable(false);
        popup.update();
    }

    public void popUpDismiss() {

        if (popup != null) {

            if (popup.isShowing()) {

                popup.dismiss();
            }
        }
    }


    // ----- SubClass ----- //

    public static class Build<T> {

        public ViewDataBinding binding;
        public PopupWindow popup;


        //----- Setup -----//

        public Build(@NonNull final View anchorView, @NonNull ViewDataBinding binding) {

            this.binding = binding;

            int width = new MyDisplay().displayWidth(anchorView.getContext());  //*

            popup = new PopupWindow(anchorView.getContext());
            popup.setContentView(binding.getRoot());
         
            binding.getRoot().measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            int he=binding.getRoot().getMeasuredHeight();
         
            popup.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            popup.setWidth(anchorView.getWidth()); //*
            popup.showAsDropDown(anchorView, 0, -anchorView.getHeight() + popup.getHeight(), Gravity.TOP);
            popup.showAsDropDown(anchorView, 0, +100);
            popup.showAsDropDown(anchorView, 0, 0);
            popup.setOutsideTouchable(true);
            popup.setFocusable(false);
            popup.update();

            int height = binding.getRoot().getHeight();

            //new LogCat("popup.getHeight()", popup.getHeight(), "height", height, "popupHeigh1", popupHeigh1, "popupHeigh2", popupHeigh2);
        }

        public Build(@NonNull final View anchorView, @NonNull ViewDataBinding binding, int top) {

            binding.getRoot().measure(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
           // int width = binding.getRoot().getMeasuredWidth();
           // int height = binding.getRoot().getMeasuredHeight();
            int width = 100;
            int height = 500;
            int xOff = (anchorView.getWidth() - width)/2;
            int yOff = - height - anchorView.getHeight() - 8;

            new LogCat("height", height, "width", width);

            this.binding = binding;
            this.popup = new PopupWindow(anchorView.getContext());
            this.popup.setContentView(binding.getRoot());
            this.popup.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            this.popup.setWidth(width);
            this.popup.setHeight(height);
            this.popup.showAsDropDown(anchorView, xOff, yOff);
            this.popup.setOutsideTouchable(true);
            this.popup.setFocusable(false);
            this.popup.update();

            new LogCat("Width", popup.getWidth(), "Height", popup.getHeight());
        }

        public Build(@NonNull final View anchorView, @NonNull ViewDataBinding binding, String bottom) {

            this.binding = binding;

            int width = new MyDisplay().displayWidth(anchorView.getContext());  //*

            popup = new PopupWindow(anchorView.getContext());
            popup.setContentView(binding.getRoot());

            binding.getRoot().measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            int he=binding.getRoot().getMeasuredHeight();

            popup.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            popup.setWidth(anchorView.getWidth()); //*
            // popup.showAsDropDown(anchorView, 0, -anchorView.getHeight() + popup.getHeight(), Gravity.TOP);
            //  popup.showAsDropDown(anchorView, 0, +100);
            popup.showAsDropDown(anchorView, 0, popup.getHeight());
            popup.setOutsideTouchable(true);
            popup.setFocusable(false);
            popup.update();

            int height = binding.getRoot().getHeight();
        }


        // ----- Methods ----- //

        public Build<T> popUp(int popupVariable) {

            this.binding.setVariable(popupVariable, popup);
            this.binding.executePendingBindings();

            return this;
        }

        public PopupWindow getPopup() {

            return popup;
        }

        public void popUpDismiss() {

            if (popup != null) {

                if (popup.isShowing()) {

                    popup.dismiss();
                }
            }
        }
    }


    // ----- Abstracts ----- //

    protected abstract void setViews(ViewDataBinding binding);
}
