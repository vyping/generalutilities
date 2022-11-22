package com.vyping.masterlibrary.GestureListeners;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.views.MyView;

import java.util.Objects;

public class MyTextWatcher {


    /*----- SetUp -----*/

    public MyTextWatcher(EditText editText, Interface interfase) {

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                String stringText = String.valueOf(charSequence);

                interfase.OnTextChanged(editText, stringText);
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }


    //----- Interface - Section-----//

    public interface Interface {

        void OnTextChanged(EditText editText, String text);
    }
}