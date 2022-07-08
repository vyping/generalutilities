package com.vyping.business.utilities.models.categories;

import static android.view.View.GONE;
import static java.lang.Boolean.FALSE;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.vyping.business.BR;
import com.vyping.business.R;
import com.vyping.business.utilities.models.subcategories.SubCategoryBinder;
import com.vyping.business.utilities.models.subcategories.SubCategoryHandler;
import com.vyping.business.utilities.models.subcategories.SubCategoryMethods;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.adapters.recyclerview.adapter.MyRecyclerAdapter;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerCompositeBinder;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerItemBinderInterfase;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandler;
import com.vyping.masterlibrary.views.MyImageView;
import com.vyping.masterlibrary.views.MyView;

import java.util.ArrayList;
import java.util.Arrays;

public class CategoryMethods {

    private final Category category;
    public RecyclerHandler<SubCategoryMethods> subCategoriesHandler;


    // ----- SetUp ----- //

    public CategoryMethods(@NonNull Category category) {

        this.category = category;

        loadSubCategoryHandler();
    }


    // ----- Basic Methods ----- //

    public Category getCategory()
    {
        return category;
    }

    public String getId()
    {
        return category.Id;
    }

    public String getName()
    {
        return category.Name;
    }

    public String getDescription()
    {
        return category.Description;
    }

    public String getImageName()
    {
        return category.ImageName;
    }

    public String getImageToken()
    {
        return category.ImageToken;
    }

    public String getPosition()
    {
        return category.Position;
    }

    public ArrayList<DataSnapshot> getSubCategories()
    {
        return category.SubCategories;
    }


    // ----- compound Methods ----- //

    private void loadSubCategoryHandler() {

        subCategoriesHandler = new RecyclerHandler<>(new SubCategoryHandler(), FALSE);

        for (DataSnapshot subCategory : category.SubCategories) {

            subCategoriesHandler.addMethod(subCategory);
        }
    }

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(category.Name, category.Description));
    }


    /*----- Binding Methods -----*/

    @BindingAdapter(value={"imageFromAssets"}, requireAll=true)
    public static void loadImage(@NonNull ImageView view, String asset) {

        new MyImageView().putImageFromAssets(view, asset);
    }

    public RecyclerItemBinderInterfase<SubCategoryMethods> getSubCategoriesBinder() {

        return new RecyclerCompositeBinder<>(new SubCategoryBinder(BR.subCategoryMethod, R.layout.subcategory_holder));
    }

    public ObservableArrayList<SubCategoryMethods> getSubCategoriesHandler() {

        return subCategoriesHandler.methodsDisplayed;
    }


    // ----- Listeners ModelMethods ----- //

    public final MyRecyclerAdapter.Interfase<SubCategoryMethods> getSubactegoriesInterfase = new  MyRecyclerAdapter.Interfase<SubCategoryMethods>()  {

        @Override
        public void OnClick(RecyclerView.ViewHolder viewHolder, @NonNull View view, SubCategoryMethods item, int position) {

            Spinner spinner = (Spinner) new MyView().getViewFromHolder(viewHolder, R.id.spinner);
            spinner.performClick();
            new LogCat("spinner: Click");
        }

        @Override
        public void OnLongClick(RecyclerView.ViewHolder viewHolder, @NonNull View view, SubCategoryMethods item, int position) {

            new LogCat("Interfase: LongClick");
        }

        @Override
        public void OnDoubleClick(RecyclerView.ViewHolder viewHolder, @NonNull View view, SubCategoryMethods item, int position) {

            new LogCat("Interfase: DoubleClick");
        }

        @Override
        public void OnTouch(RecyclerView.ViewHolder viewHolder, @NonNull View view, SubCategoryMethods item, int position) {

            new LogCat("Interfase: Touch");
        }
    };
}
