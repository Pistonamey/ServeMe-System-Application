package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class PstOrderView extends AppCompatActivity implements View.OnClickListener {

    public static final String PSTINDEX = "PSTINDEX";

    private int pstListIndex;

    private ArrayList pstList;
    private String pstListIndexString;

    private int oneStarCount, twoStarCount, threeStarCount, fourStarCount, fiveStarCount;

    private double vendorScore;

    Button btn_rateOrder;

    TextView payOrderServType, payOrderJobDescView, payOrderServDateView, payOrderDateView, payOrderperhourView, payOrderbaserateView, payOrdertotal_serv_time, payOrderrest_time, payOrderwork_time, payOrderpayment_total;
    private String payOrderServTypeString, payOrderJobDescViewString, payOrderServDateViewString, payOrderDateViewString, payOrderperhourViewString, payOrderbaserateViewString, payOrdertotal_serv_timeString, payOrderrest_timeString, payOrderwork_timeString, payOrderpayment_totalString;
    private String orderID, vendorEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pst_order_view);


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
        btn_rateOrder = findViewById(R.id.btn_rateOrder);
        btn_rateOrder.setOnClickListener(this);



        pstListIndexString = intent.getStringExtra(PSTINDEX);



        pstListIndex = Integer.parseInt(pstListIndexString);
        pstListIndex = pstListIndex - 1;


        payOrderServTypeString = pst_ord.pstOrdlist.get(pstListIndex).servType;
        payOrderJobDescViewString = pst_ord.pstOrdlist.get(pstListIndex).jobDesc;
        payOrderServDateViewString = pst_ord.pstOrdlist.get(pstListIndex).servDate;
        payOrderperhourViewString = pst_ord.pstOrdlist.get(pstListIndex).per_hour;
        payOrderbaserateViewString = pst_ord.pstOrdlist.get(pstListIndex).base_pay;
        payOrdertotal_serv_timeString = pst_ord.pstOrdlist.get(pstListIndex).servTime;
        payOrderrest_timeString = pst_ord.pstOrdlist.get(pstListIndex).rest_time;
        payOrderwork_timeString = pst_ord.pstOrdlist.get(pstListIndex).work_time;
        payOrderpayment_totalString = pst_ord.pstOrdlist.get(pstListIndex).payment_total;
        orderID = pst_ord.pstOrdlist.get(pstListIndex).orderID;
        vendorEmail = pst_ord.pstOrdlist.get(pstListIndex).vendEmail;






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

    @Override
    public void onClick(View v) {

        switch(v.getId())
        {
            case R.id.btn_rateOrder:
                rateVendor();
                break;
        }

    }

    private void rateVendor() {


        Intent intent = new Intent(PstOrderView.this, RateOrder.class);
        intent.putExtra(RateOrder.ORDERIDSTATIC, orderID);
        intent.putExtra(RateOrder.VENDERMAILSTATIC, vendorEmail);



        startActivity(intent);
        overridePendingTransition(R.anim.from_right,R.anim.to_left);



    }
}