package com.vyping.masterlibrary.Comunication;

import static com.vyping.masterlibrary.Common.MyFile.TYPE_PNG;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.vyping.masterlibrary.Common.MyNumbers;
import com.vyping.masterlibrary.Common.MyString;
import com.vyping.masterlibrary.Images.MyBitMap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Objects;

public class UploadImage {

    private final Context context;
    private final ImageView imageView;
    private String bucket, nameFile;
    private Intent data;
    private Interfase interfase;


    // ----- SetUp ----- //

    public UploadImage(@NonNull ImageView imageView, String bucket, String nameFile, Intent data) {

        this.context = imageView.getContext();
        this.imageView = imageView;
        this.bucket = bucket;
        this.nameFile = new MyString().changeToFileName(nameFile) + TYPE_PNG;
        this.data = data;
    }


    // ----- Methods ----- //

    public void mountFile(Interfase interfase) {

        this.interfase = interfase;

        if (ContextCompat.checkSelfPermission(imageView.getContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            try {

                final Uri imageUri = data.getData();

                Glide.with(context).load(imageUri).into(imageView);

                InputStream stream = context.getContentResolver().openInputStream(imageUri);
                Bitmap prevBitmap = BitmapFactory.decodeStream(stream);
                Bitmap bitmap = new MyBitMap().getResizedBitmap(prevBitmap, 700, 400);
                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/Products");

                if (!path.exists()) {

                    path.mkdirs();
                }

                File file = new File(path, nameFile);

                try {

                    file.createNewFile();
                    FileOutputStream ostream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
                    ostream.close();

                    uploadStoragePhoto(file, nameFile);

                } catch (Exception e) {

                    e.printStackTrace();

                    otherMethodToMountFile(data);
                }

            } catch (Exception e) {

                e.printStackTrace();

                interfase.Error(e.getMessage());
            }
        }
    }

    private void otherMethodToMountFile(Intent data) {

        if (ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            try {

                final Uri imageUri = data.getData();

                imageView.setImageURI(imageUri);
                imageView.setDrawingCacheEnabled(true);

                Bitmap prevBitmap = imageView.getDrawingCache();
                Bitmap bitmap = new MyBitMap().getResizedBitmap(prevBitmap, 700, 400);
                imageView.destroyDrawingCache();

                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES + "/Products");

                if (!path.exists()) {

                    path.mkdirs();
                }

                File file = new File(path, nameFile);

                try {

                    file.createNewFile();
                    FileOutputStream ostream = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, ostream);
                    ostream.close();

                    uploadStoragePhoto(file, nameFile);

                } catch (Exception e) {

                    e.printStackTrace();

                    interfase.Error(e.getMessage());
                }

            } catch (Exception e) {

                e.printStackTrace();

                interfase.Error(e.getMessage());
            }
        }
    }

    public void uploadStoragePhoto(File ImagePath, String nameFile) {

        if (ContextCompat.checkSelfPermission(Objects.requireNonNull(context), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {

            StorageReference storageReference;
            storageReference = FirebaseStorage.getInstance("gs://" + bucket).getReference().child(nameFile);

            Uri file = Uri.fromFile(new File(String.valueOf(ImagePath)));

            storageReference.putFile(file).addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {

                @Override
                public void onProgress(@NonNull UploadTask.TaskSnapshot snapshot) {

                    long bytes = snapshot.getTotalByteCount();
                    long Transfer = snapshot.getBytesTransferred();

                    int progress = new MyNumbers().longToInteger(Transfer*100/bytes);

                    interfase.Progress(progress);
                }

            }).addOnSuccessListener(new OnSuccessListener<>() {

                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<>() {

                        @Override
                        public void onSuccess(Uri uri) {

                            interfase.Success(uri, uri.toString().split("alt=media&token=")[1]);
                        }

                        private void DummyVoid() {}
                    });
                }

                private void DummyVoid() {}

            }).addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception exception) {

                    interfase.Error(exception.getMessage());
                }
            });
        }
    }


    //----- Interface - Section-----//

    public interface Interfase {

        void Success(Uri uri, String token);
        void Progress(int progress);
        void Error(String error);
    }
}
