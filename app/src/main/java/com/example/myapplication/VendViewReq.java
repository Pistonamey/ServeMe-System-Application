package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class VendViewReq extends AppCompatActivity implements View.OnClickListener {





    //for reference to database
    private DatabaseReference orderDBreference;

    private FirebaseUser vendor;

    private DatabaseReference vendorDBreference;


    private String vendorID, vendorEmail;






    final MediaPlayer[] mp = new MediaPlayer[1];




    public static final String INDEX = "INDEX";


    private ArrayList list;


    TextView custEmail, jobDesc, servDate, servTime, servType;
    EditText et_basePay, et_baseRate;
    Button btn_submit;




    private String listIndexString, custEmailString, jobDescString, servDateString, servTimeString, servTypeString, vendorType;
    private int listIndex;

    private DatabaseReference database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vend_view_req);

        Intent intent = getIntent();
        custEmail = findViewById(R.id.custEmailView);
        jobDesc = findViewById(R.id.jobDescView);
        servDate = findViewById(R.id.servDateView);
        servTime = findViewById(R.id.servTimeView);
        servType = findViewById(R.id.servTypeView);
        et_basePay = findViewById(R.id.et_basePay);
        et_baseRate = findViewById(R.id.et_baseRate);
        btn_submit = findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);


        orderDBreference = FirebaseDatabase.getInstance().getReference();

        listIndexString = intent.getStringExtra(INDEX);

        listIndex = Integer.parseInt(listIndexString);
        listIndex = listIndex - 1;




        custEmailString = req_menu.list.get(listIndex).custEmail;
        jobDescString = req_menu.list.get(listIndex).jobDesc;
        servDateString = req_menu.list.get(listIndex).servDate;
        servTimeString = req_menu.list.get(listIndex).servTime;
        servTypeString = req_menu.list.get(listIndex).servType;



        custEmail.setText(custEmailString);
        jobDesc.setText(jobDescString);
        servDate.setText(servDateString);
        servTime.setText(servTimeString);
        servType.setText(servTypeString);

        Button sett = (Button) findViewById(R.id.settings);
        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(VendViewReq.this, R.raw.but_click);
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
                Intent intent = new Intent(VendViewReq.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        Button back_button = (Button) findViewById(R.id.go_back);

        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(VendViewReq.this, R.raw.but_click);
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
                Intent intent = new Intent(VendViewReq.this, req_menu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left,R.anim.to_right);
            }

        });








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

                Toast.makeText(VendViewReq.this, "Something wrong happened in req_menu", Toast.LENGTH_LONG).show();

            }
        });












        //list with order info

















    }

    @Override
    public void onClick(View v) {


        switch(v.getId())
        {
            case R.id.btn_submit:
                vendorSubmit();
                Intent intent = new Intent(VendViewReq.this, Vend_main_menu.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left,R.anim.to_right);
                break;
        }

    }

    private void vendorSubmit() {

        String orderID = req_menu.list.get(listIndex).orderID;

        String base_pay = et_basePay.getText().toString().trim();
        String base_rate = et_baseRate.getText().toString().trim();

        if(base_pay.isEmpty())
        {
            et_basePay.setError("Full name is required");
            et_basePay.requestFocus();
            return;
        }

        if(base_rate.isEmpty())
        {
            et_baseRate.setError("Email is required");
            et_baseRate.requestFocus();
            return;
        }

        // TODO add isNumeric test cases
        // TODO ENSURE ONLY SERVICE TYPES THAT MATCH VENDORS SERVICE TYPE GET SHOWN




        //go to firebase documentation for reading data serValue to set how to fully update with Orders class all values.




        database = FirebaseDatabase.getInstance().getReference("Orders");


        //update order base_pay according to vendor's input
        orderDBreference.child("Orders").child(orderID).child("base_pay").setValue(base_pay, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null)
                {

                }
                else
                {
                    Toast.makeText(VendViewReq.this, "Order failed to update in VendViewReq", Toast.LENGTH_LONG).show();
                }
            }
        });

        //update order per hour
        orderDBreference.child("Orders").child(orderID).child("per_hour").setValue(base_rate, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null)
                {

                }
                else
                {
                    Toast.makeText(VendViewReq.this, "Order failed to update in VendViewReq", Toast.LENGTH_LONG).show();
                }
            }
        });


        //update vendorAccepted
        orderDBreference.child("Orders").child(orderID).child("vendorAccepted").setValue(true, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null)
                {

                }
                else
                {
                    Toast.makeText(VendViewReq.this, "Order failed to update in VendViewReq", Toast.LENGTH_LONG).show();
                }
            }
        });


        //update orders with vendor's email
        orderDBreference.child("Orders").child(orderID).child("vendEmail").setValue(vendorEmail, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null)
                {

                }
                else
                {
                    Toast.makeText(VendViewReq.this, "Order failed to update in VendViewReq", Toast.LENGTH_LONG).show();
                }
            }
        });



        Toast.makeText(VendViewReq.this, "Bid has been sent", Toast.LENGTH_LONG).show();

        //redirect to Login layout
        Intent intent = new Intent(VendViewReq.this, Vend_main_menu.class);
        startActivity(intent);
        overridePendingTransition(R.anim.from_right,R.anim.to_left);





























    }
}