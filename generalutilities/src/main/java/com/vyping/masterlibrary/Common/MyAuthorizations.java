package com.vyping.masterlibrary.Common;

import static com.vyping.masterlibrary.Common.Definitions.ACCESS_DENIED;

import android.content.Context;

import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.vyping.masterlibrary.Bucles.MyBucleFor;
import com.vyping.masterlibrary.Firebase.RemoteConfig;
import com.vyping.masterlibrary.Preferences.MyAccessPreferences;

import java.util.ArrayList;
import java.util.HashMap;

public class MyAuthorizations {

    public void setAdminPermissions(Context context, String position, ArrayList<Integer> modules, int defaults, PermissionsInterfase interfase) {

        String Position = new MyStrings().changeToFileName(position);

        new RemoteConfig().BasicRerquest(context, defaults, new RemoteConfig.SuccessListener() {

            @Override
            public void Success(FirebaseRemoteConfig firebaseRemoteConfig) {

                String permissions = firebaseRemoteConfig.getString(Position);
                HashMap<String, Object> mapPermissions = new Gson().fromJson(permissions, new TypeToken<HashMap<String, Object>>() {
                }.getType());

                new MyBucleFor().integersArray(modules, new MyBucleFor.IntInterface() {

                    @Override
                    public void intPosition(int module) {

                        String Module = new MyStrings().getStringResources(context, module);
                        boolean contains = mapPermissions.containsKey(Module);
                        int valueAccess = ACCESS_DENIED;

                        if (contains) {

                            Object objectAccess = mapPermissions.get(Module);
                            valueAccess = Integer.parseInt(String.valueOf(objectAccess).replace(".0", ""));
                        }

                        new MyAccessPreferences().SetAccessPermission(context, Module, valueAccess);
                    }

                    private void DummyVoid() {
                    }
                });

                interfase.Finish();
            }

            private void DummyVoid() {
            }
        });
    }


    /**
     * -------- Firebase Listeners Section
     */

    public interface PermissionsInterfase {

        void Finish();
    }
}
