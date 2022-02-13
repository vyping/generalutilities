package com.vyping.libraries.events;

import static com.vyping.libraries.general.Constants.INSTANCE_EVENTS;
import static java.lang.Boolean.TRUE;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.vyping.libraries.R;
import com.vyping.libraries.general.Models.Event;
import com.vyping.libraries.Incomes.IncomesActivity;
import com.vyping.libraries.vehicles.VehiclesActivity;
import com.vyping.masterlibrary.ActionBar.MyActionBar;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.Firebase.Realtime.DatabaseRealtime;
import com.vyping.masterlibrary.Firebase.RemoteConfig;
import com.vyping.masterlibrary.PopUps.MyPopUpBubbles;
import com.vyping.masterlibrary.ToolBars.MyToolBars;
import com.vyping.masterlibrary.Views.RecyclerViews;

import java.util.Calendar;

public class EventsActivity extends AppCompatActivity {

    private Context context;
    private DatabaseRealtime databaseEvents;
    private EventsAdapter adapterEvents;

    private MyToolBars toolBars;


    /*----- SetUp -----*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.events_activity);

        setStartProcess();
        setInherentViews();
        setActivityViews();
    }

    private void setStartProcess() {

        context = EventsActivity.this;

        new RemoteConfig(context, R.xml.remote_config, new RemoteConfig.SuccessListener() {

            @Override
            public void Success(FirebaseRemoteConfig firebaseRemoteConfig) {


            }

            private void DummyVoid() { }
        });

        databaseEvents = new DatabaseRealtime(INSTANCE_EVENTS) {};

        adapterEvents = new EventsAdapter(context);
    }

    private void setInherentViews() {

        MyActionBar actionBarCustom = new MyActionBar(context, R.drawable.icon_history, "Historial de Eventos") {};
        actionBarCustom.setButtonOption(com.vyping.masterlibrary.R.drawable.icon_car, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new MyActivity().Start(context, VehiclesActivity.class, TRUE);
            }
        });
        actionBarCustom.setButtonOption(com.vyping.masterlibrary.R.drawable.icon_money, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new MyActivity().Start(context, IncomesActivity.class, TRUE);
            }
        });
        actionBarCustom.showButtonTools();

        toolBars = new MyToolBars(context, R.id.hvvkjhgv) {};
        toolBars.setSearchToolBar(new MyToolBars.SearchInterface() {

            @Override
            public void SelectedSearch(String search) {

                if (adapterEvents != null) {

                    adapterEvents.getFilter().filter(search);
                }

                if (adapterEvents != null) {

                    adapterEvents.getFilter().filter(search);
                }
            }
        });
        toolBars.setDateToolBar(new MyToolBars.DateInterface() {

            @Override
            public void SelectedDate(Calendar Calendar, long milis, String date, String day, String month, String year) {

                String datePath = "20" + year + "/" + month + "/" + day;

                firebaseGetEvents(datePath);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setActivityViews() {

        RecyclerViews recyclerViews = new RecyclerViews(context, R.id.sdfsd, adapterEvents);
        recyclerViews.selectListener(new RecyclerViews.selectInterface() {

            @Override
            public void SelectedItem(View selectedView, int position, RecyclerView.ViewHolder viewHolder) {

                Event event = adapterEvents.getEvent(position);

                MyPopUpBubbles popUpBubbles = new MyPopUpBubbles(context, selectedView);
                popUpBubbles.setButtonMid("\uf129", new MyPopUpBubbles.ClickInterface() {

                    @Override
                    public void ButtonClick() {

                        new EventsDialogInfo(context, R.array.Dialogs_CreateVehicle, event) {};
                    }

                    private void DummyVoid() {}
                });
            }
        });
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }    // Receive from onStop()

    @Override
    protected void onStart() {

        super.onStart();

        toolBars.setDate();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }     // Receive from onPause()


    /*----- Firebase -----*/

    private void firebaseGetEvents(String datePath) {

        adapterEvents.restartAdapter();

        databaseEvents.getValueChangesOrganized(datePath, "Hour", new DatabaseRealtime.ValueListener() {

            @Override
            public void ChildAdded(@NonNull DataSnapshot snapChild) {

                Event event = new Event(snapChild);
                adapterEvents.addEventByChange(event);
            }

            @Override
            public void ChildChanged(@NonNull DataSnapshot snapChild) {

                Event event = new Event(snapChild);
                adapterEvents.modifyEvent(event);
            }

            @Override
            public void ChildRemoved(@NonNull DataSnapshot snapChild) {

                Event event = new Event(snapChild);
                adapterEvents.removeEvent(event);
            }
        });
    }


    /*----- Listeners -----*/



    /*----- Dialogs -----*/



    /*----- Tools -----*/



    /*----- Inherents -----*/

    @Override
    public void onBackPressed() {

        new MyActivity().Start(context, VehiclesActivity.class, TRUE);
    }

    @Override
    public void onLowMemory() {

        super.onLowMemory();
    }

    @Override
    protected void onPause() {

        super.onPause();
    }  // Return to onResume()

    @Override
    protected void onStop() {

        super.onStop();

        databaseEvents.removeValueListener();
    }  // Return to onRestart()

    @Override
    protected void onDestroy() {

        super.onDestroy();

        databaseEvents.removeValueListener();
    }
}