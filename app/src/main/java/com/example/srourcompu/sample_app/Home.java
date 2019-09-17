package com.example.srourcompu.sample_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.srourcompu.sample_app.Access.Access;
import com.example.srourcompu.sample_app.Interface.ItemClickListener;
import com.example.srourcompu.sample_app.Model.MyMenu;
import com.example.srourcompu.sample_app.ViewMenu.MenuViewHolder;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;


public class Home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    FirebaseDatabase db;
    DatabaseReference menu;

    TextView user_name;

    RecyclerView recycle_menu;
    RecyclerView.LayoutManager layoutManager;

    FirebaseRecyclerAdapter<MyMenu, MenuViewHolder> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("MENU");
        setSupportActionBar(toolbar);

        // Initializing Firebase
        db = FirebaseDatabase.getInstance();
        menu = db.getReference("Menu");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent table_intent = new Intent(Home.this, Table.class);
                startActivity(table_intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Setting a name for the entering user
        View header_view = navigationView.getHeaderView(0);
        user_name = (TextView)header_view.findViewById(R.id.user_name);
        user_name.setText(Access.accessUser.getClientName());

        // Loading the MyMenu
        recycle_menu = (RecyclerView)findViewById(R.id.recycler_menu);
        recycle_menu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycle_menu.setLayoutManager(layoutManager);

        loadingMenu();
    }

    private void loadingMenu() {
        adapter = new FirebaseRecyclerAdapter<MyMenu, MenuViewHolder>(MyMenu.class, R.layout.menu_item, MenuViewHolder.class, menu) {

            protected void populateViewHolder(MenuViewHolder viewHolder, MyMenu model, final int position) {
                viewHolder.name_view.setText(model.getFoodName());
                Picasso.with(getBaseContext()).load(model.getFoodImage()).into(viewHolder.image_view);

                final MyMenu click_item = model;
                viewHolder.setICL(new ItemClickListener() {
                    @Override
                    public void onClick(View v, int index, boolean bool) {
                        // Get foodMenuId and send it to the new Activity
                        Intent food_intent = new Intent(Home.this, FoodList.class);
                        // We have to get the Key of such an item because foodMenuId is a key attribute
                        food_intent.putExtra("foodMenuId", adapter.getRef(position).getKey());
                        startActivity(food_intent);
                    }
                });
            }
        };
        recycle_menu.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_menu) {
            // Handle the menu action
        } else if (id == R.id.nav_shopping) {
            Intent shop_intent = new Intent(Home.this, status.class);
            startActivity(shop_intent);

        } else if (id == R.id.nav_order) {
            Intent in = new Intent(Home.this, Table.class);
            startActivity(in);

        } else if (id == R.id.nav_sign_out) {
            Intent sign_out_intent = new Intent(Home.this, Main2Activity.class);
            sign_out_intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(sign_out_intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
