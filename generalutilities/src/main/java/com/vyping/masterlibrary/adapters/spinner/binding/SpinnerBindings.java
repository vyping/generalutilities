package com.vyping.masterlibrary.adapters.spinner.binding;

import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;

import com.smarteist.autoimageslider.SliderView;
import com.vyping.masterlibrary.adapters.spinner.adapter.MySpinnerAdapter;
import com.vyping.masterlibrary.adapters.spinner.binder.SpinnerItemBinderInterfase;

import java.util.Collection;

public class SpinnerBindings {

    private static final int KEY_ITEMS = -123;


    // ----- SetUp ----- //

    @BindingAdapter(value = {"spinnerBinder", "layout"}, requireAll = true) @SuppressWarnings("unchecked")
    public static <T> void setSpinnerBinder(@NonNull Spinner spinner, SpinnerItemBinderInterfase<T> itemViewMapper, int layout) {

        Collection<T> items = (Collection<T>) spinner.getTag(KEY_ITEMS);
        MySpinnerAdapter<T> adapter = new MySpinnerAdapter<T>(spinner.getContext(), layout, itemViewMapper, items);

        spinner.setAdapter(adapter);
    }

    @BindingAdapter("items") @SuppressWarnings("unchecked")
    public static <T> void setItems(@NonNull Spinner spinner, Collection<T> items) {

        MySpinnerAdapter<T> adapter = (MySpinnerAdapter<T>) spinner.getAdapter();

        if (adapter != null) {

            adapter.setItems(items);

        } else {

            spinner.setTag(KEY_ITEMS, items);
        }
    }
}