package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Account_Profile extends AppCompatActivity {

    //for reference to current logged in user
    private FirebaseUser user;
    //for reference to database
    private DatabaseReference reference;

    //for id of currently logged in user
    //each user in Firebase database has unique id
    private String userID;

    private Button btn_logOut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_profile);

        Intent intent = getIntent();

        btn_logOut = findViewById(R.id.btn_logOut);

        //signing out button clicked, signing out out of firebase
        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                //redirect user to login
                startActivity(new Intent(Account_Profile.this, Cust_Login.class));
            }
        });

        //current firebase user
        user = FirebaseAuth.getInstance().getCurrentUser();
        //getRefernece is for getting the name of the specific reference collection
        reference = FirebaseDatabase.getInstance().getReference("Customers");
        //get unique id of logged in user
        userID = user.getUid();

        TextView greetingTextView = (TextView) findViewById(R.id.greeting);
        TextView fullNameTextView = (TextView) findViewById(R.id.name);
        TextView emailTextView = (TextView) findViewById(R.id.emailAddress);

        //getting reference and specifying child
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Customer customerProfile = snapshot.getValue(Customer.class);

                if(customerProfile != null)
                {
                    String fullname = customerProfile.name;
                    String email = customerProfile.email;


                    greetingTextView.setText("Welcome, " + fullname + "!" );
                    fullNameTextView.setText(fullname);
                    emailTextView.setText(email);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Account_Profile.this, "Something wrong happened", Toast.LENGTH_LONG).show();

            }
        });
    }
}