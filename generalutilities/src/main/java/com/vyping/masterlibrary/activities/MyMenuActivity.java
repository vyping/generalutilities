package com.vyping.masterlibrary.activities;

import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
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
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.smarteist.autoimageslider.SliderView;
import com.vyping.masterlibrary.ActionBar.MyActionBar;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.Common.MyPermissions;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.ToolBars.MyToolBars;
import com.vyping.masterlibrary.adapters.recyclerview.adapter.MyRecyclerAdapter;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerCompositeBinder;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerConditionalBinder;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerItemBinderInterfase;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandler;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandlerInterfase;
import com.vyping.masterlibrary.adapters.sliderview.adapter.MySliderviewAdapter;
import com.vyping.masterlibrary.adapters.sliderview.binder.SliderCompositeBinder;
import com.vyping.masterlibrary.adapters.sliderview.binder.SliderConditionalBinder;
import com.vyping.masterlibrary.adapters.sliderview.binder.SliderItemBinderInterfase;
import com.vyping.masterlibrary.adapters.sliderview.handler.SliderHandler;
import com.vyping.masterlibrary.adapters.sliderview.handler.SliderHandlerInterfase;
import com.vyping.masterlibrary.aplication.MyApplication;

public abstract class MyMenuActivity<Preview, Menu> extends AppCompatActivity {

    public MyApplication application;
    public Context context;
    public ViewDataBinding binding;

    public MyActionBar actionBar;
    public MyToolBars myToolBars;
    public MyRealtime previeRealtime, menuRealtime;
    private MyPermissions myPermissions;

    public SliderView sliderView;

    public SliderItemBinderInterfase<Preview> previewBinder;
    public SliderHandler<Preview> previewHandler;
    public RecyclerItemBinderInterfase<Menu> menuBinder;
    public RecyclerHandler<Menu> menuHandler;
    public compositeInterfase<Preview, Menu> compositeInterfase;

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

    public void createActivity(Activity activity, int layout, MyMenuActivity.compositeInterfase<Preview, Menu> compositeInterfase, Class backActivity) {

        setStartProcess(activity, layout, compositeInterfase, backActivity);
        setActionBar();
        setActivityViews();

        LaunchProcess();
    }

    public void setStartProcess(Activity activity, int layout, MyMenuActivity.compositeInterfase<Preview, Menu> compositeInterfase, Class<Activity> backActivity) {

        this.context = new MyActivity().setTheme(activity);
        this.binding = DataBindingUtil.inflate(LayoutInflater.from(activity), layout, null, false);
        this.compositeInterfase = compositeInterfase;
        this.backActivity = backActivity;

        View view = binding.getRoot();
        setContentView(view);

        StartProcess(binding);
    }

    public void setActionBar() {

        ///this.actionBar = new MyActionBar(context) {};

        ActionBar();
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setActivityViews() {

        sliderView = ActivityViews();

        this.sliderView.setAutoCycleDirection(SliderView.AUTO_CYCLE_DIRECTION_BACK_AND_FORTH);
        this.sliderView.setAutoCycle(true);
        this.sliderView.setIndicatorMargin(0);
        this.sliderView.setIndicatorRadius(4);
        this.sliderView.setScrollTimeInSec(6);
        this.sliderView.startAutoCycle();

        binding.executePendingBindings();
    }


    // ----- Firebase ----- //

    public void setFirebaseService(String instancePreview, String instanceMenu) {

        previeRealtime = new MyRealtime(instancePreview);
        previeRealtime.getValueChanges(previewRealtimeListener);

        menuRealtime = new MyRealtime(instanceMenu);
        menuRealtime.getValueChanges(menuRealtimeListener);
    }


    // ----- Methods ----- //

    public void setSearchBar(int container, ToolBarsInterfase interfase) {

        myToolBars = new MyToolBars(context, container) {};
        myToolBars.setSearchToolBar(new MyToolBars.SearchInterface() {

            @Override
            public void SelectedSearch(String search) {

                menuHandler.setSearch(search);

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


    // ----- Handlers ----- //

    @SafeVarargs
    public final void setPreviewHandler(SliderHandlerInterfase<Preview> methodInterfase, boolean searched, SliderConditionalBinder<Preview>... previewBinder) {

        this.previewBinder = new SliderCompositeBinder<Preview>(previewBinder);
        this.previewHandler = new SliderHandler<>(methodInterfase, searched);
    }

    @SafeVarargs
    public final void setMenuHandler(RecyclerHandlerInterfase<Menu> handlerInterfase, boolean searched, RecyclerConditionalBinder<Menu>... menuBinder) {

        this.menuBinder = new RecyclerCompositeBinder<Menu>(menuBinder);
        this.menuHandler = new RecyclerHandler<>(handlerInterfase, searched);
    }

    // ----- Listeners ----- //

    private final MyRealtime.ValueListener previewRealtimeListener = new MyRealtime.ValueListener() {

        @Override
        public void ChildAdded(@NonNull DataSnapshot snapChild) {

            previewHandler.addMethod(snapChild);
        }

        @Override
        public void ChildChanged(@NonNull DataSnapshot snapChild) {

            previewHandler.modifyMethod(snapChild);
        }

        @Override
        public void ChildRemoved(@NonNull DataSnapshot snapChild) {

            previewHandler.removeMethod(snapChild);
        }

        @Override
        public void finishAdded() {}
    };

    private final MyRealtime.ValueListener menuRealtimeListener = new MyRealtime.ValueListener() {

        @Override
        public void ChildAdded(@NonNull DataSnapshot dataSnapshot) {

            menuHandler.addMethod(dataSnapshot);
        }

        @Override
        public void ChildChanged(@NonNull DataSnapshot dataSnapshot) {

            menuHandler.modifyMethod(dataSnapshot);
        }

        @Override
        public void ChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            menuHandler.removeMethod(dataSnapshot);
        }

        @Override
        public void finishAdded() {}
    };

    public final MySliderviewAdapter.Interfase<Preview> noticesInterfase = new MySliderviewAdapter.Interfase<>()  {

        @Override
        public void Selected(int position, Preview notice) {

            compositeInterfase.SelectNotice(position, notice);
        }

        private void DummyVoid() {}
    };

    public final MyRecyclerAdapter.Interfase<Menu> menuInterfase = new MyRecyclerAdapter.Interfase<>() {

        @Override
        public void OnClick(ViewDataBinding binding, RecyclerView.ViewHolder viewHolder, @NonNull View view, Menu menu, int position) {

            compositeInterfase.SelectMenu(viewHolder, view, menu, position);
        };

        private void DummyVoid() {}
    };


    // ----- Interfase ----- //

    protected abstract void CreateActivity();
    protected abstract void StartProcess(ViewDataBinding binding);
    protected void ActionBar() {};
    protected abstract SliderView ActivityViews();
    protected void LaunchProcess() {};
    protected void StopProcess() {};
    protected void ActivityResults(int requestCode, int resultCode, Intent data) {};

    public interface compositeInterfase<Preview, Menu> {

        void SelectNotice(int position, Preview notice);
        void SelectMenu(RecyclerView.ViewHolder viewHolder, @NonNull View view, Menu menu, int position);
    }
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        ActivityResults(requestCode, resultCode, data);
    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

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

        previeRealtime.removeValueListener();
        menuRealtime.removeValueListener();
    }
}
