package com.vyping.masterlibrary.adapters.spinner.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;

import com.vyping.masterlibrary.adapters.spinner.binder.SpinnerItemBinderInterfase;

import java.lang.ref.WeakReference;
import java.util.Collection;

public class MySpinnerAdapter<T> extends ArrayAdapter<String>  {

    private final SpinnerItemBinderInterfase<T> itemBinder;
    private final WeakReferenceOnListChangedCallback<T> onListChangedCallback;

    public ObservableList<T> items;


    // ----- SetUp ----- //

    public MySpinnerAdapter(@NonNull Context context, int layout, @NonNull SpinnerItemBinderInterfase<T> itemBinder, @NonNull Collection<T> items) {

        super(context, layout);

        this.itemBinder = itemBinder;
        this.onListChangedCallback = new WeakReferenceOnListChangedCallback<>(this);

        setItems(items);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup viewGroup) {

        return getView(position, convertView, viewGroup);
    }

    @Override @NonNull @SuppressLint("ViewHolder")
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup viewGroup) {

        final T item = items.get(position);
        int layout = itemBinder.getLayoutRes(items.get(position));

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layout, viewGroup, false);

        ViewHolder viewHolder = new ViewHolder(binding);

        viewHolder.binding.setVariable(itemBinder.getBindingVariable(item), item);
        viewHolder.binding.executePendingBindings();

        return viewHolder.binding.getRoot();
    }

    private static class ViewHolder {

        final ViewDataBinding binding;

        ViewHolder(@NonNull ViewDataBinding binding) {

            this.binding = binding;
        }
    }

    @Override
    public int getCount() {

        if (items == null) {

            return 0;

        } else {

            return items.size();
        }
    }


    // ----- Methods ----- //

    public void setItems(@Nullable Collection<T> items) {

        if (this.items == items) {

            return;
        }

        if (this.items != null) {

            this.items.removeOnListChangedCallback(onListChangedCallback);
            notifyDataSetChanged();
        }

        if (items instanceof ObservableList) {

            this.items = (ObservableList<T>) items;
            notifyDataSetChanged();
            this.items.addOnListChangedCallback(onListChangedCallback);

        } else if (items != null) {

            this.items = new ObservableArrayList<>();
            this.items.addOnListChangedCallback(onListChangedCallback);
            this.items.addAll(items);

        } else {

            this.items = null;
        }
    }


    // -----Callbacks ----- //

    private static class WeakReferenceOnListChangedCallback<T> extends ObservableList.OnListChangedCallback<ObservableList<T>> {

        private final WeakReference<MySpinnerAdapter<T>> adapterReference;

        public WeakReferenceOnListChangedCallback(MySpinnerAdapter<T> bindingRecyclerViewAdapter) {

            this.adapterReference = new WeakReference<>(bindingRecyclerViewAdapter);
        }

        @Override  @SuppressLint("NotifyDataSetChanged")
        public void onChanged(ObservableList sender) {

            MySpinnerAdapter<T> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {

            MySpinnerAdapter<T> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {

            MySpinnerAdapter<T> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {

            MySpinnerAdapter<T> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {

            MySpinnerAdapter<T> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyDataSetChanged();
            }
        }
    }
}
