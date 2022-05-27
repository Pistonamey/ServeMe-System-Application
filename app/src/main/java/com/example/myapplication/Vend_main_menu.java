package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Vend_main_menu extends AppCompatActivity {



    public static final String VENDOREMAILSTATIC = "VENDOREMAILSTATIC";

    private String email;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vend_main_menu);

        Intent intent = getIntent();


        email = intent.getStringExtra(VENDOREMAILSTATIC);

        final MediaPlayer[] mp = new MediaPlayer[1];
        Button req1 = (Button) findViewById(R.id.req_log);
        Button req2 = (Button) findViewById(R.id.req_tx);
        Button myOrders1 = (Button) findViewById(R.id.mo_log);
        Button myOrders2 = (Button) findViewById(R.id.mo_tx);

        Button sett = (Button) findViewById(R.id.settings);
        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Vend_main_menu.this, R.raw.but_click);
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
                Intent intent = new Intent(Vend_main_menu.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });


        req1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Vend_main_menu.this, req_menu.class);
                intent.putExtra(req_menu.VENDOREMAILSTATICREQ, email);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        req2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Vend_main_menu.this, req_menu.class);
                intent.putExtra(req_menu.VENDOREMAILSTATICREQ, email);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });



        myOrders1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Vend_main_menu.this, Vend_My_Orders.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);

            }
        });

        myOrders2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent(Vend_main_menu.this, Vend_My_Orders.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);

            }
        });





    }
}