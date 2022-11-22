package com.vyping.masterlibrary.adapters.recyclerview.adapter;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.Bindable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ObservableArrayList;
import androidx.databinding.ObservableList;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.GestureListeners.MyGesturesListener;
import com.vyping.masterlibrary.GestureListeners.MyItemTouchListener;
import com.vyping.masterlibrary.adapters.recyclerview.binder.RecyclerItemBinderInterfase;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerHandlerInterfase;
import com.vyping.masterlibrary.adapters.recyclerview.handler.RecyclerItems;
import com.vyping.masterlibrary.adapters.recyclerview.interfase.RecyclerInterfase;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;

public class MyRecyclerAdapter2<T> extends RecyclerView.Adapter<MyRecyclerAdapter2.ViewHolder> implements MyGesturesListener.Interfase, MyItemTouchListener.Interfase {

    private LayoutInflater inflater;
    public ViewDataBinding binding;

    private final RecyclerItemBinderInterfase<T> itemBinder;
    private final Interfase<T> interfase;

    private RecyclerView recyclerView;

    private final int tagModel;


    private final RecyclerHandlerInterfase<T> methodInterfase;

    public ArrayList<T> methodsDisplayed;
    public ArrayList<T> methodsHidden;

    private final boolean searched;
    private String identifierSelected;




    // ----- SetUp ----- //

    public MyRecyclerAdapter2(RecyclerItemBinderInterfase<T> itemBinder, RecyclerHandlerInterfase<T> modelBinder,  @Nullable Interfase<T> interfase, int tagModel) {

        this.itemBinder = itemBinder;
        this.interfase = interfase;
        this.tagModel = tagModel;
        this.methodInterfase = modelBinder;
        this.searched = TRUE;

        if (searched) {

            this.methodsHidden = new ObservableArrayList<>();
        }

        this.methodsHidden = new ObservableArrayList<>();
        this.methodsDisplayed = new ObservableArrayList<>();
    }


    public void restartAdapter() {

        methodsHidden.clear();
        methodsDisplayed.clear();

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
        binding = DataBindingUtil.inflate(inflater, itemBinder.getLayoutRes(methodsDisplayed.get(0)), viewGroup, false);

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

        final T item = methodsDisplayed.get(position);
        final RecyclerInterfase interfase = itemBinder.getInterfase(item);

       // viewHolder.setIsRecyclable(false);

        viewHolder.binding.setVariable(itemBinder.getViewDataBinding(item), binding);
        viewHolder.binding.setVariable(itemBinder.getBindingVariable(item), item);

        if (interfase != null) {

            viewHolder.binding.setVariable(itemBinder.getNameCallBack(item), interfase);
        }

        viewHolder.binding.getRoot().setTag(tagModel, item);
        //viewHolder.binding.getRoot().setOnTouchListener(new MyGesturesListener(viewHolder.binding.getRoot(), this));
        viewHolder.binding.executePendingBindings();
    }

    @Override
    public int getItemViewType(int position) {

        //return itemBinder.getLayoutRes(methodsDisplayed.get(position));
        return position;
    }


    @Override
    public long getItemId(int position) {

        return position;
    }


    public T getItem(int position) {

        return methodsDisplayed.get(position);
    }

    @Override
    public int getItemCount() {

        if (methodsDisplayed == null) {

            return 0;

        } else {

            return methodsDisplayed.size();
        }
    }

    @Override
    public void onDetachedFromRecyclerView(@NonNull RecyclerView recyclerView) {

        if (methodsDisplayed != null) {

            methodsHidden.clear();
            methodsDisplayed.clear();
        }
    }


    // ----- Model Methods ----- //

    public void setLayouts(@Nullable Collection<T> items) { }

    public ArrayList<T> getArrayItems() {

        return methodsDisplayed;
    }

    public void insertItem(T item, int position) {

        this.methodsDisplayed.add(item);
        notifyItemInserted(position);
    }



    // ----- Listeners ----- //

    @Override @SuppressWarnings("unchecked")
    public void OnClick(@NonNull View view) {

        int position = recyclerView.getChildAdapterPosition(view);
        MyRecyclerAdapter.ViewHolder viewHolder = (MyRecyclerAdapter.ViewHolder) recyclerView.findViewHolderForAdapterPosition(position);

        T item = (T) view.getTag(tagModel);

        if (interfase != null && viewHolder != null) {

            interfase.OnClick(viewHolder.binding, viewHolder, view, item, position);
        }
    }

    @Override @SuppressWarnings("unchecked")
    public void OnLongClick(@NonNull View view) {

        int position = recyclerView.getChildAdapterPosition(view);
        MyRecyclerAdapter.ViewHolder viewHolder = (MyRecyclerAdapter.ViewHolder) recyclerView.findViewHolderForAdapterPosition(position);

        T item = (T) view.getTag(tagModel);

        if (interfase != null && viewHolder != null) {

            interfase.OnLongClick(viewHolder.binding, viewHolder, view, item, position);
        }
    }

    @Override @SuppressWarnings("unchecked")
    public void OnDoubleClick(@NonNull View view) {

        int position = recyclerView.getChildAdapterPosition(view);
        MyRecyclerAdapter.ViewHolder viewHolder = (MyRecyclerAdapter.ViewHolder) recyclerView.findViewHolderForAdapterPosition(position);

        T item = (T) view.getTag(tagModel);

        if (interfase != null && viewHolder != null) {

            interfase.OnDoubleClick(viewHolder.binding, viewHolder, view, item, position);
        }
    }

    @Override @SuppressWarnings("unchecked")
    public void SelectedItem(@NonNull View selectedView, int position, RecyclerView.ViewHolder recyclerViewHolder) {

        MyRecyclerAdapter.ViewHolder viewHolder = (MyRecyclerAdapter.ViewHolder) recyclerViewHolder;

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

            new LogCat("onChanged", "sender", sender);

            RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeChanged(ObservableList sender, int positionStart, int itemCount) {

            new LogCat("onItemRangeChanged", "onItemRangeChanged", "sender", sender, "positionStart", positionStart, "itemCount", itemCount);

            RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyItemRangeChanged(positionStart, itemCount);
            }
        }

        @Override
        public void onItemRangeInserted(ObservableList sender, int positionStart, int itemCount) {

            new LogCat("itemInserted", "positionStart", positionStart, "itemCount", itemCount);

            RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyItemRangeInserted(positionStart, itemCount);
            }
        }

        @Override
        public void onItemRangeMoved(ObservableList sender, int fromPosition, int toPosition, int itemCount) {

            RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> adapter = adapterReference.get();

            if (adapter != null) {

                adapter.notifyItemMoved(fromPosition, toPosition);
            }
        }

        @Override
        public void onItemRangeRemoved(ObservableList sender, int positionStart, int itemCount) {

            RecyclerView.Adapter<MyRecyclerAdapter.ViewHolder> adapter = adapterReference.get();

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


    //----- Interface -----//


    // ----- ModelMethods ----- //

    public void resetMethods() {

        if (searched) {

            methodsHidden.clear();
        }

        methodsDisplayed.clear();
    }

    public void loadMethod(@NonNull T method) {

        int position = methodsDisplayed.size();

        if (searched) {

            this.methodsHidden.add(position, method);
        }

        this.methodsDisplayed.add(position, method);
        this.notifyItemInserted(position);
    }

    public void loadMethod(@NonNull DataSnapshot dataSnapshot) {

        T methods = methodInterfase.getMethod(dataSnapshot);
        this.loadMethod(methods);
    }

    public void addMethod(@NonNull T method) {

        String index = methodInterfase.getIndex(method);

        if (searched) {

            int positionHidden = setPositionHidden(index);
            this.methodsHidden.add(positionHidden, method);
        }

        int positionDisplayed = setPositionDisplayed(index);
        this.methodsDisplayed.add(positionDisplayed, method);
        this.notifyItemInserted(positionDisplayed);
    }

    public void addMethod(@NonNull DataSnapshot dataSnapshot) {

        T method = methodInterfase.getMethod(dataSnapshot);
        addMethod(method);
    }

    public void modifyMethod(@NonNull DataSnapshot dataSnapshot) {

        try {

            String identifier = methodInterfase.getIdentifier(dataSnapshot);
            String index = methodInterfase.getIndex(dataSnapshot);
            T method = methodInterfase.getMethod(dataSnapshot);

            if (searched) {

                int hidenMovedFrom = itemHiddenWasMoved(dataSnapshot);
                int displayedMovedFrom = itemDisplayedWasMoved(dataSnapshot);

                if (hidenMovedFrom >= 0 && displayedMovedFrom >= 0) {

                    this.methodsHidden.remove(hidenMovedFrom);
                    this.methodsDisplayed.remove(displayedMovedFrom);
                    notifyItemRemoved(displayedMovedFrom);

                    int addPositionHidden = setPositionHidden(index);
                    int addPositionDisplayed = setPositionDisplayed(index);
                    this.methodsHidden.add(addPositionHidden, method);
                    this.methodsDisplayed.add(addPositionDisplayed, method);
                    notifyItemInserted(addPositionDisplayed);

                } else {

                    int positionHidden = getPositionHidden(identifier);
                    int positionDisplayed = getPositionDisplayed(identifier);
                    this.methodsHidden.set(positionHidden, method);
                    this.methodsDisplayed.set(positionDisplayed, method);
                    notifyItemChanged(positionDisplayed);
                }

            } else {

                int displayedMovedFrom = itemDisplayedWasMoved(dataSnapshot);

                if (displayedMovedFrom >= 0) {

                    this.methodsDisplayed.remove(displayedMovedFrom);
                    notifyItemRemoved(displayedMovedFrom);

                    int addPositionDisplayed = setPositionDisplayed(index);
                    this.methodsDisplayed.add(addPositionDisplayed, method);
                    notifyItemInserted(addPositionDisplayed);

                } else {

                    int positionDisplayed = getPositionDisplayed(identifier);

                    this.methodsDisplayed.set(positionDisplayed, method);
                    notifyItemChanged(positionDisplayed);
                }
            }


        } catch (Exception ignored) {}
    }

    public void modifyMethod(@NonNull T method) {

        String identifier = methodInterfase.getIdentifier(method);
        String index = methodInterfase.getIndex(method);
        int position = -1;

        try {

            if (searched) {

                int hidenMovedFrom = itemHiddenWasMoved(method);
                int displayedMovedFrom = itemDisplayedWasMoved(method);

                if (hidenMovedFrom >= 0 && displayedMovedFrom >= 0) {

                    this.methodsHidden.remove(hidenMovedFrom);
                    this.methodsDisplayed.remove(displayedMovedFrom);
                    notifyItemRemoved(displayedMovedFrom);

                    int addPositionHidden = setPositionHidden(index);
                    int addPositionDisplayed = setPositionDisplayed(index);
                    this.methodsHidden.add(addPositionHidden, method);
                    this.methodsDisplayed.add(addPositionDisplayed, method);
                    notifyItemInserted(addPositionDisplayed);

                    position = addPositionDisplayed;

                } else {

                    int positionHidden = getPositionHidden(identifier);
                    int positionDisplayed = getPositionDisplayed(identifier);
                    this.methodsHidden.set(positionHidden, method);
                    this.methodsDisplayed.set(positionDisplayed, method);
                    notifyItemChanged(positionDisplayed);

                    position = positionDisplayed;
                }

            } else {

                int displayedMovedFrom = itemDisplayedWasMoved(method);

                if (displayedMovedFrom >= 0) {

                    this.methodsDisplayed.remove(displayedMovedFrom);
                    notifyItemRemoved(displayedMovedFrom);

                    int addPositionDisplayed = setPositionDisplayed(index);
                    this.methodsDisplayed.add(addPositionDisplayed, method);
                    notifyItemInserted(addPositionDisplayed);

                    position = addPositionDisplayed;

                } else {

                    int positionDisplayed = getPositionDisplayed(identifier);
                    this.methodsDisplayed.set(positionDisplayed, method);
                    notifyItemChanged(positionDisplayed);

                    position = positionDisplayed;
                }
            }

        } catch (Exception ignored) {}
    }

    public void modifyMethodWithOutNotify(@NonNull T method) {

        String identifier = methodInterfase.getIdentifier(method);
        String index = methodInterfase.getIndex(method);
        int position = -1;

        try {

            if (searched) {

                int hidenMovedFrom = itemHiddenWasMoved(method);
                int displayedMovedFrom = itemDisplayedWasMoved(method);

                if (hidenMovedFrom >= 0 && displayedMovedFrom >= 0) {

                    this.methodsHidden.remove(hidenMovedFrom);
                    this.methodsDisplayed.remove(displayedMovedFrom);

                    int addPositionHidden = setPositionHidden(index);
                    int addPositionDisplayed = setPositionDisplayed(index);
                    this.methodsHidden.add(addPositionHidden, method);
                    this.methodsDisplayed.add(addPositionDisplayed, method);

                    position = addPositionDisplayed;

                } else {

                    int positionHidden = getPositionHidden(identifier);
                    int positionDisplayed = getPositionDisplayed(identifier);
                    this.methodsHidden.set(positionHidden, method);
                    this.methodsDisplayed.set(positionDisplayed, method);

                    position = positionDisplayed;
                }

            } else {

                int displayedMovedFrom = itemDisplayedWasMoved(method);

                if (displayedMovedFrom >= 0) {

                    this.methodsDisplayed.remove(displayedMovedFrom);

                    int addPositionDisplayed = setPositionDisplayed(index);
                    this.methodsDisplayed.add(addPositionDisplayed, method);

                    position = addPositionDisplayed;

                } else {

                    int positionDisplayed = getPositionDisplayed(identifier);
                    this.methodsDisplayed.set(positionDisplayed, method);

                    position = positionDisplayed;
                }
            }

        } catch (Exception ignored) {}
    }

    public void moveMethod(@NonNull DataSnapshot dataSnapshot) {

        String identifier = methodInterfase.getIdentifier(dataSnapshot);
        T method = methodInterfase.getMethod(dataSnapshot);

        try {

            if (searched) {

                int positionHidden = getPositionHidden(identifier);
                int positionDisplayed = getPositionDisplayed(identifier);

                this.methodsHidden.set(positionHidden, method);
                this.methodsDisplayed.set(positionDisplayed, method);

            } else {

                int positionDisplayed = getPositionDisplayed(identifier);

                this.methodsDisplayed.set(positionDisplayed, method);
            }

        } catch (Exception ignored) {}
    }

    public void moveMethod(@NonNull T method) {

        String identifier = methodInterfase.getIdentifier(method);

        try {

            if (searched) {

                int positionHidden = getPositionHidden(identifier);
                int positionDisplayed = getPositionDisplayed(identifier);

                this.methodsHidden.set(positionHidden, method);
                this.methodsDisplayed.set(positionDisplayed, method);

            } else {

                int positionDisplayed = getPositionDisplayed(identifier);

                this.methodsDisplayed.set(positionDisplayed, method);
            }

        } catch (Exception ignored) {}
    }

    public void removeMethod(@NonNull DataSnapshot dataSnapshot) {

        String identifier = methodInterfase.getIdentifier(dataSnapshot);
        removeMethod(identifier);
    }

    public void removeMethod(@NonNull T method) {

        String identifier = methodInterfase.getIdentifier(method);
        removeMethod(identifier);
    }

    public void removeMethod(String identifier) {

        try {

            if (searched) {

                int positionHidden = getPositionHidden(identifier);
                this.methodsHidden.remove(positionHidden);
            }

            int positionDisplayed = getPositionDisplayed(identifier);
            this.methodsDisplayed.remove(positionDisplayed);
            this.notifyItemRemoved(positionDisplayed);

        } catch (Exception ignored) {}
    }

    public void removeMethodWithOutNotify(@NonNull T method) {

        String identifier = methodInterfase.getIdentifier(method);

        try {

            if (searched) {

                int positionHidden = getPositionHidden(identifier);
                this.methodsHidden.remove(positionHidden);
            }

            int positionDisplayed = getPositionDisplayed(identifier);
            this.methodsDisplayed.remove(positionDisplayed);

        } catch (Exception ignored) {}
    }

    public void removeMethod(int position) {

        if (searched) {

            //    int positionHidden = getPositionHidden(identifier);
            //   int positionDisplayed = getPositionDisplayed(identifier);

            //  this.methodsHidden.remove(positionHidden);
            //  this.methodsDisplayed.remove(positionDisplayed);

        } else {

            this.methodsDisplayed.remove(position);
        }
    }


    // ----- Tools ----- //

    public int itemHiddenWasMoved(DataSnapshot dataSnapshot) {

        String identifier = methodInterfase.getIdentifier(dataSnapshot);
        String index = methodInterfase.getIndex(dataSnapshot);
        int position =-1, movedFrom = -1;

        for (T compareMethod : methodsHidden) {

            position = position + 1;

            String compareIdentifier = methodInterfase.getIdentifier(compareMethod);

            if (identifier.equals(compareIdentifier)) {

                String compareIndex = methodInterfase.getIndex(compareMethod);

                if (!index.equals(compareIndex)) {

                    movedFrom = position;
                    break;
                }
            }
        }

        return movedFrom;
    }

    public int itemHiddenWasMoved(T method) {

        String identifier = methodInterfase.getIdentifier(method);
        String index = methodInterfase.getIndex(method);
        int position =-1, movedFrom = -1;

        for (T compareMethod : methodsHidden) {

            position = position + 1;

            String compareIdentifier = methodInterfase.getIdentifier(compareMethod);

            if (identifier.equals(compareIdentifier)) {

                String compareIndex = methodInterfase.getIndex(compareMethod);

                if (!index.equals(compareIndex)) {

                    movedFrom = position;
                    break;
                }
            }
        }

        return movedFrom;
    }

    public int itemDisplayedWasMoved(DataSnapshot dataSnapshot) {

        String identifier = methodInterfase.getIdentifier(dataSnapshot);
        String index = methodInterfase.getIndex(dataSnapshot);
        int position =-1, movedFrom = -1;

        for (T compareMethod : methodsDisplayed) {

            position = position + 1;

            String compareIdentifier = methodInterfase.getIdentifier(compareMethod);

            if (identifier.equals(compareIdentifier)) {

                String compareIndex = methodInterfase.getIndex(compareMethod);

                if (!index.equals(compareIndex)) {

                    movedFrom = position;
                    break;
                }
            }
        }

        return movedFrom;
    }

    public int itemDisplayedWasMoved(T method) {

        String identifier = methodInterfase.getIdentifier(method);
        String index = methodInterfase.getIndex(method);
        int position =-1, movedFrom = -1;

        for (T compareMethod : methodsDisplayed) {

            position = position + 1;

            String compareIdentifier = methodInterfase.getIdentifier(compareMethod);

            if (identifier.equals(compareIdentifier)) {

                String compareIndex = methodInterfase.getIndex(compareMethod);

                if (!index.equals(compareIndex)) {

                    movedFrom = position;
                    break;
                }
            }
        }

        return movedFrom;
    }

    private int getPositionHidden(String identifier) {

        int position = -1, returnPosition = -1;

        for (final T compareMethod : methodsHidden) {

            position = position + 1;

            String indexCompare = methodInterfase.getIdentifier(compareMethod);

            if (identifier.equals(indexCompare)) {

                returnPosition = position;
                break;
            }
        }

        //if (returnPosition == -1) {

        //  returnPosition = setPositionHidden(identifier);
        //}

        return returnPosition;
    }

    public int getPositionDisplayed(T method) {

        int position = -1, returnPosition = -1;

        for (final T compareMethod : methodsDisplayed) {

            position = position + 1;
            String index = methodInterfase.getIdentifier(method);
            String indexCompare = methodInterfase.getIdentifier(compareMethod);

            if (index.equals(indexCompare)) {

                returnPosition = position;
                break;
            }
        }

        // if (returnPosition == -1) {

        ///   returnPosition = setPositionDisplayed(identifier);
        //}

        return returnPosition;
    }

    public int getPositionDisplayed(DataSnapshot dataSnapshot) {

        int position = -1, returnPosition = -1;

        for (final T compareMethod : methodsDisplayed) {

            position = position + 1;
            String index = methodInterfase.getIdentifier(dataSnapshot);
            String indexCompare = methodInterfase.getIdentifier(compareMethod);

            if (index.equals(indexCompare)) {

                returnPosition = position;
                break;
            }
        }

        // if (returnPosition == -1) {

        ///   returnPosition = setPositionDisplayed(identifier);
        //}

        return returnPosition;
    }

    public int getPositionDisplayed(String identifier) {

        int position = -1, returnPosition = -1;

        for (final T compareMethod : methodsDisplayed) {

            position = position + 1;
            String indexCompare = methodInterfase.getIdentifier(compareMethod);

            if (identifier.equals(indexCompare)) {

                returnPosition = position;
                break;
            }
        }

        // if (returnPosition == -1) {

        ///   returnPosition = setPositionDisplayed(identifier);
        //}

        return returnPosition;
    }

    private int setPositionHidden(String index) {

        long arraySize = methodsHidden.size();
        int returnPosition = 0;

        if (!methodsHidden.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String indexCompare = methodInterfase.getIndex(methodsHidden.get(position));
                int compareMarge = index.compareTo(indexCompare);

                if (compareMarge < 0) {

                    returnPosition = position;
                    break;

                } else if (compareMarge == 0) {

                    returnPosition = position + 1;

                } else if (compareMarge > 0) {

                    if (position == arraySize - 1) {

                        returnPosition = position + 1;

                    } else {

                        String indexNext = methodInterfase.getIndex(methodsHidden.get(position));
                        int nextMarge = index.compareTo(indexNext);

                        if (nextMarge < 0) {

                            returnPosition = position + 1;
                            break;
                        }
                    }
                }
            }
        }

        return returnPosition;
    }

    public int setPositionDisplayed(String index) {

        long arraySize = methodsDisplayed.size();
        int returnPosition = 0;

        if (!methodsDisplayed.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String indexCompare = methodInterfase.getIndex(methodsDisplayed.get(position));
                int compareMarge = index.compareTo(indexCompare);

                if (compareMarge < 0) {

                    returnPosition = position;
                    break;

                } else if (compareMarge == 0) {

                    returnPosition = position + 1;

                } else if (compareMarge > 0) {

                    if (position == arraySize - 1) {

                        returnPosition = position + 1;

                    } else {

                        String indexNext = methodInterfase.getIndex(methodsDisplayed.get(position));
                        int nextMarge = index.compareTo(indexNext);

                        if (nextMarge < 0) {

                            returnPosition = position + 1;
                            break;
                        }
                    }
                }
            }
        }

        return returnPosition;
    }


    // ----- Search ----- //

    public boolean setSearch(@NonNull String search) {

        boolean globalResult = FALSE;

        if (searched) {

            String Search = new MyString().toLowerCaseAndRemoveAccentMark(search);

            for (final T modelMethods : methodsHidden) {

                boolean containsSearchOnChilds = methodInterfase.containsSearchOnChilds(modelMethods, Search);
                boolean contains = FALSE;

                if (containsSearchOnChilds) {

                    contains = TRUE;

                } else {

                    for (Object object : methodInterfase.getSearchParameters(modelMethods)) {

                        String Parameter = String.valueOf(object);
                        String parameter = new MyString().toLowerCaseAndRemoveAccentMark(Parameter);

                        if (parameter.contains(Search)) {

                            contains = TRUE;
                            break;
                        }
                    }
                }

                if (contains) {

                    if (!this.methodsDisplayed.contains(modelMethods)) {

                        String index = methodInterfase.getIndex(modelMethods);
                        int position = setPositionDisplayed(index);
                        this.methodsDisplayed.add(position, modelMethods);
                    }

                } else {

                    this.methodsDisplayed.remove(modelMethods);
                }

                if (globalResult == FALSE && contains == TRUE) {

                    globalResult = TRUE;
                }
            }
        }

        return globalResult;
    }
}