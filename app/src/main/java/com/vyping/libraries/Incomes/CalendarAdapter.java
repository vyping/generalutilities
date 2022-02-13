package com.vyping.libraries.Incomes;

import static com.vyping.masterlibrary.Common.DateTools.TIME_BEFORE;
import static com.vyping.masterlibrary.Common.DateTools.TIME_EQUALS;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.libraries.R;
import com.vyping.masterlibrary.Common.DateTools;
import com.vyping.masterlibrary.Common.Strings;
import com.vyping.masterlibrary.Firebase.Realtime.Readers;

import java.util.ArrayList;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.ViewHolder>{

    private final Context context;

    public static ArrayList<Day> arrayDays;


    /**
     * -------- SetUp Section
     */

    protected CalendarAdapter(final Context context) {

        this.context = context;

        restartAdapter();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void restartAdapter() {

        arrayDays = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override @NonNull @SuppressLint("InflateParams")
    public CalendarAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.calendar_globe_outside, parent, false);

        return new CalendarAdapter.ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout Ll_Background;
        TextView Tv_Day, Tv_Number, Tv_Payment;

        public ViewHolder(View vieHolder) {

            super(vieHolder);

            Ll_Background = vieHolder.findViewById(R.id.Rl_Out_General);
            Tv_Day = vieHolder.findViewById(R.id.Tv_Out_day);
            Tv_Number = vieHolder.findViewById(R.id.Tv_Out_IdVehicle);
            Tv_Payment = vieHolder.findViewById(R.id.Tv_Out_Card);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final CalendarAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        Day day = arrayDays.get(position);
        int color = context.getResources().getColor(day.setColorText());

        viewHolder.Ll_Background.setBackgroundResource(day.setBackground());
        viewHolder.Tv_Day.setText(day.dayOfWeek());
        viewHolder.Tv_Day.setTextColor(color);
        viewHolder.Tv_Number.setText(day.day);
        viewHolder.Tv_Number.setTextColor(color);
        viewHolder.Tv_Payment.setText(day.payment());
        viewHolder.Tv_Payment.setTextColor(color);
    }

    @Override
    public int getItemViewType(final int position) {

        return 0;
    }

    @Override
    public int getItemCount() {

        return arrayDays.size();
    }


    /**
     * -------- Basic Process Section
     */

    public Day getDay(View view) {

        ViewHolder holder = new ViewHolder(view);
        int position = holder.getAdapterPosition();

        return arrayDays.get(position);
    }

    public Day getDay(int position) {

        return arrayDays.get(position);
    }

    public Day getDay(String numberDay) {

        Day Day = null;

        for (Day day : arrayDays) {

            if (numberDay.equals(day.day)) {

                Day = day;
                break;
            }
        }

        return Day;
    }


    /**
     * -------- Adapter Process Section
     *
     */

    public void insertIncomeByLoad(@NonNull final Day day, int position) {

        arrayDays.add(position, day);
        notifyItemInserted(position);
    }

    public void addIncomeByLoad(@NonNull final Day day) {

        int position = arrayDays.size();

        arrayDays.add(position, day);
        notifyItemInserted(position);
    }

    public void addIncomeByChange(@NonNull final Day day) {

        boolean exist = arrayDays.contains(day);

        if (!exist) {

            int position = setPosition(day);
            arrayDays.add(position, day);
            notifyItemInserted(position);
        }
    }

    public void modifyIncome(@NonNull final Day day) {

        int position = getPosition(day);
        arrayDays.set(position, day);
        notifyItemChanged(position);
    }

    public void removeIncome(Day day) {

        int position = getPosition(day);

        if (position != -1) {

            arrayDays.remove(position);
        }
    }


    /**
     * -------- Aditional Tools Section
     */

    public int getPosition(@NonNull Day day) {

        int position = -1, IncomePosition = -1;
        String idIncome = String.valueOf(day.day);

        for (final Day getDay : arrayDays) {

            position = position + 1;
            String getIdVehicle = String.valueOf(getDay.day);

            if (idIncome.equals(getIdVehicle)) {

                IncomePosition = position;
                break;
            }
        }

        return IncomePosition;
    }

    private int setPosition(@NonNull final Day day) {

        String currentIncome = String.valueOf(day.day);
        long arraySize = arrayDays.size();
        int Return = 0;

        if (!arrayDays.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String compareIncome = String.valueOf(arrayDays.get(position).day);
                int compareMarge = currentIncome.compareTo(compareIncome);

                if (compareMarge < 0) {

                    Return = position;
                    break;

                } else if (compareMarge == 0) {

                    Return = position + 1;

                } else if (compareMarge > 0) {

                    if (position == arraySize - 1) {

                        Return = position + 1;

                    } else {

                        String nextIncome = String.valueOf(arrayDays.get(position).day);
                        int nextMarge = currentIncome.compareTo(nextIncome);

                        if (nextMarge < 0) {

                            Return = position + 1;
                            break;
                        }
                    }
                }
            }
        }

        return Return;
    }


    public static class Day extends Readers {

        public String day, month, year;
        public long payment;

        public Day(String day, String month, String year, long payment) {

            this.day = day;
            this.month = month;
            this.year = year;
            this.payment = payment;
        }

        public int setBackground() {

            if (!day.equals("00")) {

                int relation = new DateTools().comparateWithToday(day, month, year);

                if (relation == TIME_BEFORE || relation == TIME_EQUALS) {

                    boolean isThisMonth = new DateTools().isThisMonth(month, year);

                    if (isThisMonth) {

                        return R.drawable.calendar_shape_day;

                    } else {

                        return R.drawable.calendar_shape_month;
                    }

                } else {

                    return R.drawable.calendar_shape_other;
                }

            } else {

                return R.drawable.calendar_shape_void;
            }
        }

        public int setColorText() {

            if (!day.equals("00")) {

                int relation = new DateTools().comparateWithToday(day, month, year);

                if (relation == TIME_BEFORE || relation == TIME_EQUALS) {

                    return R.color.colorNegro;

                } else {

                    return R.color.colorGrey;
                }

            } else {

                return R.color.colorTransparente;
            }
        }

        public String dayOfWeek() {

            return new DateTools().dayOfWeekString(day, month, year);
        }

        public String payment() {

            int relation = new DateTools().comparateWithToday(day, month, year);

            if (relation == TIME_BEFORE || relation == TIME_EQUALS) {

                return new Strings().formatToMoney(payment);

            } else {


                return "";
            }
        }
    }
}

