package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class PayViewInfo extends AppCompatActivity implements View.OnClickListener {



    public static final String CUSTSERVTYPE = "CUSTSERVTYPE";
    public static final String JOBDESC = "JOBDESC";
    public static final String SERVDATE = "SERVDATE";
    public static final String SERVTIME = "SERVTIME";
    public static final String TOTALSERVTIME = "TOTALSERVTIME";
    public static final String RESTTIME = "RESTTIME";
    public static final String WORKTIME = "WORKTIME";
    public static final String PAYMENTTOTAL = "PAYMENTTOTAL";
    public static final String PERHOUR = "PERHOUR";
    public static final String BASEPRICE = "BASEPRICE";
    public static final String ORDERIDSTATICVIEW = "ORDERIDSTATICVIEW";

    Button paymentProceed;


    final MediaPlayer[] mp = new MediaPlayer[1];

    public static final String PAYINDEX = "PAYINDEX";

    private int listIndex;

    private ArrayList payList;


    TextView payOrderServType, payOrderJobDescView, payOrderServDateView, payOrderDateView, payOrderperhourView, payOrderbaserateView, payOrdertotal_serv_time, payOrderrest_time, payOrderwork_time, payOrderpayment_total;

    private String listIndexString, payOrderservTimeString, payOrderServTypeString, payOrderJobDescViewString, payOrderServDateViewString, payOrderperhourViewString, payOrderbaserateViewString, payOrdertotal_serv_timeString, payOrderrest_timeString, payOrderwork_timeString, payOrderpayment_totalString;


    private String orderID;

    private DatabaseReference orderDBreference;








    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_view_info);

        Intent intent = getIntent();

        orderDBreference = FirebaseDatabase.getInstance().getReference();

        payOrderServTypeString = intent.getStringExtra(CUSTSERVTYPE);
        payOrderJobDescViewString = intent.getStringExtra(JOBDESC);
        payOrderServDateViewString = intent.getStringExtra(SERVDATE);
        payOrderservTimeString = intent.getStringExtra(SERVTIME);
        payOrderperhourViewString = intent.getStringExtra(PERHOUR);
        payOrderbaserateViewString = intent.getStringExtra(BASEPRICE);
        payOrderwork_timeString = intent.getStringExtra(WORKTIME);
        payOrderrest_timeString = intent.getStringExtra(RESTTIME);
        payOrderpayment_totalString = intent.getStringExtra(PAYMENTTOTAL);
        orderID = intent.getStringExtra(ORDERIDSTATICVIEW);
        paymentProceed = findViewById(R.id.btn_paymentProceed);
        paymentProceed.setOnClickListener(this);






        payOrderServType = findViewById(R.id.payOrderServType);
        payOrderJobDescView = findViewById(R.id.payOrderJobDescView);
        payOrderServDateView = findViewById(R.id.payOrderServDateView);
        payOrderperhourView = findViewById(R.id.payOrderperhourView);
        payOrderbaserateView = findViewById(R.id.payOrderbaserateView);
        payOrdertotal_serv_time = findViewById(R.id.payOrdertotal_serv_time);
        payOrderrest_time = findViewById(R.id.payOrderrest_time);
        payOrderwork_time = findViewById(R.id.payOrderwork_time);
        payOrderpayment_total = findViewById(R.id.payOrderpayment_total);

        //listIndexString = intent.getStringExtra(PAYINDEX);

        //listIndex = Integer.parseInt(listIndexString);



        Button sett = (Button) findViewById(R.id.settings);
        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(PayViewInfo.this, R.raw.but_click);
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
                Intent intent = new Intent(PayViewInfo.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        Button back_button = (Button) findViewById(R.id.go_back);

        back_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(PayViewInfo.this, R.raw.but_click);
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
                Intent intent = new Intent(PayViewInfo.this, CurViewReq.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left,R.anim.to_right);
            }

        });


        /*payOrderServTypeString = PayRecyclerClass.payList.get(listIndex).servType;
        payOrderJobDescViewString = PayRecyclerClass.payList.get(listIndex).jobDesc;
        payOrderServDateViewString = PayRecyclerClass.payList.get(listIndex).servDate;
        payOrderperhourViewString = PayRecyclerClass.payList.get(listIndex).per_hour;
        payOrderbaserateViewString = PayRecyclerClass.payList.get(listIndex).base_pay;
        payOrdertotal_serv_timeString = CurVendViewReq.total_serv_time;
        payOrderrest_timeString = CurVendViewReq.rest_time;
        payOrderwork_timeString = CurVendViewReq.work_time;
        payOrderpayment_totalString = CurVendViewReq.payment_total;*/

        payOrderServType.setText(payOrderServTypeString);
        payOrderJobDescView.setText(payOrderJobDescViewString);
        payOrderServDateView.setText(payOrderServDateViewString);
        payOrderbaserateView.setText(payOrderbaserateViewString);
        payOrdertotal_serv_time.setText(payOrderservTimeString);
        payOrderperhourView.setText(payOrderperhourViewString);
        payOrderrest_time.setText(payOrderrest_timeString);
        payOrderwork_time.setText(payOrderwork_timeString);
        payOrderpayment_total.setText(payOrderpayment_totalString);




        /*payOrderServType.setText(payOrderbaserateViewString);
        payOrderJobDescView.setText(payOrderJobDescViewString);
        payOrderServDateView.setText(payOrderServDateViewString);
        payOrderperhourView.setText(payOrderperhourViewString);
        payOrderbaserateView.setText(payOrderbaserateViewString);
        payOrdertotal_serv_time.setText(payOrdertotal_serv_timeString);
        payOrderrest_time.setText(payOrderrest_timeString);
        payOrderwork_time.setText(payOrderwork_timeString);
        payOrderpayment_total.setText(payOrderpayment_totalString);*/





    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_paymentProceed:
                proceedToPayment();
                break;
        }
    }

    private void proceedToPayment() {


        //go to firebase documentation for reading data serValue to set how to fully update with Orders class all values.


        Intent intent = new Intent(PayViewInfo.this, PaymentPage.class);



        intent.putExtra(PaymentPage.PAYMENTPAGETOTAL, payOrderpayment_totalString);
        intent.putExtra(PaymentPage.ORDERIDSTATIC, orderID);

        startActivity(intent);


    }
}