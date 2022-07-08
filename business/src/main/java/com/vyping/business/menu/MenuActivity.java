package com.vyping.business.menu;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.vyping.business.utilities.definitions.Instances.INSTANCE_MENU;
import static com.vyping.business.utilities.definitions.Instances.INSTANCE_PREVIEWS;
import static com.vyping.business.utilities.definitions.Modules.MODULE_ICON_MENU;
import static com.vyping.business.utilities.definitions.Modules.MODULE_NAME_MENU;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.smarteist.autoimageslider.SliderView;
import com.vyping.business.BR;
import com.vyping.business.R;
import com.vyping.business.databinding.MenuActivityBinding;
import com.vyping.business.login.LogActivity;
import com.vyping.business.offers.OffersActivity;
import com.vyping.business.utilities.models.categories.CategoryBinder;
import com.vyping.business.utilities.models.categories.CategoryHandler;
import com.vyping.business.utilities.models.categories.CategoryMethods;
import com.vyping.business.utilities.models.previews.PreviewBinder;
import com.vyping.business.utilities.models.previews.PreviewHandler;
import com.vyping.business.utilities.models.previews.PreviewMethods;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.activities.MyMenuActivity;
import com.vyping.masterlibrary.popups.PopUpConfig;

public class MenuActivity extends MyMenuActivity<PreviewMethods, CategoryMethods> {

    public MenuActivityBinding binding;
    private RecyclerView.ViewHolder prevViewHolder;

    // ----- SetUp ----- //

    @Override
    protected void CreateActivity() {

        createActivity(MenuActivity.this, R.layout.menu_activity, MODULE_ICON_MENU, MODULE_NAME_MENU, compositeInterfase, LogActivity.class);
        setFirebaseService(INSTANCE_PREVIEWS, INSTANCE_MENU);
    }

    @Override
    public void StartProcess(ViewDataBinding binding) {

        setPreviewHandler(new PreviewHandler(), FALSE, new PreviewBinder(BR.previewMethods, R.layout.preview_holder));
        setMenuHandler(new CategoryHandler(), FALSE, new CategoryBinder(BR.categoryMethod, R.layout.category_holder));

        this.binding = (MenuActivityBinding) binding;
        this.binding.setMenuActivity(MenuActivity.this);
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

                        new MyActivity().RestartAndApplyChanges(context, MenuActivity.class);
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
    public SliderView ActivityViews() {

       return binding.SvMenNotices;
    }


    // ----- Methods ----- //



    // ----- Listeners ----- //


    // ----- Listeners ModelMethods ----- //

    private final MyMenuActivity.compositeInterfase<PreviewMethods, CategoryMethods> compositeInterfase = new compositeInterfase<>() {

        @Override
        public void SelectNotice(int position, @NonNull PreviewMethods previewMethods) {

            new MyActivity().Start(context, OffersActivity.class, TRUE);
        }

        @Override
        public void SelectMenu(@NonNull RecyclerView.ViewHolder viewHolder, @NonNull View view, CategoryMethods menu, int position) {

            if (prevViewHolder == null) {

                View holder = (View) viewHolder.itemView;
                LinearLayout text = holder.findViewById(R.id.Ll_Text);
                text.setVisibility(GONE);
                RecyclerView recyclerView = holder.findViewById(R.id.Rv_Rutine);
                recyclerView.setVisibility(VISIBLE);

                prevViewHolder = viewHolder;

            } else {

                if (prevViewHolder == viewHolder) {

                    View holder = (View) viewHolder.itemView;
                    LinearLayout text = holder.findViewById(R.id.Ll_Text);
                    RecyclerView recyclerView = holder.findViewById(R.id.Rv_Rutine);

                    if (recyclerView.getVisibility() == GONE) {

                        text.setVisibility(GONE);
                        recyclerView.setVisibility(VISIBLE);

                    } else {

                        text.setVisibility(VISIBLE);
                        recyclerView.setVisibility(GONE);
                    }

                } else {

                    View prevHolder = (View) prevViewHolder.itemView;
                    LinearLayout prevText = prevHolder.findViewById(R.id.Ll_Text);
                    RecyclerView prevRecyclerView = prevHolder.findViewById(R.id.Rv_Rutine);
                    prevText.setVisibility(VISIBLE);
                    prevRecyclerView.setVisibility(GONE);

                    View holder = (View) viewHolder.itemView;
                    LinearLayout text = holder.findViewById(R.id.Ll_Text);
                    RecyclerView recyclerView = holder.findViewById(R.id.Rv_Rutine);
                    text.setVisibility(GONE);
                    recyclerView.setVisibility(VISIBLE);

                    prevViewHolder = viewHolder;
                }
            }
        }
    };
}