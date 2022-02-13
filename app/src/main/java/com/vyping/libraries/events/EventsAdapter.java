package com.vyping.libraries.events;

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
import com.vyping.libraries.general.Models.Event;
import com.vyping.masterlibrary.Common.Strings;

import java.util.ArrayList;

public class EventsAdapter extends RecyclerView.Adapter<EventsAdapter.ViewHolder> implements Filterable {

    private final Context context;
    private final CustomFilter filter;

    public static String search;
    public static ArrayList<Event> arrayEvents, arrayEventsFilter;


    /**
     * -------- SetUp Section
     */

    protected EventsAdapter(final Context context) {

        this.context = context;
        this.filter = new EventsAdapter.CustomFilter(EventsAdapter.this);

        search = "";

        restartAdapter();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void restartAdapter() {

        arrayEvents = new ArrayList<>();
        arrayEventsFilter = new ArrayList<>();
        notifyDataSetChanged();
    }

    @Override @NonNull @SuppressLint("InflateParams")
    public EventsAdapter.ViewHolder onCreateViewHolder(@NonNull final ViewGroup parent, final int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.events_globe, parent, false);

        return new EventsAdapter.ViewHolder(view);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout Ll_Container;
        ImageView Iv_IconTypeEvent, Iv_IconVehicle;
        TextView Tv_Hour, Tv_TypeEvent, Tv_Vehicle;

        public ViewHolder(View vieHolder) {

            super(vieHolder);

            Ll_Container = vieHolder.findViewById(R.id.Ll_Evt_Container);
            Tv_Hour = vieHolder.findViewById(R.id.Tv_Evt_Hour);
            Iv_IconTypeEvent = vieHolder.findViewById(R.id.imageView2);
            Tv_TypeEvent = vieHolder.findViewById(R.id.Tv_Evt_Action);
            Iv_IconVehicle = vieHolder.findViewById(R.id.imageView3);
            Tv_Vehicle = vieHolder.findViewById(R.id.Tv_Evt_Vehicle);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final EventsAdapter.ViewHolder viewHolder, @SuppressLint("RecyclerView") final int position) {

        Event event = arrayEventsFilter.get(position);

        viewHolder.Ll_Container.setBackgroundResource(event.getBackground());
        viewHolder.Tv_Hour.setText(event.getHour());
        viewHolder.Iv_IconTypeEvent.setImageResource(event.getEventIcon());
        viewHolder.Tv_TypeEvent.setText(event.getEventType());
        viewHolder.Iv_IconVehicle.setImageResource(event.getVehicleIcon());
        viewHolder.Tv_Vehicle.setText(event.Vehicle);
    }

    @Override
    public int getItemViewType(final int position) {

        return arrayEventsFilter.get(position).Action;
    }

    @Override
    public int getItemCount() {

        return arrayEventsFilter.size();
    }


    /**
     * -------- Basic Process Section
     */

    public Event getEvent(View view) {

        ViewHolder holder = new ViewHolder(view);
        int position = holder.getAdapterPosition();

        return arrayEventsFilter.get(position);
    }

    public Event getEvent(int position) {

        return arrayEventsFilter.get(position);
    }

    public Event getEvent(String idEvent) {

        Event Event = null;

        for (Event event : arrayEventsFilter) {

            if (idEvent.equals(event.IdEvent)) {

                Event = event;
                break;
            }
        }

        return Event;
    }


    /**
     * -------- Adapter Process Section
     */

    public void addEventByLoad(@NonNull final Event event) {

        int position = arrayEvents.size();
        arrayEvents.add(position, event);

        if (search.length() == 0) {

            arrayEventsFilter.add(position, event);
            notifyItemInserted(position);

        } else {

            if (filter.containsSearch(event)) {

                int positionFilter = arrayEventsFilter.size();
                arrayEventsFilter.add(positionFilter, event);
                notifyItemInserted(positionFilter);
            }
        }
    }

    public void addEventByChange(@NonNull final Event event) {

        boolean exist = arrayEvents.contains(event);

        if (!exist) {

            int position = setPosition(event);
            arrayEvents.add(position, event);

            if (search.length() == 0) {

                arrayEventsFilter.add(position, event);
                notifyItemInserted(position);

            } else {

                if (filter.containsSearch(event)) {

                    int positionFilter = setPositionOnFilter(event);
                    arrayEventsFilter.add(positionFilter, event);
                    notifyItemInserted(positionFilter);
                }
            }
        }
    }

    public void modifyEvent(@NonNull final Event event) {

        int position = getPosition(event);
        arrayEvents.set(position, event);

        if (search.length() == 0) {

            arrayEventsFilter.set(position, event);
            notifyItemChanged(position);

        } else {

            if (filter.containsSearch(event)) {

                int positionFilter = getPositionOnFilter(event);
                arrayEventsFilter.set(positionFilter, event);
                notifyItemChanged(positionFilter);
            }
        }
    }

    public void removeEvent(Event event) {

        int position = getPosition(event);
        int positionFilter = getPositionOnFilter(event);

        if (position != -1) {

            arrayEvents.remove(position);
        }

        if (positionFilter != -1) {

            arrayEventsFilter.remove(positionFilter);
            notifyItemRemoved(position);
        }
    }


    /**
     * -------- Aditional Tools Section
     */

    private int setPosition(@NonNull final Event event) {

        String currentEvent = String.valueOf(event.IdEvent);
        long arraySize = arrayEvents.size();
        int Return = 0;

        if (!arrayEvents.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String compareEvent = String.valueOf(arrayEvents.get(position).IdEvent);
                int compareMarge = currentEvent.compareTo(compareEvent);

                if (compareMarge < 0) {

                    Return = position;
                    break;

                } else if (compareMarge == 0) {

                    Return = position + 1;

                } else if (compareMarge > 0) {

                    if (position == arraySize - 1) {

                        Return = position + 1;

                    } else {

                        String nextEvent = String.valueOf(arrayEvents.get(position).IdEvent);
                        int nextMarge = currentEvent.compareTo(nextEvent);

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

    public int getPosition(@NonNull Event event) {

        int position = -1, eventPosition = -1;
        String IdEvent = String.valueOf(event.IdEvent);

        for (final Event getEvent : arrayEvents) {

            position = position + 1;
            String getIdVehicle = String.valueOf(getEvent.IdEvent);

            if (IdEvent.equals(getIdVehicle)) {

                eventPosition = position;
                break;
            }
        }

        return eventPosition;
    }

    private int setPositionOnFilter(@NonNull final Event event) {

        String currentEvent = String.valueOf(event.IdEvent);
        long arraySize = arrayEventsFilter.size();
        int Return = 0;

        if (!arrayEventsFilter.isEmpty()) {

            for (int position = 0; position < arraySize; position ++) {

                String compareEvent = String.valueOf(arrayEventsFilter.get(position).IdEvent);
                int compareMarge = currentEvent.compareTo(compareEvent);

                if (compareMarge < 0) {

                    Return = position;
                    break;

                } else if (compareMarge == 0) {

                    Return = position + 1;

                } else if (compareMarge > 0) {

                    if (position == arraySize - 1) {

                        Return = position + 1;

                    } else {

                        String nextEvent = String.valueOf(arrayEventsFilter.get(position).IdEvent);
                        int nextMarge = currentEvent.compareTo(nextEvent);

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

    public int getPositionOnFilter(@NonNull final Event event) {

        int position = -1, eventPosition = -1;
        String hourEvent = String.valueOf(event.IdEvent);

        for (final Event getEvent : arrayEventsFilter) {

            position = position + 1;

            if (hourEvent.equals(String.valueOf(getEvent.IdEvent))) {

                eventPosition = position;
                break;
            }
        }

        return eventPosition;
    }


    /**
     * -------- Filter Section
     */

    @Override
    public Filter getFilter() {

        return filter;
    }

    public static class CustomFilter extends Filter {

        private final EventsAdapter listAdapter;

        private CustomFilter(EventsAdapter listAdapter) {

            super();

            this.listAdapter = listAdapter;
        }

        @Override
        protected FilterResults performFiltering(@NonNull CharSequence constraint) {

            arrayEventsFilter.clear();
            final FilterResults results = new FilterResults();

            if (constraint.length() == 0) {

                arrayEventsFilter.addAll(arrayEvents);

            } else {

                search = new Strings().toLowerCaseAndRemoveAccentMark(constraint.toString());

                for (final Event event : arrayEvents) {

                    boolean contains = containsSearch(event);

                    if (contains) {

                        arrayEventsFilter.add(event);
                    }
                }
            }

            results.values = arrayEventsFilter;
            results.count = arrayEventsFilter.size();

            return results;
        }

        public boolean containsSearch(@NonNull final Event event) {

            String typeEvent = new Strings().toLowerCaseAndRemoveAccentMark(event.getEventType());
            String hour = new Strings().toLowerCaseAndRemoveAccentMark(event.getHour());
            String vehicle = new Strings().toLowerCaseAndRemoveAccentMark(event.Vehicle);
            String manager = new Strings().toLowerCaseAndRemoveAccentMark(event.Manager);

            if (typeEvent.contains(search) || hour.contains(search) ||vehicle.contains(search) || manager.contains(search)) {

                return TRUE;

            } else {

                return FALSE;
            }
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            this.listAdapter.notifyDataSetChanged();
        }
    }
}

