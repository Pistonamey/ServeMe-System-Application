package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

public class PstVendView extends AppCompatActivity {

    public static final String PSTVENDINDEX = "PSTVENDINDEX";

    private int pstListIndex;

    private ArrayList pstVendList;
    private String pstListIndexString;

    TextView payOrderServType, payOrderJobDescView, payOrderServDateView, payOrderDateView, payOrderperhourView, payOrderbaserateView, payOrdertotal_serv_time, payOrderrest_time, payOrderwork_time, payOrderpayment_total;
    private String payOrderServTypeString, payOrderJobDescViewString, payOrderServDateViewString, payOrderDateViewString, payOrderperhourViewString, payOrderbaserateViewString, payOrdertotal_serv_timeString, payOrderrest_timeString, payOrderwork_timeString, payOrderpayment_totalString;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pst_vend_view);


        Intent intent = getIntent();



        payOrderServType = findViewById(R.id.payOrderServType);
        payOrderJobDescView = findViewById(R.id.payOrderJobDescView);
        payOrderServDateView = findViewById(R.id.payOrderServDateView);
        payOrderperhourView = findViewById(R.id.payOrderperhourView);
        payOrderbaserateView = findViewById(R.id.payOrderbaserateView);
        payOrdertotal_serv_time = findViewById(R.id.payOrdertotal_serv_time);
        payOrderrest_time = findViewById(R.id.payOrderrest_time);
        payOrderwork_time = findViewById(R.id.payOrderwork_time);
        payOrderpayment_total = findViewById(R.id.payOrderpayment_total);



        pstListIndexString = intent.getStringExtra(PSTVENDINDEX);



        pstListIndex = Integer.parseInt(pstListIndexString);
        pstListIndex = pstListIndex - 1;


        payOrderServTypeString = past_vend_ord.pstVendlist.get(pstListIndex).servType;
        payOrderJobDescViewString = past_vend_ord.pstVendlist.get(pstListIndex).jobDesc;
        payOrderServDateViewString = past_vend_ord.pstVendlist.get(pstListIndex).servDate;
        payOrderperhourViewString = past_vend_ord.pstVendlist.get(pstListIndex).per_hour;
        payOrderbaserateViewString = past_vend_ord.pstVendlist.get(pstListIndex).base_pay;
        payOrdertotal_serv_timeString = past_vend_ord.pstVendlist.get(pstListIndex).servTime;
        payOrderrest_timeString = past_vend_ord.pstVendlist.get(pstListIndex).rest_time;
        payOrderwork_timeString = past_vend_ord.pstVendlist.get(pstListIndex).work_time;
        payOrderpayment_totalString = past_vend_ord.pstVendlist.get(pstListIndex).payment_total;



        payOrderServType.setText(payOrderServTypeString);
        payOrderJobDescView.setText(payOrderJobDescViewString);
        payOrderServDateView.setText(payOrderServDateViewString);
        payOrderbaserateView.setText(payOrderbaserateViewString);
        payOrdertotal_serv_time.setText(payOrdertotal_serv_timeString);
        payOrderperhourView.setText(payOrderperhourViewString);
        payOrderrest_time.setText(payOrderrest_timeString);
        payOrderwork_time.setText(payOrderwork_timeString);
        payOrderpayment_total.setText(payOrderpayment_totalString);






    }
}