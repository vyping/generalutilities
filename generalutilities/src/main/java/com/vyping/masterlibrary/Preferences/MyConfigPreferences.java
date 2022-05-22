package com.vyping.masterlibrary.Preferences;

import static com.vyping.masterlibrary.Common.Definitions.PREFERENCES_CONFIG;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.annotation.StringDef;

import com.vyping.masterlibrary.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class MyConfigPreferences {

    private SharedPreferences.Editor editor;

    public static final String TAG_THEME = "Theme", TAG_ANIMATION = "Animations";

    @Retention(RetentionPolicy.SOURCE)
    @StringDef({TAG_THEME, TAG_ANIMATION})
    public @interface Tags {
    }


    /*----- Common Methods -----*/

    private SharedPreferences GetPreferences(@NonNull Context context) {

        return context.getSharedPreferences(PREFERENCES_CONFIG, Context.MODE_PRIVATE);
    }

    private SharedPreferences.Editor EditPreferences(@NonNull Context context) {

        editor = GetPreferences(context).edit();

        return editor;
    }

    private void ApplyChanges() {

        editor.apply();
        editor.commit();
    }

    private void ClearEditor() {

        editor.clear();
        editor.apply();
    }


    /*----- Set Methods -----*/

    public void setAppTheme(@NonNull Context context, int theme) {

        EditPreferences(context).putInt(TAG_THEME, theme);
        ApplyChanges();
    }

    public void setAnimations(@NonNull Context context, boolean active) {

        EditPreferences(context).putBoolean(TAG_ANIMATION, active);
        ApplyChanges();
    }


    /*----- Get Methods -----*/

    public int getAppTheme(@NonNull Context context) {

        return GetPreferences(context).getInt(TAG_THEME, R.style.AppLightTheme);
    }

    public boolean getAnimations(@NonNull Context context) {

        return GetPreferences(context).getBoolean(TAG_ANIMATION, false);
    }
}
