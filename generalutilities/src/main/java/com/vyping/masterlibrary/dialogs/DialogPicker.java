package com.vyping.masterlibrary.dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_REFRESH_ACCEPT;

import android.content.Context;
import android.view.ContextThemeWrapper;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.R;

public abstract class DialogPicker extends CreateDialog {

    private Context context;

    private NumberPicker Np_Picker;

    private String[] ListItems;
    private int prevPicked, picked;


    /*----- SetUp -----*/

    public DialogPicker(@NonNull Context context, final int parameters, final int Items, int Picked) {

        super(context, parameters);

        setParameters(context, Items, Picked);
        setDialogViews();
        setModeButtons(setModeButtons());
        setDialogListener(new DialogInterface() {

            @Override
            public void NegativeClick() {

            }

            @Override
            public void RefreshClick() {

                picked = prevPicked;

                Np_Picker.setValue(picked);

                setModeButtons(setModeButtons());
            }

            @Override
            public void PositiveClick() {

                picked = Np_Picker.getValue();
                String Selection = ListItems[picked];

                ButtonConfirm(picked, Selection);
            }
        });
    }

    private void setParameters(@NonNull Context context, int items, int picked) {

        this.context = context;
        this.picked = picked;

        prevPicked = picked;

        ListItems = context.getResources().getStringArray(items);
    }

    private void setDialogViews() {

        int style = R.style.DialogNumberPicker;
        int attr = R.attr.dialogNumberPicker;

        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);
        Np_Picker = new NumberPicker(wrapper, null, attr, style);
        Np_Picker.setMinValue(0);
        Np_Picker.setMaxValue(ListItems.length - 1);
        Np_Picker.setDisplayedValues(ListItems);
        Np_Picker.setWrapSelectorWheel(false);
        Np_Picker.setValue(picked);
        Np_Picker.setOnValueChangedListener(onValueChangedListener);
        Paris.style(Np_Picker).apply(style);

        addCustomView(Np_Picker);
    }


    /*----- Process -----*/

    public void setSelection(int selected) {

        this.picked = selected;

        Np_Picker.setValue(selected);
    }

    public void setSelection(String selection) {

        for (int i=0; i<=ListItems.length; i++) {

            String Selection = ListItems[i];

            if (selection.equals(Selection)) {

                picked = i;
            }
        }

        Np_Picker.setValue(picked);
    }

    public String getSelection() {

        picked = Np_Picker.getValue();

        return ListItems[picked];
    }


    /*----- Listeners -----*/

    private final NumberPicker.OnValueChangeListener onValueChangedListener = new NumberPicker.OnValueChangeListener() {

        @Override
        public void onValueChange(NumberPicker numberPicker, int i, int i1) {

            picked = i1;
            String Selection = ListItems[picked];

            ChangePickedItem(picked, Selection);

            setModeButtons(setModeButtons());
        }

        private void DummyVoid() {}
    };


    /*----- Utilities -----*/

    private int setModeButtons() {

        if (picked == prevPicked) {

            return BUTTONS_CANCEL;

        } else {

            return BUTTONS_REFRESH_ACCEPT;
        }
    }


    /*----- Return -----*/

    protected abstract void ChangePickedItem(int Selected, String Selection);
    protected abstract void ButtonConfirm(int Selected, String Selection);
}
