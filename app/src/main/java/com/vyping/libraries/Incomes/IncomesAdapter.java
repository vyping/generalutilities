package com.vyping.libraries.Incomes;

import static com.vyping.libraries.general.Definitions.PERIOD_HOURS;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.libraries.R;
import com.vyping.libraries.general.Models.Income;
import com.vyping.masterlibrary.Common.Strings;

import java.util.ArrayList;

public class IncomesAdapter extends RecyclerView.Adapter<IncomesAdapter.ViewHolder> implements Filterable {

    private final Context context;
    private final CustomFilter filter;

    public static String search;
    public static ArrayList<Income> arrayIncomes, arrayIncomesFilter;


    /**
     * -------- SetUp Section
     */

    protected IncomesAdapter(final Context context) {

        this.context = context;
        this.filter = new IncomesAdapter.CustomFilter(IncomesAdapter.this);

        search = "";

        restartAdapter();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void restartAdapter() {

        arrayIncomes = new ArrayList<>();
        arrayIncomesFilter = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override @NonNull @SuppressLint("InflateParams")
    public IncomesAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.financial_globe, parent, false);

        return new IncomesAdapter.ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout Ll_Container;
        ImageView Iv_Payment, Iv_Vehicle, Iv_Period;
        TextView Tv_Payment, Tv_Vehicle, Tv_Period;

        public ViewHolder(View vieHolder) {

            super(vieHolder);

            Ll_Container = vieHolder.findViewById(R.id.Ll_Evt_Container);

            Iv_Payment = vieHolder.findViewById(R.id.imageView2);
            Tv_Payment = vieHolder.findViewById(R.id.Tv_Evt_Action);

            Iv_Vehicle = vieHolder.findViewById(R.id.imageView3);
            Tv_Vehicle = vieHolder.findViewById(R.id.Tv_Evt_Vehicle);

            Iv_Period = vieHolder.findViewById(R.id.imageView4);
            Tv_Period = vieHolder.findViewById(R.id.Tv_Evt_Hour);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final IncomesAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        Income income = arrayIncomesFilter.get(position);
        String period;

        if (income.Tariff == PERIOD_HOURS) {

            period = income.getHourRound();

        } else {

            period = "1 mes";
        }

        viewHolder.Ll_Container.setBackgroundResource(income.getBackground());
        viewHolder.Tv_Payment.setText(income.getPayment());
        viewHolder.Iv_Vehicle.setImageResource(income.getVehicleIcon());
        viewHolder.Tv_Vehicle.setText(income.Vehicle);
        viewHolder.Iv_Period.setImageResource(income.getTariffIcon());
        viewHolder.Tv_Period.setText(period);
    }

    @Override
    public int getItemViewType(final int position) {

        return arrayIncomesFilter.get(position).Action;
    }

    @Override
    public int getItemCount() {

        return arrayIncomesFilter.size();
    }


    /**
     * -------- Basic Process Section
     */

    public Income getIncome(View view) {

        ViewHolder holder = new ViewHolder(view);
        int position = holder.getAdapterPosition();

        return arrayIncomesFilter.get(position);
    }

    public Income getIncome(int position) {

        return arrayIncomesFilter.get(position);
    }

    public Income getIncome(String idIncome) {

        Income Income = null;

        for (Income income : arrayIncomesFilter) {

            if (idIncome.equals(income.IdIncome)) {

                Income = income;
                break;
            }
        }

        return Income;
    }


    /**
     * -------- Adapter Process Section
     * @param income
     */

    public void addIncomeByLoad(@NonNull final Income income) {

        int position = arrayIncomes.size();
        arrayIncomes.add(position, income);

        if (search.length() == 0) {

            arrayIncomesFilter.add(position, income);
            notifyItemInserted(position);

        } else {

            if (filter.containsSearch(income)) {

                int positionFilter = arrayIncomesFilter.size();
                arrayIncomesFilter.add(positionFilter, income);
                notifyItemInserted(positionFilter);
            }
        }
    }

    public void addIncomeByChange(@NonNull final Income income) {

        boolean exist = arrayIncomes.contains(income);

        if (!exist) {

            int position = setPosition(income);
            arrayIncomes.add(position, income);

            if (search.length() == 0) {

                arrayIncomesFilter.add(position, income);
                notifyItemInserted(position);

            } else {

                if (filter.containsSearch(income)) {

                    int positionFilter = setPositionOnFilter(income);
                    arrayIncomesFilter.add(positionFilter, income);
                    notifyItemInserted(positionFilter);
                }
            }
        }
    }

    public void modifyIncome(@NonNull final Income income) {

        int position = getPosition(income);
        arrayIncomes.set(position, income);

        if (search.length() == 0) {

            arrayIncomesFilter.set(position, income);
            notifyItemChanged(position);

        } else {

            if (filter.containsSearch(income)) {

                int positionFilter = getPositionOnFilter(income);
                arrayIncomesFilter.set(positionFilter, income);
                notifyItemChanged(positionFilter);
            }
        }
    }

    public void removeIncome(Income income) {

        int position = getPosition(income);
        int positionFilter = getPositionOnFilter(income);

        if (position != -1) {

            arrayIncomes.remove(position);
        }

        if (positionFilter != -1) {

            arrayIncomesFilter.remove(positionFilter);
            notifyItemRemoved(position);
        }
    }


    /**
     * -------- Aditional Tools Section
     */

    private int setPosition(@NonNull final Income income) {

        String currentIncome = String.valueOf(income.IdIncome);
        long arraySize = arrayIncomes.size();
        int Return = 0;

        if (!arrayIncomes.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String compareIncome = String.valueOf(arrayIncomes.get(position).IdIncome);
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

                        String nextIncome = String.valueOf(arrayIncomes.get(position).IdIncome);
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

    public int getPosition(@NonNull Income income) {

        int position = -1, IncomePosition = -1;
        String idIncome = String.valueOf(income.IdIncome);

        for (final Income getIncome : arrayIncomes) {

            position = position + 1;
            String getIdVehicle = String.valueOf(getIncome.IdIncome);

            if (idIncome.equals(getIdVehicle)) {

                IncomePosition = position;
                break;
            }
        }

        return IncomePosition;
    }

    private int setPositionOnFilter(@NonNull final Income income) {

        String currentIncome = String.valueOf(income.IdIncome);
        long arraySize = arrayIncomesFilter.size();
        int Return = 0;

        if (!arrayIncomesFilter.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String compareIncome = String.valueOf(arrayIncomesFilter.get(position).IdIncome);
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

                        String nextIncome = String.valueOf(arrayIncomesFilter.get(position).IdIncome);
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

    public int getPositionOnFilter(@NonNull final Income income) {

        int position = -1, EventPosition = -1;
        String idEvent = String.valueOf(income.IdIncome);

        for (final Income getIncome : arrayIncomesFilter) {

            position = position + 1;

            if (idEvent.equals(String.valueOf(getIncome.IdIncome))) {

                EventPosition = position;
                break;
            }
        }

        return EventPosition;
    }


    /**
     * -------- Filter Section
     */

    @Override
    public Filter getFilter() {

        return filter;
    }

    public static class CustomFilter extends Filter {

        private final IncomesAdapter listAdapter;

        private CustomFilter(IncomesAdapter listAdapter) {

            super();

            this.listAdapter = listAdapter;
        }

        @Override
        protected FilterResults performFiltering(@NonNull CharSequence constraint) {

            arrayIncomesFilter.clear();
            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {

                arrayIncomesFilter.addAll(arrayIncomes);

            } else {

                search = new Strings().toLowerCaseAndRemoveAccentMark(constraint.toString());

                for (final Income income : arrayIncomes) {

                    boolean contains = containsSearch(income);

                    if (contains) {

                        arrayIncomesFilter.add(income);
                    }
                }
            }

            results.values = arrayIncomesFilter;
            results.count = arrayIncomesFilter.size();

            return results;
        }

        public boolean containsSearch(@NonNull final Income income) {

            String typeEvent = new Strings().toLowerCaseAndRemoveAccentMark(income.getEventType());
            String hour = new Strings().toLowerCaseAndRemoveAccentMark(income.getHourDiff());
            String vehicle = new Strings().toLowerCaseAndRemoveAccentMark(income.Vehicle);
            String manager = new Strings().toLowerCaseAndRemoveAccentMark(income.Manager);

            if (typeEvent.contains(search) || hour.contains(search) ||vehicle.contains(search) || manager.contains(search)) {

                return TRUE;

            } else {

                return FALSE;
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            listAdapter.notifyDataSetChanged();
        }
    }
}

