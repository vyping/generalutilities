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

import com.vyping.masterlibrary.BR;
import com.vyping.masterlibrary.R;
import com.vyping.masterlibrary.dialogs.DialogInformation;
import com.vyping.masterlibrary.dialogs.MyBasicDialog;
import com.vyping.masterlibrary.dialogs.DialogParams;

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
    public static final String PERMISSION_EXTERNALSTORAGE_READ = Manifest.permission.READ_EXTERNAL_STORAGE; //  <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    public static final String PERMISSION_EXTERNALSTORAGE_WRITE = Manifest.permission.WRITE_EXTERNAL_STORAGE; //   <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"/>
    public static final String PERMISSION_EXTERNALSTORAGE_MANAGE = Manifest.permission.MANAGE_EXTERNAL_STORAGE; //    <uses-permission android:name="android.permission.MANAGE_EXTERNAL_STORAGE"/>
    public static final String PERMISSION_BLUETOOTH = Manifest.permission.BLUETOOTH; //   <uses-permission android:name="android.permission.BLUETOOTH" />
    public static final String PERMISSION_BLUETOOTH_ADMIN = Manifest.permission.BLUETOOTH_ADMIN;   // <uses-permission android:name="android.permission.BLUETOOTH_ADMIN" />
    //  public static final String PERMISSION_BLUETOOTH_CONNECT = Manifest.permission.BLUETOOTH_CONNECT;   // <uses-permission android:name="android.permission.BLUETOOTH_CONNECT" />

    public static final int PERMISSIONS_GRANTED = PackageManager.PERMISSION_GRANTED;  //

    public static final int PERMISSION_CODE_PHONE_STATE = 11000;
    public static final int PERMISSION_CODE_PHONE_NUMBERS = 12000;
    public static final int PERMISSION_CODE_PHONE_CALL = 13000;
    public static final int PERMISSION_CODE_SMS_READ = 14000;
    public static final int PERMISSION_CODE_SMS_SEND = 15000;
    public static final int PERMISSION_CODE_CAMERA = 16000;
    public static final int PERMISSION_CODE_LOCATION = 17000;
    public static final int PERMISSION_CODE_RECOGNITION = 18000;
    public static final int PERMISSION_CODE_ACCOUNTS_GET = 19000;
    public static final int PERMISSION_CODE_CONTACTS_READ = 200000;
    public static final int PERMISSION_CODE_CONTACTS_WRITE = 210000;
    public static final int PERMISSION_CODE_EXTERNALSTORAGE_READ = 220000;
    public static final int PERMISSION_CODE_EXTERNALSTORAGE_WRITE = 230000;
    public static final int PERMISSION_CODE_EXTERNALSTORAGE_MANAGE = 240000;
    public static final int PERMISSION_CODE_BLUETOOTH = 250000;

    @Retention(RetentionPolicy.SOURCE)
    @IntDef({PERMISSION_CODE_PHONE_STATE, PERMISSION_CODE_PHONE_NUMBERS, PERMISSION_CODE_PHONE_CALL, PERMISSION_CODE_SMS_READ, PERMISSION_CODE_SMS_SEND, PERMISSION_CODE_CAMERA, PERMISSION_CODE_LOCATION, PERMISSION_CODE_RECOGNITION, PERMISSION_CODE_ACCOUNTS_GET, PERMISSION_CODE_CONTACTS_READ, PERMISSION_CODE_CONTACTS_WRITE, PERMISSION_CODE_EXTERNALSTORAGE_READ, PERMISSION_CODE_EXTERNALSTORAGE_WRITE, PERMISSION_CODE_BLUETOOTH})
    public @interface RequestCode {}

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

        boolean granted = FALSE;

        for (String permission : permissions) {

            if (ContextCompat.checkSelfPermission(context, permission) == PERMISSIONS_GRANTED) {

                granted = TRUE;
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

        if (grantResults.length > 0 && grantResults[0] == PERMISSIONS_GRANTED) {

            interfase.PermissionsResult(requestCode);
        }
    }


    // ----- Dialogs ----- //

    private void ShowDialogPermissions() {

        MyBasicDialog<DialogParams> myHandlerDialog = new MyBasicDialog<>(context, R.layout.dialog_information);

        DialogInformation dialogInformation = new DialogInformation(context, parameters, new DialogInformation.Interfase() {

            @Override
            public void Confirm() {

                ActivityCompat.requestPermissions(((Activity) context), permissions, requestCode);
            }

            private void DummyVoid() {}
        });

        myHandlerDialog.binding(BR.informationBinding).variable(BR.informationParams, dialogInformation).show();
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
