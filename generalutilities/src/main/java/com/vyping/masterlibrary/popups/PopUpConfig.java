package com.vyping.masterlibrary.popups;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;

import com.vyping.masterlibrary.Common.MyDisplay;
import com.vyping.masterlibrary.Common.MyServices;
import com.vyping.masterlibrary.Firebase.CloudMessaging;
import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.Preferences.MyAuthPreferences;
import com.vyping.masterlibrary.Preferences.MyConfigPreferences;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.dialogs.DialogChooseOption;

import java.util.Objects;

public class PopUpConfig {

    private Context context;
    private PopupWindow popUp;

    private View inflate, actionBar;
    private SwitchCompat switch1, switch2;
    private Button Btn_LogOut;

    private boolean darkMode, animations;
    private int delay;


    /**
     * ------  SetUp Section
     */

    @SuppressLint("SetTextI18n")
    public PopUpConfig(@NonNull Context context, View actionBar) {

        setParameters(context, actionBar);
        setPopUp();
        setPopUpViews();
    }

    private void setParameters(Context thisContext, View ActionBar) {

        context = thisContext;
        actionBar = ActionBar;

        int theme = new MyConfigPreferences().getAppTheme(context);
        darkMode = TRUE;


        animations = new MyConfigPreferences().getAnimations(context);
        delay = 500;
    }

    private void setPopUp() {

        final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflate = Objects.requireNonNull(inflater).inflate(R.layout.popup_config, null, false);

        popUp = new PopupWindow(context);
        popUp.setContentView(inflate);
        popUp.setHeight(new MyDisplay().displayHeight(context));
        popUp.setWidth(new MyDisplay().displayWidth(context));
        popUp.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        popUp.setAnimationStyle(R.style.blink_animation);
        popUp.showAsDropDown(actionBar, 0, -actionBar.getHeight());
        popUp.setOutsideTouchable(true);
        popUp.setFocusable(false);
        popUp.update();
    }

    private void setPopUpViews() {

        LinearLayout Ll_Config = inflate.findViewById(R.id.Ll_Config);
        Ll_Config.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dismissPopUp();
            }

            public void DummyVoid() {
            }
        });
    }


    /**
     * ------  Options Buttons - Section
     */

    @SuppressLint("SetTextI18n")
    public void setOptionTheme(ThemeInterfase interfase) {

        Drawable drawable = new MyDrawable().extractFromResources(context, R.drawable.icon_darkmode);

        switch1 = inflate.findViewById(R.id.Swt_Cfg_Option1);
        switch1.setText("Modo Oscuro");
        switch1.setCompoundDrawables(drawable, null, null, null);
        switch1.setChecked(darkMode);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                darkMode = isChecked;

                context.setTheme(R.style.AppTheme);
                new MyConfigPreferences().setAppTheme(context, R.style.AppTheme);

                interfase.changeDarkMode(darkMode);
            }

            private void DummyVoid() {
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void setOptionAnimation(AnimationInterfase interfase) {

        Drawable drawable = new MyDrawable().extractFromResources(context, R.drawable.icon_animation);

        switch2 = inflate.findViewById(R.id.Swt_Cfg_Option2);
        switch2.setText("Animaciones");
        switch2.setCompoundDrawables(drawable, null, null, null);
        switch2.setChecked(animations);
        switch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                animations = isChecked;

                new MyConfigPreferences().setAnimations(context, animations);

                interfase.changeAnimation(animations);
            }

            private void DummyVoid() {
            }
        });
    }

    @SuppressLint("SetTextI18n")
    public void showOptionLogOut(ConfigListener interfase) {

        Drawable drawable = new MyDrawable().extractFromResources(context, R.drawable.icon_logout);

        Btn_LogOut = inflate.findViewById(R.id.Btn_Cfg_LogOut);
        Btn_LogOut.setText("Cerrar Sesión");
        Btn_LogOut.setCompoundDrawables(drawable, null, null, null);
        Btn_LogOut.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialogCloseSession(interfase);
            }

            public void DummyVoid() {
            }
        });
    }


    /**
     * ------  SetUp Section
     */

    private void dialogCloseSession(@NonNull ConfigListener interfase) {

        String instance = interfase.showDialogLogout();

        new DialogChooseOption(context, R.array.Dialog_LogOut) {

            @Override
            protected boolean NegativeButton() {

                return true;
            }

            @Override
            protected boolean PositiveButton() {

                new MyServices().closeServices(context);
                new CloudMessaging().resetCloudMessageToken(instance, "General/TokenFCM");
                new MyAuthPreferences().ResetAuthData(context);

                interfase.closeSession();

                return false;
            }
        };
    }


    /**
     * ------  AditionalTools - Section
     */


    private void dismissPopUp() {

        popUp.dismiss();
    }


    /**
     * -------- Listeners Section
     */

    public interface ThemeInterfase {

        void changeDarkMode(boolean active);
    }

    public interface AnimationInterfase {

        void changeAnimation(boolean active);
    }

    public interface ConfigListener {

        String showDialogLogout();

        void closeSession();
    }
}


