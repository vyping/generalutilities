package com.vyping.masterlibrary.views.recyclerview.binding;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.views.recyclerview.adapter.BindingRecyclerViewAdapter;
import com.vyping.masterlibrary.views.recyclerview.binder.ItemBinder;

import java.util.Collection;

public class RecyclerViewBindings {

    private static final int KEY_ITEMS = -123;


    // ----- SetUp ----- //

    @BindingAdapter(value = {"itemViewBinder", "interfase"}, requireAll = true) @SuppressWarnings("unchecked")
    public static <T> void setItemViewBinder(@NonNull RecyclerView recyclerView, ItemBinder<T> itemViewMapper, BindingRecyclerViewAdapter.Interfase<T> interfase) {

        Collection<T> items = (Collection<T>) recyclerView.getTag(KEY_ITEMS);
        BindingRecyclerViewAdapter<T> adapter = new BindingRecyclerViewAdapter<>(itemViewMapper, items, interfase);

        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("items") @SuppressWarnings("unchecked")
    public static <T> void setItems(@NonNull RecyclerView recyclerView, Collection<T> items) {

        BindingRecyclerViewAdapter<T> adapter = (BindingRecyclerViewAdapter<T>) recyclerView.getAdapter();

        if (adapter != null) {

            adapter.setItems(items);

        } else {

            recyclerView.setTag(KEY_ITEMS, items);
        }
    }
}