package com.vyping.masterlibrary.models.selectholders;

import static android.view.View.GONE;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.os.Parcelable;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class SelectedHolderMethods<T> {

    private RecyclerView recyclerView;
    private SelectedHolder<T> selectionHolder;
    private Handler<T> handler;
    private Parcelable state;


    // ----- SetUp ----- //

    public SelectedHolderMethods(Handler<T> handler) {

        this.selectionHolder = new SelectedHolder<>();
        this.handler = handler;
    }

    public SelectedHolderMethods(RecyclerView recyclerView, Handler<T> handler) {

        this.recyclerView = recyclerView;
        this.selectionHolder = new SelectedHolder<>();
        this.handler = handler;
    }

    public SelectedHolderMethods(@NonNull SelectedHolder<T> selectionHolder) {

        this.selectionHolder = selectionHolder;
    }

    public SelectedHolderMethods(RecyclerView recyclerView, @NonNull SelectedHolder<T> selectionHolder) {

        this.recyclerView = recyclerView;
        this.selectionHolder = selectionHolder;
    }

    public SelectedHolderMethods(@NonNull RecyclerView.ViewHolder viewHolder, T method, int position) {

        this.selectionHolder = new SelectedHolder<T>(viewHolder, method, position);
    }


    // ----- Getters Methods ----- //

    public SelectedHolder<T> getSelectionHolder()
    {
        return this.selectionHolder;
    }

    public RecyclerView.ViewHolder getViewHolder()
    {
        return selectionHolder.viewHolder;
    }

    public int getPosition()
    {
        return this.selectionHolder.position;
    }

    public T getMethod()
    {
        return this.selectionHolder.method;
    }


    // ----- Setters Methods ----- //

    public void saveState() {

        state = Objects.requireNonNull(recyclerView.getLayoutManager()).onSaveInstanceState();
    }

    public void setSelectionHolder(SelectedHolder<T> selectionHolder) {

        this.selectionHolder = selectionHolder;
    }

    public void setViewHolder(RecyclerView.ViewHolder viewHolder) {

        this.selectionHolder.viewHolder = viewHolder;
    }

    public void setPosition(int position)
    {
        this.selectionHolder.position = position;
    }

    public void setMethod(T method)
    {
        this.selectionHolder.method = method;
    }


    // ----- Compounds Methods ----- //

    public void resetSelection() {

        this.selectionHolder = new SelectedHolder<>();
    }

    public SelectedHolderMethods<T> selected(RecyclerView.ViewHolder viewHolder, @NonNull T methods, int position, Interfase<T> interfase) {

        SelectedHolder<T> selectedHolder = new SelectedHolder<>(viewHolder, methods, position);

        if (getMethod() == null) {

            if (recyclerView != null) {

                state = Objects.requireNonNull(recyclerView.getLayoutManager()).onSaveInstanceState();
            }

            setSelectionHolder(handler.SetSelectionMethod(selectedHolder, TRUE));

            interfase.SelectedMethod(getMethod());

        } else {

            boolean selected = handler.IsSelectionSame(getSelectionHolder(), selectedHolder);

            if (selected) {

                if (recyclerView != null) {

                    Objects.requireNonNull(recyclerView.getLayoutManager()).onRestoreInstanceState(state);
                }

                setSelectionHolder(handler.SetSelectionMethod(selectedHolder, FALSE));

                interfase.UnselectedMethod(getMethod());

                setSelectionHolder(new SelectedHolder<>());

            } else {

                if (recyclerView != null) {

                    state = Objects.requireNonNull(recyclerView.getLayoutManager()).onSaveInstanceState();
                }

                SelectedHolder<T> prevHolder = handler.SetSelectionMethod(getSelectionHolder(), FALSE);
                setSelectionHolder(handler.SetSelectionMethod(selectedHolder, TRUE));

                interfase.ChangeMethod(prevHolder.method, getSelectionHolder().method);
            }
        }

        return this;
    }

    public SelectedHolderMethods<T> selectedWithOutReset(RecyclerView.ViewHolder viewHolder, @NonNull T methods, int position, Interfase<T> interfase) {

        SelectedHolder<T> selectedHolder = new SelectedHolder<>(viewHolder, methods, position);

        if (getMethod() == null) {

            if (recyclerView != null) {

                state = Objects.requireNonNull(recyclerView.getLayoutManager()).onSaveInstanceState();
            }

            setSelectionHolder(handler.SetSelectionMethod(selectedHolder, TRUE));

            interfase.SelectedMethod(getMethod());

        } else {

            boolean selected = handler.IsSelectionSame(getSelectionHolder(), selectedHolder);

            if (selected) {

                if (recyclerView != null) {

                    Objects.requireNonNull(recyclerView.getLayoutManager()).onRestoreInstanceState(state);
                }

                setSelectionHolder(handler.SetSelectionMethod(selectedHolder, FALSE));

                interfase.UnselectedMethod(getMethod());

            } else {

                if (recyclerView != null) {

                    state = Objects.requireNonNull(recyclerView.getLayoutManager()).onSaveInstanceState();
                }

                SelectedHolder<T> prevHolder = handler.SetSelectionMethod(getSelectionHolder(), FALSE);
                setSelectionHolder(handler.SetSelectionMethod(selectedHolder, TRUE));

                interfase.ChangeMethod(prevHolder.method, getSelectionHolder().method);
            }
        }

        return this;
    }

    public void restoreState() {

        if (recyclerView != null) {

            Objects.requireNonNull(recyclerView.getLayoutManager()).onRestoreInstanceState(state);
        }
    }


    // ----- Tools ----- //

    public void unHighLightHolder(int intView, int color) {

        if (getViewHolder() != null) {

            Context context = getViewHolder().itemView.getContext();

            View prevHolder = getViewHolder().itemView.findViewById(intView);
            prevHolder.getBackground().setTint(context.getResources().getColor(color));
        }
    }

    public void hideViewInHolder(int intView) {

        View view = getViewHolder().itemView.findViewById(intView);
        view.setVisibility(GONE);
    }

    public void unHighLightHolderAndHide(int viewUnHilighted, int viewHided, int color) {

        if (getViewHolder() != null) {

            Context context = getViewHolder().itemView.getContext();

            View prevHolder = getViewHolder().itemView.findViewById(viewUnHilighted);
            prevHolder.getBackground().setTint(context.getResources().getColor(color));

            View view = getViewHolder().itemView.findViewById(viewHided);
            view.setVisibility(GONE);
        }
    }


    // ----- Interfase ----- //

    public interface Handler<T> {

        default SelectedHolder<T> SetSelectionMethod(SelectedHolder<T>  holder, boolean selected) { return holder; };
        default boolean IsSelectionSame(SelectedHolder<T> prevHolder, SelectedHolder<T> newHolder) { return FALSE; };
    }

    public interface Interfase<T> {

        default void SelectedMethod(T newMethod) {};
        default void ChangeMethod(T prevMethod, T newMethod) {};
        default void UnselectedMethod(T oldMethod) {};
    }
}
