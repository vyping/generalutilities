package com.vyping.masterlibrary.popups;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Common.MyArrayList;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.views.MyButton;

import java.util.ArrayList;

public class MyPopUpButtons extends MyPopUp {

    private Interfase interfase;

    private ArrayList<String> arrayItems;


    //----- Setup - Section-----//

    @SuppressLint("InflateParams")
    public MyPopUpButtons(View anchorView, @NonNull final ArrayList<String> arrayItems, Interfase interfase) {

        super(anchorView);

        SetParameters(arrayItems, interfase);
        SetPopUpViews();
    }

    @SuppressLint("InflateParams")
    public MyPopUpButtons(View anchorView, @NonNull final String[] listItems, Interfase interfase) {

        super(anchorView);

        SetParameters(listItems, interfase);
        SetPopUpViews();
    }

    private void SetParameters(@NonNull final ArrayList<String> arrayItems, Interfase interfase) {

        this.interfase = interfase;
        this.arrayItems = arrayItems;
    }

    private void SetParameters(@NonNull final String[] listItems, Interfase interfase) {

        this.interfase = interfase;
        this.arrayItems = new MyArrayList().listToArray(listItems);
    }

    private void SetPopUpViews() {

        setContainerOrientation(LinearLayout.HORIZONTAL);

        for (String text : arrayItems) {

            Button button = new MyButton().createByAttributes(anchorView.getContext(), R.attr.popupListButtons, R.style.PopUpListButtons, text, new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    interfase.ClickButton(text);

                    popUpDismiss();
                }

                private void DummyVoid() {
                }
            });
            AddCustomView(button);
        }
    }


    // ----- Interface ----- //

    public interface Interfase {

        boolean ClickButton(String text);
    }

}
