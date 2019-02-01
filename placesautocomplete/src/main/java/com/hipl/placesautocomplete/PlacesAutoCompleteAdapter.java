package com.hipl.placesautocomplete;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by karanjm on 24-09-2018.
 */

public class PlacesAutoCompleteAdapter extends RecyclerView.Adapter<PlacesAutoCompleteAdapter.MyViewHolder> {

    private ArrayList<CityData.PostOffice> countryList;

    /**
     * View holder class
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView countryText;

        public MyViewHolder(View view) {
            super(view);
            countryText = view.findViewById(R.id.textView);
        }
    }

    public PlacesAutoCompleteAdapter(ArrayList<CityData.PostOffice> countryList) {
        this.countryList = countryList;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CityData.PostOffice c = countryList.get(position);
        holder.countryText.setText(c.getName() + " - " + c.getTaluk());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_autocomplete, parent, false);
        return new MyViewHolder(v);
    }
}