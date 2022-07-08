package com.vyping.business.utilities.models.subcategories;

import static com.vyping.business.utilities.definitions.Buckets.BUCKET_MENU;
import static com.vyping.masterlibrary.Common.MyFile.TYPE_PNG;
import static java.lang.Boolean.FALSE;

import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.ObservableArrayList;

import com.google.firebase.database.DataSnapshot;
import com.vyping.business.BR;
import com.vyping.business.R;
import com.vyping.business.utilities.models.stores.StoreBinder;
import com.vyping.business.utilities.models.stores.StoreHandler;
import com.vyping.business.utilities.models.stores.StoreMethods;
import com.vyping.masterlibrary.Common.MyDisplay;
import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.Firebase.models.MyUrlStorage;
import com.vyping.masterlibrary.adapters.spinner.binder.SpinnerCompositeBinder;
import com.vyping.masterlibrary.adapters.spinner.binder.SpinnerItemBinderInterfase;
import com.vyping.masterlibrary.adapters.spinner.handler.SpinnerHandler;
import com.vyping.masterlibrary.views.MyImageView;

import java.util.ArrayList;
import java.util.Arrays;

public class SubCategoryMethods {

    private final SubCategory subCategory;
    public SpinnerHandler<StoreMethods> storesHandler;


    // ----- SetUp ----- //

    public SubCategoryMethods(SubCategory subCategory) {

        this.subCategory = subCategory;

        loadStoresHandler();
    }


    // ----- Basic ModelMethods ----- //

    public SubCategory getSubCategory()
    {
        return subCategory;
    }

    public String getId()
    {
        return subCategory.Id;
    }

    public String getPosition()
    {
        return subCategory.Position;
    }

    public String getDescription()
    {
        return subCategory.Description;
    }

    public String getImage()
    {
        return subCategory.Image;
    }

    public int getIndicator()
    {
        return subCategory.Indicator;
    }

    public ArrayList<DataSnapshot> getStores()
    {
        return subCategory.Stores;
    }


    // ----- Compounds Methods ----- //

    private void loadStoresHandler() {

        storesHandler = new SpinnerHandler<>(new StoreHandler(), FALSE);

        for (DataSnapshot store : subCategory.Stores) {

            storesHandler.addMethod(store);
        }
    }

    public String getUrlImage() {

        String name = new MyString().changeToFileName(subCategory.Id);

        return new MyUrlStorage(BUCKET_MENU).file(name, TYPE_PNG).token(subCategory.Image);
    }


    /*----- Binding Methods -----*/

    @BindingAdapter("widthView")
    public static void setWidthView(@NonNull View view, @NonNull SubCategoryMethods subCategoryMethods) {

        int width = (new MyDisplay().displayWidth(view.getContext())*4)/10;

        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, ViewGroup.LayoutParams.MATCH_PARENT);
        int margin = (int) new MyDisplay().dpsToPxs(view.getContext(), 8);
        layoutParams.setMargins(0, margin, margin, margin);

        view.setLayoutParams(layoutParams);

        //new MyView().setDimentionsFromPx(view, width/2, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    @BindingAdapter(value={"imageFromAssets"}, requireAll=true)
    public static void loadImage(@NonNull ImageView view, String asset) {

        new MyImageView().putImageFromAssets(view, asset);
    }

    public SpinnerItemBinderInterfase<StoreMethods> getStoresBinder() {

        return new SpinnerCompositeBinder<>(new StoreBinder(BR.storeMethod, R.layout.store_holder));
    }

    public ObservableArrayList<StoreMethods> getStoresHandler() {

        return storesHandler.methodsDisplayed;
    }


    // ----- Search Methods ----- //

    @NonNull
    public ArrayList<Object> getSearchParameters() {

        return new ArrayList<>(Arrays.asList(subCategory.Id, subCategory.Image, subCategory.Description, subCategory.Indicator));
    }
}
