package com.vyping.libraries.shop;

import static android.view.DragEvent.ACTION_DRAG_ENDED;
import static android.view.DragEvent.ACTION_DRAG_ENTERED;
import static android.view.DragEvent.ACTION_DRAG_STARTED;
import static android.view.DragEvent.ACTION_DROP;
import static com.vyping.libraries.utilities.definitions.Instances.INSTANCE_SHOP;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_ICON_SHOP;
import static com.vyping.libraries.utilities.definitions.Modules.MODULE_NAME_SHOP;

import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;

import com.google.firebase.database.DataSnapshot;
import com.vyping.libraries.R;
import com.vyping.libraries.activity.MainActivity;
import com.vyping.libraries.databinding.ShopActivityBinding;
import com.vyping.libraries.utilities.models.products.ProductBinder;
import com.vyping.libraries.utilities.models.products.ProductMethods;
import com.vyping.libraries.utilities.models.products.ProductsHandler;
import com.vyping.masterlibrary.BR;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Firebase.MyRealtime;
import com.vyping.masterlibrary.aplication.BaseActivity;
import com.vyping.masterlibrary.popups.PopUpConfig;
import com.vyping.masterlibrary.views.recyclerview.adapter.BindingRecyclerViewAdapter;
import com.vyping.masterlibrary.views.recyclerview.adapter.ClickHandler;
import com.vyping.masterlibrary.views.recyclerview.adapter.LongClickHandler;
import com.vyping.masterlibrary.views.recyclerview.adapter.binder.CompositeItemBinder;
import com.vyping.masterlibrary.views.recyclerview.adapter.binder.ItemBinder;

public class ShopActivity extends BaseActivity implements BaseActivity.StartCallBack {

    private ShopActivityBinding binding;
    private ProductsHandler productsHandler;


    // ----- SetUp ----- //

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        CreateBindingActivity(ShopActivity.this, R.layout.shop_activity, MODULE_ICON_SHOP, MODULE_NAME_SHOP, MainActivity.class);
        setSearchBar(binding.ClMainContainer.getId(), new ToolBarsInterfase() {

            @Override
            public void setSearch(String search) {

                productsHandler.setSearch(search);
            }

            private void DummyVoid() {}
        });
        setFirebaseService(INSTANCE_SHOP, MyRealtimeListener);
    }

    @Override
    public void SetStartBindingProcess(ViewDataBinding binding) {

        this.productsHandler = new ProductsHandler();

        this.binding = (ShopActivityBinding) binding;
        this.binding.setProductsHandler(productsHandler);
        this.binding.setShopActivity(ShopActivity.this);
    }

    @Override
    public void SetActionBar() {

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

                        // new MyActivity().Start(context, LoginActivity.class, TRUE);
                    }
                });
            }

            private void DummyVoid() {}
        });
        actionBar.showButtonTools();
    }

    @Override
    public boolean SetActivityViews() {

        binding.FlMarketCar.setOnDragListener(onDragListener);

        return true;
    }


    // ----- Methods ----- //

    public ItemBinder<ProductMethods> itemViewBinder() {

        return new CompositeItemBinder<>(new ProductBinder(BR.product, R.layout.shop_holder));
    }

    public ClickHandler<ProductMethods> clickHandler() {

        return new ClickHandler<ProductMethods>() {

            @Override
            public void onClick(BindingRecyclerViewAdapter.ViewHolder holder, View view, ProductMethods viewModel) {

                Toast.makeText(ShopActivity.this, "CLICK: ", Toast.LENGTH_SHORT).show();
            }
        };
    }

    public LongClickHandler<ProductMethods> longClickHandler() {

        return new LongClickHandler<ProductMethods>() {

            @Override
            public void onLongClick(BindingRecyclerViewAdapter.ViewHolder holder, View view, ProductMethods viewModel) {

                Toast.makeText(ShopActivity.this, "LONG CLICK: ", Toast.LENGTH_SHORT).show();
            }
        };
    }


    // ----- Listeners Methods ----- //

    private final MyRealtime.ValueListener MyRealtimeListener = new MyRealtime.ValueListener() {

        @Override
        public void ChildAdded(@NonNull DataSnapshot snapChild) {

            productsHandler.addProduct(snapChild);
        }

        @Override
        public void ChildChanged(@NonNull DataSnapshot snapChild) {

            productsHandler.modifyProduct(snapChild);
        }

        @Override
        public void ChildRemoved(@NonNull DataSnapshot snapChild) {

            productsHandler.removeProduct(snapChild);
        }

        @Override
        public void finishAdded() {}
    };

    private final View.OnDragListener onDragListener = new View.OnDragListener() {

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