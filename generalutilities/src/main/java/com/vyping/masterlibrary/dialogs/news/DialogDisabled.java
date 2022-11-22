package com.vyping.masterlibrary.dialogs.news;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ViewDataBinding;

import com.vyping.masterlibrary.GestureListeners.MyGesturesListener;
import com.vyping.masterlibrary.adapters.recyclerview.interfase.RecyclerInterfase;
import com.vyping.masterlibrary.databinding.DialogDisabledBinding;
import com.vyping.masterlibrary.time.MyDelay;

public class DialogDisabled {

    @SuppressLint("StaticFieldLeak")
    public static DialogDisabledBinding dialogDisabledBinding;
    public static Dialog dialogCancelOrder;


    // ----- SetUp ----- //

    public DialogDisabled(Context context) {

        DialogViews();
    }

    private void DialogViews() {

        new MyDelay(100).interfase(new MyDelay.Interfase() {

            @Override
            public void Execute() {

                if (dialogDisabledBinding != null && dialogCancelOrder != null) {

                    new MyGesturesListener(dialogDisabledBinding.BtnBack, new MyGesturesListener.Interfase() {

                        @Override
                        public void OnClick(@NonNull View view) {

                            dialogCancelOrder.dismiss();
                        }

                        private void DummyVoid() {};
                    });

                } else {

                    DialogViews();
                }
            }

            private void DummyVoid() {};
        });
    }


    // ----- Interfase ----- //

    public interface Interfase extends RecyclerInterfase {

        default void ButtonBack() {};
        default void ButtonCancelled() {};
    }

    // ----- Binding ----- //

    @BindingAdapter(value={"dialogDisabledBinding", "dialogDisabledDialog"}, requireAll=true)
    public static void getDialogDisabledBinding(@NonNull View view, @NonNull ViewDataBinding viewDataBinding, Dialog dialog) {

        dialogDisabledBinding = (DialogDisabledBinding) viewDataBinding;
        dialogCancelOrder = dialog;
    }
}