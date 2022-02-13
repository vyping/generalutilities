package com.vyping.libraries.vehicles;

import static com.vyping.libraries.general.Definitions.FROM_OUTSIDE;
import static com.vyping.libraries.vehicles.VehiclesActivity.SOURCE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.libraries.R;
import com.vyping.libraries.general.Models.Vehicle;
import com.vyping.masterlibrary.Common.Strings;

import java.util.ArrayList;

public class VehiclesOutsideAdapter extends RecyclerView.Adapter<VehiclesOutsideAdapter.ViewHolder> implements Filterable {

    private final Context context;
    private final CustomFilter filter;

    public static String search;
    public static ArrayList<Vehicle> arrayOutVehicles, arrayOutVehiclesFilter;


    /**
     * -------- SetUp Section
     */

    protected VehiclesOutsideAdapter(final Context context) {

        this.context = context;
        this.filter = new CustomFilter(VehiclesOutsideAdapter.this);

        search = "";

        restartAdapter();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void restartAdapter() {

        arrayOutVehicles = new ArrayList<>();
        arrayOutVehiclesFilter = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override @NonNull @SuppressLint("InflateParams")
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.vehicles_globe_outside, parent, false);

        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout Rl_General;
        TextView Tv_OutIdVehicle, Tv_OutCard;

        public ViewHolder(View vieHolder) {

            super(vieHolder);

            Rl_General = vieHolder.findViewById(R.id.Rl_Out_General);

            Tv_OutIdVehicle = vieHolder.findViewById(R.id.Tv_Out_IdVehicle);
            Tv_OutCard = vieHolder.findViewById(R.id.Tv_Out_Card);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        Vehicle vehicle = arrayOutVehiclesFilter.get(position);

        viewHolder.Rl_General.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {

                SOURCE = FROM_OUTSIDE;
                Vehicle vehicle = getVehicle(view);

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

            private void DummyVOid() {}
        });
        viewHolder.Tv_OutIdVehicle.setText(vehicle.IdVehicle);
        viewHolder.Tv_OutCard.setText(vehicle.Card);
    }

    @Override
    public int getItemViewType(final int position) {

        return 0;
    }

    @Override
    public int getItemCount() {

        return arrayOutVehiclesFilter.size();
    }


    /**
     * -------- Basic Process Section
     */

    public ArrayList<Vehicle> getVehicles() {

        return arrayOutVehicles;
    }

    public ArrayList<Vehicle> getVehiclesFilters() {

        return arrayOutVehiclesFilter;
    }

    public Vehicle getVehicle(View view) {

        VehiclesOutsideAdapter.ViewHolder holder = new VehiclesOutsideAdapter.ViewHolder(view);
        TextView textView = holder.Tv_OutIdVehicle;
        Vehicle Return = null;

        if (textView != null) {

            String Register = String.valueOf(textView.getText());

            for (Vehicle vehicle : arrayOutVehiclesFilter) {

                String register = vehicle.IdVehicle;

                if (Register.equals(register)) {

                    Return = vehicle;
                    break;
                }
            }
        }

        return Return;
    }

    public Vehicle getVehicle(int position) {

        return arrayOutVehiclesFilter.get(position);
    }

    public Vehicle getVehicle(String idVehicle) {

        Vehicle Vehicle = null;

        for (Vehicle vehicle : arrayOutVehicles) {

            if (idVehicle.equals(vehicle.IdVehicle)) {

                Vehicle = vehicle;
                break;
            }
        }

        return Vehicle;
    }

    public Vehicle getVehicleByCard(String idCard) {

        Vehicle Vehicle = null;

        for (Vehicle vehicle : arrayOutVehicles) {

            if (idCard.equals(vehicle.Card)) {

                Vehicle = vehicle;
                break;
            }
        }

        return Vehicle;
    }


    /**
     * -------- Adapter Process Section
     */

    public void addVehicleByLoad(@NonNull final Vehicle vehicle) {

        int position = arrayOutVehicles.size();
        arrayOutVehicles.add(position, vehicle);

        if (search.length() == 0) {

            arrayOutVehiclesFilter.add(position, vehicle);
            notifyItemInserted(position);

        } else {

            if (filter.containsSearch(vehicle)) {

                int positionFilter = arrayOutVehiclesFilter.size();
                arrayOutVehiclesFilter.add(positionFilter, vehicle);
                notifyItemInserted(positionFilter);
            }
        }
    }

    public void addVehicleByChange(@NonNull final Vehicle vehicle) {

        boolean exist = existVehicle(vehicle.IdVehicle);

        if (!exist) {

            int position = setPosition(vehicle);
            arrayOutVehicles.add(position, vehicle);

            if (search.length() == 0) {

                arrayOutVehiclesFilter.add(position, vehicle);
                notifyItemInserted(position);

            } else {

                if (filter.containsSearch(vehicle)) {

                    int positionFilter = setPositionOnFilter(vehicle);
                    arrayOutVehiclesFilter.add(positionFilter, vehicle);
                    notifyItemInserted(positionFilter);
                }
            }
        }
    }

    public void modifyVehicle(@NonNull final Vehicle vehicle) {

        int position = getPosition(vehicle);
        arrayOutVehicles.set(position, vehicle);

        if (search.length() == 0) {

            arrayOutVehiclesFilter.set(position, vehicle);
            notifyItemChanged(position);

        } else {

            if (filter.containsSearch(vehicle)) {

                int positionFilter = getPositionOnFilter(vehicle);
                arrayOutVehiclesFilter.set(positionFilter, vehicle);
                notifyItemChanged(positionFilter);
            }
        }
    }

    public void removeVehicle(Vehicle vehicle) {

        int position = getPosition(vehicle);
        int positionFilter = getPositionOnFilter(vehicle);

        if (position != -1) {

            arrayOutVehicles.remove(position);
        }

        if (positionFilter != -1) {

            arrayOutVehiclesFilter.remove(positionFilter);
            notifyItemRemoved(position);
        }
    }


    /**
     * -------- Aditional Tools Section
     */

    public int setPosition(@NonNull final Vehicle vehicle) {

        String register = vehicle.IdVehicle;
        long arraySize = arrayOutVehicles.size();
        int Return = 0;

        if (!arrayOutVehicles.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String compareRegister = arrayOutVehicles.get(position).IdVehicle;
                int compareMarge = register.compareTo(compareRegister);

                if (compareMarge < 0) {

                    Return = position;
                    break;

                } else if (compareMarge == 0) {

                    Return = position + 1;

                } else if (compareMarge > 0) {

                    if (position == arraySize - 1) {

                        Return = position + 1;

                    } else {

                        String nextRegister = arrayOutVehicles.get(position).Card;
                        int nextMarge = register.compareTo(nextRegister);

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

    public int getPosition(@NonNull Vehicle vehicle) {

        int position = -1, traslatePosition = -1;
        String idVehicle = vehicle.IdVehicle;

        for (final Vehicle getVehicle : arrayOutVehicles) {

            position = position + 1;
            String getIdVehicle = getVehicle.IdVehicle;

            if (idVehicle.equals(getIdVehicle)) {

                traslatePosition = position;
                break;
            }
        }

        return traslatePosition;
    }

    public int setPositionOnFilter(@NonNull final Vehicle vehicle) {

        String register = vehicle.IdVehicle;
        long arraySize = arrayOutVehiclesFilter.size();
        int Return = 0;

        if (!arrayOutVehiclesFilter.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String compareRegister = arrayOutVehiclesFilter.get(position).IdVehicle;
                int compareMarge = register.compareTo(compareRegister);

                if (compareMarge < 0) {

                    Return = position;
                    break;

                } else if (compareMarge == 0) {

                    Return = position + 1;

                } else if (compareMarge > 0) {

                    if (position == arraySize - 1) {

                        Return = position + 1;

                    } else {

                        String nextRegister = arrayOutVehiclesFilter.get(position).Card;
                        int nextMarge = register.compareTo(nextRegister);

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

    public int getPositionOnFilter(@NonNull final Vehicle vehicle) {

        int position = -1, traslatePosition = -1;
        String idVehicle = vehicle.IdVehicle;

        for (final Vehicle getVehicle : arrayOutVehiclesFilter) {

            position = position + 1;

            if (idVehicle.equals(getVehicle.IdVehicle)) {

                traslatePosition = position;
                break;
            }
        }

        return traslatePosition;
    }

    public boolean existVehicle(final String IdVehicle) {

        boolean exist = FALSE;

        for (final Vehicle traslate : arrayOutVehicles) {

            String keyTraslate = traslate.IdVehicle;

            if (IdVehicle.equals(keyTraslate)) {

                exist = TRUE;
                break;
            }
        }

        return exist;
    }


    /**
     * -------- Filter Section
     */

    @Override
    public Filter getFilter() {

        return filter;
    }

    public static class CustomFilter extends Filter {

        private final VehiclesOutsideAdapter listAdapter;

        private CustomFilter(VehiclesOutsideAdapter listAdapter) {

            super();

            this.listAdapter = listAdapter;
        }

        @Override
        protected FilterResults performFiltering(@NonNull CharSequence constraint) {

            arrayOutVehiclesFilter.clear();
            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {

                arrayOutVehiclesFilter.addAll(arrayOutVehicles);

            } else {

                search = new Strings().toLowerCaseAndRemoveAccentMark(constraint.toString());

                for (final Vehicle vehicle : arrayOutVehicles) {

                    boolean contains = containsSearch(vehicle);

                    if (contains) {

                        arrayOutVehiclesFilter.add(vehicle);
                    }
                }
            }

            results.values = arrayOutVehiclesFilter;
            results.count = arrayOutVehiclesFilter.size();

            return results;
        }

        public boolean containsSearch(@NonNull final Vehicle vehicle) {

            String idVehicle = new Strings().toLowerCaseAndRemoveAccentMark(vehicle.IdVehicle);
            String register = new Strings().toLowerCaseAndRemoveAccentMark(vehicle.Card);
            String phone = new Strings().toLowerCaseAndRemoveAccentMark(vehicle.Phone);
            String hourIn = new Strings().toLowerCaseAndRemoveAccentMark(vehicle.getHourIn());
            String hourOut = new Strings().toLowerCaseAndRemoveAccentMark(vehicle.getHourOut());
            String type = new Strings().toLowerCaseAndRemoveAccentMark(vehicle.getVehicleType());

            if (idVehicle.contains(search) || register.contains(search) || phone.contains(search) || hourIn.contains(search) || hourOut.contains(search)  || type.contains(search)) {

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



