package edu.neu.madcourse.numad22sp_yizhang;

import android.view.View;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class CountryHolder extends RecyclerView.ViewHolder {
    public TextView Name;
    public TextView Country;
    public TextView Prob;

    public CountryHolder(View itemView) {
        super(itemView);
        Name = itemView.findViewById(R.id.first_name);
        Country = itemView.findViewById(R.id.country);
        Prob = itemView.findViewById(R.id.probability);
    }
}