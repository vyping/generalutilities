package com.vyping.masterlibrary.views.recyclerview.adapter;

import android.view.View;

public interface ClickHandler<T>
{
    void onClick(BindingRecyclerViewAdapter.ViewHolder holder, View view, T viewModel);
}