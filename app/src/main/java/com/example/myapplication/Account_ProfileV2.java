package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Account_ProfileV2 extends AppCompatActivity {



    //for reference to current logged in user
    private FirebaseUser user;
    //for reference to database
    private DatabaseReference reference;

    //for id of currently logged in user
    //each user in Firebase database has unique id
    private String userID;


    TextView tv_custEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_profile_v2);





        tv_custEmail = findViewById(R.id.tv_custEmail);

        //current firebase user
        user = FirebaseAuth.getInstance().getCurrentUser();
        //getRefernece is for getting the name of the specific reference collection
        reference = FirebaseDatabase.getInstance().getReference("Customers");
        //get unique id of logged in user
        userID = user.getUid();


        //getting reference and specifying child
        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Customer customerProfile = snapshot.getValue(Customer.class);

                if(customerProfile != null)
                {

                    String email = customerProfile.email;

                    tv_custEmail.setText(email);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(Account_ProfileV2.this, "Something wrong happened", Toast.LENGTH_LONG).show();

            }
        });



    }
}