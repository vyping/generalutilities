package com.vyping.masterlibrary.adapters.recyclerview.binding;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.adapters.recyclerview.adapter.MyRecyclerAdapter;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerItemBinderInterfase;

import java.util.Collection;

public class RecyclerBindings {

    private static final int KEY_ITEMS = -123;


    // ----- SetUp ----- //

    @BindingAdapter(value = {"recyclerBinder", "interfase"}, requireAll = true) @SuppressWarnings("unchecked")
    public static <T> void setRecyclerBinder(@NonNull RecyclerView recyclerView, RecyclerItemBinderInterfase<T> itemViewMapper, MyRecyclerAdapter.Interfase<T> interfase) {

        Collection<T> items = (Collection<T>) recyclerView.getTag(KEY_ITEMS);
        MyRecyclerAdapter<T> adapter = new MyRecyclerAdapter<>(itemViewMapper, items, interfase);

        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter("items") @SuppressWarnings("unchecked")
    public static <T> void setItems(@NonNull RecyclerView recyclerView, Collection<T> items) {

        MyRecyclerAdapter<T> adapter = (MyRecyclerAdapter<T>) recyclerView.getAdapter();

        if (adapter != null) {

            adapter.setItems(items);

        } else {

            recyclerView.setTag(KEY_ITEMS, items);
        }
    }
}