package com.vyping.masterlibrary.views.recyclerview.adapter;

import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.masterlibrary.Animations.MyAnimation;
import com.vyping.masterlibrary.GestureListeners.MyItemTouchListener;
import com.vyping.masterlibrary.views.recyclerview.adapter.binder.ItemBinder;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;

public class BindingRecyclerViewAdapter<T> extends RecyclerView.Adapter<BindingRecyclerViewAdapter.ViewHolder> implements View.OnClickListener, View.OnLongClickListener, MyItemTouchListener.SelectInterface{

    private LayoutInflater inflater;
    private ViewDataBinding binding;

    private final WeakReferenceOnListChangedCallback<T>  onListChangedCallback;
    private ItemBinder<T> itemBinder;

    private ClickHandler<T> clickHandler;
    private LongClickHandler<T> longClickHandler;
    private TouchHandler<T> touchHandler;

    private ObservableList<T> items;
    private static final int ITEM_MODEL = -124;
    private boolean touchable;


    // ----- SetUp ----- //

    public BindingRecyclerViewAdapter(ItemBinder<T> itemBinder, @Nullable Collection<T> items) {

        this.itemBinder = itemBinder;
        this.touchable = TRUE;
        this.onListChangedCallback = new WeakReferenceOnListChangedCallback<>(this);

        setItems(items);
    }

    public BindingRecyclerViewAdapter(ItemBinder<T> itemBinder, @Nullable Collection<T> items, boolean touchable) {

        this.itemBinder = itemBinder;
        this.touchable = touchable;
        this.onListChangedCallback = new WeakReferenceOnListChangedCallback<>(this);

        setItems(items);
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);

        if (touchable) {

            recyclerView.addOnItemTouchListener(new MyItemTouchListener(recyclerView.getContext(), this));
        }
    }

    @Override @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

        //if (inflater == null) {

            //inflater = LayoutInflater.from(viewGroup.getContext());
        //}

        inflater = LayoutInflater.from(viewGroup.getContext());
        binding = DataBindingUtil.inflate(inflater, viewType, viewGroup, false);

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

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {

        if (items != null) {

            items.removeOnListChangedCallback(onListChangedCallback);
        }
    }


    // ----- Methods ----- //


    public void setLayouts(@Nullable Collection<T> items) { }


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

    public void setClickHandler(ClickHandler<T> clickHandler) {

        this.clickHandler = clickHandler;
    }

    public void setLongClickHandler(LongClickHandler<T> clickHandler) {

        this.longClickHandler = clickHandler;
    }

    public void setTouchHandler(TouchHandler<T> touchHandler) {

        this.touchHandler = touchHandler;
    }


    // ----- Listeners ----- //

    @Override
    public void onClick(View view) {

        if (clickHandler != null) {

            ViewHolder viewHolder = new ViewHolder(binding);
            T item = (T) view.getTag(ITEM_MODEL);
            clickHandler.onClick(viewHolder, view, item);
        }
    }

    @Override
    public boolean onLongClick(View view) {

        if (longClickHandler != null) {

            ViewHolder viewHolder = new ViewHolder(binding);
            T item = (T) view.getTag(ITEM_MODEL);
            longClickHandler.onLongClick(viewHolder, view, item);
            return true;
        }
        return false;
    }

    @Override
    public void SelectedItem(View selectedView, int position, RecyclerView.ViewHolder viewHolder) {

        T item = (T) selectedView.getTag(ITEM_MODEL);
        touchHandler.onTouch((ViewHolder) viewHolder, selectedView, item, position);
    }


    // -----Callbacks ----- //

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