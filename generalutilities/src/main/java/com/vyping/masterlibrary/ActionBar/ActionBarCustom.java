package com.vyping.masterlibrary.ActionBar;

import static android.view.View.VISIBLE;
import static androidx.appcompat.app.ActionBar.DISPLAY_SHOW_CUSTOM;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.vyping.masterlibrary.Common.GeneralTools;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.Views.SetImageButton;

public abstract class ActionBarCustom {

    private final Context context;
    private GestureDetector gestureDetector;
    private TouchInterface interfase;
    private View viewActionBar;
    private boolean menuVisible;


    //----- Setup - Section-----//

    @SuppressLint("ClickableViewAccessibility")
    public ActionBarCustom(Context Context, int icon, String subtitle) {

        context = Context;

        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();

        if (actionBar != null) {

            actionBar.setDisplayOptions(DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.actionbar_custom);

            viewActionBar = actionBar.getCustomView();
            viewActionBar.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));

            Toolbar parent = (Toolbar) viewActionBar.getParent();
            parent.setPadding(0, 0, 0, 0);
            parent.setContentInsetsAbsolute(0, 0);

            setTitle(icon);
            setSubtitle(subtitle);
            setVersion();
        }
    }


    //----- Proccess Tools - Section-----//

    public View setActionBar() {

     return viewActionBar;
    }

    public void setTitle(int icon) {

        String title = context.getString(R.string.app_name);
        Drawable image = ContextCompat.getDrawable(context, icon);

        TextView  Tv_actbarTitle = viewActionBar.findViewById(R.id.Tv_Acb_Titulo);
        Tv_actbarTitle.setText(title);

        ImageButton Btn_actbarMenu = viewActionBar.findViewById(R.id.Btn_Acb_Menu);
        Btn_actbarMenu.setImageDrawable(image);
        Btn_actbarMenu.setVisibility(VISIBLE);
        Btn_actbarMenu.setOnClickListener(view -> slideBars());
    }

    public void setSubtitle(String subtitle) {

        TextView  Tv_actbarSubtitle = viewActionBar.findViewById(R.id.Tv_Acb_Subtitulo);
        Tv_actbarSubtitle.setText(subtitle);
    }

    public void setVersion() {

        TextView  Tv_actbarVersion = viewActionBar.findViewById(R.id.Tv_Version);
        Tv_actbarVersion.setText(new GeneralTools().requestVersionApp());
    }

    public void showButtonTools() {

        Drawable image = ContextCompat.getDrawable(context, R.drawable.icon_tools);

        ImageButton Btn_actbarTools = viewActionBar.findViewById(R.id.Btn_Acb_Menu2);
        Btn_actbarTools.setImageDrawable(image);
        Btn_actbarTools.setVisibility(VISIBLE);
        Btn_actbarTools.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                slideBars();
            }

            private void DummyVoid() {}
        });

        LinearLayout Ll_Tools = viewActionBar.findViewById(R.id.LL_Tools);
        Ll_Tools.setVisibility(VISIBLE);

        new Handler().postDelayed(() -> {

            menuVisible = FALSE;
            slideBars();

        }, 500);
    }

    public void setButtonOption(int icon, View.OnClickListener interfase) {

        LinearLayout Ll_Buttons = viewActionBar.findViewById(R.id.Ll_Options_Buttons);
        new SetImageButton(context, Ll_Buttons, icon, R.attr.actbarToolsButtons, R.style.ActionBarToolButtons) {

            @Override
            protected void getButtonView(ImageButton imageButton) { }

            @Override
            protected void clickOnButton(View imageButton) {

                interfase.onClick(imageButton);
            }
        };
    }

    public void setTouchListener(TouchInterface interfase) {

        this.interfase = interfase;

        gestureDetector = new GestureDetector(context, new MyGestureListener());
    }


    //----- Listeners - Section-----//

    public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) { return true; }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {

            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) { }

        @Override
        public boolean onDoubleTap(MotionEvent e) {

            return true;
        }

        @Override
        public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {

            return true;
        }

        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2, float velocityX, float velocityY) {

            float xVel = Math.abs(velocityX), yVel = Math.abs(velocityY);
            int minVel = 2000, offPath = 2000;

            if (yVel > minVel && xVel < offPath) {

                interfase.OnFling();

            } else if (xVel > minVel && yVel < offPath) {

                if (velocityX < 0 && menuVisible == TRUE) {

                    slideBars();

                } else if (velocityX > 0  && menuVisible == FALSE) {

                    slideBars();
                }
            }

            return true;
        }
    }


    //----- Aditional Tools - Section-----//

    private void slideBars() {

        LinearLayout Ll_Title = viewActionBar.findViewById(R.id.LL_Title);
        LinearLayout Ll_iconTitle = viewActionBar.findViewById(R.id.LL_Icon_Title);
        LinearLayout Ll_Tools = viewActionBar.findViewById(R.id.LL_Tools);
        LinearLayout Ll_iconTools = viewActionBar.findViewById(R.id.LL_Icon_Tools);

        if (menuVisible == TRUE) {

            // Slide to Left
            menuVisible = FALSE;

            int widthSubtitle = Ll_Title.getWidth();
            int extraSubtitle = Ll_iconTitle.getWidth() + 32;

            Ll_Title.animate().translationX(- widthSubtitle + extraSubtitle).start();
            Ll_Tools.animate().translationX(0).start();

        } else {

            // Slide to Right
            menuVisible = TRUE;

            int widthTools = Ll_Tools.getWidth();
            int extraTools = Ll_iconTools.getWidth() + 32;

            Ll_Title.animate().translationX(0).start();
            Ll_Tools.animate().translationX(widthTools - extraTools).start();
        }
    }


    //----- Interface - Section-----//

    public interface TouchInterface {

        void OnFling();
        void DummyVoid();
    }
}
