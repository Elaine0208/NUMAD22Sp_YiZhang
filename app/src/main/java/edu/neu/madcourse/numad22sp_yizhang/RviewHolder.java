package edu.neu.madcourse.numad22sp_yizhang;

import android.content.Intent;
import android.net.Uri;
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

        itemDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = itemDesc.toString();
                if (!url.startsWith("http://")) {
                    url = "http://" + url;
                }
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse(url));
                view.getContext().startActivity(intent);
            }
        });
    }
}