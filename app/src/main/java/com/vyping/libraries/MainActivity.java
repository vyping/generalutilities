package com.vyping.libraries;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.vyping.masterlibrary.dialogs.DialogPdfView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int aatrb = R.array.Dialog_Vehicle_Exit;

        new DialogPdfView(this, aatrb, "https://firebasestorage.googleapis.com/v0/b/vyp-properties-releases/o/1648483363899.pdf?alt=media&token=2aa870e9-1c62-431d-b097-80bb6cb471da") {

            @Override
            protected boolean PositiveButton() {

                return false;
            }
        };
    }
}