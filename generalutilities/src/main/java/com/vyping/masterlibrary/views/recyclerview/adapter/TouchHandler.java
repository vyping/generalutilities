package com.vyping.masterlibrary.views.recyclerview.adapter;

import android.view.View;

public interface TouchHandler<T>
{
    void onTouch(BindingRecyclerViewAdapter.ViewHolder holder, View view, T viewModel);
}
