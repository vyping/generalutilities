package com.vyping.masterlibrary.ActionBar;

import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import com.vyping.masterlibrary.GestureListeners.MyGesturesListener;
import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.databinding.ActionBarBinding;
import com.vyping.masterlibrary.menu.side.MySideMenu;

public class MyActionBar {

    public static ActionBarBinding binding;
    public MySideMenu mySideMenu;

    private View viewActionBar;


    //----- Setup - Section-----//

    public MyActionBar(Context context, Interfase interfase) {

        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();

        if (actionBar != null) {

            binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.actionbar, null, false);

            actionBar.setDisplayOptions(DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(binding.getRoot(), new Toolbar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));

            viewActionBar = actionBar.getCustomView();

            Toolbar parent = (Toolbar) viewActionBar.getParent();
            parent.setPadding(0, 0, 0, 0);
            parent.setContentInsetsAbsolute(0, 0);

            //int resourceIcon = CONTEXT.getResources().getIdentifier("icon_start", "drawable", CONTEXT.getPackageName());
            //Drawable drawableIcon = new MyDrawable().extractFromResources(CONTEXT, resourceIcon);
           // binding.BtnMenu.setImageDrawable(drawableIcon);

            int resource = context.getResources().getIdentifier("logo_actionbar", "drawable", context.getPackageName());
            Drawable drawable = new MyDrawable().extractFromResources(context, resource);
            binding.IvMainIcon.setImageDrawable(drawable);

            new MyGesturesListener(binding.BtnMenu, new MyGesturesListener.Interfase() {

                @Override
                public void OnClick(@NonNull View view) {

                    interfase.ClickMenuButton(view);
                }

                private void DummyVoid() {};
            });
        }
    }


    // ----- Tools ----- //

    public ImageButton getButtonMenu() {

        return binding.BtnMenu;
    }


    // ----- Interface ----- //

    public interface Interfase {

        void ClickMenuButton(View view);
    }
}
