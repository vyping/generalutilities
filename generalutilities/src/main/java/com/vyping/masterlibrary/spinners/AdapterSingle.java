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

public class AdapterSingle extends ArrayAdapter<String> {

    private Context mContext;
    private ArrayList<String> spinnerTitles;

    public AdapterSingle(@NonNull Context context, ArrayList<String> titles) {

        super(context, R.layout.spinner_globe_single);

        this.mContext = context;

        this.spinnerTitles = titles;
    }

    private static class ViewHolder {

        ImageView mFlag;
        TextView mName;
    }

    @Override
    public int getCount() {

        return spinnerTitles.size();
    }

    @Override
    @NonNull
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        ViewHolder mViewHolder = new ViewHolder();

        if (convertView == null) {

            LayoutInflater mInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            convertView = mInflater.inflate(R.layout.spinner_globe_single, parent, false);
            mViewHolder.mFlag = (ImageView) convertView.findViewById(R.id.ivFlag);
            mViewHolder.mName = (TextView) convertView.findViewById(R.id.tvName);
            convertView.setTag(mViewHolder);

        } else {

            mViewHolder = (ViewHolder) convertView.getTag();
        }

        mViewHolder.mName.setText(spinnerTitles.get(position));

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        return getView(position, convertView, parent);
    }
}
