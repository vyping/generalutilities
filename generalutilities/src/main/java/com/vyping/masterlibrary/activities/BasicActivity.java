package com.vyping.masterlibrary.activities;

import static java.lang.Boolean.TRUE;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.vyping.masterlibrary.ActionBar.MyActionBar;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.Common.MyPermissions;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.ToolBars.MyToolBars;
import com.vyping.masterlibrary.aplication.MyApplication;
import com.vyping.masterlibrary.time.MyTime;

public abstract class BasicActivity extends AppCompatActivity {

    public MyApplication application;
    public Context context;
    public ViewDataBinding binding;
    private MyPermissions myPermissions;

    public MyActionBar actionBar;
    public MyToolBars myToolBars;
    public MyRealtime myRealtime;

    private Class backActivity;


    // ----- SetUp ----- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (application == null) {

            application = (MyApplication) getApplication();
        }

        CreateActivity();
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


    // ----- Start ----- //

    public void createActivity(Activity activity, int layout, int icon, int module) {

        setStartProcess(activity, layout);
        setActionBar(icon, module);
        setActivityViews();

        LaunchProcess();
    }

    public void createActivity(Activity activity, int layout, int icon, int module, Class backActivity) {

        setStartProcess(activity, layout, backActivity);
        setActionBar(icon, module);
        setActivityViews();

        LaunchProcess();
    }

    public void setStartProcess(Activity activity, int layout) {

        this.context = new MyActivity().setTheme(activity);
        this.binding = DataBindingUtil.inflate(LayoutInflater.from(activity), layout, null, false);

        View view = binding.getRoot();
        setContentView(view);

        StartProcess(binding);
    }

    public void setStartProcess(Activity activity, int layout, Class<Activity> backActivity) {

        this.context = activity;
        this.backActivity = backActivity;
        this.binding = DataBindingUtil.inflate(LayoutInflater.from(activity), layout, null, false);

        View view = binding.getRoot();
        setContentView(view);

        StartProcess(binding);
    }

    public void setActionBar(int icon, int module) {

        this.actionBar = new MyActionBar(context, icon, module) {};

        ActionBar();
    }

    public void setActivityViews() {

        ActivityViews();

        binding.executePendingBindings();
    }


    // ----- Firebase ----- //


    public void setFirebaseService(String instance, Object listener) {

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


    // ----- Methods ----- //

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
            public void SelectedDate(MyTime myTime) {

                interfase.setDate(myTime);
            }

            private void DummyVoid(){}
        });
    }

    public void setMonthBar(int container, ToolBarsInterfase interfase) {

        myToolBars = new MyToolBars(context, container) {};
        myToolBars.setMonthToolBar(new MyToolBars.DateInterface() {

            @Override
            public void SelectedDate(MyTime myTime) {

                interfase.setDate(myTime);
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
            public void SelectedDate(MyTime myTime) {

                interfase.setDate(myTime);
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
        myToolBars.setMonthToolBar(new MyToolBars.DateInterface() {

            @Override
            public void SelectedDate(MyTime myTime) {

                interfase.setDate(myTime);
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

    public void requestPermissions(String[] permissions, int arrayParameters, int code, PermissionsInterfase interfase) {

       myPermissions = new MyPermissions(context, arrayParameters, permissions, code);
       myPermissions.RequestPermissions(new MyPermissions.Interfase() {

           public void PermissionsResult(int result) {

               interfase.Granted(result);
           }

           private void DummyVoid() {
           }
       });
    }

    public void requestPermissions(String[] permissions, int arrayParameters, int code, int minVersion, PermissionsInterfase interfase) {

        if (android.os.Build.VERSION.SDK_INT >= minVersion) {

            myPermissions = new MyPermissions(context, arrayParameters, permissions, code);
            myPermissions.RequestPermissions(new MyPermissions.Interfase() {

                public void PermissionsResult(int result) {

                    interfase.Granted(result);
                }

                private void DummyVoid() {
                }
            });

        } else {

            interfase.Granted(code);
        }
    }


    // ----- Interfase ----- //

    protected abstract void CreateActivity();
    protected abstract void StartProcess(ViewDataBinding binding);
    protected void ActionBar() {};
    protected void ActivityViews() {};
    protected void LaunchProcess() {};
    protected void StopProcess() {};

    public interface PermissionsInterfase {

        default void Granted(int result) {};
    }

    public interface ToolBarsInterfase {

        default void setSearch(String search) {};
        default void setDate(MyTime myTime) {};
    }


    // ----- Inherents ----- //

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        myPermissions.PermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {

        if (backActivity != null) {

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

        StopProcess();
    }
}
