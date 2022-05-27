package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
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

public class cur_ord extends AppCompatActivity {

    final MediaPlayer[] mp = new MediaPlayer[1];
    RecyclerView recyclerView;
    DatabaseReference database;
    CurAdapter curAdapter;

    public static ArrayList<Order> curList = new ArrayList<Order>();



    private FirebaseUser customer;

    private DatabaseReference customerDBreference;


    private String customerID, customerEmail;





    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cur_ord);

        customer = FirebaseAuth.getInstance().getCurrentUser();

        customerDBreference = FirebaseDatabase.getInstance().getReference("Customers");

        customerID = customer.getUid();

        Button back_button = (Button) findViewById(R.id.go_back);

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

                Toast.makeText(cur_ord.this, "Something wrong happened in cur_ord", Toast.LENGTH_LONG).show();

            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(cur_ord.this, My_Orders.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left,R.anim.to_right);
            }

        });

        Button sett = (Button) findViewById(R.id.settings);
        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(cur_ord.this, R.raw.but_click);
                    mp[0].setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            mp.start();
                        }
                    });

                    mp[0].setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.release();
                        }
                    });
                }
                Intent intent = new Intent(cur_ord.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });




        recyclerView = findViewById(R.id.curList);
        database = FirebaseDatabase.getInstance().getReference("Orders");

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        curList = new ArrayList<>();

        curAdapter = new CurAdapter(this, curList);
        recyclerView.setAdapter(curAdapter);


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {
                    Order order = dataSnapshot.getValue(Order.class);

                    if(order.customerAccepted == true && order.vendorAccepted == true && (order.orderStatus.equals("In Progress") || order.orderStatus.equals("Payment")) )// && order.custEmail.equals(customerEmail))
                    {
                        curList.add(order);
                    }
                }

                curAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }
}