package com.vyping.libraries.vehicles;

import static com.vyping.libraries.general.Definitions.FROM_INSIDE;
import static com.vyping.libraries.vehicles.VehiclesActivity.SOURCE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.content.ClipData;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.vyping.libraries.R;
import com.vyping.libraries.general.Models.Vehicle;
import com.vyping.masterlibrary.Common.Strings;
import com.vyping.masterlibrary.Images.MyDrawable;

import java.util.ArrayList;

public class VehiclesInsideAdapter extends RecyclerView.Adapter<VehiclesInsideAdapter.ViewHolder> implements Filterable {

    private final Context context;
    private final CustomFilter filter;

    public static String search;
    public static ArrayList<Vehicle> arrayInVehicles, arrayInVehiclesFilter;


    /**
     * -------- SetUp Section
     */

    protected VehiclesInsideAdapter(final Context context) {

        this.context = context;
        this.filter = new CustomFilter(VehiclesInsideAdapter.this);

        search = "";

        restartAdapter();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void restartAdapter() {

        arrayInVehicles = new ArrayList<>();
        arrayInVehiclesFilter = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override @NonNull @SuppressLint("InflateParams")
    public ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {

        View  view = LayoutInflater.from(context).inflate(R.layout.vehicles_globe_inside, parent, false);

        return new ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout Rl_General;
        ImageView Iv_InType;
        TextView Tv_InIdVehicle, Tv_InCard;

        public ViewHolder(View vieHolder) {

            super(vieHolder);

            Rl_General = vieHolder.findViewById(R.id.Rl_Ins_General);

            Iv_InType = vieHolder.findViewById(R.id.Iv_Ins_Type);
            Tv_InIdVehicle = vieHolder.findViewById(R.id.Tv_Ins_IdVehicle);
            Tv_InCard = vieHolder.findViewById(R.id.Tv_Ins_Card);

        }
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        Vehicle vehicle = arrayInVehiclesFilter.get(position);
        Drawable drawable = new MyDrawable().changeDrawableColor(context, vehicle.getVehicleIcon(), R.color.BlueDark100);

        viewHolder.Rl_General.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View view) {

                SOURCE = FROM_INSIDE;

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

            private void DummyVOid(){ }
        });
        viewHolder.Iv_InType.setImageDrawable(drawable);
        viewHolder.Tv_InIdVehicle.setText(vehicle.IdVehicle);
        viewHolder.Tv_InCard.setText(vehicle.Card);

    }

    @Override
    public int getItemViewType(final int position) {

        return 0;
    }

    @Override
    public int getItemCount() {

        return arrayInVehiclesFilter.size();
    }


    /**
     * -------- Basic Process Section
     */

    public ArrayList<Vehicle> getVehicles() {

        return arrayInVehicles;
    }

    public ArrayList<Vehicle> getVehiclesFilters() {

        return arrayInVehiclesFilter;
    }

    public Vehicle getVehicle(View view) {

        VehiclesInsideAdapter.ViewHolder holder = new VehiclesInsideAdapter.ViewHolder(view);
        TextView textView = holder.Tv_InIdVehicle;
        Vehicle Return = null;

        if (textView != null) {

            String Register = String.valueOf(textView.getText());

            for (Vehicle vehicle : arrayInVehiclesFilter) {

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

        return arrayInVehiclesFilter.get(position);
    }

    public Vehicle getVehicle(String idVehicle) {

        Vehicle Vehicle = null;

        for (Vehicle vehicle : arrayInVehicles) {

            if (idVehicle.equals(vehicle.IdVehicle)) {

                Vehicle = vehicle;
                break;
            }
        }

        return Vehicle;
    }

    public Vehicle getVehicleByCard(String idCard) {

        Vehicle Vehicle = null;

        for (Vehicle vehicle : arrayInVehicles) {

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

        int position = arrayInVehicles.size();
        arrayInVehicles.add(position, vehicle);

        if (search.length() == 0) {

            arrayInVehiclesFilter.add(position, vehicle);
            notifyItemInserted(position);

        } else {

            if (filter.containsSearch(vehicle)) {

                int positionFilter = arrayInVehiclesFilter.size();
                arrayInVehiclesFilter.add(positionFilter, vehicle);
                notifyItemInserted(positionFilter);
            }
        }
    }

    public void addVehicleByChange(@NonNull final Vehicle vehicle) {

        boolean exist = existVehicle(vehicle.IdVehicle);

        if (!exist) {

            int position = setPosition(vehicle);
            arrayInVehicles.add(position, vehicle);

            if (search.length() == 0) {

                arrayInVehiclesFilter.add(position, vehicle);
                notifyItemInserted(position);

            } else {

                if (filter.containsSearch(vehicle)) {

                    int positionFilter = setPositionOnFilter(vehicle);
                    arrayInVehiclesFilter.add(positionFilter, vehicle);
                    notifyItemInserted(positionFilter);
                }
            }
        }
    }

    public void modifyVehicle(@NonNull final Vehicle vehicle) {

        int position = getPosition(vehicle);
        arrayInVehicles.set(position, vehicle);

        if (search.length() == 0) {

            arrayInVehiclesFilter.set(position, vehicle);
            notifyItemChanged(position);

        } else {

            if (filter.containsSearch(vehicle)) {

                int positionFilter = getPositionOnFilter(vehicle);
                arrayInVehiclesFilter.set(positionFilter, vehicle);
                notifyItemChanged(positionFilter);
            }
        }
    }

    public void removeVehicle(Vehicle vehicle) {

        int position = getPosition(vehicle);
        int positionFilter = getPositionOnFilter(vehicle);

        if (position != -1) {

            arrayInVehicles.remove(position);
        }

        if (positionFilter != -1) {

            arrayInVehiclesFilter.remove(positionFilter);
            notifyItemRemoved(position);
        }
    }


    /**
     * -------- Aditional Tools Section
     */

    private int setPosition(@NonNull final Vehicle vehicle) {

        String register = vehicle.IdVehicle;
        long arraySize = arrayInVehicles.size();
        int Return = 0;

        if (!arrayInVehicles.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String compareRegister = arrayInVehicles.get(position).IdVehicle;
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

                        String nextRegister = arrayInVehicles.get(position).Card;
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

        for (final Vehicle getVehicle : arrayInVehicles) {

            position = position + 1;
            String getIdVehicle = getVehicle.IdVehicle;

            if (idVehicle.equals(getIdVehicle)) {

                traslatePosition = position;
                break;
            }
        }

        return traslatePosition;
    }

    private int setPositionOnFilter(@NonNull final Vehicle vehicle) {

        String register = vehicle.IdVehicle;
        long arraySize = arrayInVehiclesFilter.size();
        int Return = 0;

        if (!arrayInVehiclesFilter.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String compareRegister = arrayInVehiclesFilter.get(position).IdVehicle;
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

                        String nextRegister = arrayInVehiclesFilter.get(position).Card;
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

        for (final Vehicle getVehicle : arrayInVehiclesFilter) {

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

        for (final Vehicle vehicle : arrayInVehicles) {

            String keyVehicle = vehicle.IdVehicle;

            if (IdVehicle.equals(keyVehicle)) {

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

        private final VehiclesInsideAdapter listAdapter;

        private CustomFilter(VehiclesInsideAdapter listAdapter) {

            super();

            this.listAdapter = listAdapter;
        }

        @Override
        protected FilterResults performFiltering(@NonNull CharSequence constraint) {

            arrayInVehiclesFilter.clear();
            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {

                arrayInVehiclesFilter.addAll(arrayInVehicles);

            } else {

                search = new Strings().toLowerCaseAndRemoveAccentMark(constraint.toString());

                for (final Vehicle vehicle : arrayInVehicles) {

                    boolean contains = containsSearch(vehicle);

                    if (contains) {

                        arrayInVehiclesFilter.add(vehicle);
                    }
                }
            }

            results.values = arrayInVehiclesFilter;
            results.count = arrayInVehiclesFilter.size();

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



