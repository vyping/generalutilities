package com.vyping.libraries.activity;

import static com.vyping.libraries.utilities.definitions.Modules.MODULE_ICON_MAIN;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_NAME_MAIN;

import static java.lang.Boolean.FALSE;

import android.view.View;
import android.widget.AdapterView;

import androidx.databinding.ViewDataBinding;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.firebase.database.DataSnapshot;
import com.smarteist.autoimageslider.SliderView;
import com.vyping.libraries.R;
import com.vyping.libraries.databinding.MainActivityBinding;
import com.vyping.libraries.utilities.models.sizes.SizeBinder;
import com.vyping.libraries.utilities.models.sizes.SizeHandler;
import com.vyping.libraries.utilities.models.sizes.SizeMethods;
import com.vyping.masterlibrary.BR;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.activities.MyMenuActivity;
import com.vyping.masterlibrary.adapters.spinner.binder.SpinnerCompositeBinder;
import com.vyping.masterlibrary.adapters.spinner.binder.SpinnerItemBinderInterfase;
import com.vyping.masterlibrary.adapters.spinner.handler.SpinnerHandler;
import com.vyping.masterlibrary.models.notices.NoticeBinder;
import com.vyping.masterlibrary.models.notices.NoticeHandler;
import com.vyping.masterlibrary.models.notices.NoticeMethods;

public class MainActivity extends MyMenuActivity<NoticeMethods, NoticeMethods> {

    public MainActivityBinding binding;

    private GoogleSignInClient googleSignInClient;
    private GoogleSignInAccount googleSignInAccount;

    // ----- SetUp ----- //

    @Override
    protected void CreateActivity() {

        createActivity(MainActivity.this, R.layout.activity_main, MODULE_ICON_MAIN, MODULE_NAME_MAIN, menuInterfase);
        setFirebaseService("vyp-restaurant-notices", "vyp-restaurant-notices");
    }

    @Override
    protected void StartProcess(ViewDataBinding binding) {

        setPreviewHandler(new NoticeHandler(), FALSE, new NoticeBinder(BR.noticeMethod, R.layout.notice_holder));

        this.binding = (MainActivityBinding) binding;
        this.binding.setMainActivity(MainActivity.this);

        setSpinner();

        GoogleSignInOptions googleSignInOptions = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        googleSignInClient = GoogleSignIn.getClient(context, googleSignInOptions);
        googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
    }

    @Override
    protected void ActionBar() {}

    @Override
    protected SliderView ActivityViews() {

        return binding.SvMenNotices;
    }

    @Override
    protected void LaunchProcess() {}

    public SpinnerHandler<SizeMethods> spinnerHandler;

    public void setSpinner() {

        spinnerHandler = new SpinnerHandler<>(new SizeHandler(), FALSE);
        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                new LogCat("onItemSelected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {}
        });

        new MyRealtime("vyp-gym-shop").getValueChanges(new MyRealtime.ValueListener() {

            @Override
            public void ChildAdded(DataSnapshot dataSnapshot) {

                spinnerHandler.addMethod(dataSnapshot);
            }

            @Override
            public void ChildChanged(DataSnapshot dataSnapshot) {}

            @Override
            public void ChildRemoved(DataSnapshot dataSnapshot) {}
        });
    }

   public SpinnerItemBinderInterfase<SizeMethods> getSpinnerBinder() {

       return new SpinnerCompositeBinder<>(new SizeBinder(BR.sizeMethod, R.layout.size_holder));
   }

   private final MyMenuActivity.compositeInterfase<NoticeMethods, NoticeMethods> menuInterfase = new compositeInterfase<>() {

       @Override
       public void SelectNotice(NoticeMethods item) {}

       @Override
       public void SelectMenu(NoticeMethods item) {}
   };
}