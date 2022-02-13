 package com.vyping.masterlibrary.dialogs;

 import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL;

 import android.Manifest;
 import android.app.Activity;
 import android.app.Dialog;
 import android.content.Context;
 import android.content.pm.PackageManager;
 import android.os.Handler;
 import android.os.Looper;
 import android.util.SparseArray;
 import android.view.ContextThemeWrapper;
 import android.view.SurfaceHolder;
 import android.view.SurfaceView;
 import android.widget.LinearLayout;

 import androidx.annotation.NonNull;
 import androidx.core.app.ActivityCompat;

 import com.airbnb.paris.Paris;
 import com.google.android.gms.vision.CameraSource;
 import com.google.android.gms.vision.Detector;
 import com.google.android.gms.vision.barcode.Barcode;
 import com.google.android.gms.vision.barcode.BarcodeDetector;
 import com.vyping.masterlibrary.Common.Definitions;
 import com.vyping.masterlibrary.R;

 import java.io.IOException;

 public abstract class DialogQrScan extends CreateDialog {

     private Context context;

     private CameraSource cameraSource;
     private SurfaceView cameraView;

     private String code, prevCode;


     /*----- SetUp -----*/

     public DialogQrScan(final Context context, int parameters) {

         super(context, parameters);

         setParameters(context);
         setDialogViews();
         setModeButtons(BUTTONS_CANCEL);
         setDialogListener(new CreateDialog.DialogInterface() {

             @Override
             public void NegativeClick() {

             }

             @Override
             public void RefreshClick() {}

             @Override
             public void PositiveClick() {}
         });
     }

     private void setParameters(@NonNull Context context) {

         this.context = context;
         code = "";
         prevCode = "";

         BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(context).setBarcodeFormats(Barcode.ALL_FORMATS).build();
         cameraSource = new CameraSource.Builder(context, barcodeDetector).setRequestedPreviewSize(1600, 1024).setAutoFocusEnabled(true).build();
         barcodeDetector.setProcessor(CodeDetector);
     }

     private void setDialogViews() {

         int style = R.style.DialogQrScan;
         int attr = R.attr.dialogQrScan;

         ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);
         cameraView = new SurfaceView(wrapper, null, attr, style);
         Paris.style(cameraView).apply(style);
         cameraView.getHolder().addCallback(SurfaceHolder);
         addCustomView(cameraView);
     }


     /*----- Listeners -----*/

     private final SurfaceHolder.Callback SurfaceHolder = new SurfaceHolder.Callback() {

         @Override
         public void surfaceCreated(SurfaceHolder holder) {

             if (ActivityCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {

                 try {

                     cameraSource.start(cameraView.getHolder());

                 } catch (IOException ignored) { }

             } else {

                 ActivityCompat.shouldShowRequestPermissionRationale((Activity) context, Manifest.permission.CAMERA);
             }
         }

         @Override
         public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) { }

         @Override
         public void surfaceDestroyed(SurfaceHolder holder) {

             cameraSource.stop();
         }
     };

     private final Detector.Processor CodeDetector = new Detector.Processor<Barcode>(){

         @Override
         public void release() { }

         @Override
         public void receiveDetections(@NonNull com.google.android.gms.vision.Detector.Detections<Barcode> detections) {

             final SparseArray<Barcode> qrCodes = detections.getDetectedItems();

             if (qrCodes.size() > 0) {

                code = qrCodes.valueAt(0).displayValue;

                 if (!code.equals(prevCode)) {

                     prevCode = code;

                     new Handler(Looper.getMainLooper()).post(new Runnable() {

                         @Override
                         public void run() {

                             ScanCode(code);
                         }

                         private void DummyVoid() { }
                     });

                 }
             }
         }
     };


     /*----- Return -----*/

     protected abstract void ScanCode(String Code);
 }
