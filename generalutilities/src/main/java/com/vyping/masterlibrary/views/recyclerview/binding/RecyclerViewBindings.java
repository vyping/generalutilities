package com.vyping.masterlibrary.views.recyclerview.binding;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.views.recyclerview.adapter.BindingRecyclerViewAdapter;
import com.vyping.masterlibrary.views.recyclerview.adapter.ClickHandler;
import com.vyping.masterlibrary.views.recyclerview.adapter.LongClickHandler;
import com.vyping.masterlibrary.views.recyclerview.adapter.TouchHandler;
import com.vyping.masterlibrary.views.recyclerview.adapter.binder.ItemBinder;

import java.util.ArrayList;
import java.util.Collection;

public class RecyclerViewBindings {

    private static final int KEY_ITEMS = -123;
    private static final int KEY_CLICK_HANDLER = -124;
    private static final int KEY_LONG_CLICK_HANDLER = -125;
    private static final int KEY_TOUCH_HANDLER = -126;

    @SuppressWarnings("unchecked")
    @BindingAdapter("items")
    public static <T> void setItems(@NonNull RecyclerView recyclerView, Collection<T> items) {

        BindingRecyclerViewAdapter<T> adapter = (BindingRecyclerViewAdapter<T>) recyclerView.getAdapter();

        if (adapter != null) {

            adapter.setItems(items);

        } else {

            recyclerView.setTag(KEY_ITEMS, items);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("touchHandler")
    public static <T> void setHandler(@NonNull RecyclerView recyclerView, TouchHandler<T> handler) {

        BindingRecyclerViewAdapter<T> adapter = (BindingRecyclerViewAdapter<T>) recyclerView.getAdapter();

        if (adapter != null) {

            adapter.setTouchHandler(handler);

        } else {

            recyclerView.setTag(KEY_TOUCH_HANDLER, handler);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("clickHandler")
    public static <T> void setHandler(@NonNull RecyclerView recyclerView, ClickHandler<T> handler) {

        BindingRecyclerViewAdapter<T> adapter = (BindingRecyclerViewAdapter<T>) recyclerView.getAdapter();

        if (adapter != null) {

            adapter.setClickHandler(handler);

        } else {

            recyclerView.setTag(KEY_CLICK_HANDLER, handler);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("longClickHandler")
    public static <T> void setHandler(@NonNull RecyclerView recyclerView, LongClickHandler<T> handler) {

        BindingRecyclerViewAdapter<T> adapter = (BindingRecyclerViewAdapter<T>) recyclerView.getAdapter();

        if (adapter != null) {

            adapter.setLongClickHandler(handler);

        } else {

            recyclerView.setTag(KEY_LONG_CLICK_HANDLER, handler);
        }
    }

    @SuppressWarnings("unchecked")
    @BindingAdapter("itemViewBinder")
    public static <T> void setItemViewBinder(@NonNull RecyclerView recyclerView, ItemBinder<T> itemViewMapper) {

        Collection<T> items = (Collection<T>) recyclerView.getTag(KEY_ITEMS);
        ClickHandler<T> clickHandler = (ClickHandler<T>) recyclerView.getTag(KEY_CLICK_HANDLER);
        BindingRecyclerViewAdapter<T> adapter = new BindingRecyclerViewAdapter<>(itemViewMapper, items);

        if(clickHandler != null) {

            adapter.setClickHandler(clickHandler);
        }

        recyclerView.setAdapter(adapter);
    }

    @BindingAdapter(value={"itemViewBinder", "touchable"},  requireAll=true)
    public static <T> void setItemViewBinder(@NonNull RecyclerView recyclerView, ItemBinder<T> itemViewMapper, boolean touchable) {

        Collection<T> items = (Collection<T>) recyclerView.getTag(KEY_ITEMS);
        ClickHandler<T> clickHandler = (ClickHandler<T>) recyclerView.getTag(KEY_CLICK_HANDLER);
        BindingRecyclerViewAdapter<T> adapter = new BindingRecyclerViewAdapter<>(itemViewMapper, items, touchable);

        if(clickHandler != null) {

            adapter.setClickHandler(clickHandler);
        }

        recyclerView.setAdapter(adapter);
    }
}