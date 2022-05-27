package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Vend_My_Orders extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vend_my_orders);

        Intent intent = getIntent();

        Button v_cur1 = (Button) findViewById(R.id.vco_log);
        Button v_cur2 = (Button) findViewById(R.id.vco_tx);

        Button pst_ord1 = (Button) findViewById(R.id.vpo_log);
        Button pst_ord2 = (Button) findViewById(R.id.vpo_tx);






        v_cur1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Vend_My_Orders.this, cur_vend_ord.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        v_cur2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Vend_My_Orders.this, cur_vend_ord.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);



            }

        });



        pst_ord1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Vend_My_Orders.this, past_vend_ord.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);



            }

        });


        pst_ord2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Vend_My_Orders.this, past_vend_ord.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);



            }

        });

    }


}