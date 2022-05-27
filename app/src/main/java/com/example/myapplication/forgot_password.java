package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class forgot_password extends AppCompatActivity {

    final MediaPlayer[] mp = new MediaPlayer[1];
    private TextView fail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        Button sett = (Button) findViewById(R.id.settings);
        Button fgtpass = (Button) findViewById(R.id.p_r_button);

        fail = (TextView) findViewById(R.id.email_send);
        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(forgot_password.this, R.raw.but_click);
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
                Intent intent = new Intent(forgot_password.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        Button back = (Button) findViewById(R.id.go_back);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(forgot_password.this, R.raw.but_click);
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
                Intent intent = new Intent(forgot_password.this, Cust_Login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left,R.anim.to_right);
            }

        });

        fgtpass.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                fail.setText("Recovery Email Sent");
            }

        });


    }
}