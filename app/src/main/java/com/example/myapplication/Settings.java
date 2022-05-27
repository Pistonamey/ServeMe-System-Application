package com.example.myapplication;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class Settings extends AppCompatActivity  {



    //for reference to current logged in user
    private FirebaseUser user;
    //for reference to database
    private DatabaseReference reference;

    //for id of currently logged in user
    //each user in Firebase database has unique id
    private String userID;

    private Button btn_logOut;











    @Override
    protected void onCreate(Bundle savedInstanceState) {



       Intent intent = getIntent();











        final MediaPlayer[] mp = new MediaPlayer[1];
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        Button sound = (Button) findViewById(R.id.sound);
        Button notif = (Button) findViewById(R.id.notify);
        Button btn_logOut = (Button) findViewById(R.id.set_log_out);
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(Settings.this, R.raw.but_click);
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
                AlertDialog.Builder altdial = new AlertDialog.Builder(Settings.this);
                altdial.setMessage("Confirm?").setCancelable(false).setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //send all data to vender registeration database
                        MainActivity.so=true;
                        if(MainActivity.so) {
                            mp[0] = MediaPlayer.create(Settings.this, R.raw.but_click);
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

                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.so = false;

                        dialog.cancel();
                    }
                });

                AlertDialog alert = altdial.create();
                alert.show();
            }
        });

        notif.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                openDialog2();
            }
        });

        btn_logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                FirebaseAuth.getInstance().signOut();

                triggerRebirth(Settings.this);

                //uncomment down
                /*Intent intent = new Intent(Settings.this, SplashScreen.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);*/
                //uncommnet up
            }
        });
    }



    public void openDialog1()
    {
        dialog_snd dl_snd=new dialog_snd();
        dl_snd.show(getSupportFragmentManager(),"example dialog");
    }

    public void openDialog2()
    {
        dialog_notif dl_not=new dialog_notif();
        dl_not.show(getSupportFragmentManager(),"example dialog");
    }

    public static void triggerRebirth(Context context) {
        PackageManager packageManager = context.getPackageManager();
        Intent intent = packageManager.getLaunchIntentForPackage(context.getPackageName());
        ComponentName componentName = intent.getComponent();
        Intent mainIntent = Intent.makeRestartActivityTask(componentName);
        context.startActivity(mainIntent);
        Runtime.getRuntime().exit(0);
    }


}