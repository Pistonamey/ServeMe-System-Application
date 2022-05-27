package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.*;

public class date_time extends AppCompatActivity {

    public static final String DATE_S_TYPE = "DATE_S_TYPE";
    final MediaPlayer[] mp = new MediaPlayer[1];
    private EditText cust_date, cust_time;
    private Button time_next;
    private String cust_date_string;
    private String cust_time_string;
    private String typeString;
    private String month,day,year, hour, minutes;
    //ToDo add settings later



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_time);






        Intent intent = getIntent();

        typeString = intent.getStringExtra(DATE_S_TYPE);





        Button sett = (Button) findViewById(R.id.settings);
        //button to continue need to provide checks here
        cust_date = findViewById(R.id.et_cust_date);
        cust_time = findViewById(R.id.et_cust_time);
        time_next = findViewById(R.id.time_next);


        time_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(date_time.this, R.raw.but_click);
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
                //method to send data
                addData();
            }
        });

        sett.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(date_time.this, R.raw.but_click);
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
                Intent intent = new Intent(date_time.this, Settings.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_right,R.anim.to_left);
            }

        });

        Button back = (Button) findViewById(R.id.go_back);

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(MainActivity.so) {
                    mp[0] = MediaPlayer.create(date_time.this, R.raw.but_click);
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
                Intent intent = new Intent(date_time.this, Serv_req.class);
                startActivity(intent);
                overridePendingTransition(R.anim.from_left,R.anim.to_right);
            }

        });



    }
    public void addData()
    {
        //Test cases for date and time
        cust_date_string = cust_date.getText().toString().trim();
        cust_time_string = cust_time.getText().toString().trim();
        int count = 0;



        for(int i = 0; i < cust_date_string.length(); i++)
        {
            if(cust_date_string.charAt(i) == '/')
            {
                count++;
            }
        }

        if(count != 2)
        {
            cust_date.setError("Enter a valid date.");
            cust_date.requestFocus();
            return;
        }

        String [] dateParts = cust_date_string.split("/");
        month=dateParts[0];
        day=dateParts[1];
        year=dateParts[2];



        if(!isNumeric(month))
        {
            cust_date.setError("Enter a valid date.");
            cust_date.requestFocus();
            return;
        }
        else {
            int month_int = Integer.parseInt(month);
            if (month_int < 1 || month_int > 12)
            {
                cust_date.setError("Enter a valid date.");
                cust_date.requestFocus();
                return;
            }


        }

        if(!isNumeric(day))
        {
            cust_date.setError("Enter a valid date.");
            cust_date.requestFocus();
            return;
        }
        else {
            int day_int = Integer.parseInt(day);
            if (day_int < 1 || day_int > 31)
            {
                cust_date.setError("Enter a valid date.");
                cust_date.requestFocus();
                return;
            }


        }


        if(!isNumeric(year))
        {
            cust_date.setError("Enter a valid date.");
            cust_date.requestFocus();
            return;
        }
        else {
            int year_int = Integer.parseInt(year);
            if (year_int !=22)
            {
                cust_date.setError("Enter a valid date.");
                cust_date.requestFocus();
                return;
            }


        }



        //Test Cases for Time
        //count for checking : count
        int count2 = 0;
        for(int i = 0; i < cust_time_string.length(); i++)
        {
            if(cust_time_string.charAt(i) == ':')
            {
                count2++;
            }
        }



        if(count2 != 1)
        {
            cust_time.setError("Enter a valid time.");
            cust_time.requestFocus();
            return;
        }

        String [] timeParts = cust_time_string.split(":");
        hour=timeParts[0];
        minutes=timeParts[1];


        if(!isNumeric(hour))
        {
            cust_time.setError("Enter a valid time.");
            cust_time.requestFocus();
            return;
        }
        else {
            int hour_int = Integer.parseInt(hour);
            if (hour_int <0 || hour_int>24)
            {
                cust_time.setError("Enter a valid time.");
                cust_time.requestFocus();
                return;
            }


        }


        if(!isNumeric(minutes))
        {
            cust_time.setError("Enter a valid time.");
            cust_time.requestFocus();
            return;
        }
        else {
            int min_int = Integer.parseInt(minutes);
            if (min_int <0 || min_int>60)
            {
                cust_time.setError("Enter a valid time.");
                cust_time.requestFocus();
                return;
            }


        }














        Intent intent = new Intent(date_time.this, jb_des.class);



        intent.putExtra(jb_des.DATE, cust_date_string);
        intent.putExtra(jb_des.TIME, cust_time_string);
        intent.putExtra(jb_des.S_TYPE, typeString);

        startActivity(intent);
        overridePendingTransition(R.anim.from_right,R.anim.to_left);
    }

    public static boolean isNumeric(String str)
    {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }






}