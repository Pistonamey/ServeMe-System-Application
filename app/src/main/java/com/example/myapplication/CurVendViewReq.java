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

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CurVendViewReq extends AppCompatActivity{


    //for reference to database

    //for reference to database
    private DatabaseReference orderDBreference;

    private FirebaseUser vendor;

    private DatabaseReference vendorDBreference;


    private String vendorID, vendorEmail;


    public static final String CURVENDINDEX = "CURVENDINDEX ";


    private ArrayList curVendViewlist;


    TextView custEmail, jobDesc, servDate, servTime, servType, basePay, baseRate;

    public static long starttime, pause_start, resume_start, finishtime;

    public static String total_serv_time, rest_time,work_time,payment_total, orderID; // pass these four to  customers procedd btton


    private String listIndexString, custEmailString, jobDescString, servDateString, servTimeString, servTypeString, vendorType, basePayString, baseRateString;
    private int listIndex;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cur_vend_view_req);


        Button start = (Button) findViewById(R.id.str);
        Button pause = (Button) findViewById(R.id.pse);
        Button resume = (Button) findViewById(R.id.res);
        Button finish = (Button) findViewById(R.id.finish);
        TextView total = (TextView) findViewById(R.id.ttl_time);
        TextView payt = (TextView) findViewById(R.id.payl);
        TextView rest = (TextView) findViewById(R.id.resl);
        TextView tlt = (TextView) findViewById(R.id.tltl);
        Button fnsh = (Button) findViewById(R.id.vend_log_out);

        vendor = FirebaseAuth.getInstance().getCurrentUser();

        vendorDBreference = FirebaseDatabase.getInstance().getReference("Vendor");

        vendorID = vendor.getUid();

        //getting reference and specifying child
        vendorDBreference.child(vendorID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Vendor vendorProfile = snapshot.getValue(Vendor.class);

                if (vendorProfile != null) {

                    vendorEmail = vendorProfile.vendorEmail;


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(CurVendViewReq.this, "Something wrong happened in cur_vend_view_menu", Toast.LENGTH_LONG).show();

            }
        });


        Intent intent = getIntent();
        custEmail = findViewById(R.id.curVendEmail);
        jobDesc = findViewById(R.id.curVendjobDesc);
        servDate = findViewById(R.id.curVendServDateView);
        servTime = findViewById(R.id.curVendServTimeView);
        servType = findViewById(R.id.curVendServTypeView);
        basePay = findViewById(R.id.curVendBasePriceView);
        baseRate = findViewById(R.id.curVendBaseRateView);
        //btn_submit.setOnClickListener(this);

        orderDBreference = FirebaseDatabase.getInstance().getReference();


        //list with order info


        listIndexString = intent.getStringExtra(CURVENDINDEX);

        listIndex = Integer.parseInt(listIndexString);
        listIndex = listIndex - 1;


        curVendViewlist = cur_vend_ord.curVendList;




        custEmailString = cur_vend_ord.curVendList.get(listIndex).custEmail;

        jobDescString = cur_vend_ord.curVendList.get(listIndex).jobDesc;
        servDateString = cur_vend_ord.curVendList.get(listIndex).servDate;
        servTimeString = cur_vend_ord.curVendList.get(listIndex).servTime;
        servTypeString = cur_vend_ord.curVendList.get(listIndex).servType;
        basePayString = cur_vend_ord.curVendList.get(listIndex).base_pay;
        baseRateString = cur_vend_ord.curVendList.get(listIndex).per_hour;
        orderID = cur_vend_ord.curVendList.get(listIndex).orderID;

        custEmail.setText(custEmailString);
        jobDesc.setText(jobDescString);
        servDate.setText(servDateString);
        servTime.setText(servTimeString);
        servType.setText(servTypeString);
        basePay.setText(basePayString);
        baseRate.setText(baseRateString);

        orderDBreference = FirebaseDatabase.getInstance().getReference();


        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                starttime = System.currentTimeMillis();
                orderDBreference.child("Orders").child(orderID).child("orderStatus").setValue("Vendor Started", new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if(error == null)
                        {

                        }
                        else
                        {
                            Toast.makeText(CurVendViewReq.this, "Order failed to update in CurVendViewReq", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        pause.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                pause_start = System.currentTimeMillis();
            }
        });

        resume.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                resume_start = System.currentTimeMillis();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finishtime = System.currentTimeMillis();
                long bs_pay =  Long.parseLong(basePayString), ph_pay=Long.parseLong(baseRateString);
                long tttt = ((pause_start - starttime) + (finishtime-resume_start))/1000;
                work_time = String.valueOf(tttt);
                long ps = (resume_start-pause_start)/1000;
                rest_time = String.valueOf(ps);
                long pay = bs_pay + (ph_pay*tttt);
                payment_total = String.valueOf(pay);
                long tl = (finishtime-starttime)/1000;
                total_serv_time = String.valueOf(tl);

                tlt.setText("Total Service time: " + total_serv_time);
                rest.setText("Total rest time : "+rest_time);
                total.setText("Total Work time : "+work_time);
                payt.setText("Total amount to be paid : "+payment_total);

                orderDBreference.child("Orders").child(orderID).child("orderStatus").setValue("Payment", new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if(error == null)
                        {

                        }
                        else
                        {
                            Toast.makeText(CurVendViewReq.this, "Order failed to update in CurVendViewReq", Toast.LENGTH_LONG).show();
                        }
                    }
                });


                orderDBreference.child("Orders").child(orderID).child("total_serv_time").setValue(total_serv_time, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if(error == null)
                        {

                        }
                        else
                        {
                            Toast.makeText(CurVendViewReq.this, "Order failed to update in CurVendViewReq", Toast.LENGTH_LONG).show();
                        }
                    }
                });


                orderDBreference.child("Orders").child(orderID).child("rest_time").setValue(rest_time, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if(error == null)
                        {

                        }
                        else
                        {
                            Toast.makeText(CurVendViewReq.this, "Order failed to update in CurVendViewReq", Toast.LENGTH_LONG).show();
                        }
                    }
                });


                orderDBreference.child("Orders").child(orderID).child("work_time").setValue(work_time, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if(error == null)
                        {

                        }
                        else
                        {
                            Toast.makeText(CurVendViewReq.this, "Order failed to update in CurVendViewReq", Toast.LENGTH_LONG).show();
                        }
                    }
                });


                orderDBreference.child("Orders").child(orderID).child("payment_total").setValue(payment_total, new DatabaseReference.CompletionListener() {
                    @Override
                    public void onComplete(@Nullable DatabaseError error, @NonNull DatabaseReference ref) {
                        if(error == null)
                        {
                                Intent intent = new Intent(CurVendViewReq.this,Vend_main_menu.class);
                        }
                        else
                        {
                            Toast.makeText(CurVendViewReq.this, "Order failed to update in CurVendViewReq", Toast.LENGTH_LONG).show();
                        }
                    }
                });


                fnsh.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        Intent intent = new Intent(CurVendViewReq.this, Vend_main_menu.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.from_right,R.anim.to_left);
                    }
                });



            }
        });


    }
}