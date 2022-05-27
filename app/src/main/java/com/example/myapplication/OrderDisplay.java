package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class OrderDisplay extends AppCompatActivity {

    //strings to put in putExtra key value pair in first activity
    public static final String DATE = "DATE";
    public static final String TIME = "TIME";

    private TextView dateText, timeText;
    private String dateString, timeString;




    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_display);
        Intent intent = getIntent();

        //dateText = findViewById(R.id.dateView);
        //timeText = findViewById(R.id.timeView);


        //dateString = intent.getStringExtra(DATE);
        //timeString = intent.getStringExtra(TIME);

        //dateText.setText(dateString);
        // timeText.setText(timeString);




    }
}