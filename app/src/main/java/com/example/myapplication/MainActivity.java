package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.VideoView;
import android.app.Notification;
import android.app.NotificationManager;

public class MainActivity extends AppCompatActivity {

    private VideoView videoBG;
    public static Boolean so = false;
    MediaPlayer mMediaPlayer;
    final MediaPlayer[] mp = new MediaPlayer[1];
    int mCurrentVideoPosition;
    private NotificationManagerCompat notificationManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        videoBG = (VideoView) findViewById(R.id.bg_vid);

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.ames);

        videoBG.setVideoURI(uri);
        videoBG.start();

        videoBG.setOnPreparedListener((mediaPlayer) -> {
            mMediaPlayer = mediaPlayer;
            mMediaPlayer.setLooping(true);
            if (mCurrentVideoPosition != 0) {
                mMediaPlayer.seekTo(mCurrentVideoPosition);
                mMediaPlayer.start();
            }
        });

        Button log = (Button) findViewById(R.id.login_button);
        Button regis = (Button) findViewById(R.id.register_button);
        Button sett = (Button) findViewById(R.id.settings);

        log.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(so) {
                    mp[0] = MediaPlayer.create(MainActivity.this, R.raw.but_click);
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
                Intent intent = new Intent(MainActivity.this, Login_main.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });
        regis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(so) {
                    mp[0] = MediaPlayer.create(MainActivity.this, R.raw.but_click);
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
                Intent intent = new Intent(MainActivity.this, Register_main.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }
        });
        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(so) {
                    mp[0] = MediaPlayer.create(MainActivity.this, R.raw.but_click);
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
                Intent intent = new Intent(MainActivity.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left,R.anim.to_right);
            }

        });

    }






    @Override
    protected void onPause() {
        super.onPause();
        mCurrentVideoPosition = mMediaPlayer.getCurrentPosition();
        videoBG.pause();
    }

    @Override
    protected void onResume(){
        super.onResume();
        videoBG.start();
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        mMediaPlayer.release();
        mMediaPlayer=null;
    }


}

