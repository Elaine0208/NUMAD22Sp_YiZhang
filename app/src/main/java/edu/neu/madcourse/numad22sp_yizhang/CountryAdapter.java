package edu.neu.madcourse.numad22sp_yizhang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CountryAdapter extends RecyclerView.Adapter<CountryHolder> {

    private final ArrayList<CountryCard> countryList;

    //Constructor
    public CountryAdapter(ArrayList<CountryCard> countryList) {
        this.countryList = countryList;
    }

    @Override
    public CountryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.country_card, parent, false);
        return new CountryHolder(view);
    }

    @Override
    public void onBindViewHolder(CountryHolder holder, int position) {
        CountryCard currentItem = countryList.get(position);

        holder.Name.setText(currentItem.getName());
        holder.Country.setText(currentItem.getCountry());
        holder.Prob.setText(currentItem.getProb());
    }

    @Override
    public int getItemCount() {
        return countryList.size();
    }
}