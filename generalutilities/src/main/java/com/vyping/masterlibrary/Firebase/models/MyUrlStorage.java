package com.vyping.masterlibrary.Firebase.models;

import com.vyping.masterlibrary.Common.MyFile;
import com.vyping.masterlibrary.Common.MyString;

public class MyUrlStorage {

    private final String url;


    /*----- Setup -----*/

    public MyUrlStorage(String bucket) {

        url = "https://firebasestorage.googleapis.com/v0/b/" + bucket;
    }

    /*----- Methods -----*/

    public File file(String fileName) {

        String modifiedFileName = new MyString().changeToFileName(fileName);

        return new File(url + "/o/" + modifiedFileName);
    }

    public File file(String fileName, @MyFile.Type String type) {

        String modifiedFileName = new MyString().changeToFileName(fileName);

        return new File(url + "/o/" + modifiedFileName + type);
    }

    public static class File {

        String file;

        public File(String file) {

            this.file = file;
        }

        public String token(String token) {

            return file + "?alt=media&token=" + token;
        }
    }
}
