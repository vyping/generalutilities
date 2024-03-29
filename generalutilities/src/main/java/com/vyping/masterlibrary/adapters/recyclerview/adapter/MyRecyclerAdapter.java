package com.vyping.masterlibrary.adapters.recyclerview.adapter;

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

import com.vyping.masterlibrary.GestureListeners.MyGesturesListener;
import com.vyping.masterlibrary.GestureListeners.MyItemTouchListener;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerItemBinderInterfase;
import com.vyping.masterlibrary.adapters.recyclerview.interfase.RecyclerInterfase;

import java.lang.ref.WeakReference;
import java.util.Collection;

public class MyRecyclerAdapter<T> extends RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> implements MyGesturesListener.Interfase, MyItemTouchListener.Interfase {

    private LayoutInflater inflater;
    public ViewDataBinding binding;

    private final RecyclerItemBinderInterfase<T> itemBinder;
    private final WeakReferenceOnListChangedCallback<T>  onListChangedCallback;
    private final Interfase<T> interfase;

    private RecyclerView recyclerView;

    private ObservableList<T> items;
    private final int tagModel;


    // ----- SetUp ----- //

    public MyRecyclerAdapter(RecyclerItemBinderInterfase<T> itemBinder, @Nullable Collection<T> items, Interfase<T> interfase, int tagModel) {

        this.itemBinder = itemBinder;
        this.onListChangedCallback = new WeakReferenceOnListChangedCallback<>(this);
        this.interfase = interfase;
        this.tagModel = tagModel;

        setItems(items);
    }


    public void restartAdapter() {

        items.clear();
        //recyclerView.notify();
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {

        super.onAttachedToRecyclerView(recyclerView);

        this.recyclerView = recyclerView;
        //this.recyclerView.addOnItemTouchListener(new MyItemTouchListener(this.recyclerView.getContext(), this));
    }

    @Override @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {

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
        final RecyclerInterfase interfase = itemBinder.getInterfase(item);

        viewHolder.binding.setVariable(itemBinder.getViewDataBinding(item), binding);
        viewHolder.binding.setVariable(itemBinder.getBindingVariable(item), item);

        if (interfase != null) {

            viewHolder.binding.setVariable(itemBinder.getNameCallBack(item), interfase);
        }

        viewHolder.binding.getRoot().setTag(tagModel, item);
        viewHolder.binding.getRoot().setOnTouchListener(new MyGesturesListener(viewHolder.binding.getRoot(), this));
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemViewType(int position) {

        return itemBinder.getLayoutRes(items.get(position));
    }

    public T getItem(int position) {

        return items.get(position);
    }

    @Override
    public int getItemCount() {

        if (items == null) {

            return 0;

        } else {

            return items.size();
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {

        if (items != null) {

            items.removeOnListChangedCallback(onListChangedCallback);
        }
    }


    // ----- Model Methods ----- //

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

    public void insertItem(T item, int position) {

        this.items.add(item);
        notifyItemInserted(position);
    }

    // ----- Listeners ----- //

    @Override @SuppressWarnings("unchecked")
    public void OnClick(@NonNull View view) {

        int position = recyclerView.getChildAdapterPosition(view);
        ViewHolder viewHolder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(position);

        T item = (T) view.getTag(tagModel);

        if (interfase != null && viewHolder != null) {

            interfase.OnClick(viewHolder.binding, viewHolder, view, item, position);
        }
    }

    @Override @SuppressWarnings("unchecked")
    public void OnLongClick(@NonNull View view) {

        int position = recyclerView.getChildAdapterPosition(view);
        ViewHolder viewHolder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(position);

        T item = (T) view.getTag(tagModel);

        if (interfase != null && viewHolder != null) {

            interfase.OnLongClick(viewHolder.binding, viewHolder, view, item, position);
        }
    }

    @Override @SuppressWarnings("unchecked")
    public void OnDoubleClick(@NonNull View view) {

        int position = recyclerView.getChildAdapterPosition(view);
        ViewHolder viewHolder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(position);

        T item = (T) view.getTag(tagModel);

        if (interfase != null && viewHolder != null) {

            interfase.OnDoubleClick(viewHolder.binding, viewHolder, view, item, position);
        }
    }

    @Override @SuppressWarnings("unchecked")
    public void SelectedItem(@NonNull View selectedView, int position, RecyclerView.ViewHolder recyclerViewHolder) {

        ViewHolder viewHolder = (ViewHolder) recyclerViewHolder;

        T item = (T) selectedView.getTag(tagModel);

        if (interfase != null && viewHolder != null) {

            interfase.OnTouch(viewHolder.binding, viewHolder, selectedView, item, position);
        }
    }


    // -----Callbacks ----- //

    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {

        private final WeakReference<MyRecyclerAdapter<T>> adapterReference;

        public WeakReferenceOnListChangedCallback(MyRecyclerAdapter<T> bindingRecyclerViewAdapter) {

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


    //----- Interface -----//

    public interface Interfase<T> {

        default void OnClick(ViewDataBinding binding, RecyclerView.ViewHolder viewHolder, @NonNull View view, T item, int position) {};
        default void OnLongClick(ViewDataBinding binding, RecyclerView.ViewHolder viewHolder, @NonNull View view, T item, int position) {};
        default void OnDoubleClick(ViewDataBinding binding, RecyclerView.ViewHolder viewHolder, @NonNull View view, T item, int position) {};
        default void OnTouch(ViewDataBinding binding, RecyclerView.ViewHolder viewHolder, @NonNull View view, T item, int position) {};
    }
}