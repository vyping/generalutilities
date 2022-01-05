package com.vyping.masterlibrary.Dialogs;

import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_SINGLE;
import static com.vyping.masterlibrary.Common.Definitions.BUTTONS_THREE;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Common.GeneralTools;
import com.vyping.masterlibrary.R;

import java.util.ArrayList;
import java.util.Locale;

public abstract class DialogListView {

    private Context context;
    private final CreateDialog createDialog;
    private Adapter adapter;

    private SearchView Sv_SearchView;
    private ListView Lv_ListView;

    private String prevQuery, query;


    /*----- SetUp -----*/

    public DialogListView(@NonNull Context context, final int parameters, @NonNull final ArrayList<String> arrayItems, final String Query) {

        setParameters(context, arrayItems, Query);

        createDialog = new CreateDialog(context, parameters, TRUE) {

            @Override
            protected void SetDialog(Dialog dialog) {

                setViewsOnLayout(dialog);

                Sv_SearchView.setQuery(query, TRUE);
                Sv_SearchView.setOnQueryTextListener(onQueryTextListener);

                Lv_ListView.setAdapter(adapter);
                Lv_ListView.setOnItemClickListener(onItemClickListener);

                dialog.show();
            }

            @Override
            protected void RefreshClick() {

                query = "";

                Sv_SearchView.setQuery(query, TRUE);

                setOptionButtons();
            }

            @Override
            protected void PositiveClick() {

                ItemSelected(query);
            }
        };

        setOptionButtons();
    }

    private void setOptionButtons() {

        if (query.equals(prevQuery)) {

            adapter.filter("#$%&*");
            createDialog.setModeButtons(BUTTONS_SINGLE);

        } else {

            if (adapter.containsItem(query)) {

                adapter.filter("#$%&*");
                createDialog.setModeButtons(BUTTONS_THREE);

                new GeneralTools().hideSoftInput(context, Sv_SearchView);

            } else {

                adapter.filter(query);
                createDialog.setModeButtons(BUTTONS_SINGLE);
            }
        }
    }


    /*----- Process -----*/

    public void setSelection(String text) {

        query = text;

        Sv_SearchView.setQuery(query, TRUE);
    }

    public String getSelection() {

        return String.valueOf(Sv_SearchView.getQuery());
    }


    /*----- Listeners -----*/

    private final SearchView.OnQueryTextListener onQueryTextListener = new SearchView.OnQueryTextListener() {

        @Override
        public boolean onQueryTextSubmit(String newQuery) {

            query = newQuery;

            setOptionButtons();

            return false;
        }

        @Override
        public boolean onQueryTextChange(String newQuery) {

            query = newQuery;

            setOptionButtons();

            return false;
        }
    };

    private final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            query = adapter.getSelectedItem(position);

            Sv_SearchView.setQuery(query, FALSE);

            setOptionButtons();
        }

        public void DummyVoid() {}
    };


    /*----- Tools -----*/

    private void setParameters(@NonNull Context context, @NonNull final ArrayList<String> arrayItems, final String query) {

        this.context = context;
        this.query = query;

        prevQuery = query;

        adapter = new Adapter(context, arrayItems, query);
    }

    private void setViewsOnLayout(@NonNull Dialog dialog) {

        int styleSearch = R.style.DialogSearchView;
        int attrSearch = R.attr.dialogSearchView;
        int styleList = R.style.DialogListView;
        int attrList = R.attr.dialogListView;

        LinearLayout layout = dialog.findViewById(R.id.Ll_Custom_MainContainer);
        layout.setOrientation(LinearLayout.VERTICAL);

        Sv_SearchView = new SearchView(context, null, attrSearch, styleSearch);
        Lv_ListView = new ListView(context, null, attrList, styleList);

        Paris.style(Sv_SearchView).apply(styleSearch);
        Paris.style(Lv_ListView).apply(styleList);

        layout.addView(Sv_SearchView);
        layout.addView(Lv_ListView);
    }


    /*----- Return -----*/

    protected abstract void ItemSelected(String Selection);


    /*----- Adapter -----*/

    public static class Adapter extends BaseAdapter {

        private final LayoutInflater inflater;
        private final ArrayList<Item> arrayItems, arrayItemsfiltered;


        /**
         * -------- SetUp Section
         */

        public Adapter(final Context context, @NonNull final ArrayList<String> arrayItems, String query) {

            this.inflater = LayoutInflater.from(context);
            this.arrayItems = new ArrayList<>();
            this.arrayItemsfiltered = new ArrayList<>();

            for (String item : arrayItems) {

                Item Item = new Adapter.Item(item);

                this.arrayItems.add(Item);
                this.arrayItemsfiltered.add(Item);
            }

            filter(query);
        }

        public static class ViewHolder {

            TextView Tv_Item;
        }

        @SuppressLint("InflateParams")
        public View getView(final int position, View view, ViewGroup parent) {

            final ViewHolder holder;

            if (view == null) {

                holder = new ViewHolder();
                view = inflater.inflate(R.layout.listview_item, null, false);

                holder.Tv_Item = view.findViewById(R.id.name);

                view.setTag(holder);

            } else {

                holder = (ViewHolder) view.getTag();
            }

            holder.Tv_Item.setText(arrayItemsfiltered.get(position).getItem());

            return view;
        }

        @Override
        public Item getItem(final int position) {

            return arrayItemsfiltered.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public int getCount() {

            return arrayItemsfiltered.size();
        }

        public String getSelectedItem(final int position) {

            return arrayItemsfiltered.get(position).getItem();
        }

        public boolean containsItem(final String query) {

            boolean contains = FALSE;

            for (Item item : arrayItems) {

                String Item = item.getItem();

                if (query.equals(Item)) {

                    contains = TRUE;
                    break;
                }
            }

            return contains;
        }


        /**
         * -------- Filter Section
         */

        public void filter(String textFilter) {

            textFilter = textFilter.toLowerCase(Locale.getDefault());
            arrayItemsfiltered.clear();

            if (textFilter.length() == 0) {

                arrayItemsfiltered.addAll(arrayItems);

            } else {

                for (Item rowItem : arrayItems) {

                    if (String.valueOf(rowItem.getItem()).toLowerCase(Locale.getDefault()).contains(textFilter)) {

                        arrayItemsfiltered.add(rowItem);
                    }
                }
            }

            notifyDataSetChanged();
        }


        /**
         * -------- Receiver Class Section
         */

        public static class Item {

            private final String item;

            public Item(final String Item) {

                item = Item;
            }

            public String getItem() {

                return item;
            }
        }
    }
}
