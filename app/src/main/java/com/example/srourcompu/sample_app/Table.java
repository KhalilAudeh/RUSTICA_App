package com.example.srourcompu.sample_app;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.srourcompu.sample_app.Access.Access;
import com.example.srourcompu.sample_app.Database.Database;
import com.example.srourcompu.sample_app.Model.Order;
import com.example.srourcompu.sample_app.Model.Personal;
import com.example.srourcompu.sample_app.Model.User;
import com.example.srourcompu.sample_app.ViewMenu.TableAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import info.hoang8f.widget.FButton;

public class Table extends AppCompatActivity {

    RecyclerView recycle_table;
    RecyclerView.LayoutManager layoutManager;

    FirebaseDatabase db;
    DatabaseReference personal;

    TextView totalPrice;
    FButton orderButton;

    List<Order> table = new ArrayList<>();
    TableAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table);

        // Initializing Firebase
        db = FirebaseDatabase.getInstance();
        personal = db.getReference("Personal");

        recycle_table = (RecyclerView)findViewById(R.id.listTable);
        recycle_table.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recycle_table.setLayoutManager(layoutManager);

        totalPrice = (TextView)findViewById(R.id.totalPrice);
        orderButton = (FButton)findViewById(R.id.orderButton);

        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Alert();
            }
        });

        loadingTable();
    }

    private void Alert() {

        AlertDialog.Builder AD = new AlertDialog.Builder(Table.this);
        AD.setTitle("Generate Your Request Please :)");
        AD.setMessage("Please, provide your address: ");

        final EditText Addr = new EditText(Table.this);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
        );

        Addr.setLayoutParams(layoutParams);
        AD.setView(Addr);
        AD.setIcon(R.drawable.ic_shopping_basket_black_24dp);

        AD.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Personal request = new Personal(
                        Access.accessUser.getID(),
                        Access.accessUser.getClientName(),
                        Addr.getText().toString(),
                        totalPrice.getText().toString(),
                        table
                );

                personal.child(String.valueOf(System.currentTimeMillis())).setValue(request);

                new Database(getBaseContext()).clean();
                Toast.makeText(Table.this, "Thank You, Order Saved :)", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        AD.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AD.show();
    }

    private void loadingTable() {
        table = new Database(this).getCarts();
        adapter = new TableAdapter(table, this);
        recycle_table.setAdapter(adapter);

        int totalP = 0;
        for (Order order:table)
            totalP += (Integer.parseInt(order.getPrice())) * (Integer.parseInt(order.getQuantity()));
        Locale locale = new Locale("ar", "LB");
        NumberFormat NF = NumberFormat.getCurrencyInstance(locale);

        totalPrice.setText(NF.format(totalP));
    }
}
