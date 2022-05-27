package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class past_vend_ord extends AppCompatActivity {





    RecyclerView recyclerView;

    DatabaseReference database;

    PstVendAdapter pstVendAdapter;

    private FirebaseUser vendor;


    private DatabaseReference vendorDBreference;


    private String vendorID, vendorEmail;

    public static ArrayList<Order> pstVendlist = new ArrayList<Order>();







    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_vend_ord);

        vendor = FirebaseAuth.getInstance().getCurrentUser();

        vendorDBreference = FirebaseDatabase.getInstance().getReference("Vendor");

        vendorID = vendor.getUid();


        recyclerView = findViewById(R.id.pstVendList);
        database = FirebaseDatabase.getInstance().getReference("Orders");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        pstVendlist = new ArrayList<>();
        pstVendAdapter = new PstVendAdapter(this, pstVendlist);
        recyclerView.setAdapter(pstVendAdapter);


        vendorDBreference.child(vendorID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Vendor vendorProfile = snapshot.getValue(Vendor.class);

                if(vendorProfile != null)
                {

                    vendorEmail = vendorProfile.vendorEmail;


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(past_vend_ord.this, "Something wrong happened in past_vend_ord", Toast.LENGTH_LONG).show();

            }
        });


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Order order = dataSnapshot.getValue(Order.class);
                    if(order.customerAccepted == true && order.vendorAccepted == true && order.orderStatus.equals("Complete") && order.vendEmail.equals(vendorEmail))
                    {
                        pstVendlist.add(order);
                    }
                }

                pstVendAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}