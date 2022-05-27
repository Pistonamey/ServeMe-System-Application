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

public class cur_vend_ord extends AppCompatActivity {


    RecyclerView recyclerView;

    DatabaseReference database;

    CurVendAdapter curVendAdapter;


    //for reference to database
    private DatabaseReference orderDBreference;

    private FirebaseUser vendor;

    private DatabaseReference vendorDBreference;


    private String vendorID, vendorEmail;



    public static ArrayList<Order> curVendList = new ArrayList<Order>();
    //old code
    //public static ArrayList<Order> curVendList = new ArrayList<Order>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cur_vend_ord);

        vendor = FirebaseAuth.getInstance().getCurrentUser();

        vendorDBreference = FirebaseDatabase.getInstance().getReference("Vendor");

        vendorID = vendor.getUid();

        //getting reference and specifying child
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

                Toast.makeText(cur_vend_ord.this, "Something wrong happened in req_menu", Toast.LENGTH_LONG).show();

            }
        });


        recyclerView = findViewById(R.id.curVendList);
        database = FirebaseDatabase.getInstance().getReference("Orders");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        curVendList = new ArrayList<>();
        curVendAdapter = new CurVendAdapter(this, curVendList);
        recyclerView.setAdapter(curVendAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Order order = dataSnapshot.getValue(Order.class);



                    //if vendorAccept = true, customerAccept = true, and vendEmail matches email of vendor
                    if(order.customerAccepted == true && order.vendorAccepted == true && order.vendEmail.equals(vendorEmail))
                    {
                        curVendList.add(order);
                    }




                }

                curVendAdapter.notifyDataSetChanged();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}