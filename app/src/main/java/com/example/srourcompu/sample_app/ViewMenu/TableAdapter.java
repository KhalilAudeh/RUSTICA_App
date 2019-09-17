package com.example.srourcompu.sample_app.ViewMenu;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.example.srourcompu.sample_app.Interface.ItemClickListener;
import com.example.srourcompu.sample_app.Model.Order;
import com.example.srourcompu.sample_app.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by srourcompu on 4/24/2018.
 */

class TableViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    public TextView each_item_name, each_item_price;
    public ImageView count;

    private ItemClickListener ICL;

    public void setEach_item_name(TextView each_item_name) {
        this.each_item_name = each_item_name;
    }

    public TableViewHolder(View itemView) {
        super(itemView);

        each_item_name = (TextView)itemView.findViewById(R.id.each_item_name);
        each_item_price = (TextView)itemView.findViewById(R.id.each_item_price);
        count = (ImageView) itemView.findViewById(R.id.count);

    }

    @Override
    public void onClick(View v) {

    }
}

public class TableAdapter extends RecyclerView.Adapter<TableViewHolder>{

    private List<Order> list = new ArrayList<>();
    private Context context;

    public TableAdapter(List<Order> list, Context context){
        this.list = list;
        this.context = context;
    }

    @Override
    public TableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View v = inflater.inflate(R.layout.table_layout, parent, false);
        return new TableViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TableViewHolder holder, int position) {
        TextDrawable TD = TextDrawable.builder().buildRound(""+list.get(position).getQuantity(), Color.GREEN);
        holder.count.setImageDrawable(TD);

        Locale locale = new Locale("ar", "LB");
        NumberFormat NF = NumberFormat.getCurrencyInstance(locale);
        int price = (Integer.parseInt(list.get(position).getPrice())) * (Integer.parseInt(list.get(position).getQuantity()));
        holder.each_item_price.setText(NF.format(price));
        holder.each_item_name.setText(list.get(position).getFoodName());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
