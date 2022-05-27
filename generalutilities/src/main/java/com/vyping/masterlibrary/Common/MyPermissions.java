package com.vyping.masterlibrary.Common;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.vyping.masterlibrary.dialogs.CreateDialog;
import com.vyping.masterlibrary.dialogs.DialogShowInfo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;


public class MyPermissions {

    private Context context;
    private Interfase interfase;

    private String permissions[];
    private int parameters, requestCode;

    public static final String PERMISSION_PHONE = Manifest.permission.CALL_PHONE; // <uses-permission android:name="android.permission.CALL_PHONE" />
    public static final String PERMISSION_SMS = Manifest.permission.SEND_SMS; // <uses-permission android:name="android.permission.SEND_SMS"/>
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;  //     <uses-permission android:name="android.permission.CAMERA" />
    public static final String PERMISSION_LOCATION_FINE = Manifest.permission.ACCESS_FINE_LOCATION;  // <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    public static final String PERMISSION_LOCATION_COARSE = Manifest.permission.ACCESS_COARSE_LOCATION; //  <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />

    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static final String PERMISSION_RECOGNITION = Manifest.permission.ACTIVITY_RECOGNITION; //  <uses-permission android:name="android.permission.ACTIVITY_RECOGNITION"/>

    public static final int PERMISSIONS_GRANTED = PackageManager.PERMISSION_GRANTED;  //

    public static final int PERMISSION_CODE_PHONE = 10000;
    public static final int PERMISSION_CODE_SMS = 20000;
    public static final int PERMISSION_CODE_CAMERA = 30000;
    public static final int PERMISSION_CODE_LOCATION = 40000;
    public static final int PERMISSION_CODE_RECOGNITION = 50000;


    @Retention(RetentionPolicy.SOURCE)
    @IntDef({PERMISSION_CODE_PHONE, PERMISSION_CODE_SMS, PERMISSION_CODE_CAMERA, PERMISSION_CODE_LOCATION, PERMISSION_CODE_RECOGNITION})
    public @interface RequestCode { }


    // ----- Setup ----- //

    public MyPermissions(Context context, int parameters, @NonNull String permissions[], @RequestCode int requestCode) {

        SetParameters(context, parameters, permissions, requestCode);
    }

    public MyPermissions(Context context, int parameters, @NonNull String permissions[], @RequestCode int requestCode, Interfase interfase) {

        SetParameters(context, parameters, permissions, requestCode, interfase);

        boolean permissionsGranted = checkPermissionsGranted();

        if (permissionsGranted) {

            interfase.PermissionsResult(requestCode);

        } else {

            boolean permissionsRationale = checkPermissionsRationale();

            if (permissionsRationale) {

                ShowDialogPermissions();

            } else {

                ActivityCompat.requestPermissions(((Activity) context), permissions, requestCode);
            }
        }
    }

    public void RequestPermissions(Interfase interfase) {

        this.interfase = interfase;

        boolean permissionsGranted = checkPermissionsGranted();

        if (permissionsGranted) {

            interfase.PermissionsResult(requestCode);

        } else {

            boolean permissionsRationale = checkPermissionsRationale();

            if (permissionsRationale) {

                ShowDialogPermissions();

            } else {

                ActivityCompat.requestPermissions(((Activity) context), permissions, requestCode);
            }
        }
    }


    // ----- Methods ----- //

    private boolean checkPermissionsGranted() {

        boolean granted = TRUE;

        for (String permission : permissions) {

            if (ContextCompat.checkSelfPermission(context, permission) != PERMISSIONS_GRANTED) {

                granted = FALSE;
                break;
            }
        }

        return granted;
    }

    private boolean checkPermissionsRationale() {

        boolean granted = FALSE;

        for (String permission : permissions) {

            if (ActivityCompat.shouldShowRequestPermissionRationale(((Activity) context), permission)) {

                granted = TRUE;
                break;
            }
        }

        return granted;
    }

    public void PermissionsResult(@RequestCode int requestCode, String permissions[], int[] grantResults) {

        switch (requestCode) {

            case PERMISSION_CODE_PHONE: {

                if (permissions[0].equals(PERMISSION_PHONE) && (grantResults.length > 0 && grantResults[0] == PERMISSIONS_GRANTED)) {

                    interfase.PermissionsResult(PERMISSION_CODE_PHONE);
                }

            }
            case PERMISSION_CODE_SMS: {

                if (permissions[0].equals(PERMISSION_SMS) && (grantResults.length > 0 && grantResults[0] == PERMISSIONS_GRANTED)) {

                    interfase.PermissionsResult(PERMISSION_CODE_SMS);
                }

            }
            case PERMISSION_CODE_CAMERA: {

                if (permissions[0].equals(PERMISSION_CAMERA) && (grantResults.length > 0 && grantResults[0] == PERMISSIONS_GRANTED)) {

                    interfase.PermissionsResult(PERMISSION_CODE_CAMERA);
                }

            }
            case PERMISSION_CODE_LOCATION: {

                if (permissions[0].equals(PERMISSION_LOCATION_FINE) && grantResults[0] == PERMISSIONS_GRANTED && permissions[1].equals(PERMISSION_LOCATION_COARSE) && grantResults[1] == PERMISSIONS_GRANTED) {

                    interfase.PermissionsResult(PERMISSION_CODE_LOCATION);
                }
            }

            case PERMISSION_CODE_RECOGNITION: {

                if (permissions[0].equals(PERMISSION_RECOGNITION) && (grantResults.length > 0 && grantResults[0] == PERMISSIONS_GRANTED)) {

                    interfase.PermissionsResult(PERMISSION_CODE_RECOGNITION);
                }
            }
        }
    }


    // ----- Dialogs ----- //

    private void ShowDialogPermissions() {

        String[] listParams = context.getResources().getStringArray(parameters);
        String explaniation = listParams[3];

        DialogShowInfo dialogShowInfo = new DialogShowInfo(context, CreateDialog.DIALOG_CUSTOM, parameters, explaniation) {

            @Override
            protected boolean PositiveButton() {

                return false;
            }
        };
        dialogShowInfo.SetButtonNext(new CreateDialog.Interfase() {

            @Override
            public boolean ClickButton() {

                ActivityCompat.requestPermissions(((Activity) context), permissions, requestCode);

                return true;
            }

            private void DummyVoid() {
            }
        });
    }


    // ----- Tools ----- //

    private void SetParameters(Context context, int parameters, @NonNull String permissions[], @RequestCode int requestCode) {

        this.context = context;
        this.parameters = parameters;
        this.permissions = permissions;
        this.requestCode = requestCode;
    }

    private void SetParameters(Context context, int parameters, @NonNull String permissions[], @RequestCode int requestCode, Interfase interfase) {

        this.context = context;
        this.parameters = parameters;
        this.permissions = permissions;
        this.requestCode = requestCode;
        this.interfase = interfase;
    }


    // ----- Interface ----- //

    public interface Interfase {

        void PermissionsResult(int result);
    }
}
