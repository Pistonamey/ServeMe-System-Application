package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class req_completed extends AppCompatActivity {

    private VideoView BGSPL;
    final MediaPlayer[] mp = new MediaPlayer[1];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_req_completed);

        BGSPL = findViewById(R.id.bg_spl);

        String path = "android.resource://com.example.myapplication/"+ R.raw.service_placed;

        Uri uri = Uri.parse(path);
        BGSPL.setVideoURI(uri);
        BGSPL.start();

        BGSPL.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();

            }
        });
        BottomNavigationView bottomNav = findViewById(R.id.nav_bar);

        Button sett = (Button) findViewById(R.id.settings);
        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(req_completed.this, R.raw.but_click);
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
                Intent intent = new Intent(req_completed.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        bottomNav.setOnNavigationItemSelectedListener(navListener);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch (item.getItemId()) {
                case R.id.nav_home:
                    Intent intent = new Intent(req_completed.this, Cust_main_menu.class);
                    startActivity(intent);
                    break;
                case R.id.nav_dash:
                    Intent nat = new Intent(req_completed.this, Settings.class);
                    startActivity(nat);
                    break;
                case R.id.nav_info:
                    Intent ban = new Intent(req_completed.this, Serv_req.class);
                    startActivity(ban);
                    break;
            }

            return true;
        }
    };

    }
