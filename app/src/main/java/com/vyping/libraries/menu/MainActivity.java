package com.vyping.libraries.menu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;


import android.os.Bundle;

import com.vyping.libraries.R;
import com.vyping.libraries.databinding.MainActivityBinding;

public class MainActivity extends AppCompatActivity {

    
    private MainActivityBinding mainActivityBinding;
    public String exercise;
    
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        mainActivityBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainActivityBinding.setMainActivity(this);
        mainActivityBinding.setExercise("test 1");
    }
}