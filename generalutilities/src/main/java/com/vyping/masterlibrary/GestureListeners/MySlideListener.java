package com.vyping.masterlibrary.GestureListeners;

import android.app.Dialog;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import androidx.annotation.NonNull;

import cheekiat.slideview.OnChangeListener;
import cheekiat.slideview.SlideView;

public class MySlideListener {


    /*----- SetUp -----*/

    public MySlideListener(@NonNull SlideView slideView, Interface interfase) {

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

                interfase.Confirm();
                slideView.reset();
            }
        });
    }


    //----- Interface - Section-----//

    public interface Interface {

        void Confirm();
    }
}