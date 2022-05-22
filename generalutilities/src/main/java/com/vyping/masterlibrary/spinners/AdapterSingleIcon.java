package com.vyping.masterlibrary.spinners;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vyping.masterlibrary.R;

import java.util.ArrayList;

public class AdapterSingleIcon extends ArrayAdapter<String> {

    private final Context context;
    private final ArrayList<Item> arrayItems;

    public AdapterSingleIcon(@NonNull Context context, ArrayList<Item> arrayItems) {

        super(context, R.layout.spinner_globe_singleicon);

        this.context = context;
        this.arrayItems = arrayItems;
    }

    private static class ViewHolder {

        ImageView Iv_Icon;
        TextView Tv_Text;
    }

    @Override
    public int getCount() {

        return arrayItems.size();
    }

    @Override
    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder mViewHolder = new ViewHolder();
        LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        int icon = arrayItems.get(position).icon;

        if (icon == 0) {

            convertView = mInflater.inflate(R.layout.spinner_globe_single, parent, false);
            mViewHolder.Tv_Text = (TextView) convertView.findViewById(R.id.tvName);

            mViewHolder.Tv_Text.setText(arrayItems.get(position).text);

        } else {


            convertView = mInflater.inflate(R.layout.spinner_globe_singleicon, parent, false);
            mViewHolder.Iv_Icon = (ImageView) convertView.findViewById(R.id.ivFlag);
            mViewHolder.Tv_Text = (TextView) convertView.findViewById(R.id.tvName);

            mViewHolder.Iv_Icon.setImageResource(arrayItems.get(position).icon);
            mViewHolder.Tv_Text.setText(arrayItems.get(position).text);
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return getView(position, convertView, parent);
    }

    public static class Item {

        public String text;
        public int icon;

        public Item(String text) {

            this.icon = 0;
            this.text = text;
        }

        public Item(int icon, String text) {

            this.icon = icon;
            this.text = text;
        }
    }
}
