package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL_ACCEPT;

import android.app.Dialog;
import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.Views.RecyclerViews;

// TODO --- Dialogo diseñado para el ingreso de texto por parte del usuario (En una sola linea).

public abstract class DialogRecyclerView extends CreateDialog {

    private Context context;
    private RecyclerView.Adapter adapter;

    private RecyclerViews recyclerViews;


    /*----- SetUp -----*/

    public DialogRecyclerView(@NonNull Context context, int parameters, RecyclerView.Adapter adapter) {

        super(context, parameters);

        setParameters(context, adapter);
        setDialogViews();
        setModeButtons(BUTTONS_CANCEL_ACCEPT);
        setDialogListener(new DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() { }

            @Override
            public void PositiveClick() {

                ButtonConfirm();
            }
        });
    }

    private void setParameters(@NonNull Context context, RecyclerView.Adapter adapter) {

        this.context = context;
        this.adapter = adapter;
    }

    private void setDialogViews() {

        Dialog dialog = getDialog();
        int style = R.style.RecyclerView;
        int attr = R.attr.recyclerView;

        recyclerViews = new RecyclerViews(dialog, getContainer(), attr, style, adapter);
        recyclerViews.selectListener(new RecyclerViews.selectInterface() {

            @Override
            public void SelectedItem(View selectedView, int position, RecyclerView.ViewHolder viewHolder) {

                SelectItem(selectedView, position, viewHolder);
            }

            private void DummyVoid() {}
        });
    }


    /*----- Return -----*/

    protected abstract void SelectItem(View selectedView, int position, RecyclerView.ViewHolder viewHolder);
    protected abstract void ButtonConfirm();
}
