package com.vyping.masterlibrary.Dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_SINGLE;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_THREE;
import static java.lang.Boolean.TRUE;

import android.app.Dialog;
import android.content.Context;
import android.view.ContextThemeWrapper;
import android.widget.LinearLayout;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.R;

public abstract class DialogPicker {

    private Context context;
    private final CreateDialog createDialog;

    private NumberPicker Np_Picker;

    private String[] ListItems;
    private int prevPicked, picked;


    /*----- SetUp -----*/

    public DialogPicker(@NonNull Context context, final int Parameters, final int Items, int Picked) {

        setParameters(context, Items, Picked);

        createDialog = new CreateDialog(context, Parameters, TRUE) {

            @Override
            protected void SetDialog(Dialog dialog) {

                setViewsOnLayout(dialog);

                Np_Picker.setMinValue(0);
                Np_Picker.setMaxValue(ListItems.length - 1);
                Np_Picker.setDisplayedValues(ListItems);
                Np_Picker.setWrapSelectorWheel(false);
                Np_Picker.setValue(picked);
                Np_Picker.setOnValueChangedListener(onValueChangedListener);

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

                picked = prevPicked;

                Np_Picker.setValue(picked);

                setOptionButtons();
            }

            @Override
            protected void PositiveClick() {

                picked = Np_Picker.getValue();
                String Selection = ListItems[picked];

                ButtonConfirm(picked, Selection);
            }
        };

        setOptionButtons();
    }

    private void setOptionButtons() {

        if (picked == prevPicked) {

            createDialog.setModeButtons(BUTTONS_SINGLE);

        } else {

            createDialog.setModeButtons(BUTTONS_THREE);
        }
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

            setOptionButtons();
        }

        private void DummyVoid() {}
    };


    /*----- Tools -----*/

    private void setParameters(@NonNull Context context, int items, int picked) {

        this.context = context;
        this.picked = picked;

        prevPicked = picked;

        ListItems = context.getResources().getStringArray(items);
    }

    private void setViewsOnLayout(@NonNull Dialog dialog) {

        int style = R.style.DialogNumberPicker;
        int attr = R.attr.dialogNumberPicker;

        LinearLayout layout = dialog.findViewById(R.id.Ll_Custom_MainContainer);
        ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

        Np_Picker = new NumberPicker(wrapper, null, attr, style);

        Paris.style(Np_Picker).apply(style);

        layout.addView(Np_Picker);
    }

    /*----- Return -----*/

    protected abstract void ChangePickedItem(int Selected, String Selection);
    protected abstract void ButtonConfirm(int Selected, String Selection);
}
