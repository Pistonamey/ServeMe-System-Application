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
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.protobuf.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class req_menu extends AppCompatActivity {


    public static final String VENDOREMAILSTATICREQ = "VENDOREMAILSTATICREQ";


    private String email;






    RecyclerView recyclerView;


    DatabaseReference database;

    MyAdapter myAdapter;

    public static ArrayList<Order> list = new ArrayList<Order>();


    //for reference to database

    private FirebaseUser vendor;

    private DatabaseReference vendorDBreference;


    private String vendorID, uniqueID;


    private String vendorEmailIntent;










    //for id of currently logged in user
    //each user in Firebase database has unique id
    private String vendorType;

    private TextView vendorTypeView;


    final MediaPlayer[] mp = new MediaPlayer[1];



    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_menu2);


        Intent intent = getIntent();

        vendorEmailIntent = intent.getStringExtra(VENDOREMAILSTATICREQ);

        vendor = FirebaseAuth.getInstance().getCurrentUser();

        vendorDBreference = FirebaseDatabase.getInstance().getReference("Vendor");


       /* vendorDBreference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {

                    Vendor vendor = dataSnapshot.getValue(Vendor.class);
                    if(vendor.vendorEmail.equals(vendorEmailIntent))
                    {
                        vendorType = vendor.vendorServiceType;
                    }




                }






            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/





        //COMMENT OUT HERE IF NOT WORK




        vendorID = vendor.getUid();

        vendorDBreference.child(vendorID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Vendor vendorProfile = snapshot.getValue(Vendor.class);

                if(vendorProfile != null)
                {

                    vendorType = vendorProfile.vendorServiceType;


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(req_menu.this, "Something wrong happened in req_menu", Toast.LENGTH_LONG).show();

            }
        });
        //uncomment this






        recyclerView = findViewById(R.id.orderList);
        database = FirebaseDatabase.getInstance().getReference("Orders");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        list = new ArrayList<>();



        myAdapter = new MyAdapter(this, list);
        recyclerView.setAdapter(myAdapter);







        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot dataSnapshot : snapshot.getChildren())
                {

                    Order order = dataSnapshot.getValue(Order.class);
                    uniqueID = dataSnapshot.getKey();




                    if(order.customerAccepted == false && order.vendorAccepted == false && order.servType.equals(vendorType)) //order.servType.matches(vendorType))
                    {






                        //here I can edit the order to add its id with dataSnaphot.getKey()



                        list.add(order);
                    }

                }

                myAdapter.notifyDataSetChanged();




            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });






       /* vendorTypeView = findViewById(R.id.vendorTypeView);


        //getting reference and specifying child
        database.child(vendorID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Vendor vendorProfile = snapshot.getValue(Vendor.class);

                if(vendorProfile != null)
                {
                    //String for vendors service type. aka electrical, appliance, etc.
                    String vendorType = vendorProfile.vendorServiceType;


                    vendorTypeView.setText(vendorType);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(req_menu.this, "Something wrong happened. req_menu", Toast.LENGTH_LONG).show();

            }
        });
*/

      Button sett = (Button) findViewById(R.id.settings);
        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(req_menu.this, R.raw.but_click);
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
                Intent intent = new Intent(req_menu.this, Vend_sett.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        Button back = (Button) findViewById(R.id.go_back);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(req_menu.this, R.raw.but_click);
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
                Intent intent = new Intent(req_menu.this, Vend_main_menu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left,R.anim.to_right);
            }

        });



    }
}