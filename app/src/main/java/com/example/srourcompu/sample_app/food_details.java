package com.example.srourcompu.sample_app;

import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.CollapsibleActionView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cepheuen.elegantnumberbutton.view.ElegantNumberButton;
import com.example.srourcompu.sample_app.Database.Database;
import com.example.srourcompu.sample_app.Model.Food;
import com.example.srourcompu.sample_app.Model.Order;
import com.example.srourcompu.sample_app.ViewMenu.FoodViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class food_details extends AppCompatActivity {

    TextView food_name, food_price, food_description;
    ImageView food_image;
    CollapsingToolbarLayout CTL;
    FloatingActionButton FAB;
    ElegantNumberButton ENB;

    String foodId = "";

    FirebaseDatabase db;
    DatabaseReference details;

    Food fd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_details);

        // Initializing Firebase
        db = FirebaseDatabase.getInstance();
        details = db.getReference("Food");

        ENB = (ElegantNumberButton)findViewById(R.id.ElegantNumberButton);
        FAB = (FloatingActionButton)findViewById(R.id.FloatingActionButton);

        FAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Database(getBaseContext()).addToCart(new Order(
                        foodId,
                        fd.getFoodName(),
                        fd.getFoodPrice(),
                        ENB.getNumber()
                ));
                Toast.makeText(food_details.this, "Added to Table :)", Toast.LENGTH_SHORT).show();
            }
        });

        food_description = (TextView)findViewById(R.id.food_description);
        food_name = (TextView)findViewById(R.id.food_name);
        food_price = (TextView)findViewById(R.id.food_price);
        food_image = (ImageView)findViewById(R.id.food_image);
        CTL = (CollapsingToolbarLayout)findViewById(R.id.CollapsingToolbarLayout);

        // Get foodId from the Intent
        if (getIntent() != null) {
            foodId = getIntent().getStringExtra("foodId");
        }
        if (!foodId.isEmpty() && foodId != null) {
            getDetails(foodId);
        }

    }

    private void getDetails(String foodId) {
        details.child(foodId).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                fd = dataSnapshot.getValue(Food.class);

                Picasso.with(getBaseContext()).load(fd.getFoodImage()).into(food_image);

                CTL.setTitle(fd.getFoodName());

                food_name.setText(fd.getFoodName());
                food_price.setText(fd.getFoodPrice());
                food_description.setText(fd.getFoodDescription());
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
