package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class just_try extends AppCompatActivity {

    public static final String DATE = "DATE";
    public static final String TIME = "TIME";
    public static final String S_TYPE = "S_TYPE";

    private TextView dateText, timeText, typeText;
    private String dateString, timeString, typeString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_just_try);


        Intent intent = getIntent();

        dateText = findViewById(R.id.dateView);
        timeText = findViewById(R.id.timeView);
        typeText = findViewById(R.id.s_typeView);


        dateString = intent.getStringExtra(DATE);
        timeString = intent.getStringExtra(TIME);
        typeString = intent.getStringExtra(S_TYPE);

        dateText.setText(dateString);
        timeText.setText(timeString);
        typeText.setText(typeString);
    }
}