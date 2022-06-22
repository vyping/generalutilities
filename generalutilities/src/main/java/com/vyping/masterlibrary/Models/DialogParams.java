package com.vyping.masterlibrary.Models;

import static com.vyping.masterlibrary.dialogs.CreateDialog.DIALOG_NORMAL;

import android.content.Context;
import android.graphics.drawable.Drawable;

import androidx.annotation.NonNull;

import com.vyping.masterlibrary.Images.MyDrawable;
import com.vyping.masterlibrary.dialogs.CreateDialog;

public class DialogParams {

    public Context CONTEXT;
    public static String TITLE, DESCRIPTION, HELP, ERROR, SUCCESS;
    public static Drawable ICON;
    public static int MODE;
    public static String[] PARAMS;


    /*----- Main Model -----*/

    public DialogParams(@NonNull Context context, int arrayParameters) {

        CONTEXT = context;
        PARAMS = CONTEXT.getResources().getStringArray(arrayParameters);

        MODE = DIALOG_NORMAL;
        ICON = new MyDrawable().extractFromString(CONTEXT, PARAMS[0]);
        TITLE = ExtractValue(PARAMS, 1, "Title");
        DESCRIPTION = ExtractValue(PARAMS, 2, "Description");
        HELP = ExtractValue(PARAMS, 3, "Help");
        ERROR = ExtractValue(PARAMS, 4, "Error");
        SUCCESS = ExtractValue(PARAMS, 5, "Success");
    }

    public DialogParams(@NonNull Context context, @CreateDialog.DialogMode int dialogMode, int arrayParameters) {

        CONTEXT = context;
        PARAMS = CONTEXT.getResources().getStringArray(arrayParameters);

        MODE = dialogMode;
        ICON = new MyDrawable().extractFromString(CONTEXT, PARAMS[0]);
        TITLE = ExtractValue(PARAMS, 1, "Title");
        DESCRIPTION = ExtractValue(PARAMS, 2, "Description");
        HELP = ExtractValue(PARAMS, 3, "Help");
        ERROR = ExtractValue(PARAMS, 4, "Error");
        SUCCESS = ExtractValue(PARAMS, 5, "Success");
    }

    public DialogParams(@NonNull Context context, Drawable icon, String title, String description, String help, String error, String success) {

        CONTEXT = context;
        //String stringIcon = CONTEXT.getResources().getResourceEntryName(icon);
        //PARAMS = new String[]{stringIcon, title, description, help, error, success};

        MODE = DIALOG_NORMAL;
        ICON = icon;
        TITLE = title;
        DESCRIPTION = description;
        HELP = help;
        ERROR = error;
        SUCCESS = success;
    }

    public DialogParams(@NonNull Context context, @CreateDialog.DialogMode int dialogMode, Drawable icon, String title, String description, String help, String error, String success) {

        CONTEXT = context;
        //int id = context.getResources().getIdentifier("picture0001", "drawable", context.getPackageName());
        //String stringIcon = CONTEXT.getResources().getResourceEntryName(icon);
        //PARAMS = new String[]{stringIcon, title, description, help, error, success};

        MODE = dialogMode;
        ICON = icon;
        TITLE = title;
        DESCRIPTION = description;
        HELP = help;
        ERROR = error;
        SUCCESS = success;
    }

    public DialogParams(@NonNull Context context, int icon, String title, String description, String help, String error, String success) {

        CONTEXT = context;
        String stringIcon = CONTEXT.getResources().getResourceEntryName(icon);
        PARAMS = new String[]{stringIcon, title, description, help, error, success};

        MODE = DIALOG_NORMAL;
        ICON = new MyDrawable().extractFromResources(CONTEXT, icon);
        TITLE = title;
        DESCRIPTION = description;
        HELP = help;
        ERROR = error;
        SUCCESS = success;
    }

    public DialogParams(@NonNull Context context, @CreateDialog.DialogMode int dialogMode, int icon, String title, String description, String help, String error, String success) {

        CONTEXT = context;
        String stringIcon = CONTEXT.getResources().getResourceEntryName(icon);
        PARAMS = new String[]{stringIcon, title, description, help, error, success};

        MODE = dialogMode;
        ICON = new MyDrawable().extractFromResources(CONTEXT, icon);
        TITLE = title;
        DESCRIPTION = description;
        HELP = help;
        ERROR = error;
        SUCCESS = success;
    }


    /*----- ModelMethods -----*/

    public String ExtractValue(String[] listParams, int index, String ifVoid) {

        try {

            return listParams[index];

        } catch (Exception e) {

            return ifVoid;
        }
    }


    /*----- Get Parameters -----*/

    public Context GetContext() {

        return CONTEXT;
    }

    public int GetDialogMode() {

        return MODE;
    }

    public Drawable GetIcon() {

        return ICON;
    }

    public String GetTitle() {

        return TITLE;
    }

    public String GetHelp() {

        return HELP;
    }

    public String GetDescription() {

        return DESCRIPTION;
    }

    public String GetError() {

        return ERROR;
    }

    public String GetSuccess() {

        return SUCCESS;
    }


    /*----- Set Parameters -----*/

    public void SetContext(@NonNull Context context) {

        CONTEXT = context.getApplicationContext();
    }

    public void SetDialogMode(int mode) {

        MODE = mode;
    }

    public void SetIcon(String icon) {

        ICON = new MyDrawable().extractFromString(CONTEXT, icon);
    }

    public void SetIcon(int icon) {

        ICON = new MyDrawable().extractFromResources(CONTEXT, icon);
    }

    public void SetIcon(Drawable icon) {

        ICON = icon;
    }

    public void SetTitle(String title) {

        TITLE = title;
    }

    public void SetHelp(String help) {

        HELP = help;
    }

    public void SetDescription(String description) {

        DESCRIPTION = description;
    }

    public void SetError(String error) {

        ERROR = error;
    }

    public void SetSuccess(String success) {

        SUCCESS = success;
    }
}
