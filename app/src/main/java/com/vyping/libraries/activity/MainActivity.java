package com.vyping.libraries.activity;

import static com.vyping.libraries.utilities.definitions.Modules.MODULE_ICON_MAIN;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_NAME_MAIN;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import android.os.Bundle;

import com.vyping.masterlibrary.aplication.BaseActivity;
import com.vyping.libraries.R;
import com.vyping.libraries.databinding.MainActivityBinding;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.time.MyTime;
import com.vyping.masterlibrary.time.MyTimeTools;

import java.util.Calendar;

public class MainActivity extends BaseActivity implements BaseActivity.StartCallBack {

    public MainActivityBinding binding;


    // ----- SetUp ----- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        CreateBindingActivity(MainActivity.this, R.layout.activity_main, MODULE_ICON_MAIN, MODULE_NAME_MAIN, null);
        setDateBar(binding.ClMainContainer.getId(), new ToolBarsInterfase() {

            @Override
            public void setDate(Calendar calendar, long milis, String day, String month, String year) {

                new LogCat("MainActivity - day", day, "month", month, "year", year);
            }

            private void DummyVoid() {}
        });

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

    private void methodTest() {}
}