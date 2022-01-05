package com.vyping.masterlibrary.Common;

import android.content.Context;

import androidx.annotation.NonNull;

import java.io.File;

public class Files {

    public String translateToFileName(String Text) {

        if (Text != null) {

            return Text.trim().toLowerCase()
                    .replace(".", "")
                    .replace("-", "_")
                    .replace(" ", "_")
                    .replace("Ñ","N")
                    .replace("Á","A")
                    .replace("É","E")
                    .replace("Í","I")
                    .replace("Ó","O")
                    .replace("Ú","U")
                    .replace("ñ","n")
                    .replace("á","a")
                    .replace("é","e")
                    .replace("í","i")
                    .replace("ó","o")
                    .replace("ú","u");

        } else {

            return "";
        }
    }

    public void eraseFilesOnDirectory(@NonNull File directory, File file) {

        File[] files = directory.listFiles();

        if (files != null) {

            for (File previousFile : files) {

                if (!previousFile.getName().equals(file.getName())) {

                    boolean delete = previousFile.delete();
                }
            }
        }
    }

    public File CreateFileInExternalDirectory(@NonNull Context context, String folder, String file) {

        File directory = context.getExternalFilesDir(folder);

        if (!directory.exists()) {

            boolean create = directory.mkdirs();
        }

        return new File(directory, file);
    }
}
