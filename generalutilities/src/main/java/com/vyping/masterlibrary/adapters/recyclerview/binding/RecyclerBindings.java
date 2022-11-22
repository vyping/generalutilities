package com.vyping.masterlibrary.adapters.recyclerview.binding;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.adapters.recyclerview.adapter.MyRecyclerAdapter;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerItemBinderInterfase;

import java.util.Collection;
import java.util.Random;

public class RecyclerBindings {


    // ----- SetUp ----- //

    @BindingAdapter(value={"binder", "items", "interfase"}, requireAll=false)
    public static <T> void setRecyclerAdapter(@NonNull RecyclerView recyclerView, RecyclerItemBinderInterfase<T> itemViewMapper, Collection<T> items, MyRecyclerAdapter.Interfase<T> interfase) {

        int tagModel = - new Random().nextInt(99999999);

        MyRecyclerAdapter<T> adapter = new MyRecyclerAdapter<T>(itemViewMapper, items, interfase, tagModel);
       // adapter.setItems(items);
        recyclerView.setAdapter(adapter);
    }
}