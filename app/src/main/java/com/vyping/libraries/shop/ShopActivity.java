package com.vyping.libraries.shop;

import static android.view.DragEvent.ACTION_DRAG_ENDED;
import static android.view.DragEvent.ACTION_DRAG_ENTERED;
import static android.view.DragEvent.ACTION_DRAG_STARTED;
import static android.view.DragEvent.ACTION_DROP;
import static com.vyping.libraries.utilities.definitions.Instances.INSTANCE_SHOP;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_ICON_SHOP;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_NAME_SHOP;

import static java.lang.Boolean.TRUE;

import android.view.DragEvent;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.libraries.R;
import com.vyping.libraries.activity.MainActivity;
import com.vyping.libraries.databinding.ShopActivityBinding;
import com.vyping.libraries.utilities.models.products.ProductBinder;
import com.vyping.libraries.utilities.models.products.ProductMethods;
import com.vyping.libraries.utilities.models.products.ProductsHandler;
import com.vyping.libraries.utilities.models.products.TitleBinder;
import com.vyping.masterlibrary.BR;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.activities.MyRecyclerActivity;
import com.vyping.masterlibrary.adapters.recyclerview.adapter.MyRecyclerAdapter;
import com.vyping.masterlibrary.popups.PopUpConfig;

public class ShopActivity extends MyRecyclerActivity<ProductMethods> {

    private ShopActivityBinding binding;


    // ----- SetUp ----- //

    @Override
    protected void CreateActivity() {

        createActivity(ShopActivity.this, R.layout.shop_activity, MODULE_ICON_SHOP, MODULE_NAME_SHOP, MainActivity.class);
        setSearchBar(binding.ClMainContainer.getId(), new ToolBarsInterfase() {

            @Override
            public void setSearch(String search) {}

            private void DummyVoid() {}
        });
        setFirebaseService(INSTANCE_SHOP);
    }

    @Override
    protected void StartProcess(ViewDataBinding binding) {

        setRecyclerHandler(new ProductsHandler(), TRUE, new TitleBinder(BR.productMethod, R.layout.title_holder), new ProductBinder(BR.productMethod, R.layout.shop_holder));

        GridLayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        mLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup()  {

            @Override
            public int getSpanSize(int position) {

                if (recyclerHandler.methodsDisplayed.get(position).getDescription().equals("") == TRUE) {

                    return 2;

                } else {

                    return 1;
                }
            }

            private void DummyVoid() {}
        });

        this.binding = (ShopActivityBinding) binding;
        this.binding.setShopActivity(ShopActivity.this);

        this.binding.RvNotRecyclerView.setLayoutManager(mLayoutManager);
    }

    @Override
    protected void ActionBar() {

        actionBar.setButtonOption(R.drawable.icon_configuration, new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                PopUpConfig popUpConfig = new PopUpConfig(context, actionBar.getActionBar());
                popUpConfig.setOptionTheme(new PopUpConfig.ThemeInterfase() {

                    @Override
                    public void changeDarkMode(boolean active) {

                        //new MyActivity().RestartAndApplyChanges(context, MenuActivity.class);
                    }

                    private void DummyVoid() {}
                });
                popUpConfig.showOptionLogOut(new PopUpConfig.ConfigListener() {

                    @Override
                    public String showDialogLogout() {

                        //return new GeneralTools().getRealtimeInstance(SESSIOn.Type);

                        return "";
                    }

                    @Override
                    public void closeSession() {

                        // new MyActivity().Start(context, MyLoginActivity.class, TRUE);
                    }
                });
            }

            private void DummyVoid() {}
        });
        actionBar.showButtonTools();
    }

    @Override
    public void setActivityViews() {

        binding.FlMarketCar.setOnDragListener(onDragListener);
    }


    // ----- Listeners ModelMethods ----- //

    public final MyRecyclerAdapter.Interfase<ProductMethods> adapterInterfase = new MyRecyclerAdapter.Interfase<>()  {

        @Override
        public void OnClick(RecyclerView.ViewHolder viewHolder, @NonNull View view, ProductMethods item, int position) {

            new LogCat("Interfase: Click");
        }

        @Override
        public void OnLongClick(RecyclerView.ViewHolder viewHolder, @NonNull View view, ProductMethods item, int position) {

            new LogCat("Interfase: LongClick");
        }

        @Override
        public void OnDoubleClick(RecyclerView.ViewHolder viewHolder, @NonNull View view, ProductMethods item, int position) {

            new LogCat("Interfase: DoubleClick");
        }

        @Override
        public void OnTouch(RecyclerView.ViewHolder viewHolder, @NonNull View view, ProductMethods item, int position) {

            new LogCat("Interfase: Touch");
        }
    };

    private final View.OnDragListener onDragListener = new View.OnDragListener()  {

        @Override
        public boolean onDrag(View sourceView, @NonNull DragEvent dragEvent) {

            switch (dragEvent.getAction()) {

                case ACTION_DRAG_STARTED: {

                    new LogCat("STARTED");
                    break;

                } case ACTION_DRAG_ENTERED: {

                    new LogCat("ENTERED");
                    break;

                } case ACTION_DROP: {

                    new LogCat("DROP");
                    break;

                } case ACTION_DRAG_ENDED: {

                    View draggedView = (View) dragEvent.getLocalState();

                    break;

                } default: { break; }
            }

            return true;
        }

        private void DummyVoid() {}
    };
}