package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class sh_bids extends AppCompatActivity {




    RecyclerView recyclerView;

    DatabaseReference database;

    BidAdapter bidAdapter;
    public static ArrayList<Order> bidList = new ArrayList<Order>();




    private FirebaseUser customer;

    private DatabaseReference customerDBreference;


    private String customerID, customerEmail;







    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sh_bids);

        //TODO TEST THIS



        customer = FirebaseAuth.getInstance().getCurrentUser();

        customerDBreference = FirebaseDatabase.getInstance().getReference("Customers");

        customerID = customer.getUid();

        //getting reference and specifying child
        customerDBreference.child(customerID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Customer customerProfile = snapshot.getValue(Customer.class);

                if(customerProfile != null)
                {

                    customerEmail = customerProfile.email;


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(sh_bids.this, "Something wrong happened in sh_bids", Toast.LENGTH_LONG).show();

            }
        });



        recyclerView = findViewById(R.id.bidList);
        database = FirebaseDatabase.getInstance().getReference("Orders");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        bidList = new ArrayList<>();

        bidAdapter = new BidAdapter(this, bidList);
        recyclerView.setAdapter(bidAdapter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Order order = dataSnapshot.getValue(Order.class);

                    //TODO VERIFY MATCHES USER EMAIL

                    //TODO WHEN CLICKING ON BUTTON ACCESS USER PROFILE


                    if(order.customerAccepted == false && order.vendorAccepted == true && order.custEmail.equals(customerEmail))
                    {
                        bidList.add(order);
                    }
                }



                bidAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });












    }





}