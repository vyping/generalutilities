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

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.vyping.masterlibrary.Common.MyApp;
import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.views.SetImageButton;

import java.util.ArrayList;
import java.util.HashMap;

public abstract class MyActionBar {

    private final Context context;
    private GestureDetector gestureDetector;
    private TouchInterface interfase;

    private View viewActionBar;
    private ImageButton Btn_actbarMenu;

    private boolean menuVisible;
    private static ArrayList<String> filters;


    //----- Setup - Section-----//

    public MyActionBar(Context Context, int icon, int subtitle) {

        context = Context;

        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();

        if (actionBar != null) {

            actionBar.setDisplayOptions(DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.myactionbar);

            viewActionBar = actionBar.getCustomView();

            Toolbar parent = (Toolbar) viewActionBar.getParent();
            parent.setPadding(0, 0, 0, 0);
            parent.setContentInsetsAbsolute(0, 0);

            SetActionBarViews(icon);
            setSubtitle(subtitle);
        }
    }

    public MyActionBar(Context Context, Drawable icon, int subtitle) {

        context = Context;

        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();

        if (actionBar != null) {

            actionBar.setDisplayOptions(DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.myactionbar);

            viewActionBar = actionBar.getCustomView();

            Toolbar parent = (Toolbar) viewActionBar.getParent();
            parent.setPadding(0, 0, 0, 0);
            parent.setContentInsetsAbsolute(0, 0);

            SetActionBarViews(icon);
            setSubtitle(subtitle);
        }
    }

    public MyActionBar(Context Context, int icon, String subtitle) {

        context = Context;

        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();

        if (actionBar != null) {

            actionBar.setDisplayOptions(DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.myactionbar);

            viewActionBar = actionBar.getCustomView();

            Toolbar parent = (Toolbar) viewActionBar.getParent();
            parent.setPadding(0, 0, 0, 0);
            parent.setContentInsetsAbsolute(0, 0);

            SetActionBarViews(icon);
            setSubtitle(subtitle);
        }
    }

    public MyActionBar(Context Context, Drawable icon, String subtitle) {

        context = Context;

        ActionBar actionBar = ((AppCompatActivity) context).getSupportActionBar();

        if (actionBar != null) {

            actionBar.setDisplayOptions(DISPLAY_SHOW_CUSTOM);
            actionBar.setDisplayShowCustomEnabled(true);
            actionBar.setCustomView(R.layout.myactionbar);

            viewActionBar = actionBar.getCustomView();

            Toolbar parent = (Toolbar) viewActionBar.getParent();
            parent.setPadding(0, 0, 0, 0);
            parent.setContentInsetsAbsolute(0, 0);

            SetActionBarViews(icon);
            setSubtitle(subtitle);
        }
    }

    public void SetActionBarViews(Drawable icon) {

        String title = new MyApp().getName(context);
        TextView Tv_actbarTitle = viewActionBar.findViewById(R.id.Tv_Acb_Titulo);
        Tv_actbarTitle.setText(title);

        Btn_actbarMenu = viewActionBar.findViewById(R.id.Btn_Acb_Menu);
        Btn_actbarMenu.setImageDrawable(icon);
        Btn_actbarMenu.setVisibility(VISIBLE);
        Btn_actbarMenu.setOnClickListener(view -> slideBars());

        String version = new MyApp().getVersion(context);
        TextView Tv_actbarVersion = viewActionBar.findViewById(R.id.Tv_Version);
        Tv_actbarVersion.setText(version);
    }

    public void SetActionBarViews(int icon) {

        Drawable drawableIcon = new MyDrawable().extractFromResources(context, icon);

        SetActionBarViews(drawableIcon);
    }


    //----- Proccess Tools - Section-----//

    public View getActionBar() {

        return viewActionBar;
    }

    public void setSubtitle(int subtitle) {

        String Subtitle = new MyString().getStringResources(context, subtitle);

        setSubtitle(Subtitle);
    }

    public void setSubtitle(String subtitle) {

        TextView Tv_actbarSubtitle = viewActionBar.findViewById(R.id.Tv_Acb_Subtitulo);
        Tv_actbarSubtitle.setText(subtitle);
    }

    public void showButtonTools() {

        int color = R.color.colorBlanco;
        Drawable image = new MyDrawable().changeDrawableColor(context, R.drawable.icon_tools, color);

        ImageButton btn_actbarTools = viewActionBar.findViewById(R.id.Btn_Acb_Tools);
        btn_actbarTools.setImageDrawable(image);
        btn_actbarTools.setVisibility(VISIBLE);
        btn_actbarTools.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                slideBars();
            }

            private void DummyVoid() {
            }
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
        new SetImageButton(context, Ll_Buttons, icon, R.attr.actbarToolsOptionButtons, R.style.ActionBarToolsOptionsButtons) {

            @Override
            protected void getButtonView(ImageButton imageButton) {
            }

            @Override
            protected void clickOnButton(View imageButton) {

                interfase.onClick(imageButton);
            }
        };
    }

    public void setButtonRestricted(int icon, int authorization, View.OnClickListener interfase) {

        if (authorization == 2) {

            LinearLayout Ll_Buttons = viewActionBar.findViewById(R.id.Ll_Options_Buttons);
            new SetImageButton(context, Ll_Buttons, icon, R.attr.actbarToolsOptionButtons, R.style.ActionBarToolsOptionsButtons) {

                @Override
                protected void getButtonView(ImageButton imageButton) {
                }

                @Override
                protected void clickOnButton(View imageButton) {

                    interfase.onClick(imageButton);
                }
            };
        }
    }

    public void setButtonsFilter(@NonNull HashMap<Integer, String> Filters, FilterInterface interfase) {

        filters = new ArrayList<>();

        LinearLayout Ll_Buttons = viewActionBar.findViewById(R.id.Ll_Options_Buttons);

        for (HashMap.Entry<Integer, String> filter : Filters.entrySet()) {

            int iconFilter = filter.getKey();
            String textFilter = filter.getValue();

            new SetImageButton(context, Ll_Buttons, iconFilter, R.attr.actbarToolsOptionButtons, R.style.ActionBarToolsOptionsButtons) {

                @Override
                protected void getButtonView(ImageButton imageButton) {
                }

                @Override
                protected void clickOnButton(View imageButton) {

                    if (!filters.contains(textFilter)) {

                        filters.add(textFilter);

                        Drawable background = new MyDrawable().changeDrawableColor(context, com.vyping.masterlibrary.R.drawable.actbar_button_tools, R.color.colorAzulClaro);
                        imageButton.setBackground(background);
                        interfase.addToFilter(textFilter);

                    } else if (filters.contains(textFilter)) {

                        filters.remove(textFilter);


                        Drawable background = new MyDrawable().changeDrawableColor(context, com.vyping.masterlibrary.R.drawable.actbar_button_tools, R.color.BlueDark11);
                        imageButton.setBackground(background);
                        interfase.removeFromFilter(textFilter);
                    }
                }
            };
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    public void setTouchListener(TouchInterface interfase) {

        this.interfase = interfase;

        gestureDetector = new GestureDetector(context, new MyGestureListener());
        viewActionBar.setOnTouchListener((v, event) -> gestureDetector.onTouchEvent(event));
    }


    //----- Listeners - Section-----//

    public class MyGestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }

        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {

            return true;
        }

        @Override
        public void onLongPress(MotionEvent e) {
        }

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

                } else if (velocityX > 0 && menuVisible == FALSE) {

                    slideBars();
                }
            }

            return true;
        }
    }


    //----- Aditional Tools - Section-----//

    private void slideBars() {

        LinearLayout Ll_Title = viewActionBar.findViewById(R.id.LL_Title);
        LinearLayout Ll_Tools = viewActionBar.findViewById(R.id.LL_Tools);


        if (menuVisible == TRUE) {

            // Slide to Left
            menuVisible = FALSE;

            int widthSubtitle = Ll_Title.getWidth();
            int extraSubtitle = Btn_actbarMenu.getWidth() + 32;

            Ll_Title.animate().translationX(-widthSubtitle + extraSubtitle).start();
            Ll_Tools.animate().translationX(0).start();

        } else {

            // Slide to Right
            menuVisible = TRUE;

            int widthTools = Ll_Tools.getWidth();
            int extraTools = Btn_actbarMenu.getWidth() + 32;

            Ll_Title.animate().translationX(0).start();
            Ll_Tools.animate().translationX(widthTools - extraTools).start();
        }
    }


    //----- Interface - Section-----//

    public interface TouchInterface {

        void OnFling();

        void DummyVoid();
    }

    public interface FilterInterface {

        void addToFilter(String filter);

        void removeFromFilter(String filter);
    }
}
