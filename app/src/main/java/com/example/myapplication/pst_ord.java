package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class pst_ord extends AppCompatActivity {


    RecyclerView recyclerView;
    DatabaseReference database;
    PstOrderAdapter pstOrderAdapter;

    private FirebaseUser customer;

    private DatabaseReference customerDBreference;


    private String customerID, customerEmail;

    public static ArrayList<Order> pstOrdlist = new ArrayList<Order>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pst_ord);

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

                Toast.makeText(pst_ord.this, "Something wrong happened in pst_ord", Toast.LENGTH_LONG).show();

            }
        });




        recyclerView = findViewById(R.id.pstorderList);
        database = FirebaseDatabase.getInstance().getReference("Orders");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pstOrdlist = new ArrayList<>();
        pstOrderAdapter = new PstOrderAdapter(this, pstOrdlist);
        recyclerView.setAdapter(pstOrderAdapter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Order order = dataSnapshot.getValue(Order.class);
                    {
                        if(order.customerAccepted == true && order.vendorAccepted == true && order.orderStatus.equals("Complete") && order.custEmail.equals(customerEmail))
                        {
                            pstOrdlist.add(order);
                        }
                    }
                }

                pstOrderAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });




    }
}