package com.vyping.masterlibrary.GestureListeners;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

public class MyItemTouchHelperCallback extends ItemTouchHelper.Callback {

    private final ItemTouchHelperAdapter mAdapter;


    public MyItemTouchHelperCallback(ItemTouchHelperAdapter adapter) {

        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {

        int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {

        mAdapter.onItemMove(viewHolder.getAdapterPosition(), target.getAdapterPosition());

        return true;
    }

    @Override
    public boolean isLongPressDragEnabled()
    {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled()
    {
        return true;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {

        mAdapter.onItemDismiss(viewHolder.getAdapterPosition());
    }

    public static interface ItemTouchHelperAdapter {

        boolean onItemMove(int fromPosition, int toPosition);
        void onItemDismiss(int position);
    }
}

//     MyItemTouchHelperCallback MyItemTouchHelperCallback = new MyItemTouchHelperCallback(new ItemTouchHelperAdapter() {
//
//            @Override
//            public boolean onItemMove(int fromPosition, int toPosition) {
//
//                Collections.swap(recyclerHandler.methodsDisplayed, fromPosition, toPosition);
//
//                //binding.RvNotRecyclerView.getAdapter().notifyItemMoved(fromPosition, toPosition);
//                return true;
//            }
//
//            @Override
//            public void onItemDismiss(int position) {}
//        });
//        itemTouchHelper = new ItemTouchHelper(MyItemTouchHelperCallback);
//        itemTouchHelper.attachToRecyclerView(binding.RvNotRecyclerView);