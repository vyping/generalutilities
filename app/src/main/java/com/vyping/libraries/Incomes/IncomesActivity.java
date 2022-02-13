package com.vyping.libraries.Incomes;

import static com.vyping.libraries.general.Constants.INSTANCE_EVENTS;
import static com.vyping.libraries.general.Constants.INSTANCE_VEHICLES;
import static com.vyping.libraries.general.Definitions.PERIOD_HOURS;
import static com.vyping.libraries.general.Definitions.PERIOD_MONTHLY;
import static com.vyping.masterlibrary.Common.Definitions.LAYOUT_VERTICAL;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.vyping.libraries.R;
import com.vyping.libraries.events.EventsActivity;
import com.vyping.libraries.general.Models.Income;
import com.vyping.libraries.vehicles.VehiclesActivity;
import com.vyping.masterlibrary.ActionBar.MyActionBar;
import com.vyping.masterlibrary.Common.MyActivity;
import com.vyping.masterlibrary.Common.DateTools;
import com.vyping.masterlibrary.Common.LogCat;
import com.vyping.masterlibrary.Common.Strings;
import com.vyping.masterlibrary.Firebase.Realtime.DatabaseRealtime;
import com.vyping.masterlibrary.Firebase.RemoteConfig;
import com.vyping.masterlibrary.PopUps.MyPopUpBubbles;
import com.vyping.masterlibrary.ToolBars.MyToolBars;
import com.vyping.masterlibrary.Views.RecyclerViews;

import java.util.Calendar;

public class IncomesActivity extends AppCompatActivity {

    private Context context;
    private DatabaseRealtime databaseIncomes;
    private IncomesAdapter adapterIncomes;
    private CalendarAdapter adapterCalendar;

    private RecyclerViews recyclerViews;

    private LinearLayout Ll_Incomes;
    private TextView Tv_Hours, Tv_Monthly, Tv_Total;

    private long hoursPayment, monthlyPayment, totalPayment;
    private boolean monthly;
    private MyToolBars toolBars;


    /*----- SetUp -----*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.financial_activity);

        setStartProcess();
        setInherentViews();
        setActivityViews();
    }

    private void setStartProcess() {

        context = IncomesActivity.this;

        new RemoteConfig(context, R.xml.remote_config, new RemoteConfig.SuccessListener() {

            @Override
            public void Success(FirebaseRemoteConfig firebaseRemoteConfig) {


            }

            private void DummyVoid() {
            }
        });

        databaseIncomes = new DatabaseRealtime(INSTANCE_VEHICLES) {};

        adapterCalendar = new CalendarAdapter(context);
        adapterIncomes = new IncomesAdapter(context);

        monthly = TRUE;
    }

    private void setInherentViews() {

        MyActionBar actionBarCustom = new MyActionBar(context, R.drawable.icon_money, "Contabilidad") {};
        actionBarCustom.setButtonOption(com.vyping.masterlibrary.R.drawable.icon_car, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new MyActivity().Start(context, VehiclesActivity.class, TRUE);
            }
        });
        actionBarCustom.setButtonOption(com.vyping.masterlibrary.R.drawable.icon_history, new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                new MyActivity().Start(context, EventsActivity.class, TRUE);
            }
        });
        actionBarCustom.showButtonTools();

        toolBars = new MyToolBars(context, R.id.toolbars) {};
        toolBars.setMonthToolBar(new MyToolBars.MonthInterface() {

            @Override
            public void SelectedMonth(Calendar Calendar, long milis, String month, String year) {
                new LogCat("setInherentViews - month", month, "year", year);
                firebaseMonthEvents(milis, "20"+year, month);
            }
        });
    }

    @SuppressLint("ClickableViewAccessibility")
    private void setActivityViews() {

        recyclerViews = new RecyclerViews(context, R.id.sdfsd, adapterCalendar, 7, LAYOUT_VERTICAL);
        recyclerViews.selectListener(new RecyclerViews.selectInterface() {

            @Override
            public void SelectedItem(View selectedView, int position, RecyclerView.ViewHolder viewHolder) {

                if (monthly) {

                    CalendarAdapter.Day day = adapterCalendar.getDay(position);
                    String Month = day.month;
                    String Day = day.day;
                    String Year = day.year;

                    showDailyIncomes(Year, Month, Day);

                } else {

                    Income income = adapterIncomes.getIncome(position);

                    MyPopUpBubbles popUpBubbles = new MyPopUpBubbles(context, selectedView);
                    popUpBubbles.setButtonMid("\uf129", new MyPopUpBubbles.ClickInterface() {

                        @Override
                        public void ButtonClick() {

                            new IncomesDialogInfo(context, R.array.Dialogs_CreateVehicle, income) {};
                        }

                        private void DummyVoid() {}
                    });
                }
            }

            private void DummyVoid() {}
        });

        Ll_Incomes = findViewById(R.id.Ll_Evt_Container);
        Tv_Hours = findViewById(R.id.Tv_Evt_Hours);
        Tv_Monthly = findViewById(R.id.Tv_Evt_Monthly);
        Tv_Total = findViewById(R.id.Tv_Evt_Total);
    }

    @Override
    protected void onRestart() {

        super.onRestart();
    }    // Receive from onStop()

    @Override
    protected void onStart() {

        super.onStart();

        if (monthly) {

            toolBars.setMonth();

        } else {

            toolBars.setDate();
        }
    }

    @Override
    protected void onResume() {

        super.onResume();
    }     // Receive from onPause()


    /*----- Firebase -----*/

    private void firebaseMonthEvents(long milis, String year, String month) {
        new LogCat("firebaseMonthEvents - month", month, "year", year);

        adapterCalendar.restartAdapter();

        hoursPayment = 0L;
        monthlyPayment = 0L;
        totalPayment = 0L;
        int maxDayOfMonth = new DateTools().daysOfMonth(milis);
        int dayNumber = new DateTools().dayOfWeekInteger("01", month, year);
        String prevMonth = new DateTools().previousMonth(milis);

        new LogCat("maxDayOfMonth", maxDayOfMonth, "dayNumber", dayNumber, "prevMonth", prevMonth);

        if (dayNumber != 1) {

            for (int i=0; i<dayNumber-1; ++i) {

                CalendarAdapter.Day DayVoid = new CalendarAdapter.Day("00", prevMonth, year, 0);
                adapterCalendar.insertIncomeByLoad(DayVoid, i);
            }
        }

        for (int i=0; i<maxDayOfMonth; ++i) {

            String dayOfMonth = new Strings().formatDigits(i+1, 2);

            CalendarAdapter.Day Day = new CalendarAdapter.Day(dayOfMonth, month, year, 0L);
            adapterCalendar.addIncomeByLoad(Day);
        };

        databaseIncomes = new DatabaseRealtime(INSTANCE_EVENTS, year, month) {};
        databaseIncomes.getListValues(new DatabaseRealtime.ListListener() {

            @Override
            public void StartListen(boolean exist, long childCount) { }

            @Override
            public void ValueListen(String key, @NonNull DataSnapshot dataSnapshot, int count) {

                String day = databaseIncomes.getKeyString();
                long dayPayment = 0L;

                for (DataSnapshot event :  dataSnapshot.getChildren()) {

                    int tariff = databaseIncomes.getInteger(event.child("Tariff"));
                    long payment = databaseIncomes.getLong(event.child("Payment"));

                    if (tariff == PERIOD_MONTHLY) {

                        monthlyPayment = monthlyPayment + payment;
                        dayPayment = dayPayment + payment;

                    } else if (tariff == PERIOD_HOURS) {

                        hoursPayment = hoursPayment + payment;
                        dayPayment = dayPayment + payment;
                    }
                }

                totalPayment = totalPayment + dayPayment;

                CalendarAdapter.Day Day = new CalendarAdapter.Day(day, month, year, dayPayment);
                adapterCalendar.modifyIncome(Day);
            }

            @Override
            public void FinishListen() {

                String HoursPayment = new Strings().formatToMoney(hoursPayment);
                String MonthlyPayment = new Strings().formatToMoney(monthlyPayment);
                String TotalPayment = new Strings().formatToMoney(totalPayment);

                Tv_Hours.setText(HoursPayment);
                Tv_Monthly.setText(MonthlyPayment);
                Tv_Total.setText(TotalPayment);
            }
        });
    }

    private void firebaseDailyEvents(String year, String month, String day) {

        adapterIncomes.restartAdapter();

        new LogCat("day", day, "month", month, "year", year);

        databaseIncomes = new DatabaseRealtime(INSTANCE_EVENTS, year, month, day) {};
        databaseIncomes.getListValues(new DatabaseRealtime.ListListener() {

            @Override
            public void StartListen(boolean exist, long childCount) { }

            @Override
            public void ValueListen(String key, @NonNull DataSnapshot dataSnapshot, int count) {

                Income income = new Income(dataSnapshot);

                if (income.Payment != 0) {

                    adapterIncomes.addIncomeByLoad(income);
                }
            }

            @Override
            public void FinishListen() { }
        });
    }


    /*----- Methods -----*/

    private void showMonthlyIncomes() {

        monthly = TRUE;

        toolBars.setMonthToolBar(new MyToolBars.MonthInterface() {

            @Override
            public void SelectedMonth(Calendar Calendar, long milis, String month, String year) {
                new LogCat("SelectedMonth - month", month, "year", year);
                recyclerViews = new RecyclerViews(context, R.id.sdfsd, adapterCalendar, 7, LAYOUT_VERTICAL);
                firebaseMonthEvents(milis, "20" + year, month);
            }

            private void DummyVoid() {}
        });
        toolBars.setMonth();
    }

    private void showDailyIncomes(@NonNull String year, String month, String day) {

        monthly = FALSE;

        toolBars.setDateToolBar(new MyToolBars.DateInterface() {

            @Override
            public void SelectedDate(Calendar Calendar, long milis, String date, String day, String month, String year) {

                recyclerViews = new RecyclerViews(context, R.id.sdfsd, adapterIncomes);
                firebaseDailyEvents("20"+year, month, day);
            }

            private void DummyVoid() {}
        });
        toolBars.setDate(day, month, year.replace("20", ""));
    }


    /*----- Listeners -----*/



    /*----- Dialogs -----*/



    /*----- Tools -----*/



    /*----- Inherents -----*/

    @Override
    public void onBackPressed() {

        if (monthly) {

            new MyActivity().Start(context, VehiclesActivity.class, TRUE);

        } else {

            showMonthlyIncomes();
        }
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

        databaseIncomes.removeValueListener();
    }  // Return to onRestart()

    @Override
    protected void onDestroy() {

        super.onDestroy();

        databaseIncomes.removeValueListener();
    }
}