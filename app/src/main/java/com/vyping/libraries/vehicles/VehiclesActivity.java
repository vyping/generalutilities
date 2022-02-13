package com.vyping.libraries.vehicles;

import static android.view.DragEvent.ACTION_DRAG_ENDED;
import static android.view.DragEvent.ACTION_DRAG_ENTERED;
import static android.view.DragEvent.ACTION_DRAG_STARTED;
import static android.view.DragEvent.ACTION_DROP;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static android.widget.Toast.LENGTH_LONG;
import static com.vyping.libraries.general.Constants.INSTANCE_EVENTS;
import static com.vyping.libraries.general.Constants.INSTANCE_TAGS;
import static com.vyping.libraries.general.Constants.INSTANCE_TARIFFS;
import static com.vyping.libraries.general.Constants.INSTANCE_VEHICLES;
import static com.vyping.libraries.general.Definitions.FROM_CREATE;
import static com.vyping.libraries.general.Definitions.FROM_INSIDE;
import static com.vyping.libraries.general.Definitions.FROM_NULL;
import static com.vyping.libraries.general.Definitions.FROM_OUTSIDE;
import static com.vyping.libraries.general.Definitions.INSIDE;
import static com.vyping.libraries.general.Definitions.OUTSIDE;
import static com.vyping.libraries.general.Definitions.PERIOD_MONTHLY;
import static com.vyping.libraries.general.Definitions.TO_ERASE;
import static com.vyping.libraries.general.Definitions.TO_INSIDE;
import static com.vyping.libraries.general.Definitions.TO_NULL;
import static com.vyping.libraries.general.Definitions.TO_OUTSIDE;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_CANCEL_REFRESH;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_REFRESH_ACCEPT;
import static com.vyping.masterlibrary.Common.Definitions.LAYOUT_HORIZONTAL;
import static com.vyping.masterlibrary.Common.Definitions.LAYOUT_VERTICAL;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.vyping.libraries.R;
import com.vyping.libraries.events.EventsActivity;
import com.vyping.libraries.general.Definitions;
import com.vyping.libraries.general.Managers.NfcCardManager;
import com.vyping.libraries.general.Managers.TariffsManager;
import com.vyping.libraries.general.Models.Event;
import com.vyping.libraries.general.Models.NfcCard;
import com.vyping.libraries.general.Models.Tariff;
import com.vyping.libraries.general.Models.Vehicle;
import com.vyping.libraries.Incomes.IncomesActivity;
import com.vyping.masterlibrary.ActionBar.MyActionBar;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.Common.DateTools;
import com.vyping.masterlibrary.Common.MyToast;
import com.vyping.masterlibrary.Firebase.Realtime.DatabaseRealtime;
import com.vyping.masterlibrary.Firebase.RemoteConfig;
import com.vyping.masterlibrary.GestureListeners.FlingVertical;
import com.vyping.masterlibrary.ToolBars.MyToolBars;
import com.vyping.masterlibrary.Views.RecyclerViews;
import com.vyping.masterlibrary.dialogs.DialogNfcScan;
import com.vyping.masterlibrary.dialogs.DialogSlide;
import com.vyping.masterlibrary.spinners.AdapterSingleIcon;

import java.util.ArrayList;
import java.util.HashMap;

public class VehiclesActivity extends AppCompatActivity {

    private Context context;

    private DatabaseRealtime databaseVehicles, databaseCards, databaseTariffs;
    private VehiclesOutsideAdapter adapterOutside;
    private VehiclesInsideAdapter adapterInside;
    public static NfcCardManager cardManager;
    public TariffsManager tariffManager;

    private PendingIntent pendingIntent;
    private DialogNfcScan dialogNfcScan;
    private RecyclerViews recyclerOutside,recyclerInside;

    private LinearLayout Ll_Outside;
    private ImageButton Btn_Trade, Btn_Erase;

    private static String Manager;
    private static boolean nfcRequired;
    public static @Definitions.Source int SOURCE;
    public static @Definitions.Destin int DESTINE;
    public static Vehicle VEHICLE;


    /*----- SetUp -----*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.vehicles_activity);

        setStartProcess();
        setInherentViews();
        setActivityViews();
    }

    private void setStartProcess() {

        context = VehiclesActivity.this;

        new RemoteConfig(context, R.xml.remote_config, new RemoteConfig.SuccessListener() {

            @Override
            public void Success(FirebaseRemoteConfig firebaseRemoteConfig) {

                nfcRequired = firebaseRemoteConfig.getBoolean("General_NFC_Required");
            }

            private void DummyVoid() { }
        });

        Manager = "Oscar Villabon";

        databaseVehicles = new DatabaseRealtime(INSTANCE_VEHICLES) {};
        databaseCards = new DatabaseRealtime(INSTANCE_TAGS) {};
        databaseTariffs = new DatabaseRealtime(INSTANCE_TARIFFS) {};

        adapterOutside = new VehiclesOutsideAdapter(context);
        adapterInside = new VehiclesInsideAdapter(context);

        pendingIntent = PendingIntent.getActivity(context,0, new Intent(context, context.getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP),0);

        clearVariables();
    }

    private void setInherentViews() {

        MyActionBar actionBarCustom = new MyActionBar(context, R.drawable.icon_car, "Gestion de Vehículos") {};
        actionBarCustom.setButtonOption(com.vyping.masterlibrary.R.drawable.icon_history, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new MyActivity().Start(context, EventsActivity.class, TRUE);
            }
        });
        actionBarCustom.setButtonOption(com.vyping.masterlibrary.R.drawable.icon_money, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new MyActivity().Start(context, IncomesActivity.class, TRUE);
            }
        });
        actionBarCustom.showButtonTools();

        MyToolBars toolBars = new MyToolBars(context, R.id.main_container) {};
        toolBars.setSearchToolBar(new MyToolBars.SearchInterface() {

            @Override
            public void SelectedSearch(String search) {

                if (adapterOutside != null) {

                    adapterOutside.getFilter().filter(search);
                }

                if (adapterInside != null) {

                    adapterInside.getFilter().filter(search);
                }
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setActivityViews() {

        GestureDetector gestureDetector = new GestureDetector(context, new FlingVertical(new FlingVertical.Interface() {

            @Override
            public void FlingSuccess() {

                Ll_Outside.setVisibility(GONE);
            }

            private void DummyVoid() { }
        }));

        recyclerInside = new RecyclerViews(context, R.id.rcv_inside_vehicles, adapterInside, 4, LAYOUT_VERTICAL);
        recyclerInside.selectListener(new RecyclerViews.selectInterface() {

            @Override
            public void SelectedItem(View selectedView, int position, RecyclerView.ViewHolder viewHolder) {

                Vehicle vehicle = adapterInside.getVehicle(position);

                new VehicleDialogInfo(context, R.array.Dialogs_CreateVehicle, vehicle) {};
            }

            private void DummyVoid() {}
        });
        recyclerInside.setDragListener(onDragListener);

        recyclerOutside = new RecyclerViews(context, R.id.rcv_outside_vehicles, adapterOutside, 3, LAYOUT_HORIZONTAL);
        recyclerOutside.selectListener(new RecyclerViews.selectInterface() {

            @Override
            public void SelectedItem(View selectedView, int position, RecyclerView.ViewHolder viewHolder) {

                Vehicle vehicle = adapterOutside.getVehicle(position);

                new VehicleDialogInfo(context, R.array.Dialogs_CreateVehicle, vehicle) {};
            }

            private void DummyVoid() {}
        });
        recyclerOutside.setTouchListener(new RecyclerViews.TouchInterface() {

            @Override
            public void InterceptTouchEvent(@NonNull MotionEvent motionEvent) {

                gestureDetector.onTouchEvent(motionEvent);
            }

            @Override
            public void TouchEvent(@NonNull MotionEvent motionEvent) {}
        });
        recyclerOutside.setDragListener(onDragListener);

        Ll_Outside = findViewById(R.id.Ll_Vhc_OutsideContainer);
        Ll_Outside.setVisibility(GONE);

        TextView Tv_OutsideLabel = findViewById(R.id.Tv_Vhc_OutsideLabel);
        Tv_OutsideLabel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int visible = Ll_Outside.getVisibility();

                if (visible == VISIBLE) {

                    Ll_Outside.setVisibility(GONE);

                } else {

                    Ll_Outside.setVisibility(VISIBLE);
                }
            }

            private void DummyVoid() {};
        });

        ImageButton Btn_Create = findViewById(R.id.button);
        Btn_Create.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                SOURCE = FROM_CREATE;

                dialogClickCreate();
            }

            private void DummyVoid() {};
        });
        Btn_Create.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {

                SOURCE = FROM_CREATE;

                ClipData data = ClipData.newPlainText("", "");
                View.DragShadowBuilder shadowBuilder = new View.DragShadowBuilder(view);

                view.setVisibility(View.INVISIBLE);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                    view.startDragAndDrop(data, shadowBuilder, view, 0);

                } else {

                    view.startDrag(data, shadowBuilder, view, 0);
                }

                return true;
            }

            private void DummyVoid() {};
        });

        Btn_Trade = findViewById(R.id.button3);
        Btn_Trade.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                int visible = Ll_Outside.getVisibility();

                if (visible == VISIBLE) {

                    Ll_Outside.setVisibility(GONE);

                } else {

                    Ll_Outside.setVisibility(VISIBLE);
                }
            }

            private void DummyVoid() {};
        });
        Btn_Trade.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {

                dialogClickTrade();
                return true;
            }

            private void DummyVoid() {};
        });
        Btn_Trade.setOnDragListener(onDragListener);

        Btn_Erase = findViewById(R.id.button2);
        Btn_Erase.setOnDragListener(onDragListener);
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }    // Receive from onStop()

    @Override
    protected void onStart() {

        super.onStart();

        adapterOutside.restartAdapter();
        adapterInside.restartAdapter();

        firebaseGetVehicles();
        firebaseGetCards();
        firebaseTariffs();
    }

    @Override
    protected void onResume() {

        super.onResume();
    }     // Receive from onPause()

    @Override
    protected void onNewIntent(Intent intent) {

        super.onNewIntent(intent);

        setIntent(intent);

        if (dialogNfcScan != null) {

            dialogNfcScan.getTagInfo(intent);
        }
    }


    /*----- Firebase -----*/

    private void firebaseGetVehicles() {

        databaseVehicles.getValueChanges(new DatabaseRealtime.ValueListener() {

            @Override
            public void ChildAdded(@NonNull DataSnapshot snapChild) {

                Vehicle vehicle = new Vehicle(snapChild);
                int state = vehicle.State;

                if (state == OUTSIDE) {

                    adapterOutside.addVehicleByChange(vehicle);

                } else if (state == INSIDE) {

                    adapterInside.addVehicleByChange(vehicle);
                }
            }

            @Override
            public void ChildChanged(@NonNull DataSnapshot snapChild) {

                Vehicle vehicle = new Vehicle(snapChild);
                int onList = searchOnList(vehicle.IdVehicle);
                int newState = vehicle.State;

                if (onList == OUTSIDE) {

                    if (newState == OUTSIDE) {

                        adapterOutside.modifyVehicle(vehicle);

                    } else if (newState == INSIDE) {

                        removeVehicle(OUTSIDE, vehicle);
                        moveVehicle(INSIDE, vehicle, FALSE);
                    }

                } else if (onList == INSIDE) {

                    if (newState == OUTSIDE) {

                        removeVehicle(INSIDE, vehicle);
                        moveVehicle(OUTSIDE, vehicle, FALSE);

                    } else if (newState == INSIDE) {

                        adapterInside.modifyVehicle(vehicle);
                    }
                }
            }

            @Override
            public void ChildRemoved(@NonNull DataSnapshot snapChild) {

                Vehicle vehicle = new Vehicle(snapChild);
                int state = databaseVehicles.getInteger("7");

                removeVehicle(state, vehicle);
            }
        });
    }

    private void firebaseGetCards() {

        databaseCards.getListValues(new DatabaseRealtime.ListListener() {

            @Override
            public void StartListen(boolean exist, long childCount) {

                cardManager = new NfcCardManager();
            }

            @Override
            public void ValueListen(String key, @NonNull DataSnapshot dataSnapshot, int count) {

                NfcCard nfcCard = new NfcCard(dataSnapshot);
                cardManager.addCardByLoad(nfcCard);
            }

            @Override
            public void FinishListen() { }
        });
    }

    private void firebaseTariffs() {

        databaseTariffs.getListValues(new DatabaseRealtime.ListListener() {

            @Override
            public void StartListen(boolean exist, long childCount) {

                tariffManager = new TariffsManager();
            }

            @Override
            public void ValueListen(String key, @NonNull DataSnapshot dataSnapshot, int count) {

                Tariff tariff = new Tariff(dataSnapshot);
                tariffManager.addTariffByLoad(tariff);
            }

            @Override
            public void FinishListen() { }
        });
    }

    private void firebaseCreateVehicle(@NonNull Vehicle vehicle) {

        databaseVehicles.setValue(vehicle.IdVehicle, vehicle, new DatabaseRealtime.CompleteListener() {

            @Override
            public void Success() {

                new MyToast(context, "¡Vehículo registrado con éxito!");
            }

            @Override
            public void Failure() {

                new MyToast(context, "¡Imposible registrar el vehículo!");
            }
        });
    }

    private void firebaseEraseVehicle(@NonNull Vehicle vehicle)  {

        String idVehicle = vehicle.IdVehicle;

        databaseVehicles.removeValue(idVehicle);
    }

    private void firebaseChangeState(@NonNull Vehicle vehicle) {

        HashMap<String, Object> hashMap = vehicle.getVehicleToUpdate();

        databaseVehicles.updateChild(vehicle.IdVehicle, hashMap);
    }

    private void firebaseReportEventHours(@NonNull Vehicle vehicle, long payment) {

        String idEvent = new DateTools().getMillisString();
        String date = new DateTools().getTime("yyyy/MM/dd");

        Event event = new Event(idEvent, vehicle.State, vehicle.Card, vehicle.HourIn, vehicle.HourOut, Manager, payment, vehicle.Tariff, vehicle.Type, vehicle.IdVehicle);

        DatabaseRealtime databaseEvents = new DatabaseRealtime(INSTANCE_EVENTS, date) {};
        databaseEvents.setValue(idEvent, event, new DatabaseRealtime.CompleteListener() {

            @Override
            public void Success() {

                Toast.makeText(context, "¡Cambio registrado con éxito!", LENGTH_LONG).show();
            }

            @Override
            public void Failure() {

                Toast.makeText(context, "¡Imposible registrar el cambio!", LENGTH_LONG).show();
            }
        });
    }

    private void firebaseReportEventMonthly(@NonNull Vehicle vehicle, boolean firstTime) {

        String idEvent = new DateTools().getMillisString();
        String date = new DateTools().getTime("yyyy/MM/dd");
        long payment;

        if (firstTime) {

            payment = vehicle.Payment;

        } else {

            payment = 0L;
        }

        Event event = new Event(idEvent, vehicle.State, vehicle.Card, vehicle.HourIn, vehicle.HourOut, Manager, payment, vehicle.Tariff, vehicle.Type, vehicle.IdVehicle);

        DatabaseRealtime databaseEvents = new DatabaseRealtime(INSTANCE_EVENTS, date) {};
        databaseEvents.setValue(idEvent, event, new DatabaseRealtime.CompleteListener() {

            @Override
            public void Success() {

                Toast.makeText(context, "¡Cambio registrado con éxito!", LENGTH_LONG).show();
            }

            @Override
            public void Failure() {

                Toast.makeText(context, "¡Imposible registrar el cambio!", LENGTH_LONG).show();
            }
        });
    }

    private void firebaseAssignCard(@NonNull Vehicle vehicle)  {

        String idCard = vehicle.Card;

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("1", vehicle.IdVehicle);

        databaseCards.updateChild(idCard, hashMap);
    }

    private void firebaseResetCard(@NonNull Vehicle vehicle)  {

        String idCard = vehicle.Card;

        databaseCards.removeValue(idCard + "/1");
    }

 
    /*----- Methods -----*/

    private void dragStarted(View draggedView) {

        if (VEHICLE == null) {

            VEHICLE = adapterOutside.getVehicle(draggedView);
        }

        if (VEHICLE == null) {

            VEHICLE = adapterInside.getVehicle(draggedView);
        }
    }

    private void dragEntered(View sourceView) {

        if (sourceView instanceof RecyclerView) {

            RecyclerView recyclerView = (RecyclerView) sourceView;

            if (recyclerView == recyclerOutside.getRecyclerView()) {

                DESTINE = TO_OUTSIDE;

            } else if (recyclerView == recyclerInside.getRecyclerView()) {

                DESTINE = TO_INSIDE;
            }

        } else if (sourceView instanceof ImageButton) {

            ImageButton imageButton = (ImageButton) sourceView;

            if (imageButton == Btn_Trade) {

                DESTINE = TO_OUTSIDE;

            } else if (imageButton == Btn_Erase) {

                DESTINE = TO_ERASE;
            }
        }
    }

    private void dragDrop(View draggedView) {

        if (VEHICLE == null) {

            if (SOURCE == FROM_CREATE && DESTINE == TO_INSIDE) {

                dialogClickCreate();
            }

        } else {

            if (SOURCE == FROM_OUTSIDE && DESTINE == TO_INSIDE) {

                boolean isOutside = adapterOutside.existVehicle(VEHICLE.IdVehicle);

                if (isOutside) {

                    removeVehicle(OUTSIDE, VEHICLE);
                    dialogDragToEnter(VEHICLE);

                } else {

                    new MyToast(context, "¡El vehículo no se encuentra fuera del parqueadero!");
                }

            } else if (SOURCE == FROM_INSIDE && DESTINE == TO_OUTSIDE) {

                boolean isInside = adapterInside.existVehicle(VEHICLE.IdVehicle);

                if (isInside) {

                    removeVehicle(INSIDE, VEHICLE);
                    dialogDragToExit(VEHICLE);

                } else {

                    new MyToast(context, "¡El vehículo no se encuentra en el parqueadero!");
                }

            } else if (SOURCE == FROM_OUTSIDE && DESTINE == TO_ERASE) {

                removeVehicle(OUTSIDE, VEHICLE);
                dialogDragToErase(VEHICLE);
            }
        }

        draggedView.setVisibility(VISIBLE);
    }

    private void dragEnd(View draggedView, @NonNull DragEvent dragEvent) {

        if (dragEvent.getResult()) {

            clearVariables();

        } else {

            draggedView.post(new Runnable() {

                @Override
                public void run() {

                    draggedView.setVisibility(VISIBLE);
                }
            });
        }
    }

    private void moveVehicle(@Definitions.State int state, Vehicle vehicle, boolean scroll) {

        if (state == OUTSIDE) {

            adapterOutside.addVehicleByChange(vehicle);
            recyclerOutside.setSpanCount(setSpanCount(OUTSIDE));

            if (scroll) {

                recyclerOutside.smoothScroll(scrollTo(OUTSIDE, vehicle));
            }

        } else if (state == INSIDE) {

            adapterInside.addVehicleByChange(vehicle);
            recyclerInside.setSpanCount(setSpanCount(INSIDE));

            if (scroll) {

                recyclerInside.smoothScroll(scrollTo(INSIDE, vehicle));
            }
        }
    }

    private void removeVehicle(@Definitions.State int state, Vehicle vehicle) {

        if (state == OUTSIDE) {

            adapterOutside.removeVehicle(vehicle);
            recyclerOutside.setSpanCount(setSpanCount(OUTSIDE));

        } else if (state == INSIDE) {

            adapterInside.removeVehicle(vehicle);
            recyclerInside.setSpanCount(setSpanCount(INSIDE));
        }
    }


    /*----- Listeners -----*/

    public final View.OnDragListener onDragListener = new View.OnDragListener() {

        @Override
        public boolean onDrag(View sourceView, @NonNull DragEvent dragEvent) {

            View draggedView = (View) dragEvent.getLocalState();

            switch (dragEvent.getAction()) {

                case ACTION_DRAG_STARTED: {

                    dragStarted(draggedView);
                    break;

                } case ACTION_DRAG_ENTERED: {

                    dragEntered(sourceView);
                    break;

                } case ACTION_DROP: {

                    dragDrop(draggedView);
                    break;

                } case ACTION_DRAG_ENDED: {

                    dragEnd(draggedView, dragEvent);

                } default: { break; }
            }

            return true;
        }

        private void DummyVoid() {}
    };


    /*----- Dialogs -----*/

    private void dialogClickCreate() {

        if (nfcRequired) {

            ArrayList<String> arrayExistCards = cardManager.getArrayCodes();

            dialogNfcScan = new DialogNfcScan(context, R.array.Dialog_Vehicle_Exit, pendingIntent) {

                @Override
                protected void ScannedCard(String codeCard) {

                    if (arrayExistCards.contains(codeCard)) {

                        NfcCard card = cardManager.getCardByCode(codeCard);
                        String assignVehicle = card.vehicle;

                        if (assignVehicle.equals("")) {

                            successMessage("¡Tag listo para asignar al vehículo!");

                        } else {

                            errorMessage("¡El Tag ya se encuentra asignado a un vehículo!");
                        }

                    } else {

                        errorMessage("¡El Tag no se encuentra registrado en el parqueadero!");
                    }
                }

                @Override
                protected void ButtonNegative() { }

                @Override
                protected void ButtonConfirm(String codeCard) {

                    dialogNfcScan.dismissDialog();

                    dialogCreateVehicle(codeCard);
                }
            };

        } else {

            dialogCreateVehicle("");
        }
    }

    private void dialogClickTrade() {

        if (nfcRequired) {

            dialogNfcScan = new DialogNfcScan(context, R.array.Dialog_Vehicle_Exit, pendingIntent) {

                @Override
                protected void ScannedCard(String codeCard) {

                    String idCard = cardManager.getCardByCode(codeCard).idCard;
                    String vehicle = cardManager.getStatusCard(idCard);

                    if (!vehicle.equals("")) {

                        boolean isOutside = adapterOutside.existVehicle(vehicle);
                        boolean isInside = adapterInside.existVehicle(vehicle);

                        if (isOutside) {

                            successMessage("¿Realmente desea dar ingreso al vehículo " + vehicle + "?");

                        } else if (isInside) {

                            successMessage("¿Realmente desea dar salida al vehículo " + vehicle + "?");
                        }

                        setModeButtons(BUTTONS_REFRESH_ACCEPT);

                    } else {

                        new MyToast(context, "¡Tag no asignado!");

                        setModeButtons(BUTTONS_CANCEL_REFRESH);
                    }
                }

                @Override
                protected void ButtonNegative() { }

                @Override
                protected void ButtonConfirm(String codeCard) {

                    String idCard = cardManager.getCardByCode(codeCard).idCard;
                    String statusCard = cardManager.getStatusCard(idCard);

                    boolean isOutside = adapterOutside.existVehicle(statusCard);
                    boolean isInside = adapterInside.existVehicle(statusCard);

                    if (isOutside) {

                        Vehicle vehicle = adapterOutside.getVehicleByCard(idCard);
                        vehicle.setState(INSIDE);

                        removeVehicle(OUTSIDE, vehicle);
                        moveVehicle(INSIDE, vehicle, TRUE);
                        firebaseChangeState(vehicle);

                        if (vehicle.Tariff == PERIOD_MONTHLY) {

                            firebaseReportEventMonthly(vehicle, FALSE);

                        } else {

                            firebaseReportEventHours(vehicle, 0L);
                        }

                    } else if (isInside) {

                        Vehicle vehicle = adapterInside.getVehicleByCard(idCard);

                        if (vehicle.Tariff == PERIOD_MONTHLY) {

                            vehicle.setState(OUTSIDE);

                            removeVehicle(INSIDE, vehicle);
                            moveVehicle(OUTSIDE, vehicle, TRUE);
                            firebaseChangeState(vehicle);
                            firebaseReportEventMonthly(vehicle, FALSE);

                        } else {

                            new VehicleDialogPayment(context, R.array.Dialogs_CreateVehicle, vehicle) {

                                @Override
                                protected void ButtonConfirm(Vehicle vehicle, long payment) {

                                    vehicle.setState(OUTSIDE);

                                    moveVehicle(OUTSIDE, vehicle, TRUE);
                                    firebaseChangeState(vehicle);
                                    firebaseReportEventHours(vehicle, payment);
                                }

                                @Override
                                protected void ButtonCancel() { }
                            };
                        }
                    }
                }
            };

        } else {

            Toast.makeText(context, "¡Funcion deshabilitada!", LENGTH_LONG).show();
        }
    }

    private void dialogDragToEnter(@NonNull Vehicle vehicle) {

        if (nfcRequired) {

            dialogNfcScan = new DialogNfcScan(context, R.array.Dialog_Vehicle_Exit, pendingIntent) {

                @Override
                protected void ScannedCard(String codeCard) {

                    successMessage("¿Realmente desea dar ingreso al vehículo " + vehicle.IdVehicle + "?");
                }

                @Override
                protected void ButtonNegative() {

                    moveVehicle(OUTSIDE, vehicle, TRUE);
                }

                @Override
                protected void ButtonConfirm(String codeCard) {

                    vehicle.setState(INSIDE);

                    moveVehicle(INSIDE, vehicle, TRUE);
                    firebaseChangeState(vehicle);

                    if (vehicle.Tariff == PERIOD_MONTHLY) {

                        firebaseReportEventMonthly(vehicle, FALSE);

                    } else {

                        firebaseReportEventHours(vehicle, 0L);
                    }
                }
            };

        } else {

            vehicle.setState(INSIDE);

            moveVehicle(INSIDE, vehicle, TRUE);
            firebaseChangeState(vehicle);

            if (vehicle.Tariff == PERIOD_MONTHLY) {

                firebaseReportEventMonthly(vehicle, FALSE);

            } else {

                firebaseReportEventHours(vehicle, 0L);
            }
        }
    }

    private void dialogDragToExit(@NonNull Vehicle vehicle) {

        if (nfcRequired) {

            dialogNfcScan = new DialogNfcScan(context, R.array.Dialog_Vehicle_Exit, pendingIntent) {

                @Override
                protected void ScannedCard(String IdCard) {

                    successMessage("¿Realmente desea dar salida al vehículo " + vehicle.IdVehicle + "?");
                }

                @Override
                protected void ButtonNegative() {

                    moveVehicle(INSIDE, vehicle, TRUE);
                }

                @Override
                protected void ButtonConfirm(String codeCard) {

                    if (vehicle.Tariff == PERIOD_MONTHLY) {

                        vehicle.setState(OUTSIDE);

                        moveVehicle(OUTSIDE, vehicle, TRUE);
                        firebaseChangeState(vehicle);
                        firebaseReportEventMonthly(vehicle, FALSE);

                    } else {

                        new VehicleDialogPayment(context, R.array.Dialogs_CreateVehicle, vehicle) {

                            @Override
                            protected void ButtonConfirm(Vehicle vehicle, long payment) {

                                vehicle.setState(OUTSIDE);

                                moveVehicle(OUTSIDE, vehicle, TRUE);
                                firebaseChangeState(vehicle);
                                firebaseReportEventHours(vehicle, payment);
                            }

                            @Override
                            protected void ButtonCancel() {

                                moveVehicle(INSIDE, vehicle, TRUE);
                            }
                        };
                    }
                }
            };

        } else {

            if (vehicle.Tariff == PERIOD_MONTHLY) {

                vehicle.setState(OUTSIDE);

                moveVehicle(OUTSIDE, vehicle, TRUE);
                firebaseChangeState(vehicle);
                firebaseReportEventMonthly(vehicle, FALSE);

            } else {

               new VehicleDialogPayment(context, R.array.Dialogs_CreateVehicle, vehicle) {

                   @Override
                   protected void ButtonConfirm(Vehicle vehicle, long payment) {

                       vehicle.setState(OUTSIDE);

                       moveVehicle(OUTSIDE, vehicle, TRUE);
                       firebaseChangeState(vehicle);
                       firebaseReportEventHours(vehicle, payment);
                   }

                   @Override
                   protected void ButtonCancel() {

                       moveVehicle(INSIDE, vehicle, TRUE);
                   }
               };
            }
        }
    }

    private void dialogDragToErase(@NonNull Vehicle vehicle) {

        String register = vehicle.IdVehicle;
        String instructions = "Deslice para eliminar el vehículo:\n" + register;

        DialogSlide dialogSlide = new DialogSlide(context, R.array.Dialogs_SlideEraseVehicle) {

            @Override
            protected void SlideButton() {

                firebaseEraseVehicle(vehicle);
                firebaseResetCard(vehicle);
            }

            @Override
            protected void PositiveButton() {

                moveVehicle(OUTSIDE, vehicle, TRUE);
            }
        };
        dialogSlide.setText(instructions);
    }

    private void dialogCreateVehicle(@NonNull String codeCard) {

        ArrayList<Tariff> arrayTariff = tariffManager.getArrayTariff();

        if (!codeCard.equals("")) {

            NfcCard card = cardManager.getCardByCode(codeCard);

            VehicleDialogCreate createDialog = new VehicleDialogCreate(context, R.array.Dialogs_CreateVehicle, arrayTariff, card.idCard) {

                @Override
                protected void ButtonConfirm(@NonNull Vehicle vehicle) {

                    int exsist = searchOnList(vehicle.IdVehicle);

                    if (exsist != OUTSIDE && exsist != INSIDE) {

                        firebaseCreateVehicle(vehicle);
                        firebaseAssignCard(vehicle);

                        if (vehicle.Tariff == PERIOD_MONTHLY) {

                            firebaseReportEventMonthly(vehicle, TRUE);

                        } else {

                            firebaseReportEventHours(vehicle, 0L);
                        }

                    } else {

                        Toast.makeText(context, "¡El vehiculo ya existe!", LENGTH_LONG).show();
                    }
                }

                private void DummyVoid() { }
            };

        } else {

            ArrayList<AdapterSingleIcon.Item> arrayFreeCards = new ArrayList<>();

            for(NfcCard card : cardManager.getArrayCards()) {

                if (card.vehicle.equals("")) {

                    AdapterSingleIcon.Item item = new AdapterSingleIcon.Item(card.idCard);
                    arrayFreeCards.add(item);
                }
            }

            VehicleDialogCreate createDialog = new VehicleDialogCreate(context, R.array.Dialogs_CreateVehicle, arrayTariff, arrayFreeCards) {

                @Override
                protected void ButtonConfirm(@NonNull Vehicle vehicle) {

                    int exsist = searchOnList(vehicle.IdVehicle);

                    if (exsist != OUTSIDE && exsist != INSIDE) {

                        firebaseCreateVehicle(vehicle);
                        firebaseAssignCard(vehicle);

                        if (vehicle.Tariff == PERIOD_MONTHLY) {

                            firebaseReportEventMonthly(vehicle, TRUE);

                        } else {

                            firebaseReportEventHours(vehicle, 0L);
                        }

                    } else {

                        Toast.makeText(context, "¡El vehiculo ya existe!", LENGTH_LONG).show();
                    }
                }

                private void DummyVoid() { }
            };
        }
    }


    /*----- Tools -----*/

    private int searchOnList(String vehicle) {

        boolean existOnOutside = adapterOutside.existVehicle(vehicle);
        boolean existOnInside = adapterInside.existVehicle(vehicle);

        if (existOnOutside) {

            return OUTSIDE;

        } else if (existOnInside) {

            return INSIDE;

        } else {

            return 3;
        }
    }

    private int setSpanCount(@Definitions.State int adapter) {

        int maxSpan = 3;
        long items = 0;

        if (adapter == OUTSIDE) {

            items = adapterOutside.getItemCount();
            maxSpan = 3;

        } else if (adapter == INSIDE) {

            items = adapterInside.getItemCount();
            maxSpan = 4;
        }

        if (items == 0) {

            return 1;

        } else {

            if (items >= maxSpan) {

                return maxSpan;

            } else {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                    return Math.toIntExact(items);

                } else {

                    return (int) items;
                }
            }
        }
    }

    private int scrollTo(@Definitions.State int adapter, Vehicle vehicle) {

        if (adapter == OUTSIDE) {

            return adapterOutside.getPositionOnFilter(vehicle);

        } else {

            return adapterInside.getPositionOnFilter(vehicle);
        }
    }

    private void clearVariables() {

        VEHICLE = null;
        SOURCE = FROM_NULL;
        DESTINE = TO_NULL;
    }


    /*----- Inherents -----*/

    @Override
    public void onBackPressed() { }

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

        databaseVehicles.removeValueListener();
        databaseCards.removeValueListener();
    }  // Return to onRestart()

    @Override
    protected void onDestroy() {

        super.onDestroy();

        databaseVehicles.removeValueListener();
        databaseCards.removeValueListener();
    }
}
