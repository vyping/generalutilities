package com.vyping.libraries;

import static com.vyping.masterlibrary.Common.Definitions.DATEPICKER_CALENDAR;
import static com.vyping.masterlibrary.Common.Definitions.TIMEPICKER_CLOCK;
import static java.lang.Boolean.TRUE;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.vyping.masterlibrary.ActionBar.ActionBarCustom;
import com.vyping.masterlibrary.Dialogs.DialogButtons;
import com.vyping.masterlibrary.Dialogs.DialogCheck;
import com.vyping.masterlibrary.Dialogs.DialogInputText;
import com.vyping.masterlibrary.Dialogs.DialogListView;
import com.vyping.masterlibrary.Dialogs.DialogPattern;
import com.vyping.masterlibrary.Dialogs.DialogPicker;
import com.vyping.masterlibrary.Dialogs.DialogPickerDate;
import com.vyping.masterlibrary.Dialogs.DialogPickerTimer;
import com.vyping.masterlibrary.Dialogs.DialogQrScan;
import com.vyping.masterlibrary.Dialogs.DialogQuestion;
import com.vyping.masterlibrary.Dialogs.DialogShowInfo;
import com.vyping.masterlibrary.Dialogs.DialogSigning;
import com.vyping.masterlibrary.Dialogs.DialogSlide;
import com.vyping.masterlibrary.Dialogs.DialogVideoPlayer;
import com.vyping.masterlibrary.Dialogs.DialogYouTube;
import com.vyping.masterlibrary.Firebase.Realtime.DatabaseRealtime;
import com.vyping.masterlibrary.Firebase.Realtime.ListChanges;
import com.vyping.masterlibrary.Firebase.Storage;
import com.vyping.masterlibrary.Json.JsonFile;
import com.vyping.masterlibrary.PopUps.PopUpBubbles;
import com.vyping.masterlibrary.ToolBars.ToolBars;

import org.json.JSONArray;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        context = MainActivity.this;
    }
}

