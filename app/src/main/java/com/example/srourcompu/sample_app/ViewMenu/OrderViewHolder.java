package com.example.srourcompu.sample_app.ViewMenu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.srourcompu.sample_app.Interface.ItemClickListener;
import com.example.srourcompu.sample_app.R;

/**
 * Created by srourcompu on 4/25/2018.
 */

public class OrderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView order_item, order_status, order_phone, order_address;
    private ItemClickListener ICL;

    public OrderViewHolder(View itemView) {
        super(itemView);

        order_item = (TextView)itemView.findViewById(R.id.order_item);
        order_status = (TextView)itemView.findViewById(R.id.order_status);
        order_phone = (TextView)itemView.findViewById(R.id.order_phone);
        order_address = (TextView)itemView.findViewById(R.id.order_address);

        itemView.setOnClickListener(this);
    }

    public void setICL(ItemClickListener ICL) {
        this.ICL = ICL;
    }

    @Override
    public void onClick(View v) {
        ICL.onClick(v, getAdapterPosition(), false);
    }
}
