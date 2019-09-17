package com.example.srourcompu.sample_app.ViewMenu;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.srourcompu.sample_app.Interface.ItemClickListener;
import com.example.srourcompu.sample_app.R;

import org.w3c.dom.Text;

/**
 * Created by srourcompu on 4/19/2018.
 */

public class MenuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    public TextView name_view;
    public ImageView image_view;

    private ItemClickListener ICL;

    public MenuViewHolder(View itemView) {
        super(itemView);

        name_view = (TextView)itemView.findViewById(R.id.menu_name);
        image_view = (ImageView) itemView.findViewById(R.id.menu_image);

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
