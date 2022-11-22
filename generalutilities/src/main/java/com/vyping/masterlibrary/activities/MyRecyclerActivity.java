package com.vyping.masterlibrary.activities;

import static com.vyping.masterlibrary.authentication.MyAuthGoogle.REQUEST_CODE_GOOGLE_AUTH;
import static java.lang.Boolean.FALSE;
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

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.Common.MyPermissions;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.ToolBars.MyToolBars;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerCompositeBinder;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerConditionalBinder;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerItemBinderInterfase;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandler;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandlerInterfase;
import com.vyping.masterlibrary.aplication.MyApplication;
import com.vyping.masterlibrary.time.MyTime;

public abstract class MyRecyclerActivity<Item> extends AppCompatActivity {

    public MyApplication application;
    public Context context;
    public ViewDataBinding binding;
    private MyPermissions myPermissions;

    public MyToolBars myToolBars;
    public MyRealtime myRealtime;

    private Class backActivity;

    public RecyclerItemBinderInterfase<Item> recyclerBinder;
    public RecyclerHandler<Item> recyclerHandler;


    // ----- SetUp ----- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (application == null) {

            application = (MyApplication) getApplication();
        }

        //application.statusSession();

        CreateActivity();
        StartProcess();
        InherentViews();
        ActivityViews();

        binding.executePendingBindings();

        LaunchProcess();
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

    public MyRecyclerActivity<Item> activity(Activity activity, int layout) {

        this.context = new MyActivity().setTheme(activity);
        this.application.setContext(context);
        this.application.setCurrentActivity((Activity) context);
        this.binding = DataBindingUtil.inflate(LayoutInflater.from(activity), layout, null, false);

        View view = binding.getRoot();
        setContentView(view);

        return this;
    }

    public MyRecyclerActivity<Item> actionBar() {

        application.setActionBar();

        return this;
    }

    public MyRecyclerActivity<Item> setSideMenu() {

        application.setSideMenu(FALSE);

        return this;
    }

    public MyRecyclerActivity<Item> onBack(Class backActivity) {

        this.backActivity = backActivity;

        return this;
    }

    public ViewDataBinding binding() {

        return binding;
    }

    public MyToolBars toolbar(int container) {

        if (myToolBars != null) {

            myToolBars = new MyToolBars(context, container) {};
        }

        return myToolBars;
    }


    // ----- Firebase ----- //

    public void setFirebaseService(String instance, MyRealtime.SingleListener listener) {

        myRealtime = new MyRealtime(instance);
        myRealtime.getSingleValue(listener);
    }

    public void setFirebaseService(String instance, String child, MyRealtime.SingleListener listener) {

        myRealtime = new MyRealtime(instance, child);
        myRealtime.getSingleValue(listener);
    }

    public void setFirebaseService(String instance) {

        myRealtime = new MyRealtime(instance);
        myRealtime.getValueChanges(MyRealtimeListener);
    }

    public void setFirebaseService(String instance, MyRealtime.ValueListener valueListener) {

        myRealtime = new MyRealtime(instance);
        myRealtime.getValueChanges(valueListener);
    }

    public void setFirebaseService(String instance, String child) {

        myRealtime = new MyRealtime(instance, child);
        myRealtime.getValueChanges(MyRealtimeListener);
    }

    public void setFirebaseService(String instance, String child, MyRealtime.ValueListener valueListener) {

        myRealtime = new MyRealtime(instance, child);
        myRealtime.getValueChanges(valueListener);
    }


    // ----- Methods ----- //

    public void setSearchBar(int container, ToolBarsInterfase interfase) {

        myToolBars = new MyToolBars(context, container) {};
        myToolBars.setSearchToolBar(new MyToolBars.SearchInterface() {

            @Override
            public void SelectedSearch(String search) {

                recyclerHandler.setSearch(search);
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
    }


    // ----- Permissions ----- //

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


    // ----- RecyclerViewAdapter ----- //

    @SafeVarargs
    public final void setRecyclerHandler(RecyclerHandlerInterfase<Item> handlerInterfase, boolean searched, RecyclerConditionalBinder<Item>... recyclerBinder) {

        this.recyclerBinder = new RecyclerCompositeBinder<Item>(recyclerBinder);
        this.recyclerHandler = new RecyclerHandler<>(handlerInterfase, searched);
    }


    // ----- Listeners ModelMethods ----- //

    private final MyRealtime.ValueListener MyRealtimeListener = new MyRealtime.ValueListener() {

        @Override
        public void ChildAdded(@NonNull DataSnapshot dataSnapshot) {

            recyclerHandler.addMethod(dataSnapshot);
        }

        @Override
        public void ChildChanged(@NonNull DataSnapshot dataSnapshot) {

            recyclerHandler.modifyMethod(dataSnapshot);
        }

        @Override
        public void ChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            recyclerHandler.removeMethod(dataSnapshot);
        }

        @Override
        public void finishAdded() {}
    };


    // ----- Interfase ----- //

    protected abstract void CreateActivity();
    protected abstract void StartProcess();
    protected void InherentViews() {};
    protected void ActivityViews() {};
    protected void ModifyViewsByAuth() {};
    protected void LaunchProcess() {};
    protected void ActivityResults(int requestCode, int resultCode, Intent data) {};
    protected void StopProcess() {};
    protected void BackPressed() {};
    protected void loginError(String error) {};

    public interface PermissionsInterfase {

        default void Granted(int result) {};
    }
    public interface ToolBarsInterfase {

        default void setSearch(String search) {};
        default void setDate(MyTime myTime) {};
    }


    /*----- Inherents -----*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        myPermissions.PermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        application.activityResults(requestCode, data);
        ActivityResults(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {

        BackPressed();

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
    }
}
