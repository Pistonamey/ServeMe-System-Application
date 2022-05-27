package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.google.api.Distribution;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class VendorSearchResult extends AppCompatActivity {


    RecyclerView recyclerView;
    DatabaseReference database;

    SearchAdapter searchAdapter;
    ArrayList<Vendor> vendorList;



    private String vendNameSearch;






    public static final String SEARCH = "SEARCH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_search_result);

        Intent intent = getIntent();


        vendNameSearch = intent.getStringExtra(SEARCH);

        recyclerView = findViewById(R.id.searchVendorList);
        database = FirebaseDatabase.getInstance().getReference("Vendor");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        vendorList = new ArrayList<>();


        searchAdapter = new SearchAdapter(this, vendorList);
        recyclerView.setAdapter(searchAdapter);

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Vendor vendor = dataSnapshot.getValue(Vendor.class);
                    if(vendor.vendorName.toLowerCase().matches(vendNameSearch.toLowerCase()))
                    {
                        vendorList.add(vendor);
                    }

                    searchAdapter.notifyDataSetChanged();



                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });









    }
}