package com.anjali.flightbooking;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.zip.Inflater;


public class SpinnerAdapter extends ArrayAdapter<String> {
    ArrayList<String> cities ;

    public SpinnerAdapter(@NonNull Context context, int resource , ArrayList<String> cities) {
        super(context, resource , cities);
        this.cities=cities;
    }


    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = super.getDropDownView(position, convertView, parent);
        TextView tv =  view.findViewById(R.id.textView);
        tv.setText(cities.get(position));
        return view;
    }
}
