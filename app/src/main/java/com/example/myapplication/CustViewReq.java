package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CustViewReq extends AppCompatActivity implements View.OnClickListener {


    public static final String CUSTOMERINDEX = "CUSTOMERINDEX";






    TextView custServType, jobDesc, servDate, servTime, servType, basePrice, baseRate;

    Button btn_accept, btn_reject;



    //for reference to database
    private DatabaseReference orderDBreference;

    private FirebaseUser customer;

    private DatabaseReference customerDBreference;


    private String customerID, customerEmail, listIndexString;

    private int listIndex;

    private ArrayList bidList;


    private String custServTypeString, jobDescString, servDateString, servTimeString, servTypeString, baseRateString, basePayString;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cust_view_req);

        Intent intent = getIntent();
        custServType = findViewById(R.id.custServType);
        jobDesc = findViewById(R.id.custJobDescView);
        servDate = findViewById(R.id.custServDateView);
        servTime = findViewById(R.id.custServTimeView);
        servType = findViewById(R.id.custServTypeView);
        basePrice = findViewById(R.id.custBasePrice);
        baseRate = findViewById(R.id.custBaseRate);
        btn_accept = findViewById(R.id.btn_accept);
        btn_accept.setOnClickListener(this);
        btn_reject = findViewById(R.id.btn_reject);
        btn_reject.setOnClickListener(this);


        orderDBreference = FirebaseDatabase.getInstance().getReference();

        //list with order info


        listIndexString = intent.getStringExtra(CUSTOMERINDEX);

        listIndex = Integer.parseInt(listIndexString);
        listIndex = listIndex - 1;


        bidList = sh_bids.bidList;


        custServTypeString = sh_bids.bidList.get(listIndex).servType;
        jobDescString = sh_bids.bidList.get(listIndex).jobDesc;
        servDateString = sh_bids.bidList.get(listIndex).servDate;
        servTimeString = sh_bids.bidList.get(listIndex).servTime;
        servTypeString = sh_bids.bidList.get(listIndex).servType;
        baseRateString = sh_bids.bidList.get(listIndex).per_hour;
        basePayString = sh_bids.bidList.get(listIndex).base_pay;

        custServType.setText(custServTypeString);
        jobDesc.setText(jobDescString);
        servDate.setText(servDateString);
        servTime.setText(servTimeString);
        servType.setText(servTypeString);
        basePrice.setText(basePayString);
        baseRate.setText(baseRateString);










    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_reject:
                rejectBid();
                break;
            case R.id.btn_accept:
                acceptBid();
                break;
        }
    }

    //if customer accepts the bid
    private void acceptBid() {

        String orderID = sh_bids.bidList.get(listIndex).orderID;
        //update order base_pay according to vendor's input
        orderDBreference.child("Orders").child(orderID).child("customerAccepted").setValue(true, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null)
                {

                }
                else
                {
                    Toast.makeText(CustViewReq.this, "Order failed to update in CustViewReq", Toast.LENGTH_LONG).show();
                }
            }
        });

        //update order base_pay according to vendor's input
        orderDBreference.child("Orders").child(orderID).child("orderStatus").setValue("In Progress", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null)
                {

                }
                else
                {
                    Toast.makeText(CustViewReq.this, "Order failed to update in CustViewReq", Toast.LENGTH_LONG).show();
                }
            }
        });


        Toast.makeText(CustViewReq.this, "Bid has been accepted", Toast.LENGTH_LONG).show();

        //redirect to Login layout
        Intent intent = new Intent(CustViewReq.this, Cust_main_menu.class);
        startActivity(intent);
        overridePendingTransition(R.anim.from_right,R.anim.to_left);





    }

    private void rejectBid() {


        String orderID = sh_bids.bidList.get(listIndex).orderID;


        //update order base_pay according to vendor's input
        orderDBreference.child("Orders").child(orderID).child("base_pay").setValue("", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null)
                {

                }
                else
                {
                    Toast.makeText(CustViewReq.this, "Order failed to update in CustViewReq", Toast.LENGTH_LONG).show();
                }
            }
        });


        //update order per hour
        orderDBreference.child("Orders").child(orderID).child("per_hour").setValue("", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null)
                {

                }
                else
                {
                    Toast.makeText(CustViewReq.this, "Order failed to update in CustViewReq", Toast.LENGTH_LONG).show();
                }
            }
        });

        //update vendorAccepted
        orderDBreference.child("Orders").child(orderID).child("vendorAccepted").setValue(false, new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null)
                {

                }
                else
                {
                    Toast.makeText(CustViewReq.this, "Order failed to update in CustViewReq", Toast.LENGTH_LONG).show();
                }
            }
        });


        //update vendorAccepted
        orderDBreference.child("Orders").child(orderID).child("vendorEmail").setValue("", new DatabaseReference.CompletionListener() {
            @Override
            public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                if(error == null)
                {

                }
                else
                {
                    Toast.makeText(CustViewReq.this, "Order failed to update in CustViewReq", Toast.LENGTH_LONG).show();
                }
            }
        });




        Toast.makeText(CustViewReq.this, "Bid has been rejected", Toast.LENGTH_LONG).show();

        //redirect to Login layout
        Intent intent = new Intent(CustViewReq.this, Cust_main_menu.class);
        startActivity(intent);
        overridePendingTransition(R.anim.from_right,R.anim.to_left);

























    }
}