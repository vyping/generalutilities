package com.vyping.masterlibrary.activities;

import static java.lang.Boolean.TRUE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.Common.MyPermissions;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.aplication.MyApplication;

public abstract class MyLoginActivity extends AppCompatActivity {

    public MyApplication application;
    public Context context;
    public ViewDataBinding binding;
    private MyPermissions myPermissions;

    public MyRealtime myRealtime;


    // ----- SetUp ----- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (application == null) {

            application = (MyApplication) getApplication();
        }

        CreateActivity();
        StartProcess();
        InherentViews();
        ActivityViews();

        binding.executePendingBindings();

        LaunchProcess();

        application.sessionStatus();
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }

    @Override
    protected void onStart() {

        super.onStart();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }


    // ----- Chained ----- //

    public MyLoginActivity activity(@NonNull Activity activity, int layout) {

        this.context = new MyActivity().setTheme(activity);
        this.application.setContext(context);
        this.application.setCurrentActivity((Activity) context);
        this.binding = DataBindingUtil.inflate(LayoutInflater.from(activity), layout, null, false);

        View view = binding.getRoot();
        setContentView(view);

        return this;
    }

    public MyLoginActivity actionBar() {

        application.setActionBar();

        return this;
    }

    public MyLoginActivity setSideMenu() {

        application.setSideMenu(TRUE);

        return this;
    }

    public ViewDataBinding binding() {

        return binding;
    }


    // ----- Firebase ----- //

    public void setFirebaseService(String instance, Object listener)  {

        myRealtime = new MyRealtime(instance);

        if (listener instanceof MyRealtime.SingleListener) {

            MyRealtime.SingleListener singleListener = (MyRealtime.SingleListener) listener;
            myRealtime.getSingleValue(singleListener);

        } else if (listener instanceof  MyRealtime.ValueListener) {

            MyRealtime.ValueListener valueListener = (MyRealtime.ValueListener) listener;
            myRealtime.getValueChanges(valueListener);
        }
    }

    public void setFirebaseService(String instance, String child, Object listener) {

        myRealtime = new MyRealtime(instance, child);

        if (listener instanceof MyRealtime.SingleListener) {

            MyRealtime.SingleListener singleListener = (MyRealtime.SingleListener) listener;
            myRealtime.getSingleValue(singleListener);

        } else if (listener instanceof  MyRealtime.ValueListener) {

            MyRealtime.ValueListener valueListener = (MyRealtime.ValueListener) listener;
            myRealtime.getValueChanges(valueListener);
        }
    }


    // ----- Interfase ----- //

    protected abstract void CreateActivity();
    protected abstract void StartProcess();
    protected void InherentViews() {};
    protected void ActivityViews() {};
    protected void LaunchProcess() {};
    protected void StopProcess() {};
    protected void ActivityResults(int requestCode, int resultCode, Intent data) {};


    // ----- Permissions ----- //

    public void requestPermissions(String[] permissions, int arrayParameters, int code, MyPermissions.Interfase interfase) {

        myPermissions = new MyPermissions(context, arrayParameters, permissions, code);
        myPermissions.RequestPermissions(interfase);
    }

    public void requestPermissions(String[] permissions, int arrayParameters, int code, int minVersion, MyPermissions.Interfase interfase) {

        myPermissions = new MyPermissions(context, arrayParameters, permissions, code);
        myPermissions.RequestPermissions(interfase);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        myPermissions.PermissionsResult(requestCode, permissions, grantResults);
    }


    // ----- Inherents ----- //

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        ActivityResults(requestCode, resultCode, data);
        application.activityResults(requestCode, data);
    }

    @Override
    public void onBackPressed() {}

    @Override
    public void onLowMemory() {

        super.onLowMemory();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }

    @Override
    protected void onStop() {

        super.onStop();
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();

        if ( myRealtime != null) {

            myRealtime.removeValueListener();
        }

        StopProcess();
    }
}
