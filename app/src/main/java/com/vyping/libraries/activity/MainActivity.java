package com.vyping.libraries.activity;

import static com.vyping.libraries.utilities.definitions.Buckets.BUCKET_RUTINES;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_ICON_MAIN;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_NAME_MAIN;
import static com.vyping.masterlibrary.Common.MyFile.TYPE_PNG;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.vyping.libraries.utilities.definitions.Buckets;
import com.vyping.masterlibrary.Common.MyFile;
import com.vyping.masterlibrary.GestureListeners.MyGesturesListener;
import com.vyping.masterlibrary.activities.BasicActivity;
import com.vyping.libraries.R;
import com.vyping.libraries.databinding.MainActivityBinding;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.views.MyImageView;

public class MainActivity extends BasicActivity implements BasicActivity.StartCallBack {

    public MainActivityBinding binding;


    // ----- SetUp ----- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        CreateBindingActivity(MainActivity.this, R.layout.activity_main, MODULE_ICON_MAIN, MODULE_NAME_MAIN, null);

        methodTest();
    }

    @Override
    public void SetStartBindingProcess(ViewDataBinding binding) {

        this.binding = (MainActivityBinding) binding;
    }

    @Override
    public void SetActionBar() {}

    @Override
    public boolean SetActivityViews() {

        return true;
    }


    // ----- ModelMethods ----- //

    @SuppressLint("ClickableViewAccessibility")
    private void methodTest() {

        String rutine = "03 - Biceps";
        String nameImage = new MyFile().setName(rutine, TYPE_PNG);
        String url = new Buckets().getMediaResource(BUCKET_RUTINES, nameImage, "");

        new MyImageView().putImageFromAssetsOrWeb(binding.imageView, nameImage, url);

        MyGesturesListener myGesturesListener = new MyGesturesListener(binding.imageView, new MyGesturesListener.Interfase() {

            @Override
            public void OnClick(@NonNull View view) {

               new LogCat("CLickkkkk");
            }
        });

        binding.imageView.setOnTouchListener(myGesturesListener);
    }
}