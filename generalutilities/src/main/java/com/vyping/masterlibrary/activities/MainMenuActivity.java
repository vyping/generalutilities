package com.vyping.masterlibrary.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;

import com.google.firebase.database.DataSnapshot;
import com.smarteist.autoimageslider.SliderView;
import com.vyping.masterlibrary.ActionBar.MyActionBar;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.Common.MyPermissions;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.ToolBars.MyToolBars;
import com.vyping.masterlibrary.aplication.MyApplication;
import com.vyping.masterlibrary.models.notices.Notice;
import com.vyping.masterlibrary.views.recyclerview.binder.CompositeItemBinder;
import com.vyping.masterlibrary.views.recyclerview.binder.ConditionalDataBinder;
import com.vyping.masterlibrary.views.recyclerview.binder.ItemBinder;
import com.vyping.masterlibrary.views.recyclerview.methods.MethodInterfase;
import com.vyping.masterlibrary.views.recyclerview.methods.MethodsHandler;

public abstract class MainMenuActivity<T> extends AppCompatActivity {

    public MyApplication application;
    public Context context;
    public ViewDataBinding binding;
    public AdapterNotices adapterNotices;

    public MyActionBar actionBar;
    public MyToolBars myToolBars;
    public MyRealtime realtimeNotices, realtimeMenu;
    private MyPermissions myPermissions;

    public MethodsHandler<T> methodHandler;


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

    public void setStartProcess(Activity activity, int layout) {

        this.context = new MyActivity().setTheme(activity);
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

        adapterNotices = new AdapterNotices(context, selectedInterfase);

        SliderView sliderView = ActivityViews();
        sliderView.setSliderAdapter(adapterNotices);
        sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        sliderView.setAutoCycle(true);
        sliderView.setIndicatorMargin(0);
        sliderView.setIndicatorRadius(4);
        sliderView.setScrollTimeInSec(6);
        sliderView.startAutoCycle();

        binding.executePendingBindings();
    }


    // ----- Firebase ----- //

    public void setFirebaseService(String instanceNotices, String instanceMenu) {

        realtimeNotices = new MyRealtime(instanceNotices);
        realtimeNotices.getValueChanges(noticesRealtimeListener);

        realtimeMenu = new MyRealtime(instanceMenu);
        realtimeMenu.getValueChanges(MyRealtimeListener);
    }


    // ----- Methods ----- //

    public void setSearchBar(int container, ToolBarsInterfase interfase) {

        myToolBars = new MyToolBars(context, container) {};
        myToolBars.setSearchToolBar(new MyToolBars.SearchInterface() {

            @Override
            public void SelectedSearch(String search) {

                methodHandler.setSearch(search);

                interfase.setSearch(search);
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

    public void setAdapterMethodHandler(MethodInterfase<T> modelbinder, boolean searched) {

        methodHandler = new MethodsHandler<>(modelbinder, searched);
    }


    private final MyRealtime.ValueListener noticesRealtimeListener = new MyRealtime.ValueListener() {

        @Override
        public void ChildAdded(@NonNull DataSnapshot snapChild) {

            Notice notice = new Notice(snapChild);
            adapterNotices.insertByChange(notice);
        }

        @Override
        public void ChildChanged(@NonNull DataSnapshot snapChild) {

            Notice notice = new Notice(snapChild);
            adapterNotices.modifyNotice(notice);
        }

        @Override
        public void ChildRemoved(@NonNull DataSnapshot snapChild) {

            Notice notice = new Notice(snapChild);
            adapterNotices.removeNotice(notice.Id);
        }

        @Override
        public void finishAdded() {}
    };

    private final AdapterNotices.Interfase selectedInterfase = new AdapterNotices.Interfase() {

        @Override
        public void Selected(int position, @NonNull Notice notice) {

            SelectNotice(notice);
        }

        private void DummyVoid() {}
    };

    private final MyRealtime.ValueListener MyRealtimeListener = new MyRealtime.ValueListener() {

        @Override
        public void ChildAdded(@NonNull DataSnapshot dataSnapshot) {

            methodHandler.addMethod(dataSnapshot);
        }

        @Override
        public void ChildChanged(@NonNull DataSnapshot dataSnapshot) {

            methodHandler.modifyMethod(dataSnapshot);
        }

        @Override
        public void ChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            methodHandler.removeMethod(dataSnapshot);
        }

        @Override
        public void finishAdded() {}
    };


    // ----- RecyclerView ----- //

    public ItemBinder<T> itemViewBinder(ConditionalDataBinder<T> conditionalDataBinder) {

        return new CompositeItemBinder<>(conditionalDataBinder);
    }


    // ----- Interfase ----- //

    protected abstract void CreateActivity();
    protected abstract void StartProcess(ViewDataBinding binding);
    protected void ActionBar() {};
    protected abstract SliderView ActivityViews();
    protected void LaunchProcess() {};
    protected abstract void SelectNotice(Notice notice);
    protected void StopProcess() {};

    public interface PermissionsInterfase {

        default void Granted(int result) {};
    }

    public interface ToolBarsInterfase {

        default void setSearch(String search) {};
    }


    /*----- Inherents -----*/

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        myPermissions.PermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();
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

        realtimeNotices.removeValueListener();
        realtimeMenu.removeValueListener();
    }
}
