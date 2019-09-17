package com.example.srourcompu.sample_app;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.srourcompu.sample_app.Access.Access;
import com.example.srourcompu.sample_app.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Main2Activity extends AppCompatActivity {

    EditText clientID, clientPassword, clientName;
    Button SignInButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        clientID = (MaterialEditText)findViewById(R.id.clientID);
        clientPassword = (MaterialEditText)findViewById(R.id.clientPassword);
        clientName = (MaterialEditText)findViewById(R.id.clientName);

        SignInButton = (Button)findViewById(R.id.SignInButton);

        // Initializing Firebase
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference user_table = db.getReference("User");

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final ProgressDialog wait = new ProgressDialog(Main2Activity.this);
                wait.setMessage("Please Wait while Loading.....");
                wait.show();

                user_table.addValueEventListener(new ValueEventListener() {

                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Checking the case where the user does not exist in Database
                        if (dataSnapshot.child(clientID.getText().toString()).exists()) {

                            // First, Dismiss Progress Dialog
                            wait.dismiss();

                            // Get Info from User Table in the database
                            User user = dataSnapshot.child(clientID.getText().toString()).getValue(User.class);
                            user.setID(clientID.getText().toString());
                            if(user == null){
                                Toast.makeText(Main2Activity.this, "USER NULL", Toast.LENGTH_SHORT).show();
                            }
                            if (user.getClientPassword() != null && user.getClientPassword().equalsIgnoreCase(clientPassword.getText().toString()) &&
                                    user.getClientName() != null && user.getClientName().equalsIgnoreCase(clientName.getText().toString())) {

                                Toast.makeText(Main2Activity.this, "Welcome! Sign In Successfully :)", Toast.LENGTH_SHORT).show();

                                Intent home_intent = new Intent(Main2Activity.this, Home.class);
                                Access.accessUser = user;
                                startActivity(home_intent);
                                finish();

                            } else {
                                Toast.makeText(Main2Activity.this, "SORRY! Your Sign In Failed :(", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            wait.dismiss();
                            Toast.makeText(Main2Activity.this, "SORRY !! You don't have an account", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
    }
}
