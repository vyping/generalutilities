package com.vyping.libraries.activity;

import static com.vyping.libraries.utilities.definitions.Buckets.BUCKET_RUTINES;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_ICON_MAIN;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_NAME_MAIN;
import static com.vyping.masterlibrary.Common.MyFile.TYPE_PNG;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import com.vyping.libraries.utilities.definitions.Buckets;
import com.vyping.masterlibrary.Common.MyFile;
import com.vyping.masterlibrary.aplication.BaseActivity;
import com.vyping.libraries.R;
import com.vyping.libraries.databinding.MainActivityBinding;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.time.MyTime;
import com.vyping.masterlibrary.time.MyTimeTools;
import com.vyping.masterlibrary.views.MyImageView;

import java.util.Calendar;

public class MainActivity extends BaseActivity implements BaseActivity.StartCallBack {

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


    // ----- Methods ----- //

    private void methodTest() {

        String rutine = "03 - Biceps";
        String nameImage = new MyFile().setName(rutine, TYPE_PNG);
        String url = new Buckets().getMediaResource(BUCKET_RUTINES, nameImage, "");

        new MyImageView().putImageFromAssetsOrWeb(binding.imageView, nameImage, url);
    }
}