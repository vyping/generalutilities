package com.vyping.masterlibrary.GestureListeners;

import static android.view.DragEvent.ACTION_DRAG_ENDED;
import static android.view.DragEvent.ACTION_DRAG_ENTERED;
import static android.view.DragEvent.ACTION_DRAG_STARTED;
import static android.view.DragEvent.ACTION_DROP;
import static java.lang.Math.abs;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.vyping.masterlibrary.views.MyView;

public class MyBinderHandler<T> {

    private final Interfase<T> interfase;


    /*----- SetUp -----*/

    public MyBinderHandler(@NonNull ViewDataBinding viewDataBinding, @NonNull Interfase<T> interfase) {

        this.interfase = interfase;

        interfase.SetViewDataBinding(viewDataBinding);
    }


    //----- Interface -----//

    public interface Interfase<T> {

        void SetViewDataBinding(@NonNull ViewDataBinding viewDataBinding);
    }
}