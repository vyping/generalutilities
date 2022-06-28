package com.vyping.libraries.activity;

import static com.vyping.libraries.utilities.definitions.Buckets.BUCKET_RUTINES;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_ICON_MAIN;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_NAME_MAIN;
import static com.vyping.masterlibrary.Common.MyFile.TYPE_PNG;
import static com.vyping.masterlibrary.time.Definitions.FORMAT_DATE_01;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;

import com.airbnb.paris.R2;
import com.vyping.libraries.utilities.definitions.Buckets;
import com.vyping.masterlibrary.Common.MyFile;
import com.vyping.masterlibrary.GestureListeners.MyGesturesListener;
import com.vyping.masterlibrary.activities.BasicActivity;
import com.vyping.libraries.R;
import com.vyping.libraries.databinding.MainActivityBinding;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.time.MyTime;
import com.vyping.masterlibrary.views.MyImageView;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends BasicActivity {

    public MainActivityBinding binding;


    // ----- SetUp ----- //

    @Override
    protected void CreateActivity() {

        createActivity(MainActivity.this, R.layout.activity_main, MODULE_ICON_MAIN, MODULE_NAME_MAIN, null);
    }

    @Override
    protected void StartProcess(ViewDataBinding binding) {

        this.binding = (MainActivityBinding) binding;
    }

    @Override
    protected void ActionBar() {}

    @Override
    protected void LaunchProcess() {

        String rutine = "03 - Biceps";
        String nameImage = new MyFile().setName(rutine, TYPE_PNG);
        String url = new Buckets().getMediaResource(BUCKET_RUTINES, nameImage, "");

        new MyImageView().putImageFromAssetsOrWeb(binding.imageView, nameImage, url);

        long diference = new MyTime(1656307757000L).compare(1592198957000L).getDeltaYears();

        new LogCat("Diference", diference);
    }
}