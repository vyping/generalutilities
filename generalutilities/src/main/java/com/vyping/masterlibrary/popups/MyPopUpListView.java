package com.vyping.masterlibrary.popups;

import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.paris.Paris;
import com.vyping.masterlibrary.Common.MyArrayList;
import com.vyping.masterlibrary.R;

import java.util.ArrayList;

public class MyPopUpListView extends MyPopUp {

    private Adapter adapter;

    private ListView Lv_ListView;


    //----- Setup - Section-----//

    @SuppressLint("InflateParams")
    public MyPopUpListView(View anchorView, @NonNull final ArrayList<String> arrayItems) {

        super(anchorView);

        SetParameters(arrayItems);
        SetPopUpViews();
    }

    @SuppressLint("InflateParams")
    public MyPopUpListView(View anchorView, @NonNull final String[] listItems) {

        super(anchorView);

        SetParameters(listItems);
        SetPopUpViews();
    }

    private void SetParameters(@NonNull final ArrayList<String> arrayItems) {

     //   adapter = new Adapter(anchorView.getContext(), arrayItems);
    }

    private void SetParameters(@NonNull final String[] listItems) {

        ArrayList arrayItems = new MyArrayList().listToArray(listItems);

     //   adapter = new Adapter(anchorView.getContext(), arrayItems);
    }

    private void SetPopUpViews() {

        int styleList = R.style.DialogListView;
        int attrList = R.attr.dialogListView;

      //  setContainerOrientation(LinearLayout.VERTICAL);

      //  Lv_ListView = new ListView(anchorView.getContext(), null, attrList, styleList);
        Lv_ListView.setAdapter(adapter);
        Lv_ListView.setOnItemClickListener(onItemClickListener);
        Paris.style(Lv_ListView).apply(styleList);

     //   AddCustomView(Lv_ListView);
    }


    /*----- Listeners -----*/

    private final AdapterView.OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        }

        public void DummyVoid() {
        }
    };


    /*----- Adapter -----*/

    public static class Adapter extends BaseAdapter {

        private final LayoutInflater inflater;
        private final ArrayList<Item> arrayItems;


        /**
         * -------- SetUp Section
         */

        public Adapter(final Context context, @NonNull final ArrayList<String> arrayItems) {

            this.inflater = LayoutInflater.from(context);
            this.arrayItems = new ArrayList<>();

            for (String item : arrayItems) {

                Item Item = new Item(item);

                this.arrayItems.add(Item);
            }
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

            holder.Tv_Item.setText(arrayItems.get(position).getItem());

            return view;
        }

        @Override
        public Item getItem(final int position) {

            return arrayItems.get(position);
        }

        @Override
        public long getItemId(int position) {

            return position;
        }

        @Override
        public int getCount() {

            return arrayItems.size();
        }

        public String getSelectedItem(final int position) {

            return arrayItems.get(position).getItem();
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
