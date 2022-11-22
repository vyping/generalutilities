package com.vyping.masterlibrary.menu.side;

import static com.vyping.masterlibrary.aplication.MyApplication.ACCOUNT;
import static com.vyping.masterlibrary.models.accounts.Account.ACCOUNT_CLIENT;
import static com.vyping.masterlibrary.models.accounts.Account.ACCOUNT_GUEST;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.Animations.MyAnimation;
import com.vyping.masterlibrary.Common.MyDisplay;
import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.GestureListeners.MyGesturesListener;
import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.Json.JsonFile;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.adapters.recyclerview.adapter.MyRecyclerAdapter;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerCompositeBinder;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerItemBinderInterfase;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandler;
import com.vyping.masterlibrary.databinding.SideMenuBinding;
import com.vyping.masterlibrary.menu.side.holders.SideMenuHolder;
import com.vyping.masterlibrary.models.menus.Menu;
import com.vyping.masterlibrary.time.MyDelay;

import org.json.JSONObject;

public class MySideMenu {

    private final Context context;
    public static SideMenuBinding binding;
    public Interfase interfase;
    private static PopupWindow popup;

    private final View viewParent;

    public static String ThisModule;
    public static boolean displayed;

    public RecyclerHandler<SideMenuHolder.Methods> mainMenuMethodsHandler;


    // ----- SetUp ----- //

    public MySideMenu(@NonNull View viewParent, boolean mainActivity, Interfase myInterfase) {

        this.context = viewParent.getContext();
        this.viewParent = viewParent;
        this.mainMenuMethodsHandler = new RecyclerHandler<>(new SideMenuHolder.Handler(), FALSE);

        ThisModule = context.getClass().getCanonicalName();
        interfase = myInterfase;
        displayed = FALSE;

        setParameters();

        if (!mainActivity) {

            if (ACCOUNT == null) {

                setStatusOffLogged();

            } else {

                setStatusOnLogged();
            }
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setParameters() {

        LayoutInflater layoutInflater = LayoutInflater.from(context);

        binding = DataBindingUtil.inflate(layoutInflater, R.layout.sidemenu, null, false);
        binding.setSidemenu(this);

        new MyGesturesListener(binding.BtnSession, new MyGesturesListener.Interfase() {

            @Override
            public void OnClick(@NonNull View view) {

                interfase.clickSession(binding.BtnSession);
                dismiss();
            }

            private void DummyVoid() {};
        });
    }


    // ----- Methods ----- //

    public MySideMenu setStatusAnonimously() {

        Drawable drawable = new MyDrawable().toInsertOnButton(context, R.drawable.icon_mini_enter);

        binding.TvName.setText(ACCOUNT_GUEST);
        binding.TvType.setText("Sin Información...");
        binding.BtnSession.setCompoundDrawables(drawable, null, null, null);
        binding.BtnSession.setText("Iniciar Sesión");

        getMenu();
        hide();

        return this;
    }

    public MySideMenu setStatusOnLogged() {

         if (ACCOUNT.getType().equals(ACCOUNT_CLIENT)) {

            Drawable drawable = new MyDrawable().toInsertOnButton(context, R.drawable.icon_mini_exit);

            binding.TvName.setText(ACCOUNT_CLIENT);
            binding.TvType.setText(ACCOUNT.getName());
            binding.BtnSession.setCompoundDrawables(drawable, null, null, null);
            binding.BtnSession.setText("Cerrar Sesión");

        } else {

            Drawable drawable = new MyDrawable().toInsertOnButton(context, R.drawable.icon_mini_exit);

            binding.TvName.setText(ACCOUNT.getType());
            binding.TvType.setText(ACCOUNT.getName());
            binding.BtnSession.setCompoundDrawables(drawable, null, null, null);
            binding.BtnSession.setText("Cerrar Sesión");
        }

        getMenu();
        hide();

        return this;
    }

    public MySideMenu setStatusOffLogged() {

        Drawable drawable = new MyDrawable().toInsertOnButton(context, R.drawable.icon_mini_enter);

        binding.TvName.setText(ACCOUNT_GUEST);
        binding.TvType.setText("Sin Información...");
        binding.BtnSession.setCompoundDrawables(drawable, null, null, null);
        binding.BtnSession.setText("Iniciar Sesión");

        getMenu();
        hide();

        return this;
    }

    private void getMenu() {

        try {

            String state = ACCOUNT_GUEST;

            if (ACCOUNT.getType().equals(ACCOUNT_CLIENT)) {

                if (ACCOUNT.getLogged()) {

                    state = ACCOUNT_CLIENT;
                }

            } else {

                if (ACCOUNT.getStatus()) {

                    if (ACCOUNT.getLogged()) {

                        state = ACCOUNT.getType();
                    }
                }
            }

            String menu = new MyString().reduce(state);
            int rawFile = context.getResources().getIdentifier("sidemenu_" + menu, "raw", context.getPackageName());

            mainMenuMethodsHandler.resetMethods();

            new JsonFile(context, rawFile, new JsonFile.Interface() {

                @Override
                public void GetJsonObject(String key, JSONObject jsonObject) {

                    Menu menu = new Menu(key, jsonObject);
                    menu.Current = context.getClass().getSimpleName();
                    SideMenuHolder.Methods mainMenuMethods = new SideMenuHolder.Methods(menu);

                    mainMenuMethodsHandler.addMethod(mainMenuMethods);
                }

                private void DummyVoid() {}
            });

        } catch (Exception ignored) {}
    }


    // ----- Tools ----- //

    private void setPopUp() {

        TypedValue typedValue = new TypedValue();

        if (context.getTheme().resolveAttribute(android.R.attr.actionBarSize, typedValue, true)) {

            int displayHeight =  new MyDisplay().displayHeight(context);
            int actionBarHeight = TypedValue.complexToDimensionPixelSize(typedValue.data, context.getResources().getDisplayMetrics());
            int Yoff = (int) new MyDisplay().dpsToPxs(context, actionBarHeight);

            popup = new PopupWindow(context);
            popup.setContentView(binding.getRoot());
            popup.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            popup.setHeight(displayHeight - actionBarHeight);
            popup.showAtLocation(viewParent, Gravity.NO_GRAVITY, 0, Yoff);
            popup.setOutsideTouchable(true);
            popup.setFocusable(false);
            popup.update();

            new MyAnimation().HorizontalTranslation(binding.getRoot(), 100, -200, 0);
        }
    }

    public void setStatus() {

        if (displayed) {

            hide();

        } else {

            show();
        }
    }

    public void show() {

        setPopUp();

        displayed = TRUE;
    }

    public static void hide() {

        if (popup != null) {

            if (popup.isShowing() && displayed == TRUE) {

                new MyAnimation().HorizontalTranslation(binding.getRoot(), 100, 0, -200);
                new MyDelay(100).interfase(new MyDelay.Interfase() {

                    @Override
                    public void Execute() {

                        popup.dismiss();
                    }
                });
            }
        }

        displayed = FALSE;
    }

    public void dismiss() {

        if (popup != null) {

            if (popup.isShowing()) {

                popup.dismiss();
            }
        }
    }


    // ----- RecyclerView ----- //

    public RecyclerItemBinderInterfase<SideMenuHolder.Methods> getMainMenuBinder() {

        return new RecyclerCompositeBinder<>(new SideMenuHolder.Binder());
    }

    public ObservableArrayList<SideMenuHolder.Methods> getMainMenuHandler() {

        return mainMenuMethodsHandler.methodsDisplayed;
    }

    public final MyRecyclerAdapter.Interfase<SideMenuHolder.Methods> getMainMenuInterfase = new MyRecyclerAdapter.Interfase<>()  {

        @Override
        public void OnClick(ViewDataBinding binding, RecyclerView.ViewHolder viewHolder, @NonNull View view, SideMenuHolder.Methods mainMenuMethods, int position) {}

        @Override
        public void OnLongClick(ViewDataBinding binding, RecyclerView.ViewHolder viewHolder, @NonNull View view, @NonNull SideMenuHolder.Methods mainMenuMethods, int position) {}

        @Override
        public void OnDoubleClick(ViewDataBinding binding, RecyclerView.ViewHolder viewHolder, @NonNull View view, SideMenuHolder.Methods mainMenuMethods, int position) {}

        @Override
        public void OnTouch(ViewDataBinding binding, RecyclerView.ViewHolder viewHolder, @NonNull View view, SideMenuHolder.Methods mainMenuMethods, int position) {}
    };


    // ----- Interface ----- //

    public interface Interfase {

        void clickSession(Button button);
    }
}
