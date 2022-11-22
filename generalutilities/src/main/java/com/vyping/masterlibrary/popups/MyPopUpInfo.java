package com.vyping.masterlibrary.popups;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.TextView;

import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.views.MyTextView;

public class MyPopUpInfo extends MyPopUp {


    //----- Setup - Section-----//

    @SuppressLint("InflateParams")
    public MyPopUpInfo(View anchorView, String textInfo) {

        super(anchorView);

        SetParameters();
        SetPopUpViews(textInfo);
    }

    private void SetParameters() {

    }

    private void SetPopUpViews(String textInfo) {

     //   TextView Tv_Info = new MyTextView().createByAttributes(anchorView.getContext(), R.attr.popupTextView, R.style.PopUpTextView, textInfo);
       // AddCustomView(Tv_Info);
    }
}
