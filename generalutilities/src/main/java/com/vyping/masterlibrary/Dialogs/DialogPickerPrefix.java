package com.vyping.masterlibrary.Dialogs;

import static java.lang.Boolean.TRUE;

import android.app.Dialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;

public abstract class DialogPickerPrefix {

    private NumberPicker Np_Picker;
    private EditText Et_InputText;


    /*----------- SetUp - Section ----------*/

    public DialogPickerPrefix(@NonNull Context context, final int parameters, final int items) {

        String[] ListParams = context.getResources().getStringArray(parameters);
        final String[] ListItems = context.getResources().getStringArray(items);

        new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(Dialog dialog) {

                //Np_Picker = dialog.findViewById(R.id.Np_Picker_Selection);
                Np_Picker.setMinValue(0);
                Np_Picker.setMaxValue(ListItems.length - 1);
                Np_Picker.setDisplayedValues(ListItems);

                //Et_InputText = new EditTexts().setEditText(dialog, R.id.Et_InputText_Input, ListParams[6]);

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

            }

            @Override
            protected void PositiveClick() {

                int Selected = Np_Picker.getValue();
                String Selection = ListItems[Selected];
                String InputText = String.valueOf(Et_InputText.getText());

                InputAndPicked(Selected, Selection, InputText);
            }
        };
    }


    /*----------- Return - Section ----------*/

    protected abstract void InputAndPicked(int Selected, String Selection, String InputText);
}
