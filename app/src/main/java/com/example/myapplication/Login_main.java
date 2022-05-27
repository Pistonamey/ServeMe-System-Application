package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Login_main extends AppCompatActivity {

    MediaPlayer mMediaPlayer;
    final MediaPlayer[] mp = new MediaPlayer[1];
    public static Boolean so = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_main);
        Intent intent = getIntent();

        Button sett = (Button) findViewById(R.id.settings);
        Button lg_cust = (Button) findViewById(R.id.cust);
        Button lg_vend=   (Button) findViewById(R.id.vend);
        Button back = (Button) findViewById(R.id.go_back);

        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Login_main.this, R.raw.but_click);
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
                Intent intent = new Intent(Login_main.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Login_main.this, R.raw.but_click);
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
                Intent intent = new Intent(Login_main.this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left,R.anim.to_right);
            }

        });

        lg_cust.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Login_main.this, R.raw.but_click);
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
                Intent intent = new Intent(Login_main.this, Cust_Login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        lg_vend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Login_main.this, R.raw.but_click);
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
                Intent intent = new Intent(Login_main.this, Vend_login.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });
    }
}