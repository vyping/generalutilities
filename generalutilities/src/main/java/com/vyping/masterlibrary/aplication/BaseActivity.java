package com.vyping.masterlibrary.aplication;

import static java.lang.Boolean.TRUE;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.vyping.masterlibrary.ActionBar.MyActionBar;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.ToolBars.MyToolBars;

import java.util.Calendar;

public class BaseActivity extends AppCompatActivity {

    public MyApplication application;
    public Context context;
    public StartCallBack callback;

    public MyActionBar actionBar;
    public MyToolBars myToolBars;
    public MyRealtime myRealtime;

    private Class backActivity;


    /*----- SetUp -----*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (application == null) {

            application = (MyApplication) getApplication();
        }
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


    /*----- Methods -----*/

    public void CreateActivity(Activity activity, int layout, int icon, int module) {

        setStartProcess(activity, layout);
        setActionBar(icon, module);
        setActivityViews();
    }

    public void CreateActivity(Activity activity, int layout, int icon, int module, Class backActivity) {

        setStartProcess(activity, layout, backActivity);
        setActionBar(icon, module);
        setActivityViews();
    }

    public void CreateBindingActivity(Activity activity, int layout, int icon, int module) {

        setStartBindingProcess(activity, layout);
        setActionBar(icon, module);
        setActivityViews();
    }

    public void CreateBindingActivity(Activity activity, int layout, int icon, int module, Class backActivity) {

        setStartBindingProcess(activity, layout, backActivity);
        setActionBar(icon, module);
        setActivityViews();
    }

    public void setStartProcess(Activity activity, int layout) {

        context = new MyActivity().setTheme(activity);
        callback = (StartCallBack) activity;
        callback.setStartProcess();

        setContentView(layout);
    }

    public void setStartProcess(Activity activity, int layout, Class<Activity> backActivity) {

        context = activity;
        callback = (StartCallBack) activity;
        callback.setStartProcess();

        this.backActivity = backActivity;

        setContentView(layout);
    }

    public void setStartBindingProcess(Activity activity, int layout) {

        context = new MyActivity().setTheme(activity);
        callback = (StartCallBack) activity;

        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), layout, null, false);
        View view = binding.getRoot();
        setContentView(view);

        callback.setStartBindingProcess(binding);
    }

    public void setStartBindingProcess(Activity activity, int layout, Class<Activity> backActivity) {

        context = activity;
        callback = (StartCallBack) activity;
        this.backActivity = backActivity;

        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(activity), layout, null, false);
        View view = binding.getRoot();
        setContentView(view);

        callback.setStartBindingProcess(binding);
    }

    public void setActionBar(int icon, int module) {

        actionBar = new MyActionBar(context, icon, module) {};

        callback.setActionBar();
    }

    public void setSearchBar(int container, ToolBarsInterfase interfase) {

        myToolBars = new MyToolBars(context, container) {};
        myToolBars.setSearchToolBar(new MyToolBars.SearchInterface() {

            @Override
            public void SelectedSearch(String search) {

                interfase.setSearch(search);
            }

            private void DummyVoid(){}
        });
    }

    public void setDateBar(int container, ToolBarsInterfase interfase) {

        myToolBars = new MyToolBars(context, container) {};
        myToolBars.setDateToolBar(new MyToolBars.DateInterface() {

            @Override
            public void SelectedDate(Calendar Calendar, long milis, String day, String month, String year) {

                interfase.setDate(Calendar, milis, day, month, year);
            }

            private void DummyVoid(){}
        });
    }

    public void setMonthBar(int container, ToolBarsInterfase interfase) {

        myToolBars = new MyToolBars(context, container) {};
        myToolBars.setMonthToolBar(new MyToolBars.MonthInterface() {

            @Override
            public void SelectedMonth(Calendar Calendar, long milis, String month, String year) {

                interfase.setMonth(Calendar, milis, month, year);
            }

            private void DummyVoid(){}
        });
    }

    public void setSearchAndDateBar(int container, ToolBarsInterfase interfase) {

        myToolBars = new MyToolBars(context, container) {};
        myToolBars.setSearchToolBar(new MyToolBars.SearchInterface() {

            @Override
            public void SelectedSearch(String search) {

                interfase.setSearch(search);
            }

            private void DummyVoid(){}
        });
        myToolBars.setDateToolBar(new MyToolBars.DateInterface() {

            @Override
            public void SelectedDate(Calendar Calendar, long milis, String day, String month, String year) {

                interfase.setDate(Calendar, milis,day, month, year);
            }

            private void DummyVoid(){}
        });
        actionBar.setTouchListener(new MyActionBar.TouchInterface() {

            @Override
            public void OnFling() {

                myToolBars.selectBar();
            }

            @Override
            public void DummyVoid() {}
        });
    }

    public void setSearchAndMonthBar(int container, ToolBarsInterfase interfase) {

        myToolBars = new MyToolBars(context, container) {};
        myToolBars.setSearchToolBar(new MyToolBars.SearchInterface() {

            @Override
            public void SelectedSearch(String search) {

                interfase.setSearch(search);
            }

            private void DummyVoid(){}
        });
        myToolBars.setMonthToolBar(new MyToolBars.MonthInterface() {

            @Override
            public void SelectedMonth(Calendar Calendar, long milis, String month, String year) {

                interfase.setMonth(Calendar, milis, month, year);
            }

            private void DummyVoid(){}
        });
        actionBar.setTouchListener(new MyActionBar.TouchInterface() {

            @Override
            public void OnFling() {

                myToolBars.selectBar();
            }

            @Override
            public void DummyVoid() {}
        });
    }

    public void setActivityViews() {

        callback.setActivityViews();
    }


    /*----- Firebase -----*/

    public void setFirebaseService(String instance, MyRealtime.SingleListener listener) {

        myRealtime = new MyRealtime(instance);
        myRealtime.getSingleValue(listener);
    }

    public void setFirebaseService(String instance, String child, MyRealtime.SingleListener listener) {

        myRealtime = new MyRealtime(instance, child);
        myRealtime.getSingleValue(child, listener);
    }

    public void setFirebaseService(String instance, MyRealtime.ValueListener valueListener) {

        myRealtime = new MyRealtime(instance);
        myRealtime.getValueChanges(valueListener);
    }

    public void setFirebaseService(String instance, String child, MyRealtime.ValueListener valueListener) {

        myRealtime = new MyRealtime(instance, child);
        myRealtime.getValueChanges(valueListener);
    }


    /*----- Interfase -----*/

    public interface StartCallBack {

        default void setStartProcess() {};
        default void setStartBindingProcess(ViewDataBinding binding) {};
        default void setActionBar() {};
        void setActivityViews();
    }

    public interface ToolBarsInterfase {

        default void setSearch(String search) {};
        default void setDate(Calendar Calendar, long milis, String day, String month, String year) {};
        default void setMonth(Calendar Calendar, long milis, String month, String year) {};
    }


    /*----- Inherents -----*/

    @Override
    public void onBackPressed() {

        if (backActivity == null) {

            //new MyActivity().Start(context, MenuActivity.class, TRUE);

        } else {

            new MyActivity().Start(context, backActivity, TRUE);
        }
    }

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

        myRealtime.removeValueListener();
    }
}