package com.example.srourcompu.sample_app;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.srourcompu.sample_app.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Main3Activity extends AppCompatActivity {

    MaterialEditText clientID, clientName, clientPassword;
    Button SignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        clientID = (MaterialEditText)findViewById(R.id.clientID);
        clientName = (MaterialEditText)findViewById(R.id.clientName);
        clientPassword = (MaterialEditText)findViewById(R.id.clientPassword);

        SignUpButton = (Button)findViewById(R.id.SignUpButton);

        // Initializing Firebase
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference user_table = db.getReference("User");

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog wait = new ProgressDialog(Main3Activity.this);
                wait.setMessage("Please Wait while Loading.....");
                wait.show();

                user_table.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        // Checking if the user ID exists
                        if(dataSnapshot.child(clientID.getText().toString()).exists())
                        {
                            wait.dismiss();
                            Toast.makeText(Main3Activity.this, "This User ID is already registered", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            wait.dismiss();
                            User user = new User(clientName.getText().toString(), clientPassword.getText().toString());
                            user_table.child(clientID.getText().toString()).setValue(user);
                            Toast.makeText(Main3Activity.this, "Congrats !! You have been registered. ", Toast.LENGTH_SHORT).show();
                            finish();
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
