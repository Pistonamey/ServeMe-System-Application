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

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class CurViewReq extends AppCompatActivity implements View.OnClickListener {


    public static final String CURRENTINDEX = "CURRENTINDEX";


    private DatabaseReference orderDBreference;

    final MediaPlayer[] mp = new MediaPlayer[1];



    TextView custServType, jobDesc, servDate, servTime, servType, basePrice, baseRate;

    Button btn_accept, btn_reject, btn_proceed, btn_cancel, btn_change;



    //for reference to database

    private FirebaseUser customer;

    private DatabaseReference customerDBreference;


    private String customerID, customerEmail, listIndexString;

    private int listIndex;

    private ArrayList curList;


    private String custServTypeString, jobDescString, servDateString, servTimeString, servTypeString, baseRateString, basePayString, orderStatus;

    private String total_serv_time, rest_time, work_time, payment_total, orderID;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cur_view_req);

        Intent intent = getIntent();
        custServType = findViewById(R.id.curServType);
        jobDesc = findViewById(R.id.curJobDescView);
        servDate = findViewById(R.id.curServDateView);
        servTime = findViewById(R.id.curServTimeView);
        //servType = findViewById(R.id.curServTypeView);
        basePrice = findViewById(R.id.curBasePrice);
        baseRate = findViewById(R.id.curBaseRate);
        btn_proceed = findViewById(R.id.proceed);
        btn_cancel = findViewById(R.id.cancel_order);
        btn_change = findViewById(R.id.change_order);
        btn_cancel.setOnClickListener(this);
        btn_proceed.setOnClickListener(this);
        btn_change.setOnClickListener(this);

        Button sett = (Button) findViewById(R.id.settings);
        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(CurViewReq.this, R.raw.but_click);
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
                Intent intent = new Intent(CurViewReq.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        Button back_button = (Button) findViewById(R.id.go_back);

        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(CurViewReq.this, R.raw.but_click);
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
                Intent intent = new Intent(CurViewReq.this, cur_ord.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left,R.anim.to_right);
            }

        });



        orderDBreference = FirebaseDatabase.getInstance().getReference();

        //list with order info


        listIndexString = intent.getStringExtra(CURRENTINDEX);

        listIndex = Integer.parseInt(listIndexString);
        listIndex = listIndex - 1;


        curList = cur_ord.curList;


        custServTypeString = cur_ord.curList.get(listIndex).servType;
        jobDescString = cur_ord.curList.get(listIndex).jobDesc;
        servDateString = cur_ord.curList.get(listIndex).servDate;
        servTimeString = cur_ord.curList.get(listIndex).servTime;
        //servTypeString = cur_ord.curList.get(listIndex).servType;
        baseRateString = cur_ord.curList.get(listIndex).per_hour;
        basePayString = cur_ord.curList.get(listIndex).base_pay;
        orderID = cur_ord.curList.get(listIndex).orderID;
        orderStatus = cur_ord.curList.get(listIndex).orderStatus;

        total_serv_time = cur_ord.curList.get(listIndex).total_serv_time;
        rest_time = cur_ord.curList.get(listIndex).rest_time;
        work_time = cur_ord.curList.get(listIndex).work_time;
        payment_total = cur_ord.curList.get(listIndex).payment_total;

        custServType.setText(custServTypeString);
        jobDesc.setText(jobDescString);
        servDate.setText(servDateString);
        servTime.setText(servTimeString);
        //servType.setText(servTypeString);
        basePrice.setText(basePayString);
        baseRate.setText(baseRateString);










    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.proceed:
                proceedFunc();
                break;
            case R.id.cancel_order:
                cancelOrder();
                break;
            case R.id.change_order:
                changeOrder();
                break;

        }
    }

    private void changeOrder() {
        if(orderStatus.equals("Vendor Started"))
        {
            Toast.makeText(CurViewReq.this, "Order has been started and cannot be changed.", Toast.LENGTH_LONG).show();
        }
        else
        {
            if(MainActivity.so) {
                mp[0] = MediaPlayer.create(CurViewReq.this, R.raw.but_click);
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
            Intent intent = new Intent(CurViewReq.this, ChangeOrder.class);
            intent.putExtra(ChangeOrder.ORDERIDSTATIC, orderID);
            startActivity(intent);
            overridePendingTransition(R.anim.from_right,R.anim.to_left);
        }
    }

    private void cancelOrder() {

        if(orderStatus.equals("Vendor Started"))
        {
            if(MainActivity.so) {
                mp[0] = MediaPlayer.create(CurViewReq.this, R.raw.but_click);
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
            Toast.makeText(CurViewReq.this, "Order has been started and cannot be cancelled.", Toast.LENGTH_LONG).show();
        }
        else
        {
            orderDBreference.child("Orders").child(orderID).removeValue();
        }


    }

    private void proceedFunc() {
        if(MainActivity.so) {
            mp[0] = MediaPlayer.create(CurViewReq.this, R.raw.but_click);
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

        Intent intent = new Intent(CurViewReq.this, PayViewInfo.class);

        intent.putExtra(PayViewInfo.CUSTSERVTYPE, custServTypeString);
        intent.putExtra(PayViewInfo.JOBDESC, jobDescString);
        intent.putExtra(PayViewInfo.SERVDATE, servDateString);
        intent.putExtra(PayViewInfo.SERVTIME, servTimeString);
        intent.putExtra(PayViewInfo.TOTALSERVTIME, total_serv_time);
        intent.putExtra(PayViewInfo.RESTTIME, rest_time);
        intent.putExtra(PayViewInfo.WORKTIME, work_time);
        intent.putExtra(PayViewInfo.PAYMENTTOTAL, payment_total);
        intent.putExtra(PayViewInfo.PERHOUR, baseRateString);
        intent.putExtra(PayViewInfo.BASEPRICE, basePayString);
        intent.putExtra(PayViewInfo.ORDERIDSTATICVIEW, orderID);




        startActivity(intent);
        overridePendingTransition(R.anim.from_right,R.anim.to_left);


    }
}