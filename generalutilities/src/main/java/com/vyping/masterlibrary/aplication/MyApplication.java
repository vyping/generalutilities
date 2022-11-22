package com.vyping.masterlibrary.aplication;

import static com.vyping.masterlibrary.authentication.MyAuthGoogle.REQUEST_CODE_GOOGLE_AUTH;
import static com.vyping.masterlibrary.definitions.Instances.INSTANCE_ACCOUNTS;
import static com.vyping.masterlibrary.definitions.Instances.INSTANCE_MAIN;
import static com.vyping.masterlibrary.definitions.Instances.MAIN_DATABASE;
import static com.vyping.masterlibrary.models.accounts.Account.ACCOUNT_CLIENT;
import static com.vyping.masterlibrary.models.accounts.Account.ACCOUNT_GUEST;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.messaging.FirebaseMessaging;
import com.vyping.masterlibrary.ActionBar.MyActionBar;
import com.vyping.masterlibrary.BR;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.Common.MyPhone;
import com.vyping.masterlibrary.Common.MyToast;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.Firebase.RealData;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.authentication.MyAuthGoogle;
import com.vyping.masterlibrary.definitions.Buckets;
import com.vyping.masterlibrary.definitions.Instances;
import com.vyping.masterlibrary.dialogs.MyHandlerDialog;
import com.vyping.masterlibrary.dialogs.news.DialogDisabled;
import com.vyping.masterlibrary.menu.side.MySideMenu;
import com.vyping.masterlibrary.models.accounts.AccountMethods;
import com.vyping.masterlibrary.models.accounts.Account;
import com.vyping.masterlibrary.models.orderproducts.OrderProduct;
import com.vyping.masterlibrary.time.MyTime;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MyApplication extends MultiDexApplication {

    private Context context;

    @SuppressLint("StaticFieldLeak")
    public static Activity ACTIVITY;
    private static List<Activity> activitys;

    public MyActionBar actionBar;
    public MySideMenu mySideMenu;

    private MyAuthGoogle myAuthGoogle;
    public static AccountMethods ACCOUNT;

    private static String LOG;
    public static ArrayList<String> USED_INSTANCES;
    private boolean mainActivity;


    // ----- Setup -----//

    @Override
    public void onCreate() {

        super.onCreate();

        activitys = Collections.synchronizedList(new LinkedList());
        USED_INSTANCES = new ArrayList<>();

        MultiDex.install(this);
        initActivityLifeCallback();
    }

    @Override
    protected void attachBaseContext(Context base) {

        super.attachBaseContext(base);

        MultiDex.install(context);
    }

    public void setActionBar() {

        this.actionBar = new MyActionBar(context, new MyActionBar.Interfase() {

            @Override
            public void ClickMenuButton(View view) {

                if (mySideMenu != null) {

                    mySideMenu.setStatus();
                }
            }

            private void DummyVoid() {}
        });
    }


    // ----- Authentication Methods ----- //

    public FirebaseUser getCurrentAccount() {

        return myAuthGoogle.getCurrentUser();
    }

    public void sessionStatus() {

        myAuthGoogle = new MyAuthGoogle().idToken(context);
        myAuthGoogle.getLoggedStatus(new UidInterfase() {

            @Override
            public void LoggedStatus(boolean logged) {

                if (logged) {

                    if (myAuthGoogle.getCurrentUser().isAnonymous()) {

                        FirebaseUser firebaseUser = myAuthGoogle.getCurrentUser();
                        updateUI(firebaseUser, TRUE);

                    } else {

                        FirebaseUser firebaseUser = myAuthGoogle.getCurrentUser();
                        updateUI(firebaseUser, FALSE);
                    }

                } else {

                    myAuthGoogle.logInSilently(context);
                }
            }

            @Override
            public void LoggedSilent() {

                GoogleSignInAccount googleSignInAccount = myAuthGoogle.lastAuthSigned(context);

                if (googleSignInAccount != null) {

                    if (googleSignInAccount.getAccount() != null) {

                        myAuthGoogle.logInWithGoogle(context, googleSignInAccount);

                    } else {

                        myAuthGoogle.logInAnonymously(context);
                    }

                } else {

                    myAuthGoogle.logInAnonymously(context);
                }
            }

            @Override
            public void LoggedAnonimously(FirebaseUser firebaseUser) {

                updateUI(firebaseUser, TRUE);
            }

            @Override
            public void LoggedSuccess(FirebaseUser firebaseUser) {

                updateUI(firebaseUser, FALSE);
            }

            @Override
            public void LogOutUser() {

                myAuthGoogle.logOutFirebase(context);
                updateUI(null, FALSE);

                new MyToast(context, "¡Sesión finalizada con éxito");
            }

            @Override
            public void Failed() {


            }
        });
    }

    public void sessionLogOut() {

        myAuthGoogle.logOutFromGoogle();
    }

    public void updateUI(FirebaseUser firebaseUser, boolean anonimously) {

        if (firebaseUser == null) {

            ACCOUNT.setAccount(new Account());
            ACCOUNT.setLogged(FALSE);

            mySideMenu.setStatusOffLogged();
            getLaunchActivity();

        } else {

            new Instances().setInstances(context, MAIN_DATABASE);
            new Buckets().setBuckets(context, MAIN_DATABASE);

            if (anonimously) {

                ACCOUNT.setAccount(firebaseUser);
                ACCOUNT.setUid(firebaseUser.getUid());
                ACCOUNT.setType(ACCOUNT_GUEST);
                ACCOUNT.setLogged(TRUE);

                mySideMenu.setStatusAnonimously();
                getLaunchActivity();

            } else {

                ACCOUNT.setAccount(firebaseUser);
                ACCOUNT.setLogged(TRUE);

                firebaseSearchLicense(firebaseUser);
            }
        }
    }

    public void setSideMenu(boolean mainActivity) {

        this.mainActivity = mainActivity;

        ImageButton imageButton = actionBar.getButtonMenu();
        mySideMenu = new MySideMenu(imageButton, mainActivity, new MySideMenu.Interfase() {

            @Override
            public void clickSession(Button button) {

                if (ACCOUNT.getLogged()) {

                    if (ACCOUNT.getType().equals(ACCOUNT_GUEST)) {

                        myAuthGoogle.launchAuthDialog(context);

                    } else {

                        sessionLogOut();
                    }

                } else {

                    myAuthGoogle.launchAuthDialog(context);
                }
            }

            private void DummyVoid() {}
        });
    }


    // ----- Activities Methods ----- //

    public Activity getCurrentActivity() {

        return ACTIVITY;
    }

    public void setCurrentActivity(Activity mCurrentActivity){

        ACTIVITY = mCurrentActivity;
    }

    private void getLaunchActivity() {

        if (ACCOUNT.getType().equals(ACCOUNT_CLIENT) || ACCOUNT.getType().equals(ACCOUNT_GUEST)) {

            if (!mainActivity) {

                new MyActivity().Start(context, "login.LogActivity", TRUE);
            }

        } else {

            new MyActivity().LaunchMainActivity(context);
        }
    }

    public void addActivity(Activity activity) {

        if (!activitys.contains(activity)) {

            activitys.add(activity);
        }
    }

    public static void removeActivity(Activity activity) {

        if (activitys.contains(activity)) {

            activitys.remove(activity);
            activity.finish();
        }
    }

    public static void removeAllActivity() {

        for (Activity activity : activitys) {

            activity.finish();
        }

        activitys.clear();
    }

    public void activityResults(int requestCode, Intent data) {

        if (requestCode == REQUEST_CODE_GOOGLE_AUTH) {

            myAuthGoogle.onActivityResult(context, requestCode, data);
        }
    }


    // ----- Firebase ----- //

    public void firebaseSearchLicense(@NonNull FirebaseUser firebaseUser) {

        new MyRealtime(INSTANCE_MAIN, firebaseUser.getUid()).getSingleValue(new MyRealtime.SingleListener() {

            @Override
            public void ValueListen(DataSnapshot dataSnapshot) {

                String account = new RealData(dataSnapshot).getString();

                new Instances().setInstances(context, account);
                new Buckets().setBuckets(context, account);

                firebaseGetDataAccount(firebaseUser);
            }

            @Override
            public void ValueNonExist(String error) {

                ACCOUNT.setUid(firebaseUser.getUid());
                ACCOUNT.setType(ACCOUNT_CLIENT);

                mySideMenu.setStatusOnLogged();
                getLaunchActivity();
            }
        });
    }

    private void firebaseGetDataAccount(@NonNull FirebaseUser firebaseUser) {

        new MyRealtime(INSTANCE_ACCOUNTS, firebaseUser.getUid()).getSingleValue(new MyRealtime.SingleListener() {

            @Override
            public void ValueListen(DataSnapshot dataSnapshot) {

                Account account = new Account(dataSnapshot);
                ACCOUNT.setUid(firebaseUser.getUid());
                ACCOUNT.setAccount(account);

                mySideMenu.setStatusOnLogged();
                requestCloudMessageToken(firebaseUser);
            }

            @Override
            public void ValueNonExist(String error) {

                ACCOUNT.setUid(firebaseUser.getUid());
                ACCOUNT.setType(ACCOUNT_CLIENT);
                ACCOUNT.setName(firebaseUser.getDisplayName());

                mySideMenu.setStatusOnLogged();
                getLaunchActivity();

                new MyToast(context, "¡Su cuenta ha sido eliminada!");
            }
        });
    }

    private void requestCloudMessageToken(@NonNull FirebaseUser firebaseUser) {

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<>() {

            @Override
            public void onComplete(@NonNull Task<String> task) {

                if (task.isSuccessful()) {

                    String token = task.getResult();
                    ACCOUNT.setToken(token);
                }

                firebaseUpdateParams(firebaseUser);
            }

            private void DummyVoid() {}
        });
    }

    private void firebaseUpdateParams(@NonNull FirebaseUser firebaseUser) {

        String uid = firebaseUser.getUid();
        String phone = firebaseUser.getPhoneNumber() != null ? firebaseUser.getPhoneNumber() : "";
        String mail = firebaseUser.getEmail() != null ? firebaseUser.getEmail() : "";
        String dispositive = new MyPhone().phoneModel();
        long lastLogin = new MyTime().getTimestamp();

        ACCOUNT.setUid(uid);
        ACCOUNT.setPhone(phone);
        ACCOUNT.setEmail(mail);
        ACCOUNT.setDispositive(dispositive);
        ACCOUNT.setLastLogin(lastLogin);
        ACCOUNT.updateOnFirebase(new MyRealtime.CompleteListener() {

            @Override
            public void Success() {

                if (ACCOUNT.getStatus()) {

                    getLaunchActivity();

                } else {

                    dialogDisabled();
                }
            }

            @Override
            public void Failure(String error) {

                new MyToast(context, "Error al actualizar su información");
            }
        });
    }


    // ----- Methods -----//

    public void startLog(String string) {

        LOG = string;

        Log.i("Desarrollo",  LOG);
    }

    public void startLog(String tag, String value) {

        LOG = tag + ": " + value;

        Log.i("Desarrollo",  LOG);
    }

    public void addLog(String string) {

        LOG = LOG + ", " + string;

        Log.i("Desarrollo",  LOG);
    }

    public void addLog(String tag, String value) {

        LOG = LOG + ", " + tag + ": " + value;

        Log.i("Desarrollo",  LOG);
    }

    private void initActivityLifeCallback() {

        registerActivityLifecycleCallbacks(new LifecycleCallback());
    }


    // ----- Dialog ----- //

    private void dialogDisabled() {

        DialogDisabled dialogDisabled = new DialogDisabled(context);

        new MyHandlerDialog<DialogDisabled, OrderProduct>(context, R.layout.dialog_disabled)
                .binding(BR.dialogDisabledBinding)
                .dialog(BR.dialogDisabledDialog)
                .variable(BR.dialogDisabledMethod, dialogDisabled)
                .show();
    }


    // ----- Tools -----//

    public Context getContext() {

        return context;
    }

    public void setContext(Context context) {

        this.context = context;
    }


    // ----- Callbacks -----//

    public static class LifecycleCallback implements ActivityLifecycleCallbacks {

        @Override
        public void onActivityCreated(@NonNull Activity activity, Bundle bundle) {

            String name  = activity.getClass().getSimpleName();
        }

        @Override
        public void onActivityStarted(@NonNull final Activity activity) {

            String name  = activity.getClass().getSimpleName();
        }

        @Override
        public void onActivityResumed(@NonNull Activity activity) {

            String name  = activity.getClass().getSimpleName();
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {}

        @Override
        public void onActivityPaused(@NonNull Activity activity) {

            String name  = activity.getClass().getSimpleName();
        }

        @Override
        public void onActivityStopped(@NonNull Activity activity) {

            String name  = activity.getClass().getSimpleName();
        }

        @Override
        public void onActivityDestroyed(@NonNull Activity activity) {

            String name  = activity.getClass().getSimpleName();
        }
    }


    // ----- Interface ----- //

    public interface UidInterfase {

        public void LoggedStatus(boolean logged);
        public void LoggedSilent();
        public void LoggedAnonimously(FirebaseUser firebaseUser);
        public void LoggedSuccess(FirebaseUser firebaseUser);
        public void LogOutUser();
        public void Failed();
    }


    // ----- Inherents -----//

    @Override
    public void onTerminate() {

        super.onTerminate();

        myAuthGoogle.removeAuthListener();
    }
}