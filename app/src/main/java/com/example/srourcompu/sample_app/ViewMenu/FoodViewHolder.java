package com.example.srourcompu.sample_app.ViewMenu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.srourcompu.sample_app.Interface.ItemClickListener;
import com.example.srourcompu.sample_app.R;

/**
 * Created by srourcompu on 4/21/2018.
 */

public class FoodViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView food_name;
    public ImageView food_image;

    private ItemClickListener ICL;

    public void setICL(ItemClickListener ICL) {
        this.ICL = ICL;
    }

    public FoodViewHolder(View itemView) {
        super(itemView);

        food_name = (TextView)itemView.findViewById(R.id.food_name);
        food_image = (ImageView) itemView.findViewById(R.id.food_image);

        itemView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        ICL.onClick(v, getAdapterPosition(), false);
    }
}
