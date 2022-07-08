package com.vyping.masterlibrary.adapters.sliderview.binding;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import com.smarteist.autoimageslider.SliderView;
import com.vyping.masterlibrary.adapters.sliderview.adapter.MySliderviewAdapter;
import com.vyping.masterlibrary.adapters.sliderview.binder.SliderItemBinderInterfase;

import java.util.Collection;

public class SliderBindings {

    private static final int KEY_ITEMS = -123;


    // ----- SetUp ----- //

    @BindingAdapter(value = {"sliderBinder", "interfase"}, requireAll = true) @SuppressWarnings("unchecked")
    public static <T> void setSliderBinder(@NonNull SliderView sliderView, SliderItemBinderInterfase<T> itemViewMapper, MySliderviewAdapter.Interfase<T> interfase) {

        Collection<T> items = (Collection<T>) sliderView.getTag(KEY_ITEMS);
        MySliderviewAdapter<T> adapter = new MySliderviewAdapter<T>(itemViewMapper, items, interfase);

        sliderView.setSliderAdapter(adapter);
    }

    @BindingAdapter("items") @SuppressWarnings("unchecked")
    public static <T> void setItems(@NonNull SliderView sliderView, Collection<T> items) {

        MySliderviewAdapter<T> adapter = (MySliderviewAdapter<T>) sliderView.getSliderAdapter();

        if (adapter != null) {

            adapter.setItems(items);

        } else {

            sliderView.setTag(KEY_ITEMS, items);
        }
    }
}