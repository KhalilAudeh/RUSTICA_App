package com.example.srourcompu.sample_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;

import com.example.srourcompu.sample_app.Interface.ItemClickListener;
import com.example.srourcompu.sample_app.Model.Food;
import com.example.srourcompu.sample_app.ViewMenu.FoodViewHolder;
import com.example.srourcompu.sample_app.ViewMenu.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

public class FoodList extends AppCompatActivity {

    RecyclerView recycle_food;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase db;
    DatabaseReference food;

    String foodMenuId = "";

    FirebaseRecyclerAdapter<Food, FoodViewHolder> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list);

        // Initializing Firebase
        db = FirebaseDatabase.getInstance();
        food = db.getReference("Food");

        // Loading the MyMenu
        recycle_food = (RecyclerView) findViewById(R.id.recycler_food);
        recycle_food.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycle_food.setLayoutManager(layoutManager);

        // Get the Intent
        if (getIntent() != null) {
            foodMenuId = getIntent().getStringExtra("foodMenuId");
        }
        if (!foodMenuId.isEmpty() && foodMenuId != null) {
            loadingFood(foodMenuId);
        }
    }

    private void loadingFood(String foodMenuId) {
        adapter = new FirebaseRecyclerAdapter<Food, FoodViewHolder>(Food.class, R.layout.food_item,
                FoodViewHolder.class, food.orderByChild("foodMenuId").equalTo(foodMenuId)) {

            @Override
            protected void populateViewHolder(FoodViewHolder viewHolder, Food model, final int position) {
                viewHolder.food_name.setText(model.getFoodName());
                Picasso.with(getBaseContext()).load(model.getFoodImage()).into(viewHolder.food_image);

                final Food food_item = model;
                viewHolder.setICL(new ItemClickListener() {
                    @Override
                    public void onClick(View v, int index, boolean bool) {
                        // Starting New Activity
                        Intent details = new Intent(FoodList.this, food_details.class);
                        details.putExtra("foodId", adapter.getRef(position).getKey());
                        startActivity(details);
                    }
                });
            }
        };
        recycle_food.setAdapter(adapter);
    }
}
