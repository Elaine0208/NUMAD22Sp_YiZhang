package edu.neu.madcourse.numad22sp_yizhang;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RviewHolder extends RecyclerView.ViewHolder {
    public TextView itemName;
    public TextView itemDesc;

    public RviewHolder(View itemView) {
        super(itemView);
        itemName = itemView.findViewById(R.id.item_name);
        itemDesc = itemView.findViewById(R.id.item_desc);
    }
}