package com.vyping.business.utilities.models.products;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.vyping.business.R;
import com.vyping.masterlibrary.Common.LogCat;

public class SizesAdapter extends ArrayAdapter<String> {

    String[] spinnerTitles;
    int[] spinnerImages;
    String[] spinnerPopulation;
    Context mContext;

    public SizesAdapter(@NonNull Context context, String[] titles, int[] images, String[] population) {

        super(context, R.layout.custom_spinner_row);

        this.spinnerTitles = titles;
        this.spinnerImages = images;
        this.spinnerPopulation = population;
        this.mContext = context;
    }

    private static class ViewHolder {

        ImageView mFlag;
        TextView mName;
        TextView mPopulation;
    }

    @Override  @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        new LogCat("getView");

       ViewHolder mViewHolder = new ViewHolder();

        if (convertView == null) {

            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(R.layout.custom_spinner_row, parent, false);
            mViewHolder.mFlag = (ImageView) convertView.findViewById(R.id.ivFlag);
            mViewHolder.mName = (TextView) convertView.findViewById(R.id.tvName);
            mViewHolder.mPopulation = (TextView) convertView.findViewById(R.id.tvPopulation);
            convertView.setTag(mViewHolder);

        } else {

            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.mFlag.setImageResource(spinnerImages[position]);
        mViewHolder.mName.setText(spinnerTitles[position]);
        mViewHolder.mPopulation.setText(spinnerPopulation[position]);

        return convertView;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return getView(position, convertView, parent);
    }

    @Override
    public int getCount() {

        return spinnerTitles.length;
    }

}
