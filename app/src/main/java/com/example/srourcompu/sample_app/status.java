package com.example.srourcompu.sample_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.srourcompu.sample_app.Access.Access;
import com.example.srourcompu.sample_app.Interface.ItemClickListener;
import com.example.srourcompu.sample_app.Model.MyMenu;
import com.example.srourcompu.sample_app.Model.Personal;
import com.example.srourcompu.sample_app.ViewMenu.MenuViewHolder;
import com.example.srourcompu.sample_app.ViewMenu.OrderViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class status extends AppCompatActivity {

    public RecyclerView recycle_status;
    public RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase db;
    DatabaseReference personal;

    FirebaseRecyclerAdapter<Personal, OrderViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);

        // Initializing Firebase
        db = FirebaseDatabase.getInstance();
        personal = db.getReference("Personal");

        recycle_status = (RecyclerView)findViewById(R.id.orders);
        recycle_status.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycle_status.setLayoutManager(layoutManager);

        loadingOrders(Access.accessUser.getID());
    }

    private void loadingOrders(String id) {
        adapter = new FirebaseRecyclerAdapter<Personal, OrderViewHolder>(Personal.class, R.layout.orders_layout,
                OrderViewHolder.class, personal.orderByChild("phone").equalTo(id)) {

            protected void populateViewHolder(OrderViewHolder viewHolder, Personal model, final int position) {
                viewHolder.order_item.setText(adapter.getRef(position).getKey());
                viewHolder.order_status.setText(Convert(model.getStatus()));
                viewHolder.order_phone.setText(model.getPhone_number());
                viewHolder.order_address.setText(model.getCustomer_address());

            }
        };
        recycle_status.setAdapter(adapter);
    }

    private String Convert(String status) {
        if(status.equals("0"))
        {
            return "Placed";
        }
        else if(status.equals("1"))
        {
            return "On the Way";
        }
        else
        {
            return "Transported";
        }
    }
}
