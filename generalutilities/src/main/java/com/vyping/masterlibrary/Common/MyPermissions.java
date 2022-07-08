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

    public static final String PERMISSION_PHONE_STATE = Manifest.permission.READ_PHONE_STATE; // <uses-permission android:name="android.permission.READ_PHONE_STATE" />
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static final String PERMISSION_PHONE_NUMBERS = Manifest.permission.READ_PHONE_NUMBERS; // <uses-permission android:name="android.permission.READ_PHONE_NUMBERS" />
    public static final String PERMISSION_PHONE_CALL = Manifest.permission.CALL_PHONE; // <uses-permission android:name="android.permission.CALL_PHONE" />
    public static final String PERMISSION_SMS_READ = Manifest.permission.READ_SMS; // <uses-permission android:name="android.permission.READ_SMS"/>
    public static final String PERMISSION_SMS_SEND = Manifest.permission.SEND_SMS; // <uses-permission android:name="android.permission.SEND_SMS"/>
    public static final String PERMISSION_CAMERA = Manifest.permission.CAMERA;  //     <uses-permission android:name="android.permission.CAMERA" />
    public static final String PERMISSION_LOCATION_FINE = Manifest.permission.ACCESS_FINE_LOCATION;  // <uses-permission android:name="android.permission.ACCESS_FINE_LOCATION" />
    public static final String PERMISSION_LOCATION_COARSE = Manifest.permission.ACCESS_COARSE_LOCATION; //  <uses-permission android:name="android.permission.ACCESS_COARSE_LOCATION" />
    public static final String PERMISSION_ACCOUNTS_GET = Manifest.permission.GET_ACCOUNTS;  // <uses-permission android:name="android.permission.GET_ACCOUNTS " />
    public static final String PERMISSION_CONTACTS_READ = Manifest.permission.READ_CONTACTS;  // <uses-permission android:name="android.permission.READ_CONTACTS " />
    public static final String PERMISSION_CONTACTS_WRITE = Manifest.permission.WRITE_CONTACTS; //  <uses-permission android:name="android.permission.WRITE_CONTACTS" />
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public static final String PERMISSION_RECOGNITION = Manifest.permission.ACTIVITY_RECOGNITION; //  <uses-permission android:name="android.permission.ACTIVITY_RECOGNITION"/>

    public static final int PERMISSIONS_GRANTED = PackageManager.PERMISSION_GRANTED;  //

    public static final int PERMISSION_CODE_PHONE_STATE = 10000;
    public static final int PERMISSION_CODE_PHONE_NUMBERS = 20000;
    public static final int PERMISSION_CODE_PHONE_CALL = 30000;
    public static final int PERMISSION_CODE_SMS_READ = 40000;
    public static final int PERMISSION_CODE_SMS_SEND = 50000;
    public static final int PERMISSION_CODE_CAMERA = 60000;
    public static final int PERMISSION_CODE_LOCATION = 70000;
    public static final int PERMISSION_CODE_RECOGNITION = 80000;
    public static final int PERMISSION_CODE_ACCOUNTS_GET = 90000;
    public static final int PERMISSION_CODE_CONTACTS_READ = 100000;
    public static final int PERMISSION_CODE_CONTACTS_WRITE = 110000;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({PERMISSION_CODE_PHONE_STATE, PERMISSION_CODE_PHONE_NUMBERS, PERMISSION_CODE_PHONE_CALL, PERMISSION_CODE_SMS_READ, PERMISSION_CODE_SMS_SEND, PERMISSION_CODE_CAMERA, PERMISSION_CODE_LOCATION, PERMISSION_CODE_RECOGNITION, PERMISSION_CODE_ACCOUNTS_GET, PERMISSION_CODE_CONTACTS_READ, PERMISSION_CODE_CONTACTS_WRITE })
    public @interface RequestCode { }

    private Context context;
    private Interfase interfase;

    private String permissions[];
    private int parameters, requestCode, minVersion;


    // ----- Setup ----- //

    public MyPermissions(Context context, int parameters, @NonNull String permissions[], @RequestCode int requestCode) {

        SetParameters(context, parameters, permissions, requestCode, null,0);
    }

    public MyPermissions(Context context, int parameters, @NonNull String permissions[], @RequestCode int requestCode, int minVersion) {

        SetParameters(context, parameters, permissions, requestCode, null, minVersion);
    }

    public MyPermissions(Context context, int parameters, @NonNull String permissions[], @RequestCode int requestCode, Interfase interfase) {

        SetParameters(context, parameters, permissions, requestCode, interfase, 0);

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

    public MyPermissions(Context context, int parameters, @NonNull String permissions[], @RequestCode int requestCode, Interfase interfase, int minVersion) {

        SetParameters(context, parameters, permissions, requestCode, interfase, minVersion);

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

        if (minVersion == 0) {

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

        } else {

            if (Build.VERSION.SDK_INT > minVersion) {

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

            } else {

                interfase.PermissionsResult(requestCode);
            }
        }
    }

    public void RequestPermissions() {

        this.interfase = interfase;

        boolean permissionsGranted = checkPermissionsGranted();
        boolean restricted = minVersion != 0;
        boolean afirmative = Build.VERSION.SDK_INT > minVersion;

        if (restricted) {

            if (afirmative) {

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

            } else {

                interfase.PermissionsResult(requestCode);
            }

        } else {

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

        if (Build.VERSION.SDK_INT > minVersion) {

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

        } else {

            interfase.PermissionsResult(requestCode);
        }
    }


    // ----- ModelMethods ----- //

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

            case PERMISSION_CODE_PHONE_STATE: {

                if (permissions[0].equals(PERMISSION_PHONE_STATE) && (grantResults.length > 0 && grantResults[0] == PERMISSIONS_GRANTED)) {

                    interfase.PermissionsResult(PERMISSION_CODE_PHONE_STATE);
                }

            }
            case PERMISSION_CODE_PHONE_NUMBERS: {

                if (permissions[0].equals(PERMISSION_PHONE_NUMBERS) && (grantResults.length > 0 && grantResults[0] == PERMISSIONS_GRANTED)) {

                    interfase.PermissionsResult(PERMISSION_CODE_PHONE_NUMBERS);
                }

            }
            case PERMISSION_CODE_PHONE_CALL: {

                if (permissions[0].equals(PERMISSION_PHONE_CALL) && (grantResults.length > 0 && grantResults[0] == PERMISSIONS_GRANTED)) {

                    interfase.PermissionsResult(PERMISSION_CODE_PHONE_CALL);
                }

            }
            case PERMISSION_CODE_SMS_READ: {

                if (permissions[0].equals(PERMISSION_SMS_READ) && (grantResults.length > 0 && grantResults[0] == PERMISSIONS_GRANTED)) {

                    interfase.PermissionsResult(PERMISSION_CODE_SMS_READ);
                }

            }
            case PERMISSION_CODE_SMS_SEND: {

                if (permissions[0].equals(PERMISSION_SMS_SEND) && (grantResults.length > 0 && grantResults[0] == PERMISSIONS_GRANTED)) {

                    interfase.PermissionsResult(PERMISSION_CODE_SMS_SEND);
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
            case PERMISSION_CODE_ACCOUNTS_GET: {

                if (permissions[0].equals(PERMISSION_ACCOUNTS_GET) && (grantResults.length > 0 && grantResults[0] == PERMISSIONS_GRANTED)) {

                    interfase.PermissionsResult(PERMISSION_CODE_ACCOUNTS_GET);
                }
            }
            case PERMISSION_CODE_CONTACTS_READ: {

                if (permissions[0].equals(PERMISSION_CONTACTS_READ) && grantResults[0] == PERMISSIONS_GRANTED && permissions[1].equals(PERMISSION_CONTACTS_READ) && grantResults[1] == PERMISSIONS_GRANTED) {

                    interfase.PermissionsResult(PERMISSION_CODE_CONTACTS_READ);
                }
            }
            case PERMISSION_CODE_CONTACTS_WRITE: {

                if (permissions[0].equals(PERMISSION_CONTACTS_WRITE) && (grantResults.length > 0 && grantResults[0] == PERMISSIONS_GRANTED)) {

                    interfase.PermissionsResult(PERMISSION_CODE_CONTACTS_WRITE);
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

                ActivityCompat.requestPermissions(((Activity) context), permissions, requestCode);
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


    private void SetParameters(Context context, int parameters, @NonNull String permissions[], @RequestCode int requestCode, Interfase interfase, int minVersion) {

        this.context = context;
        this.parameters = parameters;
        this.permissions = permissions;
        this.requestCode = requestCode;
        this.interfase = interfase;
        this.minVersion = minVersion;
    }


    // ----- Interface ----- //

    public interface Interfase {

        void PermissionsResult(int result);
    }
}
