package com.vyping.masterlibrary.adapters.sliderview.adapter;

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

import com.smarteist.autoimageslider.SliderViewAdapter;
import com.vyping.masterlibrary.GestureListeners.MyTouchListener;
import com.vyping.masterlibrary.adapters.sliderview.binder.SliderItemBinderInterfase;

import java.lang.ref.WeakReference;
import java.util.Collection;

public class MySliderviewAdapter<T> extends SliderViewAdapter<MySliderviewAdapter.SliderAdapterViewHolder> {

    private final SliderItemBinderInterfase<T> itemBinder;
    private final WeakReferenceOnListChangedCallback<T> onListChangedCallback;

    public MySliderviewAdapter.Interfase<T> interfase;
    public ObservableList<T> items;


    // ----- SetUp ----- //

    public MySliderviewAdapter(SliderItemBinderInterfase<T> itemBinder, Collection<T> items, MySliderviewAdapter.Interfase<T> interfase) {

        this.itemBinder = itemBinder;
        this.onListChangedCallback = new WeakReferenceOnListChangedCallback<>(this);
        this.interfase = interfase;

        setItems(items);
    }

    @Override
    public MySliderviewAdapter.SliderAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());

        int layout = itemBinder.getLayoutRes(items.get(0));
        ViewDataBinding binding = DataBindingUtil.inflate(inflater, layout, viewGroup, false);

        return new MySliderviewAdapter.SliderAdapterViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull SliderAdapterViewHolder viewHolder, int position) {

        final T item = items.get(position);

        viewHolder.binding.setVariable(itemBinder.getBindingVariable(item), item);
        viewHolder.binding.executePendingBindings();

        viewHolder.itemView.setOnTouchListener(new MyTouchListener(viewHolder.itemView.getContext(), new MyTouchListener.TouchInterface() {

            @Override
            public void TouchEvent(View selectedView) {
                interfase.Selected(position, items.get(position));
            }

            private void DummyVoid() {}
        }));
    }

    public static class SliderAdapterViewHolder extends SliderViewAdapter.ViewHolder {

        ViewDataBinding binding;

        public SliderAdapterViewHolder(@NonNull ViewDataBinding binding) {

            super(binding.getRoot());

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

        private final WeakReference<MySliderviewAdapter<T>> adapterReference;

        public WeakReferenceOnListChangedCallback(MySliderviewAdapter<T> bindingRecyclerViewAdapter) {

            this.adapterReference = new WeakReference<>(bindingRecyclerViewAdapter);
        }

        @Override  @SuppressLint("NotifyDataSetChanged")
        public void onChanged(ObservableList sender) {

            MySliderviewAdapter<T> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {

            MySliderviewAdapter<T> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {

            MySliderviewAdapter<T> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {

            MySliderviewAdapter<T> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {

            MySliderviewAdapter<T> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyDataSetChanged();
            }
        }
    }


    // ----- Interface ----- //

    public interface Interfase<T> {

        void Selected(int position, T notice);
    }
}
