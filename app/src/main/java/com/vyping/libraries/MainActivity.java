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
    private ToolBars toolBars;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        context = MainActivity.this;

        new ActionBarCustom(context, R.drawable.icon_image, "Prueba") {

            {
                setTouchListener(new TouchInterface() {

                    @Override
                    public void OnFling() {

                        toolBars.selectBar();
                    }

                    @Override
                    public void DummyVoid() { }
                });
            }
        };
        toolBars = new ToolBars(context, R.id.asdasdfda) {

            {
                setDateToolBar(new DateInterface() {

                    @Override
                    public void SelectedDate(Calendar Calendar, long milis, String date, String day, String month, String year) { }

                    @Override
                    public void DummyVoid() { }
                });
                setSearchToolBar(new SearchInterface() {

                    @Override
                    public void SelectedSearch(String Search) { }

                    @Override
                    public void DummyVoid() { }
                });
            }
        };

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                PopUpBubbles(view);
            }

            private void DUmmyVoid() {}
        });

        Json();
    }


    private void Json() {

        JsonFile jsonFile = new JsonFile(context, "hospitals_cundinamarca.json", new JsonFile.Interface() {

            @Override
            public void StartListen(boolean exist, long childCount) {

                Log.i("Desarrollo", "exist: " + exist + ", childCount: " + childCount);
            }

            @Override
            public void ValueListen(String key, JSONArray valueArray, int count) {

                Log.i("Desarrollo", "key: " + key + ", valueArray: " + valueArray + ", count: " + count);
            }

            @Override
            public void FinishListen() {

                Log.i("Desarrollo", "FinishListen");
            }
        });
    }

    private void firebase() {

        DatabaseRealtime databaseRealtime = new DatabaseRealtime("vyp-accescontrol-default-rtdb");

        databaseRealtime.getListChanges(new ListChanges("Cerradura de Prueba") {

            @Override
            protected void StartListen(boolean exist, long childCount) {

                Log.i("Desarrollo", "exist: " + exist + ", childCount: " + childCount);
            }

            @Override
            protected void ValueListen(String key, @NonNull DataSnapshot dataSnapshot, int count) {

                Log.i("Desarrollo", "key: " + key);
            }

            @Override
            protected void FinishListen() {

                Log.i("Desarrollo", "finish");
            }
        });

        databaseRealtime.setValue("", 0, new DatabaseRealtime.SuccessListener() {

            @Override
            public void Success() { }

            private void DummyVoid() {}
        });


        HashMap<String, Object> map = new HashMap<>();

        databaseRealtime.updateChild("", map, new DatabaseRealtime.CompleteListener() {

            @Override
            public void Success() { }

            @Override
            public void Failure() { }
        });
    }

    private void storage() {

        Storage storage = new Storage("Test");
        storage.uploadFile("", new File(""));
    }

    private void PopUpBubbles(View view) {

        new PopUpBubbles(context, view) {

            {
                setButtonStart("1", new ClickInterface() {

                @Override
                public void ButtonClick() {

                }

                @Override
                public void DummyVoid() { }
            });
                setButtonMid("2", new ClickInterface() {

                    @Override
                    public void ButtonClick() {

                    }

                    @Override
                    public void DummyVoid() { }
                });
                setButtonMid("3", new ClickInterface() {

                    @Override
                    public void ButtonClick() {

                    }

                    @Override
                    public void DummyVoid() { }
                });
                setButtonEnd("4", new ClickInterface() {

                    @Override
                    public void ButtonClick() {

                    }

                    @Override
                    public void DummyVoid() { }
                });
            }
        };
    }

    private void dialogButtons() {

        DialogButtons dialog = new DialogButtons(context, R.array.Dialog_Params) {

            @Override
            protected void ButtonConfirm() {

                Log.i("Desarrollo", "ButtonConfirm()");
            }
        };

        dialog.setButton("\uf05a", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Desarrollo", "a");
            }

            private void DummyVoid() {}
        });
        dialog.setButton("\uf05a", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Desarrollo", "b");
            }

            private void DummyVoid() {}
        });
        dialog.setButton("\uf05a", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Desarrollo", "c");
            }

            private void DummyVoid() {}
        });
        dialog.setButton("\uf05a", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Desarrollo", "d");
            }

            private void DummyVoid() {}
        });
        dialog.setButton("\uf05a", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.i("Desarrollo", "e");
            }

            private void DummyVoid() {}
        });
    }

    private void dialogCheck() {

        new DialogCheck(context, R.array.Dialog_Params, TRUE) {

            @Override
            protected void ChangeCheckedState(boolean IsChecked) {

                Log.i("Desarrollo", "ChangeCheckedState: " + IsChecked);
            }

            @Override
            protected void ButtonConfirm(boolean IsChecked) {

                Log.i("Desarrollo", "ButtonConfirm: " + IsChecked);
            }
        };
    }

    private void dialogInputText() {

        new DialogInputText(context, R.array.Dialog_Params, "aaa") {

            @Override
            protected void ButtonConfirm(String InputText) {

                Log.i("Desarrollo", "InputText: " + InputText);
            }
        };
    }

    private void dialogListView() {

        ArrayList<String> array = new ArrayList<>();
        array.add("Perro");
        array.add("Gato");
        array.add("Vaca");
        array.add("Pato");
        array.add("Cerdo");
        array.add("Gallo");
        array.add("Mula");
        array.add("Caballo");
        array.add("Toro");
        array.add("Gallina");
        array.add("Cabra");
        array.add("Oveja");
        array.add("Pollo");
        array.add("Chivo");

        new DialogListView(context, R.array.Dialog_Params, array,"Pollo") {

            @Override
            protected void ItemSelected(String Selection) {

                Log.i("Desarrollo", "Selection: " + Selection);
            }
        };
    }

    private void dialogPattern() {

        new DialogPattern(context, R.array.Dialog_Params) {

            @Override
            protected void patternResult(String Pattern) {

                Log.i("Desarrollo", "Pattern: " + Pattern);
            }
        };
    }

    private void dialogPicker() {

        int array = R.array.Dialog_Params;

        new DialogPicker(context, R.array.Dialog_Params, array, 2) {

            @Override
            protected void ChangePickedItem(int Selected, String Selection) {

                Log.i("Desarrollo", "ChangePickedItem - Selected: " + Selected + ", Selection: " + Selection);
            }

            @Override
            protected void ButtonConfirm(int Selected, String Selection) {

                Log.i("Desarrollo", "ButtonConfirm - Selected: " + Selected + ", Selection: " + Selection);
            }
        };
    }

    private void dialogPickerDate() {

        new DialogPickerDate(context, R.array.Dialog_Params, DATEPICKER_CALENDAR) {

            @Override
            protected void SetDate(Calendar calendar, long milis, String date, String day, String month, String year) {

                Log.i("Desarrollo", "milis: " + milis + ", date: " + date);
                Log.i("Desarrollo", "day: " + day + ", month: " + month + ", year: " + year);
            }
        };
    }

    private void dialogPickerTimer() {

        new DialogPickerTimer(context, R.array.Dialog_Params, TIMEPICKER_CLOCK, TRUE) {

            @Override
            protected void SetTime(Calendar calendar, long milis, String time, String hour, String minute) {

                Log.i("Desarrollo", "milis: " + milis + ", time: " + time);
                Log.i("Desarrollo", "hour: " + hour + ", minute: " + minute);
            }
        };
    }

    private void dialogQrScan() {

        new DialogQrScan(context, R.array.Dialog_Params) {

            @Override
            protected void ScanCode(String Code) {

                Log.i("Desarrollo", "Code: " + Code);
            }
        };
    }

    private void dialogShowInfo() {

        new DialogShowInfo(context, R.array.Dialog_Params, "Test") {

            @Override
            protected void ButtonConfirm(String InputText) {

                Log.i("Desarrollo", "InputText: " + InputText);
            }
        };
    }

    private void dialogQuestion() {

        new DialogQuestion(context, R.array.Dialog_Params, "Test") {

            @Override
            protected void ButtonConfirm(String InputText) {

                Log.i("Desarrollo", "InputText: " + InputText);
            }
        };
    }

    private void dialogSigning() {

        new DialogSigning(context, R.array.Dialog_Params) {

            @Override
            protected void Signed(Bitmap SigningBitmap) {

            }
        };
    }

    private void dialogSlide() {

        new DialogSlide(context, R.array.Dialog_Params) {

            @Override
            protected void SlideButton() { }
        };
    }

    private void dialogVideoPlayer() {

        new DialogVideoPlayer(context, R.array.Dialog_Params) {

            @Override
            protected void ButtonConfirm() {

            }
        };
    }

    private void dialogYouTube() {

        new DialogYouTube(context, R.array.Dialog_Params, "") {

            @Override
            protected void ButtonConfirm(String InputText) { }
        };
    }
}

