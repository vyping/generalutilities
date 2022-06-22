package com.vyping.masterlibrary.Firebase;

import android.net.Uri;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;

public class Storage {

    private static StorageReference storageReference;


    // ----- Setup ----- //

    public Storage(@NonNull String instance) {

        String Instance = "gs://" + instance;

        storageReference = FirebaseStorage.getInstance(Instance).getReference();
    }

    public Storage(@NonNull String instance, String child1) {

        String Instance = "gs://" + instance;

        storageReference = FirebaseStorage.getInstance(Instance).getReference().child(child1);
    }

    public Storage(@NonNull String instance, String child1, String child2) {

        String Instance = "gs://" + instance;

        storageReference = FirebaseStorage.getInstance(Instance).getReference().child(child1).child(child2);
    }

    public Storage(@NonNull String instance, String child1, String child2, String child3) {

        String Instance = "gs://" + instance;

        storageReference = FirebaseStorage.getInstance(Instance).getReference().child(child1).child(child2).child(child3);
    }

    public Storage(@NonNull String instance, String child1, String child2, String child3, String child4) {

        String Instance = "gs://" + instance;

        storageReference = FirebaseStorage.getInstance(Instance).getReference().child(child1).child(child2).child(child3).child(child4);
    }

    public Storage(@NonNull String instance, String child1, String child2, String child3, String child4, String child5) {

        String Instance = "gs://" + instance;

        storageReference = FirebaseStorage.getInstance(Instance).getReference().child(child1).child(child2).child(child3).child(child4).child(child5);
    }


    // ----- ModelMethods ----- //

    /**
     * Info:
     * Carga en la actual referencia de Firebase Storage el archivo seleccionado con la ruta especificada.
     *
     * @param storagePath [String] Ruta al archivo en el Storage (Users/Pedro.png).
     * @param file        [File] Archivo a cargar desde el dispositivo (new File).
     */
    public void uploadFile(String storagePath, File file) {

        Uri uriFile = Uri.fromFile(file);

        storageReference.child(storagePath).putFile(uriFile);
    }

    public void uploadFile(String storagePath, File file, SuccessListener listener) {

        Uri uriFile = Uri.fromFile(file);

        storageReference.child(storagePath).putFile(uriFile).addOnSuccessListener(new OnSuccessListener<>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                listener.Success();
            }

            private void DummyVoid() {
            }

        });
    }

    public void uploadFile(String storagePath, File file, CompleteListener listener) {

        Uri uriFile = Uri.fromFile(file);

        storageReference.child(storagePath).putFile(uriFile).addOnSuccessListener(new OnSuccessListener<>() {

            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                listener.Success();
            }

            private void DummyVoid() {
            }

        }).addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception exception) {

                listener.Failure();
            }

            private void DummyVoid() {
            }
        });
    }

    /**
     * Info:
     * Descarga de la actual referencia de Firebase Storage el archivo indicado desde la ruta especificada.
     *
     * @param storagePath [String] Ruta al archivo en el Storage (Users/Pedro.png).
     * @param file        [File] Archivo a descargar en el dispositivo (new File).
     */
    public void downloadFile(String storagePath, File file) {

        storageReference.child(storagePath).getFile(file);
    }

    public void downloadFile(String storagePath, File file, SuccessListener listener) {

        storageReference.child(storagePath).getFile(file).addOnSuccessListener(new OnSuccessListener<>() {

            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                listener.Success();
            }

            private void DummyVoid() {
            }
        });
    }

    public void downloadFile(String storagePath, File file, CompleteListener listener) {

        storageReference.child(storagePath).getFile(file).addOnSuccessListener(new OnSuccessListener<>() {

            @Override
            public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {

                listener.Success();
            }

            private void DummyVoid() {
            }

        }).addOnFailureListener(new OnFailureListener() {

            @Override
            public void onFailure(@NonNull Exception exception) {

                listener.Failure();
            }

            private void DummyVoid() {
            }
        });
    }


    // ----- Interface ----- //

    public interface SuccessListener {

        void Success();
    }

    public interface CompleteListener {

        void Success();

        void Failure();
    }
}
