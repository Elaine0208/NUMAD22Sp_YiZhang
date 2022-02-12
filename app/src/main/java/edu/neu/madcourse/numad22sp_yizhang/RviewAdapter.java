package edu.neu.madcourse.numad22sp_yizhang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RviewAdapter extends RecyclerView.Adapter<RviewHolder> {

    private final ArrayList<ItemCard> itemList;

    //Constructor
    public RviewAdapter(ArrayList<ItemCard> itemList) {
        this.itemList = itemList;
    }

    @Override
    public RviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card, parent, false);
        return new RviewHolder(view);
    }

    @Override
    public void onBindViewHolder(RviewHolder holder, int position) {
        ItemCard currentItem = itemList.get(position);

        holder.itemName.setText(currentItem.getItemName());
        holder.itemDesc.setText(currentItem.getItemDesc());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }
}