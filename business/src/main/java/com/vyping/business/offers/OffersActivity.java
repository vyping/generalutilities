package com.vyping.business.offers;

import static com.vyping.business.utilities.definitions.Instances.INSTANCE_OFFERS;
import static com.vyping.business.utilities.definitions.Modules.MODULE_ICON_OFFERS;
import static com.vyping.business.utilities.definitions.Modules.MODULE_NAME_OFFERS;
import static java.lang.Boolean.TRUE;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.business.BR;
import com.vyping.business.R;
import com.vyping.business.databinding.OffersActivityBinding;
import com.vyping.business.menu.MenuActivity;
import com.vyping.business.utilities.models.offers.OfferBinder;
import com.vyping.business.utilities.models.offers.OfferHandler;
import com.vyping.business.utilities.models.offers.OfferMethods;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.activities.MyRecyclerActivity;
import com.vyping.masterlibrary.adapters.recyclerview.adapter.MyRecyclerAdapter;

public class OffersActivity extends MyRecyclerActivity<OfferMethods> {

    public OffersActivityBinding binding;


    // ----- SetUp ----- //

    @Override
    protected void CreateActivity() {

        createActivity(OffersActivity.this, R.layout.offers_activity, MODULE_ICON_OFFERS, MODULE_NAME_OFFERS, MenuActivity.class);
        setFirebaseService(INSTANCE_OFFERS);
    }

    @Override
    protected void InherentsViews() {

        setSearchBar(R.id.Cl_MainContainer, new ToolBarsInterfase() {

            @Override
            public void setSearch(String search) {

                ToolBarsInterfase.super.setSearch(search);
            }

            private void DummyVoid() {}
        });
    }

    @Override
    protected void StartProcess(ViewDataBinding binding) {

        setRecyclerHandler(new OfferHandler(), TRUE, new OfferBinder(BR.offerMethods, R.layout.offer_holder));

        this.binding = (OffersActivityBinding) binding;
        this.binding.setOffersActivity(OffersActivity.this);
    }


    // ----- Listeners ModelMethods ----- //

    public final MyRecyclerAdapter.Interfase<OfferMethods> adapterInterfase = new MyRecyclerAdapter.Interfase<>()  {

        @Override
        public void OnClick(RecyclerView.ViewHolder viewHolder, @NonNull View view, OfferMethods item, int position) {

            new LogCat("Interfase: Click");
        }

        @Override
        public void OnLongClick(RecyclerView.ViewHolder viewHolder, @NonNull View view, OfferMethods item, int position) {

            new LogCat("Interfase: LongClick");
        }

        @Override
        public void OnDoubleClick(RecyclerView.ViewHolder viewHolder, @NonNull View view, OfferMethods item, int position) {

            new LogCat("Interfase: DoubleClick");
        }

        @Override
        public void OnTouch(RecyclerView.ViewHolder viewHolder, @NonNull View view, OfferMethods item, int position) {

            new LogCat("Interfase: Touch");
        }
    };
}