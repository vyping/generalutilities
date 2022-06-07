package com.vyping.masterlibrary.views.recyclerview.adapter;

import android.view.View;

public interface LongClickHandler<T>
{
    void onLongClick(BindingRecyclerViewAdapter.ViewHolder holder, View view, T viewModel);
}
