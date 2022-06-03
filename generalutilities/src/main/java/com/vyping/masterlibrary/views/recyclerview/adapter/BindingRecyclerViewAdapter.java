package com.vyping.masterlibrary.views.recyclerview.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.views.recyclerview.adapter.binder.ItemBinder;

import java.lang.ref.WeakReference;
import java.util.Collection;

public class BindingRecyclerViewAdapter<T> extends RecyclerView.Adapter<BindingRecyclerViewAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener {

    private static final int ITEM_MODEL = -124;
    private final WeakReferenceOnListChangedCallback<T>  onListChangedCallback;
    private final ItemBinder<T> itemBinder;
    private ObservableList<T> base, items;
    private LayoutInflater inflater;
    private ClickHandler<T> clickHandler;
    private LongClickHandler<T> longClickHandler;


    // ----- SetUp ----- //

    public BindingRecyclerViewAdapter(ItemBinder<T> itemBinder, @Nullable Collection<T> items) {

        this.itemBinder = itemBinder;
        this.onListChangedCallback = new WeakReferenceOnListChangedCallback<>(this);
        setItems(items);
    }

    @Override @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int layoutId) {

        if (inflater == null) {

            inflater = LayoutInflater.from(viewGroup.getContext());
        }

        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layoutId, viewGroup, false);

        return new ViewHolder(binding);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        final ViewDataBinding binding;

        ViewHolder(@NonNull ViewDataBinding binding) {

            super(binding.getRoot());

            this.binding = binding;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {

        final T item = items.get(position);

        viewHolder.binding.setVariable(itemBinder.getBindingVariable(item), item);
        viewHolder.binding.getRoot().setTag(ITEM_MODEL, item);
        viewHolder.binding.getRoot().setOnClickListener(this);
        viewHolder.binding.getRoot().setOnLongClickListener(this);
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemViewType(int position) {

        return itemBinder.getLayoutRes(items.get(position));
    }

    @Override
    public int getItemCount() {

        return items == null ? 0 : items.size();
    }



    public ObservableList<T> getItems() {

        return items;
    }

    public void setItems(@Nullable Collection<T> items) {

        if (this.items == items) {

            return;
        }

        if (this.items != null) {

            this.items.removeOnListChangedCallback(onListChangedCallback);
            notifyItemRangeRemoved(0, this.items.size());
        }

        if (items instanceof ObservableList) {

            this.items = (ObservableList<T>) items;
            notifyItemRangeInserted(0, this.items.size());
            this.items.addOnListChangedCallback(onListChangedCallback);

        } else if (items != null) {

            this.items = new ObservableArrayList<>();
            this.items.addOnListChangedCallback(onListChangedCallback);
            this.items.addAll(items);

        } else {

            this.items = null;
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {

        if (items != null) {

            items.removeOnListChangedCallback(onListChangedCallback);
        }
    }




    @Override
    public void onClick(View v) {

        if (clickHandler != null) {

            T item = (T) v.getTag(ITEM_MODEL);
            clickHandler.onClick(item);
        }
    }

    public void setClickHandler(ClickHandler<T> clickHandler) {

        this.clickHandler = clickHandler;
    }

    @Override
    public boolean onLongClick(View v) {

        if (longClickHandler != null) {

            T item = (T) v.getTag(ITEM_MODEL);
            longClickHandler.onLongClick(item);
            return true;
        }
        return false;
    }

    public void setLongClickHandler(LongClickHandler<T> clickHandler) {

        this.longClickHandler = clickHandler;
    }




    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {

        private final WeakReference<BindingRecyclerViewAdapter<T>> adapterReference;

        public WeakReferenceOnListChangedCallback(BindingRecyclerViewAdapter<T> bindingRecyclerViewAdapter) {

            this.adapterReference = new WeakReference<>(bindingRecyclerViewAdapter);
        }

        @Override  @SuppressLint("NotifyDataSetChanged")
        public void onChanged(ObservableList sender) {

            RecyclerView.Adapter<ViewHolder> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {

            RecyclerView.Adapter<ViewHolder> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyItemRangeChanged(positionStart, itemCount);
            }
        }

        @Override
        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {

            RecyclerView.Adapter<ViewHolder> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyItemRangeInserted(positionStart, itemCount);
            }
        }

        @Override
        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {

            RecyclerView.Adapter<ViewHolder> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyItemMoved(fromPosition, toPosition);
            }
        }

        @Override
        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {

            RecyclerView.Adapter<ViewHolder> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyItemRangeRemoved(positionStart, itemCount);
            }
        }
    }
}