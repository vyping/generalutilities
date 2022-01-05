 package com.vyping.masterlibrary.Dialogs;

 import static java.lang.Boolean.TRUE;

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

 public abstract class DialogQrScan {

     private Context context;
     private CreateDialog createDialog;
     private Dialog dialog;

     private CameraSource cameraSource;
     private SurfaceView cameraView;
     private String code = "";
     private String prevCode = "";


     /*----- SetUp -----*/

     public DialogQrScan(final Context context, int parameters) {

         setParameters(context);

         createDialog = new CreateDialog(context, parameters, TRUE) {

             @Override
             protected void SetDialog(Dialog Dialog) {

                 dialog = Dialog;

                 setViewsOnLayout(dialog);

                 cameraView.getHolder().addCallback(SurfaceHolder);

                 dialog.show();
             }

             @Override
             protected void RefreshClick() {

             }

             @Override
             protected void PositiveClick() { }
         };

         setOptionButtons();
     }

     private void setOptionButtons() {

        createDialog.setModeButtons(Definitions.BUTTONS_SINGLE);
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

                     dialog.dismiss();

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


     /*----- Tools -----*/

     private void setParameters(@NonNull Context context) {

         this.context = context;

         BarcodeDetector barcodeDetector = new BarcodeDetector.Builder(context).setBarcodeFormats(Barcode.ALL_FORMATS).build();
         cameraSource = new CameraSource.Builder(context, barcodeDetector).setRequestedPreviewSize(1600, 1024).setAutoFocusEnabled(true).build();
         barcodeDetector.setProcessor(CodeDetector);
     }

     private void setViewsOnLayout(@NonNull Dialog dialog) {

         int style = R.style.DialogQrScan;
         int attr = R.attr.dialogQrScan;

         LinearLayout layout = dialog.findViewById(R.id.Ll_Custom_MainContainer);
         layout.setOrientation(LinearLayout.VERTICAL);
         ContextThemeWrapper wrapper = new ContextThemeWrapper(context, style);

         cameraView = new SurfaceView(wrapper, null, attr, style);

         Paris.style(cameraView).apply(style);

         layout.addView(cameraView);
     }


     /*----- Return -----*/

     protected abstract void ScanCode(String Code);
 }