package com.vyping.masterlibrary.models.selectholders;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class SelectedHolder<T> {

    public RecyclerView.ViewHolder viewHolder;
    public T method;
    public int position;


    /*----- Main Model -----*/

    public SelectedHolder() {

        this.viewHolder = null;
        this.method = null;
        this.position = -1;
    }

    public SelectedHolder(@NonNull RecyclerView.ViewHolder viewHolder, T method, int position) {

        this.viewHolder = viewHolder;
        this.method = method;
        this.position = position;
    }
}
