package com.vyping.masterlibrary.dialogs;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ViewDataBinding;

import com.vyping.masterlibrary.databinding.DialogInformationBinding;

import cheekiat.slideview.OnChangeListener;
import cheekiat.slideview.SlideView;

public class DialogInformation extends DialogParams {

    @SuppressLint("StaticFieldLeak")
    public static DialogInformationBinding productBinding;

    private static Interfase interfase;

    // ----- SetUp ----- //

    public DialogInformation(@NonNull Context context, int arrayParameters, Interfase myInterfase) {

        super(context, arrayParameters);

        interfase = myInterfase;
    }


    // ----- Binding ----- //

    @BindingAdapter("dialogInformationBinding")
    public static void getViewDataBinding(@NonNull View view, @NonNull ViewDataBinding viewDataBinding) {

        productBinding = (DialogInformationBinding) viewDataBinding;

    }

    @BindingAdapter("confirm")
    public static void getViewDataBinding(@NonNull SlideView slideView, @NonNull DialogParams dialogParams) {

        slideView.setOnChangeListener(new OnChangeListener() {

            @Override
            public void onProgressChanged(int progress) {
                //Show progress 0 ~ 100
            }

            @Override
            public void onStopChanged() {

            }

            @Override
            public void onComplete() {

                slideView.reset();
                interfase.Confirm();
                productBinding.getInformationDialog().dismiss();
            }
        });
    }


    // ----- Interface ----- //

    public interface Interfase {

        void Confirm();
    }
}
