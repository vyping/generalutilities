package com.vyping.masterlibrary.GestureListeners;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.vyping.masterlibrary.Common.MyString;

import java.util.Objects;

public class MyMoneyWatcher {

    private String prevValue;


    /*----- SetUp -----*/

    public MyMoneyWatcher(EditText editText, Interface interfase) {

        prevValue = new MyString().extractFromEditText(editText);

        editText.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                String newValue = String.valueOf(charSequence).replace("$", "").replace(",", "").replace(".", "");

                if (!Objects.equals(prevValue, String.valueOf(charSequence))) {

                    String formattedValue = new MyString().formatToMoney(newValue);
                    prevValue = formattedValue;

                    editText.setText(formattedValue);
                    editText.setSelection(editText.getText().length());
                }

                interfase.OnTextChanged(editText, newValue);
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