package com.vyping.libraries.activity;

import static com.vyping.libraries.utilities.definitions.Modules.MODULE_ICON_MAIN;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_NAME_MAIN;
import static java.lang.Boolean.FALSE;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.vyping.libraries.Aplication.BaseActivity;
import com.vyping.libraries.R;
import com.vyping.libraries.databinding.MainActivityBinding;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.ToolBars.MyToolBars;
import com.vyping.masterlibrary.dialogs.DialogPdfView;
import com.vyping.masterlibrary.time.MyTime;

import java.util.ArrayList;
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

                new LogCat("day", day, "month", month, "year", year);
            }

            private void DummyVoid() {}
        });
    }


    @Override
    public void setStartBindingProcess(ViewDataBinding binding) {

        this.binding = (MainActivityBinding) binding;
    }

    @Override
    public void setActionBar() {}

    @Override
    public void setActivityViews() {}
}